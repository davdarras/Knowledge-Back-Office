package fr.insee.knowledge.controller.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/")
    public String redirect() {
        return "redirect:/swagger-ui.html";
    }
}
