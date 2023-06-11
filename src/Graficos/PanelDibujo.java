/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Angel Of Dark
 */
public class PanelDibujo extends JPanel {

    private Image Imagen;
    private URL url;
    private URL urlfondo = getClass().getResource("/images/fondo.png");

    private int height;
    private int width;

    PanelGrafico g;

    private int posicionX;
    private int posicionY;
    private ArrayList<Personaje> bombas;
    Random R = new Random();
    AudioClip clic;
    AudioClip error;

    public PanelDibujo(ArrayList<Personaje> bombas) {
        this.bombas = bombas;
        clic = java.applet.Applet.newAudioClip(getClass().getResource("/images/bombitas.wav"));

        controlMouse();
    }

    public void controlMouse() {

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                boolean bandera = false;

                for (int i = 0; i < bombas.size(); i++) {
                    final Personaje im = bombas.get(i);
                    if (im.isInside(e.getX(), e.getY())) {

                        PanelGrafico.Puntajemas1();
                        PanelGrafico.Subirvelocidad(1);
                        clic.play();

                        bombas.remove(i);
                        bandera = true;
                        break;
                    }
                }
                if (PanelGrafico.isrun()) {
                    if (bandera == false) {
                        PanelGrafico.banderaCambiodeRitmo = 0;
                        Personaje img = null;
                        error = java.applet.Applet.newAudioClip(getClass().getResource("/images/error.wav"));
                        error.play();
                        img = new Personaje(e.getX() - 25, e.getY() - 25, "/images/bombred.gif");
                        PanelGrafico.Subirvelocidad(3);
                        bombas.add(img);
                    }

                    repaint();

                }
            }
        });

    }

    public PanelDibujo(String url, int height, int width) {

        this.url = this.getClass().getResource(url);
        this.Imagen = new ImageIcon(this.url).getImage();
        this.height = height;
        this.width = width;
        this.posicionX = 0;
        this.posicionY = 0;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.

        Image img = new ImageIcon(urlfondo).getImage();
        g.drawImage(img, 0, 0, 480, 420, this);

        for (Personaje i : bombas) {
            i.graficar(g);
        }
    }

    public void setUrlfondo(URL urlfondo) {
        this.urlfondo = urlfondo;
    }

}
