package com.circle.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class PingController {
  @RequestMapping(path = "/ping", method = RequestMethod.GET)
  public Map<String, String> ping() {
    Map<String, String> pong = new HashMap<>();
    pong.put("pong", "Hello, World!");
    return pong;
  }

  @RequestMapping(path = "/ping/{name}", method = RequestMethod.GET)
  public Map<String, String> ping(@PathVariable("name") String name) {
    Map<String, String> pong = new HashMap<>();
    pong.put("pong", "Hello, " + name + "!");
    return pong;
  }

  @RequestMapping(path = "/ping", method = RequestMethod.POST)
  public Map<String, String> ping(@RequestBody Map<String, String> ping) {
    Map<String, String> pong = new HashMap<>();
    pong.put("pong", "Hello, " + ping.get("name") + "!");
    return pong;
  }
}
