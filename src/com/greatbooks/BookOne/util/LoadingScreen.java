package com.greatbooks.BookOne.util;

import com.greatbooks.BookOne.book.BookCover;
import com.greatbooks.BookOne.util.inter.Book;
import com.greatbooks.BookOne.util.inter.Graphics;
import com.greatbooks.BookOne.util.inter.Screen;

public class LoadingScreen extends Screen{

    public LoadingScreen(Book book) {
        super(book);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = book.getGraphics();

        book.setScreen(new BookCover(book));
    }

    @Override
    public void present(float deltaTime) {

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
