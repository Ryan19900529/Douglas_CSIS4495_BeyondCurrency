-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 07, 2024 at 02:15 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `beyond_currency_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `applications`
--

CREATE TABLE `applications` (
  `application_id` int(11) NOT NULL,
  `applicant_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `poster_id` int(11) NOT NULL,
  `status` varchar(30) NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`) VALUES
(0, 'null'),
(1, 'General Furniture Assembly'),
(2, 'Electrical Appliances Assembly'),
(3, 'General Mounting'),
(4, 'TV Mounting'),
(5, 'Help Moving'),
(6, 'Trash & Furniture Removal'),
(7, 'Heavy Lifting & Loading'),
(8, 'Kitchen Cleaning'),
(9, 'Bathroom Cleaning'),
(10, 'Yard Work'),
(11, 'Lawn Care'),
(12, 'Snow Removal'),
(13, 'Electrical Help'),
(14, 'Plumbing Help'),
(15, 'Minor Home Repairs'),
(16, 'Light Carpentry'),
(17, 'Indoor Painting'),
(18, 'Outdoor Painting');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `notification_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `sender_img` varchar(255) NOT NULL,
  `service_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `show_notification` tinyint(1) NOT NULL DEFAULT 1,
  `new_notification` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `saved_talent`
--

CREATE TABLE `saved_talent` (
  `saved_talent_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `talent_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `saved_talent`
--

INSERT INTO `saved_talent` (`saved_talent_id`, `user_id`, `talent_id`) VALUES
(15, 1, 4),
(19, 1, 7),
(20, 1, 8),
(21, 1, 9),
(22, 1, 10),
(23, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `service_id` int(11) NOT NULL,
  `service_title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'open',
  `create_date` date NOT NULL DEFAULT current_timestamp(),
  `deadline` date DEFAULT NULL,
  `poster_id` int(11) NOT NULL,
  `taker_id` int(11) DEFAULT NULL,
  `exchange_service` varchar(50) DEFAULT NULL,
  `comment_to_taker` varchar(500) DEFAULT NULL,
  `comment_to_poster` varchar(500) DEFAULT NULL,
  `rate_to_taker` int(10) DEFAULT NULL,
  `rate_to_poster` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`service_id`, `service_title`, `description`, `image_url`, `category_id`, `status`, `create_date`, `deadline`, `poster_id`, `taker_id`, `exchange_service`, `comment_to_taker`, `comment_to_poster`, `rate_to_taker`, `rate_to_poster`) VALUES
