
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

    FOREIGN KEY (question_id) REFERENCES svenis.questions(id),

    CONSTRAINT pk_missions PRIMARY KEY (id)
);

INSERT INTO svenis.missions (
    name,
    description,
    title,
    success_toast_message,
    failure_toast_message,
    point_text,
    max_points,
    max_point_amount,
    question_id
) VALUES (
    'Skapa minnen.',
    'Under hela sitt liv skapar man minnen. Ett bra sÃ¤tt att spara minnen Ã¤r att ta en GROUPIE. Ta en groupie med 5 olika gÃ¤ng.',
    'Hur mÃ¥nga groupie?',
    'Du kommer bli en bra pappa som aldrig tvekar att vara med pÃ¥ bild.',
    'FÃ¥r hoppas du kommer ta lite familjefoto framÃ¶ver, fÃ¶r detta var inte Ã¶vertygande.',
    'Ta nÃ¥gra groupie',
    35,
    5,
    1
);

INSERT INTO svenis.missions (
    name,
    description,
    title,
    success_toast_message,
    failure_toast_message,
    point_text,
    max_points,
    max_point_amount,
    question_id
) VALUES (
    'Hur mycket orkar du?',
    'Detta Ã¤r ett bevis pÃ¥ om du blivit stor och orkar en rejÃ¤l bit mat.',
    'Hur mycket orkar du?',
    'Du Ã¤ter som en riktig man!',
    'Hade lite hÃ¶gre fÃ¶rvÃ¤ntningar men du Ã¥t iaf nÃ¥got annat Ã¤n burkmat.',
    'Hur mycket orkar du? (Cheese)',
    18,
    6,
    1
);