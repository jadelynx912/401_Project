import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class accountTypeSelController {

    @FXML
    private Button adminAccountButton;

    @FXML
    private Button donorAccountButton;

    @FXML
    private Button reviewerAccountButton;

    @FXML
    private Text selectAccountPrompt;

    @FXML
    private Button stewardAccountButton;

    @FXML
    private Button studentAccountButton;

    @FXML
    private Text welcomeMessage;

    @FXML
    void openAdminLogin(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(MainApp.class.getResource("AdminLogin.fxml"));
            Scene scene = new Scene(root);

            //primaryStage.setTitle("UArizona Scholarship Application Management");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Login Error");
        }
    }

    @FXML
    void openDonorLogin(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(MainApp.class.getResource("DonorLogin.fxml"));
            Scene scene = new Scene(root);

            //primaryStage.setTitle("UArizona Scholarship Application Management");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Login Error");
        }
    }

    @FXML
    void openReviewerLogin(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(MainApp.class.getResource("ReviewerLogin.fxml"));
            Scene scene = new Scene(root);

            //primaryStage.setTitle("UArizona Scholarship Application Management");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Login Error");
        }
    }

    @FXML
    void openStewardLogin(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(MainApp.class.getResource("FundsStewardLogin.fxml"));
            Scene scene = new Scene(root);

            //primaryStage.setTitle("UArizona Scholarship Application Management");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Login Error");
        }
    }

    @FXML
    void openStudentLogin(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(MainApp.class.getResource("StudentLogin.fxml"));
            Scene scene = new Scene(root);

            //primaryStage.setTitle("UArizona Scholarship Application Management");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Login Error");
        }
    }

}
