package pl.entpoint.harmony.service.employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.pojo.SimpleEmployee;
import pl.entpoint.harmony.entity.pojo.dbview.HrTable;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 07/10/2020
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    HrTableViewRepository hrTableViewRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    void getEmployeeShouldReturnAllEmployeesFromRepository() {
        //given
        List<Employee> employees = generateListOfEmployee();

        //when
        when(employeeRepository.findAll()).thenReturn(employees);

        //then
        assertThat(employeeService.getEmployees(), hasSize(6));
        assertThat(employeeService.getEmployees(), equalTo(employees));
    }

    @Test
    void getWorkingEmployeeByPositionShouldReturnAllEmployeesFromRepositoryWithSelectedPositionAndWorkStatusOtherThenNotWorking() {
        //given
        List<Employee> employees = generateListOfEmployee();
        employees.forEach(x -> x.setPosition("Lider"));

        //when
        when(employeeRepository.findByPositionAndWorkStatusNotOrderByLastName(anyString(), any(WorkStatus.class))).thenReturn(employees);

        //then
        assertThat(employeeService.getWorkingEmployeesByPosition("Lider"), hasSize(6));
        assertThat(employeeService.getWorkingEmployeesByPosition("Lider"), everyItem(hasProperty("position", equalTo("Lider"))));
        assertThat(employeeService.getWorkingEmployeesByPosition("Lider"), everyItem(instanceOf(SimpleEmployee.class)));
        assertThat(employeeService.getWorkingEmployeesByPosition("Lider").get(0).getFullName(),
                equalTo(employees.get(0).getLastName() + " " + employees.get(0).getFirstName()));
    }

    @Test
    void getEmployeeByStatusShouldReturnAllEmployeesFromRepository() {
        //given
        List<Employee> employees = generateListOfEmployee();

        //when
        when(employeeRepository.findByWorkStatus(any(WorkStatus.class))).thenReturn(employees);

        //then
        assertThat(employeeService.getEmployeesByStatus(WorkStatus.WORK), hasSize(6));
        assertThat(employeeService.getEmployeesByStatus(WorkStatus.WORK), everyItem(hasProperty("workStatus", equalTo(WorkStatus.WORK))));
    }

    @Test
    void getEmployeeByStatusShouldReturnAllEmployeesFromRepositoryWhereStatusInNotAsSelected() {
        //given
        List<Employee> employees = generateListOfEmployee();

        //when
        when(employeeRepository.findByWorkStatusNotOrderByLastName(any(WorkStatus.class))).thenReturn(employees);

        //then
        assertThat(employeeService.getEmployeesByStatusIsNot(WorkStatus.L4), hasSize(6));
        assertThat(employeeService.getEmployeesByStatusIsNot(WorkStatus.L4), everyItem(hasProperty("workStatus", not(equalTo(WorkStatus.L4)))));
    }

    @Test
    void getPersonalDateShouldReturnViewTable() {
        //given
        List<HrTable> hrTables = new ArrayList<>();
        hrTables.add(new HrTable());
        hrTables.add(new HrTable());

        //when
        when(hrTableViewRepository.findAll()).thenReturn(hrTables);

        //then
        assertThat(employeeService.getPersonalDate(), hasSize(2));
        assertThat(employeeService.getPersonalDate(), everyItem(instanceOf(HrTable.class)));
    }

    @Test
    void getEmployeesByStatusAndStartWork() {
    }

    @Test
    void getEmployeeDecrypted() {
    }

    @Test
    void getEmployeeNotDecrypted() {
    }

    @Test
    void getEmployeeByPesel() {
    }

    @Test
    void change() {
    }

    @Test
    void isPeselInDB() {
    }

    @Test
    void getFullNameByLogin() {
    }

    @Test
    void countByWorkStatus() {
    }

    @Test
    void newEmployee() {
    }

    @Test
    void fireEmployee() {
    }

    private List<Employee> generateListOfEmployee() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Testowy", "Pracownik 1", "12345678987", "m", LocalDate.of(1990,1,1), "Stanowisko 1",
                "Opis", "Umowa", "Stawka", "25", LocalDate.now(), LocalDate.now()));
        employees.add(new Employee("Testowy", "Pracownik 2", "98765432123", "m", LocalDate.of(1991,1,11), "Stanowisko 2",
                "Opis", "Umowa", "Stawka", "25", LocalDate.now(), LocalDate.now()));
        employees.add(new Employee("Testowy", "Pracownik 3", "14725836963", "k", LocalDate.of(1992,10,12), "Stanowisko 3",
                "Opis", "Umowa", "Stawka", "25", LocalDate.now(), LocalDate.now()));
        employees.add(new Employee("Testowy", "Pracownik 4", "74185296369", "m", LocalDate.of(1993,2,15), "Stanowisko 4",
                "Opis", "Umowa", "Stawka", "25", LocalDate.now(), LocalDate.now()));
        employees.add(new Employee("Testowy", "Pracownik 5", "36925814741", "k", LocalDate.of(1994,5,12), "Stanowisko 5",
                "Opis", "Umowa", "Stawka", "25", LocalDate.now(), LocalDate.now()));
        employees.add(new Employee("Testowy", "Pracownik 6", "96385274147", "m", LocalDate.of(1995,9,11), "Stanowisko 6",
                "Opis","Umowa", "Stawka", "25", LocalDate.now(), LocalDate.now()));

        return employees;
    }
}