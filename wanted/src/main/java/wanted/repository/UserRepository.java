package wanted.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wanted.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Override
	List<User> findAll();

	@Override
	Optional<User> findById(Long id);

	@Override
	User save(User user);

	@Override
	void deleteById(Long id);

}
