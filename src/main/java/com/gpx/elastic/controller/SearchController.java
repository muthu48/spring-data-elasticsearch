package com.gpx.elastic.controller;

import com.gpx.elastic.model.User;
import com.gpx.elastic.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
	public static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    UserRepository userRepository;
    
	@Autowired
	BuildProperties buildProperties;
/*    @GetMapping(value = "/name/{text}")
    public List<User> searchName(@PathVariable final String text) {
        return userRepository.findByName(text);
    }


    @GetMapping(value = "/salary/{salary}")
    public List<User> searchSalary(@PathVariable final Long salary) {
        return null;
    }
*/

/*
 * @GetMapping(value = "/user/all") public List<User> searchAll() {
 * logger.debug("Getting all the indexed User data"); List<User> usersList = new
 * ArrayList<>(); Iterable<User> userses = userRepository.findAll();
 * userses.forEach(usersList::add); return usersList; }
 * 
 * @GetMapping(value = "/user", params = { "userName"}) public List<User>
 * findByUserName(@RequestParam("userName") String userName,
 * 
 * @RequestParam(value = "pageNumber", required = false) String pageNumberParam,
 * 
 * @RequestParam(value = "pageSize", required = false) String pageSizeParam) {
 * //Sort sort = new Sort(Sort.Direction.DESC, "logdate"); int pageNo = 0; int
 * pageSize = 10;
 * 
 * if(!StringUtils.isEmpty(pageNumberParam)){ pageNo =
 * Integer.parseInt(pageNumberParam); } if(!StringUtils.isEmpty(pageSizeParam)){
 * pageSize = Integer.parseInt(pageSizeParam); }
 * 
 * PageRequest pageRequest = new PageRequest(pageNo, pageSize);
 * 
 * return userRepository.findByUsername(userName, pageRequest); }
 */
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> findByUsernameOrFirstNameOrLastName(@RequestParam("multiSearchText") String multiSearchText,
    		@RequestParam(value = "pageNumber", required = false) String pageNumberParam,
    		@RequestParam(value = "pageSize", required = false) String pageSizeParam) {
     // Ensure no leading or trailing spaces to avoid pulling all records due to wildcard search
		ResponseEntity response = null;

		 int pageNo = 0;
	     int pageSize = 10;
	try{
	  	 if(StringUtils.isEmpty(multiSearchText)){
	  		 throw new Exception("Please provide search criteria");
	  	 }
	  	 
	   	 if(!StringUtils.isEmpty(pageNumberParam)){
	   		 pageNo = Integer.parseInt(pageNumberParam);	
	   	 }
	   	 if(!StringUtils.isEmpty(pageSizeParam)){
	   		 pageSize = Integer.parseInt(pageSizeParam);	
	   	 }
	   	 
	  	 //PageRequest pageRequest = new PageRequest(pageNo, pageSize);
	  	 //String searchText = multiSearchText == null ? null : multiSearchText;
	     List<User> users = userRepository.findByUsernameOrFirstNameOrLastName(multiSearchText, PageRequest.of(pageNo, pageSize));
		 response = new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}catch(Exception e){
		response = new ResponseEntity<String>("Error while trying with search phrase " + multiSearchText + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		logger.error("Error while trying with search phrase " + multiSearchText, e);
	}
	     return response;
    }

	@RequestMapping(value = "/buildInfo", method = RequestMethod.GET)
	public String ping() {
		String version = buildProperties.getVersion();

		return "Service is available, build version " + version;
	}
	
    @GetMapping(value = "/user/getAll")
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}
