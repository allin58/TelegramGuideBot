token: 1221774799:AAFbvfEX_PI5gSmDrczZMW7_NZXva-TuYDg
username: GuideTBot
botname: GuideBot


Используется postgresql база. Версия java - 8.
Для работы бота требуется проинициализироавть базу данных начальными значениями.

CREATE DATABASE telegrambotdb;
\c telegrambotdb
CREATE TABLE city ( identity  int primary key, name varchar(50));
CREATE TABLE description ( 	identity int primary key, 	text  text,  city_id int, FOREIGN KEY (city_id ) REFERENCES city (identity) );
-------------------------------------------------------------------------------------------------------------------------
INSERT INTO city(identity, name)	VALUES (0, 'Мельбурн');
INSERT INTO city(identity, name)	VALUES (1, 'Москва');
INSERT INTO city(identity, name)	VALUES (2, 'Берлин');
INSERT INTO city(identity, name)	VALUES (3, 'Минск');
INSERT INTO city(identity, name)	VALUES (4, 'Амстердам');

INSERT INTO description(identity, text, city_id)	VALUES (0, 'Рекомендуется посетить Рынок королевы Виктории',0);
INSERT INTO description(identity, text, city_id)	VALUES (1, 'Не забудьте посетить Красную Площадь',1);
INSERT INTO description(identity, text, city_id)	VALUES (2, 'Стоит прогулятся возле Бранденбургских ворот',2);
INSERT INTO description(identity, text, city_id)	VALUES (3, 'Стоит обратить внимание на Национальный художественный музей',3);
INSERT INTO description(identity, text, city_id)	VALUES (4, 'Популярное место у туристов это Музей Ван Гога',4);

Команды INSERT выполнял через pgadmin, если делать через shell то могут возникнуть проблемы с кодировкой кирилицы.

Пароль и пользователь указанны в application.properties

Для взамодействия с REST WebService использовался postman(пример запроса находится в скриншоте)

