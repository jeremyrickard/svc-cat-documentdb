package com.github.jeremyrickard.svccatdocumentdb.controllers;

import java.util.List;
import java.util.ArrayList;

import com.github.jeremyrickard.svccatdocumentdb.model.User;
import com.github.jeremyrickard.svccatdocumentdb.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody; 



@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepo;

    @Value("${azure.documentdb.database}")
    private String dbname;


    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        List<User> retval;
        try {
            retval = userRepo.findAll();
        } catch (RuntimeException rte) {
            // The spring-data-documentdb library won't create the collection until
            // you try to save something. This error message is what you get when there
            // are zero collection created. In this case, return an empty ArrayList.
            // Otherwise, return the original RuntimeException
            String noCollectionMessageTemplate = "expect only one collection: User in database: %s, but found 0";
            if (rte.getMessage().equals(String.format(noCollectionMessageTemplate, dbname))) {
                retval = new ArrayList<>();
            }
            else {
                throw rte;
            }
        }
        return retval;
    }

    @RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

}