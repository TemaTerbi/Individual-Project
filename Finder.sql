-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:8889
-- Время создания: Сен 20 2022 г., 16:15
-- Версия сервера: 5.7.34
-- Версия PHP: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `Finder`
--

-- --------------------------------------------------------

--
-- Структура таблицы `animal_type`
--

CREATE TABLE `animal_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `animal_type`
--

INSERT INTO `animal_type` (`id`, `name`) VALUES
(3, 'Кошка');

-- --------------------------------------------------------

--
-- Структура таблицы `breed_cats`
--

CREATE TABLE `breed_cats` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `breed_cats`
--

INSERT INTO `breed_cats` (`id`, `name`) VALUES
(3, 'Шотладнский');

-- --------------------------------------------------------

--
-- Структура таблицы `color`
--

CREATE TABLE `color` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `color`
--

INSERT INTO `color` (`id`, `name`) VALUES
(3, 'Зеленый');

-- --------------------------------------------------------

--
-- Структура таблицы `phone`
--

CREATE TABLE `phone` (
  `id` bigint(20) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `phone`
--

INSERT INTO `phone` (`id`, `phone`, `user_name`) VALUES
(1, '323233232', 'TeRb1');

-- --------------------------------------------------------

--
-- Структура таблицы `places`
--

CREATE TABLE `places` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `places`
--

INSERT INTO `places` (`id`, `name`) VALUES
(2, 'питер');

-- --------------------------------------------------------

--
-- Структура таблицы `posts`
--

CREATE TABLE `posts` (
  `id` bigint(20) NOT NULL,
  `date` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `animal_type_id` bigint(20) DEFAULT NULL,
  `breed_cats_id` bigint(20) DEFAULT NULL,
  `color_id` bigint(20) DEFAULT NULL,
  `places_id` bigint(20) DEFAULT NULL,
  `social_web_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `posts`
--

INSERT INTO `posts` (`id`, `date`, `description`, `name`, `phone`, `title`, `user_id`, `animal_type_id`, `breed_cats_id`, `color_id`, `places_id`, `social_web_id`) VALUES
(8, '32323232323232', 'sdsddsds', 'sddsdsddsd', '232323232', 'testdsdsd', NULL, 3, 3, 3, 2, 4),
(9, '343534353', 'sdsdsdsds', 'fsfsfsfsfsf', '3434343434', 'dsdsdsdsd', NULL, 3, 3, 3, 2, 4),
(10, '33535535355', 'dfdfdfffdfdfd', 'tdfdfdgdgdggd', '3535355353', 'fdfdfdfddfdf', NULL, 3, 3, 3, 2, 4),
(11, 'sdsddsdsdsd', 'dfdsdsds', 'fdsdsdsds', '32323232322', 'fdsdsds', NULL, 3, 3, 3, 2, 4),
(12, 'sdsdsdssdsdsd', 'sdsdsdddsdsdsd', 'dsdsdsdsddsdsds', '232323232323', 'dsdsdsdsdsdsd', '2', 3, 3, 3, 2, 4),
(13, 'sfsfsfsf', 'fwwfwfw', 'dsfsfsf', '42422424', 'wfwfwf', '1', 3, 3, 3, 2, 4),
(14, 'sdsddsdsdsd', 'ssdsdsdsddsds', 'ssdsdsddsd', '323233232', 'sdsdsdsdsdsd', '2', 3, 3, 3, 2, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `social_web`
--

CREATE TABLE `social_web` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `social_web`
--

INSERT INTO `social_web` (`id`, `name`) VALUES
(4, 'Одноклассники'),
(5, 'ddddd');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `active`, `password`, `username`) VALUES
(1, b'1', '$2a$08$Z6/el6mecBO3q5jNNxbGuuSu9xesOT0sDS1dZ9pFPJNS6RbprZe0a', '1'),
(2, b'1', '$2a$08$bI9yvlOuEzPnxckzRaF3cet/ayjJGn9YfYbeNLruazzp3uwozv5vG', 'TeRb1');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(1, 'USER'),
(2, 'USER'),
(2, 'ADMIN');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `animal_type`
--
ALTER TABLE `animal_type`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `breed_cats`
--
ALTER TABLE `breed_cats`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `phone`
--
ALTER TABLE `phone`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `places`
--
ALTER TABLE `places`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKohq98pny8yc9wcx9067sij7tb` (`animal_type_id`),
  ADD KEY `FKn168eaj6c4wfbkgn9ef85urem` (`breed_cats_id`),
  ADD KEY `FKcjgnfmvnmvenae10aikyeauh0` (`color_id`),
  ADD KEY `FKdeq4yx6hpyijefvce9cop46d3` (`places_id`),
  ADD KEY `FKhn6lc6lvp88bxw22c380l4y7k` (`social_web_id`);

--
-- Индексы таблицы `social_web`
--
ALTER TABLE `social_web`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `animal_type`
--
ALTER TABLE `animal_type`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `breed_cats`
--
ALTER TABLE `breed_cats`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `color`
--
ALTER TABLE `color`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `phone`
--
ALTER TABLE `phone`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `places`
--
ALTER TABLE `places`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `posts`
--
ALTER TABLE `posts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT для таблицы `social_web`
--
ALTER TABLE `social_web`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `FKcjgnfmvnmvenae10aikyeauh0` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  ADD CONSTRAINT `FKdeq4yx6hpyijefvce9cop46d3` FOREIGN KEY (`places_id`) REFERENCES `places` (`id`),
  ADD CONSTRAINT `FKhn6lc6lvp88bxw22c380l4y7k` FOREIGN KEY (`social_web_id`) REFERENCES `social_web` (`id`),
  ADD CONSTRAINT `FKn168eaj6c4wfbkgn9ef85urem` FOREIGN KEY (`breed_cats_id`) REFERENCES `breed_cats` (`id`),
  ADD CONSTRAINT `FKohq98pny8yc9wcx9067sij7tb` FOREIGN KEY (`animal_type_id`) REFERENCES `animal_type` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
