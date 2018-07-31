package api.thanks.memberapi.api;

import api.thanks.memberapi.exception.MemberNotFoundException;
import api.thanks.memberapi.exception.UnauthorisedException;
import api.thanks.memberapi.model.Member;
import api.thanks.memberapi.repository.MemberRepository;
import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:8080")
public class MemberApi {

    @Autowired
    Logger log;

    @Autowired
    MemberRepository memberRepository;

    private static String jsonKeyConstant = "{\"error\": \"";

    //TODO possibli merge both resources
    @GetMapping("/members/all")
    public ResponseEntity getAll(){
        log.log(Level.INFO, "Invoked getAll");
        return ResponseEntity.ok().body(memberRepository.findAll());
    }

    @GetMapping("/members/byId")
    public ResponseEntity getMember(){
        //TODO fixme
        UUID id = UUIDs.timeBased();
        log.log(Level.INFO,"Invoked getMember for :{0} ",id);
        Member m = memberRepository.findMemberById(id);
        if(m!=null){
            return ResponseEntity.ok().body(memberRepository.findMemberById(id));
        }
        throw new MemberNotFoundException("member not found");
    }
    //TODO possibli merge both resources



    @GetMapping("/members/filter")
    public ResponseEntity getMemberByTeamId(@RequestParam UUID teamId){
        log.log(Level.INFO,"Invoked getMemberByTeamId for teamId :{0} ",teamId);
        List<Member> members = memberRepository.findMemberByTeamId(teamId);
        if(members!=null){
            return ResponseEntity.ok().body(members);
        }
        throw new MemberNotFoundException("member not found");
    }

    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody Member member){
        member.setId(UUIDs.timeBased());
        member.setCreatedDate(new Date(System.currentTimeMillis()));
        member.setUpdateDate(member.getCreatedDate());
        member.setPassword(Base64.getEncoder().encodeToString("changeme".getBytes()));
        return ResponseEntity.ok().body(memberRepository.save(member));
    }

    @PostMapping("/members/{id}/points")
    public ResponseEntity increasePoints(@PathVariable("id") UUID id){
        Member m = memberRepository.findMemberById(id);
        if(m!=null && m.getId().equals(id)){
            m.setPoints(m.getPoints() + 5);
            return ResponseEntity.ok().body(memberRepository.save(m));
        }
        throw new MemberNotFoundException("member not found");
    }

    @PutMapping("/members/{id}")
    public ResponseEntity updateMember(@PathVariable("id") UUID id,@RequestBody Member member){
        //TODO need to be refactor
        Member expectedMember = memberRepository.findMemberById(id);
        if(expectedMember !=null && expectedMember.getId().equals(member.getId())){
            member.setPoints(expectedMember.getPoints());
            member.setUpdateDate(new Date(System.currentTimeMillis()));
            if(member.getPassword()!=null){
                member.setPassword(Base64.getEncoder().encodeToString(member.getPassword().getBytes()));
            }
            return ResponseEntity.ok().body(memberRepository.save(member));
        }
        throw new MemberNotFoundException("member not found");
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") UUID id){
        Member expectedMember = memberRepository.findMemberById(id);
        if(expectedMember !=null && expectedMember.getId().equals(id)){
            memberRepository.delete(expectedMember);
            return ResponseEntity.ok().body(id+"- deleted successfully");
        }
        throw new MemberNotFoundException("member not found");
    }

    @GetMapping("/members/validate/{empid}/{password}")
    public ResponseEntity validateUser(@PathVariable("empid") String empid,@PathVariable("password")String password) {
        //TODO do this based on security compliance
        String b = Base64.getEncoder().encodeToString(password.getBytes());
        Member m = memberRepository.findByEmpIdAndPassword(empid,b);
        if(m!=null){
            m.setPassword(null);
            return ResponseEntity.ok().body(m);
        }
        throw new UnauthorisedException("member not found");
    }

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity memberNotFoundExceptionHandler(MemberNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON_UTF8).body(jsonKeyConstant + e.getMessage() + "\"}");
    }

    @ExceptionHandler(UnauthorisedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ResponseEntity unAuthorised(UnauthorisedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON_UTF8).body(jsonKeyConstant + e.getMessage() + "\"}");
    }

    private List parseTechnology(String data){
        if(!StringUtils.isEmpty(data)){
            return Arrays.asList(data.split(","));
        }
        return null;
    }
}
