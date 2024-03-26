package org.itmo.utils;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * The Settings class represents the configuration settings for the application.
 */
public class Settings {
    private static Dotenv dotenv = Dotenv.load();

    /**
     * The file path where the data should be saved.
     */
    public static final String saveFilePath = dotenv.get("SAVE_FILE_PATH"); 

    /**
     * Retrieves the save file path.
     *
     * @return the save file path
     */
    public static String getSaveFilePath() {
        return saveFilePath;
    }
}
