-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Ven 27 Janvier 2017 à 11:45
-- Version du serveur :  5.7.17-0ubuntu0.16.04.1
-- Version de PHP :  7.0.13-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `Gestion_Formation`
--

-- --------------------------------------------------------

--
-- Structure de la table `ECF`
--

CREATE TABLE `ECF` (
  `id` int(11) NOT NULL,
  `id_formation` int(11) NOT NULL,
  `nom_ecf` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Formation`
--

CREATE TABLE `Formation` (
  `id` int(11) NOT NULL,
  `nom_formation` varchar(255) NOT NULL,
  `debut` date DEFAULT NULL,
  `fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Personne`
--

CREATE TABLE `Personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `isStagiaire` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Resultat`
--

CREATE TABLE `Resultat` (
  `id_ecf` int(11) NOT NULL,
  `id_stagiaire` int(11) NOT NULL,
  `validation` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Stagiaire`
--

CREATE TABLE `Stagiaire` (
  `code` int(11) NOT NULL,
  `id_personne` int(11) NOT NULL,
  `id_formation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déclencheurs `Stagiaire`
--
DELIMITER $$
CREATE TRIGGER `Stagiaire_AFTER_DELETE` AFTER DELETE ON `Stagiaire` FOR EACH ROW BEGIN

update Personne p SET isStagiaire = 0 WHERE OLD.id_personne = p.id;

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Stagiaire_AFTER_INSERT` AFTER INSERT ON `Stagiaire` FOR EACH ROW BEGIN

update Personne p SET isStagiaire = 1 WHERE NEW.id_personne = p.id;

END
$$
DELIMITER ;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ECF`
--
ALTER TABLE `ECF`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_formation` (`id_formation`);

--
-- Index pour la table `Formation`
--
ALTER TABLE `Formation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Personne`
--
ALTER TABLE `Personne`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Resultat`
--
ALTER TABLE `Resultat`
  ADD KEY `id_ecf` (`id_ecf`,`id_stagiaire`),
  ADD KEY `id_stagiaire` (`id_stagiaire`);

--
-- Index pour la table `Stagiaire`
--
ALTER TABLE `Stagiaire`
  ADD PRIMARY KEY (`code`),
  ADD KEY `id_personne` (`id_personne`),
  ADD KEY `id_formation` (`id_formation`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `ECF`
--
ALTER TABLE `ECF`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `Formation`
--
ALTER TABLE `Formation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `Personne`
--
ALTER TABLE `Personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `Stagiaire`
--
ALTER TABLE `Stagiaire`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `ECF`
--
ALTER TABLE `ECF`
  ADD CONSTRAINT `ECF_ibfk_1` FOREIGN KEY (`id_formation`) REFERENCES `Formation` (`id`);

--
-- Contraintes pour la table `Resultat`
--
ALTER TABLE `Resultat`
  ADD CONSTRAINT `Resultat_ibfk_1` FOREIGN KEY (`id_ecf`) REFERENCES `ECF` (`id`),
  ADD CONSTRAINT `Resultat_ibfk_2` FOREIGN KEY (`id_stagiaire`) REFERENCES `Stagiaire` (`code`);

--
-- Contraintes pour la table `Stagiaire`
--
ALTER TABLE `Stagiaire`
  ADD CONSTRAINT `Stagiaire_ibfk_1` FOREIGN KEY (`id_personne`) REFERENCES `Personne` (`id`),
  ADD CONSTRAINT `Stagiaire_ibfk_2` FOREIGN KEY (`id_formation`) REFERENCES `Formation` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
