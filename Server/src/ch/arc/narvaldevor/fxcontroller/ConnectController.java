package ch.arc.narvaldevor.fxcontroller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import ch.arc.narvaldevor.client.ClientSide;
import ch.arc.narvaldevor.listener.ClientListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ConnectController implements Initializable
{
	@FXML
	JFXTextField pseudo;
	@FXML
	JFXTextField ipadress;
	@FXML
	JFXTextField port;
	@FXML
	JFXButton connectBtn;
	@FXML
	Label errorLabel;
	//Données de connexion
	String ipAdress;
	String username;
	int portnumber;
	private Scene scene;
	ClientListener clientListener;
	
	private static ConnectController connectControlerInstance;

    public ConnectController() {
    	connectControlerInstance = this;
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Event pour masquer errorLal
		this.pseudo.setOnMouseClicked(e-> this.errorLabel.setVisible(false));
		this.port.setOnMouseClicked(e-> this.errorLabel.setVisible(false));
		this.ipadress.setOnMouseClicked(e-> this.errorLabel.setVisible(false));
	}
		
	@FXML
	void connectToChat(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/spyke.fxml"));
		Parent spyke = loader.load();
		
		SpykeController spykeController = (SpykeController)loader.getController();
		//Lancer le listener
		if(!this.ipadress.getText().isEmpty() && !this.port.getText().isEmpty() && !this.pseudo.getText().isEmpty())
		{
			clientListener = new ClientListener(this.ipadress.getText(), Integer.parseInt(this.port.getText()), this.pseudo.getText(), spykeController);
			
			spykeController.setState("Connecté");
			spykeController.setPseudo(this.pseudo.getText());
			Thread thread = new Thread(clientListener);
			thread.start();
			this.scene = new Scene(spyke);
		}
		else if(this.ipadress.getText().isEmpty() || this.port.getText().isEmpty() || this.pseudo.getText().isEmpty())
		{
			this.errorLabel.setText("Renseignez tous les champs...");
			this.errorLabel.setVisible(true);
		}
		else
		{
			this.errorLabel.setText("Des entrées sont incorrectes...");
			this.errorLabel.setVisible(true);
		}		
	}
	
	public void changeScene() throws IOException {
        Platform.runLater(() -> {
            Stage stage = (Stage) pseudo.getScene().getWindow();
            stage.setResizable(false);
            stage.setOnCloseRequest((WindowEvent e) -> {
                Platform.exit();
                System.exit(0);
            });
            stage.setScene(this.scene);
        });
    }
	
	//Retourne l'instance de la classe
	public static ConnectController getInstance() 
	{
        return connectControlerInstance;
	}

	/*public Label getErroLabel() {
		return this.errorLabel;
	}*/

}
