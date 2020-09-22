package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;

/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

@Service
public class AbsenceRecordServiceImpl implements AbsenceRecordService {
	
	private final AbsenceRecordRepository absenceRepository;
	
	@Autowired
	public AbsenceRecordServiceImpl(AbsenceRecordRepository absenceRepository) {
		this.absenceRepository = absenceRepository;
	}

	@Override
	public List<AbsenceRecord> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AbsenceRecord> getEmployeeRequests(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AbsenceRecord> getMyRequests(Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submiteRequest(AbsenceRecord record) {
		// TODO Auto-generated method stub

	}

	@Override
	public void acceptRequest(AbsenceRecord record) {
		// TODO Auto-generated method stub

	}

	@Override
	public void declineRequest(AbsenceRecord record) {
		// TODO Auto-generated method stub

	}

}