(1, 'Seeking Help for General Furniture Assembly', 'Hello there! I\'m on the lookout for some assistance with a general furniture assembly project and I\'m hoping you could lend a hand. If you\'re someone who\'s handy with tools or simply enjoys the satisfaction of putting things together, your expertise would be invaluable to me.\r\nThe plan is to tackle this project over the weekend, starting [specific date and time], and your assistance would not only make the task more enjoyable but also significantly more efficient. I\'ve already gathered all the necessary tools and equipment, along with the furniture pieces waiting to be assembled. All we need now is a bit of teamwork to turn these parts into functional pieces of furniture.\r\nWhether you\'re available for just a few hours or the entire day, any help you can offer would be greatly appreciated. Together, we can breeze through this project and have everything assembled in no time. And don\'t worry, I\'ll make sure there are plenty of snacks and beverages to keep us fueled and energized throughout the day.\r\nIf you\'re interested and available to lend a hand, please let me know at your earliest convenience so we can coordinate the details. Your contribution to this project would make a world of difference, and I can\'t thank you enough in advance for your willingness to help out. Let\'s make this furniture assembly experience a memorable and successful one together!', '/img/service1.jpg', 1, 'open', '2024-04-05', '2024-03-30', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'Seeking Help for General Furniture Assembly', 'Hello there! I\'m on the lookout for some assistance with a general furniture assembly project and I\'m hoping you could lend a hand. If you\'re someone who\'s handy with tools or simply enjoys the satisfaction of putting things together, your expertise would be invaluable to me.\r\nThe plan is to tackle this project over the weekend, starting [specific date and time], and your assistance would not only make the task more enjoyable but also significantly more efficient. I\'ve already gathered all the necessary tools and equipment, along with the furniture pieces waiting to be assembled. All we need now is a bit of teamwork to turn these parts into functional pieces of furniture.\r\nWhether you\'re available for just a few hours or the entire day, any help you can offer would be greatly appreciated. Together, we can breeze through this project and have everything assembled in no time. And don\'t worry, I\'ll make sure there are plenty of snacks and beverages to keep us fueled and energized throughout the day.\r\nIf you\'re interested and available to lend a hand, please let me know at your earliest convenience so we can coordinate the details. Your contribution to this project would make a world of difference, and I can\'t thank you enough in advance for your willingness to help out. Let\'s make this furniture assembly experience a memorable and successful one together!', '/img/service2.jpg', 1, 'open', '2024-04-05', '2024-03-30', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'Seeking Help for General Furniture Assembly', 'Hello there! I\'m on the lookout for some assistance with a general furniture assembly project and I\'m hoping you could lend a hand. If you\'re someone who\'s handy with tools or simply enjoys the satisfaction of putting things together, your expertise would be invaluable to me.\r\nThe plan is to tackle this project over the weekend, starting [specific date and time], and your assistance would not only make the task more enjoyable but also significantly more efficient. I\'ve already gathered all the necessary tools and equipment, along with the furniture pieces waiting to be assembled. All we need now is a bit of teamwork to turn these parts into functional pieces of furniture.\r\nWhether you\'re available for just a few hours or the entire day, any help you can offer would be greatly appreciated. Together, we can breeze through this project and have everything assembled in no time. And don\'t worry, I\'ll make sure there are plenty of snacks and beverages to keep us fueled and energized throughout the day.\r\nIf you\'re interested and available to lend a hand, please let me know at your earliest convenience so we can coordinate the details. Your contribution to this project would make a world of difference, and I can\'t thank you enough in advance for your willingness to help out. Let\'s make this furniture assembly experience a memorable and successful one together!', '/img/service3.jpg', 1, 'open', '2024-04-05', '2024-03-30', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'Seeking Help for General Furniture Assembly', 'Hello there! I\'m on the lookout for some assistance with a general furniture assembly project and I\'m hoping you could lend a hand. If you\'re someone who\'s handy with tools or simply enjoys the satisfaction of putting things together, your expertise would be invaluable to me.\r\nThe plan is to tackle this project over the weekend, starting [specific date and time], and your assistance would not only make the task more enjoyable but also significantly more efficient. I\'ve already gathered all the necessary tools and equipment, along with the furniture pieces waiting to be assembled. All we need now is a bit of teamwork to turn these parts into functional pieces of furniture.\r\nWhether you\'re available for just a few hours or the entire day, any help you can offer would be greatly appreciated. Together, we can breeze through this project and have everything assembled in no time. And don\'t worry, I\'ll make sure there are plenty of snacks and beverages to keep us fueled and energized throughout the day.\r\nIf you\'re interested and available to lend a hand, please let me know at your earliest convenience so we can coordinate the details. Your contribution to this project would make a world of difference, and I can\'t thank you enough in advance for your willingness to help out. Let\'s make this furniture assembly experience a memorable and successful one together!', '/img/service4.jpg', 1, 'open', '2024-04-05', '2024-03-30', 2, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'Seeking Help for General Furniture Assembly', 'Hello there! I\'m on the lookout for some assistance with a general furniture assembly project and I\'m hoping you could lend a hand. If you\'re someone who\'s handy with tools or simply enjoys the satisfaction of putting things together, your expertise would be invaluable to me.\r\nThe plan is to tackle this project over the weekend, starting [specific date and time], and your assistance would not only make the task more enjoyable but also significantly more efficient. I\'ve already gathered all the necessary tools and equipment, along with the furniture pieces waiting to be assembled. All we need now is a bit of teamwork to turn these parts into functional pieces of furniture.\r\nWhether you\'re available for just a few hours or the entire day, any help you can offer would be greatly appreciated. Together, we can breeze through this project and have everything assembled in no time. And don\'t worry, I\'ll make sure there are plenty of snacks and beverages to keep us fueled and energized throughout the day.\r\nIf you\'re interested and available to lend a hand, please let me know at your earliest convenience so we can coordinate the details. Your contribution to this project would make a world of difference, and I can\'t thank you enough in advance for your willingness to help out. Let\'s make this furniture assembly experience a memorable and successful one together!', '/img/service5.jpg', 1, 'open', '2024-04-05', '2024-03-30', 2, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'Seeking Help for General Furniture Assembly', 'Hello there! I\'m on the lookout for some assistance with a general furniture assembly project and I\'m hoping you could lend a hand. If you\'re someone who\'s handy with tools or simply enjoys the satisfaction of putting things together, your expertise would be invaluable to me.\r\nThe plan is to tackle this project over the weekend, starting [specific date and time], and your assistance would not only make the task more enjoyable but also significantly more efficient. I\'ve already gathered all the necessary tools and equipment, along with the furniture pieces waiting to be assembled. All we need now is a bit of teamwork to turn these parts into functional pieces of furniture.\r\nWhether you\'re available for just a few hours or the entire day, any help you can offer would be greatly appreciated. Together, we can breeze through this project and have everything assembled in no time. And don\'t worry, I\'ll make sure there are plenty of snacks and beverages to keep us fueled and energized throughout the day.\r\nIf you\'re interested and available to lend a hand, please let me know at your earliest convenience so we can coordinate the details. Your contribution to this project would make a world of difference, and I can\'t thank you enough in advance for your willingness to help out. Let\'s make this furniture assembly experience a memorable and successful one together!', '/img/service6.jpg', 1, 'open', '2024-04-05', '2024-03-30', 2, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `language_1` varchar(20) NOT NULL,
  `language_2` varchar(20) DEFAULT NULL,
  `sign_up_date` date NOT NULL DEFAULT current_timestamp(),
  `website_url` varchar(200) DEFAULT NULL,
  `image_url` varchar(500) DEFAULT '/img/default.jpg',
  `skill_1` varchar(50) NOT NULL,
  `category_1_id` int(11) DEFAULT NULL,
  `skill_2` varchar(50) DEFAULT NULL,
  `category_2_id` int(11) DEFAULT NULL,
  `skill_3` varchar(50) DEFAULT NULL,
  `category_3_id` int(11) DEFAULT NULL,
  `trust_score` int(3) NOT NULL DEFAULT 60,
  `new_notification` tinyint(1) NOT NULL DEFAULT 0,
  `title` varchar(255) DEFAULT NULL,
  `about` text DEFAULT NULL,
  `work_done` int(5) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `password`, `phone`, `date_of_birth`, `email`, `language_1`, `language_2`, `sign_up_date`, `website_url`, `image_url`, `skill_1`, `category_1_id`, `skill_2`, `category_2_id`, `skill_3`, `category_3_id`, `trust_score`, `new_notification`, `title`, `about`, `work_done`) VALUES
