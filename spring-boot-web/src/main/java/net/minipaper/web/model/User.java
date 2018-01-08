package net.minipaper.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class User {
    private String name;
    private int age;
}
