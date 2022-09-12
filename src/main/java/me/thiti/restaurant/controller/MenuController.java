package me.thiti.restaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import me.thiti.restaurant.dto.MenuDto;
import me.thiti.restaurant.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
    
    @Autowired
    private MenuService service;

    @GetMapping
    public String getMenus(Model model) {
        model.addAttribute("menus", service.getMenus());
        return "menu";
    }

    @GetMapping("/add")
    public String getMenuForm(MenuDto menuDto) {
        return "menu-add";
    }

    @PostMapping("/add")
    public String addMenu(@Valid MenuDto menuDto, BindingResult result, Model model) {
        if (result.hasErrors())
            return "menu-add";
        
            service.addMenu(menuDto);
            return "redirect:/menu";
    }
}
