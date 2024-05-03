package org.reelskill.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int userId;
    @NotNull(message = "username is required")
    @NotBlank(message = "username is required")
    private String username;
    @NotNull(message = "password is required")
    @NotBlank(message = "password is required")
    private String password;
    @Pattern(message = "email must be an email",
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    private String emailAddress;
    private Role role;
    private List<Deck> deckList = new ArrayList<>();

    // Constructor, getters, and setters
    public User() {
    }

    public User(int userId, String emailAddress, String username, String password, Role role) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.role = role;
        this.deckList = new ArrayList<>();
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Deck> getDeckList() {
        return new ArrayList<>(deckList);
    }

    public void setDeckList(List<Deck> deckList) {
        this.deckList = deckList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(emailAddress, user.emailAddress) && role == user.role && Objects.equals(deckList, user.deckList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, emailAddress, role, deckList);
    }
}
