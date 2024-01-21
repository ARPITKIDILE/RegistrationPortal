package com.explore.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.explore.web.app.entity.Users;

/***
 * This points to Users Repository and will be used to query Users table
 * @author arpit
 *
 */

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	Users findByEmail(String email);
	Users findByUserName(String userName);
	Users findByEmailAndPassword(String email, String password);
	Users findByUserNameAndPassword(String username,String password);
}
