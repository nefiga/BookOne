package com.greatbooks.BookOne.util.inter;

public interface Music {

    public void play();

    public void stop();

    public void pause();

    public void setLoopoing(boolean looping);

    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isStopped();

    public boolean isLooping();

    public void dispose();
}
