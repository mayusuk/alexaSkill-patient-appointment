package dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PatientReminder extends DTO {
    private Timestamp dueDate;
    private Timestamp prevDate;
    private Timestamp reminderDate;
    private String reminderTypeId;
    private Long contextId;
	private Long ipid;
    private Long reminderId;
    private String description;
    private String location;
    private String messageId;
}
