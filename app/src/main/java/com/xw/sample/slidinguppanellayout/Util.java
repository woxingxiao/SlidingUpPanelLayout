package com.xw.sample.slidinguppanellayout;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * <p>
 * Created by woxingxiao on 2017-07-10.
 */

public final class Util {

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
