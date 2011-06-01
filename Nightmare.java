import java.util.concurrent.*;
import java.awt.Point;

public class Nightmare{
	public static void main(String[] args){
		//Main menu
		GMenu menu = new GMenu();
		//
	}
	public static void newGame(GWindow window,Stage stage,String hero){
		Clock clock = new Clock(32);
		KeyboardListener keyboard = new KeyboardListener();
		EventConnect connect = new EventConnect();
		
		Hero player = new Hero(keyboard,new Point(225,300),8,2);
		ArrayBlockingQueue<Printable[]> channel = new ArrayBlockingQueue<Printable[]>(1);
		Processor processor = new Processor(stage,clock,player,channel,connect);
		
		GScreen output = new GScreen(window,channel,keyboard,stage,connect);
		
		connect.addEventListener(Processor);
		connect.addEventListener(GScreen);
		
		
		Thread processorThread = new Thread(processor);
		Thread outputThread = new Thread(output);
		
		processorThread.start();
		outputThread.start();
		clock.start();
	}
}
