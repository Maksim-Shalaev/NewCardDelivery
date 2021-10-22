package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationCard;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.Registration.citiesTrue;
import static ru.netology.data.DataGenerator.Registration.generateDate;


public class CardDeliveryTest {
    RegistrationCard info = DataGenerator.Registration.generate("ru");

    @Test
    void shouldGenerateTestData(){

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(citiesTrue());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(4));
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $("[class=checkbox__box]").click();
        $("[class=button__text]").click();
        $(withText("Успешно!")).shouldBe(Condition.appear);
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + generateDate(4)));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generateDate(6));
        $(withText("Запланировать")).click();
        $("[class=button__text]").click();
        $("[data-test-id='replan-notification'] .notification__title").shouldBe(Condition.appear);
        $(withText("Перепланировать")).click();
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + generateDate(6)));
    }
}
