# wsdl_csv_retriever

Парсит необходимые csv файлы, находит в них заданное в запросе число, возвращает результат

каталог csv файлов с целыми числами задается в application.properties ключом app.csvFilesPath

После запуска приложения веб-сервис находится по адресу http://localhost:8080/ws/findByNumber.wsdl