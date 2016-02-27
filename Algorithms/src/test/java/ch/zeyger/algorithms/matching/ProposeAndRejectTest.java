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

    Element Amy    = new Element();
    Element Bertha = new Element();
    Element Clare  = new Element();
    Element Diane  = new Element();
    Element Erika  = new Element();

    Element Victor = new Element();
    Element Wyatt  = new Element();
    Element Xavier = new Element();
    Element Yancey = new Element();
    Element Zeus   = new Element();

    @Before
    public void setUp() throws Exception {
        Amy.addPreference(Zeus);
        Amy.addPreference(Victor);
        Amy.addPreference(Wyatt);
        Amy.addPreference(Yancey);
        Amy.addPreference(Xavier);
        Bertha.addPreference(Xavier);
        Bertha.addPreference(Wyatt);
        Bertha.addPreference(Yancey);
        Bertha.addPreference(Victor);
        Bertha.addPreference(Zeus);
        Clare.addPreference(Wyatt);
        Clare.addPreference(Xavier);
        Clare.addPreference(Yancey);
        Clare.addPreference(Zeus);
        Clare.addPreference(Victor);
        Diane.addPreference(Victor);
        Diane.addPreference(Zeus);
        Diane.addPreference(Yancey);
        Diane.addPreference(Xavier);
        Diane.addPreference(Wyatt);
        Erika.addPreference(Yancey);
        Erika.addPreference(Wyatt);
        Erika.addPreference(Zeus);
        Erika.addPreference(Xavier);
        Erika.addPreference(Victor);

        Victor.addPreference(Bertha);
        Victor.addPreference(Amy);
        Victor.addPreference(Diane);
        Victor.addPreference(Erika);
        Victor.addPreference(Clare);
        Wyatt.addPreference(Diane);
        Wyatt.addPreference(Bertha);
        Wyatt.addPreference(Amy);
        Wyatt.addPreference(Clare);
        Wyatt.addPreference(Erika);
        Xavier.addPreference(Bertha);
        Xavier.addPreference(Erika);
        Xavier.addPreference(Clare);
        Xavier.addPreference(Diane);
        Xavier.addPreference(Amy);
        Yancey.addPreference(Amy);
        Yancey.addPreference(Diane);
        Yancey.addPreference(Clare);
        Yancey.addPreference(Bertha);
        Yancey.addPreference(Erika);
        Zeus.addPreference(Bertha);
        Zeus.addPreference(Diane);
        Zeus.addPreference(Amy);
        Zeus.addPreference(Erika);
        Zeus.addPreference(Clare);

        women.add(Amy);
        women.add(Bertha);
        women.add(Clare);
        women.add(Diane);
        women.add(Erika);

        men.add(Victor);
        men.add(Wyatt);
        men.add(Xavier);
        men.add(Yancey);
        men.add(Zeus);

    }

    @Test
    public void testMatch() throws Exception {

        ProposeAndReject par = new ProposeAndReject();

        par.match(men, women);

        assert (Victor.getMatch() == Amy);
        assert (Wyatt.getMatch()  == Clare);
        assert (Xavier.getMatch() == Bertha);
        assert (Yancey.getMatch() == Erika);
        assert (Zeus.getMatch()   == Diane);
    }
}