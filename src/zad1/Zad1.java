import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;
 
 
/**
 * A live scatter chart.
 */
public class Zad1 extends Application {
 
    private ScatterChart.Series<Number,Number> series;
    private double nextX = 0;
    private SequentialTransition animation;
 
    public Zad1() {
        // create animation
        Timeline timeline1 = new Timeline();
        timeline1.getKeyFrames().add(
            new KeyFrame(Duration.millis(20), (ActionEvent actionEvent) -> {
                series.getData().add(new XYChart.Data<Number, Number>(
                        nextX,
                        nextX * nextX - nextX + 3
                ));
                nextX += 10;
        })
        );
        timeline1.setCycleCount(2000);
        animation = new SequentialTransition();
        animation.getChildren().addAll(timeline1);
    }
 
    public Parent createContent() {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setForceZeroInRange(false);
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setForceZeroInRange(false);
        final ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis, yAxis);
        // setup chart
        sc.setTitle("Animated Sine Wave ScatterChart");
        xAxis.setLabel("X Axis");
        xAxis.setAnimated(false);
        yAxis.setLabel("Y Axis");
        yAxis.setAnimated(false);
        // add starting data
        series = new ScatterChart.Series<>();
        series.setName("Sine Wave");
        series.getData().add(new ScatterChart.Data<Number, Number>(5d, 5d));
        sc.getData().add(series);
        return sc;
    }
 
    public void play() {
        animation.play();
    }
 
    @Override public void stop() {
        animation.pause();
    }
 
    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        play();
    }
 
    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}