package wanted.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wanted.entity.Company;
import wanted.entity.Recruitment;

@NoArgsConstructor
@Getter
@Setter
public class RecruitmentDetailResponseDTO {//채용공고 상세페이지 조회 응답 형태
	private Integer status;
    private Long id;
    private String company;
    private String country;
    private String location;
    private String position;
    private int reward;
    private String tech;
    private String description; // 채용내용
    private List<Long> otherRecruitmentIds;//회사의 다른 채용공고_id를 리스트에 저장

	public RecruitmentDetailResponseDTO(Recruitment recruitment){
		this.status = HttpStatus.OK.value();
		this.id = recruitment.getId();
		this.company = recruitment.getCompany().getName();//Recruitment를 통해 조인된 Company의 필드변수에 접근하기 위해서, .getCompany()
		this.country = recruitment.getCompany().getCountry();
		this.location = recruitment.getCompany().getLocation();
		this.position = recruitment.getPosition();
		this.reward = recruitment.getReward();
		this.tech = recruitment.getTech();
		this.description = recruitment.getDescription();

        Company company = recruitment.getCompany();//받아온 Recruitment객체과 조인된 Company 객체를 구함
        this.otherRecruitmentIds = company.getRecruitments().stream()//Company객체와 조인된 Recruitment객체들에 순차적으로 접근
                .filter(r -> !r.getId().equals(recruitment.getId())) // 현재 채용 공고는 제외함
                .map(Recruitment::getId) // Recruitment 객체의 id를 가져옴
                .collect(Collectors.toList());//List형태로
	}

    public HttpStatus getHttpStatus() {
        try{
            return HttpStatus.valueOf(status);
        }catch (IllegalArgumentException e){
            return HttpStatus.OK;
        }

    }


}

