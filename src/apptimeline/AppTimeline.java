/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apptimeline;

import com.sun.javafx.perf.PerformanceTracker;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author rafae
 */
public class AppTimeline extends Application{
    public static double ballSpeedX = 1;
    public static double ballSpeedY = 1;
    @Override
    public void start(Stage primaryStage){
        Group pane = new Group();
        // Bola que se usará para la animación
        Circle ball = new Circle(10);
        ball.setTranslateX(300 * 0.5);
        ball.setTranslateY(250 * 0.5);
        pane.getChildren().addAll(ball);
        // Etiqueta que mostrará el valor de frames por segundo (FPS)
        Label label = new Label();
        label.setTranslateX(10);
        label.setTranslateY(10);
        pane.getChildren().addAll(label);
        Scene scene = new Scene(pane, 300, 250);
        //Escuchador a incluir en el bucle de Timeline
        EventHandler<ActionEvent> eH = e->{
            // Mostrar la frecuencia de refresco FPS
            PerformanceTracker perfTracker =
                PerformanceTracker.getSceneTracker(scene);
            label.setText("FPS (Timeline) = " + perfTracker.getInstantFPS());
            Random random = new Random();
            // Cambiar la dirección de la bola si llega a los extremos
            if(ball.getTranslateX() <= 0 || ball.getTranslateX() > 300) {
                ballSpeedX *= -1;         
            }
            else if(ball.getTranslateY()<=0 || ball.getTranslateY() > 250){
                ballSpeedY *= -1;
            }
            ball.setTranslateX(ball.getTranslateX() + ballSpeedX) ;
            ball.setTranslateY(ball.getTranslateY() + ballSpeedY) ;
        };
        //Definimos el bucle con la duración, cada 5 milisegundos que son aproximadamente 60 FPS
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5), eH));
        animation.setCycleCount(Timeline.INDEFINITE);
        // iniciamos animation
        animation.play();
        
        primaryStage.setTitle("Timeline");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
}
