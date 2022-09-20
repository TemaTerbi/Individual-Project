package com.individualproject.Individual.Project.controller;

import com.individualproject.Individual.Project.model.BreedCats;
import com.individualproject.Individual.Project.model.Color;
import com.individualproject.Individual.Project.repositories.BreedCatsRepository;
import com.individualproject.Individual.Project.repositories.ColorRepository;
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
@RequestMapping("/color")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ColorController {

    @Autowired
    public ColorRepository colorRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Color> places = colorRepository.findAll();
        model.addAttribute("color", places);
        return "Color/index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("color", new Color());
        return "Color/add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("color") @Valid Color color,
                          BindingResult bindingResult,
                          Model model)
    {
        if(bindingResult.hasFieldErrors())
            return  "Color/add";

        colorRepository.save(color);
        return "redirect:/color/";
    }

    @GetMapping("/{id}")
    public  String read(@PathVariable("id") Long id, Model model)
    {
        ArrayList<Color> arrayList = new ArrayList<>();
        Optional<Color> posts = colorRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("color", arrayList);
        return "Color/info";
    }

    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id, Model model)
    {
        colorRepository.deleteById(id);
        return "redirect:/color/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id") Long id, Model model)
    {
        if (!colorRepository.existsById(id))
        {
            return "redirect:/color/";
        }
        ArrayList<Color> arrayList = new ArrayList<>();
        Optional<Color> posts = colorRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("colorInfo", arrayList.get(0));
        return "Color/edit";
    }

    @PostMapping("/edit/{id}")
    public  String edit_post(@PathVariable("id") Long id, Model model,
                             @ModelAttribute("color") @Valid Color color,
                             BindingResult bindingResul)
    {
        if(!colorRepository.existsById(id))
        {
            return "redirect:/color/";
        }
        if(bindingResul.hasErrors())
        {
            return "Color/edit";
        }

        color.setId(id);
        colorRepository.save(color);
        return "redirect:/color/";
    }
}
