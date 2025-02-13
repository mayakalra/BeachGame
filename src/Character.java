import java.awt.*;

public class Character {

    // declare variables
    public String name;
    public Image pic;
    public int xpos;
    public int ypos;
    public int dx = 1;
    public int dy = 1;
    public int width = 30;  // Default size of character
    public int height = 30;  // Default size of character
    public boolean isAlive;
    public Rectangle hitbox;

    // default constructor
    public Character() {
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    // constructor with parameters
    public Character(int paramXpos, int paramYpos, int paramDx, int paramDy, int paramWidth, int paramHeight) {
        xpos = paramXpos;
        ypos = paramYpos;
        dx = paramDx;
        dy = paramDy;
        width = paramWidth;
        height = paramHeight;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    // method to move the character and bounce
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos >= 600 - height || ypos <= 0) {
            dy = -dy;  // Reverse direction if hitting top or bottom
        }
        if (xpos >= 1000 - width || xpos <= 0) {
            dx = -dx;  // Reverse direction if hitting left or right
        }

        hitbox = new Rectangle(xpos, ypos, width, height);  // Update hitbox
    }

    // method to wrap the character
    public void wrap() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos >= height || ypos <= 0) {
            dy = -dy;  // Reverse vertical direction if hitting top/bottom
        }
        if (xpos >= 1000 && dx > 0) {
            xpos = -width;  // Wrap to the left side if reaching the right edge
        }
        if(xpos <= 0 && dx < 0) {
            xpos = 1000; // Wrap to the right side if reaching the left edge
        }

        hitbox = new Rectangle(xpos, ypos, width, height);  // Update hitbox
    }

    public void printInfo() {
        System.out.println(name + " is at (" + xpos + ", " + ypos + ")");
    }
}