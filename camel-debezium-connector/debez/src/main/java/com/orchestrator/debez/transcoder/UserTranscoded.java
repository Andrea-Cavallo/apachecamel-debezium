package com.orchestrator.debez.transcoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTranscoded {
	
	private Long id ; 
	private String fullName;

}
