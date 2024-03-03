import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReviewerHomeApplicantsController {

    ObservableList<String> searchChoiceList = FXCollections.observableArrayList("Degree", "Gender", "Application Date");

    @FXML
    private Label Desc1;
    @FXML
    private Label Desc2;
    @FXML
    private Label Desc3;
    @FXML
    private Label Desc4;
    @FXML
    private Label Desc5;
    @FXML
    private Label Name1;
    @FXML
    private Label Name2;
    @FXML
    private Label Name3;
    @FXML
    private Label Name4;
    @FXML
    private Label Name5;
    @FXML
    private Button apply1;
    @FXML
    private Button apply2;
    @FXML
    private Button apply3;
    @FXML
    private Button apply4;
    @FXML
    private Button apply5;
    @FXML
    private Button backButton;
    @FXML
    private Button checkStatus;
    @FXML
    private Button editAccount;
    @FXML
    private Button nextPage;
    @FXML
    private Button prevPage;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox<String> searchByChoice;

    @FXML
    private MenuButton searchByMenu;

    private int numDisplayOn = 0;
    private String scholarshipName;

    @FXML
    private void initialize(){
        String line;
        String[] lineParts;
        int count = 0;
        Name1.setText("Applicant 1");
        Name2.setText("Applicant 2");
        Name3.setText("Applicant 3");
        Name4.setText("Applicant 4");
        Name5.setText("Applicant 5");
        apply1.setVisible(false);
        apply2.setVisible(false);
        apply3.setVisible(false);
        apply4.setVisible(false);
        apply5.setVisible(false);

        searchByChoice.setValue("Search By");
        searchByChoice.setItems(searchChoiceList);

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentScholarship.csv"))){
            scholarshipName = reader.readLine().split("#")[0];
        }catch (Exception e) {System.out.println("Error reading from currentScholarship"); }


        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/" + scholarshipName + "_Applicants.csv"))) {
            if (numDisplayOn < 0) { numDisplayOn += 5; }
            while((line = reader.readLine()) != null){
                lineParts = line.split("#");
                if (count == 0 + numDisplayOn){
                    Desc1.setText(lineParts[2] + "\nMajor: " + lineParts[3] + "\tMinor(s): " + lineParts[4] + "\nGPA: " + lineParts[5]);
                    apply1.setVisible(true);
                }
                else if (count == 1 + numDisplayOn){
                    Desc2.setText(lineParts[2] + "\nMajor: " + lineParts[3] + "\tMinor(s): " + lineParts[4] + "\nGPA: " + lineParts[5]);
                    apply2.setVisible(true);
                }
                else if (count == 2 + numDisplayOn){
                    Desc3.setText(lineParts[2] + "\nMajor: " + lineParts[3] + "\tMinor(s): " + lineParts[4] + "\nGPA: " + lineParts[5]);
                    apply3.setVisible(true);
                }
                else if (count == 3 + numDisplayOn){
                    Desc4.setText(lineParts[2] + "\nMajor: " + lineParts[3] + "\tMinor(s): " + lineParts[4] + "\nGPA: " + lineParts[5]);
                    apply4.setVisible(true);
                }
                else if (count == 4 + numDisplayOn){
                    Desc5.setText(lineParts[2] + "\nMajor: " + lineParts[3] + "\tMinor(s): " + lineParts[4] + "\nGPA: " + lineParts[5]);
                    apply5.setVisible(true);
                }
                count += 1;
            }
            if (count < numDisplayOn){
                numDisplayOn -= 5;
            }
            while (count < 5 + numDisplayOn){
                if (count == 0 + numDisplayOn){
                    Name1.setText("");
                    Desc1.setText("");
                }
                else if (count == 1 + numDisplayOn){
                    Name2.setText("");
                    Desc2.setText("");
                }
                else if (count == 2 + numDisplayOn){
                    Name3.setText("");
                    Desc3.setText("");
                }
                else if (count == 3 + numDisplayOn){
                    Name4.setText("");
                    Desc4.setText("");
                }
                else if (count == 4 + numDisplayOn){
                    Name5.setText("");
                    Desc5.setText("");
                }
                count += 1;
            }
        } catch (Exception e) { 
            Name1.setText("");
            Desc1.setText("No applicants found");
            Name2.setText("");
            Desc2.setText("");
            Name3.setText("");
            Desc3.setText("");
            Name4.setText("");
            Desc4.setText("");
            Name5.setText("");
            Desc5.setText("");
        }
    }

    @FXML
    void displayNext5(ActionEvent event) {
        numDisplayOn += 5;
        initialize();
    }

    @FXML
    void displayPrev5(ActionEvent event) {
        numDisplayOn -= 5;
        initialize();
    }

    

    @FXML
    void apply(ActionEvent event) {
        String id = ((Node)event.getSource()).getId();
        String line = "";
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/" + scholarshipName + "_Applicants.csv"))) {
            while ((line = reader.readLine()) != null){
                if (count == numDisplayOn + Integer.parseInt(Character.toString(id.charAt(id.length() - 1))) - 1){
                    try (FileWriter fw = new FileWriter(new File("src/Data/currentApplicant.csv"))){
                        fw.write(line);
                    } catch (Exception e) {System.out.println("Error writing to curr scholarship file"); }
                }
                count += 1;
            }
        } catch (Exception e) {System.out.println("Error getting scholarship");}

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
        root = FXMLLoader.load(MainApp.class.getResource("ScoreApplication.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void searchScholarships(ActionEvent event) {
        System.out.println("Search term: " + searchBar.getText());
        System.out.println("Search criteria: " + searchByChoice.getValue());
        numDisplayOn = 0;
        initialize();
    }

    @FXML
    void backToHomepage(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(MainApp.class.getResource("ReviewerHome.fxml"));
            Scene scene = new Scene(root);

            //primaryStage.setTitle("UArizona Scholarship Application Management");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Login Error");
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

    @FXML
    void selectRecipient(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(MainApp.class.getResource("SelectAwardee.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
