insert into user_lab(username, name, second_name, email, password)
values('joanra', 'Joan Ramon', 'Roca', 'joanra@gmail.com', '{bcrypt}$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');

insert into user_lab(username, name, second_name, email, password)
values('tina', 'Cristina', 'Garcia', 'tina@gmail.com', '{bcrypt}$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');

insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Lleida-Pirineus');
insert into station(latitud, longitud, name) values('41.654221', '0.685937', 'Alcoletge');
insert into station(latitud, longitud, name) values('41.687383', '0.72789', 'Vilanova de la Barca');
insert into station(latitud, longitud, name) values('41.716451', '0.76295', 'Térmens');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Vallfogona de Balaguer');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Balaguer');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Gerb');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Sant Llorenç de Montgai');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Vilanova de la Sal');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Santa Linya');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Àger');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Cellers-Llimiana');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Guàrdia de Tremp');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Palau de Noguera');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Tremp');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'Salàs de Pallars');
insert into station(latitud, longitud, name) values('41.65434', '0.685766', 'La Pobla de Segur');


insert into journey(origin_name, destination_name) values('Lleida-Pirineus', 'La Pobla de Segur');
insert into journey(origin_name, destination_name) values('Lleida-Pirineus', 'Tremp');

insert into favorite_journey(id, journey_destination_name, journey_origin_name, user_username) values('1', 'La Pobla de Segur', 'Lleida-Pirineus', 'tina');
insert into day_time_start (id, time_start, day_of_week, favorite_journey_id) values ('1', '12:51', 'Monday', '1');
insert into day_time_start (id, time_start, day_of_week, favorite_journey_id) values ('2', '12:30', 'Tuesday', '1');


insert into friend(username, friend) values('tina', 'maria');
insert into friend(username, friend) values('tina', 'pepe');
insert into friend(username, friend) values('tina', 'pepa');
insert into friend(username, friend) values('joanra', 'pepa');



