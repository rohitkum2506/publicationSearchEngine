CREATE DATABASE  IF NOT EXISTS `msd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `msd`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: msd
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author_connections`
--

DROP TABLE IF EXISTS `author_connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author_connections` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AuthorID` int(11) NOT NULL,
  `ConnectedTo` int(11) NOT NULL,
  `Relation` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`,`AuthorID`),
  KEY `FK_author_connections_connected_to_idx` (`ConnectedTo`),
  KEY `FK_author_connections_author` (`AuthorID`),
  CONSTRAINT `FK_author_connections_author` FOREIGN KEY (`AuthorID`) REFERENCES `authors` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_author_connections_connected_to` FOREIGN KEY (`ConnectedTo`) REFERENCES `authors` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Connections between authors';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author_connections`
--

LOCK TABLES `author_connections` WRITE;
/*!40000 ALTER TABLE `author_connections` DISABLE KEYS */;
/*!40000 ALTER TABLE `author_connections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author_references`
--

DROP TABLE IF EXISTS `author_references`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author_references` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AuthorID` int(11) NOT NULL,
  `Url` varchar(1000) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_AuthoReferences_Author_idx` (`AuthorID`),
  CONSTRAINT `FK_AuthoReferences_Author` FOREIGN KEY (`AuthorID`) REFERENCES `authors` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=big5 COMMENT='External reference URL for authors';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author_references`
--

LOCK TABLES `author_references` WRITE;
/*!40000 ALTER TABLE `author_references` DISABLE KEYS */;
/*!40000 ALTER TABLE `author_references` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(500) NOT NULL,
  `HasBeenEditor` bit(1) DEFAULT NULL,
  `HasBeenPC` bit(1) DEFAULT NULL,
  `Meta` varchar(5000) DEFAULT NULL,
  `Alias` varchar(500) DEFAULT NULL,
  `Key` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Key_UNIQUE` (`Key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Details of Author of an article';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proceedings`
--

DROP TABLE IF EXISTS `proceedings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proceedings` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(1000) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Year` int(11) DEFAULT NULL,
  `Isbn` varchar(20) DEFAULT NULL,
  `BookTitle` varchar(1000) DEFAULT NULL,
  `Publisher` varchar(1000) DEFAULT NULL,
  `Url` varchar(1000) DEFAULT NULL,
  `Key` varchar(1000) DEFAULT NULL,
  `Source` varchar(50) NOT NULL,
  `SeriesTitle` varchar(1000) DEFAULT NULL,
  `SeriesUrl` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `Isbn_UNIQUE` (`Isbn`),
  KEY `FK_Source_idx` (`Source`),
  CONSTRAINT `FK_Proceedings_Source` FOREIGN KEY (`Source`) REFERENCES `sources` (`Name`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Journal or Conference details';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proceedings`
--

LOCK TABLES `proceedings` WRITE;
/*!40000 ALTER TABLE `proceedings` DISABLE KEYS */;
/*!40000 ALTER TABLE `proceedings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publication_author_info`
--

DROP TABLE IF EXISTS `publication_author_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publication_author_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PublicationID` int(11) NOT NULL,
  `AuthorID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Publication_Author_Info_Author_idx` (`AuthorID`),
  KEY `FK_Publication_Author_Info_Publication_idx` (`PublicationID`),
  CONSTRAINT `FK_Publication_Author_Info_Author` FOREIGN KEY (`AuthorID`) REFERENCES `authors` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Publication_Author_Info_Publication` FOREIGN KEY (`PublicationID`) REFERENCES `publications` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Authors of a publication';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publication_author_info`
--

LOCK TABLES `publication_author_info` WRITE;
/*!40000 ALTER TABLE `publication_author_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `publication_author_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publications`
--

DROP TABLE IF EXISTS `publications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publications` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(1000) NOT NULL,
  `Source` varchar(50) NOT NULL,
  `Type` varchar(15) NOT NULL,
  `Year` int(11) DEFAULT NULL,
  `ProceedingID` int(11) DEFAULT NULL,
  `IssueNumber` int(11) DEFAULT NULL,
  `Url` varchar(1000) DEFAULT NULL,
  `ExternalReference` varchar(1000) DEFAULT NULL,
  `Volume` int(11) DEFAULT NULL,
  `BookTitle` varchar(1000) DEFAULT NULL,
  `CrossReference` varchar(1000) DEFAULT NULL,
  `Meta` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `FK_source_idx` (`Source`),
  KEY `FK_Publications_Proceedings_idx` (`ProceedingID`),
  CONSTRAINT `FK_Publications_Proceedings` FOREIGN KEY (`ProceedingID`) REFERENCES `proceedings` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Publications_Source` FOREIGN KEY (`Source`) REFERENCES `sources` (`Name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Journal or Conference Publication details';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publications`
--

LOCK TABLES `publications` WRITE;
/*!40000 ALTER TABLE `publications` DISABLE KEYS */;
/*!40000 ALTER TABLE `publications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shortlist_authors`
--

DROP TABLE IF EXISTS `shortlist_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shortlist_authors` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shortlist` varchar(1000) NOT NULL,
  `AuthorID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_shortlist_authors_authors_idx` (`AuthorID`),
  KEY `FK_shortlist_authors_shortlist_idx` (`Shortlist`),
  CONSTRAINT `FK_shortlist_authors_authors` FOREIGN KEY (`AuthorID`) REFERENCES `authors` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_shortlist_authors_shortlist` FOREIGN KEY (`Shortlist`) REFERENCES `shortlists` (`Name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='List of authors in a shortlist';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shortlist_authors`
--

LOCK TABLES `shortlist_authors` WRITE;
/*!40000 ALTER TABLE `shortlist_authors` DISABLE KEYS */;
/*!40000 ALTER TABLE `shortlist_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shortlists`
--

DROP TABLE IF EXISTS `shortlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shortlists` (
  `Name` varchar(1000) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shortlists`
--

LOCK TABLES `shortlists` WRITE;
/*!40000 ALTER TABLE `shortlists` DISABLE KEYS */;
/*!40000 ALTER TABLE `shortlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sources`
--

DROP TABLE IF EXISTS `sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sources` (
  `Name` varchar(50) NOT NULL,
  `RootUrl` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Name`),
  UNIQUE KEY `Name_UNIQUE` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Lookup table for list of sources';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sources`
--

LOCK TABLES `sources` WRITE;
/*!40000 ALTER TABLE `sources` DISABLE KEYS */;
/*!40000 ALTER TABLE `sources` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-18  1:30:02
