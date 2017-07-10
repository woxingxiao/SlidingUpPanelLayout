package com.xw.repo;

import android.view.View;

/**
 * <></>
 * Created by woxignxiao on 2017-07-10.
 */

public interface ISlidingUpPanel<T extends View> {

    T getPanelView();

    int getExpendedHeight();

    int getCollapsedHeight();

    @SlidingUpPanelLayout.SlideState
    int getSlideState();

    void setSlideState(@SlidingUpPanelLayout.SlideState int slideState);

    int getPanelViewTopBySlidingState();

    void updateTop(int top, int slidingViewRealHeight);

    void onSliding(int top, int dy);
}
