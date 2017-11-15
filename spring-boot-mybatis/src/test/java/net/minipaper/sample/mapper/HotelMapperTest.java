package net.minipaper.sample.mapper;

import net.minipaper.sample.domain.Hotel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@MybatisTest
public class HotelMapperTest {

    @Autowired
    private HotelMapper hotelMapper;

    @Test
    public void selectByCityId() throws Exception {
        Hotel hotel = hotelMapper.selectByCityId(1);
        assertThat(hotel.getName()).isEqualTo("Conrad Treasury Place");
        assertThat(hotel.getAddress()).isEqualTo("William & George Streets");
        assertThat(hotel.getZip()).isEqualTo("4001");
    }

}