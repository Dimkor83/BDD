package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorBox = $("[data-test-id=error-notification]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void Verify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        Verify(verificationCode);
        return new DashboardPage();
    }

    public void invalidVerify(DataHelper.VerificationCode verificationCode) {
        Verify(verificationCode);
        errorBox.shouldBe(visible, ofSeconds(10));
        $("[data-test-id=error-notification]>.notification__title")
                .shouldHave(text("Ошибка"));
        $("[data-test-id=error-notification]>.notification__content")
                .shouldHave(text("Ошибка! \nНеверно указан код! Попробуйте ещё раз."));
    }
}
