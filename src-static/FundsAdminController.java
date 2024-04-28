import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FundsAdminController {
    private int funds = 0;
    Map <String, Integer> scholarships = new HashMap<String, Integer>();
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
    void depositFunds(ActionEvent event) {
        //Stage mainWindow = (Stage)ScholarshipNameDeposit.getScene().getWindow();
        String name = ScholarshipNameDeposit.getText();
        int amount = Integer.parseInt(AmountDeposit.getText());
        
        Integer prevAmount = scholarships.get(name);
        if (prevAmount == null){ prevAmount = 0;}
        scholarships.put(name, amount + prevAmount);
        this.funds += amount;
        fundsLabel.setText("$" + Integer.toString(funds));
    }

    @FXML
    void withdrawFunds(ActionEvent event) {
        //Stage mainWindow = (Stage)ScholarshipNameWithdraw.getScene().getWindow();
        String name = ScholarshipNameWithdraw.getText();
        Integer amount = scholarships.get(name);
        if (amount != null){
            this.funds -= (int)amount;
            fundsLabel.setText("$" + Integer.toString(funds));
            scholarships.remove(name);
        }
        else{
            errorMsg.setVisible(true);
        }
    }
}

