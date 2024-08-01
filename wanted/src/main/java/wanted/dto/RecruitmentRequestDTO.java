package wanted.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitmentRequestDTO {//채용공고 등록,수정을 요청할 때 쓰일 parameter들.

    private Long companyId;
    private String position;
    private int reward;
    private String description;
    private String tech;


}
