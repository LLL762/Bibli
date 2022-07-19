package fr.afpa.outils;

import fr.afpa.entites.Auteur;
import fr.afpa.entites.Livre;
import fr.afpa.entites.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The type Utile.
 */
public class Utile {

    /**
     * Exit app.
     *
     * @param message the message
     */
    public static void exitApp(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter ?");
        alert.setHeaderText(message);
        alert.setContentText("Êtes-vous sur de vouloir quitter l'application");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }

    }

    /**
     * Lire bib array list.
     *
     * @return the array list
     */
    public static ArrayList<String> lireBib() {
        ArrayList<String> lstBib = new ArrayList<String>();
        lstBib.add("Toutes les Bibliotèques");
        lstBib.add("Bibliotèques de Marmusot");
        lstBib.add("Bibliotèques à la con");
        return lstBib;
    }

    /**
     * Lire livre dans le fichier csv.
     * lit le fichier csv contenant les livres et renvoie un ObservableList de tous les livres
     *
     * @return the observable list
     */
    public static ObservableList<Livre> lireLivre() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/csv/lstLivre.csv");
            LineNumberReader lineNumberReader
                    = new LineNumberReader(fileReader);
            String ligneLue;
            String isbnLivre;
            String codTheme;
            String titreLivre;
            Auteur auteur;
            int nbExemplaire;
            int nbEmprunt;

            Livre livre;
            ArrayList<Livre> listLivre = new ArrayList<>();
            do {
                ligneLue = lineNumberReader.readLine();
                if (ligneLue != null) {
                    isbnLivre = ligneLue.substring(0, ligneLue.indexOf(","));
                    ligneLue = ligneLue.substring(ligneLue.indexOf(",") + 1);
                    System.out.println(isbnLivre);

                    titreLivre = ligneLue.substring(0, ligneLue.indexOf(","));
                    ligneLue = ligneLue.substring(ligneLue.indexOf(",") + 1);
                    System.out.println(titreLivre);

                    auteur = new Auteur(ligneLue.substring(0, ligneLue.indexOf(",")));
                    ligneLue = ligneLue.substring(ligneLue.indexOf(",") + 1);
                    System.out.println(auteur);

                    codTheme = ligneLue.substring(0, ligneLue.indexOf(","));
                    ligneLue = ligneLue.substring(ligneLue.indexOf(",") + 1);
                    System.out.println(codTheme);

                    nbExemplaire = Integer.parseInt(ligneLue.substring(0, ligneLue.indexOf(",")));
                    ligneLue = ligneLue.substring(ligneLue.indexOf(",") + 1);
                    System.out.println(nbExemplaire);

                    nbEmprunt = Integer.parseInt(ligneLue);
                    System.out.println(nbEmprunt);

                    livre = new Livre(isbnLivre, codTheme, titreLivre, auteur, nbExemplaire, nbEmprunt);
                    listLivre.add(livre);
                }
            } while (ligneLue != null);
            ObservableList<Livre> data = FXCollections.observableArrayList(listLivre);

            return data;
        } catch (NullPointerException npe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(npe.getMessage());
        } catch (FileNotFoundException fnfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(fnfe.getMessage());
            alert.setContentText("fichier non trouvé !");

        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.setTitle("Erreur");
        }
        return null;

    }

    /**
     * Lire thème dans le fichier csv.
     * lit le fichier csv contenant les thèmes et renvoie un ObservableList de tous les thèmes
     *
     * @return the observable list
     */
    public static ObservableList<Theme> lireTheme() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/csv/lstThèmes");
            LineNumberReader lineNumberReader
                    = new LineNumberReader(fileReader);
            String ligneLue;
            String codeTheme;
            String lTheme;
            String description;
            int nbEmprunt;
            Theme theme;
            ArrayList<Theme> listTheme = new ArrayList<>();
            do {
                ligneLue = lineNumberReader.readLine();
                if (ligneLue != null) {
                    codeTheme = ligneLue.substring(0, ligneLue.indexOf(","));
                    ligneLue = ligneLue.substring(ligneLue.indexOf(",") + 1);
                    lTheme = ligneLue.substring(0, ligneLue.indexOf(","));
                    ligneLue = ligneLue.substring(ligneLue.indexOf(",") + 1);
                    description = ligneLue.substring(0, ligneLue.indexOf(","));
                    ligneLue = ligneLue.substring(ligneLue.indexOf(",") + 1);
                    nbEmprunt = Integer.parseInt(ligneLue);
                    theme = new Theme(codeTheme, lTheme, description, nbEmprunt);
                    listTheme.add(theme);
                }
            } while (ligneLue != null);
            ObservableList<Theme> data = FXCollections.observableArrayList(listTheme);
            return data;
        } catch (NullPointerException npe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(npe.getMessage());
        } catch (FileNotFoundException fnfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(fnfe.getMessage());
            alert.setContentText("fichier non trouvé !");

        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.setTitle("Erreur");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Gets date time.
     *
     * @return the date time
     */
    public static LocalDateTime getDateTime() {
        return LocalDateTime.now();

    }

    /**
     * Gets date time string.
     *
     * @return the date time string
     */
    public static String getDateTimeString() {
        final String DATE_FORMATTER_JOUR = "dd/MM/yyyy";
        final String DATE_FORMATTER_HEURE = "HH'h'mm";
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatterJ = DateTimeFormatter.ofPattern(DATE_FORMATTER_JOUR);
        DateTimeFormatter formatterH = DateTimeFormatter.ofPattern(DATE_FORMATTER_HEURE);
        String formatDateTimeJ = localDateTime.format(formatterJ);
        String formatDateTimeH = localDateTime.format(formatterH);
        return String.format("Le %s à %s ", formatDateTimeJ, formatDateTimeH);

    }
}