import javafx.application.Application;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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
		// make them constant
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
			setTriangle(e, point[0], 0, ln, angles);
		});

		point[1].setOnMouseDragged(e -> {
			setTriangle(e, point[1], 1, ln, angles);
		});

		point[2].setOnMouseDragged(e -> {
			setTriangle(e, point[2], 2, ln, angles);
		});

		Scene scene = new Scene(pane, 300, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setTriangle(MouseEvent e, Circle p,
			int index, Line[] ln, Text[] angles){
		double mx = e.getX();
		double my = e.getY();

		double[] cords;
		cords = getCord(mx,my, 1);

		point[index].setCenterX(cords[0]);
		point[index].setCenterY(cords[1]);


		ln[index].setStartX(point[index].getCenterX());
		ln[index].setStartY(point[index].getCenterY());
		ln[index].setEndX(point[(index+1)%3].getCenterX());
		ln[index].setEndY(point[(index+1)%3].getCenterY());

		ln[(index+2)%3].setEndX(point[index].getCenterX());
		ln[(index+2)%3].setEndY(point[index].getCenterY());

		double[] cordangle = getCord(point[index].getCenterX(),
				point[index].getCenterY(), 0.9);
		angles[index].setX(cordangle[0]);
		angles[index].setY(cordangle[1]);

		double linea = getDistance(ln[1]);
		double lineb = getDistance(ln[2]);
		double linec = getDistance(ln[0]);
		angles[0].setText(getAngle(linea, lineb, linec) + "");
		angles[1].setText(getAngle(lineb, linea, linec) + "");
		angles[2].setText(getAngle(linec, lineb, linea) + "");
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