package com.individualproject.Individual.Project.controller;

import com.individualproject.Individual.Project.model.Places;
import com.individualproject.Individual.Project.model.Posts;
import com.individualproject.Individual.Project.repositories.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/places")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class PlacesController {

    @Autowired
    public PlacesRepository placesRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Places> places = placesRepository.findAll();
        model.addAttribute("places", places);
        return "Places/index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("places", new Places());
        return "Places/add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("places") @Valid Places places,
                          BindingResult bindingResult,
                          Model model)
    {
        if(bindingResult.hasFieldErrors())
            return  "Places/add";

        placesRepository.save(places);
        return "redirect:/places/";
    }

    @GetMapping("/{id}")
    public  String read(@PathVariable("id") Long id, Model model)
    {
        ArrayList<Places> arrayList = new ArrayList<>();
        Optional<Places> posts = placesRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("places", arrayList);
        return "Places/info";
    }

    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id, Model model)
    {
        placesRepository.deleteById(id);
        return "redirect:/places/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id") Long id, Model model)
    {
        if (!placesRepository.existsById(id))
        {
            return "redirect:/places/";
        }
        ArrayList<Places> arrayList = new ArrayList<>();
        Optional<Places> posts = placesRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("placesInfo", arrayList.get(0));
        return "Places/edit";
    }

    @PostMapping("/edit/{id}")
    public  String edit_post(@PathVariable("id") Long id, Model model,
                             @ModelAttribute("places") @Valid Places places,
                             BindingResult bindingResul)
    {
        if(!placesRepository.existsById(id))
        {
            return "redirect:/places/";
        }
        if(bindingResul.hasErrors())
        {
            return "Places/edit";
        }

        places.setId(id);
        placesRepository.save(places);
        return "redirect:/places/";
    }
}
