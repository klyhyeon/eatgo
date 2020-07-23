package kr.co.yuhyeon.eatgo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import kr.co.yuhyeon.eatgo.domain.Restaurant;
import org.junit.jupiter.api.Test;


public class RestaurantTests {
@Test

    public void creation() {
        Restaurant restaurant = new Restaurant("Bob zip", "");
        assertThat(Restaurant.getName(),is("Bob zip"));
        assertThat(Restaurant.getAddress(),is("Seoul"));

    }

    public void information() {
        Restaurant restaurant = new Restaurant("Bob zip", "Seoul");

        assertThat(Restaurant.getInformation(), is("Bob zip in Seoul"));
    }
}
