-- YOU CAN AUTOMATICALLY GENERATE MOCK DATA FROM https://www.mockaroo.com/
-- other options are: https://www.onlinedatagenerator.com/

-- run this SQL STATEMENT USING THE INBUILT RUNNER IN INTELLIJ.
--FLYWAY IS ALSO CONFIGURED. IT WILL AUTOMATICALLY CREATE THE SCHEMA BELOW IF THE TABLE EXISTS

--you can use this statement to quickly delete all tables in the database
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;


create table if not exists movies
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(255),
    recommended  VARCHAR(5),
    author_id    BIGINT,
    poster_path  VARCHAR(255),
    release_date DATE,
    type         VARCHAR(20)
);

CREATE TABLE authors
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);


ALTER TABLE movies
    ADD CONSTRAINT fk_movies_author FOREIGN KEY (author_id) REFERENCES authors (id);



-- create table if not exists bookings
-- (
--     bookid    integer                     not null,
--     facid     integer                     not null,
--     memid     integer                     not null,
--     starttime timestamp without time zone not null,
--     slots     integer                     not null
-- );


-- create table if not exists facilities
-- (
--     facid              integer                not null,
--     firstname          character varying(100) not null,
--     membercost         numeric                not null,
--     guestcost          numeric                not null,
--     initialoutlay      numeric                not null,
--     monthlymaintenance numeric                not null
-- );


-- create table if not exists members
-- (
--     memid         integer                     not null,
--     surname       character varying(200)      not null,
--     firstname     character varying(200)      not null,
--     address       character varying(300)      not null,
--     zipcode       integer                     not null,
--     telephone     character varying(20)       not null,
--     recommendedby integer,
--     joindate      timestamp without time zone not null
-- );

create TYPE valid_roles AS ENUM ('role_admin', 'role_moderator', 'role_user');

create table if not exists role
(
    id   integer primary key not null,
    role valid_roles         not null
);

insert into role(id, role)
values (1, 'role_admin'),
       (2, 'role_moderator'),
       (3, 'role_user');


create table if not exists users
(
    id       serial primary key,
    uuid     character varying(300),
    username character varying(200) not null,
    email    character varying(300) not null,
    password character varying(300) not null,
    role     integer                not null,
    constraint fk_roles foreign key (role) references role (id),
    enabled  boolean                not null,
    joindate timestamp without time zone
);

create table if not exists last_seen_movies
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id        BIGINT    NOT NULL,
    movie_id       BIGINT    NOT NULL,
    last_seen_date TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (movie_id) REFERENCES movies (id)
);


--INSERT STATEMENTS

--AUTHORS
INSERT INTO authors (id, name)
VALUES (1, 'John Smith');
INSERT INTO authors (id, name)
VALUES (2, 'Jane Doe');
INSERT INTO authors (id, name)
VALUES (3, 'John Johnson');
INSERT INTO authors (id, name)
VALUES (4, 'Jane Smith');
INSERT INTO authors (id, name)
VALUES (5, 'John Doe');
INSERT INTO authors (id, name)
VALUES (6, 'Jane Johnson');
INSERT INTO authors (id, name)
VALUES (7, 'John Smith');
INSERT INTO authors (id, name)
VALUES (8, 'Jane Doe');
INSERT INTO authors (id, name)
VALUES (10, 'Jane Smith');
INSERT INTO authors (id, name)
VALUES (11, 'Bob Johnson');
INSERT INTO authors (id, name)
VALUES (12, 'Mary Brown');
INSERT INTO authors (id, name)
VALUES (13, 'David Lee');
INSERT INTO authors (id, name)
VALUES (14, 'Sarah Davis');
INSERT INTO authors (id, name)
VALUES (15, 'Tom Wilson');
INSERT INTO authors (id, name)
VALUES (16, 'Karen Chen');

--
-- -- Movie 1
INSERT INTO movies (id, title, recommended, poster_path, release_date, type, author_id)
VALUES (1, 'Black Panther: Wakanda Forever', true, '/sv1xJUazXeYqALzczSZ3O6nkH75.jpg', '2022-11-09', 'ACTION', 1);

-- Movie 2
INSERT INTO movies (id, title, recommended, poster_path, release_date, type, author_id)
VALUES (2, 'Babylon', true, '/wjOHjWCUE0YzDiEzKv8AfqHj3ir.jpg', '2022-12-22', 'DRAMA', 2);

-- Movie 3
INSERT INTO movies (id, title, recommended, poster_path, release_date, type, author_id)
VALUES (3, 'Plane', false, '/2g9ZBjUfF1X53EinykJqiBieUaO.jpg', '2023-01-13', 'DRAMA', 3);

-- Movie 4
INSERT INTO movies (id, title, recommended, poster_path, release_date, type, author_id)
VALUES (4, 'Avatar: The Way of Water', true, '/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg', '2022-12-14', 'SCI-FI', 4);

-- Movie 5
INSERT INTO movies (id, title, recommended, poster_path, release_date, type, author_id)
VALUES (5, 'Ant-Man and the Wasp: Quantumania', false, '/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg', '2023-02-10', 'COMEDY', 5);

-- Movie 6
INSERT INTO movies (id, title, recommended, poster_path, release_date, type, author_id)
VALUES (6, 'M3GAN', true, '/d9nBoowhjiiYc4FBNtQkPY7c11H.jpg', '2022-12-28', 'HORROR', 6);

