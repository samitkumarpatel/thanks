package api.thanks.teamapi.api;

import api.thanks.teamapi.exception.TeamNotFoundException;
import api.thanks.teamapi.model.Team;
import api.thanks.teamapi.repository.TeamRepository;
import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TeamApi {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/teams")
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(teamRepository.findAll());
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity getTeamById(@PathVariable UUID id){
        Team team = teamRepository.findTeamById(id);
        if(team!=null){
            return ResponseEntity.ok().body(team);
        }
        throw new TeamNotFoundException(id+"- not found");
    }

    @PostMapping("/teams")
    public ResponseEntity saveTeam(@RequestBody Team team){
        team.setId(UUIDs.timeBased());
        team.setCreateDate(new Date(System.currentTimeMillis()));
        return ResponseEntity.ok().body(teamRepository.save(team));
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity updateTeam(@PathVariable UUID id,@RequestBody Team team){
        Team t = teamRepository.findTeamById(id);
        if(t!=null && t.getId().equals(team.getId())){
            return ResponseEntity.ok().body(teamRepository.save(team));
        }
        throw new TeamNotFoundException(id+" - not found");
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity deleteTeam(@PathVariable UUID id){
        Team team = teamRepository.findTeamById(id);
        if(team!=null){
            teamRepository.delete(team);
            return ResponseEntity.ok().body(id + " - deleted successfully");
        }
        throw new TeamNotFoundException(id + " - not found");
    }
}
