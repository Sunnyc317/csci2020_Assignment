import javafx.application.Application;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// action event for button
// transfer number of letters to histogram
// put a line under histogram and text under histogram

public class Question4 extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		Pane histogram = new Pane();
		HBox search = new HBox();

		TextField tf = new TextField();
		tf.setPrefColumnCount(26);
		Button bt = new Button("View");

		search.getChildren().addAll(new Label("Filename"), tf, bt);
		search.setStyle("-fx-border-color: black");
		pane.setBottom(search);

		//find the index of the highest rectangle

		Scene scene = new Scene(pane,400,300);
		primaryStage.setScene(scene);
		primaryStage.show();

		Rectangle[] rec = new Rectangle[26];
		Line line = new Line();
		Text[] text = new Text[26];
		double[] height = new double[26];
		double width = 12;
		double gap = 3;
		histogram.setStyle("-fx-border-color: black");

		for (int i = 0; i < 26; i++) {
			rec[i] = new Rectangle();
			text[i] = new Text(String.valueOf((char)('A'+i)));
			System.out.println(String.valueOf((char)('A'+i)));
			histogram.getChildren().add(text[i]);
			histogram.getChildren().add(rec[i]);
		}
		histogram.getChildren().addAll(line);


		bt.setOnAction(e-> {

//			String test = "c:/users/snnch/project/test_csci2020_asmt.txt";
			File file = new File(tf.getText());
//			File file = new File(test);

			int[] letters = new int[26];
			Scanner input = null;
			Label notfound = new Label("File not found");
			try {
				input = new Scanner(file);
				pane.setTop(new Label("There you go"));
			} catch (FileNotFoundException e1) {
				pane.setTop(notfound);
			}
			while (input.hasNext()) {
//				notfound.setText("There you go");
				String word = input.next();
				char[] ltr = word.toCharArray();

				for (char i: ltr) {
					if (i >= 'a' && i <='z'){
						int charnum = (int) (i - 'a');
						letters[charnum]++;
					}
					else if (i >= 'A' && i <= 'Z') {
						int charnum = (int) (i - 'A');
						letters[charnum]++;
					}
				}
			}
			input.close();

			int highest = 0;
			//set the height and width of the rectangles

			for (int i = 0; i < 26; i++){
				if (letters[i] > letters[highest]){
					highest = i;
				}
				height[i] = letters[i]*5;
			}

			primaryStage.setHeight(height[highest]+200);

			double base_y = height[highest] + primaryStage.getY();
			for (int i = 0; i < 26; i++) {
//				rec[i] = new Rectangle(width, height[i]);
				rec[i].setWidth(width);
				rec[i].setHeight(height[i]);
				rec[i].setStroke(Color.BLACK);
				rec[i].setFill(Color.WHITE);

				rec[i].xProperty().bind(primaryStage.widthProperty().divide(2).add((-13+i)*(width+gap)));
				rec[i].yProperty().bind(primaryStage.heightProperty().subtract(70).divide(2).add(height[highest]/2).subtract(height[i]));

				text[i].xProperty().bind(rec[i].xProperty().add(2));
				text[i].yProperty().bind(rec[i].yProperty().add(10 + height[i]));
			}
			line.startXProperty().bind(primaryStage.widthProperty().divide(2).add((-13)*(width+gap)));
			line.startYProperty().bind(primaryStage.heightProperty().subtract(70).divide(2).add(height[highest]/2));
			line.endXProperty().bind(primaryStage.widthProperty().divide(2).add((-13)*(width+gap)).add((width+gap) * 26));
			line.endYProperty().bind(primaryStage.heightProperty().subtract(70).divide(2).add(height[highest]/2));

		});

		pane.setCenter(histogram);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
