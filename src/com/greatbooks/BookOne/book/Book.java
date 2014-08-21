package com.greatbooks.BookOne.book;

import android.graphics.Color;
import com.greatbooks.BookOne.util.inter.Graphics;
import com.greatbooks.BookOne.util.inter.Pixmap;

public abstract class Book {

    private final String name;

    protected String[] pageText;

    public final int pages;

    protected int currentPage;

    protected final int textSize = 30;

    protected final int textColor = Color.BLACK;

    protected int[] textBox = new int[] {20, 400, 760, 200};

    Pixmap cover;

    /**
     * Creates a new Book
     * @param name The name of the book
     * @param cover The cover image
     */
    public Book(String name, Pixmap cover, int pages) {
        this.name = name;
        this.cover = cover;
        this.pages = pages;
        pageText =  new String[pages];
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void openBook(Graphics g);

    public abstract void closeBook();

    protected void renderText(Graphics g) {
        String[] lines = pageText[currentPage].split("_");

        for (int i= 0; i < lines.length; i++) {
            g.drawString(lines[i], textBox[0], textBox[1] + (textSize * i), textSize, textColor);
        }
    }

    public void nextPage() {
        currentPage++;
        if (currentPage >= pages) currentPage = pages - 1;
    }

    public void previousPage() {
        currentPage--;
        if (currentPage < 0) currentPage = 0;
    }

    public Pixmap getCover() {
        return cover;
    }

    public String getName() {
        return name;
    }
}
