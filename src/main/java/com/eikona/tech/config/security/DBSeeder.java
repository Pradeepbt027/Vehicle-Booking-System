package com.eikona.tech.config.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eikona.tech.entity.Privilege;
import com.eikona.tech.entity.Role;
import com.eikona.tech.entity.User;
import com.eikona.tech.repository.PrivilegeRepository;
import com.eikona.tech.repository.RoleRepository;
import com.eikona.tech.repository.UserRepository;

@Service
public class DBSeeder implements CommandLineRunner {
	
	 private UserRepository userRepository;
	 
	 private PrivilegeRepository privilegeRepository;
	 
	 private RoleRepository roleRepository;
	 
     private PasswordEncoder passwordEncoder;
     
     public DBSeeder(PrivilegeRepository privilegeRepository,RoleRepository roleRepository,UserRepository userRepository, PasswordEncoder passwordEncoder) {
    	 this.privilegeRepository=privilegeRepository;
    	 this.roleRepository=roleRepository;
    	 this.userRepository = userRepository;
         this.passwordEncoder = passwordEncoder;
     }

	@Override
	public void run(String... args) throws Exception {
		List<Privilege> privilegeList = privilegeRepository.findAllByIsDeletedFalse();
		
		if(null==privilegeList || privilegeList.isEmpty()) {
			List<Privilege> privileges = SeedPrivileges();
			Role admin = seedRole(privileges);
			seedUser(admin);
		}
	}
	
	private List<Privilege> SeedPrivileges() {
		
		Privilege userView = new Privilege("user_view", false);
		Privilege userCreate = new Privilege("user_create", false);
		Privilege userUpdate = new Privilege("user_update", false);
		Privilege userDelete = new Privilege("user_delete", false);
		
		Privilege roleView = new Privilege("role_view", false);
		Privilege roleCreate = new Privilege("role_create", false);
		Privilege roleUpdate = new Privilege("role_update", false);
		Privilege roleDelete = new Privilege("role_delete", false);
		
		Privilege privilegeView = new Privilege("privilege_view", false);
		Privilege privilegeUpdate = new Privilege("privilege_update", false);
		Privilege privilegeDelete = new Privilege("privilege_delete", false);
		
		Privilege driverView = new Privilege("driver_view", false);
		Privilege driverCreate = new Privilege("driver_create", false);
		
		Privilege vehicleView = new Privilege("vehicle_view", false);
		Privilege vehicleCreate = new Privilege("vehicle_create", false);
		
		Privilege containerView = new Privilege("container_view", false);
		Privilege containerCreate = new Privilege("container_create", false);
		
		Privilege bookingView = new Privilege("booking_view", false);
		Privilege bookingCreate = new Privilege("booking_create", false);
		Privilege bookingUpdate = new Privilege("booking_update", false);
		
		List<Privilege> privileges = Arrays.asList(
			userView, userCreate, userUpdate, userDelete,
			roleView, roleCreate, roleUpdate, roleDelete,
			privilegeView,privilegeUpdate,privilegeDelete,
			driverView,driverCreate,containerView,containerCreate,
			vehicleView,vehicleCreate,bookingView,bookingCreate,bookingUpdate
		);
		
		privilegeRepository.saveAll(privileges);
		
		List<Privilege> adminPrivileges = Arrays.asList(
			userView, userCreate, userUpdate, userDelete,
			roleView, roleCreate, roleUpdate, roleDelete,
			privilegeView,privilegeUpdate,privilegeDelete,
			driverView,driverCreate,containerView,containerCreate,
			vehicleView,vehicleCreate,bookingView,bookingCreate,bookingUpdate
		);
		 
		return adminPrivileges;
	}

	private Role seedRole(List<Privilege> privileges) {
		Role admin=roleRepository.findByName("Admin");
		if(null==admin) {
			 admin= new Role("Admin", privileges, false);
			roleRepository.save(admin);
		}
		return admin;
	}

	private void seedUser(Role admin) {
		List<User> userList=userRepository.findAllByIsDeletedFalse();
		if(null==userList || userList.isEmpty()) {
			User adminUser= new User("admin@gmail.com", passwordEncoder.encode("Admin@123"), true, admin, false);
			userRepository.save(adminUser);
		}
	}
}
