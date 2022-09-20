package com.individualproject.Individual.Project.controller;

import com.individualproject.Individual.Project.model.*;
import com.individualproject.Individual.Project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class PostsController {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    public AnimaTypeRepository animaTypeRepository;

    @Autowired
    public BreedCatsRepository breedCatsRepository;

    @Autowired
    public ColorRepository colorRepository;

    @Autowired
    public SocialWebRepository socialWebRepository;

    @Autowired
    public PlacesRepository placesRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PhoneRepository phoneRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Posts> posts = postsRepository.findAll();
        model.addAttribute("posts", posts);
        return "Posts/index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        update(model);
        model.addAttribute("posts", new Posts());
        return "Posts/add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("posts") @Valid Posts posts,
                          BindingResult bindingResult,
                          @RequestParam String places,
                          @RequestParam String animalType,
                          @RequestParam String breedCats,
                          @RequestParam String color,
                          @RequestParam String socialWeb,
                          @RequestParam String userName,
                          Model model
                          )
    {
        if(posts.getTitle().isEmpty() || posts.getDescription().isEmpty() || posts.getDate().isEmpty() || posts.getPhone().isEmpty() || posts.getName().isEmpty())
        {
            model.addAttribute("error", "Вы заполнили не все поля");
            model.addAttribute("posts", new Posts());
            update(model);
            return "Posts/add";
        }

        Optional<User> user_raw = Optional.ofNullable(userRepository.findByUsername(userName));
        Long id =  user_raw.get().getId();

        posts.setBreedCats(breedCatsRepository.findByName(breedCats).get(0));
        posts.setPlaces(placesRepository.findByName(places).get(0));
        posts.setAnimalType(animaTypeRepository.findByName(animalType).get(0));
        posts.setColor(colorRepository.findByName(color).get(0));
        posts.setSocialWeb(socialWebRepository.findByName(socialWeb).get(0));
        posts.setUserId(id);

        Phone phone = new Phone();
        phone.setPhone(posts.getPhone());
        phone.setUserName(userName);

        phoneRepository.save(phone);
        postsRepository.save(posts);
        return "redirect:/posts/";
    }

    private void update(Model model) {
        Iterable<Places> places = placesRepository.findAll();
        Iterable<AnimalType> animalTypes = animaTypeRepository.findAll();
        Iterable<BreedCats> breedCats = breedCatsRepository.findAll();
        Iterable<Color> colors = colorRepository.findAll();
        Iterable<SocialWeb> socialWebs = socialWebRepository.findAll();
        Iterable<User> user = userRepository.findAll();
        model.addAttribute("places", places);
        model.addAttribute("animalTypes", animalTypes);
        model.addAttribute("breedCats", breedCats);
        model.addAttribute("colors", colors);
        model.addAttribute("socialWebs", socialWebs);
        model.addAttribute("users", user);
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<Posts> posts = postsRepository.findById(id);
        ArrayList<Posts> postsArrayList = new ArrayList<>();
        posts.ifPresent(postsArrayList::add);
        model.addAttribute("posts",postsArrayList);

        return "Posts/info";
    }

    @GetMapping("/delete/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        Posts posts = postsRepository.findById(id).orElseThrow();
        postsRepository.delete(posts);
        return "redirect:/posts/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!postsRepository.existsById(id))
        {
            return "redirect:/posts/";
        }
        Optional<Posts> posts = postsRepository.findById(id);
        ArrayList<Posts> postsArrayList = new ArrayList<>();
        posts.ifPresent(postsArrayList::add);
        model.addAttribute("postsInfo", postsArrayList);

        update(model);

        return "Posts/Edit";
    }

    @PostMapping("/edit/{id}")
    public  String editNews(@ModelAttribute("posts") @Valid Posts posts,
                            BindingResult bindingResult,
                            @RequestParam String places,
                            @RequestParam String animalType,
                            @RequestParam String breedCats,
                            @RequestParam String color,
                            @RequestParam String socialWeb,
                            @PathVariable("id") Long id,
                            Model model
    )
    {

        if(posts.getTitle().isEmpty() || posts.getDescription().isEmpty() || posts.getDate().isEmpty() || posts.getPhone().isEmpty() || posts.getName().isEmpty())
        {
            model.addAttribute("error", "Вы заполнили не все поля");
            model.addAttribute("posts", new Posts());
            Optional<Posts> postss = postsRepository.findById(id);
            ArrayList<Posts> postsArrayList = new ArrayList<>();
            postss.ifPresent(postsArrayList::add);
            model.addAttribute("postsInfo", postsArrayList);
            update(model);
            return "Posts/Edit";
        }


        if(!postsRepository.existsById(id))
        {
            return "redirect:/posts/";
        }

        posts.setBreedCats(breedCatsRepository.findByName(breedCats).get(0));
        posts.setPlaces(placesRepository.findByName(places).get(0));
        posts.setAnimalType(animaTypeRepository.findByName(animalType).get(0));
        posts.setColor(colorRepository.findByName(color).get(0));
        posts.setSocialWeb(socialWebRepository.findByName(socialWeb).get(0));
        posts.setId(id);


        postsRepository.save(posts);
        return "redirect:/posts/";
    }

    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("title") String title,
            Model model)
    {
        if(title.isEmpty())
        {
            Iterable<Posts> bananas =  postsRepository.findAll();
            model.addAttribute("posts", bananas);
            return "Posts/index";
        }
        else {
            List<Posts> bananasList = postsRepository.findByTitleContains(title);
            model.addAttribute("posts", bananasList);
            return "Posts/index";
        }
    }
}
