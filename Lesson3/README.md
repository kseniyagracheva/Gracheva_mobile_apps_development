
**Отчет по практике №3**
----

В самом начале был создан новый проект Lesson 3

![image](https://github.com/user-attachments/assets/25e55f54-cc0d-4b22-b874-641ed0b3f0af)

**Задание 1**

Для выполнения первого задания необходимо было создать новый модуль IntentApp, в котором нужно было создать 2 активности.
В первой активности содержалась кнопка "Отправить время", а во второй активности выводился текст, полученный от намерения, которое было отправлено
при нажатии на кнопку в первой активности.
В файле MainActivity.java был добавлен обработчик события нажатия на кнопку, который создавал намерение для открытия второй активности и 
передавал туда полученное время.

![image](https://github.com/user-attachments/assets/37d1c100-311f-47dd-8fbb-612f3a56edd7)

В файле SecondActivity.java было реализовано получение нарения и запись полученных даннных в отображаемый текст.

![image](https://github.com/user-attachments/assets/c8fb8e6e-665f-4cd1-8361-8e1eabfdca19)

**Задание 2**

Для выполнения данного задания был создан модуль FavoriteBook, в котором было реализовано 2 активности. Первая активность создержит текстовое поле и кнопку для отправки данных во вторую активность. Вторая активность содержит два текстовых поля для отображения отправленных данны, два поля ввода текста и кнопку для отправки введенных данных.

В данном задании был написан класс ShareActivity.java, где было реализовано получение данных из намерения, а также реализован обработчик нажатия на кнопку для отправки введенных данных.

Резульат работы представлен на скриншотах ниже.

![image](https://github.com/user-attachments/assets/473cf6e6-9fdd-4cb9-ba37-497f44e3c166)

![image](https://github.com/user-attachments/assets/7a7e7a78-c0d8-4e73-aad2-5f1997dc69a2)

![image](https://github.com/user-attachments/assets/f5828133-c565-49dc-bdb1-8fc5f9f4a157)

![image](https://github.com/user-attachments/assets/e32113f0-7175-4d0b-948d-bbb32e819dfc)

**Задание 3**

Для выполнения данного задания был создан новый эмулятор с OC "Google APIs" и новый модуль SystemIntentsApp. В нем была создана активность, в который располагаются 3 кнопки.

![image](https://github.com/user-attachments/assets/d082e31e-2310-415a-bcc0-0c407b05a6d2)

При нажатии на кнопку "Позвонить", открывался набор указанного номера.

![image](https://github.com/user-attachments/assets/366d2187-50a1-4826-8f78-44423917fc6b)

При нажатии кнопки "Открыть браузер", открывается страница браузера.

![image](https://github.com/user-attachments/assets/35a0bc2a-ad7e-4930-b0fa-09a699b3139d)

При нажатии кнопки "Открыть карту", открывалась страница с Google картой.

![image](https://github.com/user-attachments/assets/5fea1f27-3343-4bfd-81e6-7dcf5d267fa2)

**Задание 4**

Для выполнения данного задания был создан новый модуль SimpleFRagmentApp. Также было создано 2 фрагмента First Fragment и Sedcond Fragment. 
Также были исправлены файла FirstFragmet.java и activity_main.xml, чтобы добавить туда дву кнопки для обработки вызова фрагментов. 
Затем был добавлен новый Resource File, для смены ориентации. 
Далее были внесены изменения в файл MAinActivity.java, для осущесвления грамотного поворота экрана.

![image](https://github.com/user-attachments/assets/a7699837-f002-455d-9e54-c063fd9ab020)

![image](https://github.com/user-attachments/assets/706e2753-c052-4600-a22d-890af784ded2)

![image](https://github.com/user-attachments/assets/9f943172-baf3-4ce6-8c5c-536f2e4a9ae2)

**Контрольное задание**

В процессе выполнения данного задания было создано 2 новых фрагмента.

Один из них - DataFragment, фрагмент, содержащий информацию об отрасли. В данном случае в файл xml-разметки были внесены изменения (отступы, цвета и стили), которые соответствуют требованиям "Material you". 
Также была внесена информация про пирог. 

![image](https://github.com/user-attachments/assets/ac078812-08c6-4fd7-8c0b-3e0d31b6bc69)

Затем был создан фрагмент WebViewFragment. Файл разметки был исправлен, а также был исправлен файл java.

![image](https://github.com/user-attachments/assets/c26db240-5ab9-4377-8e84-373fd363cf7a)

После, были внесены изменения в файл activity_main_drawer.xml, и mobile_navigation.xml чтобы добавить новые пункты в меню.

![image](https://github.com/user-attachments/assets/09ab7729-b4f2-4232-bf05-7d897d3ce2a5)

В процессе выполнения возникла сложность с выходом в интернет, поэтому в манифест файле пришлось прописать строчку

    <uses-permission android:name="android.permission.INTERNET"
