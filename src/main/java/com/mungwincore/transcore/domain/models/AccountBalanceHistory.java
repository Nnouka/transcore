package com.mungwincore.transcore.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account_balance_history")
public class AccountBalanceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    @Column(name = "opening_balance")
    private Double openingBalance;
    private Double movement;
    @Column(name = "closing_balance")
    private Double closingBalance;
    private Boolean direction;
    @Column(name = "changed_at")
    private LocalDateTime changedAt;
    private String comment;

}
