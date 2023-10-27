# Study Chat Application Backend

The backend for the Study Chat application allows users to create study rooms, join rooms, chat, and more. This platform aims to provide an efficient way for students to collaborate and study together online.

## 🛠 Tools & Technologies

- **Java Spring Boot**: For creating RESTful APIs.
- **Maven**: For dependency management.
- **JUnit & MockMvc**: For unit testing the endpoints.
- **Rest Assured with Cucumber**: For integration testing.

## 🧪 Testing

All of my endpoints have been rigorously tested to ensure reliability. I use MockMvc for unit tests and Rest Assured combined with Cucumber for integration tests. Navigate to the `test` directory to review our test cases.

## 🚧 Challenges

- Setting up and testing and points to ensure that a user was able to register, login and send messages correctly.

## 📚 Documentation & Planning

- **User Stories**: [User Stories]
- **ERD Diagram**:

  **![Blank diagram (1)](https://github.com/TheProgrammingRiver/StudyChat/assets/114197158/372ca89b-a103-49f8-956b-1dee92583d2a)**


## 📦 Installation

To set up the backend locally, ensure you have Maven installed, then follow these steps:

```bash
# Clone the repository
git clone [repo_link]

# Navigate to the repo
cd [repo_name]

# Install dependencies
mvn install

# Run the application
mvn spring-boot:run
```

🌐 API Endpoints

| Request Type | URL                        | Functionality            | Access  |
|--------------|----------------------------|--------------------------|---------|
| POST         | /auth/users/login/         | Log in a user            | Public  |
| POST         | /auth/users/register/      | Register a new user      | Public  |
| GET          | /api/studyrooms/           | Get all study rooms      | Private |
| POST         | /api/studyrooms/create     | Create a new study room  | Private |
| POST         | /api/studyrooms/join       | Join an existing room    | Private |
| GET          | /api/messages/             | Get messages from a room | Private |
