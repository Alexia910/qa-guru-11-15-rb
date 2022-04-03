# Проект по автоматизации тестирования сайта РОСБАНК Интернет-Банк
Тестируемый сайт: <a target="_blank" href="https://online.rosbank.ru/ibank/">РОСБАНК Интернет-Банк</a>

## Содержание:
- [Технологии и инструменты](#watermelon-технологии-и-инструменты)
- [Реализованные проверки](#watermelon-Реализованные-проверки)
- [Запуск из терминала](#watermelon-Запуск-тестов-из-терминала)
- [Запуск тестов в Jenkins](#watermelon-Запуск-тестов-в-Jenkins)
- [Allure отчет](#bellhop_bell-Allure-отчет)
- [Видео прохождения тестов](#watermelon-Видео-прохождения-тестов)
- [Отчет в Telegram](#bellhop_bell-Отчет-в-Telegram)

## :watermelon: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://web.telegram.org/"><img width="50" height="50"  alt="Telegram" src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\logo\Telegram.svg"></a>
</p>

Перечень технологий и инструментом, использованных при реализации этого проекта:
- автотесты написаны на языке `Java`
- для UI-тестов используется тестовый фреймворк `Selenide`
- Для сборки проекта используется `Gradle`
- Библиотека для модульного тестирования: `JUnit 5`
- `Jenkins` выполняет удаленный запуск тестов в визуальном-онлайн интерфейсе. Установки дополнительных приложений на компьютер пользователя не требуется.
- `Selenoid` демонстрирует пример запуска браузеров в контейнерах Docker (и записывает видео).
- `Allure Report` формирует наглядный графический отчет о результатах  запуска тестов.
- После завершения прогона тестов, специальный `Telegram Bot` отправляются в `Telegram` краткий вариант Allure Report

## :watermelon: Реализованные проверки
Примеры UI тестов:
- [✓] Проверка отображения формы входа
- [✓] Проверка отображения информации о копирайте
- [✓] Проверка отображения номера телефона
- [✓] Переход к восстановлению пароля
- [✓] Проверка аутентификации

## :watermelon: Запуск тестов из терминала
###### Локальный запуск:
```
gradle clean test
```
###### Удаленный запуск:
```
clean
test
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DremoteDriverUrl=https://user1:1234@${REMOTE_BROWSER}/wd/hub/
-DvideoStorage=https://${REMOTE_BROWSER}/video/
-Dthreads=${THREADS}
```

## :watermelon: Запуск тестов в Jenkins
Шаги:
1. Зарегистрированным пользователем перейти на страницу сборки проекта по ссылке: <a target="_blank" href="https://jenkins.autotests.cloud/job/011-alexiaair-hw15-rb/">Jenkins Alexiaair</a>
2. Перечисленные ниже параметры можно менять в графическом интерфейсе.
3. Запустить выполнение тестов кнопкой "Собрать" (внизу страницы)

Основные параметры сборки:
- `BROWSER` – браузер, в котором будут выполняться тесты (по умолчанию - Chrome).
- `BROWSER_VERSION` версия браузера, в которой будут выполняться тесты (по умолчанию - 91.0).
- `BROWSER_SIZE` – размер окна браузера, в котором будут выполняться тесты (по умолчанию - 1920x1080).
- `REMOTE_BROWSER` - адрес удаленного сервера (Selenoid), на котором будут запускаться тесты.

Дополнительные параметры сборки, задействованные для выгрузки краткого отчета в Telegram:
- `PROJECT_NAME`  название проекта
- `ENVIRONMENT` - тестовый стенд (prod, demo, stage...), на котором запускались тесты. <i>Можно выставить значение из доступных в выпадающем списке</i>.
- `COMMENT` - ваш текстовой комментарий

## :bellhop_bell: Allure отчет
После того как тесты завершились, можно получить визуальный Allure отчет.
<br>Способ 1: Сформировать отчет средствами IJ IDEA (Allure Serve)
<br>Способ 2:
<br>1. Выполнить сборку в Jenkins
<br>2. Убедиться, что в блоке История сборок (напротив номера #) появился желтый значок Allure Report
<br>3. Кликнуть по значку Allure Report
<br>Ожидаемый результат: Откроется страница с готовым Allure Report

###### Главный экран отчета (Owerwiev)
<p align="center">
<img title="Allure Graphics" src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\allure_mian.png">
</p>

###### Страница с проведенными тестами (Suites)
<p align="center">
<img title="Allure Graphics" src="D:\MAIN_FOLDER\git_repositories\qa-guru-11-15-rb\src\test\resources\images\allure_tests.png">
</p>

## :watermelon: Видео прохождения тестов
К каждому тесту (в отчете) прилагается автоматически сгенерирвонное Selenoid видео. Пример:
<p align="center">
  <img title="Selenoid Video" src="src\test\resources\images\video_test.gif" alt="video">
</p>

## :bellhop_bell: Отчет в Telegram
После завершения сборки специальный Telegram-бот отправляет сообщение с отчетом о прогоне тестов.
Чтобы видеть сообщения от бота, вступите (временно) в телеграм-группу `TestNotification_alexiaair`

<p align="center">
<img title="Telegram Bot" src="src\test\resources\images\telegram.png">
</p>