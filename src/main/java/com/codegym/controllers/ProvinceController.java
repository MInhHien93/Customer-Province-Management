package com.codegym.controllers;

import com.codegym.models.entities.Province;
import com.codegym.models.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {
    @Autowired
    public IProvinceService provinceService;

    @GetMapping("/create-province")
    public ModelAndView showCreateForm() {
        ModelAndView mav = new ModelAndView("/province/create");
        mav.addObject("province", new Province());
        return mav;
    }

    @PostMapping("/create-province")
    public ModelAndView saveProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView mav = new ModelAndView("/province/create");
        mav.addObject("province", province);
        mav.addObject("message", "New province created successfully!");
        return mav;
    }

    @GetMapping("/provinces")
    public ModelAndView showListProvince() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView mav = new ModelAndView("/province/list");
        mav.addObject("provinces", provinces);
        return mav;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        if (province != null) {
            ModelAndView mav = new ModelAndView("/province/edit");
            mav.addObject("province", province);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("/error.404");
            return mav;
        }
    }

    @PostMapping("/edit-province")
    public ModelAndView updateProvince(@ModelAttribute Province province) {
        provinceService.save(province);
        ModelAndView mav = new ModelAndView("/province/edit");
        mav.addObject("province",province);
        mav.addObject("message", "Province updated successfully!");
        return mav;
    }
}
