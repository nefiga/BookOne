package com.greatbooks.BookOne.book;

import com.greatbooks.BookOne.util.inter.AndroidTextLoader;
import com.greatbooks.BookOne.util.inter.Graphics;
import com.greatbooks.BookOne.util.inter.Pixmap;

public class OnceABaby extends Book{

    public OnceABaby(String name, Pixmap cover, int pages) {
        super(name, cover, pages);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        renderText(g);
    }

    @Override
    public void openBook(Graphics g) {
        pageText = AndroidTextLoader.loadText("once_a_baby.txt", pages);
        System.out.println("Working");
    }

    @Override
    public void closeBook() {

    }
}
