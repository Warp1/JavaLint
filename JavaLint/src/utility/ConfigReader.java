package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import structure.RuleEnum;
import structure.Rules;

public class ConfigReader {

	private final static String propertyFileName = "config.properties";
	private static Properties prop = new Properties();
	private static InputStream input = null;
	private final static String filePropertyPath = System.getProperty("user.dir") + "\\src\\" + propertyFileName;
	private final static String defaultPath = "PATH";

	public static Map renderAuthorization() {

		try {
			Map<RuleEnum, Boolean> propertyMap = new HashMap<RuleEnum, Boolean>();
			input = new FileInputStream(filePropertyPath);

			// load our property file
			prop.load(input);

			// get values and return map
			propertyMap.put(Rules.LINE_SIZE, readValueProp("LINE_SIZE"));
			propertyMap.put(Rules.STRING_INSTANTIATION, readValueProp("STRING_INSTANTIATION"));
			propertyMap.put(Rules.CONSTANT_UPPERCASE, readValueProp("CONSTANT_UPPERCASE"));
			propertyMap.put(Rules.CLASS_NAME_FORMAT, readValueProp("CLASS_NAME_FORMAT"));
			propertyMap.put(Rules.PARAMS_FUNCTION, readValueProp("PARAMS_FUNCTION"));
			propertyMap.put(Rules.CHECK_NULL_INPUT, readValueProp("CHECK_NULL_INPUT"));
			propertyMap.put(Rules.FINAL_DECLARATION_MISSING, readValueProp("FINAL_DECLARATION_MISSING"));
			propertyMap.put(Rules.NESTED_SPACES, readValueProp("NESTED_SPACES"));

			return propertyMap;
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String getProjectPath() {
		try {
			input = new FileInputStream(filePropertyPath);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(defaultPath);
	}

	private static Boolean readValueProp(String propertyName) {
		return (prop.getProperty(propertyName).equals("true") ? true : false);
	}

}