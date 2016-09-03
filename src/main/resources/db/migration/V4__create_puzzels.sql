
CREATE TABLE svenis.puzzle(
    id bigint NOT NULL AUTO_INCREMENT,
    title varchar(255),
    card_title varchar(255),
    card_description varchar(255),

    unit varchar(255),
    question varchar(255),
    point_text varchar(255),

    max_points int,
    min_points int,

    correct_answer int,
    points int,

    question_id bigint,

    FOREIGN KEY (question_id) REFERENCES svenis.questions(id),

    CONSTRAINT pk_puzzle PRIMARY KEY (id)
);

INSERT INTO svenis.puzzle (
    card_title,
    card_description,
    title,
    unit,
    question,
    point_text,
    max_points,
    min_points,
    correct_answer,
    points
) VALUES (
    'Ta dig till jobbet',
    'Du Ã¥ker till och frÃ¥n jobbet varje dag. Vad Ã¤r egentligen vÃ¤rdet av att bo nÃ¤ra sitt arbete?',
    'Pendling',
    'dagar',
    'Hur mycket tid per Ã¥r spenderar stockholmare i genomsnitt pÃ¥ att pendla?',
    'Uppskatta pendling',
    68,
    20,
    44,
    30
);