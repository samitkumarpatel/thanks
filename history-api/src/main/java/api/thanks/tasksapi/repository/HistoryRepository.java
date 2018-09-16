package api.thanks.tasksapi.repository;

import api.thanks.tasksapi.model.History;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends CassandraRepository<History, UUID> {

    @AllowFiltering
    History findHistoryById(UUID id);

    @AllowFiltering
    List<History> findHistoryByGivenTo(UUID memberId);

    @AllowFiltering
    List<History> findHistoryByGotFrom(UUID memberId);
}
