/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Graficos.PanelGrafico;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Angel Of Dark
 */
public class APrincipal {

    /**
     * @param args the command line arguments
     */
   
    
    public static void main(String[] args) {
        // TODO code application logic here
        
      

        
         PanelGrafico grafico = new PanelGrafico();
         
        
        grafico.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        grafico.setResizable(false);
        grafico.setVisible(true);
        grafico.setBounds(0, 0, 480, 500);
        grafico.setLocationRelativeTo(null);
        
      
    }
  
}
