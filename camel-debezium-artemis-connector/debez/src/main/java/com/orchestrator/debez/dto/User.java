package com.orchestrator.debez.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -7776966317971749808L;

	private Long id;

	private String name;

	private String surname;

}
