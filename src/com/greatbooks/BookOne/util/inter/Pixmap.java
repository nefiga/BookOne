package com.greatbooks.BookOne.util.inter;

import com.greatbooks.BookOne.util.inter.Graphics.PixmapFormat;

public interface Pixmap {

    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
