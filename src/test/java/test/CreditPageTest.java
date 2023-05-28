package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.CreditPage;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPageTest {
    private MainPage mainPage;
    private CreditPage creditPage;

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
        assertEquals("APPROVED", SQLHelper.getStatusForCredit());
    }
}
