package com.example.simple_spring_boot_rest_api.dao;

import com.example.simple_spring_boot_rest_api.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// this class is serve as a repository, and it is injected
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB
                .stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personExists = selectPersonById(id);
        if (personExists.isEmpty()) {
            return 0;
        }
        DB.remove(personExists.get());
        return 1;
    }

    @Override
    public int updatePersonByIde(UUID id, Person person) {
        return selectPersonById(id)
                .map(personToUpdate -> {
                    int indexOfPersonToDUpdate = DB.indexOf(personToUpdate);
                    if (indexOfPersonToDUpdate >= 0) {
                        DB.set(indexOfPersonToDUpdate, new Person(id, person.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
