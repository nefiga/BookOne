package com.greatbooks.BookOne.book;

import android.graphics.Color;
import com.greatbooks.BookOne.util.inter.Book;
import com.greatbooks.BookOne.util.inter.Graphics;
import com.greatbooks.BookOne.util.inter.Input.TouchEvent;
import com.greatbooks.BookOne.util.inter.Screen;

import java.util.List;

public class BookCover extends Screen{

    public BookCover(Book book) {
        super(book);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchevents = book.getInput().getTouchEvents();

        int len = touchevents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchevents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {

            }
            else if (event.type == TouchEvent.TOUCH_UP) {

            }
            else if (event.type == TouchEvent.TOUCH_DRAGGED) {

            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = book.getGraphics();

        g.drawLine(0, 0, 100, 100, Color.RED);
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        return event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height -1;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
