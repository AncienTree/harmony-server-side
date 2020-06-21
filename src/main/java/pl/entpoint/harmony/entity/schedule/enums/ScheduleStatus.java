package pl.entpoint.harmony.entity.schedule.enums;

/**
 * @author Mateusz Dąbek
 * @created 16/11/2019
 */
// TODO Utworzyć nowy status dla planera!
public enum ScheduleStatus {
    CN,
    CH,
    UB,
    UK,
    UW,
    UZ,
    WP,
    OP,
    NN,
    P,
    BLOCK
}


/*
 Przykładowe statusy 
 
CN	->	L4 nie potwierdzone
CH	->	Choroba
UB	->	Urlop bezpłatny 
UO	->	Urlop okolicznościowy
UW	->	Urlop wypoczynkowy
WP	->	Dzień ustawowo wolny
OP	->	Opieka nad dzieckiem
UZ	->	Urlop na żądanie
NN	->	Nieobecność nieusprawiedliwiona

*/