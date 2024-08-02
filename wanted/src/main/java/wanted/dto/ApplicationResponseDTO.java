package wanted.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wanted.entity.Application;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationResponseDTO {
	private Integer status;
	private Long id;
	private Long recruitment_id;
	private Long user_id;



	public ApplicationResponseDTO(Application application){
		this.status = HttpStatus.OK.value();
		this.id = application.getId();
		this.recruitment_id = application.getRecruitment().getId();
		this.user_id = application.getUser().getId();

	}

    public HttpStatus getHttpStatus() {
        try{
            return HttpStatus.valueOf(status);
        }catch (IllegalArgumentException e){
            return HttpStatus.OK;
        }

    }
}
