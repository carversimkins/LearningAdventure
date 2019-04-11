import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
/*

 0 is left position 
 1196 is right position 
 
 */
public class AdventureManager {
	
	int first =0, first1=0, beginning = 0, end = 1145;
	static int floorHeight =700;
	static int gold = 5;
	static Room currentRoom;
	static int start = 0;
	ArrayList<Platform> blocks = new ArrayList<Platform>();
	ImageIcon background;
	public static Music musicPlayer;
	public static Combat combat = new Combat();
	public static Enemy currentEnemy;
	public static Adventurer toon;
	public static Timer t;
	public static JPanel floor, dataPanel;
	public static JLabel mainPanel, healthInfo;
	public static Shop shop = new Shop();
	static Font GUIFont = new Font("Comic Sans MS", Font.PLAIN, 18);
	static Font TextFont = new Font("Comic Sans MS", Font.PLAIN, 25);
	static Treasure currentTreasure;
	static NPC currentNPC = null;
	static Room spawnRoom;
	String factoryMusic = ".//res//Laserpack.wav";
	String DRUM = ".//res//DRUM.wav";
	
	public void createWorld() {
		
		Room Room1 = new Room("Cave"); Room1.setRespawn();
		Room1.addNPC(new NPC(150, "You can use A and D to move, and W to jump! You can jump over those platforms over there!"));
		Room1.addPlatform(new Platform(500, 650));
		Room1.addPlatform(new Platform(550, 600));
		Room1.addPlatform(new Platform(600, 550));
		Room1.start();
	
		Room Room2 = new Room("Sky");
		Room2.addPlatform(new Platform(475, 610));
		Room2.addPlatform(new Platform(525, 610));
		Room2.addChest(new Treasure(850, 650, 200));
		Room2.addPlatform(new Platform(575, 610));
		Room2.addPlatform(new Platform(625, 610));
		
		NPC warningGuy = new NPC(150, "Be careful around those Robots, they are dangerous!");
		warningGuy.addItem(new Item(
				"Tasty Squash",
				"Item",
				"This is a yummy looking squash! If you eat it, the tasty fruit could probably heal you!",
				10
				), 50);
		
		warningGuy.addItem(new Item(
				"Tasty Apple",
				"Item",
				"This is a yummy looking apple! If you eat it, the tasty fruit could probably heal you!",
				5
				), 10);
		Room2.addNPC(warningGuy);
		Room2.addEnemies(new Enemy(AdventureManager.floorHeight, 500, "Robot", 25));
		
		Room Room3 = new Room("Sky");
		Room3.addPlatform(new Platform(475, 610));
		Room3.addPlatform(new Platform(525, 610));
		Room3.addPlatform(new Platform(575, 610));
		Room3.addPlatform(new Platform(625, 610));
		
		Room Room4 = new Room("Sky");
		for(int i = 0; i<1200; i+=50) {
		Room4.addPlatform(new Platform(i, 400));	
		}
		Room4.addNPC(new NPC(200, "AHHH! Run from the monsters!"));
//		Room4.addEnemies(new Robot(AdventureManager.floorHeight, 500));
//		Room4.addEnemies(new Robot(AdventureManager.floorHeight, 600));
//		Room4.addEnemies(new Robot(AdventureManager.floorHeight, 800));
		
		Room1.addRightRoom(Room2);
		Room2.addRightRoom(Room3); Room2.addLeftRoom(Room1);
		Room3.addLeftRoom(Room2); Room3.addRightRoom(Room4);
		Room4.addLeftRoom(Room3); 

	}
	
	
	
	
	
	
	public void createSpace() {
		toon = new Adventurer(floorHeight, 0);
		mainPanel = new JLabel();
		mainPanel.setSize(1194, 771);
		mainPanel.setLocation(0,0);
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.MAGENTA);
		Main.window.add(mainPanel);
		mainPanel.setIcon(background);

		
		mainPanel.add(toon);
		Main.window.addKeyListener(toon);
		
		dataPanel = new JPanel(); 
		dataPanel.setLocation(0, 0);
		dataPanel.setSize(1200, 30);
		dataPanel.setLayout(null);
		dataPanel.setBackground(Color.cyan);
		mainPanel.add(dataPanel);
		
		healthInfo = new JLabel();
		healthInfo.setText("Health: " + Adventurer.health + "/" + Adventurer.maxHealth + "   Gold: " + gold );
		healthInfo.setSize(1200,25);
		healthInfo.setLocation(0,0);
		healthInfo.setFont(GUIFont);
		dataPanel.add(healthInfo);
		
		floor = new JPanel(); 
		floor.setLocation(0, floorHeight);
		floor.setSize(1200, 300);
		floor.setBackground(Color.gray);
		mainPanel.add(floor);
		
		mainPanel.setVisible(true);
		Main.window.setVisible(true);
		floor.setVisible(true);
		
		createWorld();
	}
	static void heal(int h) {
		Adventurer.health += h;
		if(Adventurer.health > Adventurer.maxHealth) Adventurer.health = Adventurer.maxHealth;
		healthInfo.setText("Health: " + Adventurer.health + "/" + Adventurer.maxHealth + "   Gold: " + gold );
	}
	
}

