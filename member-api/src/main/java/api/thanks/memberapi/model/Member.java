package api.thanks.memberapi.model;

import com.datastax.driver.core.DataType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
public class Member {
    @PrimaryKey @Id
    private UUID id;
    private String password;
    private String firstname;
    private String lastname;
    private String role;
    private String team;
    private String empid;
    private boolean status;
    private List<String> technology;
    private int points;
    @CassandraType(type = DataType.Name.UDT,userTypeName = "address")
    private Map<String, Address> address;

    @CassandraType(type = DataType.Name.UDT,userTypeName = "contact")
    private Map<String, Address> contact;

    @Column("created_date")
    private Date createdDate;
    @Column("update_date")
    private Date updateDate;



}
