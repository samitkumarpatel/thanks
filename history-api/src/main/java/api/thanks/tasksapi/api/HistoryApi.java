package api.thanks.tasksapi.api;

import api.thanks.tasksapi.exception.HistoryNotFoundException;
import api.thanks.tasksapi.model.History;
import api.thanks.tasksapi.repository.HistoryRepository;
import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class HistoryApi {
    @Autowired
    HistoryRepository historyRepository;

    @GetMapping("/history")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(historyRepository.findAll());
    }

    @GetMapping("/history/{id}")
    public ResponseEntity getById(@PathVariable UUID id) {
        History h = historyRepository.findHistoryById(id);
        if(h!=null){
            return ResponseEntity.ok().body(h);
        }
        throw new HistoryNotFoundException(id+" - not found");
    }

    @GetMapping("/history/filter")
    public ResponseEntity getHistoryForMemberId(@RequestParam UUID memberId) {
        return ResponseEntity.ok().body(historyRepository.findHistoryByMemberId(memberId));
    }

    @PostMapping("/history")
    public ResponseEntity saveHistory(@RequestBody History history) {
        history.setId(UUIDs.timeBased());
        history.setCreateDate(new Date());
        return ResponseEntity.ok().body(historyRepository.save(history));
    }

    @PutMapping("/history/{id}")
    public ResponseEntity updateHistory(@PathVariable UUID id,@RequestBody History history) {
        History h = historyRepository.findHistoryById(id);
        history.setCreateDate(h.getCreateDate());
        if(h!=null && id.equals(history.getId())){
            return ResponseEntity.ok().body(historyRepository.save(history));
        }
        throw new HistoryNotFoundException(id+"- history not found");
    }
}
