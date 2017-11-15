package com.example.batch.repository;

import com.example.batch.repository.domain.DatabaseInfo;
import com.example.batch.repository.support.MysqlSchema;

import java.util.List;

@MysqlSchema
public interface MysqlMapper {
    List<DatabaseInfo> findAll();

    DatabaseInfo findOne(Long seq);

    void save(DatabaseInfo databaseInfo);

    void update(DatabaseInfo databaseInfo);

    void delete(DatabaseInfo databaseInfo);
}
