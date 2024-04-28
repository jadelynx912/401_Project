import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class securityQuestionController {
    @FXML
    private TextField answer;
    @FXML
    private Label question;
    @FXML
    private Button submit;
    private String correctAnswer;

    @FXML
    private void initialize(){
        Random rand = new Random();
        int num = rand.nextInt(2);
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentLogin.csv"))) {
            String line = reader.readLine();
            String[] lineArray = line.split(",");
            num = (num * 2) + 2;
            String questionText = lineArray[num];
            correctAnswer = lineArray[num + 1];
            question.setText(questionText);
        }
        catch(Exception e){ System.out.println("File not working"); }
    }

    @FXML
    void checkAnswer(ActionEvent event) {
        if (answer.getText().equals(correctAnswer)){
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Parent root;
            try {
            root = FXMLLoader.load(MainApp.class.getResource("StudentHome.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Parent root;
            try {
            root = FXMLLoader.load(MainApp.class.getResource("StudentLogin.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void backToAccountSelect (ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(MainApp.class.getResource("AccountTypeSelect.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Account Type Select Error");
        }
    }

}
