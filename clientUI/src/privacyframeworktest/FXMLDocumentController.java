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
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author arevello
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    Pane paneUserOptions;
    @FXML
    Pane paneSessionStart;
    @FXML
    Pane paneRecords;
    
    ArrayList<Pane> panes = new ArrayList<Pane>();
    
    @FXML
    private Button btnEdit;
    private Button btnStartSession;
    
    @FXML
    private void onEditButtonClicked(ActionEvent event) {
        
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
        onlyVisiblePane(paneUserOptions);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        panes.add(paneRecords);
        panes.add(paneUserOptions);
        panes.add(paneSessionStart);
        
        paneSessionStart.setVisible(true);
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
    
}
