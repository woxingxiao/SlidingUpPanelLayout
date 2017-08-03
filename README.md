# SlidingUpPanelLayout
[![](https://jitpack.io/v/woxingxiao/SlidingUpPanelLayout.svg)](https://jitpack.io/#woxingxiao/SlidingUpPanelLayout)
[![License](http://img.shields.io/badge/License-Apache%202.0-brightgreen.svg?style=flat)](https://opensource.org/licenses/Apache-2.0)

[**中文说明**](https://github.com/woxingxiao/SlidingUpPanelLayout/blob/master/README_zh.md)

![logo](https://github.com/woxingxiao/SlidingUpPanelLayout/blob/master/app/src/main/res/mipmap-xxhdpi/ic_launcher.png)

**A powerful and flexible `SlidingPanelLayout`, which can support multiple panels in the vertical direction.**

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
### 1. java (Dynamic way)
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

### 2. xml  (Static way)
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.xw.repo.supl.SlidingUpPanelLayout
    android:id="@+id/sliding_up_panel_layout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- background view, the position at 0 can only have one directly view, it can be a view or a layout. -->
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

    <!-- panel view, the position from 1 to n must be the one which implemented the interface ISlidingUpPanel.-->
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
- Your calculation must be accurate when your `PanelView` implement the interface `ISlidingUpPanel`,
which can make sure `SlidingUpPanelLayout` to work properly. Generally my demos are good reference.
- Don't set `onTouchListener` for your `PanelView` in your own code, or may cause `SlidingUpPanelLayout` working abnormally.
- Don't load too many `PanelView`s because the recycling mechanism have not been considered.

## Attributes
```xml
    <attr name="spl_disableSliding" format="boolean"/>
    <attr name="spl_expandThreshold" format="float"/>
    <attr name="spl_collapseThreshold" format="float"/>
```

Welcome to **issue** or **pull request**.

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