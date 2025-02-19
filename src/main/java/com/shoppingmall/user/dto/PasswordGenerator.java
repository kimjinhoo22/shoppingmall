package com.shoppingmall.user.dto;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_+=<>?";
    private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARS;
    private static final int PASSWORD_LENGTH = 10;

    public String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        // 각 카테고리에서 최소 1개씩 선택
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // 나머지 랜덤 문자 추가
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        // 비밀번호를 랜덤하게 섞기
        return shuffleString(password.toString());
    }
    private String shuffleString(String input) {
        StringBuilder shuffled = new StringBuilder(input.length());
        while (input.length() > 0) {
            int index = (int) (Math.random() * input.length());
            shuffled.append(input.charAt(index));
            input = input.substring(0, index) + input.substring(index + 1);
        }
        return shuffled.toString();
    }
}
