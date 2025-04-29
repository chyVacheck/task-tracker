
/**
 * @file SystemSettingsStorage.java
 * 
 * @description
 * Класс для управления системными настройками приложения, такими как последний использованный ID задачи.
 * Настройки сохраняются в JSON-файл для обеспечения постоянства данных между перезапусками приложения.
 * 
 * @details
 * Расположение файла настроек: {@code /data/settings.json}
 * 
 * Использует Jackson для сериализации и десериализации JSON.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.filesystem;

/**
 * ! lib imports
 */
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ! java imports
 */
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.system.ObjectMapperProvider;

public class SystemSettingsStorage {

	private static final String SETTINGS_FILE = "data/settings.json";
	private static final ObjectMapper objectMapper = ObjectMapperProvider.get();
	private static Map<String, Object> settings = new HashMap<>();

	/**
	 * Загружает системные настройки из файла.
	 * Если файл отсутствует, создаёт его с начальными значениями.
	 */
	public static void loadSettings() {
		try {
			File file = new File(SETTINGS_FILE);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
				settings.put("lastId", 0L);
				saveSettings();
			} else {
				settings = objectMapper.readValue(file, Map.class);
			}
		} catch (IOException e) {
			throw new RuntimeException("Can not load system settings", e);
		}
	}

	/**
	 * Сохраняет текущие настройки в файл.
	 */
	public static void saveSettings() {
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(SETTINGS_FILE), settings);
		} catch (IOException e) {
			throw new RuntimeException("Can not save system settings", e);
		}
	}

	/**
	 * Получить последний использованный ID задачи.
	 *
	 * @return последний ID
	 */
	public static long getLastId() {
		Object value = settings.getOrDefault("lastId", 0L);
		return ((Number) value).longValue();
	}

	/**
	 * Установить новый последний ID задачи.
	 *
	 * @param lastId новое значение последнего ID
	 */
	public static void setLastId(long lastId) {
		settings.put("lastId", lastId);
		saveSettings();
	}

}