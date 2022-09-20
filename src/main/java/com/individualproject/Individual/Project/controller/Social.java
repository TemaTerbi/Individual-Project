package com.individualproject.Individual.Project.controller;

import com.individualproject.Individual.Project.model.Color;
import com.individualproject.Individual.Project.model.SocialWeb;
import com.individualproject.Individual.Project.repositories.ColorRepository;
import com.individualproject.Individual.Project.repositories.SocialWebRepository;
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
@RequestMapping("/social")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class Social {

    @Autowired
    public SocialWebRepository socialWebRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<SocialWeb> places = socialWebRepository.findAll();
        model.addAttribute("social", places);
        return "Social/index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("social", new SocialWeb());
        return "Social/add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("social") @Valid SocialWeb social,
                          BindingResult bindingResult,
                          Model model)
    {
        if(bindingResult.hasFieldErrors())
            return  "Social/add";

        socialWebRepository.save(social);
        return "redirect:/social/";
    }

    @GetMapping("/{id}")
    public  String read(@PathVariable("id") Long id, Model model)
    {
        ArrayList<SocialWeb> arrayList = new ArrayList<>();
        Optional<SocialWeb> posts = socialWebRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("social", arrayList);
        return "Social/info";
    }

    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id, Model model)
    {
        socialWebRepository.deleteById(id);
        return "redirect:/social/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id") Long id, Model model)
    {
        if (!socialWebRepository.existsById(id))
        {
            return "redirect:/social/";
        }
        ArrayList<SocialWeb> arrayList = new ArrayList<>();
        Optional<SocialWeb> posts = socialWebRepository.findById(id);
        posts.ifPresent(arrayList::add);
        model.addAttribute("socialInfo", arrayList.get(0));
        return "Social/edit";
    }

    @PostMapping("/edit/{id}")
    public  String edit_post(@PathVariable("id") Long id, Model model,
                             @ModelAttribute("social") @Valid SocialWeb socialWeb,
                             BindingResult bindingResul)
    {
        if(!socialWebRepository.existsById(id))
        {
            return "redirect:/social/";
        }
        if(bindingResul.hasErrors())
        {
            return "Social/edit";
        }

        socialWeb.setId(id);
        socialWebRepository.save(socialWeb);
        return "redirect:/social/";
    }
}
