package com.xw.sample.slidinguppanellayout.demo1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import com.xw.repo.supl.ISlidingUpPanel;
import com.xw.repo.supl.SlidingUpPanelLayout;

import static com.xw.repo.supl.SlidingUpPanelLayout.COLLAPSED;

/**
 * <p>
 * Created by woxingxiao on 2017-07-10.
 */

public abstract class BaseWeatherPanelView extends CardView implements ISlidingUpPanel<BaseWeatherPanelView> {

    protected static int MAX_RADIUS;

    protected int mExpendedHeight;
    protected int mFloor; // 由下至上第几层，最高三层
    protected int mPanelHeight;
    protected int mRealPanelHeight;
    @SlidingUpPanelLayout.SlideState
    protected int mSlideState = COLLAPSED;
    protected float mSlope; // 斜率

    protected WeatherModel mWeather;

    public BaseWeatherPanelView(Context context) {
        this(context, null);
    }

    public BaseWeatherPanelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWeatherPanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        MAX_RADIUS = dp2px(16);
        setRadius(MAX_RADIUS);
        ViewCompat.setElevation(this, dp2px(16));
        setCardBackgroundColor(Color.TRANSPARENT);
    }

    public void setFloor(int floor) {
        mFloor = floor;

        mRealPanelHeight = 0;
    }

    public int getFloor() {
        return mFloor;
    }

    public void setPanelHeight(int panelHeight) {
        mPanelHeight = panelHeight;
    }

    public int getRealPanelHeight() {
        if (mRealPanelHeight == 0)
            mRealPanelHeight = mFloor * mPanelHeight;

        return mRealPanelHeight;
    }

    @NonNull
    @Override
    public BaseWeatherPanelView getPanelView() {
        return this;
    }

    @Override
    public int getPanelExpandedHeight() {
        if (mExpendedHeight == 0) {
            DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
            if (Build.VERSION.SDK_INT > 16) {
                WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                if (windowManager != null) {
                    windowManager.getDefaultDisplay().getRealMetrics(dm);
                }
            }
            mExpendedHeight = dm.heightPixels;
        }
        return mExpendedHeight;
    }

    @Override
    public int getPanelCollapsedHeight() {
        return getRealPanelHeight();
    }

    @Override
    @SlidingUpPanelLayout.SlideState
    public int getSlideState() {
        return mSlideState;
    }

    @Override
    public int getPanelTopBySlidingState(@SlidingUpPanelLayout.SlideState int slideState) {
        if (slideState == SlidingUpPanelLayout.EXPANDED) {
            return 0;
        } else if (slideState == COLLAPSED) {
            return getPanelExpandedHeight() - getPanelCollapsedHeight();
        } else if (slideState == SlidingUpPanelLayout.HIDDEN) {
            return getPanelExpandedHeight();
        }
        return 0;
    }

    @Override
    public void setSlideState(@SlidingUpPanelLayout.SlideState int slideState) {
        mSlideState = slideState;

        if (mSlideState != SlidingUpPanelLayout.EXPANDED) {
            mSlope = 0;
        }
    }

    @Override
    public void onSliding(@NonNull ISlidingUpPanel panel, int top, int dy, float slidedProgress) {
        if (panel != this) {
            int myTop = (int) (getPanelExpandedHeight() + getSlope(((BaseWeatherPanelView) panel).getRealPanelHeight()) * top);
            setTop(myTop);
        }
    }

    public float getSlope(int slidingViewRealHeight) {
        if (mSlope == 0) {
            mSlope = -1.0f * getRealPanelHeight() / (getPanelExpandedHeight() - slidingViewRealHeight);
        }

        return mSlope;
    }

    public abstract void setWeatherModel(WeatherModel weather);

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);
        ss.mSavedSlideState = mSlideState;

        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        mSlideState = ss.mSavedSlideState;
    }

    private static class SavedState extends View.BaseSavedState {
        int mSavedSlideState;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            mSavedSlideState = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(mSavedSlideState);
        }

        public static final Creator<SavedState> CREATOR =
                new Creator<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    @Override
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

    int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
