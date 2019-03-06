import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.Random;

public class Threecards extends Application{
	@Override
	public void start (Stage primaryStage) {
		HBox pane = new HBox();
		int num1 = 0;
		int num2 = 0;
		int num3;

		Random rand = new Random();
		num1 = rand.nextInt(54);
		num2 = rand.nextInt(54);
		do {
			num2 = 1 + rand.nextInt(54);
		} while((num2 == num1));
		num3 = 1 + rand.nextInt(54);
		do {
			num3 = 1 + rand.nextInt(54);
		} while((num3 == num1 && num3 == num2));

		String cardnumber1 = "file:c:/users/snnch/Downloads/Assignment/Cards/" +
					num1 + ".png";
		String cardnumber2 = "file:c:/users/snnch/Downloads/Assignment/Cards/" +
				num2 + ".png";
		String cardnumber3 = "file:c:/users/snnch/Downloads/Assignment/Cards/" +
				num3 + ".png";
//		String cardnum = "file:c:/users/snnch/Downloads/Assignment/Cards/13.png";

		ImageView card1 = new ImageView(cardnumber1);
		ImageView card2 = new ImageView(cardnumber2);
		ImageView card3 = new ImageView(cardnumber3);
		pane.getChildren().addAll(card1, card2, card3);


		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}