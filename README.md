# BootSecurity

SQL:

USE my_db1;

CREATE TABLE roles (
	  id INT PRIMARY KEY AUTO_INCREMENT,
	  name VARCHAR(45),
    enabled tinyint(1)
    );
CREATE TABLE users (
	  id INT PRIMARY KEY AUTO_INCREMENT,
	  name VARCHAR(45),
    surname VARCHAR(45),
    age INT(3),
    username VARCHAR(45),
    password VARCHAR(45),
    roles INT,
    FOREIGN KEY (roles) REFERENCES roles (id)
    );
    
  CREATE TABLE users_roles (
	  user_id int,
    role_id int,
    primary key (user_id, role_id),
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
    );
    
INSERT INTO my_db1.roles (name, enabled)
VALUES
	('ROLE_USER', 1),
	('ROLE_ADMIN', 1); 
  
INSERT INTO my_db1.users (name, surname, age, username, password, roles)
VALUES
	('ivan', 'ivanov', 45, 'ivan', 'ivan', 1),
	('petr', 'petrov', 32, 'petr', 'petr', 2),
	('sidr','sidorov', 76, 'sidr', 'sidr', 1);
  
INSERT INTO my_db1.users_roles (user_id, role_id)
VALUES
	(1, 1),
	(2, 2), 
  (3, 1); 
