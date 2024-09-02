package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
@Service
@FeignClient(name="RATING-SERVICE")
public interface ratingService {

    //get

    //post
    @PostMapping("/ratings")
    public Rating createRating(Rating values);
    //put
    @PutMapping("/ratings/{ratingId}")
    public Rating updaterating(@PathVariable("ratingId") String ratingId, Rating rating);
    //delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId);
}
