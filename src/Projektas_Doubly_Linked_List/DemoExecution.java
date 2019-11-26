/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projektas_Doubly_Linked_List;

import java.util.Locale;

import edu.ktu.ds.lab2.gui.MainWindow;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * @Author Antanas Bendoraitis
 */
public class DemoExecution extends Application {

    public static void main(String[] args) {
        DemoExecution.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus 
       // ManualTest.executeTest();
        setUserAgentStylesheet(STYLESHEET_MODENA);    //Nauja FX išvaizda
       // setUserAgentStylesheet(STYLESHEET_CASPIAN); //Sena FX išvaizda
        MainWindow.createAndShowGui(primaryStage);
    }
}
