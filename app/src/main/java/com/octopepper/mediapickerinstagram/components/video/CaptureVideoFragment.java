package com.octopepper.mediapickerinstagram.components.video;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.octopepper.mediapickerinstagram.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CaptureVideoFragment extends Fragment {

    @BindView(R.id.mCameraVideoView)
    View mCameraVideoView;

    public static CaptureVideoFragment newInstance() {
        return new CaptureVideoFragment();
    }

    private void initViews() {
        float heightDp = getResources().getDisplayMetrics().heightPixels / 1.75f;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mCameraVideoView.getLayoutParams();
        lp.height = (int)heightDp;
        mCameraVideoView.setLayoutParams(lp);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.capture_video_view, container, false);
        ButterKnife.bind(this, v);
        initViews();
        return v;
    }

}
