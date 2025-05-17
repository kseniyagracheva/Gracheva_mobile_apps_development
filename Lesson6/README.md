**Отчет по практике №6**
----

В данной работе был создан новый проект Lesson 6.

**Задание 1**

Для выполнения данного задния в модуле app в разметке было добавлено 3 поля ввода и кнопка для отправки данных.
Затем был изменен файл MainActivity, куда был добавлен код, который получает доступ к специальному файлу настроек для записи и хранения 
введенных с экрана строк.
Затем были введены данные, которы сохранились в этот файл и при перезагрузке приложения они были выведены на экран.

![image](https://github.com/user-attachments/assets/c11a2e1a-89a2-4993-b1b4-7c977bd14a1e)

![image](https://github.com/user-attachments/assets/35ed2783-571f-466a-92e1-427eee4375fc)

Затем через View ->  Tool Windows -> Device Explorer был открыт Device Explorer, в котром по пути data -> data -> ru.mirea.grachevaks.lesson6 -> shared_prefs
был открыт файл user_settings.xml, скриншот с содержимим которого был добавлен в папку raw.

![image](https://github.com/user-attachments/assets/a7c2b16a-a444-46bc-b98d-ff13780c42ad)

**Задание 2**

Для выполнения данного задания был создан модуль SecureSharedPreferences. В данном модуле отображается имя поэта и его фотография.
Также создается безопасное хранилище с помощью EncryptedSharedPreferences и данные сохраняются туда.

![image](https://github.com/user-attachments/assets/4fcfead3-0811-465b-a0e3-64ac6671af70)

Зашифрованные данные были добавлены в файл настроек.

![image](https://github.com/user-attachments/assets/7fe12079-a7c4-4fa9-baa1-9cb728623466)

**Задание 3**

Для выполнения данного задания был создан новый модуль InternalFileStorage

В данном задании в приложении вводится информация в поле ввода, а затем она записывается в отдельный файл, который сохраняется в папку files в приложении.
Затем этот файл был добавлен в директорию raw.

![image](https://github.com/user-attachments/assets/ed6a5db1-b0b4-40e8-b790-50c3fb87b4ec)

![image](https://github.com/user-attachments/assets/805942c3-07f5-4f9a-b669-affc9f9501b5)

**Задание 4**

Для данного задания был создан новый модуль Notebook.
В нем были добавлены 2 поля ввода текста - одно для ввода имени файла, второе для ввода цитаты.

Затем при нажатии на кнопку "Сохранить данные в файл", создавался новый файл, если его не было, а если он был, то он перезаписывался.
При нажатии на кнопку "Загрузить данные из файла" данные из файла выводились в поле с цитатой.

![image](https://github.com/user-attachments/assets/ac480d36-abd8-403a-b387-5160a868bade)

![image](https://github.com/user-attachments/assets/7136a083-f251-4a7d-bbb1-bf12527947cc)

Было создано 2 файла с цитатами, которые доступны для чтения.

![image](https://github.com/user-attachments/assets/3f1c22f3-0021-456b-8b32-5a2cd3a59a98)

![image](https://github.com/user-attachments/assets/790ea95b-292c-45e5-9eae-9ffe19bea400)

![image](https://github.com/user-attachments/assets/c7760308-97b9-4439-ad9a-6f932e611717)

**Задание 5**

Для выполнения данного задания был создан модуль EmployeeDB, хотя в самом модуле булет создаваться база данных про супергерев.

В первую очередь был создан класс с аннотацией @Entity, в котором была создана табоица, в которой были следующие поля:
id супергероя, имя супергеря, стихия супергероя. Название класса Superhero совпадает с названием таблицы.

Затем был создан интерфейс для взаоимодействия с базой данных и манипулирования данными. Был создан класс SuperheroDAO, в котором используется интерфейс DAO.

Потом был создан абстрактный класс SuperheroDatabase для ведения базы данных и предоставления экземпляров Dao.

В конце создается класс, который наследуется от класса Application.

Затем были внесены изменения в файл MainActivity.java. Где были добавлены записи в базу данных и настрое вывод из нее в список.

![image](https://github.com/user-attachments/assets/ddc0734e-b3aa-4397-9cf2-ad58a787d71d)

**Контрольное задание**

В данном задании в проект MireaProject нужно было добавить два фрагмента. Один для работы с профилем и сохранением в
«SharedPreferences». Второй для работы с файлами

*SharedPreferences*

Был создан новый файл разметки fragment_profile.xml. В него были добавлены 3 поля ввода и кнопка для сохранения данных.

Затем был создан новый класс фрагмента, в котором использовался SharedPreferences для сохранения в файл настроек.

А затем был добавлен новый пункт в меню.

![image](https://github.com/user-attachments/assets/c5152f71-20d9-46cd-ab1a-bcfbdfce58a1)

![image](https://github.com/user-attachments/assets/f1a1539a-1533-4996-bc68-d33dd2bccea8)

![image](https://github.com/user-attachments/assets/b78178c2-c348-458b-9130-1eba98a461cc)

Файл с данными был перемещен в папку raw.

*Работа с файлами*

Здесь был создан новый файл разметки с текстовым полем и FloatingActionButton.

Затем был создан класс FilesFragment, который позволяет записывать новые данные в файл notes.txt через диалоговое окно.

Файл с заметками был добавлен в директорию raw.

![image](https://github.com/user-attachments/assets/8cc7eddb-4ca5-43fc-b5f2-1bf71a7e3ac6)

![image](https://github.com/user-attachments/assets/dca7ef6b-e582-41fc-ac4a-3fd83f483d5d)

![image](https://github.com/user-attachments/assets/c7c2d07c-f4cb-4741-b85d-cd15ace12e9b)

![image](https://github.com/user-attachments/assets/f4eb5a69-483f-4ad0-a92e-c092fefa03f3)

