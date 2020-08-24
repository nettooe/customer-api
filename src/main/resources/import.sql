INSERT INTO user(username, password) VALUES ('admin', 'OyR+CCJbfhuC0soKon/P3a7J4YTpL3o8LG8FgG3YeLk=');
INSERT INTO user(username, password) VALUES ('user', 'u3FOP4rW7dZRx8I8FqwHfgRDu1/9yIzZO+etM0IliNc=');

insert INTO user_role(user_id, role_name) VALUES ( (SELECT u.id FROM user u WHERE u.username = 'admin') , 'ADMIN');
insert INTO user_role(user_id, role_name) VALUES ( (SELECT u.id FROM user u WHERE u.username = 'user')  , 'USER');