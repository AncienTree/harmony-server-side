package pl.entpoint.harmony.service.user;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;

import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.entity.user.enums.Roles;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userService;

	@Test
	void getUsersShouldReturnAllUsers() {
		// given
		List<User> users = getUsersList();

		// when
		when(userRepository.findAll()).thenReturn(users);

		// then
		assertThat(userService.getUsers(), hasSize(4));
		assertThat(userService.getUsers(), equalTo(users));
	}
	
	@Test
	void getUsersByRolesShouldReturnOnlyUsersWithSelectedRole() {
		//given
		List<User> users = getUsersList();
		users.forEach(x -> x.setRole(Roles.ROLE_SPEC));
		
		//when
		when(userRepository.findByRole(Roles.ROLE_SPEC)).thenReturn(users);
		
		//then
		assertThat(userService.getUsersByRoles(Roles.ROLE_SPEC), hasSize(4));
		assertThat(userService.getUsersByRoles(Roles.ROLE_SPEC).get(1).getRole(), equalTo(Roles.ROLE_SPEC));

	}
	
	@Test
	void getUsersByIdShouldReturnUserWithSelectedId() {
		//given
		Optional<User> user = Optional.ofNullable(new User("Test", "a"));
		
		//when
		when(userRepository.findById(Mockito.anyLong())).thenReturn(user);
		User userTest = userService.getUser(Mockito.anyLong());
		
		//then
		assertThat(userTest, is(notNullValue()));
		assertThat(userTest.getLogin(), equalTo("Test"));
	}
	
	@Test
	void getUsersByWrongIdShouldThrowException() {
		//given
		Optional<User> user = Optional.empty();
		
		//when
		when(userRepository.findById(Mockito.anyLong())).thenReturn(user);
		
		//then
		assertThrows(IllegalArgumentException.class, () -> userService.getUser(Mockito.anyLong()));
		assertThrows(IllegalArgumentException.class, () -> userService.changeStatus(Mockito.anyLong(), true));
	}
	
	
	@Test
	void getUsersByIdAndSendChangeStatusShouldReturnChangedStatus() {
		//given
		Optional<User> user = Optional.ofNullable(new User("Status", "a"));
		user.get().setStatus(false);
		
		//when
		when(userRepository.findById(Mockito.anyLong())).thenReturn(user);
		userService.changeStatus(Mockito.anyLong(), true);
		
		//then
		assertThat(userService.getUser(Mockito.anyLong()), is(notNullValue()));
		assertThat(userService.getUser(Mockito.anyLong()).getLogin(), equalTo("Status"));
		assertThat(userService.getUser(Mockito.anyLong()).isStatus(), equalTo(true));
	}
	
	@Test
	void getUsersByloginShouldReturnUserWithSelectedLogin() {
		//given
		User user = new User("Login", "a");
		
		//when
		when(userRepository.findByLogin(Mockito.anyString())).thenReturn(user);
		User userTest = userService.getUserByLogin(Mockito.anyString());
		
		//then
		assertThat(userTest, is(notNullValue()));
		assertThat(userTest.getLogin(), equalTo("Login"));
	}

	private List<User> getUsersList() {
		return new ArrayList<User>(List.of(
				new User("User 1", "abc"), 
				new User("User 2", "abc"),
				new User("User 3", "abc"), 
				new User("User 4", "abc")));
	}

}
