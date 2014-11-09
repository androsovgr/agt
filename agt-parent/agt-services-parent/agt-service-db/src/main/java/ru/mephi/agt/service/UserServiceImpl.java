package ru.mephi.agt.service;

import javax.ejb.Local;
import javax.ejb.Stateless;

import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.response.UserResponse;

@Stateless
@Local(UserServiceInterface.class)
public class UserServiceImpl implements UserServiceInterface {
	
	@Override
	public UserResponse getUserById(IdRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
