package com.greatbooks.BookOne.util;

import android.content.res.AssetManager;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import com.greatbooks.BookOne.util.inter.Graphics;
import com.greatbooks.BookOne.util.inter.Pixmap;

import java.io.IOException;
import java.io.InputStream;

public class AndroidGraphics implements Graphics {

    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        Config config = null;

        if (format == PixmapFormat.RGB565) config = Config.RGB_565;
        else if (format == PixmapFormat.ARGB4444) config = Config.ARGB_4444;
        else config = Config.ARGB_8888;

        Options options = new Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;

        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null) throw new RuntimeException("Couldn't load bitmap from assets " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from assets" + fileName);
        } finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {

                }
            }
        }

        if (bitmap.getConfig() == Config.RGB_565) format = PixmapFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444) format = PixmapFormat.ARGB4444;
        else format = PixmapFormat.ARGB8888;

        return new AndroidPixmap(bitmap, format);
    }

    @Override
    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    @Override
    public void drawPixel(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawPoint(x,  y, paint);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        canvas.drawRect(x, y, width, height, paint);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int scrX, int srcY, int srcWidth, int srcHeight) {
        canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap, x, y, null);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap, x, y, null);
    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
