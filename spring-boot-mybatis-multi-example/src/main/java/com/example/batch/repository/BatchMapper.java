package com.example.batch.repository;

import com.example.batch.repository.domain.DatabaseInfo;
import com.example.batch.repository.support.BatchSchema;

import java.util.List;

@BatchSchema
public interface BatchMapper {
    List<DatabaseInfo> findAll();

    DatabaseInfo findOne(Long seq);

    void save(DatabaseInfo databaseInfo);

    void update(DatabaseInfo databaseInfo);

    void delete(DatabaseInfo databaseInfo);

}
