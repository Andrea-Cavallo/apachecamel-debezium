package com.orchestrator.debez.transcoder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.orchestrator.debez.dto.User;

@Component
public class ArtemisTranscoder {
	
	
	  public List<UserTranscoded> transcodeListOfUsers(List<User> listOfUsers) {
	        return listOfUsers.stream()
	            .map(user -> new UserTranscoded(user.getId(), user.getName() + " " + user.getSurname()))
	            .collect(Collectors.toList());
	    }

}
