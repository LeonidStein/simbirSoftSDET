package com.simbirsoft;

import com.simbirsoft.data.FakeUserData;
import com.simbirsoft.framework.util.TestType;
import com.simbirsoft.page.FormFieldsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.simbirsoft.constant.UIConst.ALERT_TEXT_AFTER_POSITIVE_SUBMIT;
import static com.simbirsoft.constant.UIConst.ALERT_TEXT_BEFORE_NEGATIVE_SUBMIT;
import static com.simbirsoft.constant.UIConst.NAME_FIELD_FILLING_PAGE;
import static com.simbirsoft.framework.util.Helper.randomFrom1To10;
import static com.simbirsoft.framework.util.Helper.repeatChar;
import static com.simbirsoft.framework.util.TestType.Type.NEGATIVE;
import static com.simbirsoft.framework.util.TestType.Type.POSITIVE;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI. Страница Form Fields")
@Execution(ExecutionMode.CONCURRENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class FormFieldsTest extends BaseTest {

    @Description("Заполнение полей Name и Password -> выбор напитков Milk и Coffee ->" +
            " выбор цвета Yellow -> выбор рандомного варианта ответа на вопрос об автоматизации -> " +
            " заполнение полей Email и Message -> нажатие на кнопку Submit." +
            " Проверка текста алерта после нажатия кнопки.")
    @DisplayName("Проверка полей, форм и текста алерта согласно тестовому заданию из части 1")
    @Link(name = "Website", url = "https://practice-automation.com/form-fields/")
    @Owner("Бурштейн Леонид Олегович")
    @Severity(CRITICAL)
    @TestType(POSITIVE)
    @Tag("ui")
    @Test
    public void testFormAndFieldsFirstPart() {

        final String expectedAlertText = ALERT_TEXT_AFTER_POSITIVE_SUBMIT.getText();

        String actualAlertText = new FormFieldsPage(getDriver())
                .fillNameInput(FakeUserData.getFirstName())
                .fillPasswordInput(FakeUserData.getPassword())
                .chooseDrinkMilkCheckbox()
                .chooseDrinkCoffeeCheckbox()
                .chooseColorYellowCheckbox()
                .selectRandomAutomationOption()
                .fillEmailInput(FakeUserData.getEmail())
                .fillMassageInputWithTools()
                .pressSubmitButton()
                .getAlertText();

        step(
                format("Сравнение текста алерта. Ожидание: '%s', факт: '%s'", expectedAlertText, actualAlertText),
                () -> assertEquals(expectedAlertText, actualAlertText,
                        "Ожидаемый текст алерта не совпадает с актуальным"));
    }

    @Description("Проверка наименования страницы (headline).")
    @DisplayName("Позитивный тест согласно тестовому заданию из части 2")
    @Link(name = "Website", url = "https://practice-automation.com/form-fields/")
    @Owner("Бурштейн Леонид Олегович")
    @Severity(CRITICAL)
    @TestType(POSITIVE)
    @Tag("ui")
    @Test
    public void testPositiveSecondPart() {

        final String expectedNamePage = NAME_FIELD_FILLING_PAGE.getText();

        String actualNamePage = new FormFieldsPage(getDriver())
                .getNameFieldFillingPage();

        step(
                format("Сравнение наименования страницы Form Fields. Ожидание: '%s', факт: '%s'",
                        expectedNamePage, actualNamePage),
                () -> assertEquals(expectedNamePage, actualNamePage,
                        "Ожидаемое наименование страницы Form Fields не совпадает с текущим"));
    }

    /*
    Этот кейс абсолютно вымышленный, т.к., данная страница не продумана для негативных сценариев.
    Представим, что при заполнении обязательного поля Name пробелом или пробелами
    и после нажатии кнопки Submit должен всплывать аллерт с сообщением: "Message not received!", но этого не происходит,
    поэтому данный тест должен упасть.
     */
    @Description("Проверка отправки данных, когда обязательное поле Name содержит пробел или пробелы." +
            " Заполнение обязательного поля Name пробелом/пробелами -> нажатие кнопки Submit")
    @DisplayName("Негативный тест согласно тестовому заданию из части 2")
    @Link(name = "Website", url = "https://practice-automation.com/form-fields/")
    @Owner("Бурштейн Леонид Олегович")
    @Severity(CRITICAL)
    @TestType(NEGATIVE)
    @Issue("BUG-122") // Симуляция задачи на исправление бага
    @Tag("ui")
    @Test
    public void testNegativeSecondPart() {

        final String expectedAlertText = ALERT_TEXT_BEFORE_NEGATIVE_SUBMIT.getText();

        String actualAlertText = new FormFieldsPage(getDriver())
                .fillNameInput(repeatChar(' ', randomFrom1To10()))
                .pressSubmitButton()
                .getAlertText();

        step(
                format("Сравнение текста алерта. Ожидание: '%s', факт: '%s'", expectedAlertText, actualAlertText),
                () -> assertEquals(expectedAlertText, actualAlertText,
                        "Ожидаемый текст алерта не совпадает с актуальным"));
    }
}
