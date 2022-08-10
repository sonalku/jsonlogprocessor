package com.jsonlogprocessor.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsonlogprocessor.entity.LogEntry;
/**
 * Repository to insert record in HSQLDB
 * 
 * @author Sonal Kumbhare
 *
 */
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

}
