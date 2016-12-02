package com.octopepper.mediapickerinstagram.commons.managers;

import android.content.Context;
import android.graphics.Bitmap;

import com.octopepper.mediapickerinstagram.R;
import com.octopepper.mediapickerinstagram.commons.models.Thumbnail;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailManager {

    private static List<Thumbnail> filterThumbs = new ArrayList<>(10);
    private static List<Thumbnail> processedThumbs = new ArrayList<>(10);

    private ThumbnailManager() {
    }

    public static void addThumb(Thumbnail thumbnail) {
        filterThumbs.add(thumbnail);
    }

    public static List<Thumbnail> processThumbs(Context context) {
        for (Thumbnail thumb : filterThumbs) {
            float size = context.getResources().getDimension(R.dimen.thumbnail_size);
            thumb.image = Bitmap.createScaledBitmap(thumb.image, (int) size, (int) size, false);
            thumb.image = thumb.filter.processFilter(thumb.image);
            processedThumbs.add(thumb);
        }
        return processedThumbs;
    }

    public static void clearThumbs() {
        filterThumbs = new ArrayList<>();
        processedThumbs = new ArrayList<>();
    }

}
