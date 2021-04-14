package kr.co.yuhyeon.eatgo.application;

import kr.co.yuhyeon.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {


    //Impl 클래스로 할 경우 제한되기 때문에 Interface를 참조 Interface 내 메서드만 사용
    private RestaurantRepository restaurantRepository;

    //Impl 클래스로 할 경우 제한되기 때문에 Interface를 참조 Interface 내 메서드만 사용
    private MenuItemRepository menuItemRepository;

    public RestaurantService() {

    }

    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    //가게 목록
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    //가게 상세
    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        List <MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
