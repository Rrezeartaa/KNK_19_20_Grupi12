package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML ImageView imageView;
    int count;
    
    public void slideshow(){
        ArrayList<Image> images=new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5),event->{
            imageView.setImage(images.get(count));
            count++;
            if(count == 4)
                count = 0;

        }));
    
    
  
}
