package com.mungwincore.transcore.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "apps")
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    private String secret;
    @OneToMany(mappedBy = "app")
    private List<Business> businesses;
}
