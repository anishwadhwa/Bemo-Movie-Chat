package bemo.moviechat.models;

import java.util.ArrayList;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public class MovieResponse {
    int page;
    ArrayList<Movie> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
