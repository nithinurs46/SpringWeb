package com.springweb;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springweb.model.LoginForm;
import com.springweb.model.User;
import com.springweb.svc.UserSvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class SpringWebApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	@MockBean
	UserSvc userSvc;

	@BeforeAll
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	@BeforeEach
	private void init() {
		System.out.println("method called before execution of each tests");
	}

	@AfterEach
	void cleanUp() {
		System.out.println("**Clean Up**");
	}


	@Test
	@DisplayName("Unit test for creating new user")
	@Order(1)
	public void testCreateUser() throws Exception {
		
		mockMvc.perform(post("/saveUser?opType=add"))
				.andExpect(status().isOk()).andExpect(view().name("users"));

	}

	@Test
	@DisplayName("Unit test for retreiving all users")
	@Order(2)
	public void testGetAllUsers() throws Exception {

		User user1 = new User();
		user1.setUserId("NU");

		User user2 = new User();
		user2.setUserId("AB");

		List<User> usersList = new ArrayList<User>();
		usersList.add(user1);
		usersList.add(user2);

		when(userSvc.getAllUsers()).thenReturn(usersList);

		mockMvc.perform(get("/showUsers")).andExpect(status().isOk()).andExpect(view().name("users"))
				// .andExpect(model().attribute("usersList", hasSize(6)))
				.andExpect(model().attribute("usersList", hasItem(allOf(hasProperty("userId", is("NU"))))))
				.andExpect(model().attribute("usersList", hasItem(allOf(hasProperty("userId", is("AB"))))));

	}

	@Test
	@DisplayName("Unit test for retreiving specific user")
	@Order(3)
	public void getUser() throws Exception {
		// when(userSvc.getUser("DD")).thenThrow(new UserNotFoundException());

		// when user is present
		User user = new User();
		user.setUserId("AB");
		user.setMobileNo("1234567890");
		user.setEmailId("aa@gmail.com");
		
		when(userSvc.getUser("AB")).thenReturn(user);

		mockMvc.perform(get("/getUser/{userId}", "AB")).andExpect(status().isOk()).andExpect(view().name("add_edit_user"))
				.andExpect(model().attribute("userUpdateForm", hasProperty("mobileNo", is("1234567890"))))
				.andExpect(model().attribute("userUpdateForm", hasProperty("emailId", is("aa@gmail.com"))));
		//verifyNoMoreInteractions(userSvc);
	}

	@Test
	@DisplayName("Unit test for deleting specific user")
	@Order(4)
	public void deleteUser() throws Exception {
		mockMvc.perform(post("/deleteUser/{userId}", "NEW4")).andExpect(status().isOk())
				.andExpect(view().name("users"));
	}

	@Test
	@DisplayName("Unit test for updating specific user")
	@Order(5)
	public void updateUser() throws Exception {
		mockMvc.perform(post("/editUser/{userId}", "NEW3").param("userId", "NEW1").param("password", "Password21")
				.param("mobileNo", "9876540987")).andExpect(status().isOk());
	}

	// @Disabled -- add this annotation to stop any test method from executing
}
