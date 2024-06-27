package models;

import application.Fenetre;

import java.awt.*;

public class Barre extends Sprite{

    protected int largeur;
    protected int hauteur;
    private int vitesse = 10;


    public Barre(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public Barre() {
        super(Fenetre.LARGEUR / 2 - 75, Fenetre.HAUTEUR - 100, Color.BLUE);
        this.largeur = 150;
        this.hauteur = 20;
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x,y,largeur,hauteur);
    }

    public void moveToGauche() {
        x -= vitesse;
        if (x < 0) {
            x = 0;
        }
    }
    public void moveToDroite() {
        x += vitesse;
        if (x + largeur > Fenetre.LARGEUR) {
            x = Fenetre.LARGEUR - largeur;
        }
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}