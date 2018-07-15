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

    @Query("select * from Member where empid=?0 and password=?1 ALLOW FILTERING")
    Member findByEmpIdAndPassword(final String empid,final String password);
}
