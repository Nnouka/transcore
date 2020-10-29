package com.mungwincore.transcore.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    @Column(name = "status_before")
    private String statusBefore;
    @Column(name = "status_after")
    private String statusAfter;
    @Column(name = "changed_at")
    private LocalDateTime changedAt;
}
