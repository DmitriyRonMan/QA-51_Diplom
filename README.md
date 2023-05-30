# Дипломный проект по профессии «Тестировщик ПО»

## Документация

- [Тест план: автоматизированное тестирование приложения "Путешествие дня"](https://github.com/DmitriyRonMan/QA-51_Diplom/blob/main/docs/Plan.md)
- [Отчёт о проведённом тестировании](https://github.com/DmitriyRonMan/QA-51_Diplom/blob/main/docs/Report.md)
- [Отчёт о проведённой автоматизации](https://github.com/DmitriyRonMan/QA-51_Diplom/blob/main/docs/Summary.md)
- [Задание по дипломному проекту](https://github.com/DmitriyRonMan/QA-51_Diplom/blob/main/docs/Task.md)

## Описание приложения
Приложение — это веб-сервис "Путешествие дня", который предлагает купить тур по определённой цене двумя способами:

1. Обычная оплата по дебетовой карте.
2. Уникальная технология: выдача кредита по данным банковской карты.

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

- сервису платежей, далее - Payment Gate;
- кредитному сервису, далее - Credit Gate.

Приложение в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом. Данные карт при этом сохранять не допускается.

Более подробно ознакомится с заданием и описанием дипломного проекта можно в документации.

## Начало работы

### ПО для работы с проектом

Перед началом работы убедитесь, что на вашем устройстве установлены следующие ПО:
1. IntelliJ IDEA
2. GIT
3. Docker Desktop
4. Браузер - Google Chrome

### Установка и запуск

Склонировать репозиторий на свой рабочий стол
```
git clone git@github.com:DmitriyRonMan/QA-51_Diplom.git
```

Открыть проект в IntelliJ IDEA

Для запуска контейнеров MySql, PostgreSQL и Node.js использовать команду
```
docker-compose up
```

Для запуска приложения (jar-файл) под MySql использовать команду
```  
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
```

Для запуска приложения (jar-файл) под PostgreSQL использовать команду
```  
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
```

Для запуска тестов под MySql использовать команду
```
./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
```

Для запуска тестов под PostgreSQL использовать команду
```
./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
```

Для получения отчета Allure использовать команду 
```
./gradlew allureReport
```




