package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;

public class CardDeliveryTest {

    public String city = getCity();
    public String name = getName();
    public String date = getDate();
    public String newDate = getNewDate();
    public String phone = getPhone();

    @Test
    void shouldPlanAndReplanMeeting() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue(city);
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).isDisplayed();
        String firstDate = $(".notification__content").getText();
        assertEquals("Встреча успешно запланирована на " + getDate(), firstDate);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(newDate);
        $(byText("Запланировать")).click();
        $(byText("Перепланировать")).click();
        $(".notification_visible").shouldHave(text("Успешно!"));
        String secondDate = $("[data-test-id='success-notification'] .notification__content").getText();
        assertEquals("Встреча успешно запланирована на " + getNewDate(), secondDate);
    }
}



