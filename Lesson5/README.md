**Отчет по практике №5**
----

Для выполнения данной практики был создан новый проект Lesson5

**Задание 1**

Для выполнения данного задания был создан модуль app и изменен файл activity_main.xml, куда был добавлен ListView c id list_view, 
который связывается с элементом binding.listView в коде.
В файле MainActivity.java был добавлен код, который получает список всех датчиков с помощью SensorManager.getSensorList(Sensor.TYPE_ALL).
Затем создается массив ArrayList<HashMap<String, Object>>, где для каждого датчика создается пара "Название" и "Значение".
В результате работы данного модуля получился следующий результат.

![image](https://github.com/user-attachments/assets/801c4ef7-fc97-4bd0-b79c-3958baf46e7e)

