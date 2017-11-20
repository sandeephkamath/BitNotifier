package com.sample.bitnotifier.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.sample.bitnotifier.R;
import com.sample.bitnotifier.model.BaseModel;
import com.sample.bitnotifier.model.Price;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PriceHolder extends BaseViewHolder {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.value)
    TextView value;

    public PriceHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(BaseModel baseModel) {
        if (!(baseModel instanceof Price)) return;
        title.setText(((Price) baseModel).getTitle());
        value.setText(((Price) baseModel).getValue());
    }
}
