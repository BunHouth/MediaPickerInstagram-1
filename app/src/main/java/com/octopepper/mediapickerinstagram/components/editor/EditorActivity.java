package com.octopepper.mediapickerinstagram.components.editor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.octopepper.mediapickerinstagram.R;
import com.octopepper.mediapickerinstagram.commons.managers.ThumbnailManager;
import com.octopepper.mediapickerinstagram.commons.models.Session;
import com.octopepper.mediapickerinstagram.commons.models.Thumbnail;
import com.octopepper.mediapickerinstagram.commons.ui.ToolbarView;
import com.squareup.picasso.Picasso;
import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditorActivity extends AppCompatActivity implements ToolbarView.OnClickTitleListener,
        ToolbarView.OnClickNextListener, ToolbarView.OnClickBackListener,
        EffectAdapterListener {

    static {
        System.loadLibrary("NativeImageProcessor");
    }

    @BindView(R.id.mEditorToolbar)
    ToolbarView mEditorToolbar;
    @BindView(R.id.mEffectPreview)
    ImageView mEffectPreview;
    @BindView(R.id.mEffectChooserRecyclerView)
    RecyclerView mEffectChooserRecyclerView;

    @BindString(R.string.toolbar_title_editor)
    String _toolbarTitleEditor;

    private Session mSession = Session.getInstance();
    private Filter mCurrentFilter = null;

    private void initViews() {
        mEditorToolbar.setOnClickBackMenuListener(this)
                .setOnClickTitleListener(this)
                .setOnClickNextListener(this)
                .setTitle(_toolbarTitleEditor)
                .showNext();

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mEffectPreview.getLayoutParams();
        lp.height = getResources().getDisplayMetrics().widthPixels;
        mEffectPreview.setLayoutParams(lp);

        mEffectChooserRecyclerView.setHasFixedSize(true);
        mEffectChooserRecyclerView.setItemAnimator(new DefaultItemAnimator());
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mEffectChooserRecyclerView.setLayoutManager(mLayoutManager);
        final EffectAdapter effectAdapter = new EffectAdapter(this);
        effectAdapter.setListener(this);
        mEffectChooserRecyclerView.setAdapter(effectAdapter);

        Picasso.with(this).load(Uri.fromFile(mSession.getFileToUpload()))
                .noFade()
                .noPlaceholder()
                .into(mEffectPreview);
        mEffectPreview.setOnTouchListener(setOnTouchListener());

        effectAdapter.setItems(getFilters());
    }

    private List<Thumbnail> getFilters() {
        Bitmap bitmap = getBitmapFromFile();

        Thumbnail t1 = new Thumbnail();
        Thumbnail t2 = new Thumbnail();
        Thumbnail t3 = new Thumbnail();
        Thumbnail t4 = new Thumbnail();
        Thumbnail t5 = new Thumbnail();
        Thumbnail t6 = new Thumbnail();
        Thumbnail t7 = new Thumbnail();

        t1.image = bitmap;
        t2.image = bitmap;
        t3.image = bitmap;
        t4.image = bitmap;
        t5.image = bitmap;
        t6.image = bitmap;
        t7.image = bitmap;

        ThumbnailManager.clearThumbs();
        t1.name = "None";
        ThumbnailManager.addThumb(t1);

        t2.name = "StarLit";
        t2.filter = SampleFilters.getStarLitFilter();
        ThumbnailManager.addThumb(t2);

        t3.name = "BlueMess";
        t3.filter = SampleFilters.getBlueMessFilter();
        ThumbnailManager.addThumb(t3);

        t4.name = "AweStruckVibe";
        t4.filter = SampleFilters.getAweStruckVibeFilter();
        ThumbnailManager.addThumb(t4);

        t5.name = "Lime";
        t5.filter = SampleFilters.getLimeStutterFilter();
        ThumbnailManager.addThumb(t5);

        t6.name = "B&W";
        t6.filter = new Filter();
        t6.filter.addSubFilter(new SaturationSubfilter(-100f));
        ThumbnailManager.addThumb(t6);

        t7.name = "Sepia";
        t7.filter = new Filter();
        t7.filter.addSubFilter(new SaturationSubfilter(-100f));
        t7.filter.addSubFilter(new ColorOverlaySubfilter(1, 102, 51, 0));
        ThumbnailManager.addThumb(t7);

        return ThumbnailManager.processThumbs(this);
    }

    private Bitmap getBitmapFromFile() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inMutable = true;
        return BitmapFactory.decodeFile(mSession.getFileToUpload().getAbsolutePath(), options);
    }

    private View.OnTouchListener setOnTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int action = motionEvent.getAction();
                if (mCurrentFilter != null) {
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            mEffectPreview.setImageBitmap(getBitmapFromFile());
                            break;
                        case MotionEvent.ACTION_UP:
                            mEffectPreview.setImageBitmap(mCurrentFilter.processFilter(getBitmapFromFile()));
                            break;
                        default:
                            break;
                    }
                }

                return true;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_view);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in_right, R.anim.zoom_out);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_in, R.anim.slide_out_right);
    }

    @Override
    public void onClickBack() {
        this.onBackPressed();
    }

    @Override
    public void onClickNext() {

    }

    @Override
    public void onClickTitle() {
    }

    @Override
    public void applyEffectType(Filter filter) {
        if (filter != mCurrentFilter) {
            mCurrentFilter = filter;
            mEffectPreview.setImageBitmap(filter.processFilter(getBitmapFromFile()));
        }
    }
}
