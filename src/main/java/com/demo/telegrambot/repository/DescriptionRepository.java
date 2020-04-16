package com.demo.telegrambot.repository;

import com.demo.telegrambot.entity.Description;
import org.springframework.data.repository.CrudRepository;

public interface DescriptionRepository extends CrudRepository<Description,Integer> {
}
