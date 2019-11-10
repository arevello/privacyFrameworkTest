/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privacyframeworktest;

import MessageBlocks.Client.ClientCodes;
import MessageBlocks.Client.Messages.LoginMessage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    
    @FXML
    VBox vboxRecords;
    
    ArrayList<Pane> panes = new ArrayList<Pane>();
    
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnStartSession;
    @FXML
    private Button btnEditAccount;
    @FXML
    private Button btnConfirmRecords;
    
    @FXML
    private void btnLoginClicked(ActionEvent event) {
        onlyVisiblePane(paneLogin);
    }
    
    @FXML
    private void onStartSessionClicked(ActionEvent e){
        //onlyVisiblePane(null);
        try{
            System.out.println("HERE writing " + ClientCodes.login);
            LoginMessage lm = new LoginMessage(txtUsername.getText(), txtPassword.getText().hashCode());
            dout.writeInt(ClientCodes.login);
            dout.write(lm.toByteBuffer());
            
            if(din.readInt() == ClientCodes.acceptMsg)
                System.out.println("accept");
            else 
                System.out.println("reject");
            onlyVisiblePane(paneAccountOptions);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
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
        
        paneInit.setVisible(true);
        //onlyVisiblePane(paneSessionStart);
    }    
    
    private void onlyVisiblePane(Pane visPane){
        for(Pane p : panes){
            if(p.equals(visPane)){
                if(p.equals(paneRecords))
                    btnConfirmRecords.setVisible(true);
                p.setVisible(true);
            }
            else{
                if(p.equals(paneRecords))
                    btnConfirmRecords.setVisible(false);
                p.setVisible(false);
            }
        }
    }
    
    private VBox recordToVBox(RecordStructure rs, boolean getValue){
        VBox ret = new VBox();
        ret.setAlignment(Pos.CENTER);
        for(int i = 0; i < rs.getEmptyList().size(); i++){
            HBox h = new HBox();
            Label l = new Label(rs.getEmptyList().get(i).key);
            if(rs.getEmptyList().get(i).value.getGenericClass().equals(Boolean.class)){
                CheckBox cb = new CheckBox();
                if(getValue){
                    Boolean val;
                    val = new Boolean(rs.getEmptyList().get(i).value.getData().toString());
                    cb.setSelected(val);
                }
                h.getChildren().addAll(l,cb);
            }
            else{
                TextField t = new TextField();
                if(getValue)
                    t.setText(rs.getEmptyList().get(i).value.getData().toString());
                h.getChildren().addAll(l,t);
            }
            
            h.setAlignment(Pos.CENTER);
            ret.getChildren().add(h);
        }
        
        return ret;
    } 
    
    @FXML
    private void btnCreateAccountClicked(ActionEvent event){
        onlyVisiblePane(paneRecords);
        
        paneRecords.getChildren().add(0,this.recordToVBox(new UserInfo(), false));
    }
    
    @FXML
    private void btnEditAccountClicked(ActionEvent e){
        try {
            dout.writeInt(ClientCodes.editAccountInformation);
            int size = din.readInt();
            System.out.println("reading bytes " + size);
            byte[] b = new byte[size];
            din.read(b, 0, size);
            String temp = new String(b);
            System.out.println("read msg " + temp);
            UserInfo ui = new UserInfo(temp);
        
            onlyVisiblePane(paneRecords);
        
            vboxRecords.getChildren().add(0,this.recordToVBox(ui, true));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnConfirmRecordsClicked(ActionEvent e){
        
    }
}
