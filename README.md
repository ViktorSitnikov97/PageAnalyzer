# Проект  "Анализатор страниц"

### Hexlet tests and linter status:
[![Actions Status](https://github.com/ViktorSitnikov97/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ViktorSitnikov97/java-project-72/actions)
[![example workflow](https://github.com/ViktorSitnikov97/java-project-72/actions/workflows/main.yml/badge.svg)](https://github.com/ViktorSitnikov97/java-project-72/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ViktorSitnikov97_PageAnalyzer&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ViktorSitnikov97_PageAnalyzer)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ViktorSitnikov97_PageAnalyzer&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=ViktorSitnikov97_PageAnalyzer)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ViktorSitnikov97_PageAnalyzer&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ViktorSitnikov97_PageAnalyzer)


## Описание 

Анализатор страниц - это веб-сайт, разработанный на фреймворке Javalin. Здесь отрабатываются основные принципы построения современных сайтов на архитектуре MVC: работа с маршрутизацией, обработчиками запросов и шаблонизатором, взаимодействие с базой данных через ORM. Оcновная задача - делать анализ других сайтов на SEO-пригодность.

## Реализация

На главной странице осуществляется подготовка выбранного адреса сайта к проверке: проводится валидность адреса (проверка протокола и домена- является ли введенная ссылка адресом сайта), впервые ли он добавляется пользователем.
Затем выбранный пользователем адрес добавляется в список сайтов, которые подлежат проверке. Заводится отдельная страница для каждого адреса сайта, на которой пользователю доступна информаия о дате проведения последней проверки и о ее результате, а также функция самой проверки, посредством выполнения которой на текущей странице заполняется информация о результатах ее проведения.  

## Требования для локального запуска
Иметь, либо установить:

> [Git installed](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
> 
> [Java](https://www.oracle.com/java/technologies/downloads/)
> 
> [Gradle](https://gradle.org/install/)

## Установка и запуск
```
git clone git@github.com:ViktorSitnikov97/PageAnalyzer.git
make run
```
## Технологический стек

> * Фреймворк: Javalin
> * Веб: Bootstrap
> * Парсер: Jsoup
> * Тесты: JUnit 5, Unirest, Mockwebserver
> * Отчет об анализе кода: SonarQube
> * Отчет о тестах: Jacoco
> * Линтер: Checkstyle
> * Базы данных: H2 (внутренняя), PostgreSQL (в продакшн)
> * [Задеплоено](https://java-project-72-2xyh.onrender.com/) на бесплатный сервер от Render.


## Демонстрация
![image](https://github.com/user-attachments/assets/7db78403-5e61-4260-a2f7-ad14a2925c87) ![image](https://github.com/user-attachments/assets/32df3809-d45e-4fa5-bd25-0ba13bb5ade6) ![image](https://github.com/user-attachments/assets/8dc84c48-87eb-40c5-afa7-82506e6fe418) ![image](https://github.com/user-attachments/assets/bc001876-64fe-4c95-9e5a-0885be1cd77a)










