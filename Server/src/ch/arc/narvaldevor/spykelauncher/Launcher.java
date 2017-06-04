package ch.arc.narvaldevor.spykelauncher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends Application
{

	public static void main(String args[])
	{
        //ServerSide server = new ServerSide();
        launch(args);
    }
    
    @Override
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("../view/connect.fxml"));
		pStage = primaryStage;
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
    
    public static Stage getPrimaryStage() {
        return pStage;
    }
    private static Stage pStage;
}
