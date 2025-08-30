
CREATE DATABASE IF NOT EXISTS attendance_db


USE attendance_db;


CREATE TABLE IF NOT EXISTS attendance (
    id BIGINT NOT NULL AUTO_INCREMENT,
    employee_name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    present BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);


INSERT INTO attendance (employee_name, date, present) VALUES
('Ahmed Ali', '2025-08-21', TRUE),
('Mariam Hassan', '2025-08-22', FALSE);
