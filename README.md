# springclientservice

НАСТРОЙКА
 1. Порт сервиса по умолчанию = 8080 (настройка "service_port" файла "gradle.properties")
 2. Настрока proxy: 
  2.1. "gradle.properties" для gradle
  2.2. "src\main\resources\static\.npmrc" и "src\main\resources\static\.typingsrc" для angular 2
 
СБОРКА/ЗАПУСК 
 1. В корневой директории выполнить командный файл "start" или из командной строки/терминала: "gradlew start"
 2. http://localhost:{service_port}, где {service_port} - номер порта из настройки (см. п.1. НАСТРОЙКА)