package com.octopepper.mediapickerinstagram.commons.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.octopepper.mediapickerinstagram.R;

public class CropImageView extends ImageView {

    private Paint mGuidelinePaint;
    private boolean isUp = true;

    public CropImageView(Context context) {
        super(context);
        init(context);
    }

    public CropImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Bitmap getCroppedImage() {

        final Drawable drawable = getDrawable();
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            return null;
        }

        final float[] matrixValues = new float[9];
        getImageMatrix().getValues(matrixValues);

        final float scaleX = matrixValues[Matrix.MSCALE_X];
        final float scaleY = matrixValues[Matrix.MSCALE_Y];
        final float transX = matrixValues[Matrix.MTRANS_X];
        final float transY = matrixValues[Matrix.MTRANS_Y];

        final float bitmapLeft = (transX < 0) ? Math.abs(transX) : 0;
        final float bitmapTop = (transY < 0) ? Math.abs(transY) : 0;

        final Bitmap originalBitmap = ((BitmapDrawable) drawable).getBitmap();

        final float cropX = (bitmapLeft + getLeft()) / scaleX;
        final float cropY = (bitmapTop + getTop()) / scaleY;

        final float cropWidth = Math.min(getWidth() / scaleX, originalBitmap.getWidth() - cropX);
        final float cropHeight = Math.min(getHeight() / scaleY, originalBitmap.getHeight() - cropY);

        return Bitmap.createBitmap(originalBitmap,
                (int) cropX,
                (int) cropY,
                (int) cropWidth,
                (int) cropHeight);
    }

    private void init(@NonNull Context context) {
        final Resources resources = context.getResources();
        mGuidelinePaint = newGuidelinePaint(resources, context);
    }

    private Paint newGuidelinePaint(@NonNull Resources resources, @NonNull Context context) {
        final Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(resources.getDimension(R.dimen.guideline_thickness));
        paint.setColor(ContextCompat.getColor(context, R.color.guideline));
        return paint;
    }

    private void drawGuidelines(@NonNull Canvas canvas) {

        if (isUp) {
            return;
        }

        final float left = getLeft();
        final float top = getTop();
        final float right = getRight();
        final float bottom = getBottom();

        final float oneThirdCropWidth = getWidth() / 3;
        final float x1 = left + oneThirdCropWidth;
        canvas.drawLine(x1, top, x1, bottom, mGuidelinePaint);
        final float x2 = right - oneThirdCropWidth;
        canvas.drawLine(x2, top, x2, bottom, mGuidelinePaint);

        final float oneThirdCropHeight = getHeight() / 3;
        final float y1 = top + oneThirdCropHeight;
        canvas.drawLine(left, y1, right, y1, mGuidelinePaint);
        final float y2 = bottom - oneThirdCropHeight;
        canvas.drawLine(left, y2, right, y2, mGuidelinePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onActionDown(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                onActionUp();
                return true;
            case MotionEvent.ACTION_MOVE:
                onActionMove(event.getX(), event.getY());
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGuidelines(canvas);
    }

    private void onActionDown(float x, float y) {
        isUp = false;
        invalidate();
    }

    private void onActionUp() {
        isUp = true;
        invalidate();
    }

    private void onActionMove(float x, float y) {
        isUp = false;
        invalidate();
    }

}
