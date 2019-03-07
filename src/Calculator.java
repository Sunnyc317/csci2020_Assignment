import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Calculator extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(5);
		pane.setPadding(new Insets(10, 10, 10, 10));
		Button calculate = new Button("Calculate");

		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		TextField tf3 = new TextField();
		TextField tf4 = new TextField();
		tf4.setDisable(true);
		tf4.setStyle("-fx-opacity: 1;");

		Label amount = new Label("Investment Amount");
		Label year = new Label("Years");
		Label rate = new Label("Annual Interest Rate");
		Label value = new Label("Future value");

		pane.add(amount, 0, 0);
		pane.add(tf1, 1, 0);
		pane.add(year, 0, 1);
		pane.add(tf2, 1, 1);

		pane.add(rate, 0, 2);
		pane.add(tf3, 1, 2);
		pane.add(value, 0, 3);
		pane.add(tf4, 1, 3);

		HBox hb = new HBox();
		hb.getChildren().add(calculate);
		hb.setAlignment(Pos.CENTER_RIGHT);
		pane.add(hb, 1, 4);


//		HBox gp = new HBox();
//		gp.setSpacing(5);
//		gp.getChildren().addAll(num1, tf1, num2, tf2, res, tf3);
//		gp.setAlignment(Pos.CENTER);
//
//		HBox hb2 = new HBox();
//		hb2.setSpacing(5);
//		hb2.getChildren().addAll(add, subtract, multiply, divide);
//		hb2.setAlignment(Pos.CENTER);
//
//		BorderPane bp = new BorderPane();
//		bp.setCenter(gp);
//		bp.setBottom(hb2);
//		BorderPane.setAlignment(hb2, Pos.TOP_CENTER);

		// lambda function
		calculate.setOnAction(e -> {
			double invest = Double.parseDouble(tf1.getText());
			double yrs = Double.parseDouble(tf2.getText());
			double interest_rt = Double.parseDouble(tf3.getText()) / 100;
			double result = invest * (Math.pow(1+interest_rt/12,yrs*12));
			tf4.setText(result+"");
		});


		Scene scene = new Scene(pane, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public static void main(String[] args) {
		launch(args);
	}
}