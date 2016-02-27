package ch.zeyger.algorithms.matching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:  Claudio Bonesana
 * Date:    27.02.2016
 * Project: Algorithms
 */
public class Element {

    private int i = 0;
    private Element match;
    private List<Element> elements = new ArrayList<>();
    private Map<Element, Integer> preferences = new HashMap<>();

    public Element() {}

    public void addPreference(Element e) {
        preferences.put(e, elements.size());
        elements.add(e);
    }

    public boolean prefers(Element m) {
        // lower value = higher preference
        return preferences.get(m) < preferences.get(match);
    }

    public boolean isFree() {
        return match == null;
    }

    public boolean proposedToAll() {
        return i == elements.size();
    }

    public void free() {
        match = null;
    }

    public void match(Element e) {
        match = e;
    }

    public Element getMatch() {
        return match;
    }

    public Element getNextCandidate() {
        Element x = elements.get(i);
        i++;
        return x;
    }

}
