package com.lcwd.hotel.services;

import com.lcwd.hotel.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotelService {
    //create
    Hotel create(Hotel hotel);
    //getall
    List<Hotel> getAll();
    //getsingle
    Hotel get(String id);
}
