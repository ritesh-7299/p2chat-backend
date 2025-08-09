cat << 'EOF' > README.md
# 🗨️ P2Chat Backend

A Spring Boot–powered signaling server for peer-to-peer chat and file sharing using WebRTC. This backend handles WebSocket-based signaling, session management, and user connection lifecycle for real-time communication.

---

## 🚀 Features

- 📡 WebSocket signaling for WebRTC peer connections  
- 👤 User session management with lifecycle tracking  
- 🔗 One-to-one connection mapping between peers  
- 📁 Support for chat and file transfer via RTCDataChannel  
- 🧩 Modular Spring Boot architecture with JPA entities and services  
- 🪵 Structured logging via Logback  

---

## 🧱 Tech Stack

| Layer         | Technology              |
|--------------|--------------------------|
| Backend       | Spring Boot, WebSocket API |
| Persistence   | JPA, Hibernate, MySQL    |
| Messaging     | RTCDataChannel (via WebRTC) |
| Logging       | SLF4J + Logback          |
| Build Tool    | Maven                    |

---

## 📂 Project Structure

\`\`\`
src/
├── connection/         # Connection entity and service
├── socket/             # WebSocket signaling handler
├── system/             # BaseEntity with auditing
├── user/               # User entity and service
└── P2ChatApplication.java
\`\`\`

---

## ⚙️ Configuration

### Environment Variables

You can use a \`.env\` file and import it via \`application.properties\`:

\`\`\`properties
spring.config.import=optional:file:.env
\`\`\`

Example \`.env\`:

\`\`\`env
DB_URL=jdbc:mysql://localhost:3306/p2chat
DB_USER=root
DB_PASSWORD=yourpassword
\`\`\`

### Logging

Logback is configured to write logs to \`logs/app.log\`. You can customize this in \`logback.xml\`.

---

## 🔌 WebSocket Endpoint

- **URL:** \`ws://<host>:8080/ws\`
- **Handshake Param:** \`localId\` (used to identify the user)

### Example Client Connection

\`\`\`ts
const socket = new WebSocket("ws://localhost:8080/ws?localId=123456");
\`\`\`

---

## 📡 Signaling Flow

1. Client connects via WebSocket with \`localId\`
2. Backend registers session and creates user
3. Clients exchange signaling messages (\`offer\`, \`answer\`, \`candidate\`)
4. Backend routes messages based on \`to\` field
5. On disconnect, session and user are cleaned up

---

## 🧪 Development

### Run Locally

\`\`\`bash
./mvnw spring-boot:run
\`\`\`

### Test WebSocket

Use browser dev tools or a tool like [websocat](https://github.com/vi/websocat):

\`\`\`bash
websocat ws://localhost:8080/ws?localId=testUser
\`\`\`

---

## 📜 License

This project is open-source and available under the MIT License.

---

## 👨‍💻 Author

**Ritesh Macwan**  
Backend Specialist | WebRTC Enthusiast  
[GitHub](https://github.com/ritesh-7299) • [LinkedIn](https://www.linkedin.com/in/ritesh-macwan-8a70891ba)
