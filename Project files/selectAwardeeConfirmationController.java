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
import javafx.stage.Stage;

public class selectAwardeeConfirmationController {
    @FXML
    private Button back;
    @FXML
    private Label question;
    @FXML
    private Button submit;
    @FXML
    private Label titleLabel;

    @FXML
    void initialize(){
        String line = "";
        String[] lineParts;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentApplicant.csv"))) {
            while((line = reader.readLine()) != null){
                lineParts = line.split("#");
                if (lineParts.length > 16){ question.setText(lineParts[2] + "\t\tDate Applied: " + lineParts[15] +"\nScore: " + lineParts[16] + "\nComments: " + lineParts[17]); }
                else { question.setText(lineParts[2] + "\t\tDate Applied: " + lineParts[15] + "\nScore: \nComments: ");}
            }
        }catch (Exception e) { System.out.println("Error displaying applicant");}
    }

    @FXML
    void backToAccountSelect(ActionEvent event) {
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
    void backToApplicants(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(MainApp.class.getResource("SelectAwardee.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Account Type Select Error");
        }
    }

    @FXML
    void checkAnswer(ActionEvent event) {
        String line = "";
        String scholarshipName = "";
        String awardeeLine = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentScholarship.csv"))){
            line = reader.readLine();
            scholarshipName = line.split("#")[0];
        }catch(Exception e) {}

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentApplicant.csv"))){
            awardeeLine = reader.readLine();
        }catch(Exception e) {}

        try (FileWriter fw = new FileWriter(new File("src/Data/" + scholarshipName + "_Applicants.csv"), true)) {
            fw.write("Awardee: " + awardeeLine + "\n");
        }
        catch (Exception e){ System.out.println("Error writing to logins file"); }
    
        try (FileWriter fw = new FileWriter(new File("src/Data/readyToBeAwarded.csv"), true)) {
            fw.write(line + "\n");
        }
        catch (Exception e){ System.out.println("Error writing to logins file"); }

        line="";
        String rewrite="";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/scholarships.csv"))){
            while ((line = reader.readLine()) != null){
                if (!(line.split("#"))[0].equals(scholarshipName)){
                    rewrite += line + "\n";
                }
            }
        } catch (Exception e){ System.out.println("Error opening StudentLogins file"); }

        try (FileWriter fw = new FileWriter(new File("src/Data/scholarships.csv"))) {
            fw.write(rewrite);
        } catch (Exception e) { System.out.println("Error writing to logins file"); }


        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(MainApp.class.getResource("ReviewerHome.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Account Type Select Error");
        }

    }

}
