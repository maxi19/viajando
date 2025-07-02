DROP DATABASE IF EXISTS viajando;
CREATE DATABASE viajando;
USE viajando;



select * from destinos;
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
  stock int
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
  create table habitacion(
  id INT AUTO_INCREMENT PRIMARY KEY,
  hotel_id int,
  habitacion varchar(50),
  cantidad int,
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
  precio INT,
  imagen VARCHAR(255)
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
  DNI INT,
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
('Madrid', 'España', 100),
('Buenos Aires', 'Argentina', 80),
('Cancún', 'México', 120),
('Bariloche', 'Argentina', 90),
('Roma', 'Italia', 110),
('Tokio', 'Japón', 150),
('Nueva York', 'EEUU', 140),
('Londres', 'Reino Unido', 130),
('Rio de Janeiro', 'Brasil', 95),
('Santiago', 'Chile', 85);

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
('Vuelo a Madrid', 1, '2025-07-01', '2025-07-15', 1200, 4, '10:30:00', '22:45:00', 2, 'vueloimg1.png'),
('Vuelo a Buenos Aires', 2, '2025-08-10', '2025-08-20', 900, 3, '08:15:00', '19:30:00', 1, 'vueloimg2.png'),
('Vuelo a Cancún', 3, '2025-09-05', '2025-09-12', 1300, 5, '09:00:00', '18:00:00', 3, 'vueloimg3.png'),
('Vuelo a Bariloche', 4, '2025-07-20', '2025-07-28', 950, 4, '07:45:00', '20:10:00', 5, 'vueloimg4.png'),
('Vuelo a Roma', 5, '2025-10-01', '2025-10-15', 1400, 4.5, '11:00:00', '23:30:00', 8, 'vueloimg5.png'),
('Vuelo a Tokio', 6, '2025-11-05', '2025-11-20', 2000, 5, '13:00:00', '04:00:00', 10, 'vueloimg6.png'),
('Vuelo a Nueva York', 7, '2025-12-10', '2025-12-20', 1500, 4.2, '15:00:00', '02:00:00', 4, 'vueloimg7.png'),
('Vuelo a Londres', 8, '2025-08-25', '2025-09-02', 1600, 4.8, '12:00:00', '23:00:00', 6, 'vueloimg8.png'),
('Vuelo a Río', 9, '2025-09-15', '2025-09-22', 1100, 4, '06:30:00', '17:30:00', 7, 'vueloimg9.png'),
('Vuelo a Santiago', 10, '2025-10-10', '2025-10-18', 1050, 3.8, '09:30:00', '21:00:00', 9, 'vueloimg10.png');

INSERT INTO hotel (nombre, destino_id, estrellas, precio, imagen, stock) VALUES
('Hotel Madrid Center', 1, 4.2, 700, 'hotelimg1.png', 10),
('Hotel Buenos Aires', 2, 3.5, 500, 'hotelimg2.png', 15),
('Resort Cancún', 3, 5.0, 1200, 'hotelimg3.png', 8),
('Cabañas Bariloche', 4, 4.5, 650, 'hotelimg4.png', 12),
('Hotel Roma Lux', 5, 4.8, 1100, 'hotelimg5.png', 9),
('Tokio Inn', 6, 4.6, 1300, 'hotelimg6.png', 7),
('NY Grand Hotel', 7, 4.7, 1250, 'hotelimg7.png', 11),
('Londres Royal', 8, 5.0, 1400, 'hotelimg8.png', 10),
('Río Beach Hotel', 9, 4.0, 750, 'hotelimg9.png', 13),
('Santiago Suite', 10, 3.8, 600, 'hotelimg10.png', 14);

INSERT INTO excursion (nombre, descripcion, fecha_inicio, fecha_fin, precio, destino_id, estrellas, imagen) VALUES
('Museo del Prado', 'Visita guiada', '2025-07-03', '2025-07-03', 60, 1, 4.5, 'excursionimg1.png'),
('City Tour Buenos Aires', 'City tour', '2025-08-11', '2025-08-11', 40, 2, 4.2, 'excursionimg2.png'),
('Chichén Itzá', 'Tour arqueológico', '2025-09-06', '2025-09-06', 100, 3, 5.0, 'excursionimg3.png'),
('Cerro Catedral', 'Excursión de montaña', '2025-07-22', '2025-07-22', 70, 4, 4.6, 'excursionimg4.png'),
('Coliseo Romano', 'Visita histórica', '2025-10-03', '2025-10-03', 80, 5, 4.7, 'excursionimg5.png'),
('Templo Senso-ji', 'Tour cultural', '2025-11-10', '2025-11-10', 90, 6, 4.8, 'excursionimg6.png'),
('Central Park', 'Caminata guiada', '2025-12-12', '2025-12-12', 50, 7, 4.3, 'excursionimg7.png'),
('Big Ben y Westminster', 'Tour guiado', '2025-08-27', '2025-08-27', 85, 8, 4.9, 'excursionimg8.png'),
('Cristo Redentor', 'Excursión panorámica', '2025-09-17', '2025-09-17', 65, 9, 4.4, 'excursionimg9.png'),
('Cerro San Cristóbal', 'Vista de Santiago', '2025-10-12', '2025-10-12', 55, 10, 4.1, 'excursionimg10.png');

INSERT INTO paquete (nombre, descripcion, hotel_id, vuelo_id, excursion_id, estrellas, personas, precio, imagen) VALUES
('Paquete Madrid Cultural', 'Incluye vuelo, hotel céntrico y tour al Prado.', 1, 1, 1, 4.5, 2, 300000, 'paqueteimg1.png'),
('Buenos Aires Urbano', 'Hotel, city tour y vuelo incluido.', 2, 2, 2, 4.0, 2, 250000, 'paqueteimg2.png'),
('Cancún Relax', 'Resort y tour a Chichén Itzá.', 3, 3, 3, 5.0, 2, 400000, 'paqueteimg3.png'),
('Bariloche Aventura', 'Cabañas y excursión al cerro.', 4, 4, 4, 4.7, 2, 280000, 'paqueteimg4.png'),
('Roma Clásica', 'Hotel de lujo y Coliseo.', 5, 5, 5, 4.8, 2, 350000, 'paqueteimg5.png'),
('Tokio Tradicional', 'Templo y hospedaje en el centro.', 6, 6, 6, 4.9, 2, 450000, 'paqueteimg6.png'),
('Nueva York Exprés', 'Tour y alojamiento.', 7, 7, 7, 4.5, 2, 380000, 'paqueteimg7.png'),
('Londres Completo', 'Big Ben y Royal Hotel.', 8, 8, 8, 5.0, 2, 420000, 'paqueteimg8.png'),
('Río Turístico', 'Playa, Cristo y hotel.', 9, 9, 9, 4.6, 2, 300000, 'paqueteimg9.png'),
('Santiago City', 'Cerro San Cristóbal y hospedaje.', 10, 10, 10, 4.3, 2, 270000, 'paqueteimg10.png');



INSERT INTO usuario (nombre, apellido, usuario, password, correo) VALUES
('Miguel', 'Figueredo', 'miguel', '123456', 'miguel@gmail.com');



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
