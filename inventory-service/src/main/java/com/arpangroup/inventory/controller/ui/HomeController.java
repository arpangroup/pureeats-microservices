package com.arpangroup.inventory.controller.ui;

import com.arpangroup.inventory.entity.admin.MenuEntity;
import com.arpangroup.inventory.repository.CustomMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("")
public class HomeController {

    @Autowired
    CustomMenuRepository customMenuRepository;

    @GetMapping(value = {"", "/home", "/index"})
    public String index(Model model){
        List<MenuEntity> menus = customMenuRepository.findAll();
        model.addAttribute("menus", menus);
        return "index";
    }

    @GetMapping("/stores")
    public String stores(Model model){
        return "store_list";
    }

    @GetMapping("/itemcategories")
    public String categories(Model model){
        return "categories";
    }

}
