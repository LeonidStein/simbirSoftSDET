# Проект по автоматизированному тестированию

Этот проект создан в рамках тестового задания от IT-компании SimbirSoft для SDET-практикума
и демонстрирует автоматизацию веб-тестирования.

## Технический стек

- **Java 17** — основной язык разработки тестов.
- **Gradle 8.13** — система сборки и управления зависимостями.
- **JUnit 5** — фреймворк для написания и запуска тестов.
- **Selenium** — для взаимодействия с веб-приложением и автоматизации действий пользователя.
- **WebDriverManager** — упрощает управление драйверами браузеров.
- **Allure** — генерация наглядных отчетов о выполнении тестов.
- **AspectJ Weaver** — инструмент для внедрения аспектов.
- **JavaFaker** — генерация тестовых данных (имена, email и др.).
- **SLF4J Simple** — простой логгер для вывода информации о тестах.
- **Owner** — конфигурация для properties-файлов.
- **Test IT** — хранение и управление тестовыми сценариями.

## Структура проекта
````
test_task_sdet/
├─ .gradle/
├─ build/
├─ gradle/
├─ png/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ com/simbirsoft/
│  │  │     ├─ constant/
│  │  │     ├─ data/
│  │  │     ├─ framework/
│  │  │     │  ├─ config/
│  │  │     │  ├─ driver/
│  │  │     │  └─ util/
│  │  │     └─ page/
│  │  └─ resources/
│  │     └─ config.properties
│  └─ test/
│     ├─ java/
│     │  └─ com/simbirsoft/
│     └─ resources/
│        ├─ allure.properties
│        └─ junit-platform.properties
├─ .gitignore
├─ build.gradle.kts
├─ gradlew
├─ gradlew.bat
├─ README.MD
└─ settings.gradle.kts
````

# Руководство по началу работы с проектом

### I. Создай директорию для проекта и перейди в неё
### II. Склонируй проект в созданную директорию с GitHub:
````
git clone git@github.com:LeonidStein/simbirSoftSDET.git
````
### III. Собери проект и запусти тесты (Тесты запускаются на Google Chrome):
````
./gradlew build
````
*В случае возникновения ошибки Permission denied, выполни команду:*
````
chmod +x gradlew
````
### IV. Просмотр Allure отчёта:
````
./gradlew allureServe
````

# Тестовое задание
### Часть 1
- **Наименование тестового метода** — testFormAndFieldsFirstPart, отчёт Allure и тест кейс в Test It:
![img.png](png/allure1.png)
![img.png](png/testit1.png)

### Часть 2
- **Наименование позитивного тестового метода** — testPositiveSecondPart, отчёт Allure и тест кейс в Test It:
![img.png](png/allure2.png)
![img.png](png/testit2.png)

- **Наименование негативного тестового метода** — testNegativeSecondPart, отчёт Allure и тест кейс в Test It:
- **Примечание:** На основании технического задания был разработан тестовый кейс с негативным сценарием.
    При заполнении обязательного поля Name пробелом (или последовательностью пробелов) и последующем нажатии кнопки Submit должен отображаться алерт
    с сообщением «Message not received!». **Фактически сообщение не соответствует ожидаемому, вследствие чего тест считается не пройденным.**
![img.png](png/allure3.png)
![img.png](png/testit3.png)