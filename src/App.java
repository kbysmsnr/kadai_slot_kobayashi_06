import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.Collections;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Slot Machine");

        HBox slot_box = new HBox(20d);
        slot_box.setAlignment(Pos.CENTER);

        ArrayList<SlotLabel> slotArr = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            slotArr.add(new SlotLabel("1"));
        }

        slot_box.getChildren().addAll(slotArr);

        HBox buttonBox = new HBox(10d);
        buttonBox.setAlignment(Pos.CENTER);
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        buttonBox.getChildren().add(startButton);
        buttonBox.getChildren().add(stopButton);

//        hBoxを二つつくっていました。
        VBox all_box = new VBox(20d);
        all_box.setAlignment(Pos.CENTER);
        all_box.getChildren().add(slot_box);
        all_box.getChildren().add(buttonBox);

        startButton.setOnAction(event -> {
            for (int i = 0; i < slotArr.size(); i++) {
                slotArr.get(i).setSlotStarted();
            }
        });

        stopButton.setOnAction(event -> {
            for(int i = 0; i < slotArr.size() ;i++){
                slotArr.get(i).stopSlot();
            }

//           判定、モーダルはかけませんでした。
            ArrayList<String> res = new ArrayList<>();
            for(int i = 0; i < slotArr.size() ;i++){
                res.add(slotArr.get(i).getText());
            }
            if (Collections.frequency(res, res.get(0)) == res.size()) {
                Stage newStage = new Stage();

                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.initOwner(primaryStage);

                HBox hbox = new HBox();
                Label label = new Label("おめでとう！");
                label.setFont(new Font(20d));
                hbox.getChildren().add(label);

                newStage.setScene(new Scene(hbox));

                newStage.show();

            }
        });

        Scene scene = new Scene(all_box, 320, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}