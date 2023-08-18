# Дипломный проект по автоматизации тестирования (API)

## 	Содержание

> ➠ [API сервис Apichallenges Herokuapp](#api-сервис-apichallenges-herokuapp)
>
> ➠ [Технологический стек API](#технологический-стек-api)
> 
> ➠ [Удаленный запуск тестов в Jenkins](#-запуск)
>
> ➠ [Отчет о результатах тестирования в Allure Report](#-главная-страница-allure-отчета)
>
> ➠ [Tests as documentation в Allure Test Ops](#-тест-кейсы-в-allure-test-ops)
>
> ➠ [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)

## API сервис Apichallenges Herokuapp
###  Покрытый функционал api сервиса Apichallenges Herokuapp

> Разработаны автотесты на <code>Api сервис</code>.
### Api

- [x] Создание Todo
- [x] Изменение Todo
- [x] Создание Secret Note
- [x] Изменение Secret Note
- [x] Удаление Todo

### Технологический стек API

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="readme_interactive_elements/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="readme_interactive_elements/logo/Java.svg">
<img width="6%" title="Rest Assured" src="readme_interactive_elements/logo/Rest_Assured.png">
<img width="6%" title="Allure Report" src="readme_interactive_elements/logo/Allure_Report.svg">
<img width="6%" title="Allure Test Ops" src="readme_interactive_elements/logo/Allure_Test_Ops.svg">
<img width="6%" title="Gradle" src="readme_interactive_elements/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="readme_interactive_elements/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="readme_interactive_elements/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="readme_interactive_elements/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="readme_interactive_elements/logo/Telegram.svg">
</p>



### В данном подпроекте автотесты написаны на <code>Java</code> с использованием <code>Rest Assured</code> для Api-тестов.
>
> <code>Rest Assured</code> выполняет роль обёртки над http клиентом.
>
> <code>Allure Report</code> формирует отчет о запуске тестов.
>
> Для автоматизированной сборки проекта используется <code>Gradle</code>.
>
> В качестве библиотеки для модульного тестирования используется <code>JUnit 5</code>.
>
> <code>Jenkins</code> выполняет запуск тестов.
>
> После завершения прогона отправляются уведомления с помощью бота в <code>Telegram</code>.


## <img width="4%" title="Jenkins" src="readme_interactive_elements/logo/Jenkins.svg"> Запуск

Ссылка на Jenkins Job: <code>https://jenkins.autotests.cloud/job/19-marg0shek-final_project_api/</code>

## <img width="4%" title="Allure_Report" src="readme_interactive_elements/logo/Allure_Report.svg"> Главная страница allure отчета

<p align="center">
<img title="Allure_main" src="readme_interactive_elements/screens/AllureReportMainPage.png">
</p>

### <img width="4%" title="Allure_Report" src="readme_interactive_elements/logo/Allure_Report.svg"> Группировка тестов по проверяемому функционалу

<p align="center">
<img title="Allure_suits" src="readme_interactive_elements/screens/AllureReportSuites.png">
</p>

### <img width="4%" title="Allure_testops" src="readme_interactive_elements/logo/Allure_Test_Ops.svg"> Тест кейсы в Allure Test Ops

<p align="center">
<img title="Allure_testops" src="readme_interactive_elements/screens/TestOps.png">
</p>

## <img width="4%" title="Telegram" src="readme_interactive_elements/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

> После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне.
>
> Информация по настройке и использованию бота <code>https://github.com/qa-guru/allure-notifications</code>

<p align="center">
<img title="Telegram_notifications" src="readme_interactive_elements/screens/Telegram.png">
</p>
