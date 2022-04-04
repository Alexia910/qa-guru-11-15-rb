package guru.qa;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class TestRb extends TestBase {

    @Test
    @DisplayName("Проверка отображения формы входа")
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
    @DisplayName("Проверка отображения информации о копирайте")
    void displayingCopyrightInform() {
        step("Наличие информации о копирайте", () -> {
            $(".copyright").shouldHave(
                    text("2007 - 2020 ПАО РОСБАНК, Генеральная лицензия №2272 от 28.01.15")
            );
        });
    }

    @Test
    @DisplayName("Проверка отображения номера телефона")
    void displayingPhoneNumber() {
        step("Наличие номера телефона", () -> {
            $(".phones").shouldHave(
                    text("8-800-200-54-34 доб. 2")
            );
        });
    }

    @Test
    @DisplayName("Переход к восстановлению пароля")
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
    @ParameterizedTest
    @DisplayName("Проверка аутентификации")
    void authenticationLoginAndPassTest(String login, String pass) {
        step("Ввод значения в поле Логин", () -> {
            $(".loginField").setValue(login);
        });
        step("Ввод значения в поле Пароль", () -> {
            $(".passwordField").setValue(pass);
        });
        step("Нажатие на кнопку входа", () -> {
            $(".actionDefault").click();
        });
        step("Проверка наличия сообщения об ошибке", () -> {
            $$(".operationExecutionPanel").find(text("Введен неверный логин или пароль. После 3 неверных"
                    + " попыток ввода пароля доступ в РОСБАНК Онлайн будет заблокирован")).shouldBe(visible);
        });
    }
}