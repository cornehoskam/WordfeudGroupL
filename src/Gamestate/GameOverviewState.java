package Gamestate;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.ScrollPane;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import GameObjects.GamePanel;
import Main.GUI;
import controller.DatabaseController;

public class GameOverviewState extends Gamestate {

	private int x;
	private int y;

	private String description = "Test";

	private GamePanel gamePanel;

	private boolean isCreated = false;

	public GameOverviewState(GamestateManager gsm, DatabaseController db_c) {
		super(gsm, db_c);
		this.setLayout(new GridBagLayout());
	}

	@Override
	public void draw(Graphics2D g) {
		if (isCreated) {
			g.setColor(Color.lightGray);
			g.setFont(new Font("Comic Sans mt", Font.ITALIC, 50));
			g.drawString(description, x, y);
		}
	}

	@Override
	public void update() {
	}

	@Override
	public void create() {
		if (!isCreated) {
			gamePanel = new GamePanel(db_c, gsm);
			x = (int) (GUI.WIDTH / 2.5);

			y = 75;
			this.add(gamePanel, new GridBagConstraints());
			description = gsm.getUser().getCompetitionDescription();
			isCreated = true;
		} else {
			gamePanel.gamePanelReload();
		}
	}

}
