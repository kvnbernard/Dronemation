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
	public static String TREE_TEXT_TITLE = "Arbre détecté !";
	public static String TREE_TEXT = "Un arbre est déterminé par un pixel détecté comme vert à l’aide de sa "
		+ "composante RGB. On distinguera également arbres de contour ou non : un pixel qui ne sera pas arbre "
		+ "de contour aura tous ses voisins également détectés comme des arbres. Un arbre de contour aura donc au "
		+ "moins un voisin qui sera un pixel non détecté comme un arbre.";
	public static String FOREST_TEXT_TITLE = "Forêt détectée !";
	public static String FOREST_TEXT = "Une forêt est déterminée par un ensemble d'arbres. Une fois qu’un arbre est ajouté "
		+ "à la forêt, on vérifie également ses voisins et on les ajoute ou non à la forêt.\nOn vérifie toujours qu’un "
		+ "arbre n’est pas déjà dans une forêt avant de l’ajouter à une nouvelle et on prévoit la possible fusion de forêts.\n"
		+ "Ainsi, on obtient très vite la totalité des pixels de la forêt et ses bords sans tester plusieurs fois les mêmes.\n"
		+ "Il suffit alors de marquer les contours de cette forêt (c’est-à-dire tous les pixels détectés comme arbres de contour) "
		+ "et on peut alors afficher cet élément sur notre carte.";
	public static String FOREST_FIRE_TEXT_TITLE = "Feu de forêt détecté !";
	public static String FOREST_FIRE_TEXT = "Un feu de forêt est déterminé par un ensemble de pixels détectés comme rouges à l’aide de leur "
		+ "composante RGB, dont au moins un des bords est voisin d’une forêt. Ainsi, on détecte tous les feux au sein ou en lisière "
		+ "de forêt, que ce soit un simple départ de feu (peu de pixels) ou un grand feu (beaucoup de pixels). Une alerte est alors "
		+ "simplement écrite à côté de cette bulle d’aide. ";
}
