package wanted.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String country;
	private String location;


	@OneToMany(mappedBy = "company")
    private List<Recruitment> recruitments;//하나의 회사는 여러개의 채용공고를 올릴 수 있다. 회사의 다른 채용공고에 접근하기 위함.





}
