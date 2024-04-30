# Capstone Proposals

## 1. Problem Statement
Studying for technical interviews or trying to stay on top of emerging technologies, such as those in software engineering, can be daunting and time-consuming. Aspiring candidates often struggle to effectively organize and prioritize their study materials, especially when it comes to practicing coding problems and reviewing key concepts.

Existing platforms and resources may offer a plethora of technical interview questions and practice problems, but they often lack features that facilitate efficient learning and retention, such as spaced repetition. Without a systematic approach to review and reinforce knowledge over time, candidates may find themselves overwhelmed by the sheer volume of information or forget critical concepts before the interview.

Therefore, there is a need for a software solution that addresses these pain points by offering a comprehensive platform for technical interview preparation with built-in spaced repetition functionality. This solution should provide users with curated lists of technical interview questions, interactive coding practice sessions, and personalized study plans based on spaced repetition algorithms. By leveraging spaced repetition techniques, the platform aims to enhance users' learning efficiency, improve long-term knowledge retention, and ultimately boost their confidence and performance in technical interviews.

## 2. Technical Solution

### Scenario
Youkwhan needs to practice coding problems on LeetCode for technical interviews and to improve his problem-solving skills in his career. He navigates to the LeetCode section of the platform, where he finds a customized list of recommended questions based on difficulty levels. Youkwhan selects a few questions from the list and starts practicing. After solving each question, he rates his confidence level and adds his notes to the SR-Capstone platform. He schedules spaced repetition reviews for those questions accordingly.

As Youkwhan continues practicing, he receives notifications reminding him of scheduled reviews and suggesting new questions based on his performance and learning objectives. With the help of the platform's personalized study plan and spaced repetition feature, Youkwhan feels more confident and prepared for the next challenge.

## 3. Glossary
### Spaced Repetition
A learning technique that involves reviewing information at increasing intervals over time to enhance long-term retention and recall. 
### User Profile
A collection of information and preferences associated with a user within a system. This may include personal details, settings, and deck details.
### Decks
Organized collections of study materials or content, typically used in spaced repetition systems. Decks can contain various items, such as flashcards or practice questions, grouped by topic, difficulty level, or other criteria.
### Cards
Individual units of study material within a deck, often used in spaced repetition systems like flashcards. Cards typically contain a question, problem statement, or concept on one side, and the corresponding answer or solution on the other side.
### Study Plan
The filtering option of how they want to approach the study plan.
### LeetCode
An online platform that hosts a vast collection of coding challenges and technical interview questions used by software engineers and tech companies for interview preparation. 
### Notification
An email or messasge is sent to users to inform them about updates and reminders of the next set of problems.

## 4. High-Level  Requirement
Each user role/authority
- Sign up for a user profile (authenticate)
- Create a Deck (User/Admin)
- Create a Card (User/Admin)
- Edit a Card (User/Admin)
- Import Leetcode questions (edit/User/Admin)
- Delete a Card (User/Admin)
- Delete a Deck (User/Admin) -> confirm
- Filter Cards (User/Admin)
- Select Difficulty (User/Admin)

## 5. User Stories/Scenarios
### Sign up for a User Profile
**Story**: As a new user, I want to create a profile to personalize my study experience.

**Scenario**:

1. **Precondition**: I am not registered on the platform.
2. I navigate to the sign-up page.
3. I fill in the required information, such as username, email, and password.
4. I submit the sign-up form.
5. **Postcondition**: My user profile is created, and I am logged into the

### Create a Deck
**Story**: As a user, I want to create a deck to organize my study materials.

**Scenario**:

1. **Precondition**: I am logged into the platform.
2. I navigate to the Create Deck button
3. I enter the title for the new deck. (Confirm/Delete)
4. I save the deck.
5. **Postcondition**: The new deck is created and added to my profile.

### Create a Card
**Story**: As a user, I want to create a card to add study content to my deck.

**Scenario**:

1. **Precondition**:  I am logged into the platform and have at least one deck created.
2. I navigate to the deck where I want to add the card.
3. I select the option to add a new card.
4. I input the question title (optional tags)
5. I input the problem, link to the leetcode, and my notes (which will be hidden hover).
6. I save the card
7. **Postcondition**: The new card is created and added to the selected deck.

### Edit a Card
**Story**: As a user or admin, I want to edit a card to update its content or details.

**Scenario**:
1. **Precondition**:  I am logged into the platform and have at least one card created.
2. I navigate to the deck containing the card I want to edit.
3. I select the card I want to edit.
4. I modify the question, answer, or other details of the card.
5. I save the changes.
7. **Postcondition**: The card is updated with the new information.

### Import LeetCode Questions
**Story**: As a user or admin, I want to import LeetCode questions to add them to my study materials.

**Scenario**:

1. **Precondition**: Edit or create a card
2. navigate to the import section or feature.
3. I select the option to import LeetCode questions.
4. import leetcode by link? (will have to see how later/ maybe API fetch)
5. The platform fetches and imports the selected LeetCode questions.
6. **Postcondition**: The imported questions are added to my study materials or a designated deck.

###  Delete a Card
