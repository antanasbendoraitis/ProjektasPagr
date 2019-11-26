package edu.ktu.ds.lab2.gui;

import Projektas_Doubly_Linked_List.DoublyLinkedList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;

/**
 * Lab2 langas su JavaFX
 * <pre>
 *                     MainWindow (BorderPane)
 *  |-------------------------Top-------------------------|
 *  |                   MainWindowMenu                    |
 *  |------------------------Center-----------------------|
 *  |  |-----------------------------------------------|  |
 *  |  |                                               |  |
 *  |  |                                               |  |
 *  |  |                    taOutput                   |  |
 *  |  |                                               |  |
 *  |  |                                               |  |
 *  |  |                                               |  |
 *  |  |                                               |  |
 *  |  |                                               |  |
 *  |  |-----------------------------------------------|  |                                           |
 *  |------------------------Bottom-----------------------|
 *  |  |~~~~~~~~~~~~~~~~~~~paneBottom~~~~~~~~~~~~~~~~~~|  |
 *  |  |                                               |  |
 *  |  |  |-------------||------------||------------|  |  |
 *  |  |  | paneButtons || paneParam1 || paneParam2 |  |  |
 *  |  |  |             ||            ||            |  |  |
 *  |  |  |-------------||------------||------------|  |  |
 *  |  |                                               |  |
 *  |  |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|  |
 *  |-----------------------------------------------------|
 * </pre>
 *
 * @author antanas.bendoraitis@ktu.lt
 */
public class MainWindow extends BorderPane implements EventHandler<ActionEvent> {

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab2.gui.messages");
    
    public static final DoublyLinkedList<Image> img = new DoublyLinkedList<Image>();

    private static final int TF_WIDTH = 120;
    private static final int TF_WIDTH_SMALLER = 70;

    private static final double SPACING = 5.0;
    private static final Insets INSETS = new Insets(SPACING);
    private static final double SPACING_SMALLER = 2.0;
    private static final Insets INSETS_SMALLER = new Insets(SPACING_SMALLER);
    
    private static int count = 1;

    private final TextArea taOutput = new TextArea();
    private final GridPane paneBottom = new GridPane();
    private final GridPane paneParam2 = new GridPane();
    private final GridPane paneComboBox = new GridPane();
    private final TextField tfDelimiter = new TextField();
    private final TextField tfInput = new TextField();
    private final ComboBox cmbTreeType = new ComboBox();//Išokantis meniu

    private Panels paneParam1, paneButtons, paneButtons2;
    private MainWindowMenu mainWindowMenu;
    private final Stage stage; //Sukuriama scena

    //private static ParsableSortedSet<Book> booksSet;
   // private BooksGenerator booksGenerator = new BooksGenerator();

    private int sizeOfInitialSubSet, sizeOfGenSet, sizeOfLeftSubSet;
    private double shuffleCoef;
    private final String[] errors;

    public MainWindow(Stage stage) throws FileNotFoundException {
        this.stage = stage;
        errors = new String[]{
                MESSAGES.getString("badSetSize"),
                MESSAGES.getString("badInitialData"),
                MESSAGES.getString("badSetSizes"),
                MESSAGES.getString("badShuffleCoef")
        };
        initComponents();
    }

