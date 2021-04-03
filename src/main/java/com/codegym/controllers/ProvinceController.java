package com.codegym.controllers;

import com.codegym.models.entities.Customer;
import com.codegym.models.entities.Province;
import com.codegym.models.service.ICustomerService;
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

    @Autowired
    public ICustomerService customerService;

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
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
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
    public ModelAndView updateProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView mav = new ModelAndView("/province/edit");
        mav.addObject("province", province);
        mav.addObject("message", "Province updated successfully!");
        return mav;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Province province = provinceService.findById(id);
        if (province != null) {
            ModelAndView mav = new ModelAndView("/province/delete");
            mav.addObject("province", province);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("/error.404");
            return mav;
        }
    }

    @PostMapping("/delete-province")
    public String deleteProvince(@ModelAttribute("province") Province province) {
        provinceService.delete(province.getId());
        return "redirect:/provinces";
    }

    @GetMapping("/view-provinces{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Province province = provinceService.findById(id);
        if (province != null) {
            Iterable<Customer> customers = customerService.findAllByProvince(province);
            ModelAndView mav = new ModelAndView("/province/view");
            mav.addObject("province", province);
            mav.addObject("customers",customers);
            return mav;
        }
        else {
            return new ModelAndView("/error.404");
        }
    }

}
