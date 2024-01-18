package com.example.banknator.users;

import com.example.banknator.entity.UserCredential;
import com.example.banknator.entity.UserProfile;
import com.example.banknator.shared.MessageResponse;
import com.example.banknator.users.dto.PostNewUserInformation;
import com.example.banknator.users.dto.UpdateUserInformation;
import com.example.banknator.users.dto.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    protected final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserCredentialRepository userCredentialRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserCredentialRepository userCredentialRepository, UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder) {
        this.userCredentialRepository = userCredentialRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional(rollbackOn = {DateTimeParseException.class, RuntimeException.class})
    public User createUserInformation(PostNewUserInformation request) {
        logger.info("UserServiceImpl:MJM:L35->Creating the UserCredential and UserProfile");
        if(userCredentialRepository.findByEmail(request.email()).isPresent()) throw new EntityExistsException("Email already inuse, Please log in.");
        UserCredential userCredential = new UserCredential(request.email(), passwordEncoder.encode(request.password()));
        userCredentialRepository.save(userCredential);
        userCredential = getUserCredentialByEmail(request.email()).get();
        UserProfile userProfile = new UserProfile(
                request.firstName(),
                request.lastName(),
                request.address(),
                request.phone(),
                request.creditScore(),
                LocalDate.parse(request.dateOfBirth()),
                userCredential);
        userProfileRepository.save(userProfile);
        logger.info("UserServiceImpl:MJM:L49->Completed and returning data in a User Record");
        return new User(
                request.firstName(),
                request.lastName(),
                request.address(),
                request.phone(),
                request.email(),
                request.creditScore(),
                LocalDate.parse(request.dateOfBirth()));
    }

    @Override
    public void updateUserInformation(UpdateUserInformation request) {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(request.id());
        if(userCredential.isEmpty()) throw new EntityNotFoundException("User not in database");
        Optional<UserProfile> userProfile = userProfileRepository.findByUserCredentialId(request.id());
        if(!request.firstName().equals(userProfile.get().getFirstName())){
            userProfile.get().setFirstName(request.firstName());
        }
        if(!request.lastName().equals(userProfile.get().getLastName())){
            userProfile.get().setLastName(request.lastName());
        }
        if(!request.email().equals(userCredential.get().getEmail())) {
            userCredential.get().setEmail(request.email());
        }
        if(request.phone() != userProfile.get().getPhone()) {
            userProfile.get().setPhone(request.phone());
        }
        if(!request.address().equals(userProfile.get().getAddress())) {
            userProfile.get().setAddress(request.address());
        }
        if(!request.password().equals(userCredential.get().getPassword())) {
            userCredential.get().setPassword(passwordEncoder.encode(request.password()));
        }
        if(!Objects.equals(request.creditScore(), userProfile.get().getCreditScore())) {
            userProfile.get().setCreditScore(request.creditScore());
        }
        userCredentialRepository.save(userCredential.get());
        userProfileRepository.save(userProfile.get());
    }

    @Override
    public MessageResponse disableUser(Long id) {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(id);
        if(userCredential.isEmpty()) throw new EntityNotFoundException("User not in database");
        userCredential.get().setDisabled(true);
        userCredential.get().setDisabledAt(LocalDate.now());
        userCredentialRepository.save(userCredential.get());
        return new MessageResponse("User Disabled");
    }

    @Override
    public MessageResponse enableUser(Long id) {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(id);
        if(userCredential.isEmpty()) throw new EntityNotFoundException("User not in database");
        userCredential.get().setDisabled(false);
        userCredential.get().setDisabledAt(null);
        userCredentialRepository.save(userCredential.get());
        return new MessageResponse("User Enabled");
    }

    @Override
    public List<User> getAllUsers() {
        List<UserCredential> userCredentials = userCredentialRepository.findAll();
        if(userCredentials.isEmpty()) throw new EntityNotFoundException("No users have been made");
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < userCredentials.size(); i++) {
            users.add(new User(
                    userProfiles.get(i).getFirstName(),
                    userProfiles.get(i).getLastName(),
                    userProfiles.get(i).getAddress(),
                    userProfiles.get(i).getPhone(),
                    userCredentials.get(i).getEmail(),
                    userProfiles.get(i).getCreditScore(),
                    userProfiles.get(i).getDateOfBirth()
            ));
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(id);
        if (userCredential.isEmpty()) throw new EntityNotFoundException("User not found");
        Optional<UserProfile> userProfile = userProfileRepository.findByUserCredentialId(id);
        User user = new User(
                userProfile.get().getFirstName(),
                userProfile.get().getLastName(),
                userProfile.get().getAddress(),
                userProfile.get().getPhone(),
                userCredential.get().getEmail(),
                userProfile.get().getCreditScore(),
                userProfile.get().getDateOfBirth()
        );
        return user;
    }

    public Optional<UserCredential> getUserCredentialByEmail(String email) {
        return userCredentialRepository.findByEmail(email);
    }

}
