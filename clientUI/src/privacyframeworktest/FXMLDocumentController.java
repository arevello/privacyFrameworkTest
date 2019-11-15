/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privacyframeworktest;

import MessageBlocks.Client.ClientCodes;
import MessageBlocks.Client.Messages.LoginMessage;
import MessageBlocks.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import recordStructures.PairGeneric;
import recordStructures.RecordStructure;
import recordStructures.client.UserInfo;

/**
 *
 * @author arevello
 */
public class FXMLDocumentController implements Initializable {
    Socket s;
    DataOutputStream dout;
    DataInputStream din;
    
    @FXML
    Pane paneLogin;
    @FXML
    Pane paneInit;
    @FXML
    Pane paneRecords;
    @FXML
    Pane paneAccountOptions;
    @FXML
    Pane paneCreateAccount;
    
    @FXML
    ChoiceBox cbAccountType;
    
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    TextField txtCreateAccountPassword;
    @FXML
    TextField txtCreateAccountUsername;
    
    @FXML
    VBox vboxRecords;
    
    @FXML
    Label lblLoginHint;
    @FXML
    Label lblConfirmAccountCreateHint;
    
    ArrayList<Pane> panes = new ArrayList<Pane>();
    
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnStartSession;
    @FXML
    private Button btnEditAccount;
    @FXML
    private Button btnConfirmRecords;
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            s = new Socket("localhost",420);
            System.out.println("accepted");
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            dout.writeInt(1);
            System.out.println("wrote 1");
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        // TODO
        panes.add(paneRecords);
        panes.add(paneInit);
        panes.add(paneLogin);
        panes.add(paneAccountOptions);
        panes.add(paneCreateAccount);
        
        cbAccountType.getItems().add("User");
        cbAccountType.getItems().add("Processor");
        cbAccountType.getItems().add("ThirdParty");
        cbAccountType.setValue("User");
        
        paneInit.setVisible(true);
        //onlyVisiblePane(paneSessionStart);
    } 
    
    //display the pane to allow the user to login
    @FXML
    private void btnLoginClicked(ActionEvent event) {
        onlyVisiblePane(paneLogin);
    }
    
    //attempt to login with credentials entered in pane
    @FXML
    private void onStartSessionClicked(ActionEvent e){
        //onlyVisiblePane(null);
        try{
            System.out.println("HERE writing " + ClientCodes.login);
            LoginMessage lm = new LoginMessage(txtUsername.getText(), txtPassword.getText().hashCode(), -1);
            dout.writeInt(ClientCodes.login);
            dout.write(lm.toByteBuffer());
            
            if(din.readInt() == ClientCodes.acceptMsg)
                onlyVisiblePane(paneAccountOptions);
            else{ 
                lblLoginHint.setText("Invalid Login");
                txtPassword.setText("");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }   
    
    //make all panes not selected not visible. Only 1 pane will be visible at a time
    //also contains specific situations for nondynamic objects that need to be displayed or hidden
    private void onlyVisiblePane(Pane visPane){
        for(Pane p : panes){
            if(p.equals(visPane)){
                p.setVisible(true);
            }
            else{
                p.setVisible(false);
            }
        }
    }
    
    //converts one of the records into a displayable set of pairs involving a descriptive label
    //followed by the corresponding control
    private VBox recordToVBox(RecordStructure rs, boolean getValue){
        VBox ret = new VBox();
        ret.setAlignment(Pos.CENTER);
        
        for(Map.Entry<String, PairGeneric> e : rs.getEmptyList().entrySet()){
            HBox h = new HBox();
            Label l = new Label(e.getKey());
            if(e.getValue().getGenericClass().equals(Boolean.class)){
                CheckBox cb = new CheckBox();
                if(getValue){
                    Boolean val;
                    val = new Boolean(e.getValue().getData().toString());
                    cb.setSelected(val);
                }
                h.getChildren().addAll(l,cb);
            }
            else{
                TextField t = new TextField();
                if(getValue)
                    t.setText(e.getValue().getData().toString());
                h.getChildren().addAll(l,t);
            }
            
            h.setAlignment(Pos.CENTER);
            ret.getChildren().add(h);
        }
        
        return ret;
    } 
    
    public UserInfo vboxToUserInfo(VBox v, boolean getValue){
        UserInfo ui = new UserInfo();
        //ui.getEmptyList().
        
        return ui;
    }
    
    //displays the pane that allows the creation of a new account
    @FXML
    private void btnCreateAccountClicked(ActionEvent event){
        onlyVisiblePane(paneCreateAccount);
    }
    
    //display the pane that allows the user to see and edit their information
    @FXML
    private void btnEditAccountClicked(ActionEvent e){
        try {
            dout.writeInt(ClientCodes.editAccountInformation);
            UserInfo ui = new UserInfo();
            byte[] b = new byte[Message.messageSize];
            din.read(b, 0, Message.messageSize);
            ui.fromByteBuffer(b);
        
            onlyVisiblePane(paneRecords);
        
            //build box with fields filled in with users data
            vboxRecords.getChildren().add(0,this.recordToVBox(ui, true));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnConfirmRecordsClicked(ActionEvent e){
        ObservableList<Node> nodes = vboxRecords.getChildren();
        UserInfo ui = new UserInfo();
    }
    
    @FXML
    private void btnConfirmAccountCreateClicked(ActionEvent e){
        String temp = (String) this.cbAccountType.getValue();
        int acctType;
        if(temp.equals("User"))
            acctType = 1;
        else if(temp.equals("Processor"))
            acctType = 2;
        else
            acctType = 3;
        LoginMessage lm = new LoginMessage(this.txtCreateAccountUsername.getText(),
                this.txtCreateAccountPassword.getText().hashCode(), acctType);
        
        try {
            dout.writeInt(ClientCodes.createNewAccount);
            dout.write(lm.toByteBuffer(), 0, Message.messageSize);
            int ret = din.readInt();
            if(ret == ClientCodes.rejectMsg){
                lblConfirmAccountCreateHint.setText("Username already exists");
            }
            else{
                this.onlyVisiblePane(this.paneAccountOptions);
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
