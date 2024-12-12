package com.merito.app.categories;

import java.util.List;
import java.util.ArrayList;

public interface Category {
    public List<String> getHeaders();
    public List<String> getData();
    public boolean filter(String filter, String query);
}
