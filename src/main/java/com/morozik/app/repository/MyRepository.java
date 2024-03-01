package com.morozik.app.repository;

import com.morozik.app.model.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MyRepository extends JpaRepository<MyEntity, Long> {

    boolean existsByStringValue(String name);

    void deleteByStringValue(String name);

    @Query(value = "SELECT int_value from my_entity where string_value=:name", nativeQuery = true)
    Optional<Integer> findIntValueByStringValue(@Param("name") String name);
}
