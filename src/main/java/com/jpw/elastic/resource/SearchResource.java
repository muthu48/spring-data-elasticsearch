package com.jpw.elastic.resource;

import com.jpw.elastic.model.User;
import com.jpw.elastic.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
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

    @GetMapping(value = "/user/all")
    public List<User> searchAll() {
    	logger.debug("Getting all the indexed User data");
        List<User> usersList = new ArrayList<>();
        Iterable<User> userses = userRepository.findAll();
        userses.forEach(usersList::add);
        return usersList;
    }
    
    @GetMapping(value = "/user", params = { "userName"})
    public List<User> findByUserName(@RequestParam("userName") String userName,
    		@RequestParam(value = "pageNumber", required = false) String pageNumberParam,
    		@RequestParam(value = "pageSize", required = false) String pageSizeParam) {
   	 //Sort sort = new Sort(Sort.Direction.DESC, "logdate");
     int pageNo = 0;
     int pageSize = 10;

	 if(!StringUtils.isEmpty(pageNumberParam)){
		 pageNo = Integer.parseInt(pageNumberParam);	
	 }
	 if(!StringUtils.isEmpty(pageSizeParam)){
		 pageSize = Integer.parseInt(pageSizeParam);	
	 }
	 
   	 PageRequest pageRequest = new PageRequest(pageNo, pageSize);

     return userRepository.findByUsername(userName, pageRequest);
    }


    @GetMapping(value = "/user", params = { "multiSearchText" })
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
	   	 
	  	 PageRequest pageRequest = new PageRequest(pageNo, pageSize);
	  	 //String searchText = multiSearchText == null ? null : multiSearchText;
	     List<User> users = userRepository.findByUsernameOrFirstNameOrLastName(multiSearchText, pageRequest);
		 response = new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}catch(Exception e){
		response = new ResponseEntity<String>("Error while trying with search phrase " + multiSearchText + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		logger.error("Error while trying with search phrase " + multiSearchText, e);
	}
	     return response;
    }



}
