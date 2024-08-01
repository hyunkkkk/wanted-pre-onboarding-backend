package wanted.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wanted.entity.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	@Override
	List<Company> findAll();

	@Override
	Optional<Company> findById(Long id);

	@Override
	Company save(Company company);

	@Override
	void deleteById(Long id);

}
