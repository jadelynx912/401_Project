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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ScoreApplicationController {
    @FXML
    private Label GPA;
    @FXML
    private Button back;
    @FXML
    private Label citizenship;
    @FXML
    private Label classStanding;
    @FXML
    private TextArea comments;
    @FXML
    private Label credits;
    @FXML
    private Label filePath;
    @FXML
    private Label financialInfo;
    @FXML
    private Label gender;
    @FXML
    private Label gradDate;
    @FXML
    private Label major;
    @FXML
    private Label minors;
    @FXML
    private Label personalStatement;
    @FXML
    private TextArea questions;
    @FXML
    private Label scholarshipDesc;
    @FXML
    private Label scholarshipName;
    @FXML
    private TextField score;
    @FXML
    private Label studentID;
    @FXML
    private Button submitButton;
    @FXML
    private Label transfer;
    @FXML
    private Label dateApplied;

    @FXML
    void initialize(){
        String line;
        String[] lineParts;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentScholarship.csv"))){
            line = reader.readLine();
            scholarshipName.setText(line.split("#")[0]);
            scholarshipDesc.setText(line.split("#")[1] + "\t\t" + line.split("#")[2] + "\n" + line.split("#")[4] + " " + line.split("#")[5] + " " + line.split("#")[6]);
        }catch(Exception e) {}

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentApplicant.csv"))){
            while ((line = reader.readLine()) != null){
                lineParts = line.split("#");
                studentID.setText(lineParts[2]);
                major.setText(lineParts[3]);
                minors.setText(lineParts[4]);
                GPA.setText(lineParts[5]);
                gradDate.setText(lineParts[6]);
                classStanding.setText(lineParts[7]);
                credits.setText(lineParts[8]);
                citizenship.setText(lineParts[9]);
                gender.setText(lineParts[10]);
                transfer.setText(lineParts[11]);
                financialInfo.setText(lineParts[12]);
                personalStatement.setText(lineParts[13]);
                filePath.setText(lineParts[14]);
                dateApplied.setText(lineParts[15]);
                if (lineParts.length > 16){
                    score.setText(lineParts[16]);
                    comments.setText(lineParts[17]);
                }
            }
        } catch (Exception e){ System.out.println(e.getMessage()); }
    }

    @FXML
    void submitForm(ActionEvent event) {
        String reviewerScore = score.getText();
        String reviewerComments = comments.getText();
        String reviewerQuestions = questions.getText();
        if (!reviewerQuestions.equals("")){
            System.out.println("Have backend handle sending an email to students with the question: " + reviewerQuestions);
        }
        String rewrite = "";
        String line;
        String username = "";
        String lineFound = "";

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentApplicant.csv"))){
            line = reader.readLine();
            username = line.split("#")[0];
        }catch(Exception e) {}
        
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/" + scholarshipName.getText() + "_Applicants.csv"))){
            while ((line = reader.readLine()) != null){
                if ((line.split("#"))[0].equals(username)){
                    lineFound = line;
                }
                else{
                    rewrite += line + "\n";
                }
            }
        } catch (Exception e){ System.out.println("Error opening StudentLogins file"); }

        if (lineFound != "") {
            try (FileWriter fw = new FileWriter(new File("src/Data/" + scholarshipName.getText() + "_Applicants.csv"))) {
                fw.write(rewrite);
                fw.write(lineFound.split("#")[0] + "#" + lineFound.split("#")[1] + "#" + lineFound.split("#")[2] + "#" +
                lineFound.split("#")[3] + "#" + lineFound.split("#")[4] + "#" + lineFound.split("#")[5] + "#" + 
                lineFound.split("#")[6] + "#" + lineFound.split("#")[7] + "#" + lineFound.split("#")[8] + "#" + 
                lineFound.split("#")[9] + "#" + lineFound.split("#")[10] + "#" + lineFound.split("#")[11] + "#" +
                lineFound.split("#")[12] + "#" + lineFound.split("#")[13] + "#" + lineFound.split("#")[14] + "#" + 
                lineFound.split("#")[15] + "#" + reviewerScore + "#" + reviewerComments + "\n");
            } catch (Exception e) { System.out.println("Error writing to logins file"); }

            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Parent root;
            try {
                root = FXMLLoader.load(MainApp.class.getResource("ReviewerHomeApplicants.fxml"));
                Scene scene = new Scene(root);

                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                System.out.println("Login Error");
            }
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(MainApp.class.getResource("ReviewerHomeApplicants.fxml"));
            Scene scene = new Scene(root);

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
}
