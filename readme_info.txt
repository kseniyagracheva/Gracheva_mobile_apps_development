**Отчет по 1 практической работе**
----
В начале выполнения данной практической работы была установлена среда разработки Android Studio, затем был создан первый поект Lesson 1, в котором далее создавались различные модули.

Layouttype Module
--
Сперва для ознакомительной работы с компонентами экрана был на экран был добавлен компонент TextView, а также было изменено его содержимое.


![image](https://github.com/user-attachments/assets/26977d25-76e5-476e-baef-290e40219fc5)

Затем были создано новые файлы для изучения атрибутов макетов "ViewGroup".

**Макет  с использованием LineraLayout**

![image](https://github.com/user-attachments/assets/012a58c4-6502-46a3-b6e9-d216e670be65)

**Макет с использованием TableLayout**

![image](https://github.com/user-attachments/assets/7b2ed5a5-d273-4bc1-8442-bb5fc64b5d83)

**Макет с использованием ConstraintLayout**

![image](https://github.com/user-attachments/assets/0a1caf41-1977-4633-9841-6eb54fa7d977)


Control_lesson Module
--
В данном модуле был создан собственный макет на основе изученных элементов.

![image](https://github.com/user-attachments/assets/05f13fe0-f085-4f34-a528-a71288b45dad)

Затем был создан новый файл activity_second.xml, в котором был создан макет содеожащий PlainText и 6 кнопок.

![image](https://github.com/user-attachments/assets/60e15832-a43d-420a-b996-af199aabe118)

Далее был создан альтернативный файл actiity_second.xml (land) для альбомной ориентации.

![image](https://github.com/user-attachments/assets/455af644-5ab5-47c4-815e-d18d04cc7452)

ButtonClicker Module
--

В данном модуле был создан макет с использованием двух кнопок, текстового поля и чекбокса. А также, был модифицирован код MainActivity.java, где были добавлены обработчики событий нажатия на кнопки двумя разными способами.

![image](https://github.com/user-attachments/assets/f4641d88-da2e-4cd9-a243-ff19ca879e13)

Код в файле MainActivity.java выглядит следующим образом
  
    package com.mirea.grachevaks.buttomclicker;
    
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.TextView;
    
    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;
    
    public class MainActivity extends AppCompatActivity {
        private TextView tvOut;
        private Button btnWhoAmI;
        private Button btnItIsNotMe;
        private CheckBox checkBox;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);
            tvOut = findViewById(R.id.tvOut);
            btnWhoAmI = findViewById(R.id.WhoAmI);
            checkBox = findViewById(R.id.checkBox);
            boolean isChecked = !checkBox.isChecked();
    
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
    
            View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvOut.setText("Мой номер по списку № 8");
                    checkBox.setChecked(isChecked);
                }
            };
            btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
        }
        public void onItIsNotMeButtonClick(View view) {
            boolean isChecked = !checkBox.isChecked();
            checkBox.setChecked(isChecked);
    
            if (isChecked) {
                tvOut.setText("Это не я сделал");
            } else {
                tvOut.setText("Мой номер по списку № 15");
            }
    
        }
    }


Выводы
---
В процессе выполнения данной практической работы была произведена ознакомительная работа с Android Studio. Изучены принципы создания новых проектов и модулей в этих проектах, а также создания тестовых макетов с использованием различных элементов. Изучены принципы поддержки смены ориентации экрана, выполнено программное управление состоянием элементов и изучены два разных подхода в создании обработчиков событий для кнопок.