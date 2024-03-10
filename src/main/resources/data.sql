CREATE TABLE STUDENT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identification_code VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE SUBJECT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);


INSERT INTO student (identification_code, name) VALUES ('1111', 'Juan Sarmiento');
INSERT INTO student (identification_code, name) VALUES ('2222', 'Pepito Test');

-- Insertar materias
INSERT INTO subject (name) VALUES ('Matemáticas');
INSERT INTO subject (name) VALUES ('Historia');