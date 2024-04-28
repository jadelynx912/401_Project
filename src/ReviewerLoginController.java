import java.io.BufferedReader;
import java.io.FileReader;
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

public class ReviewerLoginController {
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

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/reviewerLogins.csv"))) {
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(",");
                if (user[0].equals(username.getText()) && user[1].equals(password.getText())) {
                    Parent root;
                    try {
                    root = FXMLLoader.load(MainApp.class.getResource("ReviewerHome.fxml"));
                    Scene scene = new Scene(root);

                    primaryStage.setScene(scene);
                    primaryStage.show();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
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
