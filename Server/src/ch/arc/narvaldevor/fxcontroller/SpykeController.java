package ch.arc.narvaldevor.fxcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SpykeController 
{
	@FXML
	JFXButton sendmessageBtn;
	@FXML
	JFXTextField writemessage;
	@FXML
	TextArea displaymessage;
	
	@FXML
	public void sendMessage(ActionEvent event)
	{
		System.out.println(writemessage.getText());
		writemessage.setText("");
		displaymessage.appendText(writemessage.getText());
		
	}
}
