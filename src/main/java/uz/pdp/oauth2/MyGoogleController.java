package uz.pdp.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/google")
public class MyGoogleController {

    @GetMapping("/login")
    public String loginPage(){
        return "google";
    }
}
