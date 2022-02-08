package com.arpangroup.controller.ui;

import com.arpangroup.common.AlertType;
import com.arpangroup.entity.master.CityEntity;
import com.arpangroup.entity.master.CuisineEntity;
import com.arpangroup.mapper.CityMapper;
import com.arpangroup.model.AlertMessage;
import com.arpangroup.repository.CityRepository;
import com.arpangroup.repository.CuisineRepository;
import com.arpangroup.util.Utils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cuisines")
public class CuisineController {
    private final CityRepository repository;
    private final CuisineRepository cuisineRepository;
    private final CityMapper mapper;

    public CuisineController(CityRepository repository, CuisineRepository cuisineRepository, CityMapper mapper) {
        this.repository = repository;
        this.cuisineRepository = cuisineRepository;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAllCuisines(Model model, @RequestParam(name = "city_id", required = false) Integer cityId, @RequestParam(name = "lat", required = false) Float lat, @RequestParam(name = "lng", required = false) Float lon, HttpSession session){
        List<CuisineEntity> cuisines = null;
        if (cityId != null){
            cuisines = repository.findAllCuisineByCityId(cityId);
        }else if(lat != null && lon != null){
            cuisines = repository.findAllCuisinesByLatLng(lat, lon);
        }else{
            cuisines = cuisines = cuisineRepository.findAll();
            session.setAttribute("message", new AlertMessage(AlertType.DANGER, "Invalid coordinates / city_id"));
        }

        model.addAttribute("cuisines", cuisines);
        return "cuisines";


    }

    @GetMapping("/addNewCuisine")
    public String addNewCuisine(Model model){
        CityEntity city = new CityEntity();
        model.addAttribute("city", city);
        return "citiesAddUpdate";
    }

    @PostMapping("/addNewCuisine")
    public String addNewCuisine(@ModelAttribute("city") CityEntity city){
        String cityName = city.getName();
        CityEntity city2 = city;
        return "cities";
    }
}
