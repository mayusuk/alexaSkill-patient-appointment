package dto;

import dto.SimpleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DTO extends SimpleDTO {
    private String creationUser;
    private Timestamp creationTStamp;
}
