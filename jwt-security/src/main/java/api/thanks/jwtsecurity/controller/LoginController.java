package api.thanks.jwtsecurity.controller;

import api.thanks.jwtsecurity.model.User;
import api.thanks.jwtsecurity.utility.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    JWTGenerator jwtGenerator;

    @Value("${super.secreat.key}")
    private String privateKey;

    @GetMapping("/logon")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "logon";
    }

    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity greetingSubmit(@ModelAttribute User user, ModelAndView m) {
        return ResponseEntity
                .ok()
                .body(jwtGenerator.generateToken(user,privateKey));
    }
}
