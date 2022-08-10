/*
 Navicat Premium Data Transfer

 Source Server         : wangkeke
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 11/08/2022 00:11:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('123', '123');
INSERT INTO `admin` VALUES ('888', '888');

-- ----------------------------
-- Table structure for grades
-- ----------------------------
DROP TABLE IF EXISTS `grades`;
CREATE TABLE `grades`  (
  `name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考生姓名',
  `grade` int NULL DEFAULT NULL COMMENT '考生成绩',
  `date` date NOT NULL COMMENT '考试日期',
  INDEX `name`(`name` ASC) USING BTREE,
  CONSTRAINT `更新成绩` FOREIGN KEY (`name`) REFERENCES `user` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of grades
-- ----------------------------
INSERT INTO `grades` VALUES ('王科可', 10, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 10, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('马化腾', 100, '2022-07-13');
INSERT INTO `grades` VALUES ('李四', 50, '2022-07-28');
INSERT INTO `grades` VALUES ('王军', 12, '2022-07-28');
INSERT INTO `grades` VALUES ('张三', 88, '2022-07-28');
INSERT INTO `grades` VALUES ('雷军', 89, '2022-07-26');
INSERT INTO `grades` VALUES ('雷军', 89, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 100, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 92, '2022-07-13');
INSERT INTO `grades` VALUES ('王科可', 95, '2022-07-13');
INSERT INTO `grades` VALUES ('马化腾', 100, '2022-07-28');
INSERT INTO `grades` VALUES ('李四', 45, '2022-07-13');
INSERT INTO `grades` VALUES ('王军', 78, '2022-07-13');
INSERT INTO `grades` VALUES ('张三', 99, '2022-07-13');
INSERT INTO `grades` VALUES ('雷军', 87, '2022-07-13');
INSERT INTO `grades` VALUES ('雷军', 89, '2022-07-28');
INSERT INTO `grades` VALUES ('张三', 12, '2022-07-26');
INSERT INTO `grades` VALUES ('刘钊', 33, '2022-07-26');
INSERT INTO `grades` VALUES ('吴兵', 34, '2022-07-26');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-30');
INSERT INTO `grades` VALUES ('大王', 0, '2022-07-30');
INSERT INTO `grades` VALUES ('王科可', 5, '2022-07-30');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-30');

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `question` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题',
  `answer` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '答案',
  `score` int NULL DEFAULT NULL COMMENT '单题分数',
  `time` int NULL DEFAULT NULL COMMENT '时间以分为单位。只在第一行数据设置即可'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES ('编译Java Application 源程序文件将产生相应的字节码文件，这些字节码文件的扩展名为(   )。\r\nA. java			B. class\r\nC. html			D. exe', 'B', 5, 11);
INSERT INTO `questions` VALUES ('不允许作为类及类成员的访问控制符的是(  )。\r\nA. public			B. private\r\nC. static			D. protected', 'C', 5, NULL);
INSERT INTO `questions` VALUES ('下列有关类、对象和实例的叙述，正确的是（  ）\nA.类就是对象，对象就是类，实例是对象的另一个名称，三者没有差别\nB.类是对象的抽象，对象是类的具体化，实例是对象的另一个名称\nC.对象是类的抽象，类是对象的具体化，实例是对象的另一个名称\nD.类是对象的抽象，对象是类的具体化，实例是类的另一个名称', 'B', 5, NULL);
INSERT INTO `questions` VALUES ('下列有关父类属性和方法继承规则的描述错误的是（  ）。\nA.父类中private修饰的属性和方法在子类中不被继承\nB.父类中public修饰的属性和方法在子类中被继承且可访问\nC.父类中protected修饰的属性和方法在子类中被继承且可访问\nD.父类中default修饰的属性和方法在之类中被继承，若父类和子类在同一个包中，则也可访问\r\n', 'A', 5, NULL);
INSERT INTO `questions` VALUES ('关于继承的说法正确的是：（　）\r\nA、子类将继承父类所有的属性和方法。\r\nB、子类将继承父类的非私有属性和方法。\r\nC、子类只继承父类public方法和属性\r\nD、子类只继承父类的方法，而不继承属性', 'B', 5, NULL);
INSERT INTO `questions` VALUES ('请说出下列代码的执行结果 : ( )\nString s = “abcd”;\nString s1 = new String(s);\nif (s = = s1) System.out.println(“the same”);\nif (s.equals(s1)) System.out.println(“equals”);\nA. the same equals B. equals\nC. the same D. 什么结果都不输出', 'B', 5, NULL);
INSERT INTO `questions` VALUES ('字符流与字节流的区别在于（　　）\nA．前者带有缓冲，后者没有\nB．前者是块读写，后者是字节读写\nC. 二者没有区别，可以互换使用\nD. 每次读写的字节数不同', 'D', 5, NULL);
INSERT INTO `questions` VALUES ('不可以用static来修饰的是（ ）\n A.属性\n B.方法\n C.代码块\n D.对象', 'D', 5, NULL);
INSERT INTO `questions` VALUES ('下面哪个不是JAVA 关键字( )\n  A.integer\n  B.double\n  C.float\n  D.default', 'A', 5, NULL);
INSERT INTO `questions` VALUES ('运算符优先级别排序正确的是（）\n\nA、 由高向低分别是：()、!、算术运算符、关系运算符、逻辑运算符、赋值运算符；\n\nB、 由高向低分别是：()、关系运算符、算术运算符、赋值运算符、!、逻辑运算符；\n\nC、 由高向低分别是：()、算术运算符、逻辑运算符、关系运算符、!、赋值运算符；\n\nD、 由高向低分别是：()、!、关系运算符、赋值运算符、算术运算符、逻辑运算符；', 'A', 5, NULL);
INSERT INTO `questions` VALUES ('下面有关for循环的描述正确的是（）\n\nA、 for循环体语句中，可以包含多条语句，但要用大括号括起来\n\nB、 for循环只能用于循环次数已经确定的情况\n\nC、 在for循环中，不能使用break语句跳出循环\n\nD、 for循环是先执行循环体语句，后进行条件判断', 'A', 5, NULL);
INSERT INTO `questions` VALUES ('对象的特征在类中表示为变量，称为类的（）。\n\nA、 对象\n\nB、 属性\n\nC、 方法\n\nD、 数据类型', 'B', 5, NULL);
INSERT INTO `questions` VALUES ('在java中下列关于自动类型转换说法正确的是（）\n\nA、 基本数据类型和String相加结果一定是字符串型\n\nB、 char类型和int类型相加结果一定是字符\n\nC、 double类型可以自动转换为int\n\nD、 char + int + double +\"\" 结果一定是double；', 'A', 5, NULL);
INSERT INTO `questions` VALUES ('在Java中,关于构造方法，下列说法错误的是( )\n\nA、 构造方法的名称必须与类名相同\n\nB、 构造方法可以带参数\n\nC、 构造方法不可以重载\n\nD、 构造方法绝对不能有返回值', 'C', 5, NULL);
INSERT INTO `questions` VALUES ('在JAVA中，以下（ ）类的对象以键-值的方式存储对象\n\nA、 java.util.List\n\nB、 java.util.ArrayList\n\nC、 java.util.HashMap\n\nD、 java.util.LinkedList', 'C', 5, NULL);
INSERT INTO `questions` VALUES ('在Java语言中，下列关于类的继承的描述，正确的是（）。\n\nA、 一个类可以继承多个父类\n\nB、 一个类可以具有多个子类\n\nC、 子类可以使用父类的所有方法\n\nD、 子类一定比父类有更多的成员方法', 'B', 5, NULL);
INSERT INTO `questions` VALUES ('下列选项中关于Java中封装的说法错误的是（）。\n\nA、 封装就是将属性私有化，提供共有的方法访问私有属性\n\nB、 属性的访问方法包括setter方法和getter方法\n\nC、 setter方法用于赋值，getter方法用于取值\n\nD、 包含属性的类都必须封装属性，否则无法通过编译', 'D', 5, NULL);
INSERT INTO `questions` VALUES ('分析选项中关于Java中this关键字的说法正确的是（）\n\nA、 this关键字是在对象内部指代自身的引用\n\nB、 this关键字可以在类中的任何位置使用\n\nC、 this关键字和类关联，而不是和特定的对象关联\n\nD、 同一个类的不同对象共用一个this', 'A', 5, NULL);
INSERT INTO `questions` VALUES ('以下关于布局的说法，错误的是（）\n\nA、 BorderLayout是边框布局，它是窗体的默认布局\n\nB、 null是空布局，它是面板的默认布局\n\nC、 FlowLayout是流布局，这种布局将其中的组件按照加入的先后顺序从左向右排列， 一行排满之后就转到下一行继续从左至右排列\n\nD、 GridLayout是网格布局，它以矩形网格形式对容器的组件进行布置。容器被分成大小相等的矩形，一个矩形中放置一个组件', 'B', 5, NULL);
INSERT INTO `questions` VALUES ('313', 'A', 31, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`, `name`) USING BTREE,
  INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '王科可', '1');
INSERT INTO `user` VALUES ('202201', '马化腾', '12345');
INSERT INTO `user` VALUES ('202202', '马云', '888');
INSERT INTO `user` VALUES ('202203', '王军', '666666');
INSERT INTO `user` VALUES ('202204', '小明', '123456');
INSERT INTO `user` VALUES ('202205', '张三', '7878');
INSERT INTO `user` VALUES ('202206', '雷军', '12345678');
INSERT INTO `user` VALUES ('202208', '刘钊', '2');
INSERT INTO `user` VALUES ('202209', '吴兵', '242');
INSERT INTO `user` VALUES ('202210', '吴军', '33333333');
INSERT INTO `user` VALUES ('202211', '李四', 'lisi4');
INSERT INTO `user` VALUES ('202212', '王五', '55555');
INSERT INTO `user` VALUES ('202213', '赵天', '0775833');
INSERT INTO `user` VALUES ('202214', '老六', '63636363');
INSERT INTO `user` VALUES ('202215', '刘强', '78787788');
INSERT INTO `user` VALUES ('202216', '黄磊', '88888888');
INSERT INTO `user` VALUES ('202217', '吴爱玲', '00000000');
INSERT INTO `user` VALUES ('202218', '黄敏', '2312');
INSERT INTO `user` VALUES ('202219', '罗辑', '1999');
INSERT INTO `user` VALUES ('20225588', '大王', '123');
INSERT INTO `user` VALUES ('202288', '程鑫', '12322222');

-- ----------------------------
-- Triggers structure for table grades
-- ----------------------------
DROP TRIGGER IF EXISTS `TimeInsert`;
delimiter ;;
CREATE TRIGGER `TimeInsert` BEFORE INSERT ON `grades` FOR EACH ROW set new.DATE = NOW()
;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
