## Workout Planner

Workout Planner é uma API para gerenciar usuários, treinos e exercícios. O projeto está em desenvolvimento e será expandido futuramente com funcionalidades como controle de permissões e autenticação.

### Funcionalidades essenciais a serem desenvolvidas

- CRUD de usuários

- CRUD de exercícios

- CRUD de treinos a partir dos exercícios cadastrados

### Melhorias futuras

- Roles e permissões
- Autenticação
- Testes unitários
- Docker

### Tecnologias

- Java 21

- Spring Boot

- JPA/Hibernate

- H2 Database (Utilizado durante a fase inicial do desenvolvimento, mas posteriomente será utilizado PostgreSQL)

- Maven

### Como rodar localmente

1. Clone o repositório:

```bash
git clone https://github.com/PhellipeLisboa/workout-planner.git
```

2. Abra o projeto em sua IDE.

3. Execute a aplicação e teste endpoints via Postman ou similar.

### Versionamento

- `main` → Branch estável

- `feature/*` → Novas funcionalidades

- `fix/*` → Correções