package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class TestRb {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("version", "34");

        //password and user for remote browser
        String user = System.getProperty("user");
        String password = System.getProperty("password");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.remote = "https://" + user + ":" + password + "@" + System.getProperty("remoteBrowser");

    @BeforeEach
    void precondition() {
        open("https://online.rosbank.ru/ibank/");
    }

    @AfterEach
    void closedBrowser() {
        closeWebDriver();
    }

    @Test
    void displayingLoginForm() {
        step("Наличие названия формы", () -> {
            $$(".operationHeader").find(text("Вход")).shouldBe(visible);
        });
        step("Наличие кнопок", () -> {
            $(".actionDefault").shouldBe(visible);
            $(".registerAction").shouldBe(visible);
        });
    }

    @Test
    void displayingCopyrightInform() {
        step("Наличие информации о копирайте", () -> {
            $(".copyright").shouldHave(
                    text("2007 - 2020 ПАО РОСБАНК, Генеральная лицензия №2272 от 28.01.15")
            );
        });
    }

    @Test
    void displayingPhoneNumber() {
        step("Наличие номера телефона", () -> {
            $(".phones").shouldHave(
                    text("8-800-200-54-34 доб. 2")
            );
        });
    }

    @Test
    void openPassRecovery() {
        step("Нажатие на кнопку перехода к восстановлению пароля", () -> {
            $(".restorePasswordAction").click();
        });
        step("Отображение формы восстановления пароля", () -> {
            $(".operationHeader").shouldHave(
                    text("Восстановление пароля")
            );
        });
    }

    static Stream<Arguments> argumentsForSecondTest() {
        return Stream.of(
                Arguments.of("58", "qwerty"),
                Arguments.of("_g", "5646")
        );
    }

    @MethodSource(value = "argumentsForSecondTest")
    @ParameterizedTest(name = "Проверка аутентификации")
    void authenticationLoginAndPassTest(String login, String pass) {
        step("Ввод значения в поле Логин", () -> {
            $(".loginField").setValue(login);
        });
        step("Ввод значения в поле Пароль", () -> {
            $(".passwordField").setValue(pass);
        });
        step("Нажати на кнопку входа", () -> {
            $(".actionDefault").click();
        });
        step("Проверка наличия сообщения об ошибке", () -> {
            $$(".operationExecutionPanel").find(text("Введен неверный логин или пароль. После 3 неверных"
                    + " попыток ввода пароля доступ в РОСБАНК Онлайн будет заблокирован")).shouldBe(visible);
        });
    }
}