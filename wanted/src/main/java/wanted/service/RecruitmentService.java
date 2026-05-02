package wanted.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wanted.entity.Recruitment;
import wanted.repository.ApplicationRepository;
import wanted.repository.RecruitmentRepository;
import wanted.repository.UserRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

@Service
public class RecruitmentService {

    @Autowired
    private RecruitmentRepository recruitmentRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Recruitment> getAllRecruitments() {
        return recruitmentRepository.findAll();
    }

    public Optional<Recruitment> getRecruitmentById(Long id) {
        return recruitmentRepository.findById(id);
    }

    public List<Recruitment> getRecruitmentsByCompany(Long companyId) {
        // Slice를 사용하면 '전체 개수'를 세지 않고, 다음 데이터가 있는지만 확인(Limit 11)합니다.
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());

        Slice<Recruitment> recruitmentSlice = recruitmentRepository.findByCompany_Id(companyId, pageRequest);

        return recruitmentSlice.getContent();
    }

    public Recruitment createRecruitment(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }

    public Recruitment updateRecruitment(Long id, Recruitment rec) {
    	Recruitment recruitment = recruitmentRepository.findById(id).orElseThrow(() -> new RuntimeException("채용공고_id가 존재하지 않습니다."));
    	recruitment.setDescription(rec.getDescription());
    	recruitment.setReward(rec.getReward());;
    	recruitment.setTech(rec.getTech());
    	recruitment.setPosition(rec.getPosition());
        return recruitmentRepository.save(recruitment);
    }

    public void deleteRecruitment(Long id) {
    	recruitmentRepository.deleteById(id);
    }

    public List<Recruitment> searchRecruitments(String keyword) {
        return recruitmentRepository.searchRecruitments(keyword);
    }

}
