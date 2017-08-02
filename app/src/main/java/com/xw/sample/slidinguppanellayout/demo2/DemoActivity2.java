package com.xw.sample.slidinguppanellayout.demo2;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xw.repo.supl.ISlidingUpPanel;
import com.xw.repo.supl.SlidingUpPanelLayout;
import com.xw.sample.slidinguppanellayout.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoActivity2 extends AppCompatActivity {

    @BindView(R.id.sliding_up_panel_layout)
    SlidingUpPanelLayout mSlidingUpPanelLayout;
    @BindView(R.id.pick_hint_text)
    TextView mPickHintText;
    @BindView(R.id.pay_hint_text)
    TextView mPayHintText;
    @BindView(R.id.bg_layout)
    View mBgLayout;

    private ISlidingUpPanel mSlidingUpPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // transparent status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_demo2);
        ButterKnife.bind(this);

        mSlidingUpPanelLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListenerAdapter() {
            @Override
            public void onPanelExpanded(ISlidingUpPanel panel) {
                mSlidingUpPanel = panel;

                int childCount = mSlidingUpPanelLayout.getChildCount();
                CardPanelView card;
                for (int i = 1; i < childCount; i++) {
                    card = (CardPanelView) mSlidingUpPanelLayout.getChildAt(i);
                    if (card != panel && card.getSlideState() == SlidingUpPanelLayout.EXPANDED) {
                        mSlidingUpPanelLayout.collapsePanel(card);
                        break;
                    }
                }

                mPayHintText.setAlpha(1.0f);
            }

            @Override
            public void onPanelSliding(ISlidingUpPanel panel, float slideProgress) {
                if (mSlidingUpPanel == null || mSlidingUpPanel == panel) {
                    mPickHintText.setAlpha(1 - slideProgress);
                    mPayHintText.setAlpha(slideProgress);
                }
            }
        });

        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = getResources().getDimensionPixelSize(resId);
        mBgLayout.setPadding(0, statusBarHeight, 0, 0);
    }
}
