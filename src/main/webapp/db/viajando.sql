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
  capacidad INT,
  FOREIGN KEY (empresa_id) REFERENCES empresa(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Vuelo
CREATE TABLE vuelo (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  destino_id INT,
  destino_value varchar(100),
  fecha_inicio DATE,
  fecha_fin DATE,
  precio INT,
  estrellas DOUBLE,
  hora_ida TIME,
  hora_vuelta TIME,
  id_avion INT,
  imagen VARCHAR(255),
  FOREIGN KEY (destino_id) REFERENCES destinos(id),
  FOREIGN KEY (id_avion) REFERENCES avion(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Hotel
CREATE TABLE hotel (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  destino_id INT,
  destino_value varchar(100),
  estrellas DOUBLE,
  precio INT,
  imagen VARCHAR(255),
  FOREIGN KEY (destino_id) REFERENCES destinos(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Excursion
CREATE TABLE excursion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  descripcion VARCHAR(100),
  fecha_inicio DATE,
  fecha_fin DATE,
  precio INT,
  destino_id INT,
  destino_value varchar(100),
  estrellas DOUBLE,
  imagen VARCHAR(255),
  FOREIGN KEY (destino_id) REFERENCES destinos(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Paquete
CREATE TABLE paquete (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  descripcion VARCHAR(100),
  fecha_inicio DATE,
  fecha_fin DATE,
  precio INT,
  destino_id INT,
  destino_value varchar(100),
  estrellas DOUBLE,
  hotel_id INT,
  vuelo_id INT,
  excursion_id INT,
  personas INT,
  imagen VARCHAR(255),
  FOREIGN KEY (destino_id) REFERENCES destinos(id),
  FOREIGN KEY (hotel_id) REFERENCES hotel(id),
  FOREIGN KEY (vuelo_id) REFERENCES vuelo(id),
  FOREIGN KEY (excursion_id) REFERENCES excursion(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Usuario
CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  apellido VARCHAR(100),
  usuario VARCHAR(100),
  password VARCHAR(100),
  correo VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Reservas
CREATE TABLE reservas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  usuario_id INT,
  nombre VARCHAR(100),
  apellido VARCHAR(100),
  DNI INT,
  id_vuelo INT,
  asiento INT,
  fecha_inicio DATE,
  fecha_fin DATE,
  precio INT,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  FOREIGN KEY (id_vuelo) REFERENCES vuelo(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tabla Butacas
CREATE TABLE butacas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  asiento INT,
  descripcion VARCHAR(100),
  avion_id INT,
  estado ENUM('disponible', 'ocupado'),
  FOREIGN KEY (avion_id) REFERENCES avion(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ------------------------------------------------------
-- DATOS DE PRUEBA
-- ------------------------------------------------------

INSERT INTO destinos (nombre, pais, precio) VALUES
('Madrid', 'España', 100),
('Buenos Aires', 'Argentina', 80),
('Cancún', 'México', 120),
('Bariloche', 'Argentina', 90);

INSERT INTO empresa (nombre, pais, imagen) VALUES
('Aerolíneas Argentinas', 'Argentina', 'aerolineas.png'),
('Iberia', 'España', 'iberia.png');

INSERT INTO avion (nombre, empresa_id, capacidad) VALUES
('Boeing 737', 1, 150),
('Airbus A320', 2, 180);

INSERT INTO vuelo (nombre, destino_id, fecha_inicio, fecha_fin, precio, estrellas, hora_ida, hora_vuelta, id_avion, imagen) VALUES
('Vuelo a Madrid', 1, '2025-07-01', '2025-07-15', 1200, 4, '10:30:00', '22:45:00', 2, 'madrid.png'),
('Vuelo a Buenos Aires', 2, '2025-08-10', '2025-08-20', 900, 3, '08:15:00', '19:30:00', 1, 'baires.png');

INSERT INTO hotel (nombre, destino_id, estrellas, precio, imagen) VALUES
('Hotel Madrid Center', 1, 4.2, 700, 'hotel_madrid.png'),
('Hotel Buenos Aires', 2, 3.5, 500, 'hotel_baires.png');

INSERT INTO excursion (nombre, descripcion, fecha_inicio, fecha_fin, precio, destino_id, estrellas, imagen) VALUES
('Museo del Prado', 'Visita guiada', '2025-07-03', '2025-07-03', 60, 1, 4.5, 'prado.png'),
('City Tour Buenos Aires', 'City tour', '2025-08-11', '2025-08-11', 40, 2, 4.2, 'tour_baires.png');

INSERT INTO paquete (nombre, descripcion, fecha_inicio, fecha_fin, precio, destino_id, estrellas, hotel_id, vuelo_id, excursion_id, personas, imagen) VALUES
('Escapada a Madrid', 'Vuelo + Hotel + Museo', '2025-07-01', '2025-07-15', 1960, 1, 4, 1, 1, 1, 2, 'paquete_madrid.png'),
('Buenos Aires Express', 'Vuelo + Hotel + City Tour', '2025-08-10', '2025-08-20', 1440, 2, 3, 2, 2, 2, 1, 'paquete_baires.png');

INSERT INTO usuario (nombre, apellido, usuario, password, correo) VALUES
('Miguel', 'Figueredo', 'miguel', '123456', 'miguel@gmail.com');

INSERT INTO reservas (usuario_id, nombre, apellido, DNI, id_vuelo, asiento, fecha_inicio, fecha_fin, precio) VALUES
(1, 'Miguel', 'Figueredo', 12345678, 1, 12, '2025-07-01', '2025-07-15', 1200);

INSERT INTO butacas (asiento, descripcion, avion_id, estado) VALUES
(1, 'Ventana', 1, 'disponible'),
(2, 'Pasillo', 1, 'ocupado');