(1, 'Matteo', 'Grasso', '1', '1234567890', '1990-01-01', '1@example.com', 'English', 'Italian', '2024-04-05', 'http://www.matteograsso.com', '/img/user1.jpg', 'General Mounting', 3, 'Kitchen Cleaning', 8, 'Lawn Care', 11, 90, 1, 'Precision Pro | Mounting MavenHandy Michael | General Furniture Assembly Expert', 'Meet Matteo Grasso, your all-in-one solution for home improvement needs! As a Mounting Maestro, I bring precision and expertise to every installation project. From mounting shelves and artwork to assembling furniture, I ensure that your belongings are securely in place.\r\nBut that\'s not all—I\'m also a Cleaning Connoisseur, dedicated to creating pristine and organized spaces. Whether it\'s a thorough deep clean or regular maintenance, I take care of every nook and cranny, leaving your home spotless. As a Landscape Lifter, I transform outdoor spaces into beautiful havens. From garden maintenance and lawn care to designing and implementing landscaping projects, I bring a green thumb and creative flair to enhance your property\'s curb appeal.\r\nWith years of experience and a passion for perfection, I\'ve earned the title of Task Titan. Your satisfaction is my priority, and I pride myself on delivering top-notch service in mounting, cleaning, and landscaping. Choose Matteo Grasso for a reliable and skilled partner in elevating your home environment.', 0),
(2, 'Elena', 'Romano', '2', '9876543210', '1992-02-02', '2@example.com', 'English', 'Spanish', '2024-04-05', 'http://www.elenaromano.com', '/img/user2.jpg', 'General Mounting', 3, 'Bathroom Cleaning', 9, 'Lawn Care', 11, 75, 1, 'Green Tasker | Mounting MagicianHandy Elena | Electrical Appliances Assembly Expert', 'Meet Elena Romano, your go-to expert for home maintenance and improvement projects! As a Mounting Maven, I excel in installing shelves, TVs, and more with precision and care.\r\n     I\'m also a Cleaning Specialist, committed to making your home sparkle with regular upkeep and deep-cleaning services. Additionally, I\'m a Lawn Lover, dedicated to keeping your outdoor spaces lush and vibrant.\r\nWith a keen eye for detail and a passion for customer satisfaction, I deliver reliable and high-quality service every time.', 7),
(3, 'John', 'Doe', '3', '1234567890', '1990-03-03', '3@example.com', 'English', 'French', '2024-04-05', 'http://www.johndoe.com', '/img/user3.jpg', 'General Furniture Assembly', 1, 'Kitchen Cleaning', 8, 'Lawn Care', 11, 90, 1, 'Handy Helper | General Mounting ExpertHandy John | General Mounting Expert', 'Meet John Doe, your friendly neighborhood handyman and cleaning expert! With years of experience in general handyman tasks, I tackle everything from minor repairs to major renovations with skill and efficiency.\r\nAs a Cleaning Pro, I leave no corner untouched, ensuring that your home is fresh and immaculate. Plus, as a Landscape Enthusiast, I take pride in enhancing outdoor spaces with beautiful gardens and well-maintained lawns.\r\nWhether you need help around the house or in the yard, count on John Doe to get the job done right.', 23),
(4, 'Anna', 'Smith', '4', '9876543210', '1992-04-04', '4@example.com', 'English', 'German', '2024-04-05', 'http://www.annasmith.com', '/img/user4.jpg', 'General Furniture Assembly', 1, 'Bathroom Cleaning', 9, 'Lawn Care', 11, 75, 1, 'Household Hero | General Mounting ExpertHandy Oliver | TV Mounting Expert', 'Meet Anna Smith, your household hero for all things home-related! As a Home Helper, I provide assistance with household tasks ranging from repairs and maintenance to cleaning and organization. With a knack for tidiness and attention to detail, I ensure that your living spaces are comfortable and inviting.\r\nAdditionally, I\'m a Cleaning Guru, specializing in deep cleans and regular upkeep to keep your home looking its best. And when it comes to the outdoors, I\'m a Garden Guru, bringing beauty and harmony to your garden oasis. Choose Anna Smith for reliable and trustworthy service that exceeds your expectations.', 9),
(5, 'Michael', 'Johnson', '5', '1234567890', '1990-05-05', '5@example.com', 'English', 'Chinese', '2024-04-05', 'http://www.michaeljohnson.com', '/img/user5.jpg', 'General Furniture Assembly', 1, 'Kitchen Cleaning', 8, 'Lawn Care', 11, 90, 1, 'Handy Michael | General Furniture Assembly ExpertHandy Michael | Help Moving Expert', 'Meet Michael Johnson, your go-to guy for home repairs and cleaning projects! As a Fix-It Master, I have the skills and expertise to tackle any repair job, big or small. From plumbing and electrical work to carpentry and painting, I handle it all with precision and care.\r\nAs a Spotless Cleaner, I take pride in leaving your home sparkling clean and fresh. Whether it\'s a regular cleaning service or a deep clean, you can count on me for impeccable results. And when it\'s time to spruce up your outdoor spaces, I\'m your Outdoor Expert, ready to transform your yard into a beautiful retreat.\r\nChoose Handy Michael for reliable service and exceptional results every time.', 30),
(6, 'Sophia', 'Brown', '6', '9876543210', '1992-06-06', '6@example.com', 'English', 'Italian', '2024-04-05', 'http://www.sophiabrown.com', '/img/user6.jpg', 'General Furniture Assembly', 1, 'Bathroom Cleaning', 9, 'Yard Work', 10, 95, 1, 'Handy Sophia | General Furniture Assembly SpecialistHandy Sophia | Trash & Furniture Removal Expert', 'Meet Sophia Brown, your friendly neighborhood handywoman and cleaning wizard! As a Home Handywoman, I specialize in all things home repair and maintenance. From fixing leaky faucets to installing shelves, I\'m here to help with all your household needs. Plus, as a Cleaning Wizard, I make dirt and grime disappear with my top-notch cleaning services.\r\nWhether it\'s a quick tidy-up or a deep clean, your home will shine when I\'m on the job. And when it comes to outdoor spaces, I\'ve got a green thumb and a passion for gardening. Choose Handy Sophia for reliable service and a job well done every time.', 20),
(7, 'William', 'Jones', '7', '1234567890', '1990-07-07', '7@example.com', 'English', 'French', '2024-04-05', 'http://www.williamjones.com', '/img/user7.jpg', 'General Furniture Assembly', 1, 'Kitchen Cleaning', 8, 'Yard Work', 10, 70, 1, 'Will the Handyman | General Furniture Assembly SpecialistHandy William | Help Moving Specialist', 'Meet William Jones, your versatile handyman and cleaning expert! With a wide range of skills and experience, I tackle everything from home repairs to deep cleaning with ease.\r\nAs a Jack-of-All-Trades, I\'m your go-to guy for any task around the house. From fixing squeaky doors to repairing drywall, I\'ve got you covered. Plus, as a Cleaning Expert, I ensure that your home is spotless and inviting. Whether it\'s a weekly clean or a one-time deep clean, I deliver exceptional results every time.\r\nAnd when it\'s time to tackle outdoor projects, I\'m your Outdoor Enthusiast, ready to transform your yard into a beautiful oasis. Choose Will the Handyman for reliable service and outstanding craftsmanship.', 8),
(8, 'Emma', 'Davis', '8', '9876543210', '1992-08-08', '8@example.com', 'English', 'German', '2024-04-05', 'http://www.emmadavis.com', '/img/user8.jpg', 'General Furniture Assembly', 1, 'Bathroom Cleaning', 9, 'Lawn Care', 11, 75, 1, 'Emma the Handywoman | General Furniture Assembly SpecialistHandy Emma | General Mounting Specialist', 'Meet Emma Davis, your dedicated handywoman and cleaning specialist! As a Home Helper, I provide assistance with all your household needs, from repairs and installations to cleaning and organizing. With attention to detail and a passion for perfection, I ensure that your home is in tip-top shape.\r\nPlus, as a Cleaning Specialist, I tackle dirt and grime with ease, leaving your home fresh and immaculate. And when it comes to outdoor spaces, I\'m a Gardening Guru, bringing beauty and serenity to your garden oasis. Choose Emma the Handywoman for reliable service and exceptional results that exceed your expectations.', 5),
(9, 'Oliver', 'Martinez', '9', '1234567890', '1990-09-09', '9@example.com', 'English', 'French', '2024-04-05', 'http://www.olivermartinez.com', '/img/user9.jpg', 'General Furniture Assembly', 1, 'Kitchen Cleaning', 8, 'Lawn Care', 11, 80, 1, 'Oliver the Handyman | General Furniture Assembly SpecialistHandy Oliver | Kitchen Cleaning Expert', 'Meet Oliver Martinez, your trusted home repair expert and cleaning pro! With years of experience in home maintenance and repair, I have the skills and knowledge to handle any job with precision and care. Whether it\'s fixing leaky faucets, repairing drywall, or installing new fixtures, I get the job done right the first time. Plus, as a Cleaning Pro, I leave your home sparkling clean and fresh, ensuring that every corner is spotless. And when it comes to outdoor projects, I\'m your go-to guy for landscaping and yard maintenance. Choose Oliver the Handyman for reliable service and exceptional results that you can count on.', 20),
(10, 'Isabella', 'Garcia', '10', '9876543210', '1992-10-10', '10@example.com', 'English', 'Spanish', '2024-04-05', 'http://www.isabellagarcia.com', '/img/user10.jpg', 'General Furniture Assembly', 1, 'Bathroom Cleaning', 9, 'Lawn Care', 11, 75, 1, 'Isabella the Handywoman | General Furniture Assembly SpecialistHandy Isabella | Bathroom Cleaning Expert', 'Meet Isabella Garcia, your go-to handywoman for all your home maintenance needs! As a Home Maintenance Guru, I specialize in repairs, installations, and general upkeep to keep your home in top condition. Whether it\'s fixing a leaky faucet, installing new fixtures, or repairing drywall, I have the skills and expertise to handle it all.\r\nPlus, as a Cleaning Whiz, I ensure that your home is clean and inviting, with every surface spotless and fresh. And when it comes to outdoor spaces, I\'m a Garden Enthusiast, ready to transform your yard into a beautiful oasis.\r\nChoose Isabella the Handywoman for reliable service and exceptional results that exceed your expectations.', 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`application_id`),
  ADD KEY `applicant` (`applicant_id`),
  ADD KEY `poster` (`poster_id`),
  ADD KEY `service` (`service_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`notification_id`),
  ADD KEY `receiver` (`user_id`);

--
-- Indexes for table `saved_talent`
--
ALTER TABLE `saved_talent`
  ADD PRIMARY KEY (`saved_talent_id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`service_id`),
  ADD KEY `user_service` (`poster_id`),
  ADD KEY `category_service` (`category_id`),
  ADD KEY `taker_id` (`taker_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `skill1_category` (`category_1_id`),
  ADD KEY `skill2_category` (`category_2_id`),
  ADD KEY `skill3_category` (`category_3_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `applications`
--
ALTER TABLE `applications`
  MODIFY `application_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `saved_talent`
--
ALTER TABLE `saved_talent`
  MODIFY `saved_talent_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `applications`
--
ALTER TABLE `applications`
  ADD CONSTRAINT `applicant` FOREIGN KEY (`applicant_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `poster` FOREIGN KEY (`poster_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `service` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`);

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `receiver` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `services`
--
ALTER TABLE `services`
  ADD CONSTRAINT `category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `taker` FOREIGN KEY (`taker_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user` FOREIGN KEY (`poster_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `skill1_category` FOREIGN KEY (`category_1_id`) REFERENCES `categories` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `skill2_category` FOREIGN KEY (`category_2_id`) REFERENCES `categories` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `skill3_category` FOREIGN KEY (`category_3_id`) REFERENCES `categories` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
