/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privacyframeworktest;

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
    Pane paneSessionStart;
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
