package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.util.Arrays;
import java.util.Random;

public class DataHelper {
    private static Faker faker = new Faker();

    private DataHelper() {

    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {

        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo authInfo) {

        return new AuthInfo("artem", "1111");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authinfo) {

        return new VerificationCode("12345");
    }

    public static VerificationCode getOtherVerificationCode(AuthInfo authinfo) {

        return new VerificationCode("54321");
    }

    @Value
    public static class CardNumber {
        private String[] cardNumber;
    }

    public static CardNumber getCardsNumbers() {
        return new CardNumber(new String[]{"5559 0000 0000 0001", "5559 0000 0000 0002"});
    }

    public static String findCardPlus() {
        int lengthOfCardsArray = getCardsNumbers().cardNumber.length;
        int indexesInCardsArray = lengthOfCardsArray - 1;
        val indexTo = generateIndexForCardPlus(indexesInCardsArray);
        val cardPlus = getCardsNumbers().getCardNumber()[indexTo];
        return cardPlus;
    }

    public static String findCardMinus(String cardPlus) {
        val lengthOfCardsArray = getCardsNumbers().cardNumber.length;
        Random random = new Random();
        int index = random.nextInt(lengthOfCardsArray);
        int exclusion = Arrays.asList(getCardsNumbers().cardNumber).indexOf(cardPlus);
        while (index == exclusion) {
            index = random.nextInt(lengthOfCardsArray);
        }
        val cardMinus = getCardsNumbers().getCardNumber()[index];
        return cardMinus;
    }

    public static String getLastDigits(String cardNumber) {
        String lastDigits = cardNumber.substring(cardNumber.length() - 4);
        return lastDigits;
    }

    public static int generateIndexForCardPlus(int limit) {
        int transferToCard = faker.random().nextInt(0, limit);
        return transferToCard;
    }

    public static int generateTransferAmountInLimit(int limit) {
        return faker.random().nextInt(1, limit);
    }

    public static int generateTransferAmountOutLimit(int limit) {
        return faker.random().nextInt(limit + 1, limit+ 10000);
    }
}

