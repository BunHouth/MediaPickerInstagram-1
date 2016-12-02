package com.octopepper.mediapickerinstagram.components.editor;

import android.content.Context;
import android.view.ViewGroup;

import com.octopepper.mediapickerinstagram.commons.adapters.RecyclerViewAdapterBase;
import com.octopepper.mediapickerinstagram.commons.adapters.ViewWrapper;
import com.octopepper.mediapickerinstagram.commons.models.Thumbnail;
import com.zomato.photofilters.imageprocessors.Filter;

import java.lang.ref.WeakReference;

class EffectAdapter extends RecyclerViewAdapterBase<Thumbnail, EffectItemView> implements EffectItemViewListener {

    private Context mContext;
    private WeakReference<EffectAdapterListener> mWrListener;

    void setListener(EffectAdapterListener listener) {
        this.mWrListener = new WeakReference<>(listener);
    }

    EffectAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    protected EffectItemView onCreateItemView(ViewGroup parent, int viewType) {
        return new EffectItemView(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<EffectItemView> holder, int position) {
        EffectItemView effectItemView = holder.getView();
        effectItemView.setListener(this);
        effectItemView.bind(mItems.get(position));
    }

    @Override
    public void onClickEffectType(Filter filter) {
        mWrListener.get().applyEffectType(filter);
    }

}