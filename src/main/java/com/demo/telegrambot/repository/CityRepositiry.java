package com.demo.telegrambot.repository;

import com.demo.telegrambot.entity.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepositiry extends CrudRepository<City,Integer> {
}
