package com.demo.telegrambot.controller;


import com.demo.telegrambot.dto.CityDTO;
import com.demo.telegrambot.exception.BadRequestException;
import com.demo.telegrambot.exception.ConflictException;
import com.demo.telegrambot.service.CityDTOService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
public class Controller {



    @Autowired
    CityDTOService cityDTOService;



    @GetMapping(value = "/{id}")
    public ResponseEntity<CityDTO> getCity(@PathVariable("id") Integer id) {


        Optional<CityDTO> city =  cityDTOService.findById(id);

        if (city.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city.get(), HttpStatus.OK);
    }


    @GetMapping(value = "/")
    public ResponseEntity<List<CityDTO>> getAll() {

        List<CityDTO> cityDTOS = cityDTOService.findAll();

        if (cityDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(cityDTOS);
    }


    @PostMapping(value = "/", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Void> create(@RequestBody CityDTO cityDTO) {

        if (cityDTO.getName() == null || cityDTO.getDescription() == null) {
            throw new BadRequestException();
        }

        if (cityDTOService.isPresent(cityDTO.getName())) {
            throw new ConflictException();
        }

        if (cityDTOService.create(cityDTO)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PutMapping(value = "/", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Void> update(@RequestBody CityDTO cityDTO) {

        if (cityDTO.getName() == null || cityDTO.getDescription() == null) {
            throw new BadRequestException();
        }

        if (!cityDTOService.isPresent(cityDTO.getName())) {
            throw new BadRequestException();
        }

        if (cityDTOService.update(cityDTO)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        return cityDTOService.delete(id) ;
    }


}