package com.greatbooks.BookOne.book;

import com.greatbooks.BookOne.util.AndroidBook;
import com.greatbooks.BookOne.util.LoadingScreen;
import com.greatbooks.BookOne.util.inter.Screen;

public class BookStart extends AndroidBook{
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
