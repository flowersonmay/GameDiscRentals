package ru.leskov.musicShop.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Game {

    private int id_game;
    @Size(min = 2, max = 50,message = "Title must be between 2 and 50")
    @NotEmpty
    private String title;
    @NotEmpty
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100")
    private String publisher;
    @Min(value = 1900, message = "Year should be greatest then 1900")
    private int year;

    public Game() {
    }

    public Game(String title, String author, int year) {
        this.title = title;
        this.publisher = author;
        this.year = year;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
