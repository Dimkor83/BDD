package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class UploadPage {
    private SelenideElement addMoneyHeading = $(withText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id='amount'] [type='text']");
    private SelenideElement fromField = $("[data-test-id='from'] [type='tel']");
    private SelenideElement uploadButton = $("[data-test-id='action-transfer']");
    private SelenideElement errorBox = $("[data-test-id=error-notification]");
    private SelenideElement errorNotification = $(".notification__content");

    public UploadPage() {
        addMoneyHeading.shouldBe(visible, ofSeconds(10));
    }

    public void setUploadButton (int amount, String cardForm) {
        amountField.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);
        amountField.setValue(String.valueOf(amount));
        fromField.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);
        fromField.setValue(cardForm);
        uploadButton.click();
    }

    public DashboardPage shouldTransferMoneyBetweenCards(int amount, String cardForm) {
        setUploadButton(amount, cardForm);
        return new DashboardPage();
    }

    public void shouldWarnThatTransferAmountIsOutLimit(int amount, String cardForm) {
        setUploadButton(amount, cardForm);
        errorBox.shouldBe(visible, ofSeconds(10));
        $("[data-test-id=error-notification]>.notification__title").shouldHave(text("Ошибка"));
        errorBox.shouldBe(visible);
    }

}
