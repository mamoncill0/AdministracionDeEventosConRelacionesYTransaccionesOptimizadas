-- Crea la tabla 'venues' de acuerdo a la entidad VenueEntity.
CREATE TABLE venues (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    capacity INT NOT NULL
);

-- Crea la tabla 'events' de acuerdo a la entidad EventEntity.
CREATE TABLE events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name_event VARCHAR(100) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    description VARCHAR(500),
    capacity INT NOT NULL,
    -- La columna para la clave foránea se crea aquí, pero la restricción se añade en V2.
    venue_id INT NOT NULL
);
