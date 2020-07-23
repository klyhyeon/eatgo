package kr.co.yuhyeon.eatgo.interfaces;


import kr.co.yuhyeon.eatgo.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = new ArrayList<>();
        return null;
    }
}
