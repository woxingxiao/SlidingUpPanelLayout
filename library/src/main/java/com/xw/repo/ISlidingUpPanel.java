package com.xw.repo;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Your views must implement this interface so the {@link SlidingUpPanelLayout} can work properly.
 * <p/>
 * Created by woxignxiao on 2017-07-10.
 */

public interface ISlidingUpPanel<T extends View> {

    /**
     * The view can be slided now.
     *
     * @return instance of a focused <b>Panel</b>.
     */
    @NonNull
    T getPanelView();

    /**
     * @return The height of a focused <b>Panel</b> when expanded. In pixels.
     */
    int getPanelExpandedHeight();

    /**
     * @return The height of a focused <b>Panel</b> when collapsed. In pixels.
     */
    int getPanelCollapsedHeight();

    /**
     * @return The {@link SlidingUpPanelLayout.SlideState} of a focused <b>Panel</b>.
     */
    @SlidingUpPanelLayout.SlideState
    int getSlideState();

    /**
     * @param slideState {@link SlidingUpPanelLayout.SlideState}
     */
    void setSlideState(@SlidingUpPanelLayout.SlideState int slideState);

    /**
     * This method would be called inside {@link SlidingUpPanelLayout#onLayout(boolean, int, int, int, int)}.
     * The value returned of this method decides the <b>Panel</b>s top pixels related to the parent.
     * <p>
     * Briefly, it controls initial position of <b>Panel</b> in parent.
     * </p>
     *
     * @return The <code>Top</code> of <b>Panel</b>. In pixels.
     */
    int getPanelTopBySlidingState(@SlidingUpPanelLayout.SlideState int slideState);

    /**
     * The method will be called when a <b>Panel</b> is being slided.
     *
     * @param panel          The <b>Panel</b> is being slided
     * @param top            The top of the <b>Panel</b> is being slided
     * @param dy             Change in Y position from the last call
     * @param slidedProgress {@link SlidingUpPanelLayout#mSlidedProgress}
     */
    void onSliding(@NonNull ISlidingUpPanel panel, int top, int dy, float slidedProgress);
}
