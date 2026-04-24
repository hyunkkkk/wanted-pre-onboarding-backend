package wanted.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wanted.dto.RecruitmentDetailResponseDTO;
import wanted.dto.RecruitmentRequestDTO;
import wanted.dto.RecruitmentResponseDTO;
import wanted.entity.Company;
import wanted.entity.Recruitment;
import wanted.service.CompanyService;
import wanted.service.RecruitmentService;

@RestController
@RequestMapping("/api/recruitments")
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<RecruitmentResponseDTO> getAllRecruitments() {
        return recruitmentService.getAllRecruitments().stream()
                .map(RecruitmentResponseDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RecruitmentDetailResponseDTO> getRecruitmentDetail(@PathVariable Long id) {
        // 1. 해당 공고 상세 정보 가져오기
        Recruitment recruitment = recruitmentService.getRecruitmentById(id)
                .orElseThrow(() -> new RuntimeException("해당 채용공고가 없습니다."));

        // 2. 이 회사의 다른 공고 리스트 가져오기 (우리가 만든 '10개 제한' 서비스 호출!)
        List<Recruitment> otherRecruitments = recruitmentService.getRecruitmentsByCompany(recruitment.getCompany().getId());

        // 3. 다른 공고들의 ID만 추출
        List<Long> otherIds = otherRecruitments.stream()
                .map(Recruitment::getId)
                .filter(recId -> !recId.equals(id)) // 현재 보고 있는 공고 ID는 제외하는 센스!
                .collect(Collectors.toList());

        // 4. DTO 생성 및 데이터 세팅
        RecruitmentDetailResponseDTO response = new RecruitmentDetailResponseDTO(recruitment);
        response.setOtherRecruitmentIds(otherIds); // DTO에 setter가 있다면 사용

        return ResponseEntity.ok(response);
    }


    @GetMapping("/search")
    public List<RecruitmentResponseDTO> searchRecruitments(@RequestParam String search) {
        List<Recruitment> lst = recruitmentService.searchRecruitments(search);
        return lst.stream()
                  .map(RecruitmentResponseDTO::new)
                  .collect(Collectors.toList());
    }


    @PostMapping
    public ResponseEntity<RecruitmentResponseDTO> createRecruitment(@RequestBody RecruitmentRequestDTO recRequest) {
        Optional<Company> companyOpt = companyService.getCompanyById(recRequest.getCompanyId());
        if (companyOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Recruitment rec = new Recruitment();
        rec.setCompany(companyOpt.get());
        rec.setPosition(recRequest.getPosition());
        rec.setReward(recRequest.getReward());
        rec.setDescription(recRequest.getDescription());
        rec.setTech(recRequest.getTech());

        Recruitment createdRecruitment = recruitmentService.createRecruitment(rec);
        RecruitmentResponseDTO responseDto = new RecruitmentResponseDTO(createdRecruitment);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruitmentResponseDTO> updateRecruitment(@PathVariable Long id, @RequestBody Recruitment rec) {
        Recruitment recruitment = recruitmentService.updateRecruitment(id, rec);
        RecruitmentResponseDTO responseDto = new RecruitmentResponseDTO(recruitment);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruitment(@PathVariable Long id) {
    	recruitmentService.deleteRecruitment(id);
        return ResponseEntity.noContent().build();
    }


}

