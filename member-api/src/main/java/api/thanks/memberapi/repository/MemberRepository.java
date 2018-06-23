package api.thanks.memberapi.repository;

import api.thanks.memberapi.model.Member;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface MemberRepository extends CassandraRepository<Member,UUID> {
    @AllowFiltering
    List<Member> findMemberById(final UUID id);
}
