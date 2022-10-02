package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class TransferPage {
    private SelenideElement addMoneyHeading = $(withText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id='amount'] [type='text']");
    private SelenideElement fromField = $("[data-test-id='from'] [type='tel']");
    private SelenideElement uploadButton = $("[data-test-id='action-transfer']");
    private SelenideElement errorBox = $("[data-test-id=error-message]");

    public TransferPage() {
        addMoneyHeading.shouldBe(visible, ofSeconds(10));
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardinfo) {
        makeTransfer(amountToTransfer, cardinfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountField.setValue(amountToTransfer);
        fromField.setValue(cardInfo.getCardNumber());
        uploadButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorBox.shouldBe(exactText(expectedText), Duration.ofSeconds(10)).shouldBe(visible);
    }

}
