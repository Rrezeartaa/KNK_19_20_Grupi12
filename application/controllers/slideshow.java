package application.controllers;

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

public class slideshow implements Initializable {
    @FXML ImageView imageView;
    int count;
    
    public void slideshow(){
        ArrayList<Image> images=new ArrayList<Image>();
        images.add(new Image("application/stilet/photos/welcome_photo.jpg"));
        images.add(new Image("application/stilet/photos/air_pic.jpg"));
        images.add(new Image("application/stilet/photos/a2.jpg"));
        images.add(new Image("application/stilet/photos/a3.jpg"));
        images.add(new Image("application/stilet/photos/a4.jpg"));
        images.add(new Image("application/stilet/photos/a1.jpg"));
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3),event->{
            imageView.setImage(images.get(count));
            count++;
            if(count == 6)
                count = 0;

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
     @FXML
	  private void s9(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/info.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	  }
     @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slideshow();
    }
}

