package com.rca.ac.rw.ne.backend.service;

import com.rca.ac.rw.ne.backend.api.model.BankingModel;
import com.rca.ac.rw.ne.backend.model.Banking;
import com.rca.ac.rw.ne.backend.model.LocalUser;
import com.rca.ac.rw.ne.backend.model.Message;
import com.rca.ac.rw.ne.backend.model.dao.BankingDAO;
import com.rca.ac.rw.ne.backend.model.dao.LocalUserDAO;
import com.rca.ac.rw.ne.backend.model.dao.MessageDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BankingService {
    private BankingDAO bankingDAO;
    private LocalUserDAO localUserDAO;

  private MessageDAO messageDAO;

    public BankingService(BankingDAO bankingDAO,LocalUserDAO localUserDAO,MessageDAO messageDAO){

        this.localUserDAO=localUserDAO;
        this.bankingDAO=bankingDAO;
        this.messageDAO=messageDAO;

    }

    @Transactional
    public Banking saveTransaction(BankingModel bankingModel) {
        LocalUser user = localUserDAO.findById(bankingModel.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        // Create a new Banking transaction
        Banking banking = new Banking();
        banking.setUser(user);
        banking.setAccount(bankingModel.getAccount());
        banking.setAmount(bankingModel.getAmount());
        banking.setType(bankingModel.getType());
        banking.setBankingDateTime(LocalDateTime.now());

        // Update user balance based on transaction type
        if (bankingModel.getType().equalsIgnoreCase("saving")) {
            user.setBalance(user.getBalance() + bankingModel.getAmount());
        } else if (bankingModel.getType().equalsIgnoreCase("withdraw")) {
            if (user.getBalance() < bankingModel.getAmount()) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            user.setBalance(user.getBalance() - bankingModel.getAmount());
        } else if (bankingModel.getType().equalsIgnoreCase("transfer")) {
            LocalUser recipient = localUserDAO.findByAccount(bankingModel.getAccount())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid recipient account"));
            if (user.getBalance() < bankingModel.getAmount()) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            user.setBalance(user.getBalance() - bankingModel.getAmount());
            recipient.setBalance(recipient.getBalance() + bankingModel.getAmount());
            localUserDAO.save(recipient); // Update recipient balance
        } else {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        localUserDAO.save(user); // Update user balance
        Banking savedBanking = bankingDAO.save(banking); // Save banking transaction

        // Send message to user about the transaction
        sendMessageToUser(user, bankingModel.getType(), bankingModel.getAmount(), bankingModel.getAccount());

        return savedBanking;
    }



    private void sendMessageToUser(LocalUser user, String transactionType, double amount, String account) {
        Message message = new Message();
        message.setUser(user);
        message.setMessage(String.format("Dear %s %s, your %s of %.2f on your account %s has been completed successfully.",
                user.getFirstname(), user.getLastname(), transactionType, amount, account));
        message.setDateTime(LocalDateTime.now());
        messageDAO.save(message);
    }
}

