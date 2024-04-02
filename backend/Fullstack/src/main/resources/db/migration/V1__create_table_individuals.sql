CREATE TABLE individual (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    score INT NOT NULL,
    parent_id BIGINT,
    CONSTRAINT fk_individual_parent FOREIGN KEY (parent_id) REFERENCES individual(id) ON DELETE CASCADE
);