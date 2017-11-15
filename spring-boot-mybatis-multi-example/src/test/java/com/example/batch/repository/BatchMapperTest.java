package com.example.batch.repository;

import com.example.batch.repository.domain.DatabaseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchMapperTest {

    @Autowired
    private BatchMapper batchMapper;

    @Test
    public void findAll() {
        List<DatabaseInfo> databaseInfos = batchMapper.findAll();
        databaseInfos.forEach(databaseInfo -> {
            System.out.println(databaseInfo);
            assertThat(databaseInfo.getDescription()).isNotNull();
        });

    }
}