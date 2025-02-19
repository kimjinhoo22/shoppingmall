package com.shoppingmall.user.service;

import com.shoppingmall.user.dto.PasswordGenerator;
import com.shoppingmall.user.model.User;
import com.shoppingmall.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    private JavaMailSender mailSender;
    private UserRepository userRepository;
    private PasswordGenerator passwordGenerator;
    private PasswordEncoder passwordEncoder;

    public EmailService(JavaMailSender mailSender , UserRepository userRepository , PasswordGenerator passwordGenerator, PasswordEncoder passwordEncoder) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }

    public void findPassword(String userId , String email) throws MessagingException {
        User user = userRepository.findByUserIdAndEmail(userId , email);
        if(user != null) {
            String newPass = passwordGenerator.generateTemporaryPassword();
            sendEmail(email,newPass);
        }
    }

    public void sendEmail(String email , String pass) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("(반려동물앱) 임시 비밀번호 발급");
        String content = "<p>발급된 임시 비밀번호 입니다. 빠른 시일내에 비밀번호를 변경해주세요."
        + "임시 비밀번호 : " + pass;

        helper.setText(content, true);
        mailSender.send(message);
    }
}
