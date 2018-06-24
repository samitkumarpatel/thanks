package api.thanks.memberapi.model;

import com.datastax.driver.core.utils.UUIDs;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@UserDefinedType
public class Address {
    private String country;
    private String city;
    private String pin;
}

