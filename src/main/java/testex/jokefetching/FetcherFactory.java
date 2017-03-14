/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jonassimonsen
 */
public class FetcherFactory implements IFetcherFactory {

    private final List<String> availableTypes = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        List<IJokeFetcher> returnList = new ArrayList<>();
        String[] tokens = jokesToFetch.split(",");
        for (String token : tokens) {
            switch (token) {
                case "EduJoke":
                    returnList.add(new EduJoke());
                    break;
                case "ChuckNorris":
                    returnList.add(new ChuckNorris());
                    break;
                case "Moma":
                    returnList.add(new Moma());
                    break;
                case "Tambal":
                    returnList.add(new Tambal());
                    break;
                default:
                    break;
            }
        }
        return returnList;
    }

}
