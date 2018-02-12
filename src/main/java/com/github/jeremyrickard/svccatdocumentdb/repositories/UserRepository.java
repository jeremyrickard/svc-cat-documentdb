package com.github.jeremyrickard.svccatdocumentdb.repositories;

import com.github.jeremyrickard.svccatdocumentdb.model.User;
import com.microsoft.azure.spring.data.documentdb.repository.DocumentDbRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DocumentDbRepository<User, String> {}