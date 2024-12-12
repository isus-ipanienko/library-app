package com.merito.app.categories;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.merito.app.categories.Category;

public class Book implements Category {
    private String author;
    private String title;

    static List<String> headers = Arrays.asList(new String[] {"Author", "Title"});
    public List<String> getHeaders() {
        return headers;
    }

    public List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add(author);
        data.add(title);
        return data;
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public boolean filter(String filter, String query) {
        if (filter.equals("author")) {
            return author.toLowerCase().startsWith(query);
        }
        if (filter.equals("title")) {
            return title.toLowerCase().startsWith(query);
        }
        return false;
    }
}

