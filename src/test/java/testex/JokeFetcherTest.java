/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import testex.jokefetching.ChuckNorris;
import testex.jokefetching.EduJoke;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;
import testex.jokefetching.Moma;
import testex.jokefetching.Tambal;

/**
 *
 * @author jonassimonsen
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {

    @Mock IDateFormatter mockDateFormatter;
    @Mock IFetcherFactory fetcherFactory;
    @Mock Moma moma;
    @Mock ChuckNorris chuck;
    @Mock EduJoke edu;
    @Mock Tambal tambal;
    
    private JokeFetcher jf;

    public JokeFetcherTest() {
    }

    @Before
    public void setUp() {
        
        // GIVEN 
        List<IJokeFetcher> fetchers = Arrays.asList(edu, chuck, moma, tambal);
        List<String> types = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");
        
        // WHEN
        when(fetcherFactory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal")).thenReturn(fetchers);
        when(fetcherFactory.getAvailableTypes()).thenReturn(types);
        
        // Kinda setup THEN
        jf = new JokeFetcher(mockDateFormatter, fetcherFactory);
    }

    @Test
    public void testGetAvailableTypes() {
        List<String> jokesList = jf.getAvailableTypes();

        assertThat(jokesList, hasSize(4));
        assertThat(jokesList, hasItems("eduprog", "chucknorris", "moma", "tambal"));
    }

    @Test
    public void testIsStringValid() {
        boolean trueRes = jf.isStringValid("eduprog,chucknorris,moma,tambal");
        boolean falseRes = jf.isStringValid("hej");

        assertThat(trueRes, is(true));
        assertThat(falseRes, is(false));
    }

    @Test
    public void testGetJokes() throws JokeException {
        given(mockDateFormatter.getFormattedDate(anyObject(), eq("Europe/Copenhagen"))).willReturn("17 feb. 2017 10:56 AM");

        assertThat(jf.getJokes("eduprog,chucknorris,moma,tambal", "Europe/Copenhagen").getTimeZoneString(), is("17 feb. 2017 10:56 AM"));
        verify(mockDateFormatter, times(1)).getFormattedDate(anyObject(), eq("Europe/Copenhagen"));
    }
    
    @Test
    public void testChuckNorrisJoke(){
        String joke = "Best chuck norris joke ever!!!";
        String reference = "Chuck Norris reference String";
        
        given(chuck.getJoke()).willReturn(new Joke(joke, reference));
        assertThat(chuck.getJoke().getJoke(), is(joke));
        assertThat(chuck.getJoke().getReference(), is(reference));
    }
    
    @Test
    public void testEducationalJoke(){
        String joke = "Best educational joke ever!!!";
        String reference = "Educational reference String";

        given(edu.getJoke()).willReturn(new Joke(joke, reference));
        assertThat(edu.getJoke().getJoke(), is(joke));
        assertThat(edu.getJoke().getReference(), is(reference));
    }
    
    @Test
    public void testMommaJoke(){
        String joke = "Best yo mama joke ever!!!";
        String reference = "Yo mama reference String";

        given(moma.getJoke()).willReturn(new Joke(joke, reference));
        assertThat(moma.getJoke().getJoke(), is(joke));
        assertThat(moma.getJoke().getReference(), is(reference));
    }
    
    @Test
    public void testTambalJoke(){
        String joke = "Best tambal joke ever!!!";
        String reference = "Tambal reference String";

        given(tambal.getJoke()).willReturn(new Joke(joke, reference));
        assertThat(tambal.getJoke().getJoke(), is(joke));
        assertThat(tambal.getJoke().getReference(), is(reference));
    }

}
