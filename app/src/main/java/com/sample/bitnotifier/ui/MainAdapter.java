package com.sample.bitnotifier.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.bitnotifier.R;
import com.sample.bitnotifier.model.BaseModel;
import com.sample.bitnotifier.model.BitCoin;
import com.sample.bitnotifier.model.Price;
import com.sample.bitnotifier.model.Prices;
import com.sample.bitnotifier.ui.viewholder.BaseViewHolder;
import com.sample.bitnotifier.ui.viewholder.BitCoinHolder;
import com.sample.bitnotifier.ui.viewholder.PriceHolder;
import com.sample.bitnotifier.ui.viewholder.PricesHolder;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    ArrayList<BaseModel> list;

    private final int PRICES_VIEW_TYPE = 0;
    private final int BITCOIN_VIEW_TYPE = 1;
    private final int PRICE_VIEW_TYPE = 2;

    public MainAdapter(ArrayList<BaseModel> response) {
        list = response;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resource = getResourceType(viewType);
        BaseViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        switch (viewType) {
            case PRICE_VIEW_TYPE:
                viewHolder = new PriceHolder(view);
                break;
            case PRICES_VIEW_TYPE:
                viewHolder = new PricesHolder(view);
                break;
            case BITCOIN_VIEW_TYPE:
                viewHolder = new BitCoinHolder(view);
                break;
        }
        return viewHolder;
    }

    private int getResourceType(int viewType) {
        int resource;
        switch (viewType) {
            case PRICE_VIEW_TYPE:
                resource = R.layout.price_holder;
                break;
            case PRICES_VIEW_TYPE:
                resource = R.layout.prices_holder;
                break;
            case BITCOIN_VIEW_TYPE:
                resource = R.layout.bitcoin_holder;
                break;
            default:
                resource = 0;
                break;
        }
        return resource;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder != null) {
            holder.onBind(list.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list != null && list.size() >= 2) {
            if (list.get(position) instanceof Prices) {
                return PRICES_VIEW_TYPE;
            } else if (list.get(position) instanceof BitCoin) {
                return BITCOIN_VIEW_TYPE;
            } else if (list.get(position) instanceof Price) {
                return PRICE_VIEW_TYPE;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (list != null) {
            size = list.size();
        }
        return size;
    }
}
