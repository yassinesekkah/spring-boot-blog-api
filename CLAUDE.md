# CLAUDE.md

> Had fichier kayqra Claude Code automatiquement f kol session. Howa "memory" dyal projet.

---

## 👤 About Me (mhim bzzaf)

I am a beginner learning Spring Boot and Angular. I have done basic tutorials only (hello world, REST controller, basic Angular components/services). This is my first time using Claude Code.

**My learning style:**
- Explain WHY before HOW
- Go step by step — don't dump everything at once
- When you write code, walk me through it line by line on the first occurrence of a new concept
- If I make a mistake, point it out and explain the reason
- Use simple language and concrete examples
- It's OK to be repetitive on important concepts

**How to respond to me:**
- If you have 2 valid approaches, tell me both with pros/cons — don't just pick
- Before adding a new annotation or pattern, explain what it does
- Ask me to try something myself when it's a good learning moment
- If I ask for "the answer", remind me gently that I'm here to learn

---

## 🎯 Project Overview

**Goal:** Build a Blog API to learn Spring Boot + Angular step by step.

**Tech stack:**
- Backend: Java 17, Spring Boot 4.0.6, Spring Web, Spring Data JPA, Spring Security
- Database: PostgreSQL (H2 for tests)
- Build: Maven
- Auth: JWT with RS256 (RSA keys) via JJWT 0.12.5
- Frontend: Angular + TypeScript + RxJS (next phase)
- Helpers: Lombok, Jackson, Bean Validation

**Phases:**
1. **Phase 1 ✅** — Backend CRUD — Post entity
2. **Phase 2 ✅** — Comment entity + OneToMany relations
3. **Phase 3 ✅** — DTOs + Mappers + Validation + Category entity
4. **Phase 4 ✅** — JWT Authentication (RS256), Spring Security, User registration/login
5. **Phase 5 (current):** JWT security hardening (current branch: `jwt_security`)
6. **Phase 6 (next):** Angular frontend

---

## 🏗️ Project Architecture

Package root: `com.example.demo`

```
src/main/java/com/example/demo/
├── controller/    → REST endpoints (thin, no logic)
│   ├── AuthController.java
│   ├── CategoryController.java
│   ├── CommentController.java
│   ├── PostController.java
│   └── UserController.java
├── service/       → Business logic
│   ├── AuthService.java
│   ├── CategoryService.java
│   ├── CommentService.java
│   ├── JwtService.java
│   ├── PostService.java
│   └── UserService.java
├── repository/    → Database access (JpaRepository)
│   ├── CategoryRepository.java
│   ├── CommentRepository.java
│   ├── PostRepository.java
│   └── UserRepository.java
├── entity/        → JPA entities
│   ├── Category.java
│   ├── Comment.java
│   ├── Post.java
│   └── User.java
├── dto/           → Request/Response objects
│   ├── CategoryRequest.java / CategoryResponse.java
│   ├── CommentDTO.java / CommentRequest.java
│   ├── LoginRequest.java / LoginResponse.java
│   ├── PostRequest.java / PostResponse.java
│   ├── RegisterRequest.java
│   └── UserResponse.java
├── mapper/        → Entity ↔ DTO conversion (manual, no MapStruct)
│   ├── CategoryMapper.java
│   ├── CommentMapper.java
│   ├── PostMapper.java
│   └── UserMapper.java
├── config/        → Spring configuration
│   ├── KeyConfig.java     (loads RSA keys for JWT)
│   └── SecurityConfig.java (Spring Security rules)
├── security/      → JWT filter + UserDetails
│   ├── CorsConfig.java
│   ├── JwtAuthFilter.java
│   ├── UserDetailsImpl.java
│   └── UserDetailsServiceImpl.java
└── exception/     → Global error handling
    └── GlobalExceptionHandler.java
```

---

## ✅ Coding Rules

### General
- Readability > cleverness
- Use meaningful variable names
- One concept at a time
- Don't refactor working code unless I ask

### Controllers
- Annotate with `@RestController` + `@RequestMapping`
- Return `ResponseEntity<>` for proper HTTP status codes
- Keep them THIN — they only receive request and call service
- ❌ No business logic in controllers
- ❌ No database calls in controllers