    private void initComponents() throws FileNotFoundException {
        //======================================================================
        // Sudaromas rezultatų išvedimo VBox klasės objektas, kuriame
        // talpinamas Label ir TextArea klasės objektai
        //======================================================================        
        VBox vboxTaOutput = new VBox();
        vboxTaOutput.setAlignment(Pos.CENTER);
        VBox.setVgrow(taOutput, Priority.ALWAYS);
        Image image1 = new Image(new FileInputStream("C:\\Users\\Antanas\\Desktop\\labasJava\\Labas2Projek\\Lab2_Projekt\\src\\edu\\ktu\\ds\\lab2\\gui\\pexels-photo-326055.jpg"));
        vboxTaOutput.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        final ImageView imv = new ImageView();
        imv.setImage(image1);
        vboxTaOutput.getChildren().add(imv);
        //======================================================================
        // Formuojamas mygtukų tinklelis (mėlynas). Naudojama klasė Panels.
        //======================================================================

        paneButtons = new Panels(
                new String[]{
                        MESSAGES.getString("button1")},
                1, 1);
        paneButtons2  = new Panels(
                new String[]{"Kitas"}, 1, 1);
        //======================================================================
        // Formuojama pirmoji parametrų lentelė (žalia). Naudojama klasė Panels.
        //======================================================================
        paneParam1 = new Panels(
                new String[]{
                        MESSAGES.getString("lblParam11")},
                new String[]{
                        MESSAGES.getString("tfParam11")},
                        TF_WIDTH_SMALLER);
        //======================================================================
        // Formuojama antroji parametrų lentelė (gelsva).
        //======================================================================
        paneParam2.setAlignment(Pos.CENTER);
        paneParam2.setNodeOrientation(NodeOrientation.INHERIT);
        paneParam2.setVgap(SPACING);
        paneParam2.setHgap(SPACING);
        paneParam2.setPadding(INSETS);
        
        paneComboBox.setAlignment(Pos.CENTER);
        paneComboBox.setNodeOrientation(NodeOrientation.INHERIT);
        paneComboBox.setVgap(SPACING);
        paneComboBox.setHgap(SPACING);
        paneComboBox.setPadding(INSETS);

        cmbTreeType.setItems(FXCollections.observableArrayList(
                MESSAGES.getString("cmbTreeType0"),
                MESSAGES.getString("cmbTreeType1"),
                MESSAGES.getString("cmbTreeType2")
        ));
        cmbTreeType.setPrefWidth(TF_WIDTH);
        cmbTreeType.getSelectionModel().select(0);
        paneComboBox.add(cmbTreeType, 1, 0);
        paneParam2.add(paneButtons2, 0, 0);

        tfDelimiter.setPrefWidth(TF_WIDTH);
        tfDelimiter.setAlignment(Pos.CENTER);

        // Vėl pirmas stulpelis, tačiau plotis - 2 celės
        tfInput.setEditable(false); //------------------------------------------------------------ Leidimas juodame langelyje rašyti tekstą
        tfInput.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        //======================================================================
        // Formuojamas bendras parametrų panelis (tamsiai pilkas).
        //======================================================================
        paneBottom.setPadding(INSETS);
        paneBottom.setHgap(SPACING);
        paneBottom.setVgap(SPACING);
        paneBottom.add(new Label("Atgal"), 0, 0);
        paneBottom.add(paneButtons, 0, 0);
        paneBottom.add(paneParam1, 1, 0);
        paneBottom.add(paneParam2, 2, 0);
        paneBottom.add(paneComboBox, 3, 0);
        paneParam1.getTfOfTable().get(0).setEditable(false);
        paneBottom.alignmentProperty().bind(new SimpleObjectProperty<>(Pos.CENTER));

        mainWindowMenu = new MainWindowMenu() {
            @Override
            public void handle(ActionEvent ae) { //Nukreipimai iš viršutiniojo meniu
                Region region = (Region) taOutput.lookup(".content");

                try {
                    Object source = ae.getSource();

                    if (source.equals(mainWindowMenu.getMenus().get(0).getItems().get(0))) {
                        System.exit(0);
                    } else if (source.equals(mainWindowMenu.getMenus().get(1).getItems().get(0))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setTitle(MESSAGES.getString("menuItem21"));
                        alert.setHeaderText(MESSAGES.getString("author"));
                        alert.showAndWait();
                    }
                } catch (ValidationException e) {
                    KsGui.ounerr(taOutput, e.getMessage());
                } catch (Exception e) {
                    KsGui.ounerr(taOutput, MESSAGES.getString("systemError"));
                    e.printStackTrace(System.out);
                }
                KsGui.setFormatStartOfLine(false);
            }
        };

        //======================================================================
        // Formuojamas Lab2 langas
        //======================================================================          
        // ..viršuje, centre ir apačioje talpiname objektus..
        setTop(mainWindowMenu); //prijungiamas viršutinis meniu
        setCenter(vboxTaOutput); //Išvedimo teksto laukas

        VBox vboxPaneBottom = new VBox();
        VBox.setVgrow(paneBottom, Priority.ALWAYS);
        vboxPaneBottom.getChildren().addAll(new Label(MESSAGES.getString("border2")), paneBottom);
        setBottom(vboxPaneBottom); //Įkeliama apačios panelė
        appearance();

        paneButtons.getButtons().forEach(btn -> btn.setOnAction(this)); //Mygtukų aktyvavimas
        paneButtons2.getButtons().forEach(btn -> btn.setOnAction(this));
        cmbTreeType.setOnAction(this); //Iššokančio meniu aktyvavimas
    }

    private void appearance() {
        paneButtons.setBackground(new Background(new BackgroundFill(Color.rgb(112, 162, 255)/* Blyškiai mėlyna */, CornerRadii.EMPTY, Insets.EMPTY)));
        paneParam1.setBackground(new Background(new BackgroundFill(Color.rgb(204, 255, 204)/* Šviesiai žalia */, CornerRadii.EMPTY, Insets.EMPTY)));
        paneParam2.setBackground(new Background(new BackgroundFill(Color.rgb(112, 162, 255)/* Gelsva */, CornerRadii.EMPTY, Insets.EMPTY)));
        paneBottom.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
        taOutput.setFont(Font.font("Monospaced", 12));
        taOutput.setStyle("-fx-text-fill: black;");
        taOutput.setEditable(false);
    }

