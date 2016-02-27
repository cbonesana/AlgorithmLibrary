package ch.zeyger.algorithms.matching;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Claudio Bonesana
 * Date:    27.02.2016
 * Project: Algorithms
 */
public class ProposeAndRejectTest {

    List<Element> men = new ArrayList<>();
    List<Element> women = new ArrayList<>();

    Element Amy = new Element();
    Element Bertha = new Element();
    Element Clare = new Element();

    Element Xavier = new Element();
    Element Yancey = new Element();
    Element Zeus = new Element();

    @Before
    public void setUp() throws Exception {
        Amy.addPreference(Yancey);
        Amy.addPreference(Xavier);
        Amy.addPreference(Zeus);
        Bertha.addPreference(Xavier);
        Bertha.addPreference(Yancey);
        Bertha.addPreference(Zeus);
        Clare.addPreference(Xavier);
        Clare.addPreference(Yancey);
        Clare.addPreference(Zeus);

        Xavier.addPreference(Amy);
        Xavier.addPreference(Bertha);
        Xavier.addPreference(Clare);
        Yancey.addPreference(Bertha);
        Yancey.addPreference(Amy);
        Yancey.addPreference(Clare);
        Zeus.addPreference(Amy);
        Zeus.addPreference(Bertha);
        Zeus.addPreference(Clare);

        women.add(Amy);
        women.add(Bertha);
        women.add(Clare);

        men.add(Xavier);
        men.add(Yancey);
        men.add(Zeus);

    }

    @Test
    public void testMatch() throws Exception {

        ProposeAndReject par = new ProposeAndReject();

        par.match(men, women);

        assert (Xavier.getMatch() == Amy);
        assert (Yancey.getMatch() == Bertha);
        assert (Zeus.getMatch() == Clare);
    }
}