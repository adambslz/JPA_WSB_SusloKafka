Laboratorium III - JPQL

Uwaga! Do wykonania zadan konieczne jest zaimplementowanie architektury warstwowej i testow z Laboratorium II !

Uzupelnij plik data.sql o dane niezbedne do realizacji nastepujacych zapytan:
1. Znajdz pacjentow po nazwisku
2. Znajdz wszystkie wizyty pacjenta po jego ID
3. znajdz pacjentow ktorzy mieli wiecej niz X wizyt (X jest parametrem wejsciowym)
4. Znajdz pacjentow po dodanym przez Ciebie polu - nie wyszukuj wprost po wartosci, uzyj zapytania typu wieksze/mniejsze/pozniej/wczesniej/zawiera, w zaleznosci od wybranego typu zmiennej.

Napisz testy do zapytan w nastepujacej formie:
1. do zapytania nr 1  - test DAO
2. do zapytania nr 2 - test serwisu
3. do zapytania nr 3 - test DAO
4. do zapytania nr 4 - test DAO

W PatientEntity, nad relacja do VisitEntity dodaj adnotacje

@Fetch(FetchMode.SELECT)

a fetchType zmien na EAGER
Uruchom test w ktorym pobierany jest Patient z wieloma wizytami. W logach zaobserwuj, jak wyglada pobieranie dodatkowych encji (ile i jakie sqle).
Nastepnie zmien adnotacje na

@Fetch(FetchMode.JOIN)

i powtorz test i obserwacje. Wnioski zapisz na dole tego pliku i skomituj.

Do wybranej encji dodaj wersjonowanie, oraz napisz test (w DAO) sprawdzajacy rownolegla modyfikacje (OptimisticLock)

WNIOSKI:
@Fetch(FetchModel.SELECT):
select pe1_0.id,pe1_0.address_id,pe1_0.date_of_birth,pe1_0.email,pe1_0.first_name,pe1_0.last_name,pe1_0.patient_number,pe1_0.pesel_number,pe1_0.telephone_number from patient pe1_0 where (select count(*) from visit v1_0 where pe1_0.id=v1_0.patient_id)>?

@Fetch(FetchModel.JOIN):
select pe1_0.id,pe1_0.address_id,pe1_0.date_of_birth,pe1_0.email,pe1_0.first_name,pe1_0.last_name,pe1_0.patient_number,pe1_0.pesel_number,pe1_0.telephone_number from patient pe1_0 where (select count(*) from visit v1_0 where pe1_0.id=v1_0.patient_id)>?

W obydwóch przypadkach zapytania zostały wykonane tak samo, ponieważ w zastosowanym przez nas kodzie nie bazujemy na sposobie w którym Hibernate odpytuje bazę danych, ale bezpośrednio odpytujemy bazę na podstawie warunku Count przez JPQL. 
Aby to zmienić pole wizyt (visits) musiałoby być pobierane w tej samej transakcji po załadowaniu danych pacjenta.