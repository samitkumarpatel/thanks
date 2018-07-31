package api.thanks.jwtsecurity.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table("member")
public class Member {
    @Id @PrimaryKey
    private UUID id;
    private String password;
    private String empId;
}
