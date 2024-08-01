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
    public RecruitmentDetailResponseDTO getRecruitmentDetail(@PathVariable Long id) {
        Optional<Recruitment> recruitment = recruitmentService.getRecruitmentById(id);
        return recruitment
                .map(RecruitmentDetailResponseDTO::new)
                .orElse(null);
    }


    @GetMapping("/search")
    public List<RecruitmentResponseDTO> searchRecruitments(@RequestParam String search) {
        List<Recruitment> lst = recruitmentService.searchRecruitments(search);
        return lst.stream()
                  .map(RecruitmentResponseDTO::new)
                  .collect(Collectors.toList());
    }



    @PostMapping
    public ResponseEntity<Recruitment> createRecruitment(@RequestBody RecruitmentRequestDTO recRequest) {
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
        return ResponseEntity.ok(createdRecruitment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recruitment> updateRecruitment(@PathVariable Long id, @RequestBody Recruitment rec) {
        Recruitment recruitment = recruitmentService.updateRecruitment(id, rec);
        return ResponseEntity.ok(recruitment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruitment(@PathVariable Long id) {
    	recruitmentService.deleteRecruitment(id);
        return ResponseEntity.noContent().build();
    }


}

