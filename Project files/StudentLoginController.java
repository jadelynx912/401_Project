import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentLoginController {
    @FXML
    private Button createAccountButton;
    @FXML
    private Button login;
    @FXML
    private Text loginError;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    @FXML
    void authenticate(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/studentLogins.csv"))) {
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(csvSplitBy);
                if (user[0].equals(username.getText()) && user[1].equals(password.getText())) {
                    Parent root;
                    try (FileWriter fw = new FileWriter(new File("src/Data/currentLogin.csv"))) {
                        fw.write(line);
                    }
                    catch (Exception e){ System.out.println("Error writing to login file"); }
                    try {
                    root = FXMLLoader.load(MainApp.class.getResource("securityQuestion.fxml"));
                    Scene scene = new Scene(root);

                    primaryStage.setScene(scene);
                    primaryStage.show();
                    } catch (IOException e) {
                        System.out.println("Main Page Error");
                    }
                    break;
                }
            }
            loginError.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void createAccount(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
        root = FXMLLoader.load(MainApp.class.getResource("AccountCreation.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