### Services
- Annotate with `@Service`
- Constructor injection (NOT `@Autowired` on fields)
- All business logic here

### Repositories
- Interface extending `JpaRepository<Entity, IdType>`
- No implementation needed — Spring generates it

### Entities
- `@Entity` + `@Table(name = "...")` if name differs
- `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` for PostgreSQL
- Use Lombok: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`
- Avoid `@Data` on entities (causes problems with relations)

### Entity Relations (implemented)
- User → Post: `@OneToMany(mappedBy = "user")` on User, `@ManyToOne` + `@JoinColumn` on Post
- Post → Comment: `@OneToMany(mappedBy = "post")` on Post, `@ManyToOne` + `@JoinColumn` on Comment
- Post → Category: `@ManyToOne` + `@JoinColumn(name = "category_id")` on Post

### DTOs & Mappers
- Entities are never returned directly in API responses — always use DTOs
- Manual mapping in `*Mapper.java` classes (no MapStruct)
- Naming: `*Request` for input, `*Response` / `*DTO` for output

### JWT Authentication
- Algorithm: RS256 (RSA asymmetric — private key signs, public key verifies)
- RSA keys loaded via `KeyConfig.java` from `application.properties`
- Token contains: email + userId claims
- `JwtAuthFilter` intercepts every request, validates token, sets SecurityContext
- Public routes: `/auth/register`, `/auth/login`
- All other routes require `Authorization: Bearer <token>` header

---

## 🌐 REST API Endpoints

### Auth (public)
| Action    | Method | URL              |
|-----------|--------|------------------|
| Register  | POST   | `/auth/register` |
| Login     | POST   | `/auth/login`    |

### Posts (protected)
| Action         | Method | URL                      |
|----------------|--------|--------------------------|
| List all       | GET    | `/posts` (with pagination)|
| Get one        | GET    | `/posts/{id}`            |
| Create         | POST   | `/posts`                 |
| Update         | PUT    | `/posts/{id}`            |
| Delete         | DELETE | `/posts/{id}`            |
| By user        | GET    | `/posts/user/{userId}`   |

### Categories, Comments, Users — similar CRUD pattern

**Status codes:**
- `200 OK` — success with body
- `201 Created` — POST success
- `204 No Content` — DELETE success
- `404 Not Found` — resource missing
- `400 Bad Request` — invalid input

---

## 🗄️ Database

**Local dev config** (`application.properties`):
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**RSA key config** (also in `application.properties`):
```properties
rsa.private-key=<base64 or PEM>
rsa.public-key=<base64 or PEM>
```

⚠️ **Security rule:** Never commit passwords or RSA private keys. Use environment variables or `application-local.properties` (gitignored).

`.gitignore` should include:
```
.env
application-local.properties
target/
.idea/
*.iml
*.pem
```

---

## 🧪 Testing

Not yet implemented. When we get there: JUnit 5 + `@SpringBootTest`, `@WebMvcTest`, MockMvc.

---

## 📦 Git Workflow

```bash
git status              # Always start with this
git add <specific-file> # Prefer this over `git add .`
git commit -m "feat: add Post entity"
git push
```

**Commit message style** (Conventional Commits):
- `feat:` new feature
- `fix:` bug fix
- `refactor:` code change without behavior change
- `docs:` documentation
- `chore:` config, dependencies

---

## 💡 Reminders for Claude

When helping me:

1. **Don't write large blocks of code without explanation.** If file > 30 lines, walk me through the important parts.
2. **New annotation = explanation required.** First time you write `@Transactional`, `@Valid`, `@PathVariable`, etc., tell me what it does in 1–2 sentences.
3. **Suggest, don't impose.** If you think we should add something, ask first — don't just add it.
4. **Run commands when needed.** If you can run `mvn spring-boot:run` or check files, do it.
5. **At end of each significant change**, give me a "what to try next".
6. **Security context:** JWT + RS256 is already implemented. Don't re-explain basics unless I ask. Focus on what's missing or broken on the current `jwt_security` branch.

---

## 📋 Current Status

**Phase:** 5 — JWT security hardening
**Branch:** `jwt_security`
**Last completed:** Migrated JWT signing from HS256 to RS256 + added UserResponse DTO
**Next step:** [update this each session]

---

**Last updated:** 2026-05-23
