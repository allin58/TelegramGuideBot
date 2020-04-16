package com.demo.telegrambot.service;

import com.demo.telegrambot.dto.CityDTO;
import com.demo.telegrambot.entity.City;
import com.demo.telegrambot.entity.Description;
import com.demo.telegrambot.exception.BadRequestException;
import com.demo.telegrambot.exception.InternalServerException;
import com.demo.telegrambot.repository.CityRepositiry;
import com.demo.telegrambot.repository.DescriptionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CityDTOService {

    @Autowired
    CityRepositiry cityRepositiry;

    @Autowired
    DescriptionRepository descriptionRepository;

    public Optional<CityDTO> findById(Integer id) {

        Optional<City> city =  cityRepositiry.findById(id);

        if (city.isEmpty()) {
            log.info("City isn't found");
            return Optional.empty();
        }

        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityId(city.get().getIdentity());
        cityDTO.setName(city.get().getName());
        cityDTO.setDescription(city.get().getDescription().getText());
        return Optional.of(cityDTO);
    }


    public List<CityDTO> findAll() {

        List<CityDTO> cityDTOList = new ArrayList<>();
        CityDTO cityDTO;
        for (City city : cityRepositiry.findAll()) {
            cityDTO = new CityDTO();
            cityDTO.setCityId(city.getIdentity());
            cityDTO.setName(city.getName());
            cityDTO.setDescription(city.getDescription().getText());
            cityDTOList.add(cityDTO);
        }

        return cityDTOList;
    }



    public boolean isPresent(String name) {
        for (City city : cityRepositiry.findAll()) {
            if (name.equalsIgnoreCase(city.getName())) {
                return  true;
            }
        }
        return false;
    }

    public Integer getDescriptionIdByCityName(String name) {
        for (City city : cityRepositiry.findAll()) {
            if (name.equalsIgnoreCase(city.getName())) {
                return city.getDescription().getIdentity();
            }
        }
        return null;
    }

    public Integer getCityIdByCityName(String name) {
        for (City city : cityRepositiry.findAll()) {
            if (name.equalsIgnoreCase(city.getName())) {
                return city.getIdentity();
            }
        }
        return null;
    }


    public boolean create(CityDTO cityDTO) {
        boolean successFlag = true;

        City city = new City();
        city.setName(cityDTO.getName());

        Description description = new Description();
        description.setText(cityDTO.getDescription());
        description.setCity(city);

        try {
            descriptionRepository.save(description);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("City isn't created");
            successFlag = false;
        }
        log.info("City is created");
        return successFlag;
    }



    public boolean update(CityDTO cityDTO) {
        boolean successFlag = true;

        City city = new City();
        city.setName(cityDTO.getName());
        city.setIdentity(getCityIdByCityName(cityDTO.getName()));

        Description description = new Description();
        description.setText(cityDTO.getDescription());
        description.setCity(city);
        description.setIdentity(getDescriptionIdByCityName(cityDTO.getName()));

        try {
            descriptionRepository.save(description);

        } catch (Exception e) {
            log.info("City isn't updated");
            successFlag = false;
        }
        log.info("City is updated");
        return successFlag;
    }

    public ResponseEntity delete(Integer id) {
        Optional<City> city = cityRepositiry.findById(id);
        if (city.isEmpty()) {
            log.info("City for deleting isn't found");
            throw new BadRequestException();
        }

        Integer identity = city.get().getIdentity();

        try {
            descriptionRepository.deleteById(city.get().getDescription().getIdentity());
        } catch (Exception e) {
            log.info("Deleting error");
            throw new InternalServerException();
        }

        log.info("City is deleted");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



}
