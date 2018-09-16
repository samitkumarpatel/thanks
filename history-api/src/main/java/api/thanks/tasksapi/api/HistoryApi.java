package api.thanks.tasksapi.api;

import api.thanks.tasksapi.exception.HistoryNotFoundException;
import api.thanks.tasksapi.model.History;
import api.thanks.tasksapi.repository.HistoryRepository;
import com.datastax.driver.core.utils.UUIDs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/history")
@CrossOrigin("http://localhost:8080")
public class HistoryApi {
    Logger log = LoggerFactory.getLogger(HistoryApi.class);

    @Autowired
    HistoryRepository historyRepository;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(historyRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable UUID id) {
        History h = historyRepository.findHistoryById(id);
        if(h!=null){
            return ResponseEntity.ok().body(h);
        }
        throw new HistoryNotFoundException(id+" - not found");
    }

    @GetMapping("/filter/{txn}/{id}")
    public ResponseEntity gotFrom(
            @PathVariable(value = "txn")String txn, @PathVariable(value = "id")UUID id ) {

        //TO-ME  & FROM-OTHER

        log.info("### filter invoked with txn: {} , for id: {} ",txn,id);
        if("TO-ME".equals(txn)){
            return ResponseEntity.ok().body(historyRepository.findHistoryGivenByMe(id));
        }

        if("FROM-OTHER".equals(txn)) {
            return ResponseEntity.ok().body(historyRepository.findHistoryForMe(id));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity saveHistory(@RequestBody History history) {
        history.setId(UUIDs.timeBased());
        history.setCreateDate(new Date());
        return ResponseEntity.ok().body(historyRepository.save(history));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateHistory(@PathVariable UUID id,@RequestBody History history) {
        History h = historyRepository.findHistoryById(id);
        history.setCreateDate(h.getCreateDate());
        if(h!=null && id.equals(history.getId())){
            return ResponseEntity.ok().body(historyRepository.save(history));
        }
        throw new HistoryNotFoundException(id+"- history not found");
    }
}
