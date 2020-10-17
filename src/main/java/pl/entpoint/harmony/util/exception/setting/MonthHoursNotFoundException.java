package pl.entpoint.harmony.util.exception.setting;

public class MonthHoursNotFoundException extends RuntimeException {
    public MonthHoursNotFoundException() {
        super("Nie znaleziono takiego zestawienia roboczogodzin");
    }
}
