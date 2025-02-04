//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.Tool;

//*******************************************************************************

public class BasicGameApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too
    Character spongebob;
    Character starfish;
    Character octopus;
    Character Shark;
    Character Seahorse;

    boolean spongebobVsOctopus = false;

    Image backgroundPic;

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 600;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    // Main method definition
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();

        backgroundPic = Toolkit.getDefaultToolkit().getImage("BeachWallpaper.png");

        //variable and objects
        //create (construct) the objects needed for the game
        spongebob = new Character(600, 400, 1, 0, 100, 150);
        spongebob.name = "spongebob";
        spongebob.pic = Toolkit.getDefaultToolkit().getImage("spongebob.png");

        starfish = new Character(300, 200, 1, 1, 150, 250);
        starfish.name = "starfish";
        starfish.pic = Toolkit.getDefaultToolkit().getImage("starfish.png");

        octopus = new Character(50, 100, 1, 1, 200,200);
        octopus.name = "octopus";
        octopus.pic = Toolkit.getDefaultToolkit().getImage("octopus.png");

        Shark = new Character(20,100,1,0,100,100);
        Shark.name = "shark";
        Shark.pic = Toolkit.getDefaultToolkit().getImage("shark.png");

        Seahorse = new Character(800,40,4,4,100,100);
        Seahorse.name = "seahorse";
        Seahorse.pic = Toolkit.getDefaultToolkit().getImage("seahorse.png");

    } // end BasicGameApp constructor

    //*******************************************************************************
//User Method Section
//
// put your code to do things here.
    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(5); // sleep for 5 ms
        }
    }

    public void moveThings() {
        //call the move() code for each object
        spongebob.wrap();
        //spongebob.printInfo();

        starfish.wrap();
        //starfish.printInfo();

        octopus.move();
        //octopus.printInfo();

        Shark.move();
        //Shark.printInfo();

        Seahorse.move();
        //Seahorse.printInfo();

        if(spongebob.hitbox.intersects(octopus.hitbox) && spongebobVsOctopus == false) {
            spongebobVsOctopus = true;

            System.out.println("crash");
            octopus.dx = -octopus.dx;
            octopus.dy = -octopus.dy;
            spongebob.dx = -spongebob.dx;
            spongebob.dy = -spongebob.dx;

            spongebob.width = spongebob.width + 100;
            spongebob.height = spongebob.height + 100;
        }
        if(starfish.hitbox.intersects(Shark.hitbox)) {
            System.out.println("crash");
            Shark.dx = -Shark.dx;
            Shark.dy = -Shark.dy;
            starfish.dx = -starfish.dx;
            starfish.dy = -starfish.dx;
        }
        if(Seahorse.hitbox.intersects(Shark.hitbox)) {
            System.out.println("crash");
            Shark.dx = -Shark.dx;
            Shark.dy = -Shark.dy;
            Seahorse.dx = -Seahorse.dx;
            Seahorse.dy = -Seahorse.dy;
        }
    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.fillRect(0,0, WIDTH, HEIGHT);

        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);

        //draws the images

        g.drawImage(spongebob.pic, spongebob.xpos, spongebob.ypos, spongebob.width, spongebob.height, null);
        g.drawImage(starfish.pic, starfish.xpos, starfish.ypos, starfish.width,starfish.height, null);
        g.drawImage(octopus.pic, octopus.xpos, octopus.ypos, octopus.width, octopus.height, null);
        g.drawImage(Shark.pic, Shark.xpos, Shark.ypos, Shark.width, Shark.height, null);
        g.drawImage(Seahorse.pic, Seahorse.xpos, Seahorse.ypos, Seahorse.width, Seahorse.height, null);

//        g.drawRect(spongebob.hitbox.x, spongebob.hitbox.y, spongebob.hitbox.width, spongebob.hitbox.height);
//        g.drawRect(starfish.hitbox.x, starfish.hitbox.y, starfish.hitbox.width, starfish.hitbox.height);
//        g.drawRect(octopus.hitbox.x, octopus.hitbox.y, octopus.hitbox.width, octopus.hitbox.height);
//        g.drawRect(Shark.hitbox.x, Shark.hitbox.y, Shark.hitbox.width, Shark.hitbox.height);
//        g.drawRect(Seahorse.hitbox.x, Seahorse.hitbox.y, Seahorse.hitbox.width, Seahorse.hitbox.height);

        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }
}