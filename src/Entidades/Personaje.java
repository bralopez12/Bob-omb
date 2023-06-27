/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

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
    
    private final int anchoPersonaje = 50;
    private final int altoPersonaje = 50;

    
    private Image Imagen;
    private URL url;
    private int NivelPersonaje;
    private Posicion posicion;

    public Personaje(int x, int y,String URL) {
        posicion = new Posicion(x,y);
        this.url = getClass().getResource(URL);
        this.Imagen = new ImageIcon(this.url).getImage();
    }
    
       public Personaje(int WidghtContenedor,String URL) {
        Random r = new Random();
        this.NivelPersonaje = r.nextInt(0, 5);
        posicion = new Posicion(r.nextInt(0,400),WidghtContenedor);
        
        this.url = getClass().getResource(URL);
        this.Imagen = new ImageIcon(this.url).getImage();
    }
    

    public int ObtenerPosicionX() {
        return posicion.getX();
    }

    public void AsignarPosicionEnX(int x) {
        this.posicion.setX(x);
    }

    public int ObtenerPosicionY() {
        return posicion.getY();
    }

    public void AsignarPosicionEnY(int y) {
        this.posicion.setY(y);
    }
    
    
     public void avanzarY() {
        this.posicion.setY(posicion.getY() - 3);
    }
     
     public boolean isInside(int x, int y) {
        return x >= this.posicion.getX() && x <= this.posicion.getX() + anchoPersonaje && y >= this.posicion.getY() && y <= this.posicion.getY() + altoPersonaje;
    }
    
    public void graficar(Graphics g){
       g.drawImage(Imagen, posicion.getX(),posicion.getY(), anchoPersonaje, altoPersonaje, null);
    }
    
    
    
}
