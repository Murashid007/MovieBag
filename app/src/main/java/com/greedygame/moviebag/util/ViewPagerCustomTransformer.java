package com.greedygame.moviebag.util;


import android.view.View;

import androidx.viewpager.widget.ViewPager;


public class ViewPagerCustomTransformer implements ViewPager.PageTransformer
{
    @Override
    public void transformPage(View page, float position) {
        final float rotation = 90f * position;
        page.setVisibility(rotation > 90f || rotation < -90f ? View.INVISIBLE : View.VISIBLE);
        page.setPivotX(position <= 0 ? page.getWidth() : 0.0f);
        page.setPivotY(page.getHeight() * 0.5f);
        page.setRotationY(rotation);
    }
}
