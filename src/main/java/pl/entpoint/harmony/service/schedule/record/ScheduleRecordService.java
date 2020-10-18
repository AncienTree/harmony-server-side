package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.util.List;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.Presence;
import pl.entpoint.harmony.entity.pojo.Record;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

public interface ScheduleRecordService {
	ScheduleRecord getScheduleRecordByDateAndEmployeeAndTypes(LocalDate date, Employee employee, ScheduleType type);
	List<ScheduleRecord> getScheduleRecordByDateAndEmployee(LocalDate date, Employee employee);
	List<Presence> getScheduleRecordForPresence(LocalDate date);
	/**
	 *  Pobieranie ilości dni na danych statusach
	 *
	 * @param employee encja użytkownika
	 * @param type as string "work", "leave", "absences".
	 * @param start od którego dnia ma szukać
	 * @param end do którego dnia ma szukać
	 * @return Zwraca ilość dni na wybranym statusie
	 */
    int getNumbersOfDaysByStatus(Employee employee, String type, LocalDate start, LocalDate end);
    int getCurrentMonthStatus(Employee employee, String type, LocalDate date);
    int getCurrentYearStatus(Employee employee, String type, LocalDate date);
	void acceptAbsence(AbsenceRecord recordAccepted);
	void create (Record record);
	void update (Record record);
}
