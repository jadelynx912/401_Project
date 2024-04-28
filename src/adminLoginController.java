

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class adminLoginController {
    @FXML
    private Button adminLoginButton;

    @FXML
    public Text adminLoginError;

    @FXML
    private TextField adminPasswordField;

    @FXML
    private TextField adminUsernameField;

    @FXML
    void authenticate(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String csvFile = "src/Data/adminLogins.csv";
        String line;
        String csvSplitBy = ",";

        // //AdminMain.showMainPage(primaryStage);
        // Parent root;
        // 

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = reader.readLine()) != null) {
                String[] user = line.split(csvSplitBy);
                if (user[0].equals(adminUsernameField.getText()) && user[1].equals(adminPasswordField.getText())) {
                    Parent root;
                    try {
                    root = FXMLLoader.load(MainApp.class.getResource("AdminMainPage.fxml"));
                    Scene scene = new Scene(root);

                    primaryStage.setScene(scene);
                    primaryStage.show();
                    } catch (IOException e) {
                        System.out.println("Main Page Error");
                        e.printStackTrace();
                    }
                    adminLoginError.setVisible(false);
                    break;
                }
            }
            adminLoginError.setVisible(true);
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


