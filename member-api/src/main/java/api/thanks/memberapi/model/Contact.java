package api.thanks.memberapi.model;

import com.datastax.driver.core.utils.UUIDs;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.Date;
import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@UserDefinedType
public class Contact {
    private String email;
    private String mobile;
    private String llt;
}

