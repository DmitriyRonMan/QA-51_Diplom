package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {

    /*для полей*/
    private SelenideElement heading = $$("h3").find(text("Оплата по карте"));
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement buttonContinue = $$("button").find(exactText("Продолжить"));

    /*для вывода сообщений*/
    private SelenideElement successNOTIF = $(".notification_status_ok");
    private SelenideElement errorNOTIF = $(".notification_status_error");
    private SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));
    private SelenideElement invalidFormat = $(byText("Неверный формат"));
    private SelenideElement invalidCharMessage = $(byText("Поле содержит недопустимые символы"));
    private SelenideElement invalidCardExpirationDateMessage = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredMessage = $(byText("Истёк срок действия карты"));

    /*для пустой формы*/

    private SelenideElement verificationErrorNumber = $$(".input__inner").findBy(text("Номер карты")).$(".input__sub");
    private SelenideElement verificationErrorMonth = $$(".input__inner").findBy(text("Месяц")).$(".input__sub");
    private SelenideElement verificationErrorYear = $$(".input__inner").findBy(text("Год")).$(".input__sub");
    private SelenideElement verificationErrorOwner = $$(".input__inner").findBy(text("Владелец")).$(".input__sub");
    private SelenideElement verificationErrorCVV = $$(".input__inner").findBy(text("CVC/CVV")).$(".input__sub");

    public DebitPage() {
        heading.shouldBe(visible);
    }

    public void fillInCardInfo(DataHelper.CardInfo cardInfo) {
        cardNumberField.sendKeys(cardInfo.getCardNumber());
        monthField.sendKeys(cardInfo.getMonth());
        yearField.sendKeys(cardInfo.getYear());
        holderField.sendKeys(cardInfo.getHolder());
        cvcField.sendKeys(cardInfo.getCvc());
        buttonContinue.click();
    }

    public void setErrorFor() {
        verificationErrorNumber.$(byText("Поле содержит недопустимые символы"));
        verificationErrorMonth.$(byText("Поле содержит недопустимые символы"));
        verificationErrorYear.$(byText("Поле содержит недопустимые символы"));
        verificationErrorOwner.$(byText("Поле содержит недопустимые символы"));
        verificationErrorCVV.$(byText("Поле содержит недопустимые символы"));

    }

    public void setSuccessNotificationVisible() {
        successNOTIF.shouldBe(visible, Duration.ofSeconds(12));
    }

    public void setErrorNotificationVisible() {
        errorNOTIF.shouldBe(visible, Duration.ofSeconds(12));
    }

    public void setRequiredFieldVisible() {
        requiredField.shouldBe(visible, Duration.ofSeconds(12));
    }

    public void setInvalidFormatVisible() {
        invalidFormat.shouldBe(visible, Duration.ofSeconds(12));
    }

    public void setInvalidCharMessageVisible() {
        invalidCharMessage.shouldBe(visible, Duration.ofSeconds(12));
    }

    public void setInvalidCardExpirationDateMessageVisible() {
        invalidCardExpirationDateMessage.shouldBe(visible, Duration.ofSeconds(12));
    }

    public void setCardExpiredMessageVisible() {
        cardExpiredMessage.shouldBe(visible, Duration.ofSeconds(12));
    }
}
