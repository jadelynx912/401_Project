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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentHomeController {
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
    private Button editAccount;
    @FXML
    private Button nextPage;
    @FXML
    private Button prevPage;
    @FXML
    private TextField searchBar;
    private int numDisplayOn = 0;
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
    private Button checkStatus;
    @FXML
    private Button backButton;
    private String filename = "src/Data/scholarships.csv";
    private int readIndex = 1;

    @FXML
    private void initialize(){
        String line;
        String[] lineParts;
        int count = 0;
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            if (numDisplayOn < 0) { numDisplayOn += 5; }
            while((line = reader.readLine()) != null){
                found = false;
                lineParts = line.split("#");
                if (count == 0 + numDisplayOn){
                    Name1.setText(lineParts[0]);
                    if (readIndex == 1){Desc1.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{
                        try (BufferedReader r1 = new BufferedReader(new FileReader("src/Data/scholarships.csv"))) {
                            while ((line = r1.readLine()) != null){
                                if (lineParts[0].equals(line.split("#")[0])){ found = true; }
                            }
                            if (found) {Desc1.setText("Waiting for Review");}
                            else {Desc1.setText("Awarded");}
                        } catch (Exception e) {System.out.println("Error writing to curr scholarship file"); }
                    }
                }
                else if (count == 1 + numDisplayOn){
                    Name2.setText(lineParts[0]);
                    if (readIndex == 1){Desc2.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{
                        try (BufferedReader r1 = new BufferedReader(new FileReader("src/Data/scholarships.csv"))) {
                            while ((line = r1.readLine()) != null){
                                if (lineParts[0].equals(line.split("#")[0])){ found = true; }
                            }
                            if (found) {Desc2.setText("Waiting for Review");}
                            else {Desc2.setText("Awarded");}
                        } catch (Exception e) {System.out.println("Error writing to curr scholarship file"); }
                    }
                }
                else if (count == 2 + numDisplayOn){
                    Name3.setText(lineParts[0]);
                    if (readIndex == 1){Desc3.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{
                        try (BufferedReader r1 = new BufferedReader(new FileReader("src/Data/scholarships.csv"))) {
                            while ((line = r1.readLine()) != null){
                                if (lineParts[0].equals(line.split("#")[0])){ found = true; }
                            }
                            if (found) {Desc3.setText("Waiting for Review");}
                            else {Desc3.setText("Awarded");}
                        } catch (Exception e) {System.out.println("Error writing to curr scholarship file"); }
                    }
                }
                else if (count == 3 + numDisplayOn){
                    Name4.setText(lineParts[0]);
                    if (readIndex == 1){Desc4.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{
                        try (BufferedReader r1 = new BufferedReader(new FileReader("src/Data/scholarships.csv"))) {
                            while ((line = r1.readLine()) != null){
                                if (lineParts[0].equals(line.split("#")[0])){ found = true; }
                            }
                            if (found) {Desc4.setText("Waiting for Review");}
                            else {Desc4.setText("Awarded");}
                        } catch (Exception e) {System.out.println("Error writing to curr scholarship file"); }
                    }
                }
                else if (count == 4 + numDisplayOn){
                    Name5.setText(lineParts[0]);
                    if (readIndex == 1){Desc5.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{
                        try (BufferedReader r1 = new BufferedReader(new FileReader("src/Data/scholarships.csv"))) {
                            while ((line = r1.readLine()) != null){
                                if (lineParts[0].equals(line.split("#")[0])){ found = true; }
                            }
                            if (found) {Desc5.setText("Waiting for Review");}
                            else {Desc5.setText("Awarded");}
                        } catch (Exception e) {System.out.println("Error writing to curr scholarship file"); }
                    }
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
            System.out.println(e.getMessage());
            Name1.setText("");
            Desc1.setText("");
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
    void editAccountInfo(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
        root = FXMLLoader.load(MainApp.class.getResource("AccountEditing.fxml"));
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
        numDisplayOn = 0;
        initialize();
    }

    @FXML
    void apply(ActionEvent event){
        String line;
        int count = 0;
        String id = ((Node)event.getSource()).getId();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/scholarships.csv"))) {
            while ((line = reader.readLine()) != null){
                if (count == numDisplayOn + Integer.parseInt(Character.toString(id.charAt(id.length() - 1))) - 1){
                    try (FileWriter fw = new FileWriter(new File("src/Data/currentScholarship.csv"))){
                        fw.write(line);
                    } catch (Exception e) {System.out.println("Error writing to curr scholarship file"); }
                }
                count += 1;
            }
        } catch (Exception e) {System.out.println("Error getting scholarship");}

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
        root = FXMLLoader.load(MainApp.class.getResource("ScholarshipApplication.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void checkStatus(ActionEvent event){
        String line;
        String username = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentLogin.csv"))) {
            while ((line = reader.readLine()) != null){
                username = line.split(",")[0];
            }
        } catch (Exception e) {System.out.println("Error getting username"); }
        filename = "src/Data/" + username + "_Applications.csv";
        numDisplayOn = 0;
        readIndex = 2;
        initialize();

        apply1.setVisible(false);
        apply2.setVisible(false);
        apply3.setVisible(false);
        apply4.setVisible(false);
        apply5.setVisible(false);
        backButton.setVisible(true);
        checkStatus.setVisible(false);
    }

    @FXML
    void backToHomepage(ActionEvent event) {
        apply1.setVisible(true);
        apply2.setVisible(true);
        apply3.setVisible(true);
        apply4.setVisible(true);
        apply5.setVisible(true);
        backButton.setVisible(false);
        checkStatus.setVisible(true);
        readIndex = 1;
        numDisplayOn = 0;
        filename = "src/Data/scholarships.csv";
        initialize();
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
