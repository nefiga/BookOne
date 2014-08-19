package com.greatbooks.BookOne.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidRenderView extends SurfaceView implements Runnable{

    AndroidBook book;
    Bitmap frameBuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;

    public AndroidRenderView(AndroidBook book, Bitmap frameBuffer) {
        super(book);
        this.book = book;
        this.frameBuffer = frameBuffer;
        this.holder = getHolder();
    }

    @Override
    public void run() {
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();

        while (running) {
            if (!holder.getSurface().isValid()) continue;

            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            book.getCurrentScreen().update(deltaTime);
            book.getCurrentScreen().present(deltaTime);

            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(frameBuffer, null, dstRect, null);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    public void pause() {
        running = false;
        while (true) {
            try {
                renderThread.join();
                return;
            }
            catch (InterruptedException e) {
                // retry
            }
        }
    }
}
