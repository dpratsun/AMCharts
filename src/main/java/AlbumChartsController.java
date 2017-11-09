/**
 * Sample Skeleton for 'AlbumChartsGui.fxml' Controller Class
 */

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import com.equilibriummusicgroup.AMCharts.model.ChartsModel;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AlbumChartsController {

    @FXML
    private ChartsModel chartsModel;

    @FXML // fx:id="countryChoiceBox"
    private ChoiceBox<String> countryChoiceBox; // Value injected by FXMLLoader

    @FXML // fx:id="genreChoiceBox"
    private ChoiceBox<String> genreChoiceBox; // Value injected by FXMLLoader

    @FXML // fx:id="getButton"
    private Button getButton; // Value injected by FXMLLoader

    @FXML // fx:id="resetButton"
    private Button resetButton; // Value injected by FXMLLoader

    @FXML // fx:id="albumsTextArea"
    private TextArea albumsTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="progressBar"
    private ProgressBar progressBar; // Value injected by FXMLLoader

    @FXML // fx:id="statusLabel"
    private Label statusLabel; // Value injected by FXMLLoader

    @FXML // fx:id="changeSceneButton"
    private Button changeSceneButton; // Value injected by FXMLLoader

    @FXML
    private Map<String, Integer> genreMap;

    @FXML
    private ObservableList<String> countryObsList = FXCollections.observableArrayList("us","es","de","it","pt");

    @FXML
    private ObservableList<String> genreObsList = FXCollections.observableArrayList("Alternative",
            "Ambient",
            "Blues",
            "Children’s Music",
            "Classical",
            "Country",
            "Dance",
            "Easy Listening",
            "Electronic",
            "Environmental",
            "Fitness & Workout",
            "Healing",
            "Holiday",
            "Instrumental",
            "Jazz",
            "Latino",
            "Lounge",
            "Meditation",
            "Nature",
            "New Age",
            "Relaxation",
            "Soundtrack",
            "Travel",
            "World");

    /**
     * This method displays the name and the version number of the program,
     * when the About item menu is clicked.
     */
    @FXML
    public void aboutItemAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AMCharts v1.0");
        alert.setHeaderText("AMCharts v1.0\n");
        alert.showAndWait();
    }

    @FXML
    void changeSceneButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SongsChartsGui.fxml"));

        Parent root = loader.load();
        SongsChartsController controller = loader.getController();
        controller.setModel(this.chartsModel);

        Stage stage = (Stage) changeSceneButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void getButtonAction(ActionEvent event) throws InvalidKeySpecException, NoSuchAlgorithmException {

        StringBuilder result = new StringBuilder();

        this.albumsTextArea.clear();

        String link = linkBuilder();

        result = chartsModel.getAlbumCharts(link);

        this.albumsTextArea.setText(result.toString());

    }

    private String linkBuilder() {

        StringBuilder link = new StringBuilder("https://api.music.apple.com/v1/catalog/"); //us/charts?types=albums&genre=1127&limit=50

        link.append(this.countryChoiceBox.getValue());
        link.append("/charts?types=albums&genre=");
        link.append(this.genreMap.get(this.genreChoiceBox.getValue()));
        link.append("&limit=50");

        System.out.println("LINK IS: " + link.toString());
        return link.toString();
    }



    /**
     * This method clears the text area.
     */
    @FXML
    public void resetAction(){

        this.albumsTextArea.clear();

    }

    public AlbumChartsController(){
        genreMap = new HashMap<>();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert countryChoiceBox != null : "fx:id=\"countryChoiceBox\" was not injected: check your FXML file 'AlbumChartsGui.fxml'.";
        assert genreChoiceBox != null : "fx:id=\"genreChoiceBox\" was not injected: check your FXML file 'AlbumChartsGui.fxml'.";
        assert getButton != null : "fx:id=\"getButton\" was not injected: check your FXML file 'AlbumChartsGui.fxml'.";
        assert resetButton != null : "fx:id=\"resetButton\" was not injected: check your FXML file 'AlbumChartsGui.fxml'.";
        assert albumsTextArea != null : "fx:id=\"albumsTextArea\" was not injected: check your FXML file 'AlbumChartsGui.fxml'.";
        assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'AlbumChartsGui.fxml'.";
        assert statusLabel != null : "fx:id=\"statusLabel\" was not injected: check your FXML file 'AlbumChartsGui.fxml'.";
        assert changeSceneButton != null : "fx:id=\"changeSceneButton\" was not injected: check your FXML file 'AlbumChartsGui.fxml'.";

        this.countryChoiceBox.setValue("us");
        this.genreChoiceBox.setValue("New Age");
        this.countryChoiceBox.setItems(this.countryObsList);
        this.genreChoiceBox.setItems(this.genreObsList);
        genreMap.put("Alternative",20);
        genreMap.put("Ambient", 1056);
        genreMap.put("Blues",2);
        genreMap.put("Children’s Music",4);
        genreMap.put("Classical",5);
        genreMap.put("Country",6);
        genreMap.put("Dance",17);
        genreMap.put("Easy Listening",25);
        genreMap.put("Electronic",7);
        genreMap.put("Environmental",1125);
        genreMap.put("Fitness & Workout",50);
        genreMap.put("Healing",1126);
        genreMap.put("Holiday",8);
        genreMap.put("Instrumental",53);
        genreMap.put("Jazz",11);
        genreMap.put("Latino",12);
        genreMap.put("Lounge",1054);
        genreMap.put("Meditation",1127);
        genreMap.put("Nature",1128);
        genreMap.put("New Age",13);
        genreMap.put("Relaxation",1129);
        genreMap.put("Soundtrack",16);
        genreMap.put("Travel", 1130);
        genreMap.put("World",19);

    }

    public void setModel(ChartsModel model) {
        this.chartsModel = model;
    }

    /**
     * Helper method to check whether the json retrieved has the field passed as a parameter the <code>jsonResponse</code> from the <code>com.equilibriummusicgroup.AMCharts.model.JsonQueryUtils</code>class.
     * @param gson the json object retrieved
     * @param key the key against which the check is made
     * @return Boolean
     */
    @FXML
    private Boolean checkNode(JsonObject gson, String key){
        return gson.has(key);
    }
}
