package com.sample.bitnotifier.ui.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sample.bitnotifier.R;
import com.sample.bitnotifier.model.BaseModel;
import com.sample.bitnotifier.model.Price;
import com.sample.bitnotifier.model.Prices;
import com.sample.bitnotifier.ui.MainAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PricesHolder extends BaseViewHolder {

    @BindView(R.id.prices_list)
    RecyclerView pricesList;

    ArrayList<BaseModel> prices;

    public PricesHolder(View itemView) {
        super(itemView);
        prices = new ArrayList<>();
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(BaseModel baseModel) {
        if (!(baseModel instanceof Prices)) return;
        prices.addAll(((Prices) baseModel).getPriceList());
        MainAdapter adapter = new MainAdapter(prices);
        pricesList.setLayoutManager(new LinearLayoutManager(pricesList.getContext()));
        pricesList.setAdapter(adapter);
    }
}
