package org.trentontelge.rpgmakermv.decrypt;

import com.sun.istack.Nullable;
import org.trentontelge.lib.UserPref;

import java.util.Properties;

/**
 * Author: Peter Dragicevic [peter@petschko.org]
 * Authors-Website: http://petschko.org/
 * Date: 30.12.2016
 * Time: 22:32
 * Update: -
 * Version: 0.0.1
 *
 * Notes: Preferences Class
 */
class Preferences extends UserPref {
	static final String ignoreFakeHeader = "ignoreFakeHeader";
	static final String loadInvalidRPGDirs = "loadInvalidRPGDirs";
	static final String overwriteFiles = "overwriteFiles";
	static final String clearOutputDirBeforeDecrypt = "clearOutputDirBeforeDecrypt";
	static final String lastOutputDir = "lastOutputDir";
	static final String lastOutputParentDir = "lastOutputParentDir";
	static final String lastRPGDir = "lastRPGParentDir";
	static final String decrypterVersion = "decrypterVersion";
	static final String decrypterRemain = "decrypterRemain";
	static final String decrypterSignature = "decrypterSignature";
	static final String decrypterHeaderLen = "decrypterHeaderLen";


	/**
	 * UserPrefs Constructor
	 *
	 * @param filePath - File-Path to UserPref-File
	 */
	Preferences(@Nullable String filePath) {
		super(filePath);
	}

	/**
	 * Load the default-values for Preferences
	 *
	 */
	@Override
	public void loadDefaults() {
		Properties p = new Properties();

		p.setProperty(Preferences.ignoreFakeHeader, "true");
		p.setProperty(Preferences.overwriteFiles, "false");
		p.setProperty(Preferences.loadInvalidRPGDirs, "false");
		p.setProperty(Preferences.clearOutputDirBeforeDecrypt, "false");
		p.setProperty(Preferences.lastOutputDir, Config.defaultOutputDir);
		p.setProperty(Preferences.lastOutputParentDir, ".");
		p.setProperty(Preferences.lastRPGDir, ".");
		p.setProperty(Preferences.decrypterVersion, Decrypter.defaultVersion);
		p.setProperty(Preferences.decrypterRemain, Decrypter.defaultRemain);
		p.setProperty(Preferences.decrypterSignature, Decrypter.defaultSignature);
		p.setProperty(Preferences.decrypterHeaderLen, Integer.toString(Decrypter.defaultHeaderLen));

		this.setProperties(p);

	}
}
