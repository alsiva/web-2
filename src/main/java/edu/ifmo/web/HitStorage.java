package edu.ifmo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HitStorage implements Serializable {

    private List<HitResult> hits = new ArrayList<>();

    public List<HitResult> getHits() {
        return hits;
    }

    public void setHits(List<HitResult> hits) {
        this.hits = hits;
    }
}
