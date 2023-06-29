/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import Entidades.Personaje;
import Sound.SoundsControllers.SoundsManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Angel Of Dark
 */
public class PanelGrafico extends JFrame implements Runnable {

    FondoPantalla fondo;
    
    PanelDibujo panel;

    private ArrayList<Personaje> bombas;

    private static Thread hilo1;
   
    private JButton btniniciar;
    
    public JLabel puntaje;

    public JMenuBar menu;
    
    SoundsManager musicaPrincipal;

    Random R;

    public PanelGrafico() {
        setTitle("Bob-omb");
        setIconImage(new ImageIcon(getClass().getResource("/images/bomb.png")).getImage());

        musicaPrincipal = new SoundsManager(); 
        musicaPrincipal.Reproducir("/Sound/Backgroundgame.wav",-1);
        initMenuBar();
        initBombas();
        PanelDeControl();
        R = new Random(100);

    }

    public void PanelDeControl() {

        btniniciar = new JButton("JUGAR");
        add(btniniciar, BorderLayout.SOUTH);

        puntaje = new JLabel("Puntaje: 0");
        puntaje.setBounds(50, 50, 50, 50);
        add(puntaje, BorderLayout.NORTH);

        btniniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btniniciar.getText() == "JUGAR") {
                    iniciar();
                } else {
                    Reiniciar();
                }

            }
        });
    }

    private void initMenuBar() {

        menu = new JMenuBar();
        Image sinsonido = new ImageIcon(getClass().getResource("/images/sins.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Image reiniciar = new ImageIcon(getClass().getResource("/images/reiniciar.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Image salir = new ImageIcon(getClass().getResource("/images/exit1.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon reset = new ImageIcon(reiniciar);
        ImageIcon nosound = new ImageIcon(sinsonido);
        ImageIcon exit = new ImageIcon(salir);

        JMenu file = new JMenu("Archivo");
        JMenu file2 = new JMenu("Ayuda");

        file.setMnemonic(KeyEvent.VK_A);

        JMenuItem eMenuItem1 = new JMenuItem("Reiniciar Juego", reset);
        JMenuItem eMenuItem2 = new JMenuItem("Silenciar Música", nosound);
        JMenuItem eMenuItem = new JMenuItem("Salir", exit);
        
        JMenuItem eMenuAyuda1 = new JMenuItem("Acerca del Juego",1);
        JMenuItem eMenuAyuda2 = new JMenuItem("Ayuda e Instrucciones",1);



        eMenuItem1.setMnemonic(KeyEvent.VK_R);
        
        eMenuAyuda1.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ImageIcon udem = new ImageIcon(getClass().getResource("/images/udem.png"));
                
                JOptionPane.showMessageDialog(PanelGrafico.this, "INGENIERIA DE SISTEMAS"
                        + "\nEstructuras de datos 2016-1"
                        + "\n\nEstudiantes:\n"
                        + "- Brayan López Foronda\n"
                        + "- Edgar Duvan  ", "Acerca de..", HEIGHT, udem);
            }
        });
        
        eMenuAyuda2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(PanelGrafico.this, "¡ Bob la bomba y fantasma quiera destruir tu hogar\n"
                        + ",no los dejes llegar hasta la entrada !"
                        + "\n\nInstrucciones:\n"
                        + "- Debes explotar las bombas dando click sobre ellas\n"
                        + "  pero ten mucho cuidado donde das click sino aparecerá\n"
                        + "  un fantasma que quiere acabar con tu hogar\n\n"
                        + "- Explotar 1 bomba = 1 punto y 1 nivel de velocidad \n"
                        + "- Aproximadamente cada 45 bombas explotadas se te \n"
                        + "  reducira la velociadad a 5, si aparece un fantasma tardara mas esta ayuda\n"
                        + "- Aparecer un fantasma = 3 niveles de velocidad\n"
                        + "- Matar un fantasma = 1 punto y 1 nivel de velocidad\n"
                        + "- La Velocidad maxima es de 50\n\n"
                        + "¡NO LOS DEJES ENTRAR!");
            }
        });

        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });

        eMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(musicaPrincipal.IsRunning())
                {
                    musicaPrincipal.Stop();
                }else
                {
                    musicaPrincipal.Reproducir();
                }
            }
        });

        eMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Reiniciar();

            }
        });

        file.add(eMenuItem1);
        file.add(eMenuItem2);
        file.add(eMenuItem);
        file2.add(eMenuAyuda1);
                file2.add(eMenuAyuda2);


        menu.add(file);
        menu.add(file2);
        setJMenuBar(menu);

    }

    public void initBombas() {
        JPanel p = new JPanel(new BorderLayout());

        bombas = new ArrayList<>();
        panel = new PanelDibujo(bombas);
        hilo1 = null;
        p.setBounds(20, 50, 2, 2);
        p.add(panel, BorderLayout.CENTER);

        add(p);

    }

    public void initFondo() {

        fondo = new FondoPantalla("/images/fondo.png");

        Container contenedor = getContentPane();

        contenedor.add(fondo);

    }

    public void iniciar() {

        if (hilo1 == null) {

            hilo1 = new Thread(this);

            btniniciar.setEnabled(false);
            hilo1.start();
        } else {
            hilo1.interrupt();

            btniniciar.setEnabled(true);
            btniniciar.setText("Reiniciar");
            hilo1 = null;
        }

    }

    public void CambiarFondo(String url) {
        panel.setUrlfondo(getClass().getResource(url));
        repaint();
    }

    public void Reiniciar() {
        puntos = 0;
        velocidad = 50;
        velocidadlabel = 0;
        btniniciar.setEnabled(false);
        CambiarFondo("/images/fondo.png");
        bombas.clear();
        iniciar();
    }

    public static int puntos = 0;

    public static void Puntajemas1() {
        puntos++;

    }

    public static int velocidad = 50;
    public static int velocidadlabel = 0;

    public static void Subirvelocidad(int milisegundos) {

        if (velocidad - milisegundos < 1) {
            velocidad = 1;
            velocidadlabel = 50;
        } else {
            velocidad -= milisegundos;
            velocidadlabel += milisegundos;
        }

    }

    private int bandera = 20;
    public static int banderaCambiodeRitmo = 0;
    
    public static boolean isrun(){
        return hilo1 != null;
    }
    @Override
    public void run() {

        while (true) {

            try {

                puntaje.setText("Puntaje: " + puntos + "    VELOCIDAD: " + velocidadlabel);

                for (Personaje b : bombas) {
                    if (b.ObtenerPosicionY() < 10) {
                        iniciar();
                        musicaPrincipal.Stop();
                        SoundsManager sonidoInstantaneo = new SoundsManager(); 
                        sonidoInstantaneo.Reproducir("/Sound/gameover.wav",0);
                        CambiarFondo("/images/boom.png");
                        JOptionPane.showMessageDialog(this, "GAME OVER\n\n"
                                + "Tu "+ puntaje.getText());

                        return;
                    }
                }

                for (Personaje r : bombas) {
                    if(!r.isEnMovimiento()){
                        r.avanzarY();
                    }
                }
                
                if(banderaCambiodeRitmo == 60){
                    System.out.println("AYUDA!");
                    banderaCambiodeRitmo = 0;
                    velocidad = 45;
                    velocidadlabel = 5;
                }

                if (bandera == 60) {
                    banderaCambiodeRitmo++;
                    int n = 0;

                    n = R.nextInt(6);

                    System.out.println(n);
                    Personaje img = new Personaje(getHeight() - 60, "/images/bombgif.gif");
        
                    bandera = 0;
                    bombas.add(img);
                } else {
                    bandera++;
                }
               

                repaint();
                Thread.sleep(velocidad);

            } catch (InterruptedException ex) {
                Logger.getLogger(PanelGrafico.class.getName()).log(Level.SEVERE, null, ex);
                hilo1 = new Thread(this);
            }

        }

    }

}
