package ch.arc.narvaldevor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by NarvalNudiste on 18/05/2017.
 */
public class Launcher extends Application{
    public static void main(String args[]){
        //Server server = new Server();
        launch(args);
    }
    
    @Override
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("view/connect.fxml"));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
