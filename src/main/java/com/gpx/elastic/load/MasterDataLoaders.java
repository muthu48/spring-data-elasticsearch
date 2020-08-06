package com.gpx.elastic.load;

import com.gpx.elastic.model.Users;
import com.gpx.elastic.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//@Component
//DO NOT NEED THIS LOADING, data would be available from Elastic Search
public class MasterDataLoaders {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void loadAll(){

        operations.putMapping(Users.class);
        System.out.println("Loading Data");
        //userRepository.save(getData());
        System.out.printf("Loading Completed");

    }

    private List<Users> getData() {
        List<Users> userses = new ArrayList<>();
        userses.add(new Users("Ajay",123L, "Accounting", 12000L));
        userses.add(new Users("Techie",1230L, "Accounting", 12000L));
        userses.add(new Users("Jaga",1234L, "Finance", 22000L));
        userses.add(new Users("Shiva",124L, "Tech", 12000L));
        userses.add(new Users("Karthick",14L, "Tech", 12000L));
        userses.add(new Users("Bhuvansesh",4L, "Accounting", 12000L));
        userses.add(new Users("Kumaran",1L, "Tech", 12000L));
        userses.add(new Users("Thiru",1235L, "Accounting", 12000L));
        
        return userses;
    }
}
