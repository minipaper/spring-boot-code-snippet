package net.minipaper.web.rest;

import net.minipaper.web.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class RestSampleControllerTest {
    @Test
    public void getUsers() throws Exception {

        // given
        RestTemplate restTemplate = new RestTemplate();

        Charset utf8 = Charset.forName("UTF-8");
        List<HttpMessageConverter<?>> httpMessageConverters = Arrays.asList(
                new MappingJackson2HttpMessageConverter(),
                new StringHttpMessageConverter(utf8)
        );

        restTemplate.setMessageConverters(httpMessageConverters);

        // header
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json", utf8);
        headers.setContentType(mediaType);

        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", headers);


        // url
        String url = "http://localhost:8080/users";

        // when
        // super type token
        List<User> users = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<User>>() {}).getBody();

        users.forEach(System.out::println);

        // then
        Assert.assertEquals(3, users.size());


    }

}