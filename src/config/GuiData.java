package config;

import java.awt.Color;

public class GuiData {
	
	public static final int WINDOW_WIDTH = 415;
	public static final int WINDOW_HEIGHT = 435;
	public static final int RECT_SIZE = 20;
	
	/**
	 * Parameters of Elements Color
	 */
	public static final Color FIRE_COLOR = Color.RED;
	public static final Color HOUSE_COLOR= Color.GRAY;
	public static final Color TREE_COLOR= Color.GREEN;
	
	/**
	 * PopUp Text
	 */
	public static String TREE_TEXT_TITLE = "Arbre d�tect� !";
	public static String TREE_TEXT = "Un arbre est d�termin� par un pixel d�tect� comme vert � l�aide de sa "
		+ "composante RGB. On distinguera �galement arbres de contour ou non : un pixel qui ne sera pas arbre "
		+ "de contour aura tous ses voisins �galement d�tect�s comme des arbres. Un arbre de contour aura donc au "
		+ "moins un voisin qui sera un pixel non d�tect� comme un arbre.";
	public static String FOREST_TEXT_TITLE = "For�t d�tect�e !";
	public static String FOREST_TEXT = "Une for�t est d�termin�e par un ensemble d'arbres. Une fois qu�un arbre est ajout� "
		+ "� la for�t, on v�rifie �galement ses voisins et on les ajoute ou non � la for�t.\nOn v�rifie toujours qu�un "
		+ "arbre n�est pas d�j� dans une for�t avant de l�ajouter � une nouvelle et on pr�voit la possible fusion de for�ts.\n"
		+ "Ainsi, on obtient tr�s vite la totalit� des pixels de la for�t et ses bords sans tester plusieurs fois les m�mes.\n"
		+ "Il suffit alors de marquer les contours de cette for�t (c�est-�-dire tous les pixels d�tect�s comme arbres de contour) "
		+ "et on peut alors afficher cet �l�ment sur notre carte.";
	public static String FOREST_FIRE_TEXT_TITLE = "Feu de for�t d�tect� !";
	public static String FOREST_FIRE_TEXT = "Un feu de for�t est d�termin� par un ensemble de pixels d�tect�s comme rouges � l�aide de leur "
		+ "composante RGB, dont au moins un des bords est voisin d�une for�t. Ainsi, on d�tecte tous les feux au sein ou en lisi�re "
		+ "de for�t, que ce soit un simple d�part de feu (peu de pixels) ou un grand feu (beaucoup de pixels). Une alerte est alors "
		+ "simplement �crite � c�t� de cette bulle d�aide. ";
}
