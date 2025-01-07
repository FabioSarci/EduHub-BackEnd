# EduHub - Piattaforma di E-Learning

EduHub è una piattaforma di e-learning moderna e intuitiva che permette a studenti e docenti di gestire corsi, materiali didattici e quiz in modo semplice ed efficace. Sviluppata con React, TypeScript e Vite, offre un'esperienza utente fluida e reattiva.

## Caratteristiche Principali

- 👥 Gestione utenti con ruoli (studenti e docenti)
- 📚 Gestione corsi e materiali didattici
- ✍️ Sistema di quiz e valutazioni
- 🎨 Interfaccia moderna con Tailwind CSS
- 🔒 Autenticazione sicura con JWT

## Prerequisiti

- Java 21 (o superiore)
- Maven
- Git

## Installazione

1. Clona il repository:
```bash
git clone https://github.com/FabioSarci/EduHub-BackEnd
cd EduHub-BackEnd
```

2. Installa le dipendenze:
```bash
tasto destro du pom.xml
# maven
reload project

3. Crea un file `.env` nella root del progetto:
```env
URL="jdbc:postgresql://localhost:5432/(nome-database)"
USER_DB="postgres"
PWD_DB="tuaPassword"
SECRET_KEY="tuaChiavesegreta"
```

4. Crea il DB
````DB 
CREATE SCHEMA nome-database;

-- Creazione della tabella USERTYPE
CREATE TABLE USERTYPE (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Creazione della tabella USER
CREATE TABLE USERS (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    birthDate DATE NOT NULL,
    idType INT REFERENCES USERTYPE(id) ON DELETE SET NULL
);

-- Creazione della tabella CREDENTIAL
CREATE TABLE CREDENTIAL (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    userId INT REFERENCES USERS(id) ON DELETE CASCADE
);

-- Creazione della tabella NOTIFICATION
CREATE TABLE NOTIFICATION (
    id SERIAL PRIMARY KEY,
    object VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    userId INT REFERENCES USERS(id) ON DELETE CASCADE
);

-- Creazione della tabella CLASS
CREATE TABLE COURSE (
    id SERIAL PRIMARY KEY,
    courseName VARCHAR(255) NOT NULL,
    section VARCHAR(255),
    subject VARCHAR(255)
);

-- Creazione della tabella USER_CLASS
CREATE TABLE USER_COURSE (
    id SERIAL PRIMARY KEY,
    classId INT REFERENCES COURSE(id) ON DELETE CASCADE,
    userId INT REFERENCES USERS(id) ON DELETE CASCADE
);

-- Creazione della tabella QUIZ
CREATE TABLE QUIZ (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    publishedAt TIMESTAMP NOT NULL,
    courseId INT REFERENCES COURSE(id) ON DELETE CASCADE
);

-- Creazione della tabella QUESTION
CREATE TABLE QUESTION (
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    quizId INT REFERENCES QUIZ(id) ON DELETE CASCADE
);

-- Creazione della tabella ANSWER
CREATE TABLE ANSWER (
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    isCorrect BOOLEAN NOT NULL,
    idQuestion INT REFERENCES QUESTION(id) ON DELETE CASCADE
);

-- Creazione della tabella USER_QUIZ
CREATE TABLE USER_QUIZ (
    id SERIAL PRIMARY KEY,
    quizId INT REFERENCES QUIZ(id) ON DELETE CASCADE,
    userId INT REFERENCES USERS(id) ON DELETE CASCADE,
    completedAt TIMESTAMP
);

-- Creazione della tabella USER_ANSWER
CREATE TABLE USER_ANSWER (
    id SERIAL PRIMARY KEY,
    questionId INT REFERENCES QUESTION(id) ON DELETE CASCADE,
    userQuizId INT REFERENCES USER_QUIZ(id) ON DELETE CASCADE,
    answerId INT REFERENCES ANSWER(id) ON DELETE CASCADE
);

-- Creazione della tabella LESSON
CREATE TABLE LESSON (
    id SERIAL PRIMARY KEY,
    topic VARCHAR(255) NOT NULL,
    description TEXT,
    classId INT REFERENCES COURSE(id) ON DELETE CASCADE,
    date DATE NOT NULL
);

-- Creazione della tabella PRESENCE
CREATE TABLE PRESENCE (
    id SERIAL PRIMARY KEY,
    lessonId INT REFERENCES LESSON(id) ON DELETE CASCADE,
    userId INT REFERENCES USERS(id) ON DELETE CASCADE,
    date DATE NOT NULL,
    isPresent BOOLEAN NOT NULL
);
````

## Tecnologie Utilizzate

- Java 23
- Maven
- Javalin
- Lombock
- Postgre
- JWT


## Struttura del Progetto

```
con.infobasic.sviluppo_software/
├── auth/     # Autenticazione
├── Controller/      # Parte della struttura mvc che gestisce le rotte
├── dao/         # Parte della struttura mvc che gestisce le operazioni col db
├── model/    # definisce i modelli delle tabelle del bd
├── service/          # Parte della struttura mvc che fa da tramite tra dao e controller
└── utility/        # Gestisce la connessione col database
├── main            #main del progetto

## Contribuire

Le pull request sono benvenute. Per modifiche importanti, apri prima un issue per discutere cosa vorresti cambiare.

## Licenza

[MIT](https://choosealicense.com/licenses/mit/)