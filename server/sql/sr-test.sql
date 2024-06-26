drop database if exists space_repetition_test;
create database space_repetition_test;
use space_repetition_test;

-- Users table to store user information
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) UNIQUE NOT NULL,
    `role` ENUM('admin', 'user') DEFAULT 'user'
);

-- Decks table to store user decks
CREATE TABLE decks (
    deck_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    deck_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Enum table for card types
CREATE TABLE cardType (
    card_type_id INT PRIMARY KEY AUTO_INCREMENT,
    difficulty_level ENUM('easy', 'medium', 'hard') UNIQUE
);

-- Enum table for card tags
CREATE TABLE cardTag (
    card_tag_id INT PRIMARY KEY AUTO_INCREMENT,
    difficulty_level ENUM('easy', 'medium', 'hard') UNIQUE NOT NULL
);

-- Cards table to store cards within decks
CREATE TABLE cards (
    card_id INT PRIMARY KEY AUTO_INCREMENT,
    deck_id INT NOT NULL,
    card_title VARCHAR(100) NOT NULL,
    card_notes TEXT,
    leetcode_problem VARCHAR(255),
    number_of_times_reviewed INT DEFAULT 0,
    last_reviewed TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    card_type_id INT,
    card_tag_id INT,
    FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
    FOREIGN KEY (card_type_id) REFERENCES cardType(card_type_id),
    FOREIGN KEY (card_tag_id) REFERENCES cardTag(card_tag_id)
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

delimiter //

create procedure set_known_good_state()
begin

     delete from recentlyDeleted;
        alter table recentlyDeleted auto_increment = 1;

        delete from cards;
        alter table cards auto_increment = 1;

        delete from decks;
        alter table decks auto_increment = 1;

        delete from users;
        alter table users auto_increment = 1;

        delete from cardType;
        alter table cardType auto_increment = 1;

        delete from cardTag;
        alter table cardTag auto_increment = 1;

    -- data
    INSERT INTO users (email_address, username, `password`, `role`)
    VALUES
        ('user1@example.com', 'user1', 'password1', 'user'),
        ('user2@example.com', 'user2', 'password2', 'user'),
        ('admin@example.com', 'admin', 'adminpassword', 'admin');

    INSERT INTO decks (user_id, deck_name)
    VALUES
        (1, 'Two Pointer'),
        (1, 'Binary Search'),
        (2, 'Depth-First Search');


    INSERT INTO cardType (difficulty_level) VALUES
        ('easy'),
        ('medium'),
        ('hard');

    INSERT INTO cardTag (difficulty_level) VALUES
        ('easy'),
        ('medium'),
        ('hard');

    INSERT INTO cards (deck_id, card_title, card_notes, leetcode_problem, card_type_id, card_tag_id)
    VALUES
        (1, '1. Two Sum', 'With using the Two Pointers pattern, and Pointer 1 pointing to the beginning of the array and Pointer 2 pointing to the end of the array, we will check if the numbers pointed by the pointers add up to the target sum. If they do, we have found our pair. If not, we should do one of these things:

                          • If the sum is bigger than the target sum, this means that we need a smaller sum so, we are going to decrement the Pointer 2 (end-pointer).

                          • If the sum is smaller than the target sum, this means that we need a bigger sum so, we are going to increment the Pointer 1 (start-pointer).',
          'https://leetcode.com/problems/two-sum/description/',
         null, 1),
		(1, '15. 3Sum', 'This problem is a follow-up of the Two Sum problem, but instead of finding two numbers that add up to a target sum, you need to find three numbers that add up to the target sum. The approach is to fix one number and then find the other two numbers using the Two Pointers pattern.',
		  'https://leetcode.com/problems/3sum/description/',
		  null, 2),
		(2, '704. Binary Search', 'Binary Search is a classic algorithm to efficiently find an element in a sorted array. The key idea is to repeatedly divide the search interval in half until the target element is found or the interval is empty.',
		  'https://leetcode.com/problems/binary-search/description/',
		  null, 1),
		(2, '56. Merge Intervals', 'Given a collection of intervals, merge all overlapping intervals. The key idea is to sort the intervals based on their start times and then merge intervals by checking for overlaps.',
		  'https://leetcode.com/problems/merge-intervals/description/',
		  null, 2),
		(3, '94. Binary Tree Inorder Traversal', 'Depth-First Search (DFS) is a fundamental algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node and explores as far as possible along each branch before backtracking.',
		  'https://leetcode.com/problems/binary-tree-inorder-traversal/description/',
		  null, 1);

    INSERT INTO recentlyDeleted (card_id, card_title, card_notes, leetcode_problem, number_of_times_reviewed, last_reviewed)
    VALUES
        (3, '124. Binary Tree Maximum Path Sum', 'For every node checking what could be the possible max path sum considering the subtrees and passing that max length to the parent. Now but the answer could be max of this path sum or left subtree max path sum till now + right subtree max path sum till now + node value or only the node value', 'https://leetcode.com/problems/binary-tree-maximum-path-sum/description/', 3, '2024-04-30 12:00:00');

end //

delimiter ;
