package fox.dev.todo_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long id;

    private String todoTitle;

    private String todoDescription;

    private boolean completed;
}
