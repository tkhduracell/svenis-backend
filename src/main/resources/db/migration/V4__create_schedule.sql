
CREATE TABLE svenis.puzzle(
    id bigint NOT NULL AUTO_INCREMENT,

    title varchar(255)
    description varchar(255),

    question_id bigint,

    CONSTRAINT fk_questions FOREIGN KEY (question_id) (svenis.questions.id),
    CONSTRAINT pk_missions PRIMARY KEY (id)
);

INSERT INTO svenis.questions (
    title, description
) VALUES (
    "Vakna!", "Höga ljud är något du får vänja dig vid när dina barn kommer springa runt överallt under dygnets alla timmar."
);

INSERT INTO svenis.questions (
    title, description
) VALUES (
    "Lekdags", "Snart har du inte tid att vara med oss mer så därför vill vi passa på att leka ordentligt med dig en sista gång. Glöm inte, det är alltid på blodigaste allvar!"
);