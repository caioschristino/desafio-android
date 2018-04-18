package com.v2.desafionubank.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v2.desafionubank.R;
import com.v2.desafionubank.adapter.RecyclerViewAdapter;
import com.v2.desafionubank.model.ReasonDetails;
import com.v2.desafionubank.model.ResponseChargeback;
import com.v2.desafionubank.model.ResponsePost;
import com.v2.desafionubank.ui.BaseFragment;
import com.v2.desafionubank.ui.view.ReasonDetailsViewHolder;
import com.v2.desafionubank.ui.view.ViewDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by CaioSChristino on 12/04/18.
 */

public class ChargebackFragment extends BaseFragment {
    @BindView(R.id.chargeback_title)
    TextView mTitle;
    @BindView(R.id.padlock_image)
    ImageView mPadLockImage;
    @BindView(R.id.padlock_lock)
    TextView mPadLockText;
    @BindView(R.id.recycle_details)
    RecyclerView mRecyclerView;
    @BindView(R.id.about_block)
    EditText mAboutBlockEdit;
    @BindView(R.id.container_block)
    RelativeLayout mContainerBlock;

    @Override
    protected void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSessionController
                .getChargeback()
                .subscribe(new ObserverController<ResponseChargeback>(getActivity().getApplicationContext()) {
                    @Override
                    public void onResult(ResponseChargeback item) {
                        if (item != null) {
                            mContainerBlock.setVisibility(View.VISIBLE);
                            mAboutBlockEdit.setHint(Html.fromHtml(item.getHint()));
                            mTitle.setText(item.getTitle());
                            mPadLockImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_chargeback_lock));
                            adapter.addItems(item.getDetails());
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                });
    }


    @Override
    protected int setContent() {
        return R.layout.chargeback_fragment;
    }

    @Override
    public void doInBackFragment() {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @OnClick(R.id.contest_btn)
    public void onClickContest() {

    }

    @OnClick(R.id.cancel_btn)
    public void onClickCancel() {
        popSelf();
    }

    private void blockUnblockCard(boolean block) {
        mSessionController
                .blockUnblockCard(block)
                .subscribe(new ObserverController<ResponsePost>(getActivity().getApplicationContext()) {
                    @Override
                    public void onResult(ResponsePost item) {
                        if (item.isBlock()) {
                            mPadLockText.setText(getString(R.string.text_lock_on));
                            mPadLockImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_chargeback_lock));
                        } else {
                            mPadLockText.setText(getString(R.string.text_lock_off));
                            mPadLockImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_chargeback_unlock));
                        }
                    }

                    @Override
                    public boolean ignoreLoader() {
                        return true;
                    }
                });
    }

    @OnClick(R.id.padlock_image)
    public void padlockClick() {
        boolean block = true;
        if (mPadLockImage.getDrawable().getConstantState().equals
                (getResources().getDrawable(R.drawable.ic_chargeback_lock).getConstantState())) {
            block = false;
        }

        blockUnblockCard(block);
    }


    @OnClick(R.id.contest_btn)
    public void sendContest() {
        mSessionController
                .sendContest(mAboutBlockEdit.getText().toString(), adapter.getmItems())
                .subscribe(new ObserverController<ResponsePost>(getActivity().getApplicationContext()) {
                    @Override
                    public void onResult(ResponsePost item) {
                        new ViewDialog().showDialog(getActivity(), () -> {
                            popSelf();
                        });
                    }
                });
        hideKeyboard();
    }


    RecyclerViewAdapter<ReasonDetails> adapter = new RecyclerViewAdapter<ReasonDetails>(getActivity()) {
        @Override
        public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.reason_item, parent, false);
            ReasonDetailsViewHolder holder = new ReasonDetailsViewHolder(view);
            return holder;
        }

        @Override
        public void onBindData(RecyclerView.ViewHolder holder, ReasonDetails item) {
            if (holder instanceof ReasonDetailsViewHolder) {
                ReasonDetailsViewHolder viewHolder = (ReasonDetailsViewHolder) holder;
                viewHolder.setItem(item);
                viewHolder.setText(item.getTitle());
            }
        }
    };
}
