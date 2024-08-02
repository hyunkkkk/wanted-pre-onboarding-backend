package wanted.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Autoincrement

    @ManyToOne
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;//여러 지원은 하나의 채용공고와 매핑될 수 있다.

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;//유저는 한번만 지원할 수 있으니, 지원과 유저는 일대일 관계.

}
