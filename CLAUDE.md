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
- Backend: Java 17+, Spring Boot, Spring Web, Spring Data JPA
- Database: PostgreSQL
- Build: Maven
- Frontend: Angular + TypeScript + RxJS (later phase)
- Helpers: Lombok, Jackson

**Phases:**
1. **Phase 1 (current):** Backend CRUD — Post entity only
2. **Phase 2:** Add Comment entity + relations (OneToMany)
3. **Phase 3:** Add DTOs + validation
4. **Phase 4:** Angular frontend
5. **Phase 5 (optional, much later):** Auth, tests, deployment

⚠️ **We are in Phase 1.** Don't introduce concepts from later phases unless I ask.

---

## 🏗️ Project Architecture

Layered architecture:

```
src/main/java/com/example/blog/
├── controller/    → REST endpoints (thin, no logic)
├── service/       → Business logic
├── repository/    → Database access (JpaRepository)
├── entity/        → JPA entities (@Entity)
├── dto/           → Request/Response objects (added in Phase 3)
├── config/        → Configuration classes (later)
└── exception/     → Custom exceptions + handler (later)
```

**Rule:** Don't create a folder until we actually need it. No empty `dto/` or `exception/` folders in Phase 1.

---

## ✅ Coding Rules

### General
- Readability > cleverness
- Use meaningful variable names
- One concept at a time
- Don't refactor working code unless I ask

### Controllers
- Annotate with `@RestController` + `@RequestMapping("/api/posts")`
- Return `ResponseEntity<>` for proper HTTP status codes
- Keep them THIN — they only receive request and call service
- ❌ No business logic in controllers
- ❌ No database calls in controllers

Example:
```java
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {  // Constructor injection
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postService.findAll());
    }
}
```

### Services
- Annotate with `@Service`
- Constructor injection (NOT `@Autowired` on fields — explain why if I ask)
- All business logic here

### Repositories
- Interface extending `JpaRepository<Entity, IdType>`
- No implementation needed — Spring generates it

```java
public interface PostRepository extends JpaRepository<Post, Long> {
}
```

### Entities
- `@Entity` + `@Table(name = "...")` if name differs
- `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` for PostgreSQL
- Use Lombok: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`
- Avoid `@Data` on entities (causes problems with relations — I'll explain when relevant)

### Relations (Phase 2+)
When we add Comments:
- Use `@OneToMany(mappedBy = "post")` on Post
- Use `@ManyToOne` + `@JoinColumn(name = "post_id")` on Comment
- Prevent infinite JSON recursion with `@JsonManagedReference` / `@JsonBackReference`
- ⚠️ When we get there, **explain the recursion problem first**, then show the fix.

### DTOs (Phase 3+)
- Don't expose entities directly in API responses
- Naming: `PostRequest`, `PostResponse`, `CommentRequest`, `CommentResponse`
- Map manually first (so I understand), then introduce MapStruct only if I ask

---

## 🌐 REST API Conventions

| Action          | Method | URL              |
|-----------------|--------|------------------|
| List all posts  | GET    | `/api/posts`     |
| Get one post    | GET    | `/api/posts/{id}`|
| Create post     | POST   | `/api/posts`     |
| Update post     | PUT    | `/api/posts/{id}`|
| Delete post     | DELETE | `/api/posts/{id}`|

❌ Never: `/getPosts`, `/createPost`, `/post/delete/1`

Status codes:
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

⚠️ **Security rule:** Never commit passwords. Use `${DB_PASSWORD}` and set it via:
- `.env` file (gitignored), or
- Environment variable, or
- `application-local.properties` (gitignored)

Add to `.gitignore`:
```
.env
application-local.properties
target/
.idea/
*.iml
```

---

## 🧪 Testing (Phase 5)
Not yet — but when we get there: JUnit 5 + `@SpringBootTest`, `@WebMvcTest`, MockMvc.

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
3. **Suggest, don't impose.** If you think we should add validation, say "we could add validation here, want me to show you?" — don't just add it.
4. **Run commands when needed.** If you can run `mvn spring-boot:run` or check files, do it — don't just tell me to do it (unless it's something I should learn to do myself).
5. **At end of each significant change**, give me a "what to try next" — like "try hitting `GET /api/posts` with curl to verify".
6. **Don't skip ahead.** No Docker, no JWT, no MapStruct, no Swagger in Phase 1 even if it would be "better".

---

## 📋 Current Status

**Phase:** 1 — Backend CRUD setup
**Last completed:** [update this each session]
**Next step:** [update this each session]

---

**Last updated:** 2026-05-18    