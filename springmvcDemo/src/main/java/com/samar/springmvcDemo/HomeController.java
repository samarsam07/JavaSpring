package com.samar.springmvcDemo;



import com.samar.springmvcDemo.dao.AleinDao;
import com.samar.springmvcDemo.model.Alein;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {
    @Autowired
   AleinRepo repo;
    @ModelAttribute
    public void modelData(Model m){
        m.addAttribute("name","Aleins");
    }

    @RequestMapping("/")
    public String home() {
        System.out.println("home page requested");
        return "index";
    }
    @RequestMapping("add")
    public ModelAndView add(@RequestParam("num1") int i,@RequestParam("num2") int j){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("result");
        int result=i+j;
        mv.addObject("num3",result);
        return mv;
    }

    @RequestMapping("addAlien")
    public String addAlein(@ModelAttribute Alein alein){
        repo.save(alein);
        return "result";
    }
    @GetMapping("getAlien")
    public String getAlein(@RequestParam int id,Model m){
        m.addAttribute("alien",repo.findAll());
        return "result";
    }
    @GetMapping("getAlienByName")
    public  String getAlienByName(@RequestParam String name,Model model){
        model.addAttribute("results",repo.find(name));
      return "showAleins";
    }

}
