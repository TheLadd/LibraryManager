package com.learn.library.domain;

import com.learn.library.data.UserJpaRepository;
import com.learn.library.data.UserRepository;
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
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Result<User> result;
        if (!violations.isEmpty()) {
            result = new Result<>(violations);
        }
        else {
            User savedUser = userRepository.save(user);
            if (savedUser != null) {
                result = new Result<>(savedUser);
            }
            else {
                result = new Result<>();
                result.setType(ResultType.DATABASE_FAILURE);
            }
        }

        return result;
    }
}
