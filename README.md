Tworzenie Szkieletu aplikacji Webowej,
Łączenie z bazą danych,
Mockowanie danych,
Tworzenie trójwarstwowej aplikacji,
Proste zapytania,
Przekazywanie parametrów w adresie
N+1 problem + rozwiązanie
Java Data, własne zapytania JPQL, następnie generowane przez konwencję nazewniczą
Stronicowanie
applying in memory problem + rozwiązanie - nie zwracanie fetchowanych danych

Problem z encjami podczas wyrzukiwania Eventów po Id Trenera -> należało dodać adnotacje JoinColumn - dodaje kolumnę TRAINER_ID do eventow. ale blad byl jednak w maperze, bo nie dodawalem id.
Bez JoinColumn tworzona jest nowa tabela. Rowniez jest problem przy zwracaniu eventow. w naszym przypadku każdy event (większość) powinna mieć prowadzących, więc wydajniej jest dodać kolumnę. 

fetch z Pageable - rozwiązanie - podwójne zapytanie id z pageable, potem dociaganie

zwracanie fetchowanych parametrów z encji.

relacja wiele do wielu z określeniem własnej tabeli

modyfikacja, usuwanie - pamiętać o cascade,

problem przy modyfikowaniu (uczestnikow eventu), najpiewr usuwa wszystko a potem dodaje?





![image](https://github.com/Mateoswiatek/GymBackend/assets/115046087/8f120c51-d2c5-4a91-89f2-60e108b5dcb4)

![Screenshot from 2024-03-13 20-45-23](https://github.com/Mateoswiatek/GymBackend/assets/115046087/9f6d2e8c-b098-426e-b792-25ab2e1346ba)



![Screenshot from 2024-03-13 21-02-39](https://github.com/Mateoswiatek/GymBackend/assets/115046087/0b466805-3428-4c02-ba32-8507b25eac4f)

Rekurencyjny problem:
![image](https://github.com/Mateoswiatek/GymBackend/assets/115046087/c875d0df-5099-4dbc-80ea-c9ec1348cede)

Antywzorzec modyfikacji - bo są listy a nie sety?
![img.png](jpgs/img_13.png)


# Endpoints
TODO
/events/{id}/enroll
/events/{id}/unenroll
/home...
/dashboard...
/trainer - jakies modyfikacje trenera? update.


- **/home** 
  - TODO GET ???
    - Request 
      - Empty
    - Response
      - usersCount (Long)
      - trainerCount (Long)
  - TODO **/find** - wyszukiwarka
    - GET
      - Request
        - dyskryminator (Long) - czego szukamy (Event, Trainer, User)
        - .......
        - konkretne parametry ??? głównie po nazwie
        - page (Long)
        - size (Long)
      - Response
        - result (List< ??? >) w zalezności od wyszukiwania to się zwróci?
    
    - TODO **/events** - eventy
      - Specyficzne dla eventow?
    - TODO **/trainers** - trenerzy
      - specyficzne dla trenerow?


- **/events** - lista wszystkich eventów
  - GET
    - Request
      - page (Long)
      - size (Long)
    - Response
      - events (List< EventShortDto>)
  - POST
  TODO: Zrobić Posta
    - Request
      - event (EventDto)
      - Trainer (z tokena?)

  - **/{id}** - konkretny event
    - GET
      - Request
        - eventId (Long)
      - Response
        - eventDto (EventDto)
    - DELETE - wycofanie się z eventu
      - Request
        - courseId (Long) - path
        - userId (Long)
      - Response
        - success (boolean)
    - **/participants** - uczestnicy
      - GET
        - Request
          - courseId (Long) - path
          - page (Long)
          - size (Long)
        - Resonse
          - participants (List< UserShortDto>)
      - PUT - zapisanie się na event.
        - Request
          - courseId (Long) - path
          - userId (Long)
        - Response
          - success (boolean)
          - info (String) - informacja od konkretnego eventu

- **/trainers** - lista trenerów 
  - GET
    - Request
      - page (Long)
      - size (Long)
    - Response
      - trainers (List< TrainerShortDto>)
  - **/{id}** - konkretny trener
    - GET
      - Request
        - trainerId (Long) - path
      - Response
        - trainer (TrainerDto)
    - **/events** - lista eventów które prowadzi
      - GET
        - Request
          - trainerId (Long) - path
          - page (Long)
          - size (Long)
        - Response
          - events (List< EventShortDto>)

- **/dashboard/{id}**

Tu będzie profil usera konkretnego, razem z możliwościa modyfikacji - tylko ten konkretny user,
po jakimś tokenie?

/events
![img.png](jpgs/img.png)

/events/{id}
![img_1.png](jpgs/img_1.png)

/events/{id}/participants
![img_2.png](jpgs/img_2.png)

/events/{id}/participants PUT - zapisanie się na event
![img.png](jpgs/img_6.png)
![img.png](jpgs/img_7.png)

/events/{id} DELETE - usunięcie eventu
![img.png](jpgs/img_11.png)

/events/{id} PUT - modyfikacja eventu
![img.png](jpgs/img_12.png)

/events POST - dodanie eventu
![img_1.png](jpgs/img_9.png)
![img.png](jpgs/img_8.png)
![img_2.png](jpgs/img_10.png)

/trainers
![img_3.png](jpgs/img_3.png)

/trainers/{id}
![img_4.png](jpgs/img_4.png)

/trainers/{id}/events/
![img_5.png](jpgs/img_5.png)
