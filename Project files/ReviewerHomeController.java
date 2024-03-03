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

public class ReviewerHomeController {
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

    private int numDisplayOn = 0;
    private String filename = "src/Data/scholarships.csv";
    private int readIndex = 1;

    @FXML
    private void initialize(){
        String line;
        String[] lineParts;
        int count = 0;
        apply1.setVisible(false);
        apply2.setVisible(false);
        apply3.setVisible(false);
        apply4.setVisible(false);
        apply5.setVisible(false);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            if (numDisplayOn < 0) { numDisplayOn += 5; }
            while((line = reader.readLine()) != null){
                lineParts = line.split("#");
                if (count == 0 + numDisplayOn){
                    Name1.setText(lineParts[0]);
                    if (readIndex == 1){Desc1.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{ Desc1.setText(lineParts[readIndex]);}
                    apply1.setVisible(true);                }
                else if (count == 1 + numDisplayOn){
                    Name2.setText(lineParts[0]);
                    if (readIndex == 1){Desc2.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{ Desc2.setText(lineParts[readIndex]);}
                    apply2.setVisible(true);
                }
                else if (count == 2 + numDisplayOn){
                    Name3.setText(lineParts[0]);
                    if (readIndex == 1){Desc3.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{ Desc3.setText(lineParts[readIndex]);}
                    apply3.setVisible(true);
                }
                else if (count == 3 + numDisplayOn){
                    Name4.setText(lineParts[0]);
                    if (readIndex == 1){Desc4.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{ Desc4.setText(lineParts[readIndex]);}
                    apply4.setVisible(true);
                }
                else if (count == 4 + numDisplayOn){
                    Name5.setText(lineParts[0]);
                    if (readIndex == 1){Desc5.setText(lineParts[1] + "\t\t" + lineParts[2] + "\n" + lineParts[4] + " " + lineParts[5] + " " + lineParts[6]);}
                    else{ Desc5.setText(lineParts[readIndex]);}
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
    void apply(ActionEvent event) {
        String line = "";
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

        String name = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentScholarship.csv"))){
            name = reader.readLine().split("/")[0];
        }catch (Exception e) {System.out.println("Error reading from currentScholarship"); }

        filename = "src/Data/" + name + "_Applicants.csv";

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(MainApp.class.getResource("ReviewerHomeApplicants.fxml"));
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
