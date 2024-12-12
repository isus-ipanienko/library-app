package com.merito.app.categories;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.merito.app.categories.Category;

public class Music implements Category {
    private String artist;
    private String title;

    static List<String> headers = Arrays.asList(new String[] {"Artist", "Title"});
    public List<String> getHeaders() {
        return headers;
    }

    public List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add(artist);
        data.add(title);
        return data;
    }

    public Music(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    public boolean filter(String filter, String query) {
        if (filter.equals("artist")) {
            return artist.toLowerCase().startsWith(query);
        }
        if (filter.equals("title")) {
            return title.toLowerCase().startsWith(query);
        }
        return false;
    }
}

