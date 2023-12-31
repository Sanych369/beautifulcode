## Название микросервиса

Beautifulcode

## Назначение

API для Сбера, проверяющая синтаксические ошибки с участием круглых скобок в полученном тексте

# Содержание

1. [Начало работы](#НачалоРаботы)
2. [Трассировка](#Трассировка)
3. [Логирование](#Логирование)
4. [OpenAPI](#OpenAPI)
6. [Метрики](#Метрики)

## Начало работы

Для начала работы на устройстве необходима JRE.
Java Runtime Environment — минимальная (без компилятора и других средств разработки) реализация виртуальной машины,
необходимая для исполнения Java-приложений.

### Как запустить проект

С использованием командной строки:

- находясь в директории с программой выполнить следующую команду:

`java -jar beautifulcode-1.0-SNAPSHOT.jar`

## Трассировка

В проект добавлена трассировка запроса.
При передаче в запросах header: requestId = anyString можно будет отследить всю цепочку запросов.
Если requestId не передаётся в заголовках запроса - он автоматически генерируется в формате UUID.

## Логирование

В текущей версии настроено 2 уровня логирования INFO, ERROR.
Логи пишутся в файл `${project.basedir}/logs/log`
Также выводятся в консоль.
При достижении размера файла лога в 10Mb - он перемещается в архив `${project.basedir}/logs/archived/` с наименованием в
формате

beautiful-code-yyyy-MM-dd HH:mm:ss, где:

- yyyy - год
- MM - месяц
- dd - день
- HH - час
- mm - минута
- ss - секунда

## OpenAPI

Реализовано описание доступных методов API.
Для просмотра UI в дефолт реализации при запущенном приложении можно перейти в браузере на URL:
`localhost:8080/swagger-ui/index.html`

## Метрики

Также есть возможность получения метрик актуатора:
`curl --location --request GET 'localhost:8080/actuator/prometheus'`
И проверки работоспособности приложения в текущий момент:
`curl --location --request GET 'localhost:8080/actuator/health'`