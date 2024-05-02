package org.reelskill.data;

import org.reelskill.models.User;

public interface UserRepository {
    User findById(int userId);
    // Authentication
    User authenticateUser(String username, String password);

    // Get user email address for notifications
    String getUserEmailAddress(String emailAddress);

    // Create new user
    User createUser(User user);
}
