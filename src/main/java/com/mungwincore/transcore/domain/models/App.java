package com.mungwincore.transcore.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "apps")
public class App {
    @Id
    private String id;

    private String secret;
}
