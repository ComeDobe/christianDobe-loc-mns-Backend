
INSERT INTO `localisation` (`loc_id`, `loc_lieu`, `loc_batiment`, `loc_salle`) VALUES
(1, '123 rue Principale', 'Bâtiment A', 'Salle 101'),
(2, '456 rue Secondaire', 'Bâtiment B', 'Salle 201'),
(3, 'mns', 'Bâtiment C', 'Salle 301'),
(4, 'mns', 'Bâtiment D', 'Salle 401'),
(5, '789 rue Tertiaire', 'Bâtiment E', 'Salle 501'),
(6, '321 rue Quaternaire', 'Bâtiment F', 'Salle 601'),
(7, '654 rue Quinternaire', 'Bâtiment G', 'Salle 701'),
(8, '987 rue Sexternaire', 'Bâtiment H', 'Salle 801'),
(9, '231 rue Septernaire', 'Bâtiment I', 'Salle 901'),
(10, '564 rue Octernaire', 'Bâtiment J', 'Salle 1001'),
(11, '897 rue Nonernaire', 'Bâtiment K', 'Salle 1101'),
(12, '312 rue Decernaire', 'Bâtiment L', 'Salle 1201');



INSERT INTO `user` (`user_name`, `user_first_name`, `user_last_name`, `user_password`, `user_adresse`, `user_city`, `user_date`, `user_email`, `user_mobile`) VALUES
('chad', 'chad', 'womgue', '$2a$10$df9z6NyAeoymjUNachbeIuwZzGuUVAJi2hLjVuI5hm0hyNpKg/yzK', '1 rue des renards 67200', 'strasbourg', '2023-06-14', 'christian.dobe@stagiairesmns.fr', '605998366'),
('come', 'dobe', 'andre', '$2a$10$mLVNExGnmoJslALmMttQUeKGbHbBpRQ4wsATrnoNyQYAxFgsUw7Ke', '1 rue des renards', 'strasbourg', '2023-06-10', 'christiandobe@hotmail.fr', '0605998366');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES

('chad', 'User'),

('come', 'User');



INSERT INTO `pret` (`pret_id`, `pret_description`, `user_name`, `pret_debut`, `pret_fin`, `pret_qte`, `pret_valide`, `prolongation_valide`) VALUES
(1, 'Team Building Event', 'christ123', '2023-06-15', '2023-06-18', 1, b'0', b'0'),
(2, 'VR Game Development', 'christ123', '2023-07-10', '2023-07-15', 1, b'0', b'0'),
(3, 'Virtual Tour', 'christ123', '2023-08-01', '2023-08-07', 2, b'0', b'1'),
(4, 'Conference Presentation', 'christ123', '2023-09-03', '2023-09-06', 3, b'0', b'0'),
(5, 'Product Demo', 'christ123', '2023-10-22', '2023-10-25', 4, b'0', b'0'),
(6, 'Training Session', 'christ123', '2023-11-12', '2023-11-18', 5, b'0', b'0'),
(7, 'Trade Show Exhibit', 'christ123', '2023-12-05', '2023-12-10', 6, b'0', b'0'),
(8, 'Research Project', 'christ123', '2024-01-22', '2024-01-24', 7, b'0', b'1'),
(9, 'Marketing Campaign', 'christ123', '2024-02-14', '2024-02-19', 8, b'0', b'1'),
(10, 'Art Installation', 'christ123', '2024-03-10', '2024-03-13', 9, b'0', b'0'),
(11, 'test validation de prolongement par l admin', 'come', '2023-06-10', '2023-07-17', 1, b'1', b'1'),
(12, 'testok', 'admin123', '2023-06-13', '2023-06-20', 4, b'0', b'1'),
(13, 'prolongement', 'admin123', '2023-06-13', '2023-06-29', 2, b'0', b'0');


INSERT INTO `type` (`type_id`, `date_retour_reel`, `type_libelle`, `pret_id`) VALUES
(1, '2024-01-01 00:00:00.000000', 'Wireless', 1),
(2, '2024-02-01 00:00:00.000000', 'Console', 2),
(3, '2024-03-01 00:00:00.000000', 'Room-scale', 3),
(4, '2024-04-01 00:00:00.000000', 'Mobile', 4),
(5, '2024-05-01 00:00:00.000000', 'Mixed Reality', 5),
(6, '2024-06-01 00:00:00.000000', 'Smartphone', 6),
(7, '2024-07-01 00:00:00.000000', 'Standalone', 7),
(8, '2024-08-01 00:00:00.000000', 'Windows Mixed Reality', 8),
(9, '2024-09-01 00:00:00.000000', 'Overheated Rendering', 9),
(10, '2024-10-01 00:00:00.000000', 'Eye Tracking', 10);




