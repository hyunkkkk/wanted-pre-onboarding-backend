package wanted.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wanted.entity.Application;
import wanted.entity.Recruitment;
import wanted.entity.User;
import wanted.repository.ApplicationRepository;
import wanted.repository.RecruitmentRepository;
import wanted.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public Application applyToRecruitment(Long recruitmentId, Long userId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
            .orElseThrow(() -> new RuntimeException("recruitmentId not found"));

        wanted.entity.User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("userId not found"));

        Application application = new Application();
        application.setRecruitment(recruitment);
        application.setUser(user);

        return applicationRepository.save(application);
    }
}
