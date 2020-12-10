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
    @Column(name = "app_name")
    private String name;
    @Column(name = "app_key")
    private String key;
    @Column(name = "app_token_1")
    private String token1;
    @Column(name = "app_token_2")
    private String token2;
    @Column(name = "app_secret")
    private String secret;
    @OneToMany(mappedBy = "app")
    private List<Business> businesses;
}
