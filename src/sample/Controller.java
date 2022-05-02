package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller {

    @FXML
    private ToggleGroup answers;

    @FXML
    private Text question_text;

    @FXML
    private RadioButton radio_btn_1;

    @FXML
    private RadioButton radio_btn_2;

    @FXML
    private RadioButton radio_btn_3;

    @FXML
    private RadioButton radio_btn_4;

    @FXML
    private Button answerBtn;

    private Questions[] questions = new Questions[] {
            new Questions("Что обеспечивают протоколы сетевого уровня:", new String[] {"Соединяют различные сети", "Тестируют работу в сети", "Доступ к сетевым ресурсам", "Обеспечивают сетевые режимы передачи данных"}),
            new Questions("Протокол FTP это...", new String[] {"Folder Transfer Protocol", "File and Text Protocol", "Flash Transfer Protocol", "File Transfer Protocol"}),
            new Questions("Где размещаются файловые вирусы?", new String[] {"В системной оболочке", "В самой папке", "Везде", "В исполняемых файлах"}),
            new Questions("Для передачи в сети web-страниц используется протокол...", new String[] {"www", "ftp", "dns", "http"}),
            new Questions("Электронная почта e-mail позволяет передавать", new String[] {"Только файлы", "Только сообщения", "Видеоизображения", "Сообщения и приложенные файлы"}),
            new Questions("Модем - это...", new String[] {"Сервер Интернет", "Почтовая программа", "Сетевой протокол", "Техническое устройство"}),
            new Questions("Языками разметки данных являются...", new String[] {"ADA", "Java", "SQL", "HTML и XML"})
    };


    private int nowQuestion = 0, correctAnswers;
    private String nowCorrectAnswer;

    @FXML
    public void initialize() {
        nowCorrectAnswer = questions[nowQuestion].correctAnswer();

        answerBtn.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();
            if(selectedRadioButton != null) {
                String toogleGroupValue = selectedRadioButton.getText();

                if(toogleGroupValue.equals(nowCorrectAnswer)) {
                    System.out.println("Верный ответ");
                    correctAnswers++;
                } else {
                    System.out.println("Не верный ответ");
                }

                // Это был последний вопрос
                if(nowQuestion + 1 == questions.length) {
                    radio_btn_1.setVisible(false);
                    radio_btn_2.setVisible(false);
                    radio_btn_3.setVisible(false);
                    radio_btn_4.setVisible(false);
                    answerBtn.setVisible(false);

                    question_text.setText("Вы ответили корректно на " + correctAnswers + " из " + questions.length + " вопросов!");
                } else {
                    nowQuestion++;
                    nowCorrectAnswer = questions[nowQuestion].correctAnswer();

                    question_text.setText(questions[nowQuestion].getQuestion());
                    String[] answers = questions[nowQuestion].getAnswers();


                    List<String> intList = Arrays.asList(answers);

                    Collections.shuffle(intList);

                    radio_btn_1.setText(intList.get(0));
                    radio_btn_2.setText(intList.get(1));
                    radio_btn_3.setText(intList.get(2));
                    radio_btn_4.setText(intList.get(3));

                    selectedRadioButton.setSelected(false);
                }

            }
        });
    }

}
