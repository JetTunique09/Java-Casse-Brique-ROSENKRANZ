package models;

import application.Fenetre;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Balle extends Sprite {

    protected int vitesseX = 5;
    protected int vitesseY = 5;
    protected int diametre = 50;

    public Balle(int x, int y, Color couleur, int diametre) {
        super(x, y , couleur);
        this.diametre = diametre;
    }

    public void deplacement(Barre barre, ArrayList<Brique> listeBriques) {

        if(x > Fenetre.LARGEUR - diametre || x < 0) {
            vitesseX = - vitesseX;
        }

        if (y < 0) {
            vitesseY = -vitesseY;
        }

        if(y > Fenetre.HAUTEUR - diametre) {
            vitesseY = - vitesseY;
        }

        if (checkCollision(barre)) {
            vitesseY = -vitesseY;
            y = barre.getY() - diametre;
        }

        Iterator<Brique> iterator = listeBriques.iterator();
        while (iterator.hasNext()) {
            Brique brique = iterator.next();
            if (checkCollision(brique)) {
                iterator.remove();
                vitesseY = -vitesseY;
                break;
            }
        }

        x += vitesseX;
        y += vitesseY;
    }

    private boolean checkCollision(Barre barre) {
        return x + diametre > barre.getX() && x < barre.getX() + barre.getLargeur() &&
                y + diametre > barre.getY() && y < barre.getY() + barre.getHauteur();
    }

    private boolean checkCollision(Brique brique) {
        return x + diametre > brique.getX() && x < brique.getX() + brique.getLargeur() &&
                y + diametre > brique.getY() && y < brique.getY() + brique.getHauteur();
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x, y, diametre, diametre);
    }
}