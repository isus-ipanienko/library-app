package com.merito.app.models;

import java.util.List;
import java.util.ArrayList;

public interface Category {
    public String getCategory();
    public List<String> getHeaders();
    public List<String> getData();
    public boolean filter(String filter, String query);
}
