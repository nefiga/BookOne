package com.greatbooks.BookOne.util.inter;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AndroidTextLoader {

    static AssetManager assetManager;

    public AndroidTextLoader(AssetManager assetManager) {
        AndroidTextLoader.assetManager = assetManager;
    }

    public static String[] loadText(String fileName, int pages) {
        InputStream is;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            is = assetManager.open("text/" + fileName);
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(reader);

            String line;
            try {
                while ((line = buffer.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load text " + fileName);
        }

        // Brakes up the text into pages
        String[] strings = new String[pages];
        int start = 0;
        int end = stringBuilder.indexOf("%");
        for (int i = 0; i < pages - 1; i++) {
            strings[i] = stringBuilder.substring(start, end);
            start = end + 1;
            end = stringBuilder.indexOf("%", start);
        }
        strings[pages - 1] = stringBuilder.substring(start, stringBuilder.length());
        return strings;
    }
}
