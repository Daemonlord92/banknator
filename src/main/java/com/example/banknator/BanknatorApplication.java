package com.example.banknator;

import com.example.banknator.Enums.AccountType;
import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.Enums.BankPosition;
import com.example.banknator.Enums.TransactionType;
import com.example.banknator.accounts.AccountService;
import com.example.banknator.accounts.dto.PostNewAccountInformation;
import com.example.banknator.applications.HiringApplicationService;
import com.example.banknator.applications.dto.PostNewHireApp;
import com.example.banknator.applications.dto.PostUpdateHireApp;
import com.example.banknator.bank.BankService;
import com.example.banknator.bank.dto.PostNewBank;
import com.example.banknator.entity.HiringApplication;
import com.example.banknator.transactions.TransactionService;
import com.example.banknator.transactions.dto.PostNewTransaction;
import com.example.banknator.users.UserService;
import com.example.banknator.users.dto.PostNewUserInformation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BanknatorApplication implements CommandLineRunner {
    protected final UserService userService;
    protected final AccountService accountService;
    protected final TransactionService transactionService;
    protected final BankService bankService;

    protected final HiringApplicationService hiringApplicationService;
    private final Logger logger = LoggerFactory.getLogger(BanknatorApplication.class);

    public BanknatorApplication(UserService userService, AccountService accountService, TransactionService transactionService, BankService bankService, HiringApplicationService hiringApplicationService) {
        this.userService = userService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.bankService = bankService;
        this.hiringApplicationService = hiringApplicationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BanknatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(System.getenv().get("OS"));
        userService.createUserInformation(new PostNewUserInformation("John", "Doe", "john.doe@example.com", "password123",
                "123 Main St, Cityville", 1234567890, 750, "1990-01-01"));
        userService.createUserInformation(new PostNewUserInformation("Jane", "Smith", "jane.smith@example.com", "securePassword",
                "456 Oak St, Townsville", 9876543210L, 800, "1985-05-15"));
        logger.info(userService.createUserInformation(new PostNewUserInformation("Matthew", "Martin", "admin@horrorbank.com", "Gudmord92!",
                "106 Mace St", 5036803865L, 517, "1992-07-21")).toString());
        logger.info("Creating Bank: "+bankService.createBank(new PostNewBank("Horrorbank Bristol Branch", 50000000.00)).message());
        hiringApplicationService.createHiringApp(new PostNewHireApp(1L, BankPosition.IT_SUPPORT, 500000.00, 3L));
        hiringApplicationService.updateApplicationStatus(ApplicationStatus.APPROVED, hiringApplicationService.getAllApps().getFirst());
        hiringApplicationService.createHiringApp(new PostNewHireApp(1L, BankPosition.TELLER, 35000.00, 1L));
        hiringApplicationService.createHiringApp(new PostNewHireApp(1L, BankPosition.GENERAL_MANAGER, 450000.00, 2L));
        accountService.createAccount(new PostNewAccountInformation(1, AccountType.CHECKING, 0.0));
        accountService.createAccount(new PostNewAccountInformation(1, AccountType.SAVING, 0.0));
        accountService.createAccount(new PostNewAccountInformation(2, AccountType.CHECKING, 0.0));
        accountService.createAccount(new PostNewAccountInformation(2, AccountType.SAVING, 0.0));
        accountService.createAccount(new PostNewAccountInformation(3, AccountType.CHECKING, 0.0));
        accountService.createAccount(new PostNewAccountInformation(3, AccountType.SAVING, 0.0));
        accountService.createAccount(new PostNewAccountInformation(3, AccountType.LOAN, 1500.00));
        accountService.createAccount(new PostNewAccountInformation(3, AccountType.CREDIT, 500.00));
        transactionService.createTransaction(new PostNewTransaction(0, 1, 5348.96, TransactionType.DEPOSIT));
        transactionService.createTransaction(new PostNewTransaction(0, 2, 15150.00, TransactionType.DEPOSIT));
        transactionService.createTransaction(new PostNewTransaction(0, 3, 5348.96, TransactionType.DEPOSIT));
        transactionService.createTransaction(new PostNewTransaction(0, 4, 15150.00, TransactionType.DEPOSIT));
        transactionService.createTransaction(new PostNewTransaction(0, 5, 5348.96, TransactionType.DEPOSIT));
        transactionService.createTransaction(new PostNewTransaction(0, 6, 15150.00, TransactionType.DEPOSIT));
        transactionService.createTransaction(new PostNewTransaction(0, 5, 5348.96, TransactionType.DEPOSIT));
        transactionService.createTransaction(new PostNewTransaction(0, 5, 15150.00, TransactionType.DEPOSIT));
        transactionService.createTransaction(new PostNewTransaction(0, 5, 5348.96, TransactionType.DEPOSIT));
        transactionService.createTransaction(new PostNewTransaction(0, 5, 15150.00, TransactionType.DEPOSIT));
        Thread.sleep(60 * 1000);
        transactionService.createTransaction(new PostNewTransaction(1, 2, 50.25, TransactionType.WITHDRAW));

    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Banknator API")
                        .description("An spring banking application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
