package Entidades;

/**
 *
 * @author blopez
 */
public class Posicion {

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    private int x;
    private int y;

    /**
     * @return la posicion en x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return la posicion en y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}
