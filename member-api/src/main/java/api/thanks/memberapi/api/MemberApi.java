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
@RequestMapping("/api/v1/members")
@CrossOrigin("http://localhost:8080")
public class MemberApi {

    @Autowired
    Logger log;

    @Autowired
    MemberRepository memberRepository;

    private static String jsonKeyConstant = "{\"error\": \"";

    @GetMapping
    public ResponseEntity getAll(){
        log.log(Level.INFO, "Invoked getAll");
        return ResponseEntity.ok().body(memberRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getMember(@PathVariable UUID id){
        log.log(Level.INFO,"Invoked getMember for :{0} ",id);
        Member m = memberRepository.findMemberById(id);
        if(m!=null){
            return ResponseEntity.ok().body(memberRepository.findMemberById(id));
        }
        throw new MemberNotFoundException("member not found");
    }

    @GetMapping("/filter")
    public ResponseEntity getMemberByTeamId(@RequestParam UUID teamId){
        log.log(Level.INFO,"Invoked getMemberByTeamId for teamId :{0} ",teamId);
        List<Member> members = memberRepository.findMemberByTeamId(teamId);
        if(members!=null){
            return ResponseEntity.ok().body(members);
        }
        throw new MemberNotFoundException("member not found");
    }

    @PostMapping
    public ResponseEntity saveMember(@RequestBody Member member){
        member.setId(UUIDs.timeBased());
        member.setCreatedDate(new Date(System.currentTimeMillis()));
        member.setUpdateDate(member.getCreatedDate());
        member.setPassword(Base64.getEncoder().encodeToString("changeme".getBytes()));
        return ResponseEntity.ok().body(memberRepository.save(member));
    }

    @PostMapping("/{id}/points")
    public ResponseEntity increasePoints(@PathVariable("id") UUID id){
        Member m = memberRepository.findMemberById(id);
        if(m!=null && m.getId().equals(id)){
            m.setPoints(m.getPoints() + 5);
            return ResponseEntity.ok().body(memberRepository.save(m));
        }
        throw new MemberNotFoundException("member not found");
    }

    //TODO add some security around PUT and DELETE

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") UUID id){
        Member expectedMember = memberRepository.findMemberById(id);
        if(expectedMember !=null && expectedMember.getId().equals(id)){
            memberRepository.delete(expectedMember);
            return ResponseEntity.ok().body(id+"- deleted successfully");
        }
        throw new MemberNotFoundException("member not found");
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
