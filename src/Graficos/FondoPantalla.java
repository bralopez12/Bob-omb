/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Angel Of Dark
 */
public class FondoPantalla extends JPanel {
    
    
    PanelDibujo dibujo;
    Image imagen;
    URL url;

    public FondoPantalla(String url) {
         this.url = getClass().getResource(url);
        this.imagen = new ImageIcon(this.url).getImage();
       
    }

    @Override
    protected void paintComponent(Graphics g) {
    
        g.drawImage(imagen, 0, 0, 480, 500, this);
        
        
    }
       
    
    
    

  

    
   
    
}
