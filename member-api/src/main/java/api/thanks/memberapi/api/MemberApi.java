package api.thanks.memberapi.api;

import api.thanks.memberapi.exception.ErrorDetails;
import api.thanks.memberapi.exception.MemberNotFoundException;
import api.thanks.memberapi.model.Member;
import api.thanks.memberapi.repository.MemberRepository;
import com.datastax.driver.core.utils.UUIDs;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.UUID;
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

    @GetMapping("/members")
    public ResponseEntity getAll(){
        log.log(Level.INFO, "Invoked getAll");
        return ResponseEntity.ok().body(memberRepository.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity getMember(@PathVariable UUID id){
        log.log(Level.INFO,"Invoked getMember for :{0} ",id);
        Member m = memberRepository.findMemberById(id);
        if(m!=null){
            return ResponseEntity.ok().body(memberRepository.findMemberById(id));
        }
        throw new MemberNotFoundException("member not found");
    }

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

    @ExceptionHandler(MemberNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleRuntimeExceptions(MemberNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(false),500);
        if(ex instanceof MemberNotFoundException) {
            errorDetails= new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(false),400);
        }
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
