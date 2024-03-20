package org.itmo.utils;
import io.github.cdimascio.dotenv.Dotenv;

public class Settings {
    private static Dotenv dotenv = Dotenv.load();
    public static final String saveFilePath = dotenv.get("SAVE_FILE_PATH"); 
}
