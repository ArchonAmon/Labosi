package gol;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BoardFrame extends JFrame {
	private static final int BOARD_HEIGHT = 30;
	private static final int BOARD_WIDTH = 30;
	private JButton btnPlay, btnStop, btnTick;
	private JSlider slider;
	private JLabel label = new JLabel();
	private JToggleButton buttons[][];
	private Board board;
	private SimulationThread simulationThread;
	private int pom;
	
	public BoardFrame() {
		JPanel buttonPanel = new JPanel();
		
		btnPlay = new JButton("Pokreni");
		buttonPanel.add(btnPlay);
		btnPlay.addActionListener((e) -> {
			simulationThread = new SimulationThread(board);
			btnPlay.setEnabled(false);
			btnStop.setEnabled(true);
			btnTick.setEnabled(false);
			simulationThread.start();
		});
		
		btnStop = new JButton("Zaustavi");
		btnStop.setEnabled(false);
		buttonPanel.add(btnStop);
		btnStop.addActionListener((e) -> {
			simulationThread.setStopping();
			btnPlay.setEnabled(true);
			btnStop.setEnabled(false);
			btnTick.setEnabled(true);
		});
		
		btnTick = new JButton("Jedna iteracija");
		buttonPanel.add(btnTick);
		btnTick.addActionListener((e) -> {
			board.playOneIteration();
		});
		
		slider = new JSlider(SwingConstants.HORIZONTAL, 50, 500, 50);   //konstruktor za jslider
        buttonPanel.add(slider);
 
        slider.addChangeListener((e) -> {                     //aktiviranje jslidera
             pom = slider.getValue();
             simulationThread.spavanje = pom;
             label.setText(String.valueOf(pom) + "ms");
        });
 
 
        label.setPreferredSize(new Dimension (40, 20));     
        label.setText(String.valueOf(slider.getValue()));
 
        buttonPanel.add(label);      

		
		add(buttonPanel, BorderLayout.NORTH);
		
		initializeButonsAndBoard();
	}

	private void initializeButonsAndBoard() {
		board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		buttons = new JToggleButton[BOARD_WIDTH][BOARD_HEIGHT];
		JPanel boardPanel = new JPanel();
		add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(new GridLayout(BOARD_WIDTH, BOARD_HEIGHT, 1, 1));
		
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				JToggleButton toggleButton = new JToggleButton();
				toggleButton.setPreferredSize(new Dimension(20, 20));
				buttons[x][y] = toggleButton;
				Point point = new Point(x, y);
				toggleButton.addActionListener((e) -> {
					if(((JToggleButton)e.getSource()).isSelected()) {
						board.setCell((int)point.getX(), (int)point.getY(), true);
					} else {
						board.setCell((int)point.getX(), (int)point.getY(), false);
					}
				});
				boardPanel.add(toggleButton);
			}
		}
		
		board.addListener((b) -> {
			boolean cells[][] = new boolean[BOARD_WIDTH][BOARD_HEIGHT];
			for (int y = 0; y < BOARD_HEIGHT; y++) {
				for (int x = 0; x < BOARD_WIDTH; x++) {
					cells[x][y] = b.isCellAlive(x, y);
				}
			}
			
			SwingUtilities.invokeLater(() -> {
				for (int y = 0; y < BOARD_HEIGHT; y++) {
					for (int x = 0; x < BOARD_WIDTH; x++) {
						buttons[x][y].setSelected(cells[x][y]);
					}
				}
			});
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			BoardFrame frame = new BoardFrame();
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		});
	}

}
