package com.cheney.demo.modle;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_user")
public class User {
    @Id
    private Integer id;
    private String name;
    private Integer age;
}
