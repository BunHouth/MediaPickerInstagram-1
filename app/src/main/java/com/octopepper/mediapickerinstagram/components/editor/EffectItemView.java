package com.octopepper.mediapickerinstagram.components.editor;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.octopepper.mediapickerinstagram.R;
import com.octopepper.mediapickerinstagram.commons.models.Session;
import com.octopepper.mediapickerinstagram.commons.models.enums.EffectType;
import com.octopepper.mediapickerinstagram.commons.modules.ReboundModule;
import com.octopepper.mediapickerinstagram.commons.modules.ReboundModuleDelegate;
import com.octopepper.mediapickerinstagram.commons.ui.CustomTextView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EffectItemView extends LinearLayout implements ReboundModuleDelegate {

    @BindView(R.id.mEffectTypeView)
    ImageView mEffectTypeView;
    @BindView(R.id.mEffectName)
    CustomTextView mEffectName;

    private Session mSession = Session.getInstance();
    private ReboundModule mReboundModule = ReboundModule.getInstance(this);
    private EffectType mCurrentEffectType;
    private WeakReference<EffectItemViewListener> mWrListener;

    void setListener(EffectItemViewListener listener) {
        this.mWrListener = new WeakReference<>(listener);
    }

    public EffectItemView(Context context) {
        super(context);
        View v = View.inflate(context, R.layout.effet_item_view, this);
        ButterKnife.bind(this, v);
    }

    public void bind(EffectType effectType) {
        mCurrentEffectType = effectType;
        mReboundModule.init(mEffectTypeView);
        mEffectName.setText(effectType.getName());
        Picasso.with(getContext())
                .load(Uri.fromFile(mSession.getFileToUpload()))
                .resize(350, 350)
                .centerCrop()
                .placeholder(R.drawable.placeholder_media)
                .error(R.drawable.placeholder_error_media)
                .noFade()
                .into(mEffectTypeView);
    }

    @Override
    public void onTouchActionUp() {
        mWrListener.get().onClickEffectType(mCurrentEffectType);
    }
}
