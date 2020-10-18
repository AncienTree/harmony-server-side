package pl.entpoint.harmony.util.exception.setting;

public class DayOffNotFoundException extends RuntimeException {
    public DayOffNotFoundException() {
        super("Nie znaleziono wolnego dnia od pracy");
    }
}