INSERT INTO `materiel` (`materiel_id`, `materiel_marque`, `materiel_ref`, `loc_id`, `materiel_des`, `materiel_qte`, `reserved`, `type_id`) VALUES
(1, 'Telephone', '456', 1, 'Clavier et souris sans fil Logitech avec récepteur USB.', 20, b'0', 10),
(2, 'Imprimante', '789', 1, 'Imprimante HP multifonction avec fonctionnalités d impression, de numérisation et de copie.', 100, b'0', 9),
(3, 'Ancre', '111', 2, 'Imprimante HP multifonction avec fonctionnalités d impression, de numérisation et de copie.', 25, b'0', 8),
(4, 'Cable', '222', 2, 'Surface Pro 7 avec processeur Intel Core i5, 8 Go de RAM et 256 Go de stockage.', 52, b'0', 1),
(5, 'Apple', '1234', 3, 'MacBook Pro avec écran Retina, processeur Apple M1 et 512 Go de stockage', 100, b'0', 2),
(6, 'Dell', '4567', 3, 'Ordinateur de bureau Dell avec écran 24 pouces, processeur Intel Core i7 et 16 Go de RAM.', 25, b'0', 3),
(7, 'Acer', '7890', 3, 'Imprimante HP multifonction avec fonctionnalités d impression, de numérisation et de copie.', 89, b'0', 4),
(8, 'Lenovo', '2111', 4, 'Tablette Lenovo avec écran tactile, processeur Qualcomm Snapdragon et 64 Go de stockage.', 77, b'0', 5),
(9, 'Hp', '1322', 4, 'Ordinateur portable HP avec processeur Intel Core i5 et 8 Go de RAM.', 99, b'0', 6),
(10, 'Samsung', '1987', 4, 'Écran incurvé Samsung de 32 pouces avec résolution 4K et technologie QLED.', 652, b'0', 7),
(11, 'BMW2', '123', 4, 'Ordinateur portable Asus avec processeur AMD Ryzen 5 et 12 Go de RAM.', 477, b'0', 8),
(12, 'Imprimante', '789', 1, 'Imprimante HP multifonction avec fonctionnalités dimpression, de numérisation et de copie.', 9, b'0', 9),
(13, 'Hp', '1322', 4, 'Ordinateur portable HP avec processeur Intel Core i5 et 8 Go de RAM.', 22, b'0', 10),
(14, 'Samsung', '1987', 4, 'Écran incurvé Samsung de 32 pouces avec résolution 4K et technologie QLED.', 99, b'0', 1),
(15, 'Sony', '1234', 5, 'Caméra Sony 4K avec objectif grand angle et capacité de stockage de 128 Go.', 8, b'0', 2),
(16, 'Canon', '4567', 5, 'Appareil photo Canon avec objectif 24-105mm et capteur de 50,6 mégapixels.', 36, b'0', 3),
(17, 'Nikon', '7890', 6, 'Appareil photo Nikon avec zoom optique 35x et capteur de 20,2 mégapixels.', 9, b'0', 4),
(18, 'JBL', '2111', 6, 'Haut-parleur JBL portable avec Bluetooth et résistant à leau.', 58, b'0', 5),
(19, 'Bose', '1322', 7, 'Casque Bose sans fil avec réduction de bruit.', 36, b'0', 6),
(20, 'Samsung', '1987', 7, 'Smart TV Samsung de 55 pouces avec résolution 4K et technologie QLED.', 77, b'0', 7),
(21, 'LG', '2123', 8, 'Réfrigérateur LG avec capacité de 26,2 pieds cubes et distributeur de glaçons.', 55, b'0', 8),
(22, 'Frigidaire', '4568', 8, 'Four Frigidaire avec 5 brûleurs et nettoyage à vapeur.', 222, b'0', 9),
(23, 'Sony', '7891', 9, 'Console de jeu Sony PlayStation 5 avec disque dur de 825 Go.', 44, b'0', 10),
(24, 'Microsoft', '2112', 9, 'Console de jeu Microsoft Xbox Series X avec disque dur de 1 To.', 8, b'0', 1),
(25, 'Nintendo', '1323', 10, 'Console de jeu Nintendo Switch avec manettes Joy-Con.', 9687, b'0', 2),
(26, 'GoPro', '1988', 10, 'Caméra GoPro HERO8 avec stabilisation dimage.', 125, b'0', 3),
(27, 'Panasonic', '2124', 11, 'Caméscope Panasonic avec capacité d enregistrement 4K.', 78, b'0', 4),
(28, 'DJI', '4569', 11, 'Drone DJI Phantom avec caméra 4K et contrôleur de vol.', 1000, b'0', 5),
(29, 'Apple', '7892', 12, 'iPhone Apple avec écran 6,1 pouces, capacité de 128 Go et compatible 5G.', 2589, b'0', 6),
(30, 'Samsung', '2113', 12, 'Smartphone Samsung Galaxy avec écran 6,2 pouces, capacité de 128 Go et compatible 5G.', 14, b'0', 7);


INSERT INTO `pannes` (`panne_id`, `panne_description`, `user_name`, `image_url`, `materiel_materiel_id`) VALUES
(1, 'Problème d écran noir au démarrage', 'christ123', 'url_de_l_image_1', 1),
(2, 'Le clavier ne fonctionne pas correctement, certain', 'christ123', 'url_de_l_image_2', 2),
(3, 'Le système d exploitation se bloque régulièrement ', 'christ123', 'url_de_l_image_3', 3),
(4, 'Le ventilateur de refroidissement émet un bruit an', 'christ123', 'url_de_l_image_4', 4),
(5, 'Le disque dur ne peut pas être détecté par le syst', 'christ123', 'url_de_l_image_5', 5),
(6, 'L imprimante ne parvient pas à se connecter au rés', 'christ123', 'url_de_l_image_6', 6),
(7, 'La batterie de l ordinateur portable se décharge r', 'christ123', 'url_de_l_image_7', 7),
(8, 'Le lecteur de CD/DVD ne lit pas les disques correc', 'christ123', 'url_de_l_image_8', 8),
(9, 'Le port USB ne reconnaît pas les périphériques con', 'christ123', 'url_de_l_image_9', 9),
(10, 'L écran présente des lignes colorées ou des pixels', 'christ123', 'url_de_l_image_10', 10);











