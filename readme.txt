token: 1221774799:AAFbvfEX_PI5gSmDrczZMW7_NZXva-TuYDg
username: GuideTBot
botname: GuideBot


������������ postgresql ����. ������ java - 8.
��� ������ ���� ��������� ������������������� ���� ������ ���������� ����������.

CREATE DATABASE telegrambotdb;
\c telegrambotdb
CREATE TABLE city ( identity  int primary key, name varchar(50));
CREATE TABLE description ( 	identity int primary key, 	text  text,  city_id int, FOREIGN KEY (city_id ) REFERENCES city (identity) );
-------------------------------------------------------------------------------------------------------------------------
INSERT INTO city(identity, name)	VALUES (0, '��������');
INSERT INTO city(identity, name)	VALUES (1, '������');
INSERT INTO city(identity, name)	VALUES (2, '������');
INSERT INTO city(identity, name)	VALUES (3, '�����');
INSERT INTO city(identity, name)	VALUES (4, '���������');

INSERT INTO description(identity, text, city_id)	VALUES (0, '������������� �������� ����� �������� ��������',0);
INSERT INTO description(identity, text, city_id)	VALUES (1, '�� �������� �������� ������� �������',1);
INSERT INTO description(identity, text, city_id)	VALUES (2, '����� ���������� ����� ��������������� �����',2);
INSERT INTO description(identity, text, city_id)	VALUES (3, '����� �������� �������� �� ������������ �������������� �����',3);
INSERT INTO description(identity, text, city_id)	VALUES (4, '���������� ����� � �������� ��� ����� ��� ����',4);

������� INSERT �������� ����� pgadmin, ���� ������ ����� shell �� ����� ���������� �������� � ���������� ��������.

������ � ������������ �������� � application.properties

��� ������������� � REST WebService ������������� postman(������ ������� ��������� � ���������)

