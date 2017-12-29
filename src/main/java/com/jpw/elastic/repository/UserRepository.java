package com.jpw.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jpw.elastic.model.User;


public interface UserRepository extends ElasticsearchRepository<User, String> {
	User findByUserName(String UserName);

    //List<Users> findBySalary(Long salary);
}
