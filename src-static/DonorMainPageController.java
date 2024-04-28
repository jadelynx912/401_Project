import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.Reader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class DonorMainPageController {
    @FXML
    private Label titleLabel;

    @FXML
    private Button createButton;

    @FXML
    private Button viewButton;


     @FXML
    private TextField nameField;

     @FXML
    private TextField emailField;

     @FXML
    private TextField amountField;

     @FXML
    private TextField awardNameField;

    @FXML
    private TextArea awardRequirementsField;

    @FXML
    private TextArea recipientRequirementsField;

    @FXML
    private TextArea commentsField;

    @FXML
    private TextField deadlineField;

    @FXML
    private Button submitScholarshipButton;


    @FXML 
    private void initialize () {
        //titleLabel.setText("Scholarship Sponsor Main Menu");
        //createButton.setOnAction(event -> handleCreateButton());
        //viewButton.setOnAction(event -> handleViewButton());

    }

    @FXML
    public void handleCreateButton(ActionEvent event) {
        System.out.println("View Created Scholarships button clicked!");
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SponsorCreateNewScholarshipPage.fxml"));
            Scene createScholarshipScene = new Scene(loader.load());

            primaryStage.setScene(createScholarshipScene);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        // submitScholarshipButton.setOnAction(event -> submitScholarship(nameField.getText(),
        // emailField.getText(), amountField.getText(), awardNameField.getText(),
        // awardRequirementsField.getText(), recipientRequirementsField.getText(),
        // commentsField.getText(), deadlineField.getText()));

        
    }

    //I was thinking about creating a view scholarships tab but we dont have it in our
    //SRS so we can discuss if we want to implement it or not
    @FXML
    public void handleViewButton(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SponsorViewScholarshipsPage.fxml"));
            Scene viewScholarshipScene = new Scene(loader.load());

            primaryStage.setScene(viewScholarshipScene);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        displayScholarships("src/Data/SponsorScholarships.csv");

    }

    @FXML
    public void displayScholarships(String filePath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                System.out.println(fields[3] + " " + fields[2]);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void submitScholarship(ActionEvent event) {

        String filePath = "src/Data/SponsorScholarships.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.format("%s#%s#%s#%s#%s#%s#%s#%s\n",
            nameField.getText(), emailField.getText(), amountField.getText(), awardNameField.getText(), awardRequirementsField.getText(), recipientRequirementsField.getText(),
            commentsField.getText(), deadlineField.getText()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SponsorScholarshipCreationConfirmation.fxml"));
            Scene viewScholarshipScene = new Scene(loader.load());

            primaryStage.setScene(viewScholarshipScene);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void displayMain (ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SponsorMainPage.fxml"));
            Scene viewScholarshipScene = new Scene(loader.load());

            primaryStage.setScene(viewScholarshipScene);
            primaryStage.show();
        }
        catch (IOException e) {
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

    

