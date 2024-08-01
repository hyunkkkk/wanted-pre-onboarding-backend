package wanted.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wanted.entity.Recruitment;
import wanted.repository.ApplicationRepository;
import wanted.repository.RecruitmentRepository;
import wanted.repository.UserRepository;

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


    public List<Recruitment> getRecruitmentsByCompany(Long companyId) {
        return recruitmentRepository.findByCompany_Id(companyId);
    }
}
