package wanted.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wanted.entity.Recruitment;

@NoArgsConstructor
@Getter
@Setter
public class RecruitmentResponseDTO {//채용공고 조회 응답형태
	private Integer status;
	private Long id;
	private String company;
	private String country;
	private String location;
	private String position;
	private int reward;
	private String tech;

	public RecruitmentResponseDTO(Recruitment recruitment){
		this.status = HttpStatus.OK.value();
		this.id = recruitment.getId();
		this.company = recruitment.getCompany().getName();
		this.country = recruitment.getCompany().getCountry();
		this.location = recruitment.getCompany().getLocation();
		this.position = recruitment.getPosition();
		this.reward = recruitment.getReward();
		this.tech = recruitment.getTech();
	}

    public HttpStatus getHttpStatus() {
        try{
            return HttpStatus.valueOf(status);
        }catch (IllegalArgumentException e){
            return HttpStatus.OK;
        }

    }


}

