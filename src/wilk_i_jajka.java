
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class wilk_i_jajka extends JLabel implements ActionListener, KeyListener {
    int i = 2000;
    final int PANEL_WIDTH = 800;
    final int PANEL_HEIGHT = 800;
    Image backgroundImage;
    Timer timer;
    Timer timer2;


    int xVelocity = 1;
    int yVelocity = 1;

    int x = 0;
    int y = 0;

    int x_bundle = 180;
    int y_bundle = 180;


    int x_sword = 300;

    int y_sword = 300;

    public static Image egg;
    public static Image bundle;
    public static Image bundle_filled;

    public static Image wolf;

    public static Image chicken;
    public static Image background;
    int score = 0;
    int bundle_animation = 0;
    int change_speed = 0;
    int change_speed_timer=0;


    public wilk_i_jajka() throws IOException{
        egg = ImageIO.read(new File("egg.png")).getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        bundle = ImageIO.read(new File("bundle.png")).getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        bundle_filled = ImageIO.read(new File("bundle_filled.png")).getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        wolf = ImageIO.read(new File("wolf.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        chicken = ImageIO.read(new File("chicken.png")).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        background = ImageIO.read(new File("background.jpg"));
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.CYAN);
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(this);
        frame.addKeyListener(this);
        timer = new Timer(10, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D wolf1 = (Graphics2D) g;
        Graphics2D egg1 = (Graphics2D) g;
        Graphics2D chicken1 = (Graphics2D) g;
        Graphics2D chicken2 = (Graphics2D) g;
        Graphics2D chicken3 = (Graphics2D) g;
        Graphics2D chicken4 = (Graphics2D) g;
        Graphics2D bundle1 = (Graphics2D) g;
        Graphics2D background1 = (Graphics2D) g;
        background1.drawImage(background, 0, 0, null);
        wolf1.drawImage(wolf, 290, 280, null);
        egg1.drawImage(egg, x, y, null);
        chicken1.drawImage(chicken, 20, 20, null);
        chicken2.drawImage(chicken, 20, 600, null);
        chicken3.drawImage(chicken, 620, 20, null);
        chicken4.drawImage(chicken, 620, 600, null);
        bundle1.drawImage(bundle, x_bundle, y_bundle, null);
        if(bundle_animation!=0){
            bundle_animation--;
        }
        if(bundle_animation==1){
            try {
                bundle = ImageIO.read(new File("bundle.png")).getScaledInstance(80, 80, Image.SCALE_DEFAULT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(change_speed_timer!=0 && change_speed_timer%10==0){
            change_speed++;
            change_speed_timer++;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        x = x + xVelocity;
        y = y + yVelocity;

        if(x_bundle>= x-40 && x_bundle<=x+40 && y_bundle>= y-40 && y_bundle<=y+40) {
            change_speed_timer++;
            Random random = new Random();
            try {
                bundle = ImageIO.read(new File("bundle_filled.png")).getScaledInstance(80, 80, Image.SCALE_DEFAULT);
                bundle_animation = 40;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            int where_egg;
            where_egg = random.nextInt(5);
            if (where_egg == 0) {
                x = 20;
                y = 20;
                xVelocity = 1 + change_speed;
                yVelocity = 1 + change_speed;
                score++;
                System.out.println("Score: "+score);
            } else if (where_egg == 1) {
                x = 20;
                y = 600;
                xVelocity = 1 + change_speed;
                yVelocity = -1 - change_speed;
               score++;
                System.out.println("Score: "+score);
            } else if (where_egg == 2) {
                x = 620;
                y = 20;
                xVelocity = -1 - change_speed;
                yVelocity = 1 + change_speed;
                score++;
                System.out.println("Score: "+score);
            } else if (where_egg == 3) {
                x = 620;
                y = 600;
                xVelocity = -1 - change_speed;
                yVelocity = -1 - change_speed;
                score++;
                System.out.println("Score: "+score);
            }

        }

        if((x>= 290 && x<=500) && (y>=280&& y<=380)){
            System.out.println("You dead!!!!");
            System.out.println("Final score: " + score);
            timer.stop();
            System. exit(0);
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case 49:
                x_bundle = 180;
                y_bundle = 180;
                break;
            case 50:
                x_bundle = 480;
                y_bundle = 180;
                break;
            case 51:
                x_bundle = 180;
                y_bundle = 480;
                break;
            case 52:
                x_bundle = 480;
                y_bundle = 480;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}