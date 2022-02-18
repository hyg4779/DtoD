-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: i6b210.p.ssafy.io    Database: dtod_db
-- ------------------------------------------------------
-- Server version	5.7.37

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
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`authority_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cboardcomment`
--

DROP TABLE IF EXISTS `cboardcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cboardcomment` (
  `ccomment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ccomment_content` varchar(50) DEFAULT NULL,
  `ccomment_time` datetime(6) DEFAULT NULL,
  `cboard_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ccomment_id`),
  KEY `FK97w69p6743ffqygtykpst9pcc` (`cboard_id`),
  KEY `FKa2of9567ubodf9vspq36hjwbw` (`user_id`),
  CONSTRAINT `FK97w69p6743ffqygtykpst9pcc` FOREIGN KEY (`cboard_id`) REFERENCES `commonboard` (`cboard_id`) ON DELETE CASCADE,
  CONSTRAINT `FKa2of9567ubodf9vspq36hjwbw` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cboardcomment`
--

LOCK TABLES `cboardcomment` WRITE;
/*!40000 ALTER TABLE `cboardcomment` DISABLE KEYS */;
INSERT INTO `cboardcomment` VALUES (1,'동의합니다','2022-02-18 10:46:26.958076',1,3),(2,'이번 주는 쉬어야죠ㅠㅠ','2022-02-18 10:47:55.427292',2,2),(3,'저는 잘 모르겠네요...','2022-02-18 10:49:23.424562',3,3),(4,'으아아악','2022-02-18 11:35:31.991746',7,2);
/*!40000 ALTER TABLE `cboardcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkin`
--

DROP TABLE IF EXISTS `checkin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkin` (
  `check_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `check_date` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`check_id`),
  KEY `FKdcvw8ewu6co9o6crg2wt0xi9l` (`user_id`),
  CONSTRAINT `FKdcvw8ewu6co9o6crg2wt0xi9l` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkin`
--

LOCK TABLES `checkin` WRITE;
/*!40000 ALTER TABLE `checkin` DISABLE KEYS */;
INSERT INTO `checkin` VALUES (1,'2022-02-18 11:11:01.431485',3),(2,'2022-02-18 11:17:04.024889',2),(3,'2022-02-18 11:22:05.629191',6),(4,'2022-02-18 11:24:30.381876',7);
/*!40000 ALTER TABLE `checkin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkout`
--

DROP TABLE IF EXISTS `checkout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkout` (
  `checkout_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkout_date` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`checkout_id`),
  KEY `FK2t04bbekmv496a8dkcmqsknrr` (`user_id`),
  CONSTRAINT `FK2t04bbekmv496a8dkcmqsknrr` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkout`
--

