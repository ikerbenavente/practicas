-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS akihabara_db;
USE akihabara_db;

-- Crear la tabla producto
CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    precio DECIMAL(10, 2),
    stock INT
);

-- Crear el usuario y establecer la contraseña
CREATE USER IF NOT EXISTS 'userAkihabara'@'localhost' IDENTIFIED BY 'Akihabara123.';

-- Conceder permisos CRUD sobre la base de datos akihabara_db
GRANT SELECT, INSERT, UPDATE, DELETE ON akihabara_db.* TO 'userAkihabara'@'localhost';

-- Aplicar cambios de permisos
FLUSH PRIVILEGES;

-- Insertar productos de prueba
INSERT INTO producto (nombre, categoria, precio, stock) VALUES 
('Naruto Shippuden Figura', 'Figura', 34.99, 15),
('One Piece Llavero Zoro', 'Llavero', 5.50, 50),
('Death Note Manga Vol. 1', 'Manga', 8.95, 25),
('Póster Dragon Ball Z', 'Póster', 12.00, 30),
('Camiseta Totoro Talla M', 'Ropa', 19.99, 20),
('Figura Evangelion Unit 01', 'Figura', 45.00, 10),
('Llavero Pikachu', 'Llavero', 4.99, 40),
('Manga Attack on Titan Vol. 5', 'Manga', 9.50, 18),
('Póster Demon Slayer', 'Póster', 11.25, 12),
('Sudadera My Hero Academia L', 'Ropa', 29.90, 8);
