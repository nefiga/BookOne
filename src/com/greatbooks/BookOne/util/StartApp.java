package com.greatbooks.BookOne.util;

import com.greatbooks.BookOne.util.inter.Screen;

public class StartApp extends AndroidMain {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
