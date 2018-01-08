package net.minipaper.web.rest;

import lombok.extern.slf4j.Slf4j;
import net.minipaper.web.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Slf4j
@Controller
public class RestSampleController {

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(
                Arrays.asList(
                        new User("성민", 34),
                        new User("은혜", 30),
                        new User("주완", 1)
                )
        );
    }


}
