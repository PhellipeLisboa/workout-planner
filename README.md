## 💪 Workout Planner

Workout Planner é uma API para gerenciar usuários, treinos e exercícios. O projeto está em desenvolvimento e será expandido futuramente com funcionalidades como controle de permissões e autenticação.

### ✨ Tecnologias

- Java 21
- Spring Boot
- JPA/Hibernate
- H2 Database (Utilizado durante a fase inicial do desenvolvimento, mas posteriomente será utilizado PostgreSQL)
- Maven

### 📃 Funcionalidades 

- CRUD de usuários e exercícios com DTOs, validação, mappers e endpoints REST
- Filtros de exercícios por nome, grupo muscular e equipamento
- CRUD de treinos a partir dos exercícios cadastrados

### 💻 Como rodar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/PhellipeLisboa/workout-planner.git
```

2. Abra o projeto em sua IDE.

3. Execute a aplicação e teste endpoints via Postman ou similar.

### 🚀 Melhorias futuras

- Roles e permissões
- Autenticação
- Exceções personalizadas
- Testes unitários
- Docker
