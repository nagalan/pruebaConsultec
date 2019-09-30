
INSERT INTO office (date_created, name) VALUES
  (CURRENT_TIMESTAMP,'concesionario1'),
  (CURRENT_TIMESTAMP,'concesionario2'),
  (CURRENT_TIMESTAMP,'concesionario3');
  
INSERT INTO customer (date_created, name, last_name, user_name, password, email, address, status, office_id) VALUES
  (CURRENT_TIMESTAMP,'nombre1', 'apellido1', 'usuario1','1111','user1@gmail.com','direccion1','ENABLED',1),
  (CURRENT_TIMESTAMP,'nombre2', 'apellido2', 'usuario2','2222','user2@gmail.com','direccion2','ENABLED',2),
  (CURRENT_TIMESTAMP,'nombre3', 'apellido3', 'usuario3','3333','user3@gmail.com','direccion3','ENABLED',2),
  (CURRENT_TIMESTAMP,'nombre4', 'apellido4', 'usuario4','4444','user4@gmail.com','direccion4','ENABLED',1);

