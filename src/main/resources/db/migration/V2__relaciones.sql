-- Añade la restricción de clave foránea para relacionar la tabla 'events' con 'venues'.
-- Esto asegura la integridad referencial: un evento no puede existir sin un venue válido.
ALTER TABLE events
ADD CONSTRAINT fk_events_venues
FOREIGN KEY (venue_id) REFERENCES venues(id);

-- Crea un índice en la columna 'venue_id' de la tabla 'events'.
-- Los índices mejoran significativamente el rendimiento de las consultas que filtran o unen por esta columna.
CREATE INDEX idx_events_venue_id ON events(venue_id);
