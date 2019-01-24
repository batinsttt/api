package com.sttt.ruby.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sttt.ruby.entities.AppUser;
import com.sttt.ruby.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8090")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value="/users")
	public ResponseEntity<List<AppUser>> getUsers(){
		List<AppUser> users = userRepository.findAll();
		return new ResponseEntity<List<AppUser>>(users, HttpStatus.OK);
	}
	@GetMapping(value="/default")
	public ResponseEntity<List<Map<String, Object>>> getJson() throws FileNotFoundException{
		String path = "default.json";
	    BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Gson gson = new Gson();
		Type resultType = new TypeToken<List<Map<String, Object>>>(){}.getType();
		List<Map<String, Object>> result = gson.fromJson(bufferedReader, resultType);
	    return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
	}
}
