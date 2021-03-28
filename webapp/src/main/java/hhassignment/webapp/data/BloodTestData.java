package hhassignment.webapp.data;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Class providing the data access for the Blood Tests names and thresholds.
 * Will be loaded on application startup <br>
 * Since it was a requirement to allow a flexible data structure the data is
 * loaded as a <code>Map</code> where the key is the name and the value is the
 * object itself containing all its properties. <br>
 * This will allow for easy changes to the data structure without having to the
 * change the data access Class, however this means the application logic must
 * handle the data structure and type conversions.
 */
@Component
public class BloodTestData {
	private Map<String, Map<String, Object>> dataSet;
	private List<String> dataSetKeys;

	Logger logger = Logger.getLogger(BloodTestData.class);

	/**
	 * Constructs the data access class loading the dataset into memory. Values
	 * pertaining to the data source are injected from configuration.
	 * 
	 * @param sourcePath   <code>String</code> path to the data file.
	 * @param rootProperty <code>String</code> property name for the root of the
	 *                     data array.
	 * @param keyPropName  <code>String</code> property name to use as a unique
	 *                     identifier for the data map.
	 */
	BloodTestData(@Value("${datasource-config.blood-test-file-path}") String sourcePath,
			@Value("${datasource-config.blood-test-root-prop}") String rootProperty,
			@Value("${datasource-config.blood-test-identifier-property-name}") String keyPropName) {
		dataSet = null;
		dataSetKeys = null;

		JsonElement data = this.readLocalDataSet(sourcePath);

		if (data != null) {
			this.processData(data, rootProperty, keyPropName);
		} else {
			logger.error("Data initialization failed.");
		}
	}

	/**
	 * Reads the dataset from local file system and returns a
	 * <code>JsonElement</code> representing the data.
	 * 
	 * @param sourcePath <code>String</code> path to data file location.
	 * @return <code>JsonElement</code> representation of the dataset.
	 */
	private JsonElement readLocalDataSet(String sourcePath) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(sourcePath);

			InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

			return JsonParser.parseReader(streamReader);
		} catch (Exception e) {
			logger.error(String.format("Failed to read local dataset file. Exception: ", e.getMessage()));
			return null;
		}
	}

	/**
	 * Parses and processes the dataset and sets the Class fields if parsing is
	 * successful.
	 * 
	 * @param data         <code>JsonElement</code> containing the dataset.
	 * @param rootProperty <code>String</code> name of the dataset's root property
	 *                     that holds the data array.
	 * @param keyPropName  <code>String</code> name of each member to use as a
	 *                     unique identifier for the member.
	 */
	private void processData(JsonElement data, String rootProperty, String keyPropName) {
		if (data.isJsonObject() && data.getAsJsonObject().has(rootProperty)) {
			try {
				Gson gson = new Gson();
				JsonArray root = data.getAsJsonObject().getAsJsonArray(rootProperty);
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> entries = gson.fromJson(root, List.class);

				HashMap<String, Map<String, Object>> pendingDataSet = new HashMap<>();
				ArrayList<String> pendingDataSetKeys = new ArrayList<>();

				entries.forEach(entry -> {
					String key = (String) entry.get(keyPropName);
					if (key != null && !pendingDataSet.containsKey(entry.get(keyPropName))) {
						pendingDataSet.put(key, entry);
						pendingDataSetKeys.add(key);
					} else {
						logger.warn(String.format("Object skipped due to invalid key. key: %s", key));
					}
				});

				dataSet = pendingDataSet;
				dataSetKeys = pendingDataSetKeys;
			} catch (Exception e) {
				logger.error(String.format("Failed to read dataset Object. Exception: %s", e.getMessage()));
			}
		} else {
			logger.error(
					"Failed to initialize dataset. Data is not a valid JsonObject or is missing the rootProperty.");
		}
	}

	/**
	 * Returns the dataset.
	 * 
	 * @return Dataset as <code>{@literal Map<String, Map<String, Object>>}</code>.
	 *         <code>null</code> if dataset was not loaded.
	 */
	public Map<String, Map<String, Object>> getDataSet() {
		return dataSet;
	}

	/**
	 * Returns the dataset keys.
	 * 
	 * @return Dataset keys as <code>{@literal List<String>}</code>.
	 *         <code>null</code> if dataset was not loaded or keys failed to be
	 *         extracted.
	 */
	public List<String> getDataSetKeys() {
		return dataSetKeys;
	}

	/**
	 * Returns the entry from the dataset if it exists.
	 * 
	 * @param key <code>String</code> the key for the entry. Should be the name of
	 *            the <b>Blood Test</b>.
	 * @return Entry as a <code>{@literal Map<String, Object>}</code>.
	 *         <code>null</code> if it doesn't exist or if dataset was not loaded.
	 */
	public Map<String, Object> getEntryByKey(String key) {
		if (dataSet != null) {
			return dataSet.get(key);
		}
		return null;
	}

	/**
	 * Returns a specific value from an entry in the dataset. The return type is an
	 * <code>Object</code> and the caller must handle the casting.
	 * 
	 * @param key          <code>String</code> the key for the entry. Should be the
	 *                     name of the <b>Blood Test</b>.
	 * @param propertyName <code>String</code> the name of the property of the
	 *                     entry.
	 * @return <code>Object</code> of the property value in the entry if it's found.
	 *         <code>null</code> will be returned if there is no such entry or no
	 *         such property for a found entry. <code>null</code> will also be
	 *         returned if the dataset was not loaded.
	 */
	public Object getValueByPropertyName(String key, String propertyName) {
		if (dataSet != null) {
			if (dataSet.containsKey(key)) {
				return dataSet.get(key).get(propertyName);
			}
		}

		return null;
	}
}
