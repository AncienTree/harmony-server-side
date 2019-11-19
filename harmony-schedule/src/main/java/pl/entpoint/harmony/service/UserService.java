package pl.entpoint.harmony.service;

import java.util.List;

import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.entity.enums.Roles;

public interface UserService {

	public List<User> getUsers();
	
	public List<User> getUsersByRoles(Roles role);

	public void createUser(User user);

	public User getUser(int theId);

	public void deleteUser(int theId);
	
	public boolean isPeselInDB(long pesel);

	public void changeStatus(int id);
}
