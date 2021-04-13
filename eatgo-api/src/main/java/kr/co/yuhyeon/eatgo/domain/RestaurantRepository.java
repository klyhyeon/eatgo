package kr.co.yuhyeon.eatgo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    //optional 데이터 타입은 null을 처리하지 않고 Restaurant 있냐없냐고 처리할 수 있는 데이터 타입
    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant restaurant);
}
