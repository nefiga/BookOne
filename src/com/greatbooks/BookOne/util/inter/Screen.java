package com.greatbooks.BookOne.util.inter;

public abstract class Screen {

    protected final Book book;

    public Screen(Book book) {
        this.book = book;
    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
