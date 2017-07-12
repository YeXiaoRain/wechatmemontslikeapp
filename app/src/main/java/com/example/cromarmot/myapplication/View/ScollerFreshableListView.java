package com.example.cromarmot.myapplication.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.cromarmot.myapplication.R;

/**
 * Created by cromarmot on 17-7-5.
 *
 * call setOnRefreshListener( customListener ) to set Listener
 *
 * after get new data call hideFooterView() to hide
 *
 */
public class ScollerFreshableListView extends ListView implements AbsListView.OnScrollListener {
    private FcScrollerListener mOnFooterRefershListener;
    private boolean isScrollToBottom;
    private View footerView;
    private int footerViewHeight;
    private boolean isLoadingMore = false;

    public ScollerFreshableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFooterView();
        this.setOnScrollListener(this);
    }

    //calculate and deploy
    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.view_footer, null);
        footerView.measure(0, 0);
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        this.addFooterView(footerView);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            if (isScrollToBottom && !isLoadingMore) {
                isLoadingMore = true;
                footerView.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());

                if (mOnFooterRefershListener != null) {
                    mOnFooterRefershListener.onLoadingMore();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,  int visibleItemCount, int totalItemCount) {
        isScrollToBottom = getLastVisiblePosition() == (totalItemCount - 1);
    }

    public void hideFooterView() {
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        isLoadingMore = false;
    }

    public void setOnRefreshListener(FcScrollerListener listener) {
        mOnFooterRefershListener = listener;
    }
}
