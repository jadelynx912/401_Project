import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class adminMainPageController implements Initializable{

    private ArrayList<String> scholarships;

    private ArrayList<String> unpubScholarships;

    private ArrayList<String> readyToAwardScholarships;

    @FXML
    private MenuButton adminActionSelect;

    @FXML
    private AnchorPane adminMainPane;

    @FXML
    private AnchorPane notifyApplicantsPane;

    @FXML
    private AnchorPane createOrDeletePane;

    @FXML
    private AnchorPane createAccountsPane;

    @FXML 
    private AnchorPane deleteAccountsPane;

    @FXML
    private AnchorPane createAccountFieldsPane;

    @FXML
    private AnchorPane accountCreationConfirmationPane;

    @FXML
    private AnchorPane accountDeletionConfirmationPane;

    @FXML
    private AnchorPane findDeletePane;

    @FXML
    private AnchorPane confirmDeletePane;

    @FXML
    private AnchorPane notificationConfirmation;

    @FXML
    private AnchorPane scholarshipFormCreationPane;

    @FXML
    private ScrollPane scholarshipToPublishScroll;

    @FXML
    private AnchorPane formCreationConfirmationPane;

    @FXML
    private AnchorPane scholarshipAwardDetailsPane;

    @FXML
    private AnchorPane scholarshipsToAwardPane;

    @FXML
    private AnchorPane awardApprovalConfirmationPane;

    @FXML
    private Button createAccounts;

    @FXML
    private Button deleteAccounts;

    @FXML
    private Label adminMainTitle;

    @FXML
    private Text applicationDeadline;

    @FXML
    private DatePicker applicationDeadlinePicker;

    @FXML
    private Text confirmationAmount;

    @FXML
    private Text confirmationDeadline;

    @FXML
    private Text confirmationEssayQ;

    @FXML
    private Text confirmationGPA;

    @FXML
    private Text confirmationMajors;

    @FXML
    private Text confirmationText;

    @FXML
    private Text confirmationTitle;

    @FXML
    private MenuItem createScholarship;

    @FXML
    private Button createScholarshipButton;

    @FXML
    private Button createScholarshipDone;

    @FXML
    private Text essayQuestion;

    @FXML
    private TextField essayQuestionField;

    @FXML
    private MenuItem manageAccounts;

    @FXML
    private Text minimumGPA;

    @FXML
    private TextField minimumGPAField;

    @FXML
    private MenuItem notifyApplicants;

    @FXML
    private Text preferredMajor;

    @FXML
    private TextField preferredMajorField;

    @FXML
    private Text scholarshipAmount;

    @FXML
    private TextField scholarshipAmountField;

    @FXML
    private Text scholarshipTitle;

    @FXML
    private TextField scholarshipTitleField;

    @FXML
    private TextField searchBar;

    @FXML
    private Text searchScholarships;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private ListView<String> unpublishedScholarshipsList;

    @FXML
    private ListView<String> scholarshipsToAwardList;

    @FXML
    private MenuButton accountTypeSel;

    @FXML
    private MenuItem adminType;

    @FXML 
    private MenuItem reviewerType;

    @FXML
    private MenuItem sponsorType;

    @FXML
    private MenuItem stewardType;

    @FXML
    private Button createAccountButton;

    @FXML
    private TextField accountCreateUser;

    @FXML
    private TextField accountCreatePass;

    @FXML
    private TextField accountCreatePassConfirm;

    @FXML
    private TextField accountCreateEmail;

    @FXML
    private Text uniqueUserError;

    @FXML
    private Text passMatchError;

    @FXML
    private Text confirmUser;

    @FXML
    private Text confirmType;

    @FXML
    private Text confirmEmail;

    @FXML
    private Button accountCreateDone;

    @FXML
    private Button createAnotherAccount;

    @FXML
    private Text confirmDeleteType;

    @FXML
    private Text confirmDeleteUser;

    @FXML
    private Text confirmDeleteEmail;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private Button findDeleteButton;

    @FXML
    private TextField findDeleteUser;

    @FXML
    private Button accountDeleteDone;

    @FXML
    private Button deleteAnotherAccount;

    @FXML
    private ScrollPane scholarshipToNotifyScroll;

    @FXML
    private Button notifyApplicantsButton;

    @FXML
    private Text notifSuccess;

    @FXML
    private GridPane scholarshipPubGridPane;

    @FXML
    private Text pubTitle, pubAmount, pubDeadline, pubCreator, pubScholReq, pubRecReq, pubNotes;

    @FXML
    private Text titleInfo, amountInfo, scholarshipRequirementsInfo, recipientRequirementsInfo, notesInfo, deadlineInfo, creatorInfo;

    @FXML
    private Text confirmEmail1, confirmType1, confirmUser1;

    @FXML
    private Text awardTitle, awardAmount, awardDetails, studentID, studentScore, studentComments;

    @FXML
    void search(ActionEvent event) {
        scholarships = getScholarships();
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(),scholarships));
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        scholarships = getScholarships();
        unpubScholarships = getUnpubScholarships();
        listView.getItems().addAll(scholarships);
        unpublishedScholarshipsList.getItems().addAll(unpubScholarships);
        readyToAwardScholarships = getReadyToAward();
        scholarshipsToAwardList.getItems().addAll(readyToAwardScholarships);
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    private ArrayList<String> getScholarships() {
        String csvFile = "src/Data/scholarships.csv";
        String line;

        ArrayList<String> returnScholarships = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");
                returnScholarships.add(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnScholarships;
    }

    private ArrayList<String> getReadyToAward() {
        String csvFile = "src/Data/readyToBeAwarded.csv";
        String line;

        ArrayList<String> returnScholarships = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");
                returnScholarships.add(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnScholarships;
    }

    private ArrayList<String> getUnpubScholarships() {
        String csvFile = "src/Data/SponsorScholarships.csv";
        String line;

        ArrayList<String> returnScholarships = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");
                returnScholarships.add(parts[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnScholarships;
    }

    @FXML
    void displayFormCreation (ActionEvent event) {
        adminActionSelect.setText("Create Scholarship Forms");

        notifyApplicantsPane.setVisible(false);
        createOrDeletePane.setVisible(false);
        createAccountsPane.setVisible(false);
        deleteAccountsPane.setVisible(false);
        createAccountFieldsPane.setVisible(false);
        accountCreationConfirmationPane.setVisible(false);
        accountDeletionConfirmationPane.setVisible(false);
        findDeletePane.setVisible(false);
        confirmDeletePane.setVisible(false);
        scholarshipToNotifyScroll.setVisible(false);
        notificationConfirmation.setVisible(false);
        scholarshipToPublishScroll.setVisible(false);
        formCreationConfirmationPane.setVisible(false);
        scholarshipsToAwardPane.setVisible(false);
        scholarshipAwardDetailsPane.setVisible(false);
        awardApprovalConfirmationPane.setVisible(false);

        unpubScholarships = getUnpubScholarships();
        unpublishedScholarshipsList.getItems().clear();
        unpublishedScholarshipsList.getItems().addAll(unpubScholarships);


        scholarshipFormCreationPane.setVisible(true);

    }

    @FXML
    void displaySelectedUnpubScholarship () {

        String csvFile = "src/Data/SponsorScholarships.csv";
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");
                if (parts[3].equals(unpublishedScholarshipsList.getSelectionModel().getSelectedItem())) {
                    pubTitle.setText(parts[3]);
                    pubAmount.setText(parts[2]);
                    pubDeadline.setText(parts[7]);
                    pubCreator.setText(parts[0] + ", " + parts[1]);
                    pubScholReq.setText(parts[4]);
                    pubRecReq.setText(parts[5]);
                    pubNotes.setText(parts[6]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scholarshipFormCreationPane.setVisible(false);
        scholarshipToPublishScroll.setVisible(true);
    }

    @FXML
    void publishScholarship (ActionEvent event) {
        String pubFile = "src/Data/scholarships.csv";

        try { 
            FileWriter outputFile = new FileWriter(new File(pubFile), true); 

            StringBuilder newLine = new StringBuilder();
            newLine.append(pubTitle.getText());
            newLine.append("#");
            newLine.append(pubAmount.getText());
            newLine.append("#");
            newLine.append(pubDeadline.getText());
            newLine.append("#");
            newLine.append(pubCreator.getText());
            newLine.append("#");
            newLine.append(pubScholReq.getText());
            newLine.append("#");
            newLine.append(pubRecReq.getText());
            newLine.append("#");
            newLine.append(pubNotes.getText());
            newLine.append("\n");

            outputFile.write(newLine.toString());
            outputFile.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        String line;
        String unpubFile = "src/Data/SponsorScholarships.csv";
        String rewrite = new String("");
        String[] lineParts;
        try (BufferedReader reader = new BufferedReader(new FileReader(unpubFile))){
            while ((line = reader.readLine()) != null){
                if (!(lineParts = line.split("#"))[3].equals(pubTitle.getText())){
                    rewrite += line;
                    rewrite += "\n";
                }
            }
        } catch (Exception e){ System.out.println("Error opening schol file"); }
        try (FileWriter fw = new FileWriter(new File(unpubFile))) {
            fw.write(rewrite);
        } catch (Exception e) { System.out.println("Error writing to schol file"); }

        scholarshipToPublishScroll.setVisible(false);
        formCreationConfirmationPane.setVisible(true);
    }

    @FXML
    void openManageAccounts(ActionEvent event) {
        notifyApplicantsPane.setVisible(false);
        notifyApplicantsPane.setVisible(false);
        createAccountsPane.setVisible(false);
        deleteAccountsPane.setVisible(false);
        createAccountFieldsPane.setVisible(false);
        accountCreationConfirmationPane.setVisible(false);
        accountDeletionConfirmationPane.setVisible(false);
        findDeletePane.setVisible(false);
        confirmDeletePane.setVisible(false);
        scholarshipToNotifyScroll.setVisible(false);
        notificationConfirmation.setVisible(false);
        scholarshipFormCreationPane.setVisible(false);
        scholarshipToPublishScroll.setVisible(false);
        formCreationConfirmationPane.setVisible(false);
        scholarshipsToAwardPane.setVisible(false);
        scholarshipAwardDetailsPane.setVisible(false);
        awardApprovalConfirmationPane.setVisible(false);

        findDeleteUser.setText(null);
        confirmDeleteEmail.setText(null);
        confirmDeleteType.setText(null);
        confirmDeleteUser.setText(null);

        accountCreateUser.setText(null);
        accountCreateEmail.setText(null);
        accountCreatePass.setText(null);
        accountCreatePassConfirm.setText(null);

        createOrDeletePane.setVisible(true);
        adminActionSelect.setText("Manage Accounts");
    }

    @FXML
    void openNotifyApplicants(ActionEvent event) {
        scholarships = getScholarships();
        listView.getItems().clear();
        listView.getItems().addAll(scholarships);

        notifyApplicantsPane.setVisible(true);
        createOrDeletePane.setVisible(false);
        createAccountsPane.setVisible(false);
        deleteAccountsPane.setVisible(false);
        createAccountFieldsPane.setVisible(false);
        accountCreationConfirmationPane.setVisible(false);
        accountDeletionConfirmationPane.setVisible(false);
        findDeletePane.setVisible(false);
        confirmDeletePane.setVisible(false);
        scholarshipToNotifyScroll.setVisible(false);
        notificationConfirmation.setVisible(false);
        scholarshipFormCreationPane.setVisible(false);
        scholarshipToPublishScroll.setVisible(false);
        formCreationConfirmationPane.setVisible(false);
        scholarshipsToAwardPane.setVisible(false);
        scholarshipAwardDetailsPane.setVisible(false);
        awardApprovalConfirmationPane.setVisible(false);

        accountCreateUser.setText(null);
        accountCreateEmail.setText(null);
        accountCreatePass.setText(null);
        accountCreatePassConfirm.setText(null);

        findDeleteUser.setText(null);
        confirmDeleteEmail.setText(null);
        confirmDeleteType.setText(null);
        confirmDeleteUser.setText(null);

        scholarships = getScholarships();

        adminActionSelect.setText("Notify Applicants");
    }

    @FXML
    void returnToMain(ActionEvent event) {
        notifyApplicantsPane.setVisible(false);
        createOrDeletePane.setVisible(false);
        createAccountsPane.setVisible(false);
        deleteAccountsPane.setVisible(false);
        createAccountFieldsPane.setVisible(false);
        accountCreationConfirmationPane.setVisible(false);
        accountDeletionConfirmationPane.setVisible(false);
        findDeletePane.setVisible(false);
        confirmDeletePane.setVisible(false);
        scholarshipToNotifyScroll.setVisible(false);
        notificationConfirmation.setVisible(false);
        scholarshipFormCreationPane.setVisible(false);
        scholarshipToPublishScroll.setVisible(false);
        formCreationConfirmationPane.setVisible(false);
        scholarshipsToAwardPane.setVisible(false);
        scholarshipAwardDetailsPane.setVisible(false);
        awardApprovalConfirmationPane.setVisible(false);

        adminActionSelect.setText("Options");


        accountCreateUser.setText(null);
        accountCreateEmail.setText(null);
        accountCreatePass.setText(null);
        accountCreatePassConfirm.setText(null);

        findDeleteUser.setText(null);
        confirmDeleteEmail.setText(null);
        confirmDeleteType.setText(null);
        confirmDeleteUser.setText(null);
    }

    @FXML
    void openCreateAccounts(ActionEvent event) {
        accountTypeSel.setText("Select");
        notifyApplicantsPane.setVisible(false);
        createOrDeletePane.setVisible(false);
        deleteAccountsPane.setVisible(false);
        createAccountFieldsPane.setVisible(false);
        accountCreationConfirmationPane.setVisible(false);

        accountCreateUser.setText(null);
        accountCreateEmail.setText(null);
        accountCreatePass.setText(null);
        accountCreatePassConfirm.setText(null);

        createAccountsPane.setVisible(true);
    }

    @FXML
    void displayAccountFields(ActionEvent event) {
        MenuItem m = (MenuItem)event.getSource();
        String str = m.getId();
        switch(str) {
            case "adminType":
                createAccountButton.setText("Create Administrator Account");
                //createAccountButton.setId("adminCreateAccountButton");
                accountTypeSel.setText("Administrator");
                break;
            case "reviewerType":
                createAccountButton.setText("Create Reviewer Account");
                //createAccountButton.setId("reviewerCreateAccountButton");
                accountTypeSel.setText("Reviewer");
                break;
            case "sponsorType":
                createAccountButton.setText("Create Sponsor/Donor Account");
                //createAccountButton.setId("sponsorCreateAccountButton");
                accountTypeSel.setText("Sponsor/Donor");
                break;
            case "stewardType":
                createAccountButton.setText("Create Steward Account");
                //createAccountButton.setId("stewardCreateAccountButton");
                accountTypeSel.setText("Steward");
                break;
            default:
                System.out.println("ERROR: " + str);
            break;
        }
        notifyApplicantsPane.setVisible(false);
        createOrDeletePane.setVisible(false);
        deleteAccountsPane.setVisible(false);
        accountCreationConfirmationPane.setVisible(false);

        createAccountFieldsPane.setVisible(true);
    }

    @FXML
    void openDeleteAccounts(ActionEvent event) {
        notifyApplicantsPane.setVisible(false);
        createOrDeletePane.setVisible(false);
        createAccountsPane.setVisible(false);
        createAccountFieldsPane.setVisible(false);
        accountCreationConfirmationPane.setVisible(false);
        accountDeletionConfirmationPane.setVisible(false);
        findDeletePane.setVisible(false);
        confirmDeletePane.setVisible(false);


        deleteAccountsPane.setVisible(true);
    }

    @FXML
    void displayFindDelete (ActionEvent event) {
        MenuItem m = (MenuItem)event.getSource();
        String str = m.getId();
        switch(str) {
            case "adminType1":
                findDeleteButton.setText("Find Administrator Account");
                //createAccountButton.setId("adminCreateAccountButton");
                accountTypeSel.setText("Administrator");
                break;
            case "reviewerType1":
                findDeleteButton.setText("Find Reviewer Account");
                //createAccountButton.setId("reviewerCreateAccountButton");
                accountTypeSel.setText("Reviewer");
                break;
            case "sponsorType1":
                findDeleteButton.setText("Find Sponsor/Donor Account");
                //createAccountButton.setId("sponsorCreateAccountButton");
                accountTypeSel.setText("Sponsor/Donor");
                break;
            case "stewardType1":
                findDeleteButton.setText("Find Steward Account");
                //createAccountButton.setId("stewardCreateAccountButton");
                accountTypeSel.setText("Steward");
                break;
            default:
                System.out.println("ERROR: " + str);
            break;
        }
        findDeletePane.setVisible(true);
    }

    @FXML
    void displayDeleteConfirmation (ActionEvent event) {
        confirmDeleteUser.setText(null);
        confirmDeleteEmail.setText(null);
        confirmDeleteType.setText(null);
        String type = (String)((Labeled) event.getSource()).getText();
        String csvFile;
        String Type;
        switch (type) {
            case "Find Administrator Account":
                csvFile = new String("src/Data/adminLogins.csv");
                Type = new String("Administrator");
                break;
            case "Find Reviewer Account":
                csvFile = new String("src/Data/reviewerLogins.csv");
                Type = new String("Reviewer");
                break;
            case "Find Sponsor/Donor Account":
                csvFile = new String("src/Data/donorLogins.csv");
                Type = new String("Sponsor/Donor");
                break;
            case "Find Steward Account":
                csvFile = new String("src/Data/fundStewardLogins.csv");
                Type = new String("Steward");
                break;
            default:
                csvFile = new String("src/Data/dummy.csv");
                System.out.println("ERROR: " + type);
                Type = new String("ERROR TYPE");
            break;
        }
        String line;
        String csvSplitBy = ",";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = reader.readLine()) != null) {
                String[] user = line.split(csvSplitBy);
                if (user[0].equals((findDeleteUser.getText()))) {
                    confirmDeleteUser.setText("Username: " + user[0]);
                    confirmDeleteEmail.setText("Email: " + user[2]);
                    confirmDeleteType.setText(Type);
                    break;
                }
                confirmDeleteType.setText("User not found. Please try again.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        confirmDeletePane.setVisible(true);
    }

    @FXML
    void createAccount(ActionEvent event) {
        String type = (String)((Labeled) event.getSource()).getText();
        String csvFile;
        String Type;
        switch (type) {
            case "Create Administrator Account":
                csvFile = new String("src/Data/adminLogins.csv");
                Type = new String("Administrator");
                break;
            case "Create Reviewer Account":
                csvFile = new String("src/Data/reviewerLogins.csv");
                Type = new String("Reviewer");
                break;
            case "Create Sponsor/Donor Account":
                csvFile = new String("src/Data/donorLogins.csv");
                Type = new String("Sponsor/Donor");
                break;
            case "Create Steward Account":
                csvFile = new String("src/Data/fundStewardLogins.csv");
                Type = new String("Steward");
                break;
            default:
                csvFile = new String("src/Data/dummy.csv");
                System.out.println("ERROR: " + type);
                Type = new String("ERROR TYPE");
            break;
        }
        String line;
        String csvSplitBy = ",";
        Boolean uniqueUser = true;
        Boolean passwordsMatch = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = reader.readLine()) != null) {
                String[] user = line.split(csvSplitBy);
                if (user[0].equals((accountCreateUser.getText()))) {
                    uniqueUser = false;
                }
            }
            if (!accountCreatePass.getText().equals(accountCreatePassConfirm.getText())) {
                passwordsMatch = false;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!uniqueUser) {
            passMatchError.setVisible(false);
            uniqueUserError.setVisible(true);
        }
        else if (!passwordsMatch) {
            uniqueUserError.setVisible(false);
            passMatchError.setVisible(true);
        }
        else {
            try { 
            // create FileWriter object with file as parameter 
            FileWriter outputFile = new FileWriter(new File(csvFile), true); 

            StringBuilder newLine = new StringBuilder();
            newLine.append(accountCreateUser.getText());
            newLine.append(",");
            newLine.append(accountCreatePass.getText());
            newLine.append(",");
            newLine.append(accountCreateEmail.getText());
            newLine.append("\n");

            outputFile.write(newLine.toString());
            outputFile.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            passMatchError.setVisible(false);
            uniqueUserError.setVisible(false);

            confirmType.setText(Type);
            confirmUser.setText("Username: " + accountCreateUser.getText());
            confirmEmail.setText("Email: " + accountCreateEmail.getText());

            accountCreateUser.setText(null);
            accountCreateEmail.setText(null);
            accountCreatePass.setText(null);
            accountCreatePassConfirm.setText(null);

            notifyApplicantsPane.setVisible(false);
            createOrDeletePane.setVisible(false);
            createAccountsPane.setVisible(false);
            deleteAccountsPane.setVisible(false);
            createAccountFieldsPane.setVisible(false);

            accountCreationConfirmationPane.setVisible(true);

        }
    }

    @FXML
    void deleteAccount (ActionEvent event) {
        String type = confirmDeleteType.getText();
        String csvFile;
        switch (type) {
            case "Administrator":
                csvFile = new String("src/Data/adminLogins.csv");
                break;
            case "Reviewer":
                csvFile = new String("src/Data/reviewerLogins.csv");
                break;
            case "Sponsor/Donor":
                csvFile = new String("src/Data/donorLogins.csv");
                break;
            case "Steward":
                csvFile = new String("src/Data/fundStewardLogins.csv");
                break;
            default:
                csvFile = new String("src/Data/dummy.csv");
                System.out.println("ERROR: " + type);
            break;
        }
        String line;
        String rewrite = new String("");
        String[] lineParts;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
            while ((line = reader.readLine()) != null){
                if (!(lineParts = line.split(","))[0].equals(findDeleteUser.getText())){
                    rewrite += line;
                    rewrite += "\n";
                }
            }
        } catch (Exception e){ System.out.println("Error opening login file"); }
        try (FileWriter fw = new FileWriter(new File(csvFile))) {
            fw.write(rewrite);
        } catch (Exception e) { System.out.println("Error writing to logins file"); }

        findDeleteUser.setText(null);
        

        deleteAccountsPane.setVisible(false);
        findDeletePane.setVisible(false);
        confirmDeletePane.setVisible(false);

        confirmType1.setText(confirmDeleteType.getText());
        confirmUser1.setText(confirmDeleteUser.getText());
        confirmEmail1.setText(confirmDeleteEmail.getText());

        confirmDeleteEmail.setText(null);
        confirmDeleteType.setText(null);
        confirmDeleteUser.setText(null);

        accountDeletionConfirmationPane.setVisible(true);
    }

    @FXML
    void displaySelectedScholarship () {
        notifyApplicantsPane.setVisible(false);

        String title = listView.getSelectionModel().getSelectedItem();
        String csvFile = new String("src/Data/scholarships.csv");
        String line;
        String csvSplitBy = new String("#");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = reader.readLine()) != null) {
                String[] scholarship = line.split(csvSplitBy);
                if (scholarship[0].equals(title)) {
                    titleInfo.setText(scholarship[0]);
                    amountInfo.setText(scholarship[1]);
                    deadlineInfo.setText(scholarship[2]);
                    creatorInfo.setText(scholarship[3]);
                    scholarshipRequirementsInfo.setText(scholarship[4]);
                    recipientRequirementsInfo.setText(scholarship[5]);
                    notesInfo.setText(scholarship[6]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        scholarshipToNotifyScroll.setVisible(true);
    }

    @FXML
    void reportEngineNotifyApplicants (ActionEvent event) {
        // Report engine function call with scholarship title as input
        LocalDate date = LocalDate.now();
        String time = new String("" + LocalTime.now());

        System.out.println("REPORT ENGINE DATA RECIEVED: ");
        System.out.println("\tScholarship Title: " + listView.getSelectionModel().getSelectedItem());

        notifSuccess.setText("Notification Sent Successfully on " + date + " at " + time.substring(0,5));

        scholarshipToNotifyScroll.setVisible(false);
        notificationConfirmation.setVisible(true);
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
    void openScholarshipsToBeAwarded (ActionEvent event) {
        readyToAwardScholarships = getReadyToAward();
        scholarshipsToAwardList.getItems().clear();
        scholarshipsToAwardList.getItems().addAll(readyToAwardScholarships);

        notifyApplicantsPane.setVisible(false);
        createOrDeletePane.setVisible(false);
        createAccountsPane.setVisible(false);
        deleteAccountsPane.setVisible(false);
        createAccountFieldsPane.setVisible(false);
        accountCreationConfirmationPane.setVisible(false);
        accountDeletionConfirmationPane.setVisible(false);
        findDeletePane.setVisible(false);
        confirmDeletePane.setVisible(false);
        scholarshipToNotifyScroll.setVisible(false);
        notificationConfirmation.setVisible(false);
        scholarshipFormCreationPane.setVisible(false);
        scholarshipToPublishScroll.setVisible(false);
        formCreationConfirmationPane.setVisible(false);
        scholarshipAwardDetailsPane.setVisible(false);
        awardApprovalConfirmationPane.setVisible(false);

        scholarshipsToAwardPane.setVisible(true);

        adminActionSelect.setText("Approve Award Recipients");
    }

    @FXML
    void displayRecipientDetails() {
        String title = scholarshipsToAwardList.getSelectionModel().getSelectedItem();
        String csvFile = new String("src/Data/readyToBeAwarded.csv");
        String line;
        String csvSplitBy = new String("#");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = reader.readLine()) != null) {
                String[] scholarship = line.split(csvSplitBy);
                if (scholarship[0].equals(title)) {
                    awardTitle.setText(scholarship[0]);
                    awardAmount.setText(scholarship[1]);
                    awardDetails.setText(scholarship[4] + " " + scholarship[5]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String appFile = new String("src/Data/" + title + "_Applicants.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(appFile))) {
            String prevLine = "";
            while ((line = reader.readLine()) != null) {
                prevLine = line;
            }
            String[] student = prevLine.split(csvSplitBy);
            studentID.setText(student[2]);
            studentScore.setText(student[16]);
            studentComments.setText(student[17]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        scholarshipsToAwardPane.setVisible(false);
        scholarshipAwardDetailsPane.setVisible(true);
    }

    @FXML
    void displayAwardConfirmation(ActionEvent event) {
        String archiveFile = "src/Data/archivedScholarships.csv";
        String reviewFile = "src/Data/readyToBeAwarded.csv";
        String title = awardTitle.getText();
        String line;
        String[] lineParts;

        try { 
            FileWriter outputFile = new FileWriter(new File(archiveFile), true); 

            try (BufferedReader reader = new BufferedReader(new FileReader(reviewFile))){
            while ((line = reader.readLine()) != null){
                if ((lineParts = line.split("#"))[0].equals(title)) {
                    StringBuilder newLine = new StringBuilder();
                    for (int i = 0; i < 6; i++) {
                        newLine.append(lineParts[i]);
                        newLine.append("#");
                    }
                    newLine.append(lineParts[6]);
                    newLine.append("\n");

                    outputFile.write(newLine.toString());
                    outputFile.close();
                }
                }
            } catch (Exception e){ System.out.println("Error opening schol file"); }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        String rewrite = new String("");
        try (BufferedReader reader = new BufferedReader(new FileReader(reviewFile))){
            while ((line = reader.readLine()) != null){
                if (!(lineParts = line.split("#"))[0].equals(title)){
                    rewrite += line;
                    rewrite += "\n";
                }
            }
        } catch (Exception e){ System.out.println("Error opening schol file"); }
        try (FileWriter fw = new FileWriter(new File(reviewFile))) {
            fw.write(rewrite);
        } catch (Exception e) { System.out.println("Error writing to schol file"); }

        String studentFile = "src/Data/studentInfo.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))){
            while ((line = reader.readLine()) != null){
                if ((lineParts = line.split("/"))[3].equals(studentID.getText())){
                    System.out.println("Email sent to " + lineParts[2]);
                    break;
                }
            }
        } catch (Exception e){ System.out.println("Error opening schol file"); }

        scholarshipAwardDetailsPane.setVisible(false);
        awardApprovalConfirmationPane.setVisible(true);
    }


}
