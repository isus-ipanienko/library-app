package com.merito.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.merito.app.categories.Category;
import com.merito.app.categories.Book;
import com.merito.app.categories.Movie;
import com.merito.app.categories.Music;

@Controller
public class HomeController {
    private static class SearchOption {
        String name;
        boolean isSelected;

        public SearchOption(String name, boolean selected) {
            this.name = name;
            this.isSelected = selected;
        }

        public String getOption() {
            return this.name;
        }
        
        public boolean isSelected() {
            return this.isSelected;
        }
    }

    static List<SearchOption> optionsBooks = Arrays.asList(new SearchOption[]
            {new SearchOption("Books", true), new SearchOption("Movies", false), new SearchOption("Music", false)});
    static List<SearchOption> optionsMovies = Arrays.asList(new SearchOption[]
            {new SearchOption("Books", false), new SearchOption("Movies", true), new SearchOption("Music", false)});
    static List<SearchOption> optionsMusic = Arrays.asList(new SearchOption[]
            {new SearchOption("Books", false), new SearchOption("Movies", false), new SearchOption("Music", true)});

    static List<Category> empty = new ArrayList<Category>();
    static List<Book> books = new ArrayList<Book>();
    static List<Movie> movies = new ArrayList<Movie>();
    static List<Music> music = new ArrayList<Music>();

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

        music.add(new Music("Krzysztof Krawczyk", "Parostatek"));
        music.add(new Music("Krzysztof Krawczyk", "Mój Przyjacielu"));
        music.add(new Music("Caravan Palace", "Lone Digger"));
    }

    private List<? extends Category> getItems(String category) {
        if (category.equals("books")) {
            return books;
        }
        if (category.equals("movies")) {
            return movies;
        }
        if (category.equals("music")) {
            return music;
        }
        return empty;
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
        model.addAttribute("headers", results.get(0).getHeaders());
    }

    private void setOptions(Model model, String category) {
        if (category.equals("books")) {
            model.addAttribute("searchOptions", this.optionsBooks);
        } else if (category.equals("movies")) {
            model.addAttribute("searchOptions", this.optionsMovies);
        } else if (category.equals("music")) {
            model.addAttribute("searchOptions", this.optionsMusic);
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        this.setResults(model, books);
        this.setOptions(model, "books");
        model.addAttribute("category", "Books");
        return "index";
    }

    @PostMapping("/category")
    public String category(String category, Model model) {
        this.setResults(model, this.getItems(category.toLowerCase()));
        this.setOptions(model, category.toLowerCase());
        model.addAttribute("category", category);
        return "index :: category";
    }

    @GetMapping("/search")
    public String search(String query, String category, String filter, Model model) {
        this.setResults(model, this.getItems(category.toLowerCase()) 
                .stream()
                .filter(s -> s.filter(filter.toLowerCase(), query.toLowerCase()))
                .toList());
        model.addAttribute("category", category);
        return "index :: search-results";
    }
}
