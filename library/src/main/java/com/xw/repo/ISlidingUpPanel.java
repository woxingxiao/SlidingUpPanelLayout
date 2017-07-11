package com.xw.repo;

import android.view.View;

/**
 * <></>
 * Created by woxignxiao on 2017-07-10.
 */

public interface ISlidingUpPanel<T extends View> {

    T getPanelView();

    int getPanelExpendedHeight();

    int getPanelCollapsedHeight();

    @SlidingUpPanelLayout.SlideState
    int getSlideState();

    void setSlideState(@SlidingUpPanelLayout.SlideState int slideState);

    int getPanelTopBySlidingState();

    void onSliding(ISlidingUpPanel panel, int top, int dy, float slidedProgress);
}
