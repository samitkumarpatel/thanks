package api.thanks.tasksapi.repository;

import api.thanks.tasksapi.model.History;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends CassandraRepository<History, UUID> {

    @AllowFiltering
    History findHistoryById(UUID id);

    @Query("select * from History where to_member_id=?0 ALLOW FILTERING")
    List<History> findHistoryGivenByMe(UUID memberId);

    @Query("select * from History where member_id=?0 ALLOW FILTERING")
    List<History> findHistoryForMe(UUID memberId);
}
