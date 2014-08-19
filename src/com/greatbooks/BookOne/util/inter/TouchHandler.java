package com.greatbooks.BookOne.util.inter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.greatbooks.BookOne.util.inter.Input.TouchEvent;

import java.util.List;

public interface TouchHandler extends OnTouchListener {

    public boolean isTouchDown(int pointer);

    public int getTouchX(int  pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();

    public boolean onTouch(View v, MotionEvent event);
}
