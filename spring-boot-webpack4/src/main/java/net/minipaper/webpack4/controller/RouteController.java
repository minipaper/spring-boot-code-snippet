package net.minipaper.webpack4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {

  @RequestMapping("/")
  public String forwardIndex() {
    return "index.html";
  }

  @RequestMapping(value = "/{path:[^\\.]*}")
  public String redirect() {
    return "forward:/index.html";
  }
}
