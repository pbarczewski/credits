# credits
## Spis treści
* [Informacje ogólne](#info)
* [Technologie](#technologie)
* [Uruchomienie](#uruchomienie)
* [Rest Api](#rest-api)
* [Status](#status)

## Informacje ogólne
Projekt RestApi 

## Technologie
Stworzony za pomocą
- Java
- Hibernate
- SpringBoot
- Docker


## Uruchomienie
1. Sciągniecie projektu
 - git clone https://github.com/pbarczewski/xxx.git
2. Uruchomienie kontenerów dockera
 - W wierszu poleceń w folderze w którym sklonowaliśmy repozytoria wpisujemy poniższe polecenia. Stworzą one i uruchomią kontenery dockera:
 	-  docker-compose -f docker-compose.yml build
	-  docker-compose up

## Rest Api
Głownym komponentem programu jest moduł "credit" w którym zawarta jest znacząca większość logiki biznesowej. Moduły products i customers to moduły pomocnicze, służące do organizacji danych związanych odpowiednio z produktami i klientami. Encje wykorzysytwane w projekcie to "Credit", "Product" i "Customer". Wszystkie trzy posiadają to samo id, nadawane automatycznie przez program w wyniku losowania numeru. 

1. Tabela "Credit" zawiera kolumny "id", "name", oraz klucze obce reprezentowane przez obiekty "product" i "customer". Powiązanie pomiędzy tabelą "credit" a tabelami "product" i "customer" realizowane jest w jednokierunkowej relacji jeden do jednego.
2. Tabela "Customer" zawiera kolumny "creditId","firstName", "surname", "pesel"
3. Tabela "Product" zawiera kolumny "creditId", "name", "value"


##Post - tworzenie kredytu
Baza danych jest pusta więc żeby zacząć pracę z programem, musimy wprowadzić do niego odpowiednie dane. Na wejściu program potrzebuje stworzyć trzy obiekty, skupione w jednym. Polecenie w odpowiedzi przekaże numer kredytu przyznanego przez aplikację. Realizacja zapytania za pomocą Postmana (zawierająca przykładowe dane) będzie wyglądać jak poniżej:

POST: http://localhost:9001/credits

{
    "credit" : {
        "name" : "Nowy kredyt"
    },
    "customer": {
        "firstName": "Jan",
        "surname": "Kowalski",
        "pesel":"12345678901"
    },
    "product": {
        "name": "Produkt",
        "value": 100
    }
}

Rezultat: {losowa_liczba}

W przypadku korzystania z "curl" przykładowe zapytanie będzie wyglądało następująco (korzystałem z wiersza poleceń systemu Windows):
curl -X POST http://localhost:9001/credits -H "Content-Type: application/json" -d "{\"credit\":{\"name\":\"Kredyt 1\"},\"customer\":{\"firstName\":\"Jan\",\"surname\":\"Kowalski\",\"pesel\":\"12345678901\"},\"product\":{\"name\":\"Produkt 1\",\"value\":100}}"


Wpisywane dane posiadają pewne ograniczenia, blokujące przesyłanie nieprawidłowych danych.
1. Credit:
 - "name" (regexp="^[A-Z]([a-zA-Z0-9]+_?\\s?)*[a-zA-Z0-9]+$") - Nazwa musi zaczynać się od wielkiej litery, może posiadać cyfry, spacje oraz znak "_". Musi kończyć się znakiem alfanumerycznym.
2. Customer:
 - "firstName" (regexp="[A-Z][a-z]{2,49}$") - Imię musi zaczynać się od wielkiej litery, minimalnie trzy znaki, maksmymalnie 50. Dozwolone są jedynie znaki alfabetyczne
 - "surname" (regexp="[A-Z][a-z]+[-]?[A-Z]?[a-z]+$") - Nazwisko musi zaczynać się od wielkiej litery, nie ma ograniczenia maksymalnej liczby znaków. Może zawierać znak "-", musi kończyć się małą literą.
 - "pesel" (regexp="[0-9]{11}") - Pesel musi zawierać 11 cyfr (Uwaga: Pesel jest wartością unikatową, nie można zapisać do bazy danych dwóch rekordów o tym samym peselu
3. Product
 - "name" (regexp="[A-Z]([a-zA-Z0-9]+_?\\s?)*[a-zA-Z0-9]+$") - Nazwa musi zaczynać się od wielkiej litery, może posiadać cyfry, spacje oraz znak "_". Musi kończyć się znakiem alfanumerycznym.
 - "value" @Min(1), @Max(1000000)


## Get - pobieranie użytkowników
Zapytanie Get pobiera dane. Poniżej przykładowe zapytania realizowane za pomocą wiersza poleceń:

Po wprowadzeniu wartości:
curl -X POST http://localhost:9001/credits -H "Content-Type: application/json" -d "{\"credit\":{\"name\":\"Kredyt 3\"},\"customer\":{\"firstName\":\"Robert\",\"surname\":\"Lewandowski\",\"pesel\":\"12345678903\"},\"product\":{\"name\":\"Produkt 3\",\"value\":300}}"

curl -X POST http://localhost:9001/credits -H "Content-Type: application/json" -d "{\"credit\":{\"name\":\"Nowy kredyt\"},\"customer\":{\"firstName\":\"Jan\",\"surname\":\"Kowalski\",\"pesel\":\"12345678901\"},\"product\":{\"name\":\"Produkt\",\"value\":100}}"

curl -X POST http://localhost:9001/credits -H "Content-Type: application/json" -d "{\"credit\":{\"name\":\"Kredyt 2\"},\"customer\":{\"firstName\":\"Janina\",\"surname\":\"Nowak\",\"pesel\":\"12345678902\"},\"product\":{\"name\":\"Produkt 2\",\"value\":200}}"


1. curl http://localhost:9001/credits  - zapytanie zwraca wszystkie kredyty wprowadzone do bazy danych. 

Przykładowa odpowiedź:
[{"id":556818,"name":"Nowy kredyt","customer":{"creditId":556818,"firstName":"Jan","surname":"Kowalski","pesel":"12345678901"},"product":{"creditId":556818,"name":"Produkt","value":100}},{"id":738796,"name":"Kredyt 3","customer":{"creditId":738796,"firstName":"Robert","surname":"Lewandowski","pesel":"12345678903"},"product":{"creditId":738796,"name":"Produkt 3","value":300}},{"id":870195,"name":"Kredyt 2","customer":{"creditId":870195,"firstName":"Janina","surname":"Nowak","pesel":"12345678902"},"product":{"creditId":870195,"name":"Produkt 2","value":200}}]

2. curl http://localhost:9001/products - zapytanie zwraca wszystkie produkty wprowadzone do bazy danych

Przykładowa odpowiedź:
[{"creditId":556818,"name":"Produkt","value":100},{"creditId":738796,"name":"Produkt 3","value":300},{"creditId":870195,"name":"Produkt 2","value":200}]

3. curl http://localhost:9001/products?id=556818,738796 - zapytanie zwróci produkty o podanym numerze kredytu

Przykładowa odpowiedź:
[{"creditId":556818,"name":"Produkt","value":100},{"creditId":738796,"name":"Produkt 3","value":300}]

4. curl http://localhost:9001/products?id=556818,xxx - w przypadku podania nieprawidłowego parametru, lub kredytu o nieistniejącym numerze zapytanie zwróci tylko te wyniki które znajdują się w bazie danych

Przykładowa odpowiedź:
[{"creditId":556818,"name":"Produkt","value":100}]

5. curl http://localhost:9001/products?id=11111,xxxx - gdy obie wartości są nieprawidłowe, zwrócona zostanie pusta lista
Przykładowa odpowiedź:
[]

6. curl http://localhost:9001/products?id= - zwróci wszystkie wyniki

Przykładowa odpowiedź:
[{"creditId":556818,"name":"Produkt","value":100},{"creditId":738796,"name":"Produkt 3","value":300},{"creditId":870195,"name":"Produkt 2","value":200}]

7. curl http://localhost:9001/customers - zapytanie zwraca wszystkich klientów wprowadzonych do bazy danych

Przykładowa odpowiedź:
[{"creditId":556818,"firstName":"Jan","surname":"Kowalski","pesel":"12345678901"},{"creditId":738796,"firstName":"Robert","surname":"Lewandowski","pesel":"12345678903"},{"creditId":870195,"firstName":"Janina","surname":"Nowak","pesel":"12345678902"}]

3. curl http://localhost:9001/customers?id=556818,870195 - zapytanie zwróci produkty o podanym numerze kredytu

Przykładowa odpowiedź:
[{"creditId":556818,"firstName":"Jan","surname":"Kowalski","pesel":"12345678901"},{"creditId":870195,"firstName":"Janina","surname":"Nowak","pesel":"12345678902"}]

4. curl http://localhost:9001/customers?id=556818,xxxx - w przypadku podania nieprawidłowego parametru, lub kredytu o nieistniejącym numerze zapytanie zwróci tylko te wyniki które znajdują się w bazie danych
Przykładowa odpowiedź:
[{"creditId":556818,"firstName":"Jan","surname":"Kowalski","pesel":"12345678901"}]

5. curl http://localhost:9001/customers?id=11111,xxxx - gdy obie wartości są nieprawidłowe, zwrócona zostanie pusta lista
Przykładowa odpowiedź:
[]

6. curl http://localhost:9001/customers?id= - zwróci wszystkie wyniki
Przykładowa odpowiedź:
[{"creditId":556818,"firstName":"Jan","surname":"Kowalski","pesel":"12345678901"},{"creditId":738796,"firstName":"Robert","surname":"Lewandowski","pesel":"12345678903"},{"creditId":870195,"firstName":"Janina","surname":"Nowak","pesel":"12345678902"}]

## Status
Projekt gotowy
