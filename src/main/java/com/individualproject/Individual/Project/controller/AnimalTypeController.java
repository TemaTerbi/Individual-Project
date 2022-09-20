package com.individualproject.Individual.Project.controller;

import com.individualproject.Individual.Project.model.AnimalType;
import com.individualproject.Individual.Project.model.Places;
import com.individualproject.Individual.Project.repositories.AnimaTypeRepository;
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
@RequestMapping("/type")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AnimalTypeController {

    @Autowired
    public AnimaTypeRepository animaTypeRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<AnimalType> places = animaTypeRepository.findAll();
        model.addAttribute("animal", places);
        return "AnimalType/index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("animal", new AnimalType());
        return "AnimalType/add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("animal") @Valid AnimalType animalType,
                          BindingResult bindingResult,
                          Model model)
    {
        if(bindingResult.hasFieldErrors())
            return  "AnimalType/add";

        animaTypeRepository.save(animalType);
        return "redirect:/type/";
    }

    @GetMapping("/{id}")
    public  String read(@PathVariable("id") Long id, Model model)
    {
        ArrayList<AnimalType> arrayList = new ArrayList<>();
        Optional<AnimalType> posts = animaTypeRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("animal", arrayList);
        return "AnimalType/info";
    }

    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id, Model model)
    {
        animaTypeRepository.deleteById(id);
        return "redirect:/type/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id") Long id, Model model)
    {
        if (!animaTypeRepository.existsById(id))
        {
            return "redirect:/type/";
        }
        ArrayList<AnimalType> arrayList = new ArrayList<>();
        Optional<AnimalType> posts = animaTypeRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("animalInfo", arrayList.get(0));
        return "AnimalType/edit";
    }

    @PostMapping("/edit/{id}")
    public  String edit_post(@PathVariable("id") Long id, Model model,
                             @ModelAttribute("animal") @Valid AnimalType animalType,
                             BindingResult bindingResul)
    {
        if(!animaTypeRepository.existsById(id))
        {
            return "redirect:/type/";
        }
        if(bindingResul.hasErrors())
        {
            return "AnimalType/edit";
        }

        animalType.setId(id);
        animaTypeRepository.save(animalType);
        return "redirect:/type/";
    }
}
