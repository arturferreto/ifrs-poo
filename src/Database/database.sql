CREATE TABLE cu_addresses(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    number       VARCHAR(10)  NOT NULL,
    street       VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    complement   VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL,
    state        VARCHAR(255) NOT NULL,
    country      VARCHAR(255) NOT NULL,
    postal_code  VARCHAR(255) NOT NULL
);

CREATE TABLE cu_people(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    email          VARCHAR(255) NOT NULL,
    phone          VARCHAR(20)  NOT NULL,
    is_driver      BOOLEAN      NOT NULL,
    fav_address_id INT,

    FOREIGN KEY(fav_address_id) REFERENCES cu_addresses(id)
);

CREATE TABLE cu_vehicles(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT          NOT NULL,
    plate     VARCHAR(10)  NOT NULL,
    color     VARCHAR(255) NOT NULL,
    brand     VARCHAR(255) NOT NULL,
    model     VARCHAR(255) NOT NULL,
    seats     INTEGER      NOT NULL,

    FOREIGN KEY(person_id) REFERENCES cu_people(id)
);

CREATE TABLE cu_person_address(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    person_id  INT NOT NULL,
    address_id INT NOT NULL,

    FOREIGN KEY(person_id)  REFERENCES cu_people(id),
    FOREIGN KEY(address_id) REFERENCES cu_addresses(id)
);

CREATE TABLE cu_rides(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    vehicle_id  INT          NOT NULL,
    person_id   INT          NOT NULL,
    address_id  INT          NOT NULL,
    ride_type   SMALLINT     NOT NULL,

    FOREIGN KEY(person_id)  REFERENCES cu_people(id),
    FOREIGN KEY(address_id) REFERENCES cu_addresses(id),
    FOREIGN KEY(vehicle_id) REFERENCES cu_vehicles(id)
);

CREATE TABLE cu_passengers(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    ride_id    INT  NOT NULL,
    person_id  INT  NOT NULL,
    address_id INT  NOT NULL,
    date       DATE NOT NULL,

    FOREIGN KEY(ride_id)    REFERENCES cu_rides(id),
    FOREIGN KEY(person_id)  REFERENCES cu_people(id),
    FOREIGN KEY(address_id) REFERENCES cu_addresses(id)
);

ALTER TABLE cu_people
    ADD COLUMN fav_vehicle_id INT,
    ADD FOREIGN KEY (fav_vehicle_id) REFERENCES cu_vehicles(id);