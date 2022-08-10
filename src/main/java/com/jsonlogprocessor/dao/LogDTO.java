package com.jsonlogprocessor.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To Hold Data
 * 
 * @author Sonal Kumbhare
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

	private String id;
	
	private String state;
	
	private String type;
	
	private String host;
	
	private long timestamp;
}
