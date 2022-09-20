package com.individualproject.Individual.Project.controller;

import com.individualproject.Individual.Project.model.AnimalType;
import com.individualproject.Individual.Project.model.BreedCats;
import com.individualproject.Individual.Project.repositories.AnimaTypeRepository;
import com.individualproject.Individual.Project.repositories.BreedCatsRepository;
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
@RequestMapping("/breed")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class BreedController {

    @Autowired
    public BreedCatsRepository breedCatsRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<BreedCats> places = breedCatsRepository.findAll();
        model.addAttribute("breed", places);
        return "Breed/index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("breed", new BreedCats());
        return "Breed/add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("breed") @Valid BreedCats breedCats,
                          BindingResult bindingResult,
                          Model model)
    {
        if(bindingResult.hasFieldErrors())
            return  "Breed/add";

        breedCatsRepository.save(breedCats);
        return "redirect:/breed/";
    }

    @GetMapping("/{id}")
    public  String read(@PathVariable("id") Long id, Model model)
    {
        ArrayList<BreedCats> arrayList = new ArrayList<>();
        Optional<BreedCats> posts = breedCatsRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("breed", arrayList);
        return "Breed/info";
    }

    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id, Model model)
    {
        breedCatsRepository.deleteById(id);
        return "redirect:/breed/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id") Long id, Model model)
    {
        if (!breedCatsRepository.existsById(id))
        {
            return "redirect:/breed/";
        }
        ArrayList<BreedCats> arrayList = new ArrayList<>();
        Optional<BreedCats> posts = breedCatsRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("breedInfo", arrayList.get(0));
        return "Breed/edit";
    }

    @PostMapping("/edit/{id}")
    public  String edit_post(@PathVariable("id") Long id, Model model,
                             @ModelAttribute("breed") @Valid BreedCats breedCats,
                             BindingResult bindingResul)
    {
        if(!breedCatsRepository.existsById(id))
        {
            return "redirect:/breed/";
        }
        if(bindingResul.hasErrors())
        {
            return "Breed/edit";
        }

        breedCats.setId(id);
        breedCatsRepository.save(breedCats);
        return "redirect:/breed/";
    }
}
