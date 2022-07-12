// Made by LUMINOVA_#8461

package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 15;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 100;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean gameRunning = false;
    boolean gameOver = false;
    boolean menuOpened = false;
    boolean gridEnabled = false;
    Timer timer;
    Random random;
    JButton startB = new JButton("START");
    JButton menuB = new JButton("MENU");
    JButton gridBFalse = new JButton("OFF");
    JButton gridBTrue = new JButton("ON");
    JButton backB = new JButton("BACK");
    //JButton tryAgainB = new JButton("Try Again");
    JLabel scoreL = new JLabel();
    JLabel overL = new JLabel("Game Over");
    JLabel titleL = new JLabel();
    JLabel tryAgainText = new JLabel("Please restart the game!"); // Yuh, it sucks but I will try to make the default position to work
    JLabel dc = new JLabel("Made by LUMINOVA_#8461");
    JLabel gridO = new JLabel("GRID:");

    Panel() {
        random = new Random();
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.add(startB);
        this.add(menuB);
        this.add(gridBFalse);
        this.add(gridBTrue);
        this.add(backB);
        //this.add(tryAgainB);
        //will be added later
        this.add(scoreL);
        this.add(overL);
        this.add(titleL);
        this.add(tryAgainText);
        this.add(dc);
        this.add(gridO);

        startB.setBounds(200,300,200,80);
        startB.setFocusable(false);
        startB.setBackground(null);
        startB.setBorder(null);
        startB.setFont(new Font("Papyrus",Font.BOLD,40));
        startB.setForeground(Color.green);
        startB.setContentAreaFilled(false);
        startB.addActionListener(e -> {
            startB.setVisible(false);
            menuB.setVisible(false);
            scoreL.setVisible(true);
            titleL.setVisible(false);
            dc.setVisible(false);
            startGame();
        });

        menuB.setBounds(200,360,200,80);
        menuB.setFocusable(false);
        menuB.setBackground(null);
        menuB.setBorder(null);
        menuB.setFont(new Font("Papyrus",Font.BOLD,40));
        menuB.setForeground(Color.green);
        menuB.setContentAreaFilled(false);
        menuB.addActionListener(e -> {
            menuB.setVisible(false);
            startB.setVisible(false);
            gridBFalse.setVisible(true);
            gridBTrue.setVisible(true);
            backB.setVisible(true);
            scoreL.setVisible(false);
            overL.setVisible(false);
            gridO.setVisible(true);
            menuOpened = true;
        });

        gridBFalse.setVisible(false);
        gridBFalse.setBounds(220,200,200,100);
        gridBFalse.setFocusable(false);
        gridBFalse.setBackground(null);
        gridBFalse.setBorder(null);
        gridBFalse.setFont(new Font("Papyrus",Font.BOLD,30));
        gridBFalse.setForeground(Color.green);
        gridBFalse.setContentAreaFilled(false);
        gridBFalse.addActionListener(e -> {
            gridBFalse.setForeground(Color.blue);
            gridBTrue.setForeground(Color.green);
            gridEnabled = false;
        });

        gridBTrue.setVisible(false);
        gridBTrue.setBounds(350,200,200,100);
        gridBTrue.setFocusable(false);
        gridBTrue.setBackground(null);
        gridBTrue.setBorder(null);
        gridBTrue.setFont(new Font("Papyrus",Font.BOLD,30));
        gridBTrue.setForeground(Color.green);
        gridBTrue.setContentAreaFilled(false);
        gridBTrue.addActionListener(e -> {
            gridBTrue.setForeground(Color.blue);
            gridBFalse.setForeground(Color.green);
            gridEnabled = true;
        });

        backB.setVisible(false);
        backB.setBounds(0,500,200,100);
        backB.setFocusable(false);
        backB.setBackground(null);
        backB.setBorder(null);
        backB.setFont(new Font("Papyrus",Font.BOLD,30));
        backB.setForeground(Color.green);
        backB.setContentAreaFilled(false);
        backB.addActionListener(e -> {
            startB.setVisible(true);
            menuB.setVisible(true);
            gridBTrue.setVisible(false);
            gridBFalse.setVisible(false);
            backB.setVisible(false);
            gridO.setVisible(false);
        });

        /*

        tryAgainB.setVisible(false);
        tryAgainB.setBounds(0,500,200,100);
        tryAgainB.setFocusable(false);
        tryAgainB.setBackground(null);
        tryAgainB.setBorder(null);
        tryAgainB.setFont(new Font("Papyrus",Font.BOLD,20));
        tryAgainB.setForeground(Color.red);
        tryAgainB.setContentAreaFilled(false);
        tryAgainB.addActionListener(e -> {
            startB.setVisible(true);
            menuB.setVisible(true);
            tryAgainB.setVisible(false);
            overL.setVisible(false);
            titleL.setVisible(true);
            scoreL.setVisible(false);
            gameRunning = false;
            gameOver = false;
        });

         */

        scoreL.setVisible(false);
        scoreL.setForeground(Color.green);
        scoreL.setFont(new Font("Papyrus", Font.BOLD, 20));
        scoreL.setBounds(500,-120,300,300);

        overL.setVisible(false);
        overL.setForeground(Color.orange);
        overL.setFont(new Font("Papyrus", Font.BOLD, 60));
        overL.setBounds(140,100,400,400);

        titleL.setVisible(true);
        titleL.setIcon(new ImageIcon("src/main/titleL.png"));
        titleL.setBounds(125,20,400,200);

        tryAgainText.setVisible(false);
        tryAgainText.setForeground(Color.white);
        tryAgainText.setFont(new Font("Papyrus", Font.BOLD, 15));
        tryAgainText.setBounds(225,150,200,400);

        dc.setVisible(true);
        dc.setForeground(Color.green);
        dc.setFont(new Font("Papyrus", Font.BOLD, 15));
        dc.setBounds(350,380,300,400);

        gridO.setVisible(false);
        gridO.setForeground(Color.green);
        gridO.setFont(new Font("Papyrus", Font.BOLD, 30));
        gridO.setBounds(160,175,150,150);


    }

    public void startGame() {
        addApple();
        gameRunning = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(gridEnabled) {
            grid(g);
        }
        if(gameOver) { //boolean that sets to true when hitting something
            gameOver();
        }
        else {
            draw(g);
            generateSnake(g);
        }
    }

    public void grid(Graphics g) {
            for(int i = 0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }
    }

    public void draw(Graphics g) {
        if(gameRunning) {
            g.setColor(Color.orange);
            g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);
        }
    }

    public void move() {
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction) {
            case 'U' -> y[0] = y[0] - UNIT_SIZE;
            case 'D' -> y[0] = y[0] + UNIT_SIZE;
            case 'L' -> x[0] = x[0] - UNIT_SIZE;
            case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }
    }

    public void generateSnake(Graphics g) {
        for(int i = 0;i<bodyParts;i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                g.drawOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }

    public void addApple() {
        appleX = random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
    }

    public void appleCheck() {
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            scoreL.setText("Score: "+applesEaten);
            addApple();
        }
    }

    public void collisionsCheck() {
        //check if the head of the snake collides with the body
        for(int i = bodyParts;i>0;i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                gameOver = true;
                break;
            }
        }
        //check if head touches the border on the left
        if(x[0] < 0) {
            gameOver = true;
        }
        //check if head touches the border on the right
        if(x[0] > SCREEN_WIDTH) {
            gameOver = true;
        }
        //check if head touches the top border
        if(y[0] < 0) {
            gameOver = true;
        }
        //check if head touches the bottom border
        if(y[0] > SCREEN_HEIGHT) {
            gameOver = true;
        }
    }

    public void gameOver() {
        overL.setVisible(true);
        // tryAgainB.setVisible(true);
        tryAgainText.setVisible(true);
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameRunning) {
            move();
            appleCheck();
            collisionsCheck();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
