import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ScholarshipApplicationController {
    @FXML
    private TextField GPA;
    @FXML
    private TextField classStanding;
    @FXML
    private TextField credits;
    @FXML
    private Label errorMessage;
    @FXML
    private TextArea financialInfo;
    @FXML
    private TextField gender;
    @FXML
    private TextField gradDate;
    @FXML
    private TextField major;
    @FXML
    private TextField minors;
    @FXML
    private TextField name;
    @FXML
    private TextField netID;
    @FXML
    private RadioButton noCitizen;
    @FXML
    private RadioButton noTransfer;
    @FXML
    private TextArea personalStatement;
    @FXML
    private TextField studentID;
    @FXML
    private Button submitButton;
    @FXML
    private RadioButton yesCitizen;
    @FXML
    private RadioButton yesTransfer;
    @FXML 
    private Label scholarshipName;
    @FXML
    private Label scholarshipDesc;
    @FXML
    private Button upload;
    @FXML
    private Label filePath;

    @FXML
    private void initialize(){
        String line;
        String[] lineParts;
        String username = "";

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentLogin.csv"))){
            line = reader.readLine();
            username = line.split(",")[0];
        }catch(Exception e) {}

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentScholarship.csv"))){
            line = reader.readLine();
            scholarshipName.setText(line.split("#")[0]);
            scholarshipDesc.setText(line.split("#")[1] + "       " + line.split("#")[2] + "\n" + line.split("#")[4] + " " + line.split("#")[5] + " " + line.split("#")[6]);
        }catch(Exception e) {}

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/studentInfo.csv"))){
            while ((line = reader.readLine()) != null){
                if ((lineParts = line.split("/"))[0].equals(username)){
                    netID.setText(username);
                    name.setText(lineParts[1]);
                    studentID.setText(lineParts[3]);
                    major.setText(lineParts[4]);
                    minors.setText(lineParts[5]);
                    GPA.setText(lineParts[6]);
                    gradDate.setText(lineParts[7]);
                    classStanding.setText(lineParts[8]);
                    credits.setText(lineParts[9]);
                    if (lineParts[10].equals("no")){ noCitizen.setSelected(true); }
                    else { yesCitizen.setSelected(true); }
                    gender.setText(lineParts[11]);
                    if (lineParts[12].equals("no")){ noTransfer.setSelected(true); }
                    else { yesTransfer.setSelected(true); }
                    financialInfo.setText(lineParts[13]);
                    personalStatement.setText(lineParts[14]);
                }
            }
        } catch (Exception e){ System.out.println("Error opening StudentLogins file"); }
    }

    @FXML
    void checkCitizen(MouseEvent event) {
        Node buttonClicked = (Node)event.getSource();
        if (buttonClicked.getId().equals("yesCitizen")){
            noCitizen.setSelected(false);
        }
        else if (buttonClicked.getId().equals("noCitizen")){
            yesCitizen.setSelected(false);
        }
        else {
            System.out.println("Transfer buttons acting up");
        }
    }

    @FXML
    void checkTransfer(MouseEvent event) {
        Node buttonClicked = (Node)event.getSource();
        if (buttonClicked.getId().equals("yesTransfer")){
            noTransfer.setSelected(false);
        }
        else if (buttonClicked.getId().equals("noTransfer")){
            yesTransfer.setSelected(false);
        }
        else {
            System.out.println("Transfer buttons acting up");
        }
    }

    @FXML
    void uploadFile(ActionEvent event){
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
        File selectedFile = fc.showOpenDialog(primaryStage);
        filePath.setText(selectedFile.getName());
        filePath.setVisible(true);
        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    }

    @FXML
    void submitForm(ActionEvent event) {
        String username = netID.getText();

        try (FileWriter fw = new FileWriter(new File("src/Data/" + username + "_Applications.csv"), true)){
            fw.write(scholarshipName.getText() + "#" + scholarshipDesc.getText().replace("\n", "\\n") + "#" + "Waiting for Review" + "\n");
        } catch (Exception e) { System.out.println("Problem saving application"); }

        String transfer = yesTransfer.isSelected() ? "yes" : "no";
        String citizenship = yesCitizen.isSelected() ? "yes" : "no";
        try (FileWriter fw = new FileWriter(new File("src/Data/" + scholarshipName.getText() + "_Applicants.csv"), true)){
            fw.write(netID.getText() + "#" + name.getText() + "#" + studentID.getText() + "#" + 
                    major.getText() + "#" + minors.getText() + "#" + GPA.getText() + "#" + gradDate.getText() + "#" + 
                    classStanding.getText() + "#" + credits.getText() + "#" + citizenship + "#" + gender.getText() + "#" + 
                    transfer + "#" + financialInfo.getText() + "#" + personalStatement.getText() + "#" + filePath.getText() + "#" 
                    + LocalDateTime.now().toString().split("T")[0].replace("-", "/") + "\n");
        } catch (Exception e) { System.out.println("Problem saving application"); }

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
