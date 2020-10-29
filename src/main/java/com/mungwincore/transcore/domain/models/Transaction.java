package com.mungwincore.transcore.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "debit_account_id")
    private Account debitAccount;
    @ManyToOne
    @JoinColumn(name = "credit_account_id")
    private Account creditAccount;
    private String motive;
    private String status;
    @Column(name = "initiated_by")
    private String initiatedBy;
    @Column(name = "confirmed_by")
    private String confirmedBy;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "transaction")
    private List<TransactionHistory> transactionHistoryList;

}
