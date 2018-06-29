package api.thanks.teamapi.repository;

import api.thanks.teamapi.model.Team;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface TeamRepository extends CassandraRepository<Team,UUID> {

    @AllowFiltering
    Team findTeamById(UUID id);
}
