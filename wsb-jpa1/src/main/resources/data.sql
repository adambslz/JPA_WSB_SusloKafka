INSERT INTO ADDRESS (city, address_line1, address_line2, postal_code) VALUES
('Warszawa', 'ul. Krakowskie Przedmieście 1', 'lok. 5', '00-001'),
('Wrocław', 'ul. Piłsudskiego 10', NULL, '50-001'),
('Gdańsk', 'ul. Długa 15', 'mieszkanie 2A', '80-001'),
('Kraków', 'ul. Floriańska 20', NULL, '31-001'),
('Poznań', 'ul. Święty Marcin 50', 'lok. 12', '60-001'),
('Łódź', 'ul. Piotrkowska 100', NULL, '90-001'),
('Szczecin', 'ul. Jasna 5', 'mieszkanie 8', '70-001'),
('Katowice', 'ul. Kościuszki 30', NULL, '40-001'),
('Lublin', 'ul. Zana 45', 'lok. 3', '20-001'),
('Białystok', 'ul. Lipowa 10', NULL, '15-001');

INSERT INTO DOCTOR (first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES
('Jan', 'Kowalski', '123456789', 'jan.kowalski@example.com', 'DOC001', 'SURGEON', 1),
('Anna', 'Nowak', '987654321', 'anna.nowak@example.com', 'DOC002', 'GP', 2),
('Piotr', 'Wiśniewski', '555666777', 'piotr.wisniewski@example.com', 'DOC003', 'DERMATOLOGIST', 3),
('Magdalena', 'Jankowska', '444555666', 'magdalena.jankowska@example.com', 'DOC004', 'OCULIST', 4),
('Tomasz', 'Zieliński', '333222111', 'tomasz.zielinski@example.com', 'DOC005', 'SURGEON', 5),
('Katarzyna', 'Wójcik', '666777888', 'katarzyna.wojcik@example.com', 'DOC006', 'GP', 6),
('Marek', 'Lewandowski', '111222333', 'marek.lewandowski@example.com', 'DOC007', 'DERMATOLOGIST', 7),
('Zofia', 'Szymańska', '999888777', 'zofia.szymanska@example.com', 'DOC008', 'OCULIST', 8),
('Krzysztof', 'Dąbrowski', '777666555', 'krzysztof.dabrowski@example.com', 'DOC009', 'SURGEON', 9),
('Aleksandra', 'Pawlak', '888555222', 'aleksandra.pawlak@example.com','DOC010' , 'GP', 10);

INSERT INTO PATIENT (first_name, last_name, pesel_number, telephone_number, email, patient_number, date_of_birth, address_id) VALUES
('Jan', 'Kowalski', 90010112345, '123456789', 'jan.kowalski@example.com', 'PAT001', '1990-01-01', 1),
('Anna', 'Nowak', 85050567890, '987654321', 'anna.nowak@example.com', 'PAT002', '1985-05-05', 2),
('Piotr', 'Wiśniewski', 92030334567, '555666777', 'piotr.wisniewski@example.com', 'PAT003', '1992-03-03', 3),
('Magdalena', 'Jankowska', 80080878901, '444555666', 'magdalena.jankowska@example.com', 'PAT004', '1980-08-08', 4),
('Tomasz', 'Zieliński', 97070723456, '333222111', 'tomasz.zielinski@example.com', 'PAT005', '1997-07-07', 5),
('Katarzyna', 'Wójcik', 88020234567, '666777888', 'katarzyna.wojcik@example.com', 'PAT006', '1988-02-02', 6),
('Marek', 'Lewandowski', 94090956789, '111222333', 'marek.lewandowski@example.com', 'PAT007', '1994-09-09', 7),
('Zofia', 'Szymańska', 87070712345, '999888777', 'zofia.szymanska@example.com', 'PAT008', '1987-07-07', 8),
('Krzysztof', 'Dąbrowski', 93050567890, '777666555', 'krzysztof.dabrowski@example.com', 'PAT009', '1993-05-05', 9),
('Aleksandra', 'Pawlak', 89010145678, '222333444', 'aleksandra.pawlak@example.com', 'PAT010', '1989-01-01', 10);

INSERT INTO VISIT (description, time, doctor_id, patient_id) VALUES
('Konsultacja chirurgiczna', '2025-05-01 10:00:00', 1, 1), -- SURGEON
('Badanie ogólne', '2025-05-02 11:30:00', 2, 2), -- GP
('Kontrola dermatologiczna', '2025-05-03 14:00:00', 3, 3), -- DERMATOLOGIST
('Badanie okulistyczne', '2025-05-04 09:15:00', 4, 4), -- OCULIST
('Konsultacja chirurgiczna', '2025-05-05 13:45:00', 5, 5), -- SURGEON
('Badanie ogólne', '2025-05-06 16:20:00', 6, 6), -- GP
('Kontrola dermatologiczna', '2025-05-07 08:30:00', 7, 7), -- DERMATOLOGIST
('Badanie okulistyczne', '2025-05-08 12:00:00', 8, 8), -- OCULIST
('Konsultacja chirurgiczna', '2025-05-09 15:10:00', 9, 9), -- SURGEON
('Badanie ogólne', '2025-05-10 17:40:00', 10, 10); -- GP

INSERT INTO MEDICAL_TREATMENT (description, type, visit_id) VALUES
    ('Badanie ultrasonograficzne jamy brzusznej', 'USG', 1),
    ('Echo serca', 'USG', 2),
    ('Standardowe badanie EKG', 'EKG', 3),
    ('EKG wysiłkowe', 'EKG', 4),
    ('RTG klatki piersiowej', 'RTG', 5),
    ('RTG kręgosłupa lędźwiowego', 'RTG', 6),
    ('Badanie USG tarczycy', 'USG', 7),
    ('Holter EKG - monitorowanie rytmu serca', 'EKG', 8),
    ('RTG kończyny dolnej', 'RTG', 9),
    ('USG dopplerowskie naczyń krwionośnych', 'USG', 10);

