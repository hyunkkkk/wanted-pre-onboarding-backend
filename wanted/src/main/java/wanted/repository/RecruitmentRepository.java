package wanted.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import wanted.entity.Recruitment;
@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>{


	@Override
	List<Recruitment> findAll();

	@Override
	Optional<Recruitment> findById(Long id);

	@Override
	Recruitment save(Recruitment recruitment);

	@Override
	void deleteById(Long id);

    @Query("SELECT r FROM Recruitment r WHERE r.company.name LIKE %:keyword%")
    List<Recruitment> searchRecruitments(@Param("keyword") String keyword);

    @Query("SELECT r FROM Recruitment r WHERE r.company.id = :companyId")
	List<Recruitment> findByCompany_Id(Long companyId);

    @Query("SELECT r FROM Recruitment r WHERE r.company.id = :companyId")
    // 반환 타입을 Page로 바꾸고 Pageable 파라미터를 추가합니다.
    Page<Recruitment> findByCompany_Id(@Param("companyId") Long companyId, Pageable pageable);
}
