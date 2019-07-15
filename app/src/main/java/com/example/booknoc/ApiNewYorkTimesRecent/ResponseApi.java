package com.example.booknoc.ApiNewYorkTimesRecent;

import java.util.ArrayList;
import java.util.List;

public class ResponseApi {
    //On déclare les différents attributs de la réponse JSON.
    private List<Results> results = new ArrayList<>();

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}
