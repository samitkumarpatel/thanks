package api.thanks.memberapi.repository;

import api.thanks.memberapi.model.Member;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MemberRepository extends CassandraRepository<Member,UUID> {
    @AllowFiltering
    Member findMemberById(final UUID id);

    @AllowFiltering
    List<Member> findMemberByTeamId(final UUID teamId);

    @Query("select m from Member m where u.email=?1 and u.password=?2")
    Member getMemberByEmailAndPassword(String username,String password);
}
