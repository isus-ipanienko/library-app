package com.merito.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.merito.app.models.Category;
import com.merito.app.models.Book;
import com.merito.app.models.Movie;

@Controller
public class HomeController {
    private class SearchOption {
        String name;
        boolean isSelected;

        public SearchOption(String name) {
            this.name = name;
            this.isSelected = false;
        }

        public void select() { this.isSelected = true; }
        public void unselect() { this.isSelected = false; }

        public String getOption() {
            return this.name;
        }
        
        public boolean isSelected() {
            return this.isSelected;
        }
    }

    SearchOption moviesOption = new SearchOption("Movies");
    SearchOption booksOption = new SearchOption("Books");
    List<SearchOption> options = Arrays.asList(new SearchOption[] {booksOption, moviesOption});

    static List<Category> empty = new ArrayList<Category>();
    static List<Book> books = new ArrayList<Book>();
    static List<Movie> movies = new ArrayList<Movie>();

    static public void initialize() {
        movies.add(new Movie("George Lucas", "Star Wars: A New Hope"));
        movies.add(new Movie("George Lucas", "Star Wars: The Empire Strikes Back"));
        movies.add(new Movie("George Lucas", "Star Wars: Return of the Jedi"));
        movies.add(new Movie("Rob Cohen", "Fast & Furious"));
        movies.add(new Movie("John Singleton", "2 Fast 2 Furious"));

        books.add(new Book("Andrzej Sapkowski", "Sword of Destiny"));
        books.add(new Book("Andrzej Sapkowski", "Season of Storms"));
        books.add(new Book("Andrzej Sapkowski", "The Last Wish"));
        books.add(new Book("Dmitry Glukhovsky", "Metro 2033"));
        books.add(new Book("DecoMorreno", "Extra Dark"));
    }

    private void setResults(Model model, List<? extends Category> results) {
        List<List<String>> res = new ArrayList<List<String>>();
        for (Category result : results) {
            res.add(result.getData());
        }
        model.addAttribute("results", res);
        if (res.isEmpty()) {
            return;
        }
        model.addAttribute("category", results.get(0).getCategory());
        model.addAttribute("headers", results.get(0).getHeaders());
    }

    @GetMapping("/")
    public String home(Model model) {
        this.booksOption.select();
        this.moviesOption.unselect();
        this.setResults(model, books);
        model.addAttribute("searchOptions", this.options);
        return "index";
    }

    private List<? extends Category>getCategory(String category) {
        if (category.toLowerCase().equals("books")) {
            this.booksOption.select();
            this.moviesOption.unselect();
            return books;
        }
        if (category.toLowerCase().equals("movies")) {
            this.booksOption.unselect();
            this.moviesOption.select();
            return movies;
        }
        return empty;
    }

    @PostMapping("/category")
    public String search(String category, Model model) {
        this.setResults(model, this.getCategory(category));
        model.addAttribute("searchOptions", this.options);
        return "index :: category";
    }

    @GetMapping("/search")
    public String search(String query, String category, String filter, Model model) {
        System.out.println(query + " " + category + " " + filter);
        this.setResults(model, this.getCategory(category) 
                .stream()
                .filter(s -> s.filter(filter.toLowerCase(), query.toLowerCase()))
                .toList());
        return "index :: search-results";
    }
}
