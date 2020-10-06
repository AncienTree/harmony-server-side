package pl.entpoint.harmony.service.user;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import pl.entpoint.harmony.entity.user.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserServiceImpl userService;

  @Test
  public void getAllUsersFromRepo() {
	  //given
	  List<User> users = new ArrayList<User>(List.of(
			  	new User("User 1", "abc"),
				new User("User 2", "abc"),
				new User("User 3", "abc"),
				new User("User 4", "abc")			  	  
			  ));		
	  
	  //when
	  when(userRepository.findAll()).thenReturn(users);
	  
	  //then
	  assertThat(userService.getUsers(), hasSize(4));
	  assertThat(userService.getUsers(), equalTo(users));
  }

}
