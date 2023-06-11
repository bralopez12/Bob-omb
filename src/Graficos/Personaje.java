/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Angel Of Dark
 */
public class Personaje {
    
     private int x;
    private int y;
    
    private Image Imagen;
    private URL url;

    public Personaje(int x, int y,String URL) {
        this.x = x;
        this.y = y;
        
        this.url = getClass().getResource(URL);
        this.Imagen = new ImageIcon(this.url).getImage();
    }
    
       public Personaje(int WidghtContenedor,String URL) {
        Random r = new Random();
        this.x = r.nextInt(0,400);
        this.y = WidghtContenedor;
        
        this.url = getClass().getResource(URL);
        this.Imagen = new ImageIcon(this.url).getImage();
    }
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
     public void avanzarY() {
        y -= 1;
    }
     
     public boolean isInside(int x, int y) {
        return x >= this.x && x <= this.x + 50 && y >= this.y && y <= this.y + 50;
    }
    
    public void graficar(Graphics g){
       g.drawImage(Imagen, x,y, 50, 50, null);

    }
    
    
    
}
