package pdp.uz.codingbat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResultDto {

    private Long userId;
    private Long questionId;
    private String response;
    private boolean isCorrect;

}
