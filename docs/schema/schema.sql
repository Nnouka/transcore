CREATE TABLE IF NOT EXISTS apps (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    app_name VARCHAR(256) NULL ,
    app_key VARCHAR (256) NOT NULL ,
    app_secret VARCHAR (256) NOT NULL
);

CREATE TABLE IF NOT EXISTS businesses (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR (256) NULL ,
    app_id BIGINT NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint fk_ap_k foreign key (app_id) references apps(id)
);

CREATE TABLE IF NOT EXISTS contacts (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(256) NULL ,
    email VARCHAR(256) NOT NULL ,
    phone VARCHAR(256) NULL
);

CREATE TABLE IF NOT EXISTS business_contact (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    contact_id BIGINT NOT NULL ,
    business_id BIGINT NOT NULL ,
    constraint fk_cnt_k foreign key (contact_id) references contacts(id),
    constraint fk_biz_k foreign key (business_id) references businesses(id)
);

CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(256),
    current_balance DOUBLE NOT NULL DEFAULT 0,
    business_id BIGINT NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint fk_biz_ac_k foreign key (business_id) references businesses(id)
);

CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
    debit_account_id BIGINT NOT NULL ,
    credit_account_id BIGINT NOT NULL ,
    code VARCHAR(256) NOT NULL,
    motive VARCHAR(100) NOT NULL ,
    status VARCHAR(50) NOT NULL,
    initiated_by VARCHAR(256) NOT NULL ,
    confirmed_by VARCHAR(256) NULL,
    amount DOUBLE NOT NULL ,
    business_id BIGINT NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint fk_biz_tr_k foreign key (business_id) references businesses(id),
    CONSTRAINT fk_accounts_dr_id FOREIGN KEY (debit_account_id) REFERENCES accounts (id),
    CONSTRAINT fk_accounts_cr_id FOREIGN KEY (credit_account_id) REFERENCES accounts (id)
);
CREATE TABLE IF NOT EXISTS transaction_history (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
    transaction_id BIGINT NOT NULL,
    status_before VARCHAR(50) NOT NULL,
    status_after VARCHAR(50) NOT NULL ,
    changed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transactions_id FOREIGN KEY (transaction_id) REFERENCES transactions (id)
);
CREATE TABLE IF NOT EXISTS account_balance_history (
   id BIGINT AUTO_INCREMENT PRIMARY KEY ,
   account_id BIGINT  NOT NULL ,
   transaction_id BIGINT  NOT NULL,
   opening_balance DOUBLE NOT NULL DEFAULT 0,
   movement DOUBLE NOT NULL DEFAULT 0,
   closing_balance DOUBLE NOT NULL DEFAULT 0,
   direction BOOLEAN NOT NULL DEFAULT '1',
   changed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   comment VARCHAR(256) NULL,
   CONSTRAINT fk_accounts_id FOREIGN KEY (account_id) REFERENCES accounts (id),
   CONSTRAINT fk_transactions_bl_id FOREIGN KEY (transaction_id) REFERENCES transactions (id)
);

