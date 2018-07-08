package api.thanks.teamapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Team {
    @PrimaryKey @Id
    private UUID id;
    private String teamid;
    private String name;
    @Column("member_uuid_as_an_admin")
    private UUID adminUid;
    private String description;
    @Column("created_date")
    private Date createDate;
}
