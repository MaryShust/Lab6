CREATE TYPE VehicleType AS ENUM (
    'CAR', 'DRONE', 'BOAT', 'BICYCLE'
);


CREATE TYPE FuelType AS ENUM (
    'ELECTRICITY', 'ALCOHOL', 'MANPOWER', 'PLASMA'
);


CREATE TABLE IF NOT EXISTS Users
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(40) UNIQUE      NOT NULL,
    password_digest VARCHAR(96)             NOT NULL,
    creation_date   TIMESTAMP DEFAULT NOW() NOT NULL
);


CREATE TABLE IF NOT EXISTS Vector (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL CONSTRAINT notEmptyName CHECK(length(name) > 0),
    x FLOAT NOT NULL CONSTRAINT positiveX CHECK(x > -845),
    y INTEGER NOT NULL,
    creationDate DATE DEFAULT NOW() NOT NULL,
    enginePower INTEGER NOT NULL CONSTRAINT positiveEnginePower CHECK (enginePower > 0),
    distanceTravelled INTEGER NOT NULL CONSTRAINT positiveDistanceTravelled CHECK(distanceTravelled > 0),
    type VehicleType NOT NULL,
    fuelType FuelType NOT NULL,
    creator_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);


