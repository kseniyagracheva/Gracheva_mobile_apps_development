**Отчет по 1 практической работе**
----
В прцессе выполнения данной работы был создан проект Lesson1, в котором были созданы 3 модуля: Layouttype, control_lesson, button_clicker. Был создан эмулятор, и проведена ознакомительная работа с Android Studio.

![image](https://github.com/user-attachments/assets/cdbc3903-de96-4774-bb91-78c6c86dd61a)

Сперва для ознакомительной работы с компонентами экрана был на экран был добавлен компонент TextView, а также было изменено его содержимое.

![image](https://github.com/user-attachments/assets/7ecdfeea-9ce2-4fea-9cdc-a28916844cea)

Затем были создано новые файлы для изучения атрибутов макетов "ViewGroup".

**Макет  с использованием LineraLayout в модуле Layouttype**

![image](https://github.com/user-attachments/assets/0f705caa-132f-4a68-8189-822da8def62d)

**Макет с использованием TableLayout**

![image](https://github.com/user-attachments/assets/26438b8d-7ea1-4739-86ee-f34547611704)

**Макет с использованием ConstraintLayout**

![image](https://github.com/user-attachments/assets/6837871c-40e0-448f-88eb-41f63c9cce29)

В модуле control_lesson был создан собственный макет на основе изученных элементов.

![image](https://github.com/user-attachments/assets/04cc8177-7f4a-4142-946d-a7ecdb215ac1)

Затем был создан новый файл activity_second.xml для изучения горизонтальной и вертикальной ориентаций.

![image](https://github.com/user-attachments/assets/c7482216-48b5-4b8c-894c-51e5a756d26e)


![image](https://github.com/user-attachments/assets/8ace5ae9-ef9e-41f0-8917-2fa32c90cdc2)

В итоге получилась следующая структура

![image](https://github.com/user-attachments/assets/bd87cf6b-75cc-46f9-b6db-47742583b2a1)

В модуле button_clicker были отработы обработчики событий нажатия на кнопку

![image](https://github.com/user-attachments/assets/2ff7e0ee-77e0-4ead-b908-0cd5bcacc095)

![image](https://github.com/user-attachments/assets/5bce1efb-37ea-45a8-8483-c11f9de4139e)


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
        private Button btnWhoAmI_KG;
        private CheckBox checkBox;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);
            tvOut = findViewById(R.id.tvOut);
            btnWhoAmI_KG = findViewById(R.id.WhoAmI_KG);
            checkBox = findViewById(R.id.checkBox);
            boolean isChecked = !checkBox.isChecked();
    
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
    
            View.OnClickListener oclBtnWhoAmI_KG = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvOut.setText("Мой номер по списку № 8");
                    checkBox.setChecked(isChecked);
                }
            };
            btnWhoAmI_KG.setOnClickListener(oclBtnWhoAmI_KG);
        }
        public void onItIsNotMeButtonClick_KseniyaGracheva(View view) {
            boolean isChecked = !checkBox.isChecked();
            checkBox.setChecked(isChecked);
    
            if (isChecked) {
                tvOut.setText("Мой номер по списку № 8");
            } else {
                tvOut.setText("Я Грачева Ксения!");
            }
    
        }
    }


