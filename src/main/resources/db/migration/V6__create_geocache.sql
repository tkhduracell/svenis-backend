
CREATE TABLE svenis.geocaches(
    id bigint NOT NULL AUTO_INCREMENT,

    lat FLOAT,
    lng FLOAT,

    password TINYTEXT,
    clue TINYTEXT,
    point_text TINYTEXT,
    title TINYTEXT,
    hint TINYTEXT,
    toast_hint TINYTEXT,

    session_id bigint NOT NULL,

    FOREIGN KEY (session_id) REFERENCES svenis.sessions(id),

    CONSTRAINT pk_geocaches PRIMARY KEY (id)
);

INSERT INTO svenis.geocaches (
    lat,
    lng,
    id,
    password,
    clue,
    point_text,
    title,
    hint,
    toast_hint,
    session_id
) VALUES (
    59.324488,
    18.009762,
    0,
    'jidder',
    'Gul markering',
    'Geocaching: Fotboll',
    'Människans bästa vän',
    'Hunden sägs ofta vara människans bästa vän. Nu ska du få ett litet tips på vart du kan hitta ledtråden med hjälp av den nära vännen.\n\nVärldens minsta hund är chihuahuan. Ledtråden befinner sig omkring 2 chihuahua upp i luften noga uppklistrad.',
    'Fel!! Försök igen!',
    1
);