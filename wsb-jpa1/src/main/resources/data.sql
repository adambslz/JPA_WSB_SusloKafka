insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'Źródlana', '78', 'Pawłowice', '65-922'),
                   (2, 'Dolna', '72', 'Kamień', '64-166'),
                   (3, 'Lubelska', '56', 'Olsztyn', '65-115'),
                   (4, 'Krzywa', '55', 'Świecie', '09-346'),
                   (5, 'Diamentowa', '24', 'Zamość', '22-239'),
                   (6, 'Modrzewiowa', '04', 'Bogaczów', '87-662'),
                   (7, 'Wieniawskiego', '31', 'Sopot', '36-125'),
                   (8, 'Skośna', '85', 'Jeziorna', '25-943'),
                   (9, 'Lechicka', '62', 'Pęcice', '61-825'),
                   (10, 'Konopnickiej', '82', 'Szteklin', '22-555');

insert into doctor (address_id, id, doctor_number, email, first_name, last_name, telephone_number, specialization)
            values (1, 1, '101', 'ddomagala@gravatar.com', 'Dagmara', 'Domagała', '909248742', 'GP'),
                   (2, 2, '102', 'wratajczak@patch.com', 'Witold', 'Ratajczak', '121361141', 'SURGEON'),
                   (3, 3, '103', 'zkowalska@mysql.com', 'Żaneta', 'Kowalska', '278381765', 'DERMATOLOGIST'),
                   (4, 4, '104', 'wadamzyk@elpais.com', 'Wojciech', 'Adamczyk', '235764112', 'OCULIST'),
                   (5, 5, '105', 'korlowski@guardian.co.uk', 'Kornel', 'Orłowski', '793612387', 'GP'),
                   (6, 6, '106', 'mdomanska@chicagotribune.com', 'Michalina', 'Domańska', '994148098', 'SURGEON'),
                   (7, 7, '107', 'uchmielewska@multiply.com', 'Urszula', 'Chmielewska', '256634960', 'DERMATOLOGIST'),
                   (8, 8, '108', 'apiatek@narod.ru', 'Anna', 'Piątek', '099629229', 'OCULIST'),
                   (9, 9, '109', 'akaczmarek@goo.ne.jp', 'Artur', 'Kaczmarek', '458977036', 'GP'),
                   (10, 10, '110', 'kkinga@shareasale.com', 'Kinga', 'Kulaga', '470805930', 'GP');

insert into patient (date_of_birth, address_id, id, email, first_name, last_name, patient_number, telephone_number)
            values ('2011-11-29', 1, 1, 'jwasilewski@google.fr', 'Jacek', 'Wasilewski', '201', '703784990'),
                   ('1960-04-12', 2, 2, 'kbrzozowski@1688.com', 'Kornel', 'Brzozowski', '202', '779088870'),
                   ('1947-06-29', 3, 3, 'jsliwinska@google.fr', 'Joanna', 'Śliwińska', '203', '729696313'),
                   ('2024-09-04', 4, 4, 'kkruk@ehow.com', 'Krystian', 'Kruk', '204', '411393837'),
                   ('1970-01-30', 5, 5, 'rdomanski@harvard.edu', 'Radosław', 'Domański', '205', '600519516'),
                   ('1941-05-17', 6, 6, 'agraboeska@hibu.com', 'Agnieszka', 'Grabowska', '206', '775626943'),
                   ('1976-03-07', 7, 7, 'nmarciniak@gizmodo.com', 'Natasza', 'Marciniak', '207', '550145540'),
                   ('1940-10-14', 8, 8, 'zwesolowski@who.int', 'Zenon', 'Wesołowski', '208', '910210215'),
                   ('1984-07-04', 9, 9, 'tczerwinski@geocities.com', 'Tomasz', 'Czerwiński', '209', '768084930'),
                   ('1997-03-14', 10, 10, 'jkot@independent.co.uk', 'Janina', 'Kot', '210', '192950959');

insert into visit (doctor_id, id, patient_id, time, description)
            values (1, 1, 1, '2025-07-10 15:30:00', 'ból brzucha'),
                   (2, 2, 2, '2025-07-03 10:15:00', 'omdlenia'),
                   (3, 3, 3, '2025-07-08 14:42:00', 'wymioty'),
                   (4, 4, 4, '2025-07-12 09:30:00', 'uraz głowy'),
                   (5, 5, 5, '2025-07-16 17:05:00', 'zatrucie pokarmowe'),
                   (6, 6, 6, '2025-07-20 12:28:00', 'gorączka'),
                   (7, 7, 7, '2025-07-23 08:55:00', 'infekcja'),
                   (8, 8, 8, '2025-07-25 16:10:00', 'problemy skórne'),
                   (9, 9, 9, '2025-07-28 11:37:00', 'szczepienie'),
                   (10, 10, 10, '2025-07-31 13:51:00', 'ból stawów');

insert into medical_treatment (id, visit_id, description, type)
            values (1, 1, 'jama brzuszna', 'USG'),
		           (2, 2, 'tarczyca', 'USG'),
                   (3, 3, 'piersi', 'USG'),
                   (4, 4, 'kręgosłupa', 'RTG'),
                   (5, 5, 'klatki piersiowej', 'RTG'),
                   (6, 6, 'zębów', 'RTG'),
                   (7, 7, 'spoczynkowe', 'EKG'),
                   (8, 8, 'wysiłkowe', 'EKG'),
                   (9, 9, 'przedoperacyjne', 'EKG'),
                   (10, 10, 'stawu kolanowego', 'RTG');