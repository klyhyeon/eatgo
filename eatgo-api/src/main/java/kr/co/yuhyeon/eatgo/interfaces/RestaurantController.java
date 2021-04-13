package kr.co.yuhyeon.eatgo.interfaces;

import kr.co.yuhyeon.eatgo.application.RestaurantService;
import kr.co.yuhyeon.eatgo.application.RestaurantService;
import kr.co.yuhyeon.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin //for JS
@RestController
public class RestaurantController {

    //자동으로 repository 객체 생성해서 Controller에 의존주입
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    //가게 목록
    public List<Restaurant> list() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    //가게 상세
    public Restaurant detail(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource)
            throws URISyntaxException {
        //@RequestBody JSON 형식으로 넘겨준 형식을 받을 수 있는 클래스를 만들어 줄것임
        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = new Restaurant(name, address);
        restaurantService.addRestaurant(restaurant);

        //TODO: ResponseEntity를 왜 사용하는지?
        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }
}
