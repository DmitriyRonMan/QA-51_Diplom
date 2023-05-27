package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPage {
    private MainPage mainPage;
    private page.CreditPage creditPage;

    @BeforeEach
    void setupTest() {

        open("http://localhost:8080/");
        mainPage = new MainPage();
        creditPage = mainPage.goToCreditPage();
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Покупка картой со статусом APPROVED")
    void shouldTestBuyCardForStatusApproved() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getCardNumberForStatusApproved());
        creditPage.setSuccessNotificationVisible();
        assertEquals("APPROVED", SQLHelper.getStatusForPayment());
    }

    @Test
    @DisplayName("Покупка картой со статусом DECLINED")
    void shouldTestBuyCardForStatusDeclined() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getCardNumberForStatusDeclined());
        creditPage.setErrorNotificationVisible();
        assertEquals("DECLINED", SQLHelper.getStatusForPayment());
    }

    @Test
    @DisplayName("Покупка картой без статуса")
    void shouldTestBuyForCardOfNotStatus() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getNonStatusCardNumber());
        creditPage.setSuccessNotificationVisible();
        assertEquals(null, SQLHelper.getStatusForPayment());
    }

    @Test
    @DisplayName("Покупка картой с пустым полем 'Номер карты'")
    void shouldTestThePurchaseWithAnEmptyCardNumberField() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getCardNumberForEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка картой с номером из 14 цифр")
    void shouldTestTheBuyWithA14DigitCard() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getCardNumberOf14Digits());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с нулевым номером")
    void shouldTestTheBuyWithACardNumberZero() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getCardNumberOfZero());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Проверка нижнего граничного значения поля 'Месяц'")
    void shouldTestTheLowerBoundaryValueOfTheMonthField() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getTheFirstMonth());
        creditPage.setSuccessNotificationVisible();
    }

    @Test
    @DisplayName("Проверка верхнего граничного значения поля 'Месяц'")
    void shouldTestTheUpperBoundaryValueOfTheMonthField() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getTheLastMonth());
        creditPage.setSuccessNotificationVisible();
    }

    @Test
    @DisplayName("Покупка картой с нулевым полем 'Месяц'")
    void shouldTestMonthFieldOfZero() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getMonthOfZero());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Месяц'")
    void shouldTestEmptyMonthField() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getMonthEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Проверка поля 'Месяц' со значением выше верхнего граничного значения")
    void shouldTestMonthFieldForOverUpperBoundaryValue() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getMonthNotValid());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Год'")
    void shouldTestEmptyYearField() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getYearEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка картой с нулевым полем 'Год'")
    void shouldTestYearFieldOfZero() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getYearOfZero());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка с истекшим сроком действия карты")
    void shouldTestPatsValueForYearField() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getThePastValueInTheYearField());
        creditPage.setCardExpiredMessageVisible();
    }

    @Test
    @DisplayName("Покупка с ненаступившим сроком действия карты")
    void shouldTestFutureValueForYearField() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getTheFutureValueInTheYearField());
        creditPage.setInvalidCardExpirationDateMessageVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Владелец'")
    void shouldTestEmptyHolderField() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getHolderEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Поле 'Владелец' состоит из одного имени")
    void shouldTestHolderWithOneName() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getHolderWithOneName());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' состоит из кириллицы")
    void shouldTestHolderInCyrillic() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getHolderInCyrillic());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' состоит из цифр")
    void shouldTestHolderForDigits() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getHolderFromDigits());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' состоит из спецсимволов")
    void shouldTestHolderForSpecialCharacters() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getHolderFromSpecialCharacters());
        creditPage.setInvalidCharMessageVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'CVC/CVV'")
    void shouldTestEmptyCVCField() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getCVCEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Значение поля 'CVC/CVV' состоит из 2 цифр")
    void shouldTestCVCTwoDigits() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getCVCTwoDigits());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Значение поля 'CVC/CVV' состоит из нулей")
    void shouldTestCVCFieldOfZero() {

        Configuration.holdBrowserOpen = true;
        creditPage.fillInCardInfo(DataHelper.getCVCOfZero());
        creditPage.setInvalidFormatVisible();
    }
}
