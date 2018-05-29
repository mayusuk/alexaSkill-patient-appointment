package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SimpleDTO {
    private Long accountId;
    private String modifiedUser;
    private Timestamp modifiedTStamp;
    private Boolean active;
}