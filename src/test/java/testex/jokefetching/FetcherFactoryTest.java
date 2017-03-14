/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mock;

/**
 *
 * @author jonassimonsen
 */
public class FetcherFactoryTest {

    @Mock EduJoke eduJoke;
    @Mock ChuckNorris chuckNorris;
    @Mock Moma moma;
    @Mock Tambal tambal;
    FetcherFactory factory;

    public FetcherFactoryTest() {
    }

    @Before
    public void setUp() {
        factory = new FetcherFactory();
    }

    @Test
    public void testGetJokeFetcher() {
        List<IJokeFetcher> result = factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal");
        assertThat(result.toArray(), arrayContainingInAnyOrder(instanceOf(EduJoke.class), instanceOf(ChuckNorris.class), instanceOf(Moma.class), instanceOf(Tambal.class)));
        assertThat(result.toArray(), is(arrayWithSize(4)));
    }

}
