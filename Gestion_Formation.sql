-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Dim 29 Janvier 2017 à 14:50
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestion_formation`
--

-- --------------------------------------------------------

--
-- Structure de la table `ecf`
--

CREATE TABLE `ecf` (
  `id` int(11) NOT NULL,
  `id_formation` int(11) NOT NULL,
  `nom_ecf` varchar(255) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id` int(11) NOT NULL,
  `nom_formation` varchar(255) NOT NULL,
  `debut` date DEFAULT NULL,
  `fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `isStagiaire` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `resultat`
--

CREATE TABLE `resultat` (
  `id_ecf` int(11) NOT NULL,
  `id_stagiaire` int(11) NOT NULL,
  `validation` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `stagiaire`
--

CREATE TABLE `stagiaire` (
  `code` int(11) NOT NULL,
  `id_personne` int(11) NOT NULL,
  `id_formation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déclencheurs `stagiaire`
--
DELIMITER $$
CREATE TRIGGER `Stagiaire_AFTER_DELETE` AFTER DELETE ON `stagiaire` FOR EACH ROW BEGIN

update Personne p SET isStagiaire = 0 WHERE OLD.id_personne = p.id;

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Stagiaire_AFTER_INSERT` AFTER INSERT ON `stagiaire` FOR EACH ROW BEGIN

update Personne p SET isStagiaire = 1 WHERE NEW.id_personne = p.id;

END
$$
DELIMITER ;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ecf`
--
ALTER TABLE `ecf`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_formation` (`id_formation`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `resultat`
--
ALTER TABLE `resultat`
  ADD KEY `id_ecf` (`id_ecf`,`id_stagiaire`),
  ADD KEY `id_stagiaire` (`id_stagiaire`);

--
-- Index pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD PRIMARY KEY (`code`),
  ADD KEY `id_personne` (`id_personne`),
  ADD KEY `id_formation` (`id_formation`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `ecf`
--
ALTER TABLE `ecf`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `ecf`
--
ALTER TABLE `ecf`
  ADD CONSTRAINT `ECF_ibfk_1` FOREIGN KEY (`id_formation`) REFERENCES `formation` (`id`);

--
-- Contraintes pour la table `resultat`
--
ALTER TABLE `resultat`
  ADD CONSTRAINT `Resultat_ibfk_1` FOREIGN KEY (`id_ecf`) REFERENCES `ecf` (`id`),
  ADD CONSTRAINT `Resultat_ibfk_2` FOREIGN KEY (`id_stagiaire`) REFERENCES `stagiaire` (`code`);

--
-- Contraintes pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD CONSTRAINT `Stagiaire_ibfk_1` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `Stagiaire_ibfk_2` FOREIGN KEY (`id_formation`) REFERENCES `formation` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
