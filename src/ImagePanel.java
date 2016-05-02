

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/*
 * JPanel qui affiche une image.
 */
public class ImagePanel extends JPanel {

    // L'image affichée par le JPanel.
    BufferedImage bufferedImage;

    public ImagePanel() {

        /*File fichier = new File("milo.png");
        BufferedImage image = null;

        try {
            image = ImageIO.read(fichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBufferedImage(image);*/
    }

    public ImagePanel(final BufferedImage bf) {
        this.setBufferedImage(bf);

    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;

        // On appelle la méthode repaint() (cette méthode va appeler notre méthode paintComponent()) 
        // afin de mettre à jour l'affichage avec la nouvel image.
        this.repaint();
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

    /*
     * Cette méthode est automatiquement appelée par Java chaque fois que le JPanel doit être 
     * affiché ou réaffiché (par exemple lorsqu'on change sa taille à l'écran).
     * Elle est aussi appelée chaque fois qu'on appelle la méthode repaint() sur le JPanel.
     */
    @Override
    public void paintComponent(final Graphics g) {

        // Le code ci-dessous affiche l'image dans le JPanel.
        if (bufferedImage != null) {
            g.drawImage(bufferedImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }



    }
}
