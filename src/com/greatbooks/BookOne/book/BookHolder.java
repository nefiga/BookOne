package com.greatbooks.BookOne.book;

import com.greatbooks.BookOne.util.inter.Graphics;
import com.greatbooks.BookOne.util.inter.Main;

import java.util.*;

public class BookHolder {

    private Map<String, Book> books = new HashMap<String, Book>();
    List<String> bookList = new ArrayList<String>();

    private Book currentBook = null;

    public void update() {
        if (currentBook != null) currentBook.update();
    }

    public void render(Graphics g) {
        if (currentBook != null) currentBook.render(g);
    }

    public void addBook(Book book) {
        books.put(book.getName(), book);
        bookList.add(book.getName());
        Collections.sort(bookList);
    }

    /**
     * Tries to open a book
     *
     * @param name The name of the book
     * @param g    A Graphics reference used to load the books assets
     * @return True if there is a book to open else return false
     */
    public boolean openBook(String name, Graphics g) {
        if (books.get(name) != null) {
            currentBook = books.get(name);
            currentBook.openBook(g);
            return true;
        }
        return false;
    }

    /**
     * Closes the currently opened book.
     */
    public void closeCurrentBook() {
        currentBook.closeBook();
        currentBook = null;
    }

    public Book getCurrentBook() {
        return currentBook;
    }

    public Book getBook(int slot) {
        if (slot < bookList.size()) return books.get(bookList.get(slot));
        else return null;
    }

    public String getBookName(int slot) {
        if (slot >= bookList.size()) return null;
        else return books.get(bookList.get(slot)).getName();
    }
}
