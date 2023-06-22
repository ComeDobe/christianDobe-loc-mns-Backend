-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 22 juin 2023 à 09:21
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `dobelocmns`
--

-- --------------------------------------------------------

--
-- Structure de la table `documents`
--

DROP TABLE IF EXISTS `documents`;
CREATE TABLE IF NOT EXISTS `documents` (
  `doc_id` int NOT NULL AUTO_INCREMENT,
  `doc_description` varchar(255) DEFAULT NULL,
  `doc_titre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- --------------------------------------------------------

--
-- Structure de la table `etat_materiel`
--

DROP TABLE IF EXISTS `etat_materiel`;
CREATE TABLE IF NOT EXISTS `etat_materiel` (
  `etat_id` int NOT NULL AUTO_INCREMENT,
  `etat_condition` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`etat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- --------------------------------------------------------

--
-- Structure de la table `gerer`
--

DROP TABLE IF EXISTS `gerer`;
CREATE TABLE IF NOT EXISTS `gerer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `materiel_id` int DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqv2cub2qewbpaywxc1kp0u4p1` (`materiel_id`),
  KEY `FK9q4ras1bl5dk4ct2v06p5r15u` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- --------------------------------------------------------

--
-- Structure de la table `localisation`
--

DROP TABLE IF EXISTS `localisation`;
CREATE TABLE IF NOT EXISTS `localisation` (
  `loc_id` int NOT NULL AUTO_INCREMENT,
  `loc_batiment` varchar(255) DEFAULT NULL,
  `loc_lieu` varchar(255) DEFAULT NULL,
  `loc_salle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`loc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8
;

--
-- Déchargement des données de la table `localisation`
--

INSERT INTO `localisation` (`loc_id`, `loc_batiment`, `loc_lieu`, `loc_salle`) VALUES
(1, 'Bâtiment A', '123 rue Principale', 'Salle 101'),
(2, 'Bâtiment B', '456 rue Secondaire', 'Salle 201'),
(3, 'Bâtiment C', 'mns', 'Salle 301'),
(4, 'Bâtiment D', 'mns', 'Salle 401'),
(5, 'Bâtiment E', '789 rue Tertiaire', 'Salle 501'),
(6, 'Bâtiment F', '321 rue Quaternaire', 'Salle 601'),
(7, 'Bâtiment G', '654 rue Quinternaire', 'Salle 701'),
(8, 'Bâtiment H', '987 rue Sexternaire', 'Salle 801'),
(9, 'Bâtiment I', '231 rue Septernaire', 'Salle 901'),
(10, 'Bâtiment J', '564 rue Octernaire', 'Salle 1001'),
(11, 'Bâtiment K', '897 rue Nonernaire', 'Salle 1101'),
(12, 'Bâtiment L', '312 rue Decernaire', 'Salle 1201');

-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

DROP TABLE IF EXISTS `materiel`;
CREATE TABLE IF NOT EXISTS `materiel` (
  `materiel_id` int NOT NULL AUTO_INCREMENT,
  `materiel_des` varchar(255) DEFAULT NULL,
  `materiel_marque` varchar(255) DEFAULT NULL,
  `materiel_qte` int DEFAULT NULL,
  `materiel_ref` varchar(255) DEFAULT NULL,
  `reserved` bit(1) DEFAULT NULL,
  `loc_id` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  PRIMARY KEY (`materiel_id`),
  KEY `FKsa3n9an46s281l837py8no07v` (`loc_id`),
  KEY `FKnqm0ujpetfyejxk3yu34myejb` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8
;

--
-- Déchargement des données de la table `materiel`
--

INSERT INTO `materiel` (`materiel_id`, `materiel_des`, `materiel_marque`, `materiel_qte`, `materiel_ref`, `reserved`, `loc_id`, `type_id`) VALUES
(1, 'Clavier et souris sans fil Logitech avec récepteur USB.', 'Telephone', 20, '456', b'0', 1, 10),
(2, 'Imprimante HP multifonction avec fonctionnalités d impression, de numérisation et de copie.', 'Imprimante', 100, '789', b'0', 1, 9),
(3, 'Imprimante HP multifonction avec fonctionnalités d impression, de numérisation et de copie.', 'Ancre', 25, '111', b'0', 2, 8),
(4, 'Surface Pro 7 avec processeur Intel Core i5, 8 Go de RAM et 256 Go de stockage.', 'Cable', 52, '222', b'0', 2, 1),
(5, 'MacBook Pro avec écran Retina, processeur Apple M1 et 512 Go de stockage', 'Apple', 100, '1234', b'0', 3, 2),
(6, 'Ordinateur de bureau Dell avec écran 24 pouces, processeur Intel Core i7 et 16 Go de RAM.', 'Dell', 25, '4567', b'0', 3, 3),
(7, 'Imprimante HP multifonction avec fonctionnalités d impression, de numérisation et de copie.', 'Acer', 89, '7890', b'0', 3, 4),
(8, 'Tablette Lenovo avec écran tactile, processeur Qualcomm Snapdragon et 64 Go de stockage.', 'Lenovo', 77, '2111', b'0', 4, 5),
(9, 'Ordinateur portable HP avec processeur Intel Core i5 et 8 Go de RAM.', 'Hp', 99, '1322', b'0', 4, 6),
(10, 'Écran incurvé Samsung de 32 pouces avec résolution 4K et technologie QLED.', 'Samsung', 652, '1987', b'0', 4, 7),
(11, 'Ordinateur portable Asus avec processeur AMD Ryzen 5 et 12 Go de RAM.', 'BMW2', 477, '123', b'0', 4, 8),
(12, 'Imprimante HP multifonction avec fonctionnalités dimpression, de numérisation et de copie.', 'Imprimante', 9, '789', b'0', 1, 9),
(13, 'Ordinateur portable HP avec processeur Intel Core i5 et 8 Go de RAM.', 'Hp', 22, '1322', b'0', 4, 10),
(14, 'Écran incurvé Samsung de 32 pouces avec résolution 4K et technologie QLED.', 'Samsung', 99, '1987', b'0', 4, 1),
(15, 'Caméra Sony 4K avec objectif grand angle et capacité de stockage de 128 Go.', 'Sony', 8, '1234', b'0', 5, 2),
(16, 'Appareil photo Canon avec objectif 24-105mm et capteur de 50,6 mégapixels.', 'Canon', 36, '4567', b'0', 5, 3),
(17, 'Appareil photo Nikon avec zoom optique 35x et capteur de 20,2 mégapixels.', 'Nikon', 9, '7890', b'0', 6, 4),
(18, 'Haut-parleur JBL portable avec Bluetooth et résistant à leau.', 'JBL', 58, '2111', b'0', 6, 5),
(19, 'Casque Bose sans fil avec réduction de bruit.', 'Bose', 36, '1322', b'0', 7, 6),
(20, 'Smart TV Samsung de 55 pouces avec résolution 4K et technologie QLED.', 'Samsung', 77, '1987', b'0', 7, 7),
(21, 'Réfrigérateur LG avec capacité de 26,2 pieds cubes et distributeur de glaçons.', 'LG', 55, '2123', b'0', 8, 8),
(22, 'Four Frigidaire avec 5 brûleurs et nettoyage à vapeur.', 'Frigidaire', 222, '4568', b'0', 8, 9),
(23, 'Console de jeu Sony PlayStation 5 avec disque dur de 825 Go.', 'Sony', 44, '7891', b'0', 9, 10),
(24, 'Console de jeu Microsoft Xbox Series X avec disque dur de 1 To.', 'Microsoft', 8, '2112', b'0', 9, 1),
(25, 'Console de jeu Nintendo Switch avec manettes Joy-Con.', 'Nintendo', 9687, '1323', b'0', 10, 2),
(26, 'Caméra GoPro HERO8 avec stabilisation dimage.', 'GoPro', 125, '1988', b'0', 10, 3),
(27, 'Caméscope Panasonic avec capacité d enregistrement 4K.', 'Panasonic', 78, '2124', b'0', 11, 4),
(28, 'Drone DJI Phantom avec caméra 4K et contrôleur de vol.', 'DJI', 1000, '4569', b'0', 11, 5),
(29, 'iPhone Apple avec écran 6,1 pouces, capacité de 128 Go et compatible 5G.', 'Apple', 2589, '7892', b'0', 12, 6),
(30, 'Smartphone Samsung Galaxy avec écran 6,2 pouces, capacité de 128 Go et compatible 5G.', 'Samsung', 14, '2113', b'0', 12, 7);

-- --------------------------------------------------------

--
-- Structure de la table `pannes`
--

DROP TABLE IF EXISTS `pannes`;
CREATE TABLE IF NOT EXISTS `pannes` (
  `panne_id` int NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `panne_description` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `materiel_materiel_id` int DEFAULT NULL,
  PRIMARY KEY (`panne_id`),
  KEY `FKlhhpymnb23urt1fawm3a3kube` (`materiel_materiel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8
;

--
-- Déchargement des données de la table `pannes`
--

INSERT INTO `pannes` (`panne_id`, `image_url`, `panne_description`, `user_name`, `materiel_materiel_id`) VALUES
(1, 'url_de_l_image_1', 'Problème d écran noir au démarrage', 'christ123', 1),
(2, 'url_de_l_image_2', 'Le clavier ne fonctionne pas correctement, certain', 'christ123', 2),
(3, 'url_de_l_image_3', 'Le système d exploitation se bloque régulièrement ', 'christ123', 3),
(4, 'url_de_l_image_4', 'Le ventilateur de refroidissement émet un bruit an', 'christ123', 4),
(5, 'url_de_l_image_5', 'Le disque dur ne peut pas être détecté par le syst', 'christ123', 5),
(6, 'url_de_l_image_6', 'L imprimante ne parvient pas à se connecter au rés', 'christ123', 6),
(7, 'url_de_l_image_7', 'La batterie de l ordinateur portable se décharge r', 'christ123', 7),
(8, 'url_de_l_image_8', 'Le lecteur de CD/DVD ne lit pas les disques correc', 'christ123', 8),
(9, 'url_de_l_image_9', 'Le port USB ne reconnaît pas les périphériques con', 'christ123', 9),
(10, 'url_de_l_image_10', 'L écran présente des lignes colorées ou des pixels', 'christ123', 10);

-- --------------------------------------------------------

--
-- Structure de la table `posseder`
--

DROP TABLE IF EXISTS `posseder`;
CREATE TABLE IF NOT EXISTS `posseder` (
  `materiel_id` int NOT NULL,
  `doc_id` int NOT NULL,
  PRIMARY KEY (`materiel_id`,`doc_id`),
  KEY `FKta4o7syg4l0jasr7kumhs42lt` (`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- --------------------------------------------------------

--
-- Structure de la table `pret`
--

DROP TABLE IF EXISTS `pret`;
CREATE TABLE IF NOT EXISTS `pret` (
  `pret_id` int NOT NULL AUTO_INCREMENT,
  `pret_debut` date DEFAULT NULL,
  `pret_fin` date DEFAULT NULL,
  `pret_description` varchar(255) DEFAULT NULL,
  `pret_qte` int DEFAULT NULL,
  `pret_valide` bit(1) DEFAULT NULL,
  `prolongation_valide` bit(1) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pret_id`),
  KEY `FKp01nhomcb1hxaf6gprcu2ityr` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8
;

--
-- Déchargement des données de la table `pret`
--

INSERT INTO `pret` (`pret_id`, `pret_debut`, `pret_fin`, `pret_description`, `pret_qte`, `pret_valide`, `prolongation_valide`, `user_name`) VALUES
(1, '2023-06-15', '2023-06-18', 'Team Building Event', 1, b'0', b'0', 'christ123'),
(2, '2023-07-10', '2023-07-15', 'VR Game Development', 1, b'0', b'0', 'christ123'),
(3, '2023-08-01', '2023-08-07', 'Virtual Tour', 2, b'0', b'1', 'christ123'),
(4, '2023-09-03', '2023-09-06', 'Conference Presentation', 3, b'0', b'0', 'christ123'),
(5, '2023-10-22', '2023-10-25', 'Product Demo', 4, b'0', b'0', 'christ123'),
(6, '2023-11-12', '2023-11-18', 'Training Session', 5, b'0', b'0', 'christ123'),
(7, '2023-12-05', '2023-12-10', 'Trade Show Exhibit', 6, b'0', b'0', 'christ123'),
(8, '2024-01-22', '2024-01-24', 'Research Project', 7, b'0', b'1', 'christ123'),
(9, '2024-02-14', '2024-02-19', 'Marketing Campaign', 8, b'0', b'1', 'christ123'),
(10, '2024-03-10', '2024-03-13', 'Art Installation', 9, b'0', b'0', 'christ123'),
(11, '2023-06-10', '2023-07-17', 'test validation de prolongement par l admin', 1, b'1', b'1', 'come'),
(12, '2023-06-13', '2023-06-20', 'testok', 4, b'0', b'1', 'admin123'),
(13, '2023-06-13', '2023-06-29', 'prolongement', 2, b'0', b'0', 'admin123');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `role_name` varchar(255) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`role_name`, `role_description`) VALUES
('Admin', 'Admin role'),
('User', 'Default role');

-- --------------------------------------------------------

--
-- Structure de la table `suivi`
--

DROP TABLE IF EXISTS `suivi`;
CREATE TABLE IF NOT EXISTS `suivi` (
  `suivi_id` int NOT NULL AUTO_INCREMENT,
  `suivi_statut` varchar(255) DEFAULT NULL,
  `panne_id` int DEFAULT NULL,
  PRIMARY KEY (`suivi_id`),
  KEY `FK2tffo955apg2p228yp4qo3hg3` (`panne_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `date_retour_reel` datetime(6) DEFAULT NULL,
  `type_libelle` varchar(255) DEFAULT NULL,
  `pret_id` int DEFAULT NULL,
  PRIMARY KEY (`type_id`),
  KEY `FK5osp4lb9ogcli1orui5kmakeg` (`pret_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8
;

--
-- Déchargement des données de la table `type`
--

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

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_name` varchar(255) NOT NULL,
  `user_adresse` varchar(255) DEFAULT NULL,
  `user_city` varchar(255) DEFAULT NULL,
  `user_date` date DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_last_name` varchar(255) DEFAULT NULL,
  `user_mobile` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`user_name`, `user_adresse`, `user_city`, `user_date`, `user_email`, `user_first_name`, `user_last_name`, `user_mobile`, `user_password`) VALUES
('admin123', NULL, NULL, NULL, NULL, 'admin', 'admin', NULL, '$2a$10$iH4EuDsIAN9QtNQdXyz5T.vimFawFcjZq1KK/Usftawl.sJjrhv.y'),
('chad', '1 rue des renards 67200', 'strasbourg', '2023-06-14', 'christian.dobe@stagiairesmns.fr', 'chad', 'womgue', '605998366', '$2a$10$df9z6NyAeoymjUNachbeIuwZzGuUVAJi2hLjVuI5hm0hyNpKg/yzK'),
('christ123', NULL, NULL, NULL, NULL, 'christ', 'dobe', NULL, '$2a$10$1jIsmlx2vJ/siGaJBiFkROWjpFYGwzvsEmHcc0Fu/fuxWhg3JkhTe'),
('come', '1 rue des renards', 'strasbourg', '2023-06-10', 'christiandobe@hotmail.fr', 'dobe', 'andre', '0605998366', '$2a$10$mLVNExGnmoJslALmMttQUeKGbHbBpRQ4wsATrnoNyQYAxFgsUw7Ke');

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
('admin123', 'Admin'),
('chad', 'User'),
('christ123', 'User'),
('come', 'User');

-- --------------------------------------------------------

--
-- Structure de la table `usure`
--

DROP TABLE IF EXISTS `usure`;
CREATE TABLE IF NOT EXISTS `usure` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_changement` varchar(255) DEFAULT NULL,
  `etat_id` int DEFAULT NULL,
  `materiel_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ua3xx3i7xdwwqls30ovchr7w` (`etat_id`),
  KEY `FKlov9kd2h8scxcalw3gyfpbwld` (`materiel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `gerer`
--
ALTER TABLE `gerer`
  ADD CONSTRAINT `FK9q4ras1bl5dk4ct2v06p5r15u` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`),
  ADD CONSTRAINT `FKqv2cub2qewbpaywxc1kp0u4p1` FOREIGN KEY (`materiel_id`) REFERENCES `materiel` (`materiel_id`);

--
-- Contraintes pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD CONSTRAINT `FKnqm0ujpetfyejxk3yu34myejb` FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`),
  ADD CONSTRAINT `FKsa3n9an46s281l837py8no07v` FOREIGN KEY (`loc_id`) REFERENCES `localisation` (`loc_id`);

--
-- Contraintes pour la table `pannes`
--
ALTER TABLE `pannes`
  ADD CONSTRAINT `FKlhhpymnb23urt1fawm3a3kube` FOREIGN KEY (`materiel_materiel_id`) REFERENCES `materiel` (`materiel_id`);

--
-- Contraintes pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `FKagthnrcj3rgrl9tsj8wtji8yi` FOREIGN KEY (`materiel_id`) REFERENCES `materiel` (`materiel_id`),
  ADD CONSTRAINT `FKta4o7syg4l0jasr7kumhs42lt` FOREIGN KEY (`doc_id`) REFERENCES `documents` (`doc_id`);

--
-- Contraintes pour la table `pret`
--
ALTER TABLE `pret`
  ADD CONSTRAINT `FKp01nhomcb1hxaf6gprcu2ityr` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`);

--
-- Contraintes pour la table `suivi`
--
ALTER TABLE `suivi`
  ADD CONSTRAINT `FK2tffo955apg2p228yp4qo3hg3` FOREIGN KEY (`panne_id`) REFERENCES `pannes` (`panne_id`);

--
-- Contraintes pour la table `type`
--
ALTER TABLE `type`
  ADD CONSTRAINT `FK5osp4lb9ogcli1orui5kmakeg` FOREIGN KEY (`pret_id`) REFERENCES `pret` (`pret_id`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_name`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_name`);

--
-- Contraintes pour la table `usure`
--
ALTER TABLE `usure`
  ADD CONSTRAINT `FK3ua3xx3i7xdwwqls30ovchr7w` FOREIGN KEY (`etat_id`) REFERENCES `etat_materiel` (`etat_id`),
  ADD CONSTRAINT `FKlov9kd2h8scxcalw3gyfpbwld` FOREIGN KEY (`materiel_id`) REFERENCES `materiel` (`materiel_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
