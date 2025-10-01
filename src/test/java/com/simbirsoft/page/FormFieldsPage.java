package com.simbirsoft.page;

import io.qameta.allure.Step;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.simbirsoft.constant.WaitSecConst.TEN_SECONDS;

public final class FormFieldsPage extends BasePage {

    public FormFieldsPage(final @NonNull WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1[itemprop='headline']")
    public WebElement nameFieldFillingPage;

    @FindBy(id = "name-input")
    private WebElement nameInputField;

    @FindBy(css = "input[type='password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//input[@id='drink2']")
    private WebElement drinkMilkCheckbox;

    @FindBy(css = "input[id=drink3]")
    private WebElement drinkCoffeeCheckbox;

    @FindBy(css = "input[id=color3]")
    private WebElement colorYellowRadioButton;

    @FindBy(css = "select[id='automation']")
    private WebElement automationDropList;

    @FindBy(xpath = "//label[text()='Automation tools']/following-sibling::ul/li")
    private List<WebElement> automationToolsList;

    @FindBy(css = "input[id='email']")
    private WebElement emailInputField;

    @FindBy(css = "textarea[id=message]")
    private WebElement messageInputField;

    @FindBy(css = "button[id='submit-btn']")
    private WebElement submitButton;

    private int getCountNumberOfAutomationTools() {
        return automationToolsList.size();
    }

    private String getAutomationToolsLongestName() {
        return automationToolsList.stream()
                .map(WebElement::getText)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    @Step("UI: Получение наименования страницы Form Fields")
    public String getNameFieldFillingPage() {
        return nameFieldFillingPage.getText();
    }

    @Step("UI: Заполнение поля Name, имя: {inputName}")
    public FormFieldsPage fillNameInput(String inputName) {
        nameInputField.sendKeys(inputName);

        return this;
    }

    @Step("UI: Заполнение поля Password, пароль: {password}")
    public FormFieldsPage fillPasswordInput(String password) {
        passwordInputField.sendKeys(password);

        return this;
    }

    @Step("UI: Выбор чекбокса Milk в списке What is your favorite drink?")
    public FormFieldsPage chooseDrinkMilkCheckbox() {
        drinkMilkCheckbox.click();

        return this;
    }

    @Step("UI: Выбор чекбокса Coffee в списке What is your favorite drink?")
    public FormFieldsPage chooseDrinkCoffeeCheckbox() {
        drinkCoffeeCheckbox.click();

        return this;
    }

    @Step("UI: Выбор радиокнопки Yellow в списке What is your favorite color?")
    public FormFieldsPage chooseColorYellowCheckbox() {
        colorYellowRadioButton.click();

        return this;
    }

    @Step("UI: Выбор рандомного селекта в Do you like automation?")
    public FormFieldsPage selectRandomAutomationOption() {
        final Select select = new Select(automationDropList);
        final List<WebElement> options = select.getOptions();

        select.selectByIndex(new Random().nextInt(options.size()));

        return this;
    }

    @Step("UI: Заполнение поля Email, email: {userEmail}")
    public FormFieldsPage fillEmailInput(String userEmail) {
        emailInputField.sendKeys(userEmail);

        return this;
    }

    @Step("UI: Заполнение поля Message кол-вом инструментов и инструментом с наибольшим кол-вом символов")
    public FormFieldsPage fillMassageInputWithTools() {
        final String text = getCountNumberOfAutomationTools() + "\n" + getAutomationToolsLongestName();

        messageInputField.sendKeys(text);

        return this;
    }

    @Step("UI: Нажатие кнопки Submit")
    public FormFieldsPage pressSubmitButton() {
        new Actions(getDriver()).moveToElement(submitButton).perform();
        submitButton.click();

        return this;
    }

    @Step("UI: Получение текста с алерта")
    public String getAlertText() {
        final WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TEN_SECONDS.getSeconds()));
        final Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        final String alertText = alert.getText();
        alert.accept();

        return alertText;
    }
}
