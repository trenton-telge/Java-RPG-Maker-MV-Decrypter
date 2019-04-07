package org.trentontelge.lib;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * Author: Peter Dragicevic [peter@petschko.org]
 * Authors-Website: http://petschko.org/
 * Date: 23.12.2016
 * Time: 04:44
 * Update: -
 * Version: 0.1.0
 *
 * Notes: UserPref Class
 */
public abstract class UserPref {
	private Properties properties = null;
	private String filePath;

	/**
	 * UserPrefs Constructor
	 *
	 * @param filePath - File-Path to UserPref-File
	 */
	public UserPref(@Nullable String filePath) {
		this.setFilePath(filePath);

		if(filePath != null)
			this.load();
	}

	/**
	 * Returns the Properties Object
	 *
	 * @return - Properties Object
	 */
	private Properties getProperties() {
		return properties;
	}

	/**
	 * Sets the Properties Object
	 *
	 * @param properties - Properties Object
	 */
	protected void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * Returns the File-Path of the Properties File
	 *
	 * @return - File-Path of the Properties File or null if not set
	 */
	private String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the File-Path of the Properties File
	 *
	 * @param filePath - File-Path of the Properties File
	 */
	private void setFilePath(@Nullable String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Loads the Properties of the User from a File
	 *
	 */
	private void load() {
		if(this.getFilePath() == null) {
			this.loadDefaults();
			return;
		}

		if(! File.existsFile(this.getFilePath())) {
			this.loadDefaults();
			return;
		}

		// Try to read the File
		Properties p = new Properties();
		Reader reader;
		try {
			reader = new FileReader(this.getFilePath());
			p.load(reader);
		} catch(Exception e) {
			e.printStackTrace();

			loadDefaults();
		} finally {
			this.setProperties(p);
		}

	}

	/**
	 * Loads the Default settings
	 *
	 */
	public abstract void loadDefaults();

	/**
	 * Saves the Properties of the User to a File
	 *
	 * @return - true if the Save was successful else false
	 */
	public boolean save() {
		if(this.getFilePath() == null || this.getProperties() == null)
			return false;

		// Check if File Exists if not try to create it
		if(! File.existsFile(this.getFilePath(), true))
			return false;

		try {
			FileWriter fileWriter = new FileWriter(this.getFilePath());
			this.getProperties().store(fileWriter, "User Config File");
		} catch(IOException e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * Returns the requested Property-Value with a Fallback-Value
	 * @param configKey - Property-Key (Name)
	 * @return - Property-Value
	 */
	public String getConfig(@NotNull String configKey) {
		return getConfig(configKey, null);
	}

	/**
	 * Returns the requested Property-Value or the Fallback-Value
	 *
	 * @param configKey - Property-Key (Name)
	 * @param fallbackValue - Fallback-Value if the Key is not set or null to ignore
	 * @return - Property-Value
	 */
	public String getConfig(@NotNull String configKey, @Nullable String fallbackValue) {
		if(this.getProperties() == null)
			this.load();

		if(fallbackValue == null)
			return this.getProperties().getProperty(configKey);

		// Try to add the Property if not exists
		if(this.getProperties().getProperty(configKey) == null)
			this.setConfig(configKey, fallbackValue);

		return this.getProperties().getProperty(configKey, fallbackValue);
	}

	/**
	 * Sets a Value to the Properties Object
	 *
	 * @param configKey - Property-Key (Name)
	 * @param newValue - New Value for this Properties-Key
	 */
	public void setConfig(@NotNull String configKey, @NotNull String newValue) {
		if(this.getProperties() == null)
			this.load();

		this.getProperties().setProperty(configKey, newValue);
	}

	/**
	 * Switches a Boolean "String" value to the opposite Boolean "String" Value if the value is not a Boolean String it doesn't change anything
	 *
	 * @param configKey - Config Key of the Value to switch
	 */
	public void switchBoolConfig(@NotNull String configKey) {
		String config = this.getConfig(configKey);

		// Config doesn't exists
		if(config == null)
			config = "false";

		config = config.toLowerCase();

		// Only switch on boolean values
		if(config.equals("true") || config.equals("false") || config.equals("1") || config.equals("0")) {
			boolean newValue = ! Functions.strToBool(config);

			this.setConfig(configKey, Boolean.toString(newValue));
		}
	}
}
