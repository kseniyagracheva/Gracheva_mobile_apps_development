**Отчет по практике №2**
------

В самом начале работы был создан новый проект Lesson2, а в нем новый модуль AсtivityLifeCycle.

![image](https://github.com/user-attachments/assets/2b8274a4-2718-4258-98ed-2023680fdc6c)

![image](https://github.com/user-attachments/assets/5fb4d0c9-693e-43f2-974c-43c7e8d2186a)

**Задание №1**

В первом задании нужно было познакомиться с жизненным циклом Activity. В модуле ActivityLifeCycle, в файле MainActivity.java были
переопределены основные методы жизненного цикла родительского класса. 
Были рассмотрены следующие методы:
1. onCreate() - был переопределен зарание;
2. onStart();
3. onRestoreInstanceState(Bundle savedInstanceState);
4. onRestart();
5. onResume();
6. onPause();
7. onSaveInstanceState(Bundle savedInstanceState);
8. onStop();
9. onDestroy().

В каждом методе был добавлен вывод сообщения о выполнении данного метода. 
Затем в разметке activity_main.xml, было добавлено поле для ввода текста.

![image](https://github.com/user-attachments/assets/8099d6a4-bb14-4bb5-ad8a-c8412c5d08e4)

Для вызова методов жизненного цикла было произведено взаимодействие с полем ввода текста, был произведен выход на "Главный экран" и перезапуск приложения.
В результате сообщения, которые были выведены с помощью инструмента LogCat можно посмотреть в соответствующей вкладке.

![image](https://github.com/user-attachments/assets/ce421e27-b0e3-459b-8b23-2a2478a90c2c)

*Ответы на вопросы:*
1. Будет ли вызван метод «onCreate» после нажатия на кнопку «Home» и возврата
в приложение?
Ответ: Нет. Данный метод предназначен для связывания кодовой части и графического интерфейса и не вызывается при выходе на "Главный экран". 
Он вызывается, когда возникает новая задача.
2. Изменится ли значение поля «EditText» после нажатия на кнопку «Home» и
возврата в приложение?
Ответ: Нет. Вызывается метод onPause() и состояния UI-элементов сохраняются.
3. Изменится ли значение поля «EditText» после нажатия на кнопку «Back» и
возврата в приложение?
Ответ: Да. Вызывается метод onDestroy() и вместе с activity удаляются все состояния UI-элементов.

**Задание 2**
-----
В ходе выполнения данного задания был создан новые модули MultiActivity и IntentFilter, в котором была проведена работа с явными и невяными намерениями.

**1. Явные намерения**

Для работы с явным намерением в модуле MultiActivity была создана новая activity - SecondActivity - которая создавалась при нажатии на кнопку в главной activity.
В activity_main.xml было добавлено поле ввода текста, а также кнопка для отправки этого текста и в activity_second и загрузки соответствующей activity.

![image](https://github.com/user-attachments/assets/7549cce8-8335-4f5b-97f9-438de954c575)

В файле MainActivity.java был добавлен обработчик события нажатия на кнопку, а также намерение, которое получает текст из поля ввода.
В activity_second был добавлен элемент TextView для отображения введенного текста. 

![image](https://github.com/user-attachments/assets/1b65ef58-9e77-41c5-bfb5-ed9c610f08ff)

В файле SecondActivity.java была реализована логика получения намерения и изменения текста. 

Также для отображения методов жизненного цикла, которые вызывались в данном контексте в фалах MinActivity.java и SecondActivity.java были переопределены основные методы жизненного цикла с выводом сообщений в LogCat.

![image](https://github.com/user-attachments/assets/9742f957-8ebb-4edd-9ab5-eb4f38a54cfd)
![image](https://github.com/user-attachments/assets/58b4d8ef-6255-40b5-ab8e-d34fa538e658)

**2. Неявные намерения**

В модуле IntentFilter была произведена работы с неявными намерениями. В файле activity_main.xml были добавлены 2 кнопки: "Открыть браузер" и "Передать ФИО".

![image](https://github.com/user-attachments/assets/da17619e-3e6c-45df-aa5f-f8c8f28dddd6)

После в файле MainActivity.java были добавлены обработчики событий нажатия на данные кнопки. 

При нажатии на кнопку "Открыть браузер", открывался сайт МИРЭА благодаря Intent.ACTION_VIEW.

![image](https://github.com/user-attachments/assets/f2f7eb53-1bf3-45e3-9e17-3243842ca180)

При нажатии на кнопку "Передать ФИО" показывался текст, которы должен быть передан благодаря Intent.ACTION_SEND

![image](https://github.com/user-attachments/assets/ea25d889-0d86-481a-bde1-f59e346a8fe2)

**Задание №3**
----

**Всплывающие уведомления**

Для выполнения данного задания был создан модуль ToastApp. В activity_main.xml была добавлена строка ввода текста и кнопка. 
При нажатии на кнопку нужно было посчитать количество символов в введенной строке и вывести это количество и еще текст во всплывающем уведомлении. 
Обработчик события нажатия на кнопку с реализация показа высплывающего уведомления был добавлен в файл MainActivity.java

![image](https://github.com/user-attachments/assets/0fdbb752-4412-4e34-a683-34e1319404ab)

**Уведомления**

Для данного задания был создан модуль NotificationApp. 
Была добавлена кнопка для отправки уведомления.

![image](https://github.com/user-attachments/assets/07b0f942-0d2b-4c8a-ac91-be324706a2e1)

В манифест файле было добавлено разрешение на показ уведомления, а также в файле MainActivity.java был добавлен обработчик события нажатия на кнопку для отправки уведомления.

![image](https://github.com/user-attachments/assets/2a6714f4-bb59-45c7-899e-7df6ba478b24)

![image](https://github.com/user-attachments/assets/56381ad2-b39f-4d22-863b-0b974c34be69)

**Диалоговые окна**

Для работы с диалоговыми окнами был создан новый модуль Dialog. А также новые Java Class, которые наследуются от класс DialogFragment. 
Всего было добавлено 3 класса:
1.  MyTimeDialogFragment;
2.  MyDateDialogFragment;
3.  MyProgressDialogFragment.

В файле activity_main.xml были добавлены 3 кнопки.

![image](https://github.com/user-attachments/assets/23449589-8167-41b6-bee0-ee9a045e62b7)

В файле MainActivity.java были добавлены обработчики событий нажатия на данные кнопки.

При нажатии на кнопку  "Show TimwDialog" всплывает диалоговое окно TimePickerDialog.

![image](https://github.com/user-attachments/assets/5d3d1114-d525-4dac-9a25-c1b5d06fc2f2)

При нажатии на кнопку "Show ProgressDialog" всплывает диалоговое окно ProgressDialog.

![image](https://github.com/user-attachments/assets/adf7b0a0-8af6-47ef-9f29-eaa817d1e6e1)

При нажатии на кнопку "Show DateDialog" всплывает диалоговое окно  DatePickerDialog.

![image](https://github.com/user-attachments/assets/694cdc4a-f064-4b7f-aaa5-bcd4ec89675a)


