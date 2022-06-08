package com.example.simple_spring_boot_rest_api.dao;

import com.example.simple_spring_boot_rest_api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO person (id, name) VALUES (?,?)";
        return jdbcTemplate.update(sql, id, person.getName());
    }

    @Override
    public List<Person> getAllPeople() {
        final String sql = "SELECT id, name FROM person";
        try {
            return jdbcTemplate.query(sql, (resultSet, i) -> new Person(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name")));
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id=?";
        try {
            Person person = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    (resultSet, i) -> new Person(
                            UUID.fromString(resultSet.getString("id")),
                            resultSet.getString("name"))
            );
            return Optional.of(person);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM person WHERE id=?";
        try {
            jdbcTemplate.update(sql, id);
            return 1;
        } catch (Error e) {
            return 0;
        }
    }

    @Override
    public int updatePersonByIde(UUID id, Person person) {
        try {
            jdbcTemplate.update("UPDATE person SET name=? WHERE id=?",
                    new Object[]{person.getName(), id}
            );
            return 1;
        } catch (Error e) {
            return 0;
        }
    }
}