    @Override
    public void handle(ActionEvent ae) { //Signalų pagavimas iš mygtukų ir ComboBox
        try {
            System.gc();
            System.gc();
            System.gc();
            Region region = (Region) taOutput.lookup(".content");

            Object source = ae.getSource();
            if (source instanceof Button) {
                handleButtons(source);
            } else if (source instanceof ComboBox && source.equals(cmbTreeType)) {
                handleComboBox(source);
            }
        } catch (ValidationException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                KsGui.ounerr(taOutput, errors[e.getCode()] + ": " + e.getMessage());
                if (e.getCode() == 2) {
                    paneParam1.getTfOfTable().get(0).setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                    paneParam1.getTfOfTable().get(1).setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            } else if (e.getCode() == 4) {
                KsGui.ounerr(taOutput, MESSAGES.getString("allSetIsPrinted"));
            } else {
                KsGui.ounerr(taOutput, e.getMessage());
            }
        } catch (UnsupportedOperationException e) {
            KsGui.ounerr(taOutput, e.getLocalizedMessage());
        } catch (Exception e) {
            KsGui.ounerr(taOutput, MESSAGES.getString("systemError"));
            e.printStackTrace(System.out);
        }
    }
    //Mygtukams priskiriami metodai
    private void handleButtons(Object source) throws ValidationException {
        if (source.equals(paneButtons.getButtons().get(0))) {
            Kitas();
        }
        else  if (source.equals(paneButtons2.getButtons().get(0))) {
            Kitas2();
        }
    }
    
    private void handleComboBox(Object source) throws ValidationException 
    {
               Object obj = "Pasirinkti:";
               switch (cmbTreeType.getSelectionModel().getSelectedIndex()) {
            case 1:
                            ShowImage(img.toBeginOrEnd(true));
                            count = 1;
                            paneParam1.getTfOfTable().get(0).setText(count + "");
                            paneButtons.getButtons().get(0).setText("...");
                            paneButtons2.getButtons().get(0).setText("Kitas");
                            cmbTreeType.setValue(obj); 
                break;
            case 2:
                              ShowImage(img.toBeginOrEnd(false));
                              count = img.Size();
                              paneParam1.getTfOfTable().get(0).setText(count + "");
                              paneButtons.getButtons().get(0).setText("Atgal");
                              paneButtons2.getButtons().get(0).setText("...");
                              cmbTreeType.setValue(obj);
                break;
            case 3:
                break;
            }
               
    }
    public void Kitas()
    {
                    ShowImage(img.get(false));
                     paneButtons.getButtons().get(0).setText("Atgal"); 
                     paneButtons2.getButtons().get(0).setText("Kitas");
                     
                     if (img.checkLeft()) 
                     {
                            paneButtons.getButtons().get(0).setText("...");
                            count = 1;
                     }
                     else
                        count--;
                        paneParam1.getTfOfTable().get(0).setText(count + "");
    }
       
    public void Kitas2()
    {
                Image im = img.get(true);
                ShowImage(im);
                paneButtons2.getButtons().get(0).setText("Kitas");
                paneButtons.getButtons().get(0).setText("Atgal");
                if (im == img.get(img.Size()-1)) 
                {
                     count = img.Size();
                     paneButtons2.getButtons().get(0).setText("...");
                     paneParam1.getTfOfTable().get(0).setText(count + "");
                }
                else
                {
                    count++;
                    paneParam1.getTfOfTable().get(0).setText(count + "");
                }
    }
    public void ShowImage(Image img)
    {
        VBox vboxTaOutput = new VBox();
        vboxTaOutput.setAlignment(Pos.CENTER);
        vboxTaOutput.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(255, 255, 255, 0.5);";
        vboxTaOutput.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox.setVgrow(taOutput, Priority.ALWAYS);
        Image image1;
                final ImageView imv = new ImageView();
                 imv.setImage(img);
                 vboxTaOutput.getChildren().add(imv);
        setCenter(vboxTaOutput); 
    }
    

    public static void createAndShowGui(Stage stage) throws FileNotFoundException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        MainWindow window = new MainWindow(stage);
       loadImg();
        stage.setScene(new Scene(window, 1100, 650));
        stage.setTitle(MESSAGES.getString("title"));
        stage.getIcons().add(img.get(0));
        stage.show();
    }
    public static void loadImg()
    {
        try {
            img.add(new Image(new FileInputStream("C:\\Users\\Antanas\\Desktop\\labasJava\\Labas2Projek\\Lab2_Projekt\\src\\edu\\ktu\\ds\\lab2\\gui\\pexels-photo-326055.jpg")));
            img.add(new Image(new FileInputStream("C:\\Users\\Antanas\\Desktop\\labasJava\\Labas2Projek\\Lab2_Projekt\\src\\edu\\ktu\\ds\\lab2\\gui\\rose-blue-flower-rose-blooms-67636.jpg")));
            img.addLast(new Image(new FileInputStream("C:\\Users\\Antanas\\Desktop\\labasJava\\Labas2Projek\\Lab2_Projekt\\src\\edu\\ktu\\ds\\lab2\\gui\\water-1330252_960_720.jpg")));
            img.addLast(new Image(new FileInputStream("C:\\Users\\Antanas\\Desktop\\labasJava\\Labas2Projek\\Lab2_Projekt\\src\\edu\\ktu\\ds\\lab2\\gui\\y1f68eqt.bmp")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
