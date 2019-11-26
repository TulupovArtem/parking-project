DROP TABLE IF EXISTS car_history;
DROP TABLE IF EXISTS parking;
DROP TABLE IF EXISTS tickets;

CREATE TABLE tickets
(
    ticket_id SERIAL,
    ticket_number VARCHAR(100) NOT NULL,
    car_model VARCHAR(100) NOT NULL,
    car_number VARCHAR(100) NOT NULL,
    number_place INTEGER NOT NULL,
    time TIMESTAMP NOT NULL,
    on_parking VARCHAR(5) NOT NULL,
    PRIMARY KEY (ticket_id)
);

CREATE TABLE parking
(
    number_place SERIAL,
    ticket_number VARCHAR(100),
    car_number VARCHAR(100),
    free VARCHAR(5),
    PRIMARY KEY (number_place)
);

CREATE TABLE car_history
(
    car_id SERIAL,
    car_model VARCHAR(100) NOT NULL,
    car_number VARCHAR(100) NOT NULL,
    PRIMARY KEY (car_id)
);