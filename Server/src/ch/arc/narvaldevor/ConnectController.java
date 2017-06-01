package ch.arc.narvaldevor;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnectController 
{
	@FXML
	JFXTextField username;
	@FXML
	JFXTextField ipadress;
	@FXML
	JFXButton connectBtn;
	
	@FXML
	void connectToChat(ActionEvent event) throws IOException
	{
		System.out.println("click");
		
		Parent spyke = FXMLLoader.load(getClass().getResource("view/spyke.fxml"));
		
		Scene scene = new Scene(spyke);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
		
	}

}
