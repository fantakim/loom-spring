package com.scumm.loom.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.scumm.loom.dto.user.CreateUserDto;
import com.scumm.loom.dto.user.UpdateUserDto;
import com.scumm.loom.exception.ResourceNotFoundException;
import com.scumm.loom.model.QUser;
import com.scumm.loom.model.User;
import com.scumm.loom.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService extends QuerydslRepositorySupport {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(User.class);
        this.userRepository = userRepository;
    }

    public User create(CreateUserDto dto) {
        final User user = new User(dto.getEmail(), dto.getUsername());
        return userRepository.save(user);
    }

    public User update(long id, UpdateUserDto dto) {
        final User user = find(id);
        user.update(dto.getEmail(), dto.getUsername());
        return userRepository.save(user);
    }

    public void delete(long id) {
        final User user = find(id);
        userRepository.delete(user);
    }

    public User find(long id) {
        final Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new ResourceNotFoundException("user"));
        return user.get();
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<User> search(String keyword, Pageable pageable) {
        final QUser user = QUser.user;
        JPQLQuery<User> query;

        BooleanBuilder whereClause = new BooleanBuilder();
        whereClause.or(user.email.containsIgnoreCase(keyword));
        whereClause.or(user.username.containsIgnoreCase(keyword));

        query = from(user).where(whereClause);

        final List<User> users = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(users, pageable, query.fetchCount());
    }
}
