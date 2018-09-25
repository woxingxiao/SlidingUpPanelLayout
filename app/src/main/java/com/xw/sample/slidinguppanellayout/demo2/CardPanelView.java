package com.xw.sample.slidinguppanellayout.demo2;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xw.repo.supl.ISlidingUpPanel;
import com.xw.repo.supl.SlidingUpPanelLayout;
import com.xw.sample.slidinguppanellayout.R;

import static com.xw.repo.supl.SlidingUpPanelLayout.COLLAPSED;
import static com.xw.repo.supl.SlidingUpPanelLayout.EXPANDED;


/**
 * <p>
 * Created by woxingxiao on 2017-07-11.
 */

public class CardPanelView extends FrameLayout implements ISlidingUpPanel<CardPanelView> {

    private int mCardPosition;
    private int mCardCount;

    private int mPanelExpendedHeight;
    private int mPanelHeight;
    private int mPanelRealHeight;
    @SlidingUpPanelLayout.SlideState
    private int mSlideState = COLLAPSED;
    private int mPaddingTop;

    public CardPanelView(Context context) {
        this(context, null);
    }

    public CardPanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CardPanelView, defStyleAttr, 0);
        mCardPosition = a.getInt(R.styleable.CardPanelView_cpv_cardPosition, 0);
        mCardCount = a.getInt(R.styleable.CardPanelView_cpv_cardCount, 0);
        int imageRes = a.getResourceId(R.styleable.CardPanelView_cpv_cardImageRes, 0);
        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.content_card_panel_view, this, true);
        View cardView = findViewById(R.id.panel_card_view);
        ImageView cardImg = findViewById(R.id.panel_card_img);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = (int) (width * 10 / 16.0f); // W:H = 16:10
        LayoutParams layoutParams = new LayoutParams(width, height);
        cardView.setLayoutParams(layoutParams);
        if (imageRes != 0) {
            cardImg.setImageResource(imageRes);
        }

        mPanelHeight = dp2px(108) - mCardCount * dp2px(10);
        if (mPanelHeight <= 10) {
            mPanelHeight = dp2px(20);
        }
        mPaddingTop = dp2px(32);
        setPadding(0, mPaddingTop, 0, 0);

        setClickable(true);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSlideState() != EXPANDED) {
                    ((SlidingUpPanelLayout) getParent()).expandPanel();
                } else {
                    ((SlidingUpPanelLayout) getParent()).collapsePanel();
                }
            }
        });
    }

    @NonNull
    @Override
    public CardPanelView getPanelView() {
        return this;
    }

    @Override
    public int getPanelExpandedHeight() {
        if (mPanelExpendedHeight == 0) {
            mPanelExpendedHeight = Resources.getSystem().getDisplayMetrics().heightPixels - mPaddingTop;
        }
        return mPanelExpendedHeight;
    }

    @Override
    public int getPanelCollapsedHeight() {
        return getPanelRealHeight();
    }

    @Override
    public int getSlideState() {
        return mSlideState;
    }

    @Override
    public void setSlideState(@SlidingUpPanelLayout.SlideState int slideState) {
        this.mSlideState = slideState;
    }

    @Override
    public int getPanelTopBySlidingState(@SlidingUpPanelLayout.SlideState int slideState) {
        if (slideState == EXPANDED) {
            return mPaddingTop;
        } else if (slideState == COLLAPSED) {
            return getPanelExpandedHeight() - getPanelCollapsedHeight();
        }
        return 0;
    }

    @Override
    public void onSliding(@NonNull ISlidingUpPanel panel, int top, int dy, float slidedProgress) {

    }

    public int getPanelRealHeight() {
        if (mPanelRealHeight == 0) {
            mPanelRealHeight = (mCardCount - mCardPosition) * mPanelHeight;
        }
        return mPanelRealHeight;
    }

    int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
