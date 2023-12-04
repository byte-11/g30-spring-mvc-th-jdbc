package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.domain.User;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/users")
    public ModelAndView users(@RequestParam(name = "status", required = false) Boolean status) {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users",
                List.of(
                        new User(213, "John", "Doe", 20),
                        new User(2343, "Doe", "John", 23),
                        new User(546, "Alice", "Gram", 19),
                        new User(645, "Harry", "Kane", 24),
                        new User(3245, "Andrei", "Onana", 30),
                        new User(4354, "Ben", "White", 27),
                        new User(565, "Erron", "Black", 22),
                        new User(435, "Ashley", "Adam", 18)
                ));
        modelAndView.addObject("status", status);
        return modelAndView;
    }

}
