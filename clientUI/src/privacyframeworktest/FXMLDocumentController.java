/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privacyframeworktest;

import java.io.DataOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
    
    @FXML
    Pane paneLogin;
    @FXML
    Pane paneInit;
    @FXML
    Pane paneRecords;
    
    @FXML
    VBox vboxRecords;
    
    ArrayList<Pane> panes = new ArrayList<Pane>();
    
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnStartSession;
    
    @FXML
    private void btnLoginClicked(ActionEvent event) {
        onlyVisiblePane(paneLogin);
    }
    
    @FXML
    private void btnCreateAccountClicked(ActionEvent event){
        onlyVisiblePane(paneRecords);
        
        paneRecords.getChildren().add(this.recordToVBox(new UserInfo(), false));
    }
    
    @FXML
    private void onStartSessionClicked(ActionEvent e){
        onlyVisiblePane(null);
        try{
            Socket s=new Socket("localhost",420);
            System.out.println("accepted");
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
            dout.writeInt(1);
            System.out.println("wrote 1");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        onlyVisiblePane(paneInit);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        panes.add(paneRecords);
        panes.add(paneInit);
        panes.add(paneLogin);
        
        paneInit.setVisible(true);
        //onlyVisiblePane(paneSessionStart);
    }    
    
    private void onlyVisiblePane(Pane visPane){
        for(Pane p : panes){
            if(p.equals(visPane))
                p.setVisible(true);
            else
                p.setVisible(false);
        }
    }
    
    private VBox recordToVBox(RecordStructure rs, boolean getValue){
        VBox ret = new VBox();
        ret.setAlignment(Pos.CENTER);
        for(int i = 0; i < rs.getEmptyList().size(); i++){
            Label l = new Label(rs.getEmptyList().get(i).key);
            TextField t = new TextField();
            if(getValue)
                t.setText(rs.getEmptyList().get(i).toString());
            HBox h = new HBox(l,t);
            h.setAlignment(Pos.CENTER);
            ret.getChildren().add(h);
        }
        
        return ret;
    } 
}
