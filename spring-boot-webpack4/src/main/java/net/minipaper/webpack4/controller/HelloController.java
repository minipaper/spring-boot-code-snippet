package net.minipaper.webpack4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HelloController {

  @GetMapping("/hello")
  public ResponseEntity<?> hello() {
    return ResponseEntity.ok("Hello Api");
  }
}
