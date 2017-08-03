# SlidingUpPanelLayout
[![](https://jitpack.io/v/woxingxiao/SlidingUpPanelLayout.svg)](https://jitpack.io/#woxingxiao/SlidingUpPanelLayout)
[![License](http://img.shields.io/badge/License-Apache%202.0-brightgreen.svg?style=flat)](https://opensource.org/licenses/Apache-2.0)

[**ENGLISH**](https://github.com/woxingxiao/SlidingUpPanelLayout/blob/master/README.md)

![logo](https://github.com/woxingxiao/SlidingUpPanelLayout/blob/master/app/src/main/res/mipmap-xxhdpi/ic_launcher.png)

**一个强大而灵活的`SlidingPanelLayout`，可以在竖直方向上操控多个panel，实现炫酷的交互效果。**

****
## Screenshot
![demo1](https://github.com/woxingxiao/SlidingUpPanelLayout/blob/master/screenshot/demo1.gif)
![demo2](https://github.com/woxingxiao/SlidingUpPanelLayout/blob/master/screenshot/demo2.gif)

## Download
### 1. [sample apk](https://fir.im/spl)
### 2. Gradle
root project:`build.gradle`
```groovy
  allprojects {
	 repositories {
		...
		maven { url "https://jitpack.io" }
	 }
  }
```
app:`build.gradle`
```groovy
  dependencies {
     // e.g. compile 'com.github.woxingxiao:SlidingUpPanelLayout:1.1.0'
     compile 'com.github.woxingxiao:SlidingUpPanelLayout:${LATEST_VERSION}'
  }
```

## Usage
### 1. java (动态方式)
```java
SlidingUpPanelLayout.setAdapter(new SlidingUpPanelLayout.Adapter() {

      private final int mSize = mWeatherList.size();

      @Override
      public int getItemCount() {
          return mSize;
      }

      @NonNull
      @Override
      public ISlidingUpPanel onCreateSlidingPanel(int position) {
          WeatherPanelView panel = new WeatherPanelView(DemoActivity1.this);
          if (position == 0) {
              panel.setSlideState(EXPANDED);
          } else {
              panel.setSlideState(HIDDEN);
          }

          return panel;
      }

      @Override
      public void onBindView(final ISlidingUpPanel panel, int position) {
          if (mSize == 0)
              return;

          BaseWeatherPanelView BasePanel = (BaseWeatherPanelView) panel;
          BasePanel.setWeatherModel(mWeatherList.get(position));
          BasePanel.setClickable(true);
          BasePanel.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (panel.getSlideState() != EXPANDED) {
                      mSlidingUpPanelLayout.expandPanel();
                  } else {
                      mSlidingUpPanelLayout.collapsePanel();
                  }
              }
          });
      }
  });
```
Go to [sample](https://github.com/woxingxiao/SlidingUpPanelLayout/tree/master/app/src/main/java/com/xw/sample/slidinguppanellayout/demo1) for more details.

### 2. xml  (静态方式)
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.xw.repo.supl.SlidingUpPanelLayout
    android:id="@+id/sliding_up_panel_layout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- 背景view, 在位置0处只能有一个直接子view，可以是一个view也可以是一个layout。 -->
    <android.support.constraint.ConstraintLayout
        android:id="@+id/bg_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#616161">

        <TextView
            android:id="@+id/pick_hint_text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginTop="120dp"
            android:text="Pick a credit card to pay"
            android:textColor="@android:color/white"
            android:textSize="20sp"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>

        <TextView
            android:id="@+id/pay_hint_text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="260dp"
            android:alpha="0.0"
            android:drawablePadding="8dp"
            android:drawableTop="@mipmap/ic_finger_print"
            android:text="Pay with Touch ID"
            android:textColor="@android:color/white"
            android:textSize="18sp"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            tools:alpha="1.0"/>
    </android.support.constraint.ConstraintLayout>

    <!-- panel view, 从位置1到最后，这些子view必须是实现了ISlidingUpPanel接口的view.-->
    <com.xw.sample.slidinguppanellayout.demo2.CardPanelView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:cpv_cardCount="4"
        app:cpv_cardImageRes="@mipmap/pic_card1"
        app:cpv_cardPosition="0"/>

    <com.xw.sample.slidinguppanellayout.demo2.CardPanelView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:cpv_cardCount="4"
        app:cpv_cardImageRes="@mipmap/pic_card2"
        app:cpv_cardPosition="1"/>

    <com.xw.sample.slidinguppanellayout.demo2.CardPanelView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:cpv_cardCount="4"
        app:cpv_cardImageRes="@mipmap/pic_card3"
        app:cpv_cardPosition="2"/>

    <com.xw.sample.slidinguppanellayout.demo2.CardPanelView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:cpv_cardCount="4"
        app:cpv_cardImageRes="@mipmap/pic_card4"
        app:cpv_cardPosition="3"/>

</com.xw.repo.supl.SlidingUpPanelLayout>

```
Go to [sample](https://github.com/woxingxiao/SlidingUpPanelLayout/tree/master/app/src/main/java/com/xw/sample/slidinguppanellayout/demo2) for more details.

## Attentions
- 当你的`PanelView`实现`ISlidingUpPanel`后，计算需要准确无误才能确保`SlidingUpPanelLayout`工作正常。通常情况可参考我的demo来改。
- 不要为你的`PanelView`设置`onTouchListener`监听，否则可能造成`SlidingUpPanelLayout`工作异常。
- 不要加载太多的`PanelView`，因为没有考虑回收机制，过多必然会影响性能。

## Attributes
```xml
    <attr name="spl_disableSliding" format="boolean"/>
    <attr name="spl_expandThreshold" format="float"/>
    <attr name="spl_collapseThreshold" format="float"/>
```

欢迎提交**issue**或者**pull request**.

## License
```
   Copyright 2017 woxingxiao

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```