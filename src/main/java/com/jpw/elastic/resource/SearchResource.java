package com.jpw.elastic.resource;

import com.jpw.elastic.model.User;
import com.jpw.elastic.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/search")
public class SearchResource {
	public static final Logger logger = LoggerFactory.getLogger(SearchResource.class);

    @Autowired
    UserRepository userRepository;

/*    @GetMapping(value = "/name/{text}")
    public List<User> searchName(@PathVariable final String text) {
        return userRepository.findByName(text);
    }


    @GetMapping(value = "/salary/{salary}")
    public List<User> searchSalary(@PathVariable final Long salary) {
        return null;
    }
*/

    @GetMapping(value = "/all")
    public List<User> searchAll() {
    	logger.debug("Getting all the indexed User data");
        List<User> usersList = new ArrayList<>();
        Iterable<User> userses = userRepository.findAll();
        userses.forEach(usersList::add);
        return usersList;
    }


}
