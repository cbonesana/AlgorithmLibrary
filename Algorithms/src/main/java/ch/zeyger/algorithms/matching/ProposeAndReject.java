package ch.zeyger.algorithms.matching;

import java.util.List;

/**
 * Author:  Claudio Bonesana
 * Date:    27.02.2016
 * Project: Algorithms
 *
 * [Gale-Shapley 1962]
 */
public class ProposeAndReject {

    /**
     * This matching is optimal for men (first list).
     * @param men list of men
     * @param women list of woman
     */
    public void match(List<Element> men, List<Element> women) {

        // Initialize each person to be free.
        men.forEach(Element::free);
        women.forEach(Element::free);

        // while some man is free and hasn't proposed to every woman, chose such a man m
        Element m;
        while ((m = checkIfFree(men)) != null && !m.proposedToAll()) {
            // w is a woman on m's list to whom m has not yet proposed
            Element w = m.getNextCandidate();

            if (w.isFree()) {           // if w is free
                engage(m, w);           // assign m and w to be engaged
            } else if (w.prefers(m)) {  // w prefers m to her fiancee m'
                w.getMatch().free();    // assign m and w to be engaged, and m' to be free
                engage(m, w);
            }
            // else, w rejects m
        }
    }

    protected void engage(Element m, Element w) {
        m.match(w);
        w.match(m);
    }

    protected Element checkIfFree(List<Element> elements) {
        for (Element e : elements)
            if (e.isFree())
                return e;
        return null;
    }
}
