

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/*
 * Fenêtre principale de l'application. Elle gère aussi les menus.
 */
public class FenetreLogiciel extends JFrame implements ActionListener {

    // Le panel qui affiche l'image "source"
    private ImagePanel panelImageSource;
    // Le panel qui affiche l'image retouchée
    private ImagePanel panelImageRetouchee;
    private JMenuItem itemMenuOuvrir;
    private JMenuItem itemMenuNegatif;
    private JMenuItem itemMenuSauver;
    private JMenuItem itemMenuQuitter;
    private JMenuItem itemMenuRouge;
    private JMenuItem itemMenuVert;
    private JMenuItem itemMenuBleu;
    private JMenuItem itemMenuMorpho;
    private JMenuItem itemMenuErosion;
    private JMenuItem itemMenuDilatation;
    private JMenuItem itemMenuGradient;
    private JMenuItem itemMenuContourExterieur;
    private JMenuItem itemMenuContourInterieur;
    private JMenuItem itemMenuOuverture;
    private JMenuItem itemMenuFermeture;
    private JMenuItem itemMenuReset;

    public FenetreLogiciel() {

        // Définit un titre pour votre fenêtre
        this.setTitle("Mon logiciel de retouche d'images.");
        // Nous allons maintenant dire à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        //Rendre la fenêtre visible
        this.setVisible(true);
        // Terminer le processus lorsqu'on clique sur "Fermer"
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.panelImageSource = new ImagePanel();
        this.panelImageRetouchee = new ImagePanel();


        // Le panel général, qui contiendra le panel source et le panel image retouchée.
        JPanel panel2images = new JPanel();
        panel2images.setLayout(new GridLayout(1, 2));
        panel2images.add(this.panelImageSource);
        panel2images.add(this.panelImageRetouchee);

        this.add(panel2images);

        this.itemMenuOuvrir = new JMenuItem("Ouvrir");
        this.itemMenuNegatif = new JMenuItem("Image Negatif");
        this.itemMenuSauver = new JMenuItem("Enregistrer");
        this.itemMenuQuitter = new JMenuItem("Quitter");
        this.itemMenuRouge = new JMenuItem("Rouge");
        this.itemMenuVert = new JMenuItem("Vert");
        this.itemMenuBleu = new JMenuItem("Bleu");
        this.itemMenuDilatation = new JMenuItem("Dilatation");
        this.itemMenuErosion = new JMenuItem("Erosion");
        this.itemMenuGradient = new JMenuItem("Gradient");
        this.itemMenuContourInterieur = new JMenuItem("Contour Intérieur");
        this.itemMenuContourExterieur = new JMenuItem("Contour Extérieur");
        this.itemMenuOuverture = new JMenuItem("Ouverture");
        this.itemMenuFermeture = new JMenuItem("Fermeture");
        this.itemMenuReset = new JMenuItem("Reset");


        this.creerMenu();

        this.setSize(800, 400);
        this.setVisible(true);

    }

    public void creerMenu() {

        JMenuBar barreDesMenus = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        JMenu menuTraitement = new JMenu("Traitement");
        JMenu menuExtraire = new JMenu("Extraire canal");
        JMenu menuMorpho = new JMenu("Morphologie");


        itemMenuOuvrir.addActionListener(this);
        itemMenuNegatif.addActionListener(this);
        itemMenuSauver.addActionListener(this);
        itemMenuRouge.addActionListener(this);
        itemMenuVert.addActionListener(this);
        itemMenuBleu.addActionListener(this);
        itemMenuDilatation.addActionListener(this);
        itemMenuErosion.addActionListener(this);
        itemMenuGradient.addActionListener(this);
        itemMenuContourInterieur.addActionListener(this);
        itemMenuContourExterieur.addActionListener(this);
        itemMenuOuverture.addActionListener(this);
        itemMenuFermeture.addActionListener(this);
        itemMenuReset.addActionListener(this);
        menuFichier.add(itemMenuOuvrir);
        menuFichier.add(itemMenuSauver);
        menuFichier.add(itemMenuQuitter);
        menuTraitement.add(itemMenuNegatif);
        menuTraitement.add(menuExtraire);
        menuTraitement.add(menuMorpho);
        menuTraitement.add(itemMenuReset);
        menuExtraire.add(itemMenuRouge);
        menuExtraire.add(itemMenuVert);
        menuExtraire.add(itemMenuBleu);
        menuMorpho.add(itemMenuErosion);
        menuMorpho.add(itemMenuDilatation);
        menuMorpho.add(itemMenuGradient);
        menuMorpho.add(itemMenuContourInterieur);
        menuMorpho.add(itemMenuContourExterieur);
        menuMorpho.add(itemMenuOuverture);
        menuMorpho.add(itemMenuFermeture);
        this.setJMenuBar(barreDesMenus);
        barreDesMenus.add(menuFichier);
        barreDesMenus.add(menuTraitement);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.itemMenuOuvrir)) {
            JFileChooser selecteurDeFichier = new JFileChooser();
            selecteurDeFichier.setDialogTitle("Ouvrir");
            selecteurDeFichier.setApproveButtonText("Ouvrir");
            int retour = selecteurDeFichier.showOpenDialog(FenetreLogiciel.this);
            if (retour == JFileChooser.APPROVE_OPTION) {
                File fichier = selecteurDeFichier.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(fichier);
                    FenetreLogiciel.this.panelImageSource.setBufferedImage(image);
                    FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(image);
                } catch (IOException ex) {
                    System.out.println("erreur d'ouverture de l'image");
                }
                FenetreLogiciel.this.panelImageSource.repaint();
            }
        } else if (e.getSource().equals(this.itemMenuQuitter)) {
        } //si l'image est ouverte
        else if (panelImageSource.getBufferedImage() != null) {
            if (e.getSource().equals(this.itemMenuSauver)) {
                JFileChooser selecteurDeFichier = new JFileChooser();
                int retour = selecteurDeFichier.showOpenDialog(FenetreLogiciel.this);
                if (retour == JFileChooser.APPROVE_OPTION) {
                    File fichier = selecteurDeFichier.getSelectedFile();
                    BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                    Operations.stockImage(fichier, im);
                }
            } else if (e.getSource().equals(this.itemMenuNegatif)) {
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.negatif(panelImageRetouchee.getBufferedImage()));

            } else if (e.getSource().equals(this.itemMenuRouge)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.ExtraireCanal(im, 'r'));
            } else if (e.getSource().equals(this.itemMenuBleu)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.ExtraireCanal(im, 'v'));
            } else if (e.getSource().equals(this.itemMenuVert)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.ExtraireCanal(im, 'b'));
            } else if (e.getSource().equals(this.itemMenuDilatation)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.dilatation(im));
            } else if (e.getSource().equals(this.itemMenuErosion)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.erosion(im));
            } else if (e.getSource().equals(this.itemMenuGradient)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.gradient(im));
            } else if (e.getSource().equals(this.itemMenuContourInterieur)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.contourInterieur(im));
            } else if (e.getSource().equals(this.itemMenuContourExterieur)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.contourExterieur(im));
            } else if (e.getSource().equals(this.itemMenuFermeture)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.fermeture(im));
            } else if (e.getSource().equals(this.itemMenuOuverture)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(Operations.ouverture(im));
            } else if (e.getSource().equals(this.itemMenuReset)) {
                BufferedImage im = FenetreLogiciel.this.panelImageRetouchee.getBufferedImage();
                FenetreLogiciel.this.panelImageRetouchee.setBufferedImage(this.panelImageSource.getBufferedImage());
            }

        }
    }
}
