package com.greedygame.moviebag.model;

import java.util.ArrayList;

public class ReviewResult {
    public ArrayList<Review> getResults() {
        return results;
    }

    public void setResults(ArrayList<Review> results) {
        this.results = results;
    }

    private ArrayList<Review> results;
}
