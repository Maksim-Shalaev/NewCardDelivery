package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationInfo generate(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationInfo(
                    faker.address().city(),
                    faker.name().lastName() + " " + faker.name().firstName(),
                    faker.numerify("+7##########"));
        }

        public static String generateDate(int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String getCity() {
            String[] myCityList = new String[]{"Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Владикавказ", "Горно-Алтайск", "Йошкар-Ола", "Казань", "Калининград", "Калуга", "Краснодар", "Магас", "Махачкала", "Нарьян-Мар", "Салехард", "Самара", "Саранск", "Саратов", "Хабаровск", "Ханты-Мансийск", "Южно-Сахалинск", "Великий Новгород", "Владивосток", "Владимир", "Вологда", "Рязань", "Биробиджан", "Чебоксары", "Москва", "Санкт-Петербург", "Ульяновск", "Симферополь", "Ростов-на-Дону"};
            int city = (int) Math.floor(Math.random() * myCityList.length);
            return myCityList[city];
        }
    }
}
