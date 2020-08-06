package com.gpx.elastic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gpx.elastic.model.User;

public interface UserRepository extends ElasticsearchRepository<User, String> {
	List<User> findByUsername(String username, Pageable pageable);
	
	User findByUsername(String Username);
  
	@Query(" {" + 
    "      \"bool\": {" + 
    "        \"must\": [" + 
    "          {\"query_string\" : {" + 
    "            \"query\" : \"?0*\"," + 
    "            \"fields\": [" +
    "       \"username\", " +
    "       \"full_name\", " +
    "       \"firstName\", " +
    "       \"lastName\"" +
    "       ]" + 
    "            }" + 
    "          }" + 
    "        ]" + 
    "      }" + 
    "    }" + 
    "")
    List<User> findByUsernameOrFirstNameOrLastName(String text, Pageable pageable);
}
