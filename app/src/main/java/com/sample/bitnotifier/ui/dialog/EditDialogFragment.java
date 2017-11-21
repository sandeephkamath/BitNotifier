package com.sample.bitnotifier.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sample.bitnotifier.R;
import com.sample.bitnotifier.application.BitNotifierApplication;
import com.sample.bitnotifier.interfaces.OnDataChangeListener;
import com.sample.bitnotifier.model.BitCoin;
import com.sample.bitnotifier.model.BitCoinModel;
import com.sample.bitnotifier.utils.SharedPrefUtils;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EditDialogFragment extends DialogFragment {

    @Inject
    SharedPrefUtils sharedPrefUtils;

    @BindView(R.id.last_traded_price)
    TextView lastTradedPrice;

    @BindView(R.id.value)
    EditText valueEdit;

    @BindView(R.id.range)
    EditText rangeEdit;

    @BindView(R.id.notification)
    Button notification;

    private OnDataChangeListener listener;

    @OnClick(R.id.ok)
    void onOk() {
        saveData();
        if (listener != null) {
            listener.onDataChange();
        }
        dismiss();
    }

    @OnClick(R.id.notification)
    void onNotificationToggle() {
        if (getString(R.string.start_notification).equalsIgnoreCase(notification.getText().toString())) {
            notification.setText(getString(R.string.stop_notification));
        } else {
            notification.setText(getString(R.string.start_notification));
        }
    }

    @OnClick(R.id.cancel)
    void onCancel() {
        dismiss();
    }

    private static final String BITCOIN = "bitcoin";
    private BitCoin bitCoin;

    public EditDialogFragment() {
        // Required empty public constructor
    }

    public static EditDialogFragment newInstance(BitCoin bitCoin) {
        EditDialogFragment editDialogFragment = new EditDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BITCOIN, bitCoin);
        editDialogFragment.setArguments(bundle);
        return editDialogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BitNotifierApplication.getApplicationComponent().inject(this);
        if (getArguments() != null) {
            bitCoin = getArguments().getParcelable(BITCOIN);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_dialog, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        BitCoinModel bitCoinModel = bitCoin.getBitCoinModel();
        lastTradedPrice.setText(bitCoinModel.getLast_traded_price());
        valueEdit.setText(String.format(Locale.ENGLISH, "%d", bitCoinModel.getSetValue()));
        rangeEdit.setText(String.format(Locale.ENGLISH, "%d", bitCoinModel.getRange()));
        if (sharedPrefUtils.getNotifyStatus(bitCoin.getTitle())) {
            notification.setText(getString(R.string.stop_notification));
        } else {
            notification.setText(getString(R.string.start_notification));
        }
    }


    private void saveData() {
        sharedPrefUtils.setValue(Long.parseLong(valueEdit.getText().toString()), bitCoin.getTitle());
        sharedPrefUtils.setRange(Long.parseLong(rangeEdit.getText().toString()), bitCoin.getTitle());
        if (getString(R.string.start_notification).equalsIgnoreCase(notification.getText().toString())) {
            sharedPrefUtils.setNotify(false, bitCoin.getTitle());
        } else {
            sharedPrefUtils.setNotify(true, bitCoin.getTitle());
        }
    }

    public void setChangeListener(OnDataChangeListener listener) {
        this.listener = listener;
    }
}
