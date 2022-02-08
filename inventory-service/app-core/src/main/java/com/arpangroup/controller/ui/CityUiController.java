package com.arpangroup.controller.ui;

import com.arpangroup.common.AlertType;
import com.arpangroup.entity.master.CityEntity;
import com.arpangroup.mapper.CityMapper;
import com.arpangroup.model.AlertMessage;
import com.arpangroup.repository.CityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cities")
public class CityUiController {
    private final CityRepository repository;
    private final CityMapper mapper;

    public CityUiController(CityRepository repository, CityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAllCities(Model model){
        model.addAttribute("cities", repository.findAll());
        return "cities";
    }

    @GetMapping("/addNewCity")
    public String addNewCity(Model model){
        CityEntity city = new CityEntity();
        model.addAttribute("city", city);
        return "citiesAddUpdate";
    }

    @PostMapping("/addNewCity")
    public String addNewCity(@ModelAttribute("city") CityEntity city, HttpSession session){
        String cityName = city.getName();
        CityEntity city2 = city;
        session.setAttribute("message", new AlertMessage(AlertType.SUCCESS, "Record added successfully!!"));
        return "cities";
    }
}
