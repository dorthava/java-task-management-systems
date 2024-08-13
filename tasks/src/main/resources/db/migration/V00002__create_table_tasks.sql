CREATE TABLE tasks
(
    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title       VARCHAR(50)  NOT NULL,
    description VARCHAR(255) NOT NULL,
    status      VARCHAR(25)  NOT NULL,
    priority    VARCHAR(25)  NOT NULL,
    author_id   INT          NOT NULL,
    assignee_id INT          NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id),
    FOREIGN KEY (assignee_id) REFERENCES users (id)
)