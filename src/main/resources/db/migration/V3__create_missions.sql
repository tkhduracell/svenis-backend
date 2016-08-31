
CREATE TABLE svenis.missions(
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255),
    description varchar(255),
    title varchar(255),
    success_toast_message varchar(255),
    failure_toast_message varchar(255),
    point_text varchar(255),
    max_points int,
    max_point_amount int,

    question_id bigint,

    CONSTRAINT fk_questions FOREIGN KEY (question_id) (svenis.questions.id),
    CONSTRAINT pk_missions PRIMARY KEY (id)
);

INSERT INTO svenis.questions (
    name, description, title,
    success_toast_message, failure_toast_message,
    point_text, max_points, max_point_amount
) VALUES (
    "Skapa minnen.",
    "Under hela sitt liv skapar man minnen. Ett bra sÃ¤tt att spara minnen Ã¤r att ta en GROUPIE. Ta en groupie med 5 olika gÃ¤ng.",
    "Hur mÃ¥nga groupie?",
    "Du kommer bli en bra pappa som aldrig tvekar att vara med pÃ¥ bild."
    "FÃ¥r hoppas du kommer ta lite familjefoto framÃ¶ver, fÃ¶r detta var inte Ã¶vertygande."
    "Ta nÃ¥gra groupie",
    35,
    5,
    "amount"
);

    "max_points": 35,
    "max_point_amount": 5,
    "type": "amount"