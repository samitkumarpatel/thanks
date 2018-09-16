package api.thanks.tasksapi.model;

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
public class History {
    @PrimaryKey @Id
    private UUID id;
    @Column("given_to")
    private UUID givenTo;
    @Column("got_from")
    private UUID gotFrom;
    private int point;
    @Column("created_date")
    private Date createDate;
}
