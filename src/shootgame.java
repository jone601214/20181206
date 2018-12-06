import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class shootgame extends JFrame {
    private Container cp;
    private  int count =0;


    private JLabel jlbBackground=new JLabel();  // background
    private JLabel jlbParachute_left=new JLabel(); // parachute_left
    private JLabel jlbParachute_right=new JLabel(); // parachute_right
    private JLabel jlbMissle=new JLabel(); // missle
    private JLabel jlbsorceboard=new JLabel("記分板:");
    private JLabel jlbsorce=new JLabel("0");
    private boolean flag=false;
    private Random rand = new Random();
    private int x;
    private int y;

    private  Timer t1;
    private  Timer t2;

    // private ImageIcon Background=new ImageIcon("BlueSky.jpg");
    private ImageIcon Background=new ImageIcon("moutain.png");
    private ImageIcon imgparachute1=new ImageIcon("parachute_left.png");
    private ImageIcon imgparachute2=new ImageIcon("parachute_right.png");
    private ImageIcon missle=new ImageIcon("missle.png");

    private int targetX,targetY;
    private int origX,origY;
    private boolean isobselect=false;

    public shootgame() {
        super("跳傘");
        setSize(800,1000);
        Container con=getContentPane();
        con.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            }
        });
        this.setBounds(0,0,1000,800);
        cp=this.getContentPane();
        cp.setLayout(null);
        Image img1=imgparachute1.getImage();
        Image img11=img1.getScaledInstance(120,180,Image.SCALE_AREA_AVERAGING);
        imgparachute1.setImage(img11);
        jlbParachute_left.setIcon(imgparachute1);
        jlbParachute_left.setBounds(550,-200,269,187);
        cp.add(jlbParachute_left);

        Image img2=imgparachute2.getImage();
        Image img22=img2.getScaledInstance(120,180,Image.SCALE_AREA_AVERAGING);
        imgparachute2.setImage(img22);
        jlbParachute_right.setIcon(imgparachute2);

        Image img3=missle.getImage();
        Image img33=img3.getScaledInstance(15,60,Image.SCALE_AREA_AVERAGING);
        jlbMissle.setIcon(missle);
        missle.setImage(img33);
        jlbMissle.setBounds(350,800,30,60);
        cp.add(jlbMissle);

        Image imgBack=Background.getImage();
        //  Image imgBack1=imgBack.getScaledInstance(1000,800,Image.SCALE_AREA_AVERAGING);
        Background.setImage(imgBack);
        jlbBackground.setIcon(Background);
        jlbBackground.setBounds(0,650,1000,120);
        jlbsorceboard.setBounds(800,20,200,100);
        jlbsorceboard.setFont(new Font("標楷體", Font.BOLD, 25));
        jlbsorce.setBounds(900,20,200,100);
        jlbsorce.setFont(new Font("標楷體", Font.BOLD, 25));
        cp.add(jlbBackground);
        cp.add(jlbsorceboard);
        cp.add(jlbsorce);
        t1=new Timer(50, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jlbMissle.setLocation(jlbMissle.getX(),jlbMissle.getY()-5);
                if (jlbMissle.getY()<-60&&jlbParachute_left.getY()>800){
                    t1.stop();
                    flag=true;
                }
            }
        });
        t2=new Timer(50, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlbParachute_left.setLocation(jlbParachute_left.getX(),jlbParachute_left.getY()+5);
                if (jlbParachute_left.getY()>800&&jlbMissle.getY()<-60){
                    t2.stop();
                    flag=true;
                    count=count+20;
                    jlbsorce.setText(Integer.toString(count));
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getExtendedKeyCode());
                switch (e.getKeyCode()){
                    case 37:  // left
                        jlbParachute_left.setIcon(imgparachute1);
                        jlbParachute_left.setLocation(jlbParachute_left.getX()-5,jlbParachute_left.getY());
                        break;
                    case 39: // right
                        jlbParachute_left.setIcon(imgparachute2);
                        jlbParachute_left.setLocation(jlbParachute_left.getX()+5,jlbParachute_left.getY());
                        break;
                    case 90: // z
                        t1.start();
                        t2.start();
                        x=rand.nextInt(900)+1;
                        y=rand.nextInt(900)+1;
                        if (flag){
                            jlbMissle.setBounds(x,800,30,60);
                            jlbParachute_left.setBounds(y,-200,269,187);
                            flag=false;
                        }
                        break;
                    case 82:
                        jlbsorce.setText("0");
                        jlbParachute_left.setBounds(550,-200,269,187);
                        jlbMissle.setBounds(350,800,30,60);
                        t1.stop();
                        t2.stop();
                }

            }
        });

    }
}