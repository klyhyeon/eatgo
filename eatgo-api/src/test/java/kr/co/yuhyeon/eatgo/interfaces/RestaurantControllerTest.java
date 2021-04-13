package kr.co.yuhyeon.eatgo.interfaces;

import kr.co.yuhyeon.eatgo.application.RestaurantService;
import kr.co.yuhyeon.eatgo.domain.MenuItem;
import kr.co.yuhyeon.eatgo.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    //가짜 객체
    private RestaurantService restaurantService;

//    @SpyBean
//    //Container 생성되었을 때 SpringBoot의 경우 Test에 SpyBean으로 의존성 주입 필요함
//    //실제 구현이 없는 Interface의 경우 SpyBean 뒤에 실제 구현 클래스 명을 기재해줘야한다.
//    private RestaurantService restaurantService;
//
//    @SpyBean (RestaurantRepositoryImpl.class)
//    private RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;

    @Test
    //처리할 web에 대한 요청
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "JOKER House", "Seoul"));
        //import mockito given 메서드 - 가짜 객체
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ));

    }

    @Test
    //가게 상세
    public void detail() throws Exception {

        Restaurant restaurant1 = new Restaurant(1004L, "JOKER House", "Seoul");
        Restaurant restaurant2 = new Restaurant(2020L, "Cyber Food", "Seoul");

        restaurant1.addMenuItem(new MenuItem("Kimchi"));
        restaurant2.addMenuItem(new MenuItem("Kimchi"));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ));


        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")
                ));
    }

    @Test
    public void create() throws Exception {
//        given(restaurantService.addRestaurant(any())).will(invocation -> {
//            Restaurant restaurant = invocation.getArgument(0);
//            return Restaurant.builder()
//                    .id(1234L)
//                    .categoryId(1L)
//                    .name(restaurant.getName())
//                    .address(restaurant.getAddress())
//                    .build();
//        });


        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Sushi\",\"address\":\"Changwon\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any()); //무엇이든 넣어도 완성되는 any mockito
    }

}