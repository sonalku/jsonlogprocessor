package com.jsonlogprocessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsonlogprocessor.dao.LogDTO;
import com.jsonlogprocessor.entity.LogEntry;
import com.jsonlogprocessor.repositry.LogEntryRepository;

/**
 * To process the task
 * 
 * @author Sonal Kumbhare
 *
 */
@Service
public class TaskService {

	private Logger log = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private LogEntryRepository logEntryRepository;

	private final Map<String, LogDTO> logEntryMap = new ConcurrentHashMap<String, LogDTO>();

	public void execute(String filePath) {
		log.info("##################:STARTED FILE PROCESSING:##################");
		try {
			log.info("File Path :" + filePath);
			File f = new File(filePath);
			Files.lines(Paths.get(f.getPath())).parallel().forEach(this::parseLogEntry);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("##################:STOPPED FILE PROCESSING:##################");	}
	
	/**
	 * 
	 * @param object
	 */
	private void parseLogEntry(String object) {
		JSONObject logObject;
		try {
			logObject = (JSONObject) new JSONParser().parse(object);
			String id = (String) logObject.get("id");
			String state = (String) logObject.get("state");
			String type = (String) logObject.get("type");
			String host = (String) logObject.get("host");
			long timestamp = (long) logObject.get("timestamp");

			LogDTO logDto = LogDTO.builder().id(id).type(type).state(state).host(host).timestamp(timestamp).build();

			if (!logEntryMap.containsKey(id)) {
				logEntryMap.put(id, logDto);
			} else {
				LogDTO existing = logEntryMap.get(id);
				if (existing != null) {
					
					/**
					 * As id would be always unique no need to check whether first entry in map is
					 * with "STARTED" or "FININED" Our intend is to get the difference of time stamp
					 * after getting value we are deleting it from map We took ConcurrentHashMap as
					 * its fail-safe and thread safe
					 */

					logEntryMap.remove(id);

					LogEntry logEntry = LogEntry.builder()
							.id_name(id)
							.type(type)
							.host(host)
							.duration(Math.abs(existing.getTimestamp() - timestamp))
							.alter(Math.abs(existing.getTimestamp() - timestamp) > 4 ? true : false).build();

					LogEntry savedEntry = logEntryRepository.save(logEntry);

					log.info("Thread: :", Thread.currentThread().getId() +" : "+ savedEntry.toString());
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
