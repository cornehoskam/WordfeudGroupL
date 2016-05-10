package Gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Main.GUI;
import model.User;
import controller.DatabaseController;

@SuppressWarnings("serial")
public class GamestateManager extends JPanel {
	
	private DatabaseController db_c;
	private GUI gui;
	// keeps track of all gamestates
	private ArrayList<Gamestate> gamestates;

	// different types of gamestates
	public final static int loginState = 0;
	public final static int playState = 1;
	public final static int registerState = 2;
	public final static int playerNewWordState = 3;
	public final static int moderatorReviewWordState = 4;
	public final static int spectatorState = 5;
	public final static int gameOverviewState = 6;
	public final static int competitionState = 7;
	public final static int adminState = 8;
	public final static int rankingState = 9;
	public final static int mainMenuState = 10;
	public final static int infoUserState = 11;

	// displays the current state of the game
	private int currentState;
	
	private User user;

	public GamestateManager(GUI gui) {
		this.gui = gui;
		create();
	}


	private void create() {
		db_c = new DatabaseController();
		this.setBackground(new Color(24, 24, 24));
		this.currentState = -1;
		this.gamestates = new ArrayList<Gamestate>();
		this.gamestates.add(new Loginstate(this,db_c));
		this.gamestates.add(new Playstate(this,db_c));
		this.gamestates.add(new Registerstate(this,db_c));
		this.gamestates.add(new NewWordstate(this, db_c));
		this.gamestates.add(new ReviewWordstate(this, db_c));
		this.gamestates.add(new SpectatorState(this,db_c));
		this.gamestates.add(new GameOverviewState(this, db_c));
		this.gamestates.add(new Competitionstate(this, db_c));
		this.gamestates.add(new AdminState(this, db_c));
		this.gamestates.add(new RankingState(this, db_c));
		this.gamestates.add(new MainMenuState(this, db_c));
//		this.gamestates.add(new InfoUserState(this, db_c));
		// state you want to start with
		this.setGamestate(loginState);
		/*
		 * add all gamestates to the array list
		 */
	}

	public void setGamestate(int gamestate) {
		if (currentState != -1) {

			this.remove(gamestates.get(currentState));
		}
		this.currentState = gamestate;
		//TODO array loop gamestates, betere manier vinden
		this.add(gamestates.get(currentState));
		this.gamestates.get(currentState).create();
		this.validate();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		this.gamestates.get(currentState).draw(g2d);
	}

	public void update() {
		this.gamestates.get(currentState).update();
	}
	
	//Set the user and add the necessary menus with the gui.createRoleMenus method.
	public void setUser(User user){
		this.user = user;
	}


	public User getUser()
	{
		return user;
	}
	
	public User getUser(String username){
		return user;
	}
	
	public DatabaseController getDatabaseController() {
		return db_c;
	}	
	
	public int getCurrentState()
	{
		return currentState;
	}
}
