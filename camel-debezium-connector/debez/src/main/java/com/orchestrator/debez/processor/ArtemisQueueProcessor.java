package com.orchestrator.debez.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.orchestrator.debez.dto.User;
import com.orchestrator.debez.transcoder.ArtemisTranscoder;
import com.orchestrator.debez.transcoder.UserTranscoded;
import static com.orchestrator.debez.utils.JsonUtils.toJson;
import static com.orchestrator.debez.utils.JsonUtils.getMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ArtemisQueueProcessor implements Processor {

	private final ArtemisTranscoder artemisTranscoder;

	@Override
	public void process(Exchange exchange) throws Exception {
	
		String body = exchange.getIn().getBody(String.class);
		List<User> userList = getMapper().readValue(body, new TypeReference<List<User>>(){});	
		List<UserTranscoded> transcodedList = artemisTranscoder.transcodeListOfUsers(userList);
		String transcodedListSerialized = toJson(transcodedList);
		log.info("Transcoded list of users is {}", transcodedListSerialized);
		
		exchange.getIn().setBody(transcodedListSerialized);
	}
}