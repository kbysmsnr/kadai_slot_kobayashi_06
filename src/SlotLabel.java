import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.Random;

public class SlotLabel extends Label {

    private Timeline timer;

    private boolean isSlotStarted;

    SlotLabel(String text){
        super(text);
        isSlotStarted = false;

        this.setPrefSize(80,80);

        this.setAlignment(Pos.CENTER);

        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
    }

    public void setSlotStarted(){

        if (isSlotStarted) {
            return;
        }
        // スロットが回っているのでisSlotStartedをtrueに変える
//        このtrue判定がわかりませんでした。
        isSlotStarted = true;

        Random rand = new Random();

        timer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            int randomNumber = rand.nextInt(9) + 1;
            this.setText(String.valueOf(randomNumber));
        }));

        // アニメーションをスタート
//        タイマーを自分でスタートできませんでした。
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

    }

    public void stopSlot() {

        if (isSlotStarted){
            timer.stop();
            isSlotStarted = false;
        }
    }

}