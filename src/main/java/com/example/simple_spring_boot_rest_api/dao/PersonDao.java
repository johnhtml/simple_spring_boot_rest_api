package com.example.simple_spring_boot_rest_api.dao;

import com.example.simple_spring_boot_rest_api.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    // Method overload
    int insertPerson(UUID id, Person person);
    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> getAllPeople();

    Optional<Person> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonByIde(UUID id, Person person);
}
