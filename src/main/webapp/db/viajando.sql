DROP DATABASE IF EXISTS viajando;
CREATE DATABASE viajando;
USE viajando;

-- Tabla Destinos
CREATE TABLE destinos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  pais VARCHAR(50),
  precio INT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Empresa
CREATE TABLE empresa (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(35),
  pais VARCHAR(35),
  imagen VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Avion
CREATE TABLE avion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(35),
  empresa_id INT,
  capacidad INT
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Vuelo
CREATE TABLE vuelo (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  destino_id INT,
  fecha_inicio DATE,
  fecha_fin DATE,
  precio INT,
  estrellas DOUBLE,
  hora_ida TIME,
  hora_vuelta TIME,
  id_avion INT,
  imagen VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Tabla Hotel
CREATE TABLE hotel (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  destino_id INT,
  estrellas DOUBLE,
  precio INT,
  imagen VARCHAR(255),
  stock int -- cuantas habitaciones se van a crear
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
 -- se crean las habitaciones a partir del stock
  create table habitacion(
  id INT AUTO_INCREMENT PRIMARY KEY,
  hotel_id int, -- el hotel de la habitacion
  habitacion varchar(50), -- el nombre de la habitacion (101B, 103A, etc)
  cantidad int, -- para cuantas personas es la habitacion
  estado enum ('disponible', 'ocupado') default ('disponible')
);
 

CREATE TABLE excursion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  descripcion VARCHAR(100),
  fecha_inicio DATE,
  fecha_fin DATE,
  precio INT,
  destino_id INT,
  estrellas DOUBLE,
  imagen VARCHAR(255)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE paquete (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  descripcion VARCHAR(100),
  hotel_id INT default null,
  vuelo_id INT default null,
  excursion_id INT default null,
  estrellas DOUBLE,
  personas INT,
  precio INT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;





CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  apellido VARCHAR(100),
  usuario VARCHAR(100),
  password VARCHAR(100),
  correo VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE reservas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  identificador VARCHAR(50) NOT NULL, -- unica
  nombre VARCHAR(100), -- datos de la persona ingresada
  apellido VARCHAR(100),
  sexo varchar(100),
  DNI varchar(8),
  tipo_servicio VARCHAR(50) NOT NULL, -- vuelo / hotel / excursion
  id_vuelo int default null,
  id_hotel int default null,
  id_excursion int default null,
  id_paquete int  default null,
  butaca int default null,
  precio INT
 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE butaca_vuelo (
  id INT AUTO_INCREMENT PRIMARY KEY,
  asiento INT,
  vuelo_id INT,
  estado ENUM('disponible', 'ocupado') default 'disponible'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ------------------------------------------------------
-- DATOS DE PRUEBA
-- ------------------------------------------------------

INSERT INTO destinos (nombre, pais, precio) VALUES
('París', 'Francia', 135),
('Berlín', 'Alemania', 125),
('Lisboa', 'Portugal', 110),
('Ámsterdam', 'Países Bajos', 140),
('Seúl', 'Corea del Sur', 145),
('Sídney', 'Australia', 160),
('Toronto', 'Canadá', 130),
('Estocolmo', 'Suecia', 120),
('Atenas', 'Grecia', 115);


INSERT INTO empresa (nombre, pais, imagen) VALUES
('Aerolíneas Argentinas', 'Argentina', 'empresaimg1.png'),
('Iberia', 'España', 'empresaimg2.png'),
('LATAM', 'Chile', 'empresaimg3.png'),
('American Airlines', 'EEUU', 'empresaimg4.png'),
('Air France', 'Francia', 'empresaimg5.png'),
('Lufthansa', 'Alemania', 'empresaimg6.png'),
('British Airways', 'Reino Unido', 'empresaimg7.png'),
('Alitalia', 'Italia', 'empresaimg8.png'),
('Emirates', 'Emiratos Árabes', 'empresaimg9.png'),
('Qatar Airways', 'Qatar', 'empresaimg10.png');

INSERT INTO avion (nombre, empresa_id, capacidad) VALUES
('Boeing 737', 1, 150),
('Airbus A320', 2, 180),
('Boeing 777', 3, 300),
('Airbus A350', 4, 280),
('Embraer 190', 1, 100),
('Boeing 787', 5, 250),
('Airbus A330', 6, 240),
('Boeing 767', 7, 200),
('Concorde', 8, 100),
('Airbus A380', 9, 500);



INSERT INTO vuelo (nombre, destino_id, fecha_inicio, fecha_fin, precio, estrellas, hora_ida, hora_vuelta, id_avion, imagen) VALUES
('Vuelo a París', 1, '2025-09-01', '2025-09-10', 1450, 4.6, '11:00:00', '23:00:00', 1, 'vueloimg1.png'),
('Vuelo a Berlín', 2, '2025-09-15', '2025-09-22', 1380, 4.3, '10:30:00', '22:30:00', 2, 'vueloimg2.png'),
('Vuelo a Lisboa', 3, '2025-10-05', '2025-10-12', 1250, 4.0, '09:00:00', '21:00:00', 3, 'vueloimg3.png'),
('Vuelo a Ámsterdam', 4, '2025-10-20', '2025-10-28', 1500, 4.8, '12:00:00', '23:30:00', 4, 'vueloimg4.png'),
('Vuelo a Seúl', 5, '2025-11-01', '2025-11-15', 1750, 4.9, '13:00:00', '03:00:00', 5, 'vueloimg5.png'),
('Vuelo a Sídney', 6, '2025-11-20', '2025-12-05', 1900, 4.7, '14:00:00', '04:00:00', 6, 'vueloimg6.png'),
('Vuelo a Toronto', 7, '2025-12-01', '2025-12-10', 1600, 4.2, '15:30:00', '02:30:00', 7, 'vueloimg7.png'),
('Vuelo a Estocolmo', 8, '2025-12-15', '2025-12-22', 1550, 4.4, '10:00:00', '20:00:00', 8, 'vueloimg8.png'),
('Vuelo a Atenas', 9, '2026-01-05', '2026-01-12', 1400, 4.1, '08:45:00', '19:45:00', 9, 'vueloimg9.png');



INSERT INTO hotel (nombre, destino_id, estrellas, precio, imagen, stock) VALUES
('Hotel París Louvre', 1, 4.7, 1350, 'hotelimg1.png', 10),
('Berlin Grand Hotel', 2, 4.5, 1250, 'hotelimg2.png', 12),
('Lisboa Central Inn', 3, 4.0, 1100, 'hotelimg3.png', 8),
('Canal View Ámsterdam', 4, 4.8, 1450, 'hotelimg4.png', 9),
('Seúl Palace', 5, 5.0, 1500, 'hotelimg5.png', 6),
('Sídney Beach Resort', 6, 4.6, 1550, 'hotelimg6.png', 7),
('Toronto Maple Hotel', 7, 4.3, 1200, 'hotelimg7.png', 10),
('Estocolmo Ice Hotel', 8, 4.4, 1300, 'hotelimg8.png', 11),
('Atenas Acropolis View', 9, 4.1, 1150, 'hotelimg9.png', 8);


INSERT INTO excursion (nombre, descripcion, fecha_inicio, fecha_fin, precio, destino_id, estrellas, imagen) VALUES
('Torre Eiffel y Louvre', 'Tour histórico y artístico', '2025-09-03', '2025-09-03', 90, 1, 4.7, 'excursionimg1.png'),
('Muro de Berlín', 'Recorrido guiado', '2025-09-17', '2025-09-17', 85, 2, 4.4, 'excursionimg2.png'),
('Barrio Alto y Belém', 'Tour por Lisboa clásica', '2025-10-06', '2025-10-06', 80, 3, 4.3, 'excursionimg3.png'),
('Museo Van Gogh', 'Tour artístico', '2025-10-22', '2025-10-22', 95, 4, 4.6, 'excursionimg4.png'),
('Palacio Gyeongbokgung', 'Cultura coreana', '2025-11-05', '2025-11-05', 100, 5, 4.9, 'excursionimg5.png'),
('Opera House', 'Tour icónico', '2025-11-23', '2025-11-23', 105, 6, 4.8, 'excursionimg6.png'),
('Niágara Falls', 'Excursión natural', '2025-12-03', '2025-12-03', 88, 7, 4.5, 'excursionimg7.png'),
('Islas del Archipiélago', 'Paseo en barco', '2025-12-17', '2025-12-17', 92, 8, 4.4, 'excursionimg8.png'),
('Templo de Zeus', 'Tour histórico', '2026-01-07', '2026-01-07', 85, 9, 4.2, 'excursionimg9.png');



INSERT INTO paquete (nombre, descripcion, hotel_id, vuelo_id, excursion_id, estrellas, personas, precio) VALUES
('Paquete París Romántico', 'Hotel, vuelo y Louvre.', 1, 1, 1, 4.7, 2, 330000),
('Descubrí Berlín', 'Todo incluido con recorrido histórico.', 2, 2, 2, 4.4, 2, 310000),
('Lisboa Encantadora', 'Hotel céntrico y Belém tour.', 3, 3, 3, 4.3, 2, 290000),
('Arte en Ámsterdam', 'Van Gogh y paseo por canales.', 4, 4, 4, 4.8, 2, 360000),
('Tradiciones de Seúl', 'Palacio y hospedaje top.', 5, 5, 5, 5.0, 2, 380000),
('Australia Única', 'Opera House y playa.', 6, 6, 6, 4.8, 2, 400000),
('Niágara Experience', 'Tour + hotel + vuelo.', 7, 7, 7, 4.5, 2, 340000),
('Estocolmo Increíble', 'Barco por islas y hospedaje.', 8, 8, 8, 4.4, 2, 350000),
('Atenas Histórica', 'Templo de Zeus y más.', 9, 9, 9, 4.3, 2, 320000);



INSERT INTO usuario (nombre, apellido, usuario, password, correo) VALUES
('Miguel', 'Figueredo', 'miguel', '123456', 'miguel@gmail.com');


INSERT INTO reservas (identificador, nombre, apellido, sexo, DNI, tipo_servicio, id_vuelo, butaca, precio)
VALUES 
('RESV001', 'Juan', 'Pérez', 'Masculino', '12345678', 'vuelo', 1, 12, 45000),
('RESV002', 'Lucía', 'Gómez', 'Femenino', '87654321', 'vuelo', 2, 7, 52000),
('RESV003', 'Carlos', 'Díaz', 'Masculino', '23456789', 'vuelo', 3, 19, 48000);

INSERT INTO reservas (identificador, nombre, apellido, sexo, DNI, tipo_servicio, id_hotel, precio)
VALUES 
('RESH001', 'Ana', 'Martínez', 'Femenino', '34567890', 'hotel', 1, 60000),
('RESH002', 'Pedro', 'Fernández', 'Masculino', '45678901', 'hotel', 2, 75000),
('RESH003', 'Sofía', 'López', 'Femenino', '56789012', 'hotel', 3, 68000);

INSERT INTO reservas (identificador, nombre, apellido, sexo, DNI, tipo_servicio, id_excursion, precio)
VALUES 
('RESE001', 'Tomás', 'Ibarra', 'Masculino', '67890123', 'excursion', 1, 15000),
('RESE002', 'Camila', 'Ruiz', 'Femenino', '78901234', 'excursion', 2, 17000);


INSERT INTO reservas (identificador, nombre, apellido, sexo, DNI, tipo_servicio, id_paquete, precio)
VALUES 
('RESP001', 'María', 'Suárez', 'Femenino', '89012345', 'paquete', 1, 120000),
('RESP002', 'Diego', 'Castro', 'Masculino', '90123456', 'paquete', 2, 132000);




INSERT INTO butaca_vuelo (vuelo_id, asiento, estado) VALUES
(1, 1, 'disponible'),
(1, 2, 'ocupado'),
(1, 3, 'disponible'),
(1, 4, 'ocupado'),
(1, 5, 'ocupado'),
(1, 6, 'disponible'),
(1, 7, 'disponible'),
(1, 8, 'ocupado'),
(1, 9, 'disponible'),
(1, 10, 'disponible'),
(1, 11, 'ocupado'),
(1, 12, 'disponible'),
(1, 13, 'ocupado'),
(1, 14, 'disponible'),
(1, 15, 'ocupado'),
(1, 16, 'disponible'),
(1, 17, 'ocupado'),
(1, 18, 'ocupado'),
(1, 19, 'disponible'),
(1, 20, 'disponible'),
(1, 21, 'ocupado'),
(1, 22, 'disponible'),
(1, 23, 'ocupado'),
(1, 24, 'disponible'),
(1, 25, 'ocupado'),
(1, 26, 'disponible'),
(1, 27, 'ocupado'),
(1, 28, 'disponible'),
(1, 29, 'disponible'),
(1, 30, 'ocupado'),
(1, 31, 'disponible'),
(1, 32, 'ocupado'),
(1, 33, 'disponible'),
(1, 34, 'disponible'),
(1, 35, 'ocupado');


INSERT INTO butaca_vuelo (vuelo_id, asiento, estado) VALUES
(2, 1, 'disponible'),
(2, 2, 'disponible'),
(2, 3, 'ocupado'),
(2, 4, 'ocupado'),
(2, 5, 'disponible'),
(2, 6, 'disponible'),
(2, 7, 'ocupado'),
(2, 8, 'ocupado'),
(2, 9, 'disponible'),
(2, 10, 'ocupado'),
(2, 11, 'disponible'),
(2, 12, 'ocupado'),
(2, 13, 'disponible'),
(2, 14, 'ocupado'),
(2, 15, 'disponible'),
(2, 16, 'disponible'),
(2, 17, 'ocupado'),
(2, 18, 'disponible'),
(2, 19, 'ocupado'),
(2, 20, 'disponible'),
(2, 21, 'ocupado'),
(2, 22, 'ocupado'),
(2, 23, 'disponible'),
(2, 24, 'disponible'),
(2, 25, 'ocupado'),
(2, 26, 'disponible'),
(2, 27, 'disponible'),
(2, 28, 'ocupado'),
(2, 29, 'ocupado'),
(2, 30, 'disponible'),
(2, 31, 'ocupado'),
(2, 32, 'disponible'),
(2, 33, 'ocupado'),
(2, 34, 'disponible'),
(2, 35, 'ocupado'),
(2, 36, 'disponible'),
(2, 37, 'disponible'),
(2, 38, 'ocupado'),
(2, 39, 'ocupado'),
(2, 40, 'disponible'),
(2, 41, 'ocupado'),
(2, 42, 'disponible'),
(2, 43, 'ocupado'),
(2, 44, 'disponible'),
(2, 45, 'ocupado');

-- Butacas para vuelos del 3 al 9 (mezcladas entre ocupadas y disponibles)

INSERT INTO butaca_vuelo (vuelo_id, asiento, estado) VALUES
-- Vuelo 3
(3, 1, 'disponible'), (3, 2, 'ocupado'), (3, 3, 'disponible'), (3, 4, 'ocupado'), (3, 5, 'disponible'),
(3, 6, 'ocupado'), (3, 7, 'ocupado'), (3, 8, 'disponible'), (3, 9, 'disponible'), (3, 10, 'ocupado'),
(3, 11, 'disponible'), (3, 12, 'ocupado'), (3, 13, 'disponible'), (3, 14, 'ocupado'), (3, 15, 'disponible'),
(3, 16, 'ocupado'), (3, 17, 'disponible'), (3, 18, 'ocupado'), (3, 19, 'disponible'), (3, 20, 'disponible'),
(3, 21, 'ocupado'), (3, 22, 'disponible'), (3, 23, 'ocupado'), (3, 24, 'disponible'), (3, 25, 'ocupado'),
(3, 26, 'disponible'), (3, 27, 'ocupado'), (3, 28, 'disponible'), (3, 29, 'ocupado'), (3, 30, 'disponible'),
(3, 31, 'disponible'), (3, 32, 'ocupado'), (3, 33, 'disponible'), (3, 34, 'ocupado'), (3, 35, 'disponible'),

-- Vuelo 4
(4, 1, 'disponible'), (4, 2, 'ocupado'), (4, 3, 'ocupado'), (4, 4, 'disponible'), (4, 5, 'ocupado'),
(4, 6, 'disponible'), (4, 7, 'disponible'), (4, 8, 'ocupado'), (4, 9, 'disponible'), (4, 10, 'ocupado'),
(4, 11, 'ocupado'), (4, 12, 'disponible'), (4, 13, 'ocupado'), (4, 14, 'disponible'), (4, 15, 'ocupado'),
(4, 16, 'disponible'), (4, 17, 'ocupado'), (4, 18, 'ocupado'), (4, 19, 'disponible'), (4, 20, 'ocupado'),
(4, 21, 'disponible'), (4, 22, 'disponible'), (4, 23, 'ocupado'), (4, 24, 'disponible'), (4, 25, 'ocupado'),
(4, 26, 'disponible'), (4, 27, 'ocupado'), (4, 28, 'disponible'), (4, 29, 'ocupado'), (4, 30, 'disponible'),
(4, 31, 'ocupado'), (4, 32, 'disponible'), (4, 33, 'disponible'), (4, 34, 'ocupado'), (4, 35, 'disponible'),

-- Vuelo 5
(5, 1, 'ocupado'), (5, 2, 'disponible'), (5, 3, 'disponible'), (5, 4, 'ocupado'), (5, 5, 'disponible'),
(5, 6, 'ocupado'), (5, 7, 'disponible'), (5, 8, 'disponible'), (5, 9, 'ocupado'), (5, 10, 'disponible'),
(5, 11, 'ocupado'), (5, 12, 'disponible'), (5, 13, 'ocupado'), (5, 14, 'disponible'), (5, 15, 'ocupado'),
(5, 16, 'disponible'), (5, 17, 'disponible'), (5, 18, 'ocupado'), (5, 19, 'disponible'), (5, 20, 'ocupado'),
(5, 21, 'ocupado'), (5, 22, 'disponible'), (5, 23, 'disponible'), (5, 24, 'ocupado'), (5, 25, 'disponible'),
(5, 26, 'ocupado'), (5, 27, 'disponible'), (5, 28, 'ocupado'), (5, 29, 'disponible'), (5, 30, 'ocupado'),
(5, 31, 'disponible'), (5, 32, 'disponible'), (5, 33, 'ocupado'), (5, 34, 'disponible'), (5, 35, 'ocupado'),

-- Vuelo 6
(6, 1, 'disponible'), (6, 2, 'ocupado'), (6, 3, 'disponible'), (6, 4, 'ocupado'), (6, 5, 'ocupado'),
(6, 6, 'disponible'), (6, 7, 'disponible'), (6, 8, 'ocupado'), (6, 9, 'disponible'), (6, 10, 'ocupado'),
(6, 11, 'ocupado'), (6, 12, 'disponible'), (6, 13, 'ocupado'), (6, 14, 'disponible'), (6, 15, 'ocupado'),
(6, 16, 'disponible'), (6, 17, 'ocupado'), (6, 18, 'disponible'), (6, 19, 'disponible'), (6, 20, 'ocupado'),
(6, 21, 'disponible'), (6, 22, 'disponible'), (6, 23, 'ocupado'), (6, 24, 'disponible'), (6, 25, 'ocupado'),
(6, 26, 'disponible'), (6, 27, 'ocupado'), (6, 28, 'disponible'), (6, 29, 'ocupado'), (6, 30, 'disponible'),
(6, 31, 'ocupado'), (6, 32, 'disponible'), (6, 33, 'disponible'), (6, 34, 'ocupado'), (6, 35, 'disponible'),

-- Vuelo 7
(7, 1, 'disponible'), (7, 2, 'disponible'), (7, 3, 'ocupado'), (7, 4, 'disponible'), (7, 5, 'ocupado'),
(7, 6, 'ocupado'), (7, 7, 'disponible'), (7, 8, 'ocupado'), (7, 9, 'disponible'), (7, 10, 'disponible'),
(7, 11, 'ocupado'), (7, 12, 'disponible'), (7, 13, 'ocupado'), (7, 14, 'disponible'), (7, 15, 'ocupado'),
(7, 16, 'disponible'), (7, 17, 'ocupado'), (7, 18, 'disponible'), (7, 19, 'disponible'), (7, 20, 'ocupado'),
(7, 21, 'disponible'), (7, 22, 'ocupado'), (7, 23, 'disponible'), (7, 24, 'ocupado'), (7, 25, 'disponible'),
(7, 26, 'ocupado'), (7, 27, 'disponible'), (7, 28, 'ocupado'), (7, 29, 'disponible'), (7, 30, 'ocupado'),
(7, 31, 'disponible'), (7, 32, 'ocupado'), (7, 33, 'disponible'), (7, 34, 'ocupado'), (7, 35, 'disponible'),

-- Vuelo 8
(8, 1, 'ocupado'), (8, 2, 'disponible'), (8, 3, 'disponible'), (8, 4, 'ocupado'), (8, 5, 'disponible'),
(8, 6, 'disponible'), (8, 7, 'ocupado'), (8, 8, 'ocupado'), (8, 9, 'disponible'), (8, 10, 'ocupado'),
(8, 11, 'disponible'), (8, 12, 'disponible'), (8, 13, 'ocupado'), (8, 14, 'disponible'), (8, 15, 'ocupado'),
(8, 16, 'disponible'), (8, 17, 'ocupado'), (8, 18, 'ocupado'), (8, 19, 'disponible'), (8, 20, 'ocupado'),
(8, 21, 'disponible'), (8, 22, 'disponible'), (8, 23, 'ocupado'), (8, 24, 'disponible'), (8, 25, 'ocupado'),
(8, 26, 'disponible'), (8, 27, 'ocupado'), (8, 28, 'disponible'), (8, 29, 'ocupado'), (8, 30, 'disponible'),
(8, 31, 'disponible'), (8, 32, 'ocupado'), (8, 33, 'disponible'), (8, 34, 'ocupado'), (8, 35, 'disponible'),

-- Vuelo 9
(9, 1, 'disponible'), (9, 2, 'ocupado'), (9, 3, 'disponible'), (9, 4, 'ocupado'), (9, 5, 'disponible'),
(9, 6, 'ocupado'), (9, 7, 'ocupado'), (9, 8, 'disponible'), (9, 9, 'ocupado'), (9, 10, 'disponible'),
(9, 11, 'disponible'), (9, 12, 'ocupado'), (9, 13, 'disponible'), (9, 14, 'ocupado'), (9, 15, 'disponible'),
(9, 16, 'disponible'), (9, 17, 'ocupado'), (9, 18, 'disponible'), (9, 19, 'ocupado'), (9, 20, 'disponible'),
(9, 21, 'ocupado'), (9, 22, 'disponible'), (9, 23, 'ocupado'), (9, 24, 'disponible'), (9, 25, 'ocupado'),
(9, 26, 'disponible'), (9, 27, 'ocupado'), (9, 28, 'disponible'), (9, 29, 'ocupado'), (9, 30, 'disponible'),
(9, 31, 'disponible'), (9, 32, 'ocupado'), (9, 33, 'disponible'), (9, 34, 'ocupado'), (9, 35, 'disponible');


