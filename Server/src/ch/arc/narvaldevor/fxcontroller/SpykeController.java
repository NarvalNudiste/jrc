package ch.arc.narvaldevor.fxcontroller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import ch.arc.narvaldevor.listener.ClientListener;
import ch.arc.narvaldevor.spykelauncher.Launcher;
import ch.arc.narvaldevor.utils.Message;
import ch.arc.narvaldevor.utils.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SpykeController implements Initializable
{
	@FXML
	JFXButton sendmessageBtn;
	@FXML
	TextArea writemessage;
	@FXML
	TextArea displaymessage;
	@FXML
	Label status;
	@FXML
	Label pseudo;
	@FXML
	Label state;
	@FXML
	Label connect;
	@FXML
	ListView<User> connectList;
	ObservableList<User> observerListUser; 
	@FXML
	AnchorPane anchor;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Envoie du message en appuyant sur ENTER
        writemessage.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                	sendMessage(new ActionEvent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ke.consume();
            }
        });		
	}
	
	@FXML
	public void sendMessage(ActionEvent event) throws IOException
	{
		if(writemessage.getText().equals(""))
		{
			System.out.println("Chaine vide");
		}
		else
		{
			//Appel de la methode d'envoie de message 
			//ClientListener.sendMessage(this.pseudo.getText()+ ": " +writemessage.getText()+"\n");
			//Appel de la méthode send de ClientListener qui passera le style de message à (message) donc un simple message
			ClientListener.sendMessage(writemessage.getText()+"\n");
			writemessage.setText("");
			//Son lorsque le message est envoyé et affiché
			AudioClip audio = new AudioClip(this.getClass().getResource("../media/son_pour_message.wav").toString());
			audio.play();
		}
		
	}
	
	//Le satus passe à connecter une fois que la connexion est établie
	public void setState(String status) {
		this.state.setTextFill(Color.web("#2ecc71"));
		this.state.setText(status);
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo.setText(pseudo);
	}
	
	//Affichage du message dans la texteArea. 
	public void sendMessage(Message message)
	{
		Platform.runLater(() -> {
			displaymessage.appendText(writeTime() + " " + message.getPseudo().toUpperCase()+": "+message.getMessage());
        });
	}
	public void serverMessage(Message message)
	{
		Platform.runLater(() -> {
			displaymessage.appendText(message.getMessage().toLowerCase());
        });
	}
	/**
	 * Ututilisateurs connectés
	 */
	
	public void updateList(Message message)
	{
		Platform.runLater(()-> {
			//Un observer de la listView
			ObservableList<User> usersObList = FXCollections.observableArrayList (message.getUserList());
			connectList.setItems(usersObList);
			connectList.setCellFactory(new Callback<ListView<User>,ListCell<User>>()
			{
				@Override
				public ListCell<User> call(ListView<User> param) 
				{
					return new Cellule();
				}
			});
		});
	}
	/**
	 * Afficher l'heure courante
	 * @return
	 */
	public String writeTime()
	{
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat simpleDf = new SimpleDateFormat("HH:mm");
	    return simpleDf.format(cal.getTime());
	}
	
	//Construction des cellules constituant la liste view des utilisateurs connecté au serveur.
	//Une cellule contient une ImageView et le pseudo de l'utilisateur
	class Cellule extends ListCell<User> 
	{ 
	    @Override 
	    protected void updateItem(User user, boolean empty) { 
	        super.updateItem(user, empty); 
	        String path = "ch/arc/narvaldevor/view/profile.png";
	        setGraphic(null); 
	        setText(null); 
	        if (!empty && user != null) { 
	        	HBox horizontalBox = new HBox();
	        	ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResource(path).toString(),60,60,true,true));
	        	Text pseudo = new Text(user.getPseudo());
	        	pseudo.setFill(Color.web("#3498db"));
	        	pseudo.setFont(Font.font(null, FontWeight.BOLD, 16));
	        	horizontalBox.getChildren().addAll(imageView,pseudo);
	        	horizontalBox.setAlignment(Pos.CENTER_LEFT);
	            setGraphic(horizontalBox); 
	        } 
	    } 
	}
	
	

}
