-- En este archivo puedes añadir ajustes adicionales, como inserciones de datos iniciales o modificaciones de columnas.
-- Se corrige el nombre de la tabla a 'venues' para que coincida con V1__init.sql.
INSERT INTO venues (name, city, address, capacity) VALUES
('Estadio Atanasio Girardot', 'Medellín', 'Carrera 74 # 48-210', 45000),
('Movistar Arena', 'Bogotá', 'Diagonal 61C # 26-36', 14000);
