package com.sample.bitnotifier.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.sample.bitnotifier.R;
import com.sample.bitnotifier.model.BaseModel;
import com.sample.bitnotifier.model.BitCoin;
import com.sample.bitnotifier.model.BitCoinModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BitCoinHolder extends BaseViewHolder {

    @BindView(R.id.last_traded_price)
    TextView lastTradedPrice;

    @BindView(R.id.lowest_ask)
    TextView lowestAsk;

    @BindView(R.id.highest_bid)
    TextView highestBid;

    @BindView(R.id.min_24hrs)
    TextView minHrs;

    @BindView(R.id.max_24hrs)
    TextView maxHrs;

    @BindView(R.id.vol_24hrs)
    TextView volHrs;

    @BindView(R.id.title)
    TextView title;


    public BitCoinHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(BaseModel baseModel) {
        if (!(baseModel instanceof BitCoin)) return;
        title.setText(((BitCoin) baseModel).getTitle());
        BitCoinModel bitCoinModel = ((BitCoin) baseModel).getBitCoinModel();
        lastTradedPrice.setText(bitCoinModel.getLast_traded_price());
        lowestAsk.setText(bitCoinModel.getLowest_ask());
        highestBid.setText(bitCoinModel.getHighest_bid());
        minHrs.setText(bitCoinModel.getMin_24hrs());
        maxHrs.setText(bitCoinModel.getMax_24hrs());
        volHrs.setText(bitCoinModel.getVol_24hrs());

    }
}
