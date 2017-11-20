package com.sample.bitnotifier.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sample.bitnotifier.model.BaseModel;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(BaseModel baseModel);
}
