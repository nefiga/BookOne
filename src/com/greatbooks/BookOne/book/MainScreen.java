package com.greatbooks.BookOne.book;

import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import com.greatbooks.BookOne.util.Assets;
import com.greatbooks.BookOne.util.inter.Main;
import com.greatbooks.BookOne.util.inter.Graphics;
import com.greatbooks.BookOne.util.inter.Input.TouchEvent;
import com.greatbooks.BookOne.util.inter.Screen;

import java.util.List;

public class MainScreen extends Screen {

    BookHolder bookHolder;
    RadialGradient gradient;

    /**
     * True if a book is opened
     */
    private boolean reading;

    /**
     * Used to hide buttons after no touchEvents have occurred after the set period of time
     */
    float buttonTimer, hideButtons = 4;

    int bookOffset;

    /**
     * The bounds of the books
     */
    private final int[][] bookSlots = new int[][] {
            {85, 140, 150, 200},
            {245, 140, 150, 200},
            {405, 140, 150, 200},
            {565, 140, 150, 200}
    };

    /**
     * Bounds for the buttons
     */
    private final int[] back = new int[]{30, 220, 100, 100};
    private final int[] forward = new int[]{700, 220, 100, 100};
    private final int[] home = new int[]{700, 0, 100, 100};

    public MainScreen(Main main) {
        super(main);
        bookHolder = new BookHolder();
        loadBooks();
    }

    @Override
    public void update(float deltaTime) {
        buttonTimer += deltaTime;
        List<TouchEvent> touchEvents = main.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            buttonTimer = 0;
            TouchEvent event = touchEvents.get(i);
            // Checks input if buttons are visible
            if (buttonTimer < hideButtons) {
                if (event.type == TouchEvent.TOUCH_DOWN) {
                    touchDown(event);
                } else if (event.type == TouchEvent.TOUCH_UP) {
                    touchUp(event);
                } else if (event.type == TouchEvent.TOUCH_DRAGGED) {
                    touchDragged(event);
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = main.getGraphics();
        g.clear(Color.BLUE);
        g.drawPixmap(Assets.background, 0, 0);

        if (reading) bookHolder.render(g);
        else {
            for (int i = 0; i < 4; i++) {
                int bookPosition = i + bookOffset;
                if (bookHolder.getBook(bookPosition) != null) g.drawPixmap(bookHolder.getBook(bookPosition).getCover(), bookSlots[i][0], bookSlots[i][1]);
                else g.drawPixmap(Assets.emtpy_book, bookSlots[i][0], bookSlots[i][1]);
            }
        }

        // Render buttons if a touchEvent has happened
        if (buttonTimer < hideButtons) {
            g.drawPixmap(Assets.home, home[0], home[1]);
            g.drawPixmap(Assets.left_arrow, back[0], back[1]);
            g.drawPixmap(Assets.right_arrow, forward[0], forward[1]);
        }
    }

    /**
     * Called when there is a touch up event.
     */
    public void touchUp(TouchEvent event) {
    }

    /**
     * Called when there is a touch down event.
     */
    public void touchDown(TouchEvent event) {
        if (!reading) {
            //Checks to see if a book was selected
            for (int i = 0; i < 4; i++) {
                int bookPosition = i + bookOffset;
                if (inBounds(event, bookSlots[i][0], bookSlots[i][1], bookSlots[i][2], bookSlots[i][3])) {
                    if (bookHolder.openBook(bookHolder.getBookName(bookPosition), main.getGraphics())) {
                        reading = true;
                        return;
                    }
                }
            }
        }
        else {
            //Home button
            if (inBounds(event, home[0], home[1], home[2], home[3])) {
                reading = false;
                bookHolder.closeCurrentBook();
            }
        }

        //Back button
        if (inBounds(event, back[0], back[1], back[2], back[3])) back();
        //Forward button
        if (inBounds(event, forward[0], forward[1], forward[2], forward[3])) forward();
    }

    /**
     * Called when the  back button is pressed
     */
    public void back() {
        if (reading) {
            bookHolder.getCurrentBook().previousPage();
        }
        else {
            bookOffset--;
            if (bookOffset < 0) bookOffset = 0;
        }
    }

    /**
     * Called when the forward button is pressed
     */
    public void forward() {
        if (reading) {
            bookHolder.getCurrentBook().nextPage();
        }
        else {
            if (bookHolder.getBook(bookOffset + 3) != null) {
                bookOffset++;
            }
        }
    }

    /**
     * Called when there is a touch dragged event.
     */
    public void touchDragged(TouchEvent event) {

    }

    /**
     * Checks if the event was in the bounds
     *
     * @param event  The fired event
     * @param x      The starting x position of the bounds
     * @param y      The starting y position of the bounds
     * @param width  The width of the bounds
     * @param height The height of the bounds
     * @return True if the event was inside of the create bounds else false
     */
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        return event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1;
    }

    public void loadBooks() {
        bookHolder.addBook(new OnceABaby("OnceABaby", Assets.once_a_baby, 20));
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
