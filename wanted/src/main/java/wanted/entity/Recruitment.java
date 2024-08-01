package wanted.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recruitment {

	@ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;//하나의 회사는 여러개의 채용공고를 올릴 수 있다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Autoincrement

    private String position;//채용 포지션
    private int reward;//채용 보상금
    private String description;//채용 내용
	private String tech;//사용 기술, 언어



}
