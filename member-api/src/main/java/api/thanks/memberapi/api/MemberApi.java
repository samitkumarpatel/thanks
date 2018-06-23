package api.thanks.memberapi.api;

import api.thanks.memberapi.model.Member;
import api.thanks.memberapi.repository.MemberRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public ResponseEntity saveMember(Member member){
        return ResponseEntity.ok().body(memberRepository.save(member));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity updateMember(@PathParam("id") UUID id, Member member){
        Member expectedMember = memberRepository.findById(id).get();
        //TODO fixme?
        return ResponseEntity.ok().body(memberRepository.save(member));
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity deleteMember(@PathParam("id") UUID id){
        Member expectedMember = memberRepository.findById(id).get();
        memberRepository.delete(expectedMember);
        return ResponseEntity.ok().build();
    }

}
