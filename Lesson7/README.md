**Отчет по практике №7**
----

**Задание 1**

В данном задании необходимо было получить время с сервера, разобрать полученную строку и сделать экран отображения времени и даты.

Сначала был создан отдельный класс SocketUtils, который содержит методы для работы с сокетами. 

Затем была дополнена логика основного класса GetTimeTask в MainActivity.java.

Также в манифест файл было добавлено разрешение для выхода в интернет.

![image](https://github.com/user-attachments/assets/d77abc51-45bb-4e7f-be99-3e7373375205)

![image](https://github.com/user-attachments/assets/993f10c8-d719-4cb7-b9ee-bf31856dafcd)

**Задание 2**

В данном задании требовалось получить внешний ip-адрес устройства и далее передать его в сервис погоды для получения данных о погоде в данном месте. Для этого необходимо было подключить разрешения в манифест файле, которые позволяют выходить в интернет и работать с сетью. 

Сначала была создана xml разметка активности, где были добавлены текстовые поля для вывода ip адреса, города, региона, страны и погоды в данном регионе. 
Затем был изменен класс MAinActivity.java, куда была добавлена логика работы с Json-объектами и отправки и получения данных со стороннего сервиса. 

![image](https://github.com/user-attachments/assets/5cc37f96-4819-4b0d-8533-a82c5b76a87c)

![image](https://github.com/user-attachments/assets/81d0f82a-46e7-4652-a063-ee9a470e3f4d)

**Задание 3**

Для выполнения данного задания сначала было необхолимо подключиться к обачной базе данных Firebase.
Для этого нужно было выбрать необходимый модуль:

![image](https://github.com/user-attachments/assets/c539395d-833c-440c-b563-5edcfa99c61a)
![image](https://github.com/user-attachments/assets/37bdee1a-21da-4a92-b0e4-044a8b6c7258)

Затем был создан новый проект на платформе Firebase

![image](https://github.com/user-attachments/assets/093d37b7-535a-4947-b7c7-534a77b6a6ca)
![image](https://github.com/user-attachments/assets/d1251c4f-b0c6-4f0c-85f7-87f3a486c516)

После этого было установлено соединение

![image](https://github.com/user-attachments/assets/d8b11272-8dad-47a7-ad43-ff64981646df)

После были добавлены необходимые зависимости в проект

![image](https://github.com/user-attachments/assets/9787fa99-bc14-4899-b092-a9e672661b71)

После была активирована авторизация по почте и паролю в консоли разработчика в настройках проекта.

![image](https://github.com/user-attachments/assets/f4b11b1e-8796-411f-8f3d-0f3a890c9b93)

Далее необходимо было создать приложение в котором можно было бы создавать аккаунт и проходить аутентификацию.

Сначала был создан макет приложения.
Затем были добавлены необходимые строковые константы в файл strings.xml.
После был изменен класс MainActivity.java, куда были добавлены методы регистрации, аутентификации, верификации почтового ящика и выхода из аккаунта, а также изменение экрана.

![image](https://github.com/user-attachments/assets/d084a44f-089b-426b-9949-8397e533bf8f)

![image](https://github.com/user-attachments/assets/3170e3b6-7d10-4097-9e7f-7f1f6cc7f03d)

После чего было отправлено письмо для верификации

![image](https://github.com/user-attachments/assets/64100295-d735-42ca-b13d-7f52c02f044e)

![image](https://github.com/user-attachments/assets/48c45ec4-09cc-4bcf-8929-77a9f7333fd3)

После чего аккаунт стал верифицированным

![image](https://github.com/user-attachments/assets/5fe00bb1-502e-4f0f-9aa6-d4bbd8f158c1)

Пользователи системы:

![image](https://github.com/user-attachments/assets/000e7d74-408c-4a58-9e10-e34691c0d3ae)

**Контрользое задание**

Для выполнения данного задания проект MireaProject был подключен к Firebase, где был создан новый одноименный проект.

Затем была создана новая активность LoginActivity и в ней был создана xml разметка.
А также в основном файле была добавлена логикак входа и выхода в приложение, а также регистрации.

Затем в манифест файле были внесены изменения для запуска LoginActivity первой.

Далее в MainActivity была добавлена логикак отображения погоды, как во 2 задании.

Теперь при входе на главном экране сразу отображается информация из сетевого ресурса.

![image](https://github.com/user-attachments/assets/e1ce714b-ef34-48d1-9559-3740de13edb2)

![image](https://github.com/user-attachments/assets/3a4ab8da-b842-4fb8-8e07-33bf26ce96ad)

![image](https://github.com/user-attachments/assets/d384270a-3ba2-4343-8f1c-b382f01f5ddc)

![image](https://github.com/user-attachments/assets/328ea177-8195-4f69-ba0e-f6698955d6df)

