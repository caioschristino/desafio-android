package com.v2.desafionubank.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.v2.desafionubank.R;
import com.v2.desafionubank.adapter.RecyclerViewAdapter;
import com.v2.desafionubank.model.NoticeAction;
import com.v2.desafionubank.model.ResponseNotice;
import com.v2.desafionubank.ui.BaseFragment;
import com.v2.desafionubank.ui.view.ActionViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by CaioSChristino on 24/03/18.
 */

public class NoticeFragment extends BaseFragment {
    @BindView(R.id.title_notice)
    TextView mTitle;
    @BindView(R.id.body_notice)
    TextView mBody;
    @BindView(R.id.recycle_action)
    RecyclerView mRecyclerView;

    @Override
    protected void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final List<NoticeAction> mActions = new ArrayList<>();
        mSessionController.getNotice("https://nu-mobile-hiring.herokuapp.com/")
                .subscribe(new ObserverController<ResponseNotice>(getActivity().getApplicationContext()) {
                    @Override
                    public void onResult(ResponseNotice notice) {
                        if (null != notice) {
                            mTitle.setText(notice.getTitle());
                            mBody.setText(Html.fromHtml(notice.getDescription()));

                            if (null != notice.getPrimaryAction()) {
                                mActions.add(notice.getPrimaryAction());
                            }
                            if (null != notice.getSecondaryAction()) {
                                mActions.add(notice.getSecondaryAction());
                            }
                            adapter.addItems(mActions);
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    @Override
    protected int setContent() {
        return R.layout.notice_fragment;
    }

    @Override
    public void doInBackFragment() {

    }

    @Override
    public String getTitle() {
        return getResources().getString(R.string.app_name);
    }

    RecyclerViewAdapter<NoticeAction> adapter = new RecyclerViewAdapter<NoticeAction>(getActivity()) {
        @Override
        public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.action_item, parent, false);
            ActionViewHolder holder = new ActionViewHolder(view);
            return holder;
        }

        @Override
        public void onBindData(RecyclerView.ViewHolder holder, final NoticeAction item) {
            if (holder instanceof ActionViewHolder) {
                ActionViewHolder viewHolder = (ActionViewHolder) holder;

                viewHolder.setTitle(item);
                viewHolder.setOnclick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (item.getAction()) {
                            case "continue":
                                pushFragment(new ChargebackFragment());
                                break;
                            case "cancel":
                                popSelf();
                                break;
                        }
                    }
                });
            }
        }
    };
}
