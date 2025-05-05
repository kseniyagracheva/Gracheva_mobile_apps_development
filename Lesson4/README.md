**Отчет по практике №4**
----
**Задание 1**

В ходе выполнения данного задания необходимо было создать экран музыкального плеера с помощтю "binding" для вертикальной и горизонтальной 
ориентации. 

Для начала, чтобы включить ViewBinding, необходимо было внести изменения в блок android в файле build.gradle.kts (Module: app):

    buildFeatures{
        viewBinding = true
    }

Затем нужно было синхрнизировать проект с gradle файлами и затем пересобрать его.

![image](https://github.com/user-attachments/assets/ab54419c-bed7-44c5-accc-5524ba208df6)
![image](https://github.com/user-attachments/assets/c061c17e-b350-44c7-8f76-964c64325e73)

Далее необходимо созданть новый Android Resource File activity_main.xml (land) и внести в них измеения (были добавлены кнопки
для перемотки песен, для остановки\возобновления песни, а также был добавлен ползунок для промотки песни и, соответственно ее название).

Затем был сдлеан билд проекта, чтобы появился сгенерированный класс binding.

Потом в классе MainActivity.java была создана переменная binding, которая содержит все ссылки на объекты из activity_main.xml.
Благодаря этому объекту можно обращаться к элементам интерфейса без вызова findViewById.

Также была добавлена переменная MediaPlayer, которая управляет воспроизведением аудио.

Затем, в методе OnCreate было реализовано создание объекта ViewBinding. С помощью метода getLayoutInflater() он может создавать из xml элементов
элементы View.
Затем произошла установка корневого View, в данном случае LinearLayout.

Затем была реализована логика нажатия на кнопки, а также использования ползунка. Также был загружен аудио-файл.

![image](https://github.com/user-attachments/assets/6e1c0be1-2818-40bc-955d-2db986686d5c)

![image](https://github.com/user-attachments/assets/b9568584-e5b1-4273-a809-4931c8102741)

**Задание 2**

В данном задании необходимо было созданть новый модуль Thread для работы с потоками.
В данном случае задача потока заключалась в рассчете среднего кол-ва пар в месяц.

Для этого были добавлены два поля ввода текста, текстовое поле для вывода результата, кнопка, к которой привязано событие вызова
потока, а также текстовое поле, в котором выводятся данные о предыдущем и текущем имени потока.
Также был изменен файл MainActivity.java, все обращения к View элементам происходили через binding.
В методе OnCreate было изменено имя потока, а также в привязанном к нопку событии был вызван поток и осуществлены все вычисления.
Рассчеты в потоке производились некоторое количество секунд.
При каждом новом нажатии на кнопку создавался новый поток.

![image](https://github.com/user-attachments/assets/42402de0-094c-487d-81ca-c0c53fd1ab72)

![image](https://github.com/user-attachments/assets/405f4ae5-e65e-4d6f-a710-20deb08007c4)

![image](https://github.com/user-attachments/assets/9e967e76-c72c-4597-a51e-68519f1fffe8)

**Задание 3**

В данном задании необходимо было ознакомиться с функциями, которые отправляют задачи в UI-поток. Были рассмотрены функции runOnUiThread, post и postDelayed.

![image](https://github.com/user-attachments/assets/1bb0b07b-cae4-4ec2-8a0c-a8606329d289)

Файл MainActivity.java выглядит следующим образом:
    
    package ru.mirea.grachevaks.data_thread;
    
    import android.os.Bundle;
    import ru.mirea.grachevaks.data_thread.databinding.ActivityMainBinding;
    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import java.util.concurrent.TimeUnit;
    
    public class MainActivity extends AppCompatActivity {
    
        private ActivityMainBinding binding;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
    
            String report = "runn1 запускается первым,\n" +
                    "runn2 запускается вторым сразу после завершения runn1\n" +
                    "runn3 запускается последним с задержкой в 2 секунды\n" +
                    "runOnUiThread - сразу запускает задачи в UI-поток\n" +
                    "post - ставит задачи в очередь, ожидая завершения предыдущих\n" +
                    "postDelayed - ставит задачи в очередь, как post, но еще устанавлиает\n" +
                    "некотрую задержку.";
    
            final Runnable runn1 = new Runnable() {
                public void run() {
                    binding.tvInfo.setText("runn1");
                }
            };
            final Runnable runn2 = new Runnable() {
                public void run() {
                    binding.tvInfo.setText("runn2");
                }
            };
            final Runnable runn3 = new Runnable() {
                public void run() {
                    binding.tvInfo.setText("runn3");
                    binding.tvInfo.postDelayed(() -> binding.tvInfo.setText(report), 2000);
                }
            };
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        runOnUiThread(runn1);
                        TimeUnit.SECONDS.sleep(1);
                        binding.tvInfo.postDelayed(runn3, 2000);
                        binding.tvInfo.post(runn2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
    
        }
    }

**Задание 4**

В данном задании была проведена работа с Lopper и Hadler.
Был создан новый модуль Looper, в котором был создан новый класс MyLooper, где происходила обработка сообщения полученного из основного потока. Получение данных с основного потока и отправка их в looper происходит в классе MainActivity.
Были введены возраст и должность. Задержка составила число секунд равных возрасту.

![image](https://github.com/user-attachments/assets/8af73074-2382-41da-9ba4-53619c355932)

![image](https://github.com/user-attachments/assets/c67e67cf-d3df-44c0-83e5-52a2718ab7d7)

**Задание 5**

В данном задании была произведена работа с Loader. Был создан новый модуль CryptoLoader, а в нем новый класс MyLoader. Нужно было отправить фразу, которая шифровалась бы с помощью алгоритма AES и дешифрованная передавалась бы в toast.

![image](https://github.com/user-attachments/assets/00e3d93d-27be-4902-b981-31085aa5b186)

**Задание 6**

В данном задании необходимо было добавть воспроизведение аудиозаписи при помощи службы. 
Для этого был создан нвый модуль ServiceApp, а в нем был создан новый класс PlayerService.
В папке res была создана папка raw, куда была помещена аудиозапись.
Далее были добавлены 2 кнопки "Воспроизвести" и "Остановить".

![image](https://github.com/user-attachments/assets/e3c02834-7999-4931-8fe3-930b97416b71)

При нажатии на кнопку "Воспроизвести" запускается служба, включается аудиозапись и приходит увеомление с названием композиции.

![image](https://github.com/user-attachments/assets/81de8944-1ef3-41c0-b006-11c20261c9a8)

Для создания уведомлений фонового сервиса были добавлены некоторые разрешения в манифест файл.

    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK" />	/>
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS"	/>

А в блок service была добавлена следующая запись
    
    <service
            android:name=".PlayerService"
            android:enabled="true"
            android:exported="true"
            android:foregroundServiceType="mediaPlayback"/>

**Задание 7**

В данном заании был создано новый модуль WorkManager, где был реализован новый класс UploadWorker. В процессе выполнения данного задания были поставлены ограничения для запуска приложения.
А также были добавлены новые зависимости в gradle файл

**Задание 8  - Контрольное задание**

В данном задании в проект MireaProject был добавлен новый фрагмент для вызова фонового сервиса воспроизведения музыки в одном из вкладок меню.

Сначала была снова создана директория raw  в папке res для хранения аудиозаписи.

Затем был создан сервис для фонового воспроизведения музыки MusicPlayerService.java

Затем, были внесены изменения в манифест файл, где был добавлен созданный сервис.

    <service
        android:name=".ui.MusicPlayerService"
        android:enabled="true"
        android:exported="false" />
Потом, был создан фрагмент MusicServiceFragment для управления сервисом и была добавлена xml-разметка fragment_music_service.xml для данного фрагмента.

Далее были внесены изменения в файл activity_main_drawer.xml, где был добавлен новый пункт меню, а также была импортирована иконка в папку drawable.

    <item
        android:id="@+id/nav_music_service"
        android:icon="@drawable/ic_music_note"
        android:title="Музыка (Service)" />
Затем новый фрагмент был записан в файл mobile_navigation.xml.

    <fragment
        android:id="@+id/nav_music_service"
        android:name="ru.mirea.grachevaks.mireaproject.ui.MusicServiceFragment"
        android:label="Музыка (Service)" />    
Также были внесены изменения в файл MainACtivity.java.

    mAppBarConfiguration = new AppBarConfiguration.Builder(
            R.id.nav_data,
            R.id.nav_web,
            R.id.nav_home,
            R.id.nav_gallery,
            R.id.nav_slideshow,
            R.id.nav_music_service)
            .setOpenableLayout(drawer)
            .build();
                
![image](https://github.com/user-attachments/assets/f0a79b95-9093-4b92-a982-39955c880138)

![image](https://github.com/user-attachments/assets/a3924ea1-3636-47e0-bdfa-50b3d0439a51)
