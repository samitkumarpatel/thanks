package api.thanks.memberapi.model;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.utils.UUIDs;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
public class Member {
    @PrimaryKey @Id
    private UUID id = UUIDs.timeBased();
    private String password;
    private String firstname;
    private String lastname;
    private String role;
    private String team;
    private String empid;
    private boolean status;
    private List<String> technology;
    private int points;

    @CassandraType(type = DataType.Name.UDT,userTypeName = "history")
    private List<History> history;

}
