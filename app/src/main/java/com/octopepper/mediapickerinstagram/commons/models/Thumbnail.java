package com.octopepper.mediapickerinstagram.commons.models;

import android.graphics.Bitmap;

import com.zomato.photofilters.imageprocessors.Filter;

public class Thumbnail {

    public Bitmap image;
    public Filter filter;
    public String name;

    public Thumbnail() {
        image = null;
        filter = new Filter();
    }

}
