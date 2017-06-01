package ch.arc.narvaldevor.fxcontroller;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import ch.arc.narvaldevor.client.ClientSide;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConnectController 
{
	@FXML
	JFXTextField username;
	@FXML
	JFXTextField ipadress;
	@FXML
	JFXTextField port;
	@FXML
	JFXButton connectBtn;
	@FXML
	Label errorLabel;
	
	@FXML
	void connectToChat(ActionEvent event) throws IOException
	{
		System.out.println("click");
		
		try
		{
			ClientSide client = new ClientSide(this.username.getText(),this.ipadress.getText(), Integer.parseInt(this.port.getText()));
			if(client.isConnect())
			{
				System.out.println("OK");
				//Parent spyke = FXMLLoader.load(getClass().getResource("../view/spyke.fxml"));
				
				//Scene scene = new Scene(spyke);
				//Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				//stage.hide();
				//stage.setScene(scene);
				//stage.show();
			}
			else
			{
				this.errorLabel.setVisible(true);
			}
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
	}

}
