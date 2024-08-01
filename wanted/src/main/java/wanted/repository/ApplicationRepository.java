package wanted.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wanted.entity.Application;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{

	@Override
	Application save(Application application);

}
