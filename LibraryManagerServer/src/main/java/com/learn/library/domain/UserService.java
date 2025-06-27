package com.learn.library.domain;

import com.learn.library.data.UserJpaRepository;
import com.learn.library.data.UserRepository;
import com.learn.library.domain.ErrorMessages.UserErrorMessage;
import com.learn.library.model.User;
import jakarta.persistence.EntityManagerFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    UserRepository userRepository;
    Validator validator;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public Result<User> add(User user) {
        Result<User> result = new Result<>();
        if (user == null) {
            result.addMessage(UserErrorMessage.NULL);
            return result;
        }

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            result.addMessages(violations.stream().map(ConstraintViolation::getMessage).toList());
        }
        else {
            User savedUser = userRepository.save(user);
            if (savedUser != null) {
                result.setPayload(savedUser);
            }
            else {
                result.setType(ResultType.DATABASE_FAILURE);
            }
        }

        return result;
    }
}
