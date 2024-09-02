package com.lcwd.rating.repositiories;

import com.lcwd.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {
    //custom finder methods
    //Using user Id
    List<Rating> findByUserId(String userId);
    //using hotel Id
    List<Rating> findByHotelId(String hotelId);
}
