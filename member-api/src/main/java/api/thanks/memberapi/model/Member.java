package api.thanks.memberapi.model;

import com.datastax.driver.core.DataType;
import jnr.x86asm.Mem;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.*;

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

    //TODO need to look how to achieve this with map - and yes we have to change the db structure as well
    @CassandraType(type = DataType.Name.UDT,userTypeName = "address",
            typeArguments = DataType.Name.CUSTOM)
    private Address address;
    @CassandraType(type = DataType.Name.UDT,userTypeName = "address",
            typeArguments = DataType.Name.CUSTOM)
    private Contact contact;

    @Column("created_date")
    private Date createdDate;
    @Column("update_date")
    private Date updateDate;

}
