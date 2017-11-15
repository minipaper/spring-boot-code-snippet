package net.minipaper.sample;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisSampleApplicationTests {

    @ClassRule
    public static OutputCapture out = new OutputCapture();

    @Test
    public void test() {
        String output = out.toString();
        assertThat(output).contains("City{id=1, name='San Francisco', state='CA', country='US'}");
        assertThat(output).contains("Hotel{city=1, name='Conrad Treasury Place', address='William & George Streets', zip='4001'}");
    }

}
