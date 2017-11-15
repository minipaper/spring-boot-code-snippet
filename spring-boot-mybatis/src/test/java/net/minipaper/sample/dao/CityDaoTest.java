package net.minipaper.sample.dao;

import net.minipaper.sample.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@Import(CityDao.class)
@MybatisTest
public class CityDaoTest {

    @Autowired
    private CityDao cityDao;

    @Test
    public void selectCityByIdTest() {
        City city = cityDao.selectCityById(1);
        assertThat(city.getName()).isEqualTo("San Francisco");
        assertThat(city.getState()).isEqualTo("CA");
        assertThat(city.getCountry()).isEqualTo("US");
    }
}