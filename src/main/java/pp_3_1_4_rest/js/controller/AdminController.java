package pp_3_1_4_rest.js.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String openStartPage(){
        return "admin";
    }
}

