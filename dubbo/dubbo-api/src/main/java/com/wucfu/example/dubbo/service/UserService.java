package com.wucfu.example.dubbo.service;

import java.util.Collection;

public interface UserService {

	boolean save(User user);

	boolean remove(Long userId);

	Collection<User> findAll();

}
