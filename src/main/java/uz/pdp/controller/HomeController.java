package uz.pdp.controller;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.domain.User;

@Controller
public class HomeController {
    @GetMapping("/home")
    public ModelAndView home(){
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user",
                new User(1L, "John", "Doe", 22));
        return modelAndView;
    }
}
