package pl.entpoint.harmony.dao;

import java.util.List;

import pl.entpoint.harmony.entity.User;

public interface TestUserDao {
	
	public List<User> getCustomers();

	public void saveCustomer(User theCustomer);

	public User getCustomer(int theId);

	public void deleteCustomer(int theId);
}
