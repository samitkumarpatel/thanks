package api.thanks.memberapi.api;

import api.thanks.memberapi.model.Member;
import api.thanks.memberapi.repository.MemberRepository;
import com.datastax.driver.core.utils.UUIDs;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
@NoArgsConstructor
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
        return ResponseEntity.ok().body(memberRepository.findMemberById(id));
    }

    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody Member member){
        member.setId(UUIDs.timeBased());
        member.setCreatedDate(new Date(System.currentTimeMillis()));
        member.setUpdateDate(member.getCreatedDate());
        return ResponseEntity.ok().body(memberRepository.save(member));
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
        return ResponseEntity.badRequest().body(id+" not found");
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") UUID id){
        Member expectedMember = memberRepository.findMemberById(id);
        if(expectedMember !=null && expectedMember.getId().equals(id)){
            memberRepository.delete(expectedMember);
            return ResponseEntity.ok().body(id);
        }
        return ResponseEntity.badRequest().body(id+" not found");
    }

}
