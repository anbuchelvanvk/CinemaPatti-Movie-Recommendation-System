# CinemaPatti 🎬  
**Terminal-Based Java Movie Review & Recommendation System**  

Presented at: Campus Recruitment Training Phase 2, 5th July  

## Overview  
CinemaPatti is a terminal-based application that brings film reviewing and genre-based recommendations to life through clean object-oriented design. It allows users to post reviews, rate films, and receive personalized recommendations based on their viewing preferences — all within a Java terminal interface.

## Features  
- List all available films with average review scores  
- Add new films dynamically if not already listed  
- Post reviews with numerical ratings  
- View the most-reviewed films  
- Get personalized movie recommendations based on genre interest  

## Technologies Used  
- Java Standard Edition (Java SE)  
- Collections Framework: `HashMap`, `ArrayList`, `HashSet`  
- Lambda expressions for sorting  
- `Scanner` class for user input  

## Class Architecture  
| Class Name            | Description                                                   |
|----------------------|---------------------------------------------------------------|
| `Movie`              | Stores film info, reviews, and calculates average scores      |
| `User`               | Tracks user name and preferred genres                         |
| `Review`             | Connects users with reviewed movies                           |
| `MovieManager`       | Manages film storage, top-rated logic                         |
| `RecommendationEngine` | Suggests films based on genre overlap                        |

## Learning Outcomes  
- Emphasized clean data modeling and extensible architecture  
- Practiced algorithmic logic for real-world user interactions  
- Designed personalized features resembling streaming platforms  
- Applied storytelling principles to feature design and code structure  

## Future Enhancements  
- Persistent storage with file I/O or databases  
- JavaFX GUI for visual interface  
- Web-based implementation using Spring Boot  
- API integrations (e.g. TMDb) for dynamic content  
- Sentiment-aware recommendations  

## How to Run  
1. Clone the repository  
2. Compile Java classes using `javac`  
3. Run `Main.java` via terminal  
4. Follow on-screen prompts to interact with the system

## Screenshots
1. Home :
<img width="943" height="409" alt="Screenshot 2025-07-31 084259" src="https://github.com/user-attachments/assets/daca7f65-a9ff-401e-91f1-f16381c227f1" />

2. Movie List :
<img width="625" height="276" alt="Screenshot 2025-07-31 084313" src="https://github.com/user-attachments/assets/57e82c29-5f73-415e-a4af-67513f7ccfc7" />


## Contribution & Feedback  
We welcome feedback, ideas, and collaboration! Whether you're a Java dev, cinephile, or both — feel free to open issues, submit pull requests, or drop us a message.
