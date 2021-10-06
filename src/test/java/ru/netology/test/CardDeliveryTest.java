package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    DataGenerator dataGenerator = new DataGenerator();
    String name = dataGenerator.getName();
    String phone = dataGenerator.getPhone();
    String city = dataGenerator.getCity();

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys(dataGenerator.createDate(3));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".notification_status_ok").shouldBe(exist);
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + dataGenerator.createDate(3)));
        $("[data-test-id=date] input").doubleClick().sendKeys(dataGenerator.createDate(17));
        $(".button").click();
        $("[data-test-id=replan-notification]").shouldBe(exist, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $(".notification_status_ok").shouldBe(exist);
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + dataGenerator.createDate(17)));
    }
}