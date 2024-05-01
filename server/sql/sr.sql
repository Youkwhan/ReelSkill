drop database if exists space_repetition;
create database space_repetition;
use space_repetition;

-- Users table to store user information
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    email_address VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` ENUM('admin', 'user') DEFAULT 'user'
);

-- Decks table to store user decks
CREATE TABLE decks (
    deck_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    deck_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Enum table for card types
CREATE TABLE cardType (
    cardType_id INT PRIMARY KEY AUTO_INCREMENT,
    cardType_name ENUM('easy', 'medium', 'hard') UNIQUE NOT NULL
);

-- Enum table for card tags
CREATE TABLE cardTag (
    cardTag_id INT PRIMARY KEY AUTO_INCREMENT,
    cardTag_name ENUM('easy', 'medium', 'hard') UNIQUE NOT NULL
);

-- Cards table to store cards within decks
CREATE TABLE cards (
    card_id INT PRIMARY KEY AUTO_INCREMENT,
    deck_id INT NOT NULL,
    card_title VARCHAR(100) NOT NULL,
    card_notes TEXT,
    leetcode_problem VARCHAR(255),
    number_of_times_reviewed INT DEFAULT 0,
    last_reviewed TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    cardType_id INT NOT NULL,
    cardTag_id INT NOT NULL,
    FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
    FOREIGN KEY (cardType_id) REFERENCES cardType(cardType_id),
    FOREIGN KEY (cardTag_id) REFERENCES cardTag(cardTag_id)
);


-- Table to store recently deleted cards for potential recovery
-- When a card is deleted from the Cards table, its information is copied to the RecentlyDeleted table before it is permanently removed from the database.
-- When undoing a deletion, you can retrieve the card information from the RecentlyDeleted table based on its CardID. Once the card is restored, you can remove its entry from the RecentlyDeleted table.
CREATE TABLE recentlyDeleted (
    card_id INT PRIMARY KEY,
    card_title VARCHAR(100) NOT NULL,
    card_notes TEXT,
    leetcode_problem VARCHAR(255),
    number_of_times_reviewed INT DEFAULT 0,
    last_reviewed TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

