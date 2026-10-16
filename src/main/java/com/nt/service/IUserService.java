package com.nt.service;

import java.util.List;

import com.nt.entity.User;

public interface IUserService {
	public User registerUser(User user);
	public User loginUser(String email, String password);
	public User getUserById(Long id);
	public List<User> getAllUsers() ;
	public void deleteUser(Long id) ;
	
}
