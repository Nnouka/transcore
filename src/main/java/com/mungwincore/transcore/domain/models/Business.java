package com.mungwincore.transcore.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "app_id")
    private App app;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToMany
    @JoinTable(
        name = "business_contact",
        joinColumns = @JoinColumn(name = "business_id"),
        inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private List<BusinessContact> businessContacts;
    @OneToMany(mappedBy = "business")
    private List<Account> accountList;
    @OneToMany(mappedBy = "business")
    private List<Transaction> transactionList;

}
