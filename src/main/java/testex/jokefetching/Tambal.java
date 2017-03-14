/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import static com.jayway.restassured.RestAssured.given;
import testex.Joke;

/**
 *
 * @author jonassimonsen
 */
public class Tambal implements IJokeFetcher {

    @Override
    public Joke getJoke() {
        try {
            String joke = given().get("http://tambal.azurewebsites.net/joke/random").path("joke");
            return new Joke(joke, "http://tambal.azurewebsites.net/joke/random");
        } catch (Exception e) {
            return null;
        }

    }

}
