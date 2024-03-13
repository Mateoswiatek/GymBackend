Tworzenie Szkieletu aplikacji Webowej,
Łączenie z bazą danych,
Mockowanie danych,
Tworzenie trójwarstwowej aplikacji,
Proste zapytania,
Przekazywanie parametrów w adresie
N+1 problem + rozwiązanie
Java Data, własne zapytania JPQL, następnie generowane przez konwencję nazewniczą
Stronicowanie
applying in memory problem + rozwiązanie

Problem z encjami podczas wyrzukiwania Eventów po Id Trenera -> należało dodać adnotacje JoinColumn - dodaje kolumnę TRAINER_ID do eventow. ale blad byl jednak w maperze, bo nie dodawalem id.
Bez JoinColumn tworzona jest nowa tabela. Rowniez jest problem przy zwracaniu eventow. w naszym przypadku każdy event (większość) powinna mieć prowadzących, więc wydajniej jest dodać kolumnę. 

Użycie count zamiast find przy tworzeniu statystyki "inGym"


![image](https://github.com/Mateoswiatek/GymBackend/assets/115046087/8f120c51-d2c5-4a91-89f2-60e108b5dcb4)

Rekurencyjny problem:
![image](https://github.com/Mateoswiatek/GymBackend/assets/115046087/c875d0df-5099-4dbc-80ea-c9ec1348cede)

