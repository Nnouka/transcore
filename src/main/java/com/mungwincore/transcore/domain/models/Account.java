package com.mungwincore.transcore.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "current_balance")
    private Double currentBalance;
    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;
    @OneToMany(mappedBy = "debitAccount")
    private List<Transaction> debitTransactionList;
    @OneToMany(mappedBy = "creditAccount")
    private List<Transaction> creditTransactionList;
    @OneToMany(mappedBy = "account")
    private List<AccountBalanceHistory> accountBalanceHistoryList;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
