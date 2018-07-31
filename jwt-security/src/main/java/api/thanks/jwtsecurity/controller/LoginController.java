package api.thanks.jwtsecurity.controller;

import api.thanks.jwtsecurity.model.Member;
import api.thanks.jwtsecurity.model.User;
import api.thanks.jwtsecurity.repository.MemberRepository;
import api.thanks.jwtsecurity.utility.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Base64;

@Controller
@RequestMapping("/auth")
@CrossOrigin("http://localhost:8080")
public class LoginController {

    @Autowired
    JWTGenerator jwtGenerator;

    @Autowired
    MemberRepository repository;

    @Value("${super.secreat.key}")
    private String privateKey;
    private String responseBody = "{\n" +"  \"jsonToken\" : \"";
    @GetMapping({"/logon","/"})
    public String logon(Model model) {
        model.addAttribute("user", new User());
        return "logon";
    }

    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity greetingSubmit(@ModelAttribute User user) {
        if(user==null || user.getUsername()==null || user.getPassword()==null){
            return ResponseEntity.badRequest().build();
        }

        String passwordEncr = Base64.getEncoder().encodeToString(user.getPassword() != null ? user.getPassword().getBytes() : null);
        Member m = repository.findByEmpIdAndPassword(user.getUsername(), passwordEncr);
        if (m != null) {
            String jwtToken = jwtGenerator.generateToken(user, m, privateKey);
            return ResponseEntity
                    .ok()
                    .header("Set-Cookie", "jwtToken=" + jwtToken+";Max-Age=60")
                    .body(responseBody+jwtToken+"\"}");
        } else {
            return ResponseEntity.status(401).body("not authorised");
        }

    }

    @GetMapping({"/logout","/logoff"})
    public ResponseEntity logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity
                .ok()
                .header("Set-Cookie", "jwtToken=null;Max-Age=-1")
                .body("ok");
    }
}
