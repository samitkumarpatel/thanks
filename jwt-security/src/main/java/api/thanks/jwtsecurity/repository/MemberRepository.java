package api.thanks.jwtsecurity.repository;

import java.util.UUID;

import api.thanks.jwtsecurity.model.Member;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

public interface MemberRepository extends CassandraRepository<Member,UUID> {

    @Query("select * from Member where empid=?0 and password=?1 ALLOW FILTERING")
    Member findByEmpIdAndPassword(final String empid,final String password);
}
