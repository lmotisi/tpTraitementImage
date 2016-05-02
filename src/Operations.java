

/*
 * =====================================================================================
 *
 * Nom du fichier:  Operations.java
 *
 *    Description:  Permet de faire des opérations sur des BufferedImage, toutes les mthodes sont en static
 *
 *        Version:  4.0
 *        Créé le:  22/09/2009 09:47:08
 *     Modifié le:  13/10/2009
 *      Compileur:  javac
 *
 *         Auteur:  @Mellouli
 *
 * =====================================================================================
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Operations {

    /**
     * Méthode qui permet de stocker une image Depuis une variabe de type
     * BufferedImage *
     */
    public static void stockImage(File file, BufferedImage im) {
        String nom = file.getName();
        BufferedImage buffer = new BufferedImage(im.getWidth(), im.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffer.createGraphics();
        g.drawImage(im, 0, 0, im.getWidth(), im.getHeight(), null);
        String format_image = nom.substring(nom.lastIndexOf('.') + 1);
        try {
            boolean sauv = ImageIO.write(buffer, format_image, file);
            if (!sauv) {
                //JOptionPane.showMessageDialog(new JFrame(),
                System.out.println("Echec de sauvegarde du fichier");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui permet de charger une image dans une variabe de type
     * BufferedImage *
     */
    public static BufferedImage chargeImage(File file) {
        BufferedImage img = null;
        try {
            if (!file.exists() && !file.canRead()) {
                throw new RuntimeException("can not read file" + file);
            }
            img = ImageIO.read(file);
            System.out.println("H=" + img.getHeight() + "--W=" + img.getWidth()
                    + "--Espace Couleurs" + img.getColorModel());
        } catch (IOException e) {
            throw new RuntimeException("failed to read image file", e);
        }
        return img;
    }

    /**
     * Méthode qui permet de calculer les voisins d'un point à partir d'une
     * arrayList de point à partir du point central (0,0) *
     */
    /** Méthode qui transforme une image en son négatif * */
    public static BufferedImage negatif(BufferedImage in) {
        // Methode qui prend une BufferedImage en paramètre et retourne son
        // négatif
        int largeur, hauteur;
        int i, j;
        largeur = in.getWidth();
        hauteur = in.getHeight();
        BufferedImage negatif = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);
        for (i = 0; i < hauteur; i++) {
            for (j = 0; j < largeur; j++) {
                negatif.setRGB(j, i, 255 - in.getRGB(j, i));
            }
        }

        return negatif;
    }

    public static void AffichageCouleurPixel(BufferedImage image) {
        int i, j;
        Color c;
        for (i = 0; i < image.getHeight(); i++) {
            for (j = 0; j < image.getWidth(); j++) {
                c = new Color(image.getRGB(j, i));
                System.out.println("pixel(" + i + "," + j + ") : rouge :" + c.getRed() + ", vert :" + c.getGreen() + ", bleu :" + c.getBlue());
            }
        }
    }

    public static BufferedImage ExtraireCanal(BufferedImage img, char canal) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage imgres = new BufferedImage(w, h, img.getType());
        int i, j;
        for (i = 0; i < h; i++) {
            for (j = 0; j < w; j++) {
                Color couleur = new Color(img.getRGB(j, i));
                int rouge = couleur.getRed();
                int vert = couleur.getGreen();
                int bleu = couleur.getBlue();

                switch (canal) {
                    case 'r':
                        imgres.setRGB(j, i, new Color(rouge, 0, 0).getRGB());
                        break;
                    case 'v':
                        imgres.setRGB(j, i, new Color(0, vert, 0).getRGB());
                        break;
                    case 'b':
                        imgres.setRGB(j, i, new Color(0, 0, bleu).getRGB());
                }
            }
        }
        return imgres;
    }

    public static int CouleurNB(BufferedImage im, int x, int y) {
        int RGB = im.getRGB(x, y);
        Color c = new Color(RGB);
        return c.getRed();
    }

    public static BufferedImage erosion2(BufferedImage im, ArrayList<Point> elm) {

        int w = im.getWidth();
        int h = im.getHeight();
        BufferedImage imageErodee = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < elm.size(); k++) {
                    if (CouleurNB(im, (int) (i + elm.get(k).getX()), (int) (j + elm.get(k).getY())) == 255) {
                        imageErodee.setRGB(i, j, new Color(255, 255, 255).getRGB());
                    } else {
                        imageErodee.setRGB(i, j, new Color(0, 0, 0).getRGB());
                    }
                }
            }
        }
        return imageErodee;

    }

    public static BufferedImage erosion(BufferedImage im) {

        int w = im.getWidth();
        int h = im.getHeight();
        BufferedImage imageErodee = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for (int i = 1; i < w - 1; i++) {
            for (int j = 1; j < h - 1; j++) {


                if ((CouleurNB(im, i, j) == 255) && (CouleurNB(im, i - 1, j) == 255) && (CouleurNB(im, i + 1, j) == 255) && (CouleurNB(im, i, j - 1) == 255) && (CouleurNB(im, i, j + 1) == 255)) {
                    imageErodee.setRGB(i, j, new Color(255, 255, 255).getRGB());
                } else {
                    imageErodee.setRGB(i, j, new Color(0, 0, 0).getRGB());
                }
            }
        }
        return imageErodee;

    }

    public static BufferedImage dilatation(BufferedImage im) {

        int w = im.getWidth();
        int h = im.getHeight();
        BufferedImage imageDilatee = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for (int i = 1; i < w - 1; i++) {
            for (int j = 1; j < h - 1; j++) {


                if ((CouleurNB(im, i, j) == 255) || (CouleurNB(im, i - 1, j) == 255) || (CouleurNB(im, i + 1, j) == 255) || (CouleurNB(im, i, j - 1) == 255) || (CouleurNB(im, i, j + 1) == 255)) {
                    imageDilatee.setRGB(i, j, new Color(255, 255, 255).getRGB());
                } else {
                    imageDilatee.setRGB(i, j, new Color(0, 0, 0).getRGB());
                }
            }
        }
        return imageDilatee;

    }

    public static BufferedImage imgDiff(BufferedImage im1, BufferedImage im2) {

        int g;
        int w = im1.getWidth();
        int h = im1.getHeight();

        BufferedImage imgDiff=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        for(int i=0; i<w; i++) {
            for(int j=0;j<h;j++) {
                g = CouleurNB(im1,i,j)-CouleurNB(im2,i,j);
                imgDiff.setRGB(i,j, new Color(g,g,g).getRGB());
            }
        }
        return imgDiff;
    }

    public static BufferedImage gradient(BufferedImage im) {
        return imgDiff(dilatation(im),erosion(im));

    }

    public static BufferedImage contourInterieur(BufferedImage im) {
        return imgDiff(im,erosion(im));

    }

    public static BufferedImage contourExterieur(BufferedImage im) {
        return imgDiff(dilatation(im),im);

    }


    public static BufferedImage fermeture(BufferedImage im) {
        return (dilatation(erosion(im)));
    }

    public static BufferedImage ouverture(BufferedImage im) {
        return (erosion(dilatation(im)));
    }

}
