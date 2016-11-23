package com.octopepper.mediapickerinstagram.components.gallery;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.octopepper.mediapickerinstagram.R;
import com.octopepper.mediapickerinstagram.commons.models.Session;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryPickerFragment extends Fragment implements GridAdapterListener {

    @BindView(R.id.mGalleryRecyclerView)
    RecyclerView mGalleryRecyclerView;
    @BindView(R.id.mPreview)
    ImageView mPreview;
    @BindView(R.id.mAppBarContainer)
    AppBarLayout mAppBarContainer;

    private static final String EXTENSION_JPG = ".jpg";
    private static final String EXTENSION_JPEG = ".jpeg";
    private static final String EXTENSION_PNG = ".png";
    private static final int MARGING_GRID = 2;

    private Session mSession = Session.getInstance();
    private GridAdapter mGridAdapter;
    private ArrayList<File> mFiles;
    private boolean isFirstLoad = true;

    public static GalleryPickerFragment newInstance() {
        return new GalleryPickerFragment();
    }

    private void initViews() {
        if (isFirstLoad) {
            mGridAdapter = new GridAdapter(getContext());
        }
        mGridAdapter.setListener(this);
        mGalleryRecyclerView.setAdapter(mGridAdapter);
        mGalleryRecyclerView.setHasFixedSize(true);
        mGalleryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mGalleryRecyclerView.addItemDecoration(addItemDecoration());
        fetchMedia();
    }

    private RecyclerView.ItemDecoration addItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view,
                                       RecyclerView parent, RecyclerView.State state) {
                outRect.left = MARGING_GRID;
                outRect.right = MARGING_GRID;
                outRect.bottom = MARGING_GRID;
                if (parent.getChildLayoutPosition(view) >= 0 && parent.getChildLayoutPosition(view) <= 3) {
                    outRect.top = MARGING_GRID;
                }
            }
        };
    }

    private void fetchMedia() {
        mFiles = new ArrayList<>();
        File dirDownloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        parseDir(dirDownloads);
        File dirDcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        parseDir(dirDcim);
        File dirPictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        parseDir(dirPictures);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            File dirDocuments = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            parseDir(dirDocuments);
        }

        if (mFiles.size() > 0) {
            displayPreview(mFiles.get(0));
            mGridAdapter.setItems(mFiles); // getRangePets()
        }
        isFirstLoad = false;
    }

    private void parseDir(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            parseFileList(files);
        }
    }

    private void parseFileList(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                if (!file.getName().toLowerCase().startsWith(".")) {
                    parseDir(file);
                }
            } else {
                if (file.getName().toLowerCase().endsWith(EXTENSION_JPG)
                        || file.getName().toLowerCase().endsWith(EXTENSION_JPEG)
                        || file.getName().toLowerCase().endsWith(EXTENSION_PNG)) {
                    mFiles.add(file);
                }
            }
        }
    }

    private void displayPreview(File file) {
        mSession.setFileToUpload(file);
        Picasso.with(getContext())
                .load(Uri.fromFile(file))
                .noFade()
                .noPlaceholder()
                .transform(setTransformation())
                .into(mPreview);
    }

    private Transformation setTransformation() {
        return new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth, targetHeight;
                double aspectRatio;

                if (source.getWidth() > source.getHeight()) {
                    targetWidth = getMaxSize(mPreview.getHeight());
                    aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                    targetHeight = (int) (targetWidth * aspectRatio);
                } else if (source.getWidth() < source.getHeight()) {
                    targetHeight = getMaxSize(mPreview.getWidth());
                    aspectRatio = (double) source.getWidth() / (double) source.getHeight();
                    targetWidth = (int) (targetHeight * aspectRatio);
                } else {
                    targetHeight = mPreview.getWidth();
                    aspectRatio = (double) source.getWidth() / (double) source.getHeight();
                    targetWidth = (int) (targetHeight * aspectRatio);
                }

                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return mPreview.getWidth() + "x" + mPreview.getHeight();
            }
        };
    }

    private int getMaxSize(int size) {
        double d = size * 1.5;
        return (int) Math.round(d);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gallery_picker_view, container, false);
        ButterKnife.bind(this, v);
        initViews();
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        Picasso.with(getContext()).cancelRequest(mPreview);
    }

    @Override
    public void onClickMediaItem(File file) {
        displayPreview(file);
        mAppBarContainer.setExpanded(true, true);
    }

}