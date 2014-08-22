package com.greatbooks.BookOne.util;

import com.greatbooks.BookOne.book.MainScreen;
import com.greatbooks.BookOne.util.inter.Main;
import com.greatbooks.BookOne.util.inter.Graphics;
import com.greatbooks.BookOne.util.inter.Screen;

public class LoadingScreen extends Screen{

    public LoadingScreen(Main main) {
        super(main);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = main.getGraphics();

        loadAssets(g);

        main.setScreen(new MainScreen(main));
    }

    private void loadAssets(Graphics g) {
        Assets.background = g.newPixmap("background.png", Graphics.PixmapFormat.RGB565);

        Assets.emtpy_book = g.newPixmap("empty_book.png", Graphics.PixmapFormat.RGB565);
        Assets.once_a_baby = g.newPixmap("once_a_baby.png", Graphics.PixmapFormat.RGB565);
        Assets.grandmas_do = g.newPixmap("grandmas_do.png", Graphics.PixmapFormat.RGB565);

        Assets.home = g.newPixmap("home.png", Graphics.PixmapFormat.ARGB8888);
        Assets.sound = g.newPixmap("sound.png", Graphics.PixmapFormat.ARGB8888);
        Assets.left_arrow = g.newPixmap("left_arrow.png", Graphics.PixmapFormat.ARGB8888);
        Assets.right_arrow = g.newPixmap("right_arrow.png", Graphics.PixmapFormat.ARGB8888);
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
