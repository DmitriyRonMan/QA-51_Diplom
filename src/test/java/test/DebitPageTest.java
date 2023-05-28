package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DebitPage;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitPageTest {
    private MainPage mainPage;
    private DebitPage debitPage;

    @BeforeEach
    void setupTest() {

        open("http://localhost:8080/");
        mainPage = new MainPage();
        debitPage = mainPage.goToDebitPage();
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Покупка картой со статусом APPROVED")
    void shouldTestBuyCardForStatusApproved() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getCardNumberForStatusApproved());
        debitPage.setSuccessNotificationVisible();
        assertEquals("APPROVED", SQLHelper.getStatusForPayment());
    }

    /*скрин 1 баг*/
    @Test
    @DisplayName("Покупка картой со статусом DECLINED")
    void shouldTestBuyCardForStatusDeclined() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getCardNumberForStatusDeclined());
        debitPage.setErrorNotificationVisible();
        assertEquals("DECLINED", SQLHelper.getStatusForPayment());
    }

    @Test
    @DisplayName("Покупка картой без статуса")
    void shouldTestBuyForCardOfNotStatus() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getNonStatusCardNumber());
        debitPage.setErrorNotificationVisible();
        assertEquals(null, SQLHelper.getStatusForPayment());
    }

    /*скрин 8 баг*/
    @Test
    @DisplayName("Покупка картой с пустым полем 'Номер карты'")
    void shouldTestThePurchaseWithAnEmptyCardNumberField() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getCardNumberForEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка картой с номером из 14 цифр")
    void shouldTestTheBuyWithA14DigitCard() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getCardNumberOf14Digits());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с нулевым номером")
    void shouldTestTheBuyWithACardNumberZero() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getCardNumberOfZero());
        debitPage.setErrorNotificationVisible();
    }

    @Test
    @DisplayName("Проверка нижнего граничного значения поля 'Месяц'")
    void shouldTestTheLowerBoundaryValueOfTheMonthField() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getTheFirstMonth());
        debitPage.setSuccessNotificationVisible();
    }

    @Test
    @DisplayName("Проверка верхнего граничного значения поля 'Месяц'")
    void shouldTestTheUpperBoundaryValueOfTheMonthField() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getTheLastMonth());
        debitPage.setSuccessNotificationVisible();
    }

    @Test
    @DisplayName("Покупка картой с нулевым полем 'Месяц'")
    void shouldTestMonthFieldOfZero() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getMonthOfZero());
        debitPage.setInvalidCardExpirationDateMessageVisible();
    }

    /*скрин 9 баг*/
    @Test
    @DisplayName("Проверка пустого поля 'Месяц'")
    void shouldTestEmptyMonthField() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getMonthEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Проверка поля 'Месяц' со значением выше верхнего граничного значения")
    void shouldTestMonthFieldForOverUpperBoundaryValue() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getMonthNotValid());
        debitPage.setInvalidCardExpirationDateMessageVisible();
    }

    /*скрин 10 баг*/
    @Test
    @DisplayName("Проверка пустого поля 'Год'")
    void shouldTestEmptyYearField() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getYearEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка картой с нулевым полем 'Год'")
    void shouldTestYearFieldOfZero() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getYearOfZero());
        debitPage.setCardExpiredMessageVisible();
    }

    @Test
    @DisplayName("Покупка с истекшим сроком действия карты")
    void shouldTestPatsValueForYearField() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getThePastValueInTheYearField());
        debitPage.setCardExpiredMessageVisible();
    }

    @Test
    @DisplayName("Покупка с ненаступившим сроком действия карты")
    void shouldTestFutureValueForYearField() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getTheFutureValueInTheYearField());
        debitPage.setInvalidCardExpirationDateMessageVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Владелец'")
    void shouldTestEmptyHolderField() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getHolderEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    /*скрин 2 баг*/
    @Test
    @DisplayName("Поле 'Владелец' состоит из одного имени")
    void shouldTestHolderWithOneName() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getHolderWithOneName());
        debitPage.setInvalidFormatVisible();
    }

    /*скрин 3 баг*/
    @Test
    @DisplayName("Значение поля 'Владелец' состоит из кириллицы")
    void shouldTestHolderInCyrillic() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getHolderInCyrillic());
        debitPage.setInvalidCharMessageVisible();
    }

    /*скрин 4 баг*/
    @Test
    @DisplayName("Значение поля 'Владелец' состоит из цифр")
    void shouldTestHolderForDigits() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getHolderFromDigits());
        debitPage.setInvalidCharMessageVisible();
    }

    /*скрин 5 баг*/
    @Test
    @DisplayName("Значение поля 'Владелец' состоит из спецсимволов")
    void shouldTestHolderForSpecialCharacters() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getHolderFromSpecialCharacters());
        debitPage.setInvalidCharMessageVisible();
    }

    /*скрин 6 баг*/
    @Test
    @DisplayName("Проверка пустого поля 'CVC/CVV'")
    void shouldTestEmptyCVCField() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getCVCEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Значение поля 'CVC/CVV' состоит из 2 цифр")
    void shouldTestCVCTwoDigits() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getCVCTwoDigits());
        debitPage.setInvalidFormatVisible();
    }

    /*скрин 7 баг*/
    @Test
    @DisplayName("Значение поля 'CVC/CVV' состоит из нулей")
    void shouldTestCVCFieldOfZero() {

        Configuration.holdBrowserOpen = true;
        debitPage.fillInCardInfo(DataHelper.getCVCOfZero());
        debitPage.setInvalidFormatVisible();
    }

}