LOCK TABLES `checkout` WRITE;
/*!40000 ALTER TABLE `checkout` DISABLE KEYS */;
INSERT INTO `checkout` VALUES (1,'2022-02-18 11:22:11.711700',6),(2,'2022-02-18 11:24:34.957223',7);
/*!40000 ALTER TABLE `checkout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commonboard`
--

DROP TABLE IF EXISTS `commonboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commonboard` (
  `cboard_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cboard_code` text,
  `cboard_content` text,
  `cboard_img` varchar(100) DEFAULT NULL,
  `cboard_techstack` varchar(100) DEFAULT NULL,
  `cboard_title` varchar(20) DEFAULT NULL,
  `cboard_type` varchar(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cboard_id`),
  KEY `FK7qm8avj2neu9hhte1ibtu16tn` (`user_id`),
  CONSTRAINT `FK7qm8avj2neu9hhte1ibtu16tn` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commonboard`
--

LOCK TABLES `commonboard` WRITE;
/*!40000 ALTER TABLE `commonboard` DISABLE KEYS */;
INSERT INTO `commonboard` VALUES (1,NULL,'개발자 너무 힘들어!!','#FFAB5E','','개발자 너무 힘들다!!','자유',5),(2,NULL,'주말에 뭐해?\n뭐하는게 좋을까?','#FFAB5E','','다들 주말에 뭐해?','자유',5),(3,'@GetMapping(\"/list/{cboardId}\")\n	ResponseEntity<List<CboardComment>> listComment(@PathVariable Long cboardId){\n		CommonBoard commonboard = commonboardService.findByBoardId(cboardId);\n		return ResponseEntity.ok(cboardcommentService.listComment(commonboard));\n	}\n	\n	@PostMapping(\"/regist/{cboardId}\")\n	public ResponseEntity<CboardComment> registComment(@RequestBody RegistCboardCommentDto dto, @PathVariable Long cboardId){\n		dto.setUser(userService.getMyUserWithAuthorities().get());\n		dto.setCommonboard(commonboardService.findByBoardId(cboardId));\n		return ResponseEntity.ok(cboardcommentService.registComment(dto));\n	}\n	\n	@PutMapping(\"/update/{cboardId}\")\n	public ResponseEntity<CboardComment> updateComment(@RequestBody UpdateCboardCommentDto dto, @PathVariable Long cboardId){\n		dto.setUser(userService.getMyUserWithAuthorities().get());\n		dto.setCommonboard(commonboardService.findByBoardId(cboardId));\n		return ResponseEntity.ok(cboardcommentService.updateComment(dto));\n	}\n	\n	@DeleteMapping(\"/delete/{ccommentId}\")\n	public void deleteComment(@PathVariable Long ccommentId) {\n		cboardcommentService.deleteComment(ccommentId);\n	}\n	','잘 짜여진 코드인지 확인해주시면 감사합니다.','#83E38C','java,spring','java 코드 맞는지 봐주세요!','질문',5),(4,'public class UserController {\n    private final UserService userService;\n \n    public UserController(UserService userService) {\n        this.userService = userService;\n    }\n \n    @PostMapping(\"/signup\")\n    public ResponseEntity<User> signup(\n            @RequestBody UserDto userDto\n    ) {\n        return ResponseEntity.ok(userService.signup(userDto));\n    }\n}','이거 맞나요? 잘 모르겠네염\n','#83E38C','java','이게 맞는건가요?','질문',2),(5,NULL,'여러분 고생하셨습니다.','#83E38C','','드디어 끝이 보이네요','자유',2),(6,'@GetMapping(\"/list/{cboardId}\")\n	ResponseEntity<List<CboardComment>> listComment(@PathVariable Long cboardId){\n		CommonBoard commonboard = commonboardService.findByBoardId(cboardId);\n		return ResponseEntity.ok(cboardcommentService.listComment(commonboard));\n	}\n	\n	@PostMapping(\"/regist/{cboardId}\")\n	public ResponseEntity<CboardComment> registComment(@RequestBody RegistCboardCommentDto dto, @PathVariable Long cboardId){\n		dto.setUser(userService.getMyUserWithAuthorities().get());\n		dto.setCommonboard(commonboardService.findByBoardId(cboardId));\n		return ResponseEntity.ok(cboardcommentService.registComment(dto));\n	}\n	\n	@PutMapping(\"/update/{cboardId}\")\n	public ResponseEntity<CboardComment> updateComment(@RequestBody UpdateCboardCommentDto dto, @PathVariable Long cboardId){\n		dto.setUser(userService.getMyUserWithAuthorities().get());\n		dto.setCommonboard(commonboardService.findByBoardId(cboardId));\n		return ResponseEntity.ok(cboardcommentService.updateComment(dto));\n	}\n	\n	@DeleteMapping(\"/delete/{ccommentId}\")\n	public void deleteComment(@PathVariable Long ccommentId) {\n		cboardcommentService.deleteComment(ccommentId);\n	}\n	','Spring 코드 에러가 생기는데 어디가 문제일까요?','#83E38C','spring','Spring에러 어디가 문제일까요?','질문',5),(7,NULL,'살려주세요!!!!!!!!!!!!!!!!!!','#FC8F8F','','후아후아','자유',1),(8,NULL,'보노보노야,,프로젝트를 끝내줘,,,','#FFAB5E','','보노보노야','자유',4),(10,'@GetMapping(\"/list/{cboardId}\")\n	ResponseEntity<List<CboardComment>> listComment(@PathVariable Long cboardId){\n		CommonBoard commonboard = commonboardService.findByBoardId(cboardId);\n		return ResponseEntity.ok(cboardcommentService.listComment(commonboard));\n	}\n	\n	@PostMapping(\"/regist/{cboardId}\")\n	public ResponseEntity<CboardComment> registComment(@RequestBody RegistCboardCommentDto dto, @PathVariable Long cboardId){\n		dto.setUser(userService.getMyUserWithAuthorities().get());\n		dto.setCommonboard(commonboardService.findByBoardId(cboardId));\n		return ResponseEntity.ok(cboardcommentService.registComment(dto));\n	}\n	\n	@PutMapping(\"/update/{cboardId}\")\n	public ResponseEntity<CboardComment> updateComment(@RequestBody UpdateCboardCommentDto dto, @PathVariable Long cboardId){\n		dto.setUser(userService.getMyUserWithAuthorities().get());\n		dto.setCommonboard(commonboardService.findByBoardId(cboardId));\n		return ResponseEntity.ok(cboardcommentService.updateComment(dto));\n	}\n	\n	@DeleteMapping(\"/delete/{ccommentId}\")\n	public void deleteComment(@PathVariable Long ccommentId) {\n		cboardcommentService.deleteComment(ccommentId);\n	}\n	','코드가 맞게 설정된건가요?','#FC8F8F','java','코드 질문입니다.','질문',5),(11,'const dummy = [\n  {\n    name: \'Mike\',\n    age: 30,\n    isAdult: true,\n  },','이렇게 작성해서 다른 파일에 import 해서 다운받으련느데 잘 안되네요\n어디가 잘못된 건가요','#FC8F8F','javascript','더미데이터 넘겨주기','질문',4);
/*!40000 ALTER TABLE `commonboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myroom`
--

DROP TABLE IF EXISTS `myroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myroom` (
  `myroom_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`myroom_id`),
  KEY `FKbxeg4vt2hq231shb3ynlkjrlt` (`room_id`),
  KEY `FKhgtbfxb85go0vnyhoad73kqfj` (`user_id`),
  CONSTRAINT `FKbxeg4vt2hq231shb3ynlkjrlt` FOREIGN KEY (`room_id`) REFERENCES `studyroom` (`room_id`) ON DELETE CASCADE,
  CONSTRAINT `FKhgtbfxb85go0vnyhoad73kqfj` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myroom`
--

LOCK TABLES `myroom` WRITE;
/*!40000 ALTER TABLE `myroom` DISABLE KEYS */;
INSERT INTO `myroom` VALUES (1,1,4),(2,2,2),(3,3,5),(4,3,3),(5,2,4),(6,2,3),(7,4,3),(8,2,5),(9,4,5),(10,1,5);
/*!40000 ALTER TABLE `myroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sboardcomment`
--

DROP TABLE IF EXISTS `sboardcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sboardcomment` (
  `scomment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scomment_content` varchar(50) DEFAULT NULL,
  `scomment_time` datetime(6) DEFAULT NULL,
  `sboard_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`scomment_id`),
  KEY `FKthsb75coyi3vlogmph3oyylof` (`sboard_id`),
  KEY `FK2pc6n6qeyoaxfo2hciei75opr` (`user_id`),
  CONSTRAINT `FK2pc6n6qeyoaxfo2hciei75opr` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `FKthsb75coyi3vlogmph3oyylof` FOREIGN KEY (`sboard_id`) REFERENCES `studyboard` (`sboard_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sboardcomment`
--

LOCK TABLES `sboardcomment` WRITE;
/*!40000 ALTER TABLE `sboardcomment` DISABLE KEYS */;
INSERT INTO `sboardcomment` VALUES (1,'ㅋㅋㅋㅋ','2022-02-18 10:29:24.269255',1,3),(2,'안녕하세요!','2022-02-18 10:43:57.508884',1,5),(3,'ㅌㅌㅌ','2022-02-18 10:44:51.787011',1,3);
/*!40000 ALTER TABLE `sboardcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studyboard`
--

DROP TABLE IF EXISTS `studyboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studyboard` (
  `sboard_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sboard_content1` text,
  `sboard_content2` text,
  `sboard_content3` text,
  `sboard_img` varchar(100) DEFAULT NULL,
  `sboard_ingdate` varchar(30) DEFAULT NULL,
  `sboard_joindate` varchar(30) DEFAULT NULL,
  `sboard_person` int(11) DEFAULT NULL,
  `sboard_techstack` varchar(100) DEFAULT NULL,
  `sboard_title` varchar(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sboard_id`),
  KEY `FKpk0g39cbbnwhantdablw50n75` (`user_id`),
  CONSTRAINT `FKpk0g39cbbnwhantdablw50n75` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studyboard`
--

LOCK TABLES `studyboard` WRITE;
/*!40000 ALTER TABLE `studyboard` DISABLE KEYS */;
INSERT INTO `studyboard` VALUES (1,'010-1234-5678','화상 미팅 서비스 제작하고 싶습니다\n지원해주세요','1. 워라벨 준수\n2. 지각 금지','#83E38C','2022-02-22 - 2022-03-22','2022-02-18 - 2022-02-21',4,'javascript,java,spring,vue','web rtc 스터디 모집',3),(2,'010-4534-3453','인공지능 공부하면서 Project 진행할 예정입니다.','하루 3끼 챙겨먹기\n하루 30분 스트레칭','#FC8F8F','2022-03-01 - 2022-03-05','2022-02-22 - 2022-02-26',4,'kotlin,flutter,swift','인공지능 Project 팀원 구해요!',5),(3,'010-6786-6786','스프링 함께 공부할 스터디원 구합니다.','하루 3끼 챙겨먹기\n하루 30분 스트레칭','#FC8F8F','2022-03-09 - 2022-03-23','2022-02-25 - 2022-02-27',4,'java,spring','스프링 스터디원 모집',5),(4,'010-6634-5435','파이썬으로 알고리즘 푸실 분 구합니다.','하루 3끼 챙겨먹기\n하루 30분 스트레칭','#83E38C','2022-03-15 - 2022-03-25','2022-02-24 - 2022-02-26',3,'python','Python 알고리즘 스터디 모집',5),(5,'010-2423-4243','장고로 함께 프로젝트 진행해봐요!','하루 3끼 챙겨먹기\n하루 30분 스트레칭','#83E38C','2022-03-15 - 2022-03-23','2022-02-24 - 2022-02-27',5,'django,python','Django 프로젝트원 모집합니다!',5),(6,'xxx-xxxx-xxxx','머신러닝 딥러닝 분야에 대해 공부할 예정입니다.\n관심은 있지만 시도하지 못하셨던 분들, 혼자 하기 벅찼던 분들 대 환영!\n같이 빅데이터 공부해서 취업합시다!','지각, 결석은 최대한 자제해주세요\n우리 모두 처음 공부하는 거니까 기죽지말기\n다른 사람 비방 금지!','#83E38C','2022-02-27 - 2022-03-26','2022-02-18 - 2022-02-21',4,'django,python','빅데이터 스터디',4),(7,'010-3563-3636','노트와 자바스크립트로 공부','하루 3끼 챙겨먹기\n하루 30분 스트레칭','#FFAB5E','2022-03-29 - 2022-03-31','2022-02-24 - 2022-02-26',3,'javascript,node','노드,자바스크립트 스터디 모집',5),(8,'xxx-xxxx-xxxx','python, js, vue, django 를 이용해서 서비스를 하나 만들고자 합니다!\n주제는 모여서 정해봅시다\n3월 한 달 동안 진행할 예정입니다','지각 결석 절대절대 금지!\n다른 기술 스텍 제안은 너무너무 환영\n다른 사람 비방은 절대 금지입니다','#FFAB5E','2022-03-01 - 2022-03-31','2022-02-18 - 2022-02-27',4,'javascript,django,vue,python','서비스 하나 만드실 분!',4);
/*!40000 ALTER TABLE `studyboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studyroom`
--

DROP TABLE IF EXISTS `studyroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studyroom` (
  `room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_content1` text,
  `room_content2` text,
  `room_img` varchar(100) DEFAULT NULL,
  `room_ingdate` varchar(30) DEFAULT NULL,
  `room_ingday` varchar(20) DEFAULT NULL,
  `room_person` int(11) DEFAULT NULL,
  `room_pwd` varchar(20) DEFAULT NULL,
  `room_techstack` varchar(100) DEFAULT NULL,
  `room_time` date DEFAULT NULL,
  `room_title` varchar(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FK9tfdk99yjosyy02kkfxow3pp0` (`user_id`),
  CONSTRAINT `FK9tfdk99yjosyy02kkfxow3pp0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studyroom`
--

LOCK TABLES `studyroom` WRITE;
/*!40000 ALTER TABLE `studyroom` DISABLE KEYS */;
INSERT INTO `studyroom` VALUES (1,'xxx-xxxx-xxxx','파이썬 알고리즘 스터디 모집합니다!\n화 목 토 주 3일 진행하고 시간은 모여서 합의하고 정할 예정입니다\n많이많이 지원해주세요','#FC8F8F','2022-02-22 - 2022-03-21','tue,thu,sat',4,'1234','python','2022-02-18','고리즘 스터디',4),(2,'010.1123.1212','리눅스 만지다 스트레스 받아서 공부하려고 합니다.\n매주 월, 화 진행합니다.','#83E38C','2022-03-01 - 2022-03-31','mon,tue',4,'1234','etc','2022-02-18','리눅스 스터디',2),(3,'010-5354-3543','Spring으로 공부하고 프로젝트를 진행하는 스터디입니다.','#FC8F8F','2022-03-09 - 2022-03-24','tue,thu',6,'1234','java,spring','2022-02-18','Spring 스터디',5),(4,'010-1234-5678','빅데이터를 이용해 분산 서비스를 제공하려고 합니다.\n하고싶은 분, 기술 스택이 맞는 분 들어오세요.','#83E38C','2022-02-22 - 2022-03-22','mon,tue,wed,thu,fri',6,'1234','javascript,django,vue,python','2022-02-18','빅데이터 분산 서비스',3);
/*!40000 ALTER TABLE `studyroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_activated` bit(1) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_img` varchar(150) DEFAULT NULL,
  `user_jobs` varchar(50) DEFAULT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `user_pwd` varchar(100) DEFAULT NULL,
  `user_techstack` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_j09k2v8lxofv2vecxu2hde9so` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '','admin','https://firebasestorage.googleapis.com/v0/b/dtod-image-upload.appspot.com/o/asd.png?alt=media&token=8ed6a40e-6abd-46ff-8111-207756a7f428','서버 개발자','admin','$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi','java,react,spring,vue'),(2,_binary '','ssafy@ssafy.com',NULL,'서버 개발자','ssafy최고','$2a$10$FsknVuRnb4jPyug7SW3tGOW/8RJwdqZdp.PsPts5auJXiJISypO/S','javascript,spring'),(3,_binary '','ssafy@hotsix.com','https://firebasestorage.googleapis.com/v0/b/dtod-image-upload.appspot.com/o/dongchul.png?alt=media&token=21be038d-26a3-4969-b2e2-7e8360faa28e','프론트엔드 엔지니어','동구리동동','$2a$10$pGZTRpcTVCogwJ8UmrKtkerJn1RoHeCvEu8O/ZERoh4qeGQ6i6Iaq','javascript,django,vue,python'),(4,_binary '','myid@myid.com','https://firebasestorage.googleapis.com/v0/b/dtod-image-upload.appspot.com/o/bono3.jpg?alt=media&token=13e1b39e-3180-4e0a-993a-9d699a01d240','프론트엔드 엔지니어','myid','$2a$10$u/x5EfnGYM9.L1zX3Jn7EOjif9rufp2OtEdTNpKWtdjfqYohsSxay','javascript,django,python'),(5,_binary '','ahn@naver.com','https://firebasestorage.googleapis.com/v0/b/dtod-image-upload.appspot.com/o/123.jpg?alt=media&token=3667f48f-7904-48f2-8fe5-0eaeb64c7490','백엔드 엔지니어','안정석짱','$2a$10$lBzOUorK9eotiX2xut655Ozo8P9iympS/wCO7SvRsuMRK7wA2Nfv2','spring,vue'),(6,_binary '','ahn1@naver.com','https://firebasestorage.googleapis.com/v0/b/dtod-image-upload.appspot.com/o/123.jpg?alt=media&token=992116b6-22af-4b52-80fb-95ccf82fe30f','백엔드 엔지니어','안정석2','$2a$10$wiguu2Xn1sy2mSE2HPmyJeO2795BTnLsNHC0xun6fD3/iwJYzRaP2','spring,python'),(7,_binary '','ahn2@naver.com','https://firebasestorage.googleapis.com/v0/b/dtod-image-upload.appspot.com/o/123.jpg?alt=media&token=ff2a0f42-e67e-4a84-8fa5-2bb447992b0d','백엔드 엔지니어','안정석e','$2a$10$fLWCoI8B8Pu1muLdKM2e2.URdN7og0MZ7mEua7JkBAwN5RXDRM5Wy','spring,python');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `FK6ktglpl5mjosa283rvken2py5` (`authority_name`),
  CONSTRAINT `FK6ktglpl5mjosa283rvken2py5` FOREIGN KEY (`authority_name`) REFERENCES `authority` (`authority_name`),
  CONSTRAINT `FKpqlsjpkybgos9w2svcri7j8xy` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES (1,'ROLE_ADMIN'),(1,'ROLE_USER'),(2,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER'),(6,'ROLE_USER'),(7,'ROLE_USER');
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 11:42:53
