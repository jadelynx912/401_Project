
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;

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

public class FundsStewardController {
    private int funds = 0;
    HashMap <String, Integer> scholarships = new HashMap<String, Integer>();
    @FXML
    private TextField AmountDeposit;
    @FXML
    private Button DepositButton;
    @FXML
    private TextField ScholarshipNameDeposit;
    @FXML
    private TextField ScholarshipNameWithdraw;
    @FXML
    private Button WithdrawButton;
    @FXML
    private Label fundsLabel;
    @FXML
    private Label errorMsg;

    @FXML
    private void initialize(){
        String line;
        String amount;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/fundStewardFunds.csv"))) {
            line = reader.readLine();
            amount = line.split(",")[1];
            fundsLabel.setText("$" + amount);
        } catch (IOException e) {
            System.out.println("Error reading from file");
            fundsLabel.setText("$-1");
        }
    }

    @FXML
    void DepositFunds(ActionEvent event) {
        String name = ScholarshipNameDeposit.getText();
        int amount = Integer.parseInt(AmountDeposit.getText());
        //Stage mainWindow = (Stage)ScholarshipNameDeposit.getScene().getWindow();
        String line;
        String rewrite = "";
        Boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/fundStewardFunds.csv"))) {
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                if ("Total".equals(words[0])){
                    rewrite += "Total," + (amount + Integer.parseInt(words[1])) + "\n";
                    fundsLabel.setText("$" + Integer.toString(amount + Integer.parseInt(words[1])));
                }
                else if (name.equals(words[0])){
                    found = true;
                    rewrite += name + "," + (amount + Integer.parseInt(words[1])) + "\n";
                }
                else{
                    rewrite += line + "\n";
                }
            }
            if (!found){
                rewrite += name + "," + amount + "\n";
            }
        }
        catch(IOException e){
            System.out.println("Error reading file");
        }

        try (FileWriter fw = new FileWriter(new File("src/Data/fundStewardFunds.csv"))){
            fw.write(rewrite);
        }
        catch(IOException e){
            System.out.println("Error writing to file");
        }
        
        // Integer prevAmount = scholarships.get(name);
        // if (prevAmount == null){ prevAmount = 0;}
        // scholarships.put(name, amount + prevAmount);
        // this.funds += amount;
    }

    @FXML
    void WithdrawFunds(ActionEvent event) {
        //Stage mainWindow = (Stage)ScholarshipNameWithdraw.getScene().getWindow();
        String line;
        String name = ScholarshipNameWithdraw.getText();
        int total = 0;
        Boolean found = false;
        String rewrite = "";
        String amount = "0";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/fundStewardFunds.csv"))) {
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                if ("Total".equals(words[0])){
                    total = Integer.parseInt(words[1]);
                }
                else if (name.equals(words[0]) && (words.length != 3)){
                    found = true;
                    amount = words[1];
                    rewrite += name + "," + words[1] + ",withdrawn\n";
                }
                else{
                    rewrite += line + "\n";
                }
            }
        } catch (IOException e){
            System.out.println("Can't read from file");
        }

        if (found){
            rewrite = "Total," + (total - Integer.parseInt(amount)) + "\n" + rewrite;
            fundsLabel.setText("$" + Integer.toString(total - Integer.parseInt(amount)));
            try (FileWriter fw = new FileWriter(new File("src/Data/fundStewardFunds.csv"))){
                fw.write(rewrite);
            }
            catch(IOException e){
                System.out.println("Error writing to file");
            }
        }
        else{
            errorMsg.setVisible(true);
        }
        // Integer amount = scholarships.get(name);
        // if (amount != null){
        //     this.funds -= (int)amount;
        //     fundsLabel.setText("$" + Integer.toString(funds));
        //     scholarships.remove(name);
        //     errorMsg.setVisible(false);
        // }
        // else{
        //     errorMsg.setVisible(true);
        // }
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

