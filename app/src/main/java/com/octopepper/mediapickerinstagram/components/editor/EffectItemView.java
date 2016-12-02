package com.octopepper.mediapickerinstagram.components.editor;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.octopepper.mediapickerinstagram.R;
import com.octopepper.mediapickerinstagram.commons.models.Thumbnail;
import com.octopepper.mediapickerinstagram.commons.modules.ReboundModule;
import com.octopepper.mediapickerinstagram.commons.modules.ReboundModuleDelegate;
import com.octopepper.mediapickerinstagram.commons.ui.CustomTextView;
import com.zomato.photofilters.imageprocessors.Filter;

import java.lang.ref.WeakReference;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EffectItemView extends LinearLayout implements ReboundModuleDelegate {

    @BindView(R.id.mEffectTypeView)
    ImageView mEffectTypeView;
    @BindView(R.id.mEffectName)
    CustomTextView mEffectName;

    @BindColor(R.color.dark_chocolate)
    int _darkChocolate;
    @BindColor(R.color.light_cream)
    int _lightCream;

    private ReboundModule mReboundModule = ReboundModule.getInstance(this);
    private WeakReference<EffectItemViewListener> mWrListener;
    private Filter mFilter;

    void setListener(EffectItemViewListener listener) {
        this.mWrListener = new WeakReference<>(listener);
    }

    public EffectItemView(Context context) {
        super(context);
        View v = View.inflate(context, R.layout.effet_item_view, this);
        ButterKnife.bind(this, v);
    }

    public void bind(Thumbnail thumbnail) {
        mReboundModule.init(mEffectTypeView);
        mEffectName.setText(thumbnail.name);

        // TODO change text color if isSelected

        mEffectTypeView.setImageBitmap(thumbnail.image);
        mFilter = thumbnail.filter;
    }

    @Override
    public void onTouchActionUp() {
        mWrListener.get().onClickEffectType(mFilter);
    }

}
