package com.greatbooks.BookOne.util;

import android.graphics.Bitmap;
import com.greatbooks.BookOne.util.inter.Graphics.PixmapFormat;
import com.greatbooks.BookOne.util.inter.Pixmap;

public class AndroidPixmap implements Pixmap {

   public Bitmap bitmap;
    PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
