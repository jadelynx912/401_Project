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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AccountEditingController {
    ObservableList<String> securityQuestionsList = FXCollections.observableArrayList("What is your mother's maiden name?", 
            "What is your first pet's name?", "What is your father's middle name?", "What is the name of the street you lived on growing up?",
            "What was the make of your first car?", "What was the first job you had?", "What city did your parents meet in?", "What is your favorite animal?");
    @FXML
    private TextField GPA;
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
    private RadioButton noTransfer;
    @FXML
    private PasswordField password;
    @FXML
    private TextArea personalStatement;
    @FXML
    private TextField studentID;
    @FXML
    private Button submitButton;
    @FXML
    private RadioButton yesTransfer;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField securityQuestion1Answer;
    @FXML
    private TextField securityQuestion2Answer;
    @FXML
    private TextField securityQuestion3Answer;
    @FXML
    private ChoiceBox<String> securityQuestionChoice1;
    @FXML
    private ChoiceBox<String> securityQuestionChoice2;
    @FXML
    private ChoiceBox<String> securityQuestionChoice3;
    @FXML
    private RadioButton yesCitizen;
    @FXML
    private RadioButton noCitizen;
    @FXML
    private TextField classStanding;
    @FXML
    private TextField credits;
    @FXML
    private Button upload;
    @FXML
    private Label filePath;
    
    @FXML
    private void initialize(){
        securityQuestionChoice1.setItems(securityQuestionsList);
        securityQuestionChoice2.setItems(securityQuestionsList);
        securityQuestionChoice3.setItems(securityQuestionsList);
        String[] lineParts;
        String rewrite = "";
        boolean found = false;
        String line;
        String username = "";

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/currentLogin.csv"))){
            line = reader.readLine();
            username = line.split(",")[0];
        }catch(Exception e) {}
        
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/studentLogins.csv"))){
            while ((line = reader.readLine()) != null){
                if ((lineParts = line.split(","))[0].equals(username)){
                    found = true;
                    netID.setText(lineParts[0]);
                    password.setText(lineParts[1]);
                    securityQuestionChoice1.setValue(lineParts[2]);
                    securityQuestion1Answer.setText(lineParts[3]);
                    securityQuestionChoice2.setValue(lineParts[4]);
                    securityQuestion2Answer.setText(lineParts[5]);
                    securityQuestionChoice3.setValue(lineParts[6]);
                    securityQuestion3Answer.setText(lineParts[7]);
                }
                else{
                    rewrite += line + "\n";
                }
            }
        } catch (Exception e){ System.out.println("Error opening StudentLogins file"); }

        if (found) {
            try (FileWriter fw = new FileWriter(new File("src/Data/studentLogins.csv"))) {
                fw.write(rewrite);
            } catch (Exception e) { System.out.println("Error writing to logins file"); }
        }
        rewrite = "";
        found = false;  //Should never be false

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/studentInfo.csv"))){
            while ((line = reader.readLine()) != null){
                if ((lineParts = line.split("/"))[0].equals(username)){
                    found = true;
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
                else{
                    rewrite += line + "\n";
                }
            }
        } catch (Exception e){ System.out.println("Error opening StudentLogins file"); }

        if (found) {
                try (FileWriter fw = new FileWriter(new File("src/Data/studentInfo.csv"))) {
                    fw.write(rewrite);
                } catch (Exception e) { System.out.println("Error writing to logins file"); }
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
    void checkCitizen(MouseEvent event){
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
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        if (name.getText() == "" || studentID.getText() == "" || password.getText() == ""
                || netID.getText() == "" || GPA.getText() == "" || major.getText() == "" || 
                minors.getText() == "" || gradDate.getText() == ""){
            errorMessage.setVisible(true);
        }
        else {
            String username = netID.getText();
            String pw = password.getText();

            //Write new/updated info to both the logins and the info file
            try (FileWriter fw = new FileWriter(new File("src/Data/studentLogins.csv"), true)) {
                fw.write(username + "," + pw + "," + securityQuestionChoice1.getValue() + "," + 
                        securityQuestion1Answer.getText() + "," + securityQuestionChoice2.getValue() + "," 
                        + securityQuestion2Answer.getText() + "," + securityQuestionChoice3.getValue()
                         + "," + securityQuestion3Answer.getText() + "\n");
            }
            catch (Exception e){ System.out.println("Error writing to logins file"); }
            
            String transfer = yesTransfer.isSelected() ? "yes" : "no";
            String citizenship = yesCitizen.isSelected() ? "yes" : "no";
            try (FileWriter fw = new FileWriter(new File("src/Data/studentInfo.csv"), true)){
                fw.write(username + "/" + name.getText() + "/" + netID.getText() + "@arizona.edu" + "/" + 
                        studentID.getText() + "/" + major.getText() + "/" + minors.getText() + "/" + GPA.getText()
                         + "/" + gradDate.getText() + "/" + classStanding.getText() + "/" + credits.getText() + "/"
                         + citizenship + "/" + gender.getText() + "/" + transfer + "/" + financialInfo.getText()
                         + "/" + personalStatement.getText() + "/" + filePath.getText() + "\n");
            } catch (Exception e){
                System.out.println("Can't write to info file");
            }

            Parent root;
            try {
                root = FXMLLoader.load(MainApp.class.getResource("StudentHome.fxml"));
                Scene scene = new Scene(root);

                //primaryStage.setTitle("UArizona Scholarship Application Management");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                System.out.println("Login Error");
            }
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
