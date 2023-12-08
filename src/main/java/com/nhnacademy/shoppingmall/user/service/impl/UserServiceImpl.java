package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId) {
        //todo#4-1 회원조회
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        if (userRepository.countByUserId(user.getUserId()) != 0) {
            throw new UserAlreadyExistsException(user.getUserId());
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if (userRepository.countByUserId(user.getUserId()) == 0) {
            throw new UserNotFoundException(user.getUserId());
        }
        userRepository.update(user);
    }

    @Override
    public void updateByUser(User user) {
        if (userRepository.countByUserId(user.getUserId()) == 0) {
            throw new UserNotFoundException(user.getUserId());
        }
        userRepository.updateByUser(user);
    }

    @Override
    public void updatePoint(String userId, int pointChanged) {
        if (userRepository.countByUserId(userId) == 0) {
            throw new UserNotFoundException(userId);
        }
        int prevPoint = userRepository.findPoint(userId);
        userRepository.updatePoint(userId, pointChanged+prevPoint);
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if (userRepository.countByUserId(userId) == 0) {
            throw new UserNotFoundException(userId);
        }

        userRepository.deleteByUserId(userId);
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        Optional<User> user = userRepository.findByUserIdAndUserPassword(userId, userPassword);
        if (user.isPresent()) {
            userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
            return user.get();
        }
        throw new UserNotFoundException(userId);
    }

    @Override
    public List<User> getUsers(User.Auth auth) {
        if (userRepository.totalCount() == 0) {
            return new ArrayList<>();
        }
        return userRepository.findUsers(auth);
    }

    @Override
    public int getTotalCount() {
        return userRepository.totalCount();
    }

    @Override
    public Page<User> getUserByPage(User.Auth auth, int page, int pageSize) {
        if (userRepository.totalCount(auth) == 0) {
            throw new UserNotFoundException(auth.toString());
        }

        return userRepository.findUserByPage(auth, page, pageSize);
    }
}
