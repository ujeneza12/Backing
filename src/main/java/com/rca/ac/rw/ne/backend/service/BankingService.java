package com.rca.ac.rw.ne.backend.service;

import com.rca.ac.rw.ne.backend.api.model.BankingModel;
import com.rca.ac.rw.ne.backend.model.Banking;
import com.rca.ac.rw.ne.backend.model.LocalUser;
import com.rca.ac.rw.ne.backend.model.Message;
import com.rca.ac.rw.ne.backend.model.dao.BankingDAO;
import com.rca.ac.rw.ne.backend.model.dao.LocalUserDAO;
import com.rca.ac.rw.ne.backend.model.dao.MessageDAO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BankingService {
    private BankingDAO bankingDAO;
    private LocalUserDAO localUserDAO;

  private MessageDAO messageDAO;
//  private final JavaMailSender javaMailSender;

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
        banking.setBanking_date_time(banking.getBanking_date_time());

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

//    private void sendEmail(String recipientEmail, String subject, String messageBody) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper;
//
//        try {
//            helper = new MimeMessageHelper(message, true);
//            helper.setTo(recipientEmail);
//            helper.setSubject(subject);
//            helper.setText(messageBody, true); // true indicates HTML format
//
//            javaMailSender.send(message);
//        } catch (MessagingException e) {
//            // Handle exception properly
//            e.printStackTrace();
//            // You can log the exception or throw a custom exception
//            throw new RuntimeException("Failed to send email", e);
//        }
//    }

    private void sendMessageToUser(LocalUser user, String transactionType, double amount, String account) {

        String messageText = String.format("Dear %s %s, your %s of %.2f on your account %s has been completed successfully.",
                user.getFirstname(), user.getLastname(), transactionType, amount, account);
        Message message = new Message();
//        sendEmail(user.getEmail(), "Transaction Notification", messageText);

        message.setUser(user);
        message.setMessage(messageText);
        message.setDateTime(LocalDateTime.now());
        messageDAO.save(message);
    }
}

