
CREATE TABLE svenis.schedule(
    id bigint NOT NULL AUTO_INCREMENT,

    title varchar(255),
    description varchar(255),

    question_id bigint,

    FOREIGN KEY (question_id) REFERENCES svenis.questions(id),

    CONSTRAINT pk_schedule PRIMARY KEY (id)
);

INSERT INTO svenis.schedule (
    title, description, question_id
) VALUES (
    'Vakna!',
    'Höga ljud är något du får vänja dig vid när dina barn kommer springa runt överallt under dygnets alla timmar.',
    1
);

INSERT INTO svenis.schedule (
    title, description, question_id
) VALUES (
    'Lekdags',
    'Snart har du inte tid att vara med oss mer så därför vill vi passa på att leka ordentligt med dig en sista gång. Glöm inte, det är alltid på blodigaste allvar!',
    1
);