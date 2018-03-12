package utils;

public class Constants {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 420;
    public static final String DRIVER_NAME ="org.mariadb.jdbc.Driver";
    public static Database DB;
    public static String requeteListeBoutique = "SELECT * FROM Boutique";
    public static String requeteListeProduit = "";
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final int IMAGE_WIDTH = 1434;
    public static final int IMAGE_HEIGHT = 1032;
    public static final int MAP_HEIGHT = 721;
    public static final int MAP_WIDTH = 999;
    public static final float ECHELLE_MAP = 7.6f;//pour 8 pixel
    public static final int BTN_HEIGHT = 60;
    public static final int BTN_WIDTH = 150;
    public static final String  DEFAULT_NO_LOGO = "Prototype/src/res/empty-logo.png";
}
