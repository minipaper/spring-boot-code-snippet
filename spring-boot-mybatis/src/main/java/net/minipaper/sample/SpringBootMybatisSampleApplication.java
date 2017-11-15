package net.minipaper.sample;

import net.minipaper.sample.dao.CityDao;
import net.minipaper.sample.mapper.HotelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.minipaper.sample.mapper")
public class SpringBootMybatisSampleApplication implements CommandLineRunner {


    public SpringBootMybatisSampleApplication(CityDao cityDao, HotelMapper hotelMapper) {
        this.cityDao = cityDao;
        this.hotelMapper = hotelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisSampleApplication.class, args);
    }

    private final CityDao cityDao;

    private final HotelMapper hotelMapper;

    @Override
    public void run(String... strings) throws Exception {

        System.out.println(this.cityDao.selectCityById(1));
        System.out.println(this.cityDao.selectCityByState("CA"));

        System.out.println(this.hotelMapper.selectByCityId(1));
    }
}
