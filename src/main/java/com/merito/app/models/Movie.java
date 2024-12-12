package com.merito.app.models;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.merito.app.models.Category;

public class Movie implements Category {
    public String director;
    public String title;

    public String getCategory() {
        return "Movies";
    }

    static List<String> headers = Arrays.asList(new String[] {"Director", "Title"});
    public List<String> getHeaders() {
        return headers;
    }

    public List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add(director);
        data.add(title);
        return data;
    }

    public Movie(String director, String title) {
        this.director = director;
        this.title = title;
    }

    public boolean filter(String filter, String query) {
        if (filter.equals("director")) {
            return director.toLowerCase().startsWith(query);
        }
        if (filter.equals("title")) {
            return title.toLowerCase().startsWith(query);
        }
        return false;
    }
}

