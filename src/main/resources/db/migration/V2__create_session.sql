
CREATE TABLE svenis.sessions (
    id bigint NOT NULL AUTO_INCREMENT,
    language varchar(255),
    app_name varchar(255),
    title varchar(255),
    gender varchar(255),
    name varchar(255),
    admin_pin varchar(255),

    CONSTRAINT pk_session PRIMARY KEY (id)
);

INSERT INTO svenis.sessions (
    language, app_name, title, gender, name, admin_pin
) VALUES (
    'sv', 'my-app', 'my title', 'hermale', 'pella', '1234'
);