-- Movie 7
INSERT INTO movies (id, title, recommended, poster_path, release_date, type, author_id)
VALUES (7, 'Your Place or Mine', false, '/ApkSeqfIPRCxOtfjXYYE6Ji7jVU.jpg', '2023-02-10', 'DRAMA', 7);

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (8, 'Whitney Houston: I Wanna Dance with Somebody', false, 8, '/oodPJ3Op1pWBhnEFyw5fampRCWf.jpg', '2022-12-20',
        'COMEDY');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (9, 'Empire of Light', false, 8, '/h84SnIQF91Gz2Fv1OpMJ3245t4i.jpg', '2022-12-09', 'DRAMA');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (10, 'Shotgun Wedding', true, 10, '/t79ozwWnwekO0ADIzsFP1E5SkvR.jpg', '2022-12-28', 'ACTION');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (11, 'Somebody I Used to Know', false, 11, '/ovHxxphDgjyEpYriDoGoIHfrdZL.jpg', '2023-02-10', 'ROMANCE');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (12, 'Titanic', true, 12, '/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg', '1997-11-18', 'DRAMA');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (13, 'Teen Wolf: The Movie', true, 13, '/wAkpPm3wcHRqZl8XjUI3Y2chYq2.jpg', '2023-01-18', 'HORROR');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (14, 'Puss in Boots: The Last Wish', true, 14, '/kuf6dutpsT0vSVehic3EZIqkOBt.jpg', '2022-12-07', 'ANIMATION');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (15, 'Vikingulven', false, 15, '/9CxWs95VQWlOAdgE0iadrz3RPwH.jpg', '2022-11-18', 'HORROR');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (16, 'Top Gun: Maverick', true, 16, '/62HCnUTziyWcpDaBO2i1DX17ljH.jpg', '2022-05-24', 'ACTION');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (17, 'Knock at the Cabin', false, 1, '/dm06L9pxDOL9jNSK4Cb6y139rrG.jpg', '2023-02-01', 'DRAMA');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (18, 'Everything Everywhere All at Once', true, 2, '/w3LxiVYdWWRvEVdn5RYq6jIqkb1.jpg', '2022-03-24', 'COMEDY');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (19, 'The Menu', true, 3, '/fPtUgMcLIboqlTlPrq0bQpKK8eq.jpg', '2022-11-17', 'ROMANCE');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (20, 'True Spirit', false, 16, '/B7m21gukMeVK3NAuk1PLCo9C8p.jpg', '2023-01-26', 'DRAMA');


--NEW GROUP

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (21, 'House', false, 1, '/ysIPmxLVYYvTUaOwEsLjF7nEf4C.jpg', '1986-02-27', 'HORROR');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (22, 'House', false, 2, '/5QPnbZLJIO1r6vP9Uejf96V1U1w.jpg', '2008-11-06', 'HORROR');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (23, 'HOUSE', true, 3, '/awHCV8PRDeA66RFlnEXANAx2Hpi.jpg', '2008-11-06', 'COMEDY');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (24, 'HOUSE', false, 4, null, '1986-02-27', 'COMEDY');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (25, 'House', false, 5, '/aCHv1T0kcsk5k5aOet9kefStSxb.jpg', '2010-02-17', 'ROMANCE');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (26, 'House', false, 6, '/oj8R1DrSqt7el42XHol3YW6hNK2.jpg', '2007-03-21', 'DRAMA');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (27, 'House', false, 7, '/v612ukb7I6g0cAgvCnABaZnj1eZ.jpg', '1995-01-01', 'DRAMA');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (28, 'HOUSE', false, 8, '/m9d4lMEqvxJC01AqPfD1DaDSGSi.jpg', '2007-03-21', 'HORROR');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (29, 'House', false, 8, null, '2002-08-31', 'ACTION');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (30, 'House Party', true, 10, '/KiyKR9m6h01eIvGObGmpt16U3F.jpg', '2023-01-12', 'DRAMA');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (31, 'The Painted House', false, 11, '/milQQcApBicb9x4ishCMA5zYz8c.jpg', '2015-12-04', 'COMEDY');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (32, 'House!', true, 12, NULL, '2000-01-01', 'ROMANCE');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (33, 'The House of the Lost on the Cape', false, 13, '/5afJWPrNwm7a220mOccwB30PCOq.jpg', '2021-08-27', 'HORROR');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (34, 'Housekeeper', false, 14, '/ff3HhJwfKPPLCqYMg9whlSNwIob.jpg', '2020-03-26', 'ACTION');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (35, 'Constantine: The House of Mystery', true, 15, '/kqYDoEb9PxLXM2ccoHInMMJalti.jpg', '2022-05-02', 'DRAMA');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (36, 'House of Flying Daggers', true, 16, '/lMwVwfPTVbpMScANktbD7NmMFCi.jpg', '2004-05-19', 'COMEDY');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (37, 'House of the Rising Sun', 'false', 1, '/nstOSqjuutx0nbDBom24dGwMTbD.jpg', '2011-07-19', 'ACTION');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (38, 'The House', 'true', 2, '/iZjMFSKCrleKolC1gYcz5Rs8bk1.jpg', '2022-01-14', 'DRAMA');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (39, 'The Housemaid', 'false', 3, '/yFZc1A26WAVmZJrYrX713ULStUp.jpg', '2010-05-13', 'COMEDY');

INSERT INTO movies (id, title, recommended, author_id, poster_path, release_date, type)
VALUES (40, 'Housekeeper - My Wife''s Friend', 'false', 4, '/k5vIKo60IUrqsbeWu6vJuECIte4.jpg', '2019-10-11', 'ROMANCE');



