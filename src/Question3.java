import javafx.application.Application;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Question3 extends Application {
	Circle[] point = new Circle[3];
	int radius = 80;
	int cx = 150;
	int cy = 150;
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		// make them constent
		Circle c = new Circle(150, 150, radius);
		c.setFill(Color.WHITE);
		c.setStroke(Color.BLACK);
		pane.getChildren().add(c);

		for (int i = 0; i < 3; i++) {
			Random rand = new Random();
			double angle = Math.toRadians(rand.nextInt(360));
			double dx = radius * Math.cos(angle);
			double dy = radius * Math.sin(angle);

			point[i] = new Circle(cy - dy, cx + dx, 5);
			point[i].setStroke(Color.BLACK);
			point[i].setFill(Color.RED);
		}

		Line[] ln = new Line[3];
		for (int i = 0; i < 3; i++) {
			ln[i] = new Line(point[i].getCenterX(),point[i].getCenterY(),
					point[(i+1) % 3].getCenterX(), point[(i+1) % 3].getCenterY());
			ln[i].setStroke(Color.BLACK);
			pane.getChildren().add(ln[i]);
		}

		Text[] angles = new Text[3];
		for (int i = 0; i < 3; i++) {
			double linea = getDistance(ln[(i+1)%3]);
			double lineb = getDistance(ln[(i+2)%3]);
			double linec = getDistance(ln[(i+3)%3]);
			double[] cordangle = getCord(point[i].getCenterX(), point[i].getCenterY(), 0.9);

			angles[i] = new Text(cordangle[0], cordangle[1], getAngle(linea, lineb, linec)+"");
			pane.getChildren().add(angles[i]);
			pane.getChildren().add(point[i]);
		}

		point[0].setOnMouseDragged(e -> {
			double mx = e.getX();
			double my = e.getY();

			double[] cords;
			cords = getCord(mx,my, 1);

			point[0].setCenterX(cords[0]);
			point[0].setCenterY(cords[1]);


			ln[0].setStartX(point[0].getCenterX());
			ln[0].setStartY(point[0].getCenterY());
			ln[0].setEndX(point[1].getCenterX());
			ln[0].setEndY(point[1].getCenterY());

			ln[2].setEndX(point[0].getCenterX());
			ln[2].setEndY(point[0].getCenterY());

			double[] cordangle = getCord(point[0].getCenterX(), point[0].getCenterY(), 0.9);
			angles[0].setX(cordangle[0]);
			angles[0].setY(cordangle[1]);

			double linea = getDistance(ln[1]);
			double lineb = getDistance(ln[2]);
			double linec = getDistance(ln[0]);
			angles[0].setText(getAngle(linea, lineb, linec) + "");
			angles[1].setText(getAngle(lineb, linea, linec) + "");
			angles[2].setText(getAngle(linec, lineb, linea) + "");
		});

		point[1].setOnMouseDragged(e -> {
			double mx = e.getX();
			double my = e.getY();

			double[] cords;
			cords = getCord(mx,my, 1);

			point[1].setCenterX(cords[0]);
			point[1].setCenterY(cords[1]);


			ln[1].setStartX(point[1].getCenterX());
			ln[1].setStartY(point[1].getCenterY());
			ln[1].setEndX(point[2].getCenterX());
			ln[1].setEndY(point[2].getCenterY());

			ln[0].setEndX(point[1].getCenterX());
			ln[0].setEndY(point[1].getCenterY());

			double[] cordangle = getCord(point[1].getCenterX(), point[1].getCenterY(), 0.9);
			angles[1].setX(cordangle[0]);
			angles[1].setY(cordangle[1]);

			double linea = getDistance(ln[1]);
			double lineb = getDistance(ln[2]);
			double linec = getDistance(ln[0]);
			angles[0].setText(getAngle(linea, lineb, linec) + "");
			angles[1].setText(getAngle(lineb, linea, linec) + "");
			angles[2].setText(getAngle(linec, lineb, linea) + "");
		});

		point[2].setOnMouseDragged(e -> {
			double mx = e.getX();
			double my = e.getY();

			double[] cords;
			cords = getCord(mx,my, 1);

			point[2].setCenterX(cords[0]);
			point[2].setCenterY(cords[1]);


			ln[2].setStartX(point[2].getCenterX());
			ln[2].setStartY(point[2].getCenterY());
			ln[2].setEndX(point[0].getCenterX());
			ln[2].setEndY(point[0].getCenterY());

			ln[1].setEndX(point[2].getCenterX());
			ln[1].setEndY(point[2].getCenterY());

			double linea = getDistance(ln[1]);
			double lineb = getDistance(ln[2]);
			double linec = getDistance(ln[0]);
			angles[0].setText(getAngle(linea, lineb, linec) + "");
			angles[1].setText(getAngle(lineb, linea, linec) + "");
			angles[2].setText(getAngle(linec, lineb, linea) + "");

			double[] cordangle = getCord(point[2].getCenterX(), point[2].getCenterY(), 0.9);
			angles[2].setX(cordangle[0]);
			angles[2].setY(cordangle[1]);
		});

		Scene scene = new Scene(pane, 300, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public int getAngle(double la, double lb, double lc) {
		return (int)Math.toDegrees(Math.acos((la * la - lb * lb - lc * lc) / ((-2) * lb * lc)));
	}

	public double getDistance(Line ln){
		return Math.sqrt(Math.pow((ln.getStartX() - ln.getEndX()),2) +
				Math.pow((ln.getStartY() - ln.getEndY()),2));
	}

	public double[] getCord(double mx, double my, double dr) {
		double distance = Math.sqrt(Math.pow((mx - cx),2) + Math.pow((my - cy),2));
		double diff_x = (radius * dr) * (mx - cx * dr) / distance ;
		double diff_y = (radius * dr) * (my - cy * dr) / distance ;

		double cords[] = new double[2];
		cords[0] = cx + diff_x;
		cords[1] = cy + diff_y;

		return cords;
	}


	public static void main(String[] args) {
		launch(args);
	}
}