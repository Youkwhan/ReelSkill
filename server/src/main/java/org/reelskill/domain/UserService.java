package org.reelskill.domain;

import jakarta.validation.ConstraintViolation;
import org.reelskill.data.UserRepository;
import org.reelskill.models.User;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository repository;
    private final Validator validator;

    public UserService(UserRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public User findById(int userId) {
        return repository.findById(userId);
    }

    public User authenticateUser(String username, String password) {
        return repository.authenticateUser(username, password);
    }

    public String getUserEmailAddress(String username) {
        return repository.getUserEmailAddress(username);
    }

    public Result<User> createUser(User user) {
        Result<User> result = validate(user);
        if (!result.isSuccess())  {
            return result;
        }

        if (user.getUserId() != 0)  {
            result.addMessage("userId cannot be set for 'create' operation", ResultType.INVALID);
            return result;
        }

        user = repository.createUser(user);
        result.setPayload(user);
        return result;
    }

    private Result<User> validate(User user)  {
        Result<User> result = new Result<>();
        if (user == null) {
            result.addMessage("user cannot be null", ResultType.INVALID);
            return result;
        }

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (var violation : violations) {
            result.addMessage(violation.getMessage(), ResultType.INVALID);
        }

        return result;
    }

}
