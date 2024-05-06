-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id: Primary key, integer, auto-increment.',
  `title` varchar(255) NOT NULL COMMENT 'title: Title of the post, VARCHAR.',
  `content` text COMMENT 'Main Post Content',
  `createdAt` datetime(6) DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_author_id` (`author_id`),
  CONSTRAINT `fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (16,'Plog Post #1','Scripts.com\nBee Movie\nBy Jerry Seinfeld\n\nNARRATOR:\n(Black screen with text; The sound of buzzing bees can be heard)\nAccording to all known laws\nof aviation,\n :\nthere is no way a bee\nshould be able to fly.\n :\nIts wings are too small to get\nits fat little body off the ground.\n :\nThe bee, of course, flies anyway\n :\nbecause bees don\'t care\nwhat humans think is impossible.\nBARRY BENSON:\n(Barry is picking out a shirt)\nYellow, black. Yellow, black.\nYellow, black. Yellow, black.\n :\nOoh, black and yellow!\nLet\'s shake it up a little.\nJANET BENSON:\nBarry! Breakfast is ready!\nBARRY:\nComing!\n :\nHang on a second.\n(Barry uses his antenna like a phone)\n :\nHello?\nADAM FLAYMAN:\n\n(Through phone)\n- Barry?\nBARRY:\n- Adam?\nADAM:\n- Can you believe this is happening?\nBARRY:\n- I can\'t. I\'ll pick you up.\n(Barry flies down the stairs)\n :\nMARTIN BENSON:\nLooking sharp.\nJANET:\nUse the stairs. Your father\npaid good money for those.\nBARRY:\nSorry. I\'m excited.\nMARTIN:\nHere\'s the graduate.\nWe\'re very proud of you, son.\n :\nA perfect report card, all B\'s.\nJANET:\nVery proud.\n(Rubs Barry\'s hair)\nBARRY=\nMa! I got a thing going here.\nJANET:\n- You got lint on your fuzz.\nBARRY:\n- Ow! That\'s me!\n\nJANET:\n- Wave to us! We\'ll be in row 118,000.\n- Bye!\n(Barry flies out the door)\nJANET:\nBarry, I told you,\nstop flying in the house!\n(Barry drives through the hive,and is waved at by Adam who is reading a\nnewspaper)\nBARRY==\n- Hey, Adam.\nADAM:\n- Hey, Barry.\n(Adam gets in Barry\'s car)\n :\n- Is that fuzz gel?\nBARRY:\n- A little. Special day, graduation.\nADAM:\nNever thought I\'d make it.\n(Barry pulls away from the house and continues driving)\nBARRY:\nThree days grade school,\nthree days high school...\nADAM:\nThose were awkward.\nBARRY:\nThree days college. I\'m glad I took\na day and hitchhiked around the hive.\nADAM==\nYou did come back different.\n(Barry and Adam pass by Artie, who is jogging)\nARTIE:\n- Hi, Barry!\n\nBARRY:\n- Artie, growing a mustache? Looks good.\nADAM:\n- Hear about Frankie?\nBARRY:\n- Yeah.\nADAM==\n- You going to the funeral?\nBARRY:\n- No, I\'m not going to his funeral.\n :\nEverybody knows,\nsting someone, you die.\n :\nDon\'t waste it on a squirrel.\nSuch a hothead.\nADAM:\nI guess he could have\njust gotten out of the way.\n(The car does a barrel roll on the loop-shaped bridge and lands on the\nhighway)\n :\nI love this incorporating\nan amusement park into our regular day.\nBARRY:\nI guess that\'s why they say we don\'t need vacations.\n(Barry parallel parks the car and together they fly over the graduating\nstudents)\nBoy, quite a bit of pomp...\nunder the circumstances.\n(Barry and Adam sit down and put on their hats)\n :\n- Well, Adam, today we are men.\n\nADAM:\n- We are!\nBARRY=\n- Bee-men.\n=ADAM=\n- Amen!\nBARRY AND ADAM:\nHallelujah!\n(Barry and Adam both have a happy spasm)\nANNOUNCER:\nStudents, faculty, distinguished bees,\n :\nplease welcome Dean Buzzwell.\nDEAN BUZZWELL:\nWelcome, New Hive Oity\ngraduating class of...\n :\n...9:\n :\nThat concludes our ceremonies.\n :\nAnd begins your career\nat Honex Industries!\nADAM:\nWill we pick our job today?\n(Adam and Barry get into a tour bus)\nBARRY=\nI heard it\'s just orientation.\n(Tour buses rise out of the ground and the students are automatically\nloaded into the buses)\nTOUR GUIDE:\nHeads up! Here we go.\n\nANNOUNCER:\nKeep your hands and antennas\ninside the tram at all times.\nBARRY:\n- Wonder what it\'ll be like?\nADAM:\n- A li',NULL,NULL);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-06 12:54:20
