# Проект  "Анализатор страниц"

### Hexlet tests and linter status:
[![Actions Status](https://github.com/ViktorSitnikov97/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ViktorSitnikov97/java-project-72/actions)[![example workflow](https://github.com/ViktorSitnikov97/java-project-72/actions/workflows/main.yml/badge.svg)](https://github.com/ViktorSitnikov97/java-project-72/actions)[![Maintainability](https://api.codeclimate.com/v1/badges/3b58008aec971875ce24/maintainability)](https://codeclimate.com/github/ViktorSitnikov97/java-project-72/maintainability)[![Test Coverage](https://api.codeclimate.com/v1/badges/3b58008aec971875ce24/test_coverage)](https://codeclimate.com/github/ViktorSitnikov97/java-project-72/test_coverage)


## Описание 

Анализатор страниц - это веб-сайт, разработанный на фреймворке Javalin. Здесь отрабатываются основные принципы построения современных сайтов на архитектуре MVC: работа с маршрутизацией, обработчиками запросов и шаблонизатором, взаимодействие с базой данных через ORM. ОСновная задача - делать анализ других сайтов на SEO-пригодность.

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
git clone git@github.com:ViktorSitnikov97/java-project-72.git
make run
```
## Технологический стек

> * Фреймворк: Javalin
> * Веб: Bootstrap
> * Парсер: Jsoup
> * Тесты: JUnit 5, Unirest, Mockwebserver
> * Отчет о тестах: Jacoco
> * Линтер: Checkstyle
> * Базы данных: H2 (внутренняя), PostgreSQL (в продакшн)
> * [Задеплоено](https://java-project-72-2xyh.onrender.com/) на бесплатный сервер от Render.


## Демонстрация







