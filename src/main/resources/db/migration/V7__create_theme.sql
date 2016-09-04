
CREATE TABLE svenis.themes(
    id bigint NOT NULL AUTO_INCREMENT,

    primary_color VARCHAR(7),
    primary_dark_color VARCHAR(7),
    primary_text_color VARCHAR(7),
    accent_color VARCHAR(7),

    session_id bigint NOT NULL,

    FOREIGN KEY (session_id) REFERENCES svenis.sessions(id),

    CONSTRAINT pk_themes PRIMARY KEY (id)
);

INSERT INTO svenis.themes (
    primary_color,
    primary_dark_color,
    primary_text_color,
    accent_color,
    session_id
) VALUES (
    '#8BC34A',
    '#689F38',
    '#000000',
    '#4B0082',
    1
);