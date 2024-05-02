package org.reelskill.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String username;
    private String password;
    private String emailAddress;
    private Role role;
    private List<Deck> deckList = new ArrayList<>();

        // Constructor, getters, and setters
        public User() {
        }

        public User(int userId, String emailAddress, String username, String password, Role role, List<Deck> deckList) {
            this.userId = userId;
            this.emailAddress = emailAddress;
            this.username = username;
            this.password = password;
            this.role = role;
            this.deckList = deckList;
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
        }
