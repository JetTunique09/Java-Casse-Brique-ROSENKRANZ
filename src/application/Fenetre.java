package application;

import models.Balle;
import models.Barre;
import models.Sprite;
import models.Brique;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Fenetre extends Canvas implements KeyListener {

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 700;

    protected boolean toucheEspace = false;
    protected boolean gauche = false;
    protected boolean droite = false;

    ArrayList<Balle> listeBalles = new ArrayList<>();
    ArrayList<Sprite> listeSprites = new ArrayList<>();
    ArrayList<Brique> listeBriques = new ArrayList<>();
    Barre barre;

    Fenetre()  throws InterruptedException {

        JFrame fenetre = new JFrame();

        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0, 0, LARGEUR, HAUTEUR);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);

        fenetre.pack();
        fenetre.setSize(LARGEUR, HAUTEUR );
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.requestFocus();
        fenetre.addKeyListener(this);


        Container panneau = fenetre.getContentPane();
        panneau.add(this);

        fenetre.setVisible(true);
        this.createBufferStrategy(2);



        this.demarrer();
    }

    public void demarrer() throws InterruptedException {

        barre = new Barre();
        listeSprites.add(barre);

        Balle balle = new Balle(100, 200 , Color.PINK, 30);

        listeBalles.add(balle);
        listeSprites.add(balle);

        createBriques();

        while(true) {

            Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0,0,LARGEUR,HAUTEUR);

            //----- app -----

            // mouvement barre
            if (gauche) {
                barre.moveToGauche();
            }
            if (droite) {
                barre.moveToDroite();
            }

            // mouvement balle et collinsion
            for(Balle b : listeBalles) {
                b.deplacement(barre, listeBriques);
            }

            for(Sprite s : listeSprites) {
                s.dessiner(dessin);
            }

            for (Brique brique : listeBriques) {
                brique.dessiner(dessin);
            }

            if(toucheEspace) {
                listeBalles.add( new Balle(200, 400 , Color.BLUE, 50));
            }
            //---------------

            dessin.dispose();
            this.getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }

    }

    private void createBriques() {
        int rows = 5;
        int cols = 10;
        int briqueLargeur = LARGEUR / cols;
        int briqueHauteur = 50;

        //placement des briques
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Color couleur = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                Brique brique = new Brique(j * briqueLargeur, i * briqueHauteur, briqueLargeur, briqueHauteur, couleur);
                listeBriques.add(brique);
                listeSprites.add(brique);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            toucheEspace = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            gauche = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            droite = true;
        }
    }

    @Override
    public void  keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            toucheEspace = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            gauche = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            droite = false;
        }
    }
}