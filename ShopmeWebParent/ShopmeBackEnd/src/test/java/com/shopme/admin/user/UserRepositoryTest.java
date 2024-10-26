package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entitytManager;
	
	@Test
	public void testCreateUserWithOneRole() {
		Role roleAdmin = entitytManager.find(Role.class, 1);
		User userShiv = new User("rajputabhi07.ss@gmail.com", "shiv2024", "Shiv", "Sinh");
		userShiv.addRole(roleAdmin);
		
		User savedUser = repo.save(userShiv);
		
		assertThat(savedUser.getId()).isGreaterThan(0);;
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userShivi = new User("rajputshivi16@gmail.com", "shivi2024", "Shivi", "Singh");
		Role roleEditor = entitytManager.find(Role.class, 3);
		Role roleAssistant = entitytManager.find(Role.class, 5);
		
		userShivi.addRole(roleEditor);
		userShivi.addRole(roleAssistant);
		
		User savedUser = repo.save(userShivi);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles2() {
		User userSai = new User("yenugwarsai01@gmail.com", "sai2024", "Sai", "Yenugwar");
		Role roleEditor = entitytManager.find(Role.class, 3);
		Role roleAssistant = entitytManager.find(Role.class, 5);
		
		userSai.addRole(roleEditor);
		userSai.addRole(roleAssistant);
		
		User savedUser = repo.save(userSai);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
		
	}
	
	@Test
	public void testGetUserById() {
		User userName = repo.findById(5).get();
		System.out.println(userName);
		assertThat(userName).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userName = repo.findById(5).get();
		userName.setEnabled(true);
		userName.setEmail("abc1@gmaiil.com");
		repo.save(userName);

		
	}
	
	/*
	@Test
	public void testUpdateUserRoles() {
		User userSai = repo.findById(7).get();
		
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		
		userSai.getRoles().remove(roleEditor);
		userSai.addRole(roleSalesperson);
		
		repo.save(userSai);
	}
	*/
	
	@Test
	public void testDeleteUser() {
		Integer userId = 7;
		repo.deleteById(userId);
		
	}


}









