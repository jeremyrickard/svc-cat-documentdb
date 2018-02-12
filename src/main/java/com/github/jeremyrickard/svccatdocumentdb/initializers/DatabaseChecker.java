package com.github.jeremyrickard.svccatdocumentdb.initializers;

import java.util.List;

import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.spring.data.documentdb.DocumentDbFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DatabaseChecker {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DocumentDbFactory factory;

    @Value("${azure.documentdb.database}")
    private String databaseName;
    
    @EventListener(ApplicationReadyEvent.class)
    public void checkDatabase() {

        DocumentClient client = factory.getDocumentClient();
       
        String queryString = String.format("SELECT * FROM root r WHERE r.id='%s'", databaseName);
        List<Database> databaseList = client.queryDatabases(queryString, null).getQueryIterable().toList();
        if (databaseList.size() < 1) {
            try { 
                Database database = new Database();
                database.setId(databaseName);   
                client.createDatabase(database, null).getResource();
            } catch (DocumentClientException dce) {
                logger.error("Unable to create databae", dce);
            }
        }      
    }
}

