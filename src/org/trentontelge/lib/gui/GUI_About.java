package org.trentontelge.lib.gui;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Author: Peter Dragicevic [peter@petschko.org]
 * Authors-Website: http://petschko.org/
 * Date: 15.01.2017
 * Time: 17:45
 * Update: -
 * Version: 0.0.1
 *
 * Notes: GUI_About Class
 */
public abstract class GUI_About extends JDialog {
	protected JImageLabel imagePanel;
	private Component parent;
	protected JButton closeButton;

	/**
	 * Creates a new, initially invisible <code>Frame</code> with the
	 * specified title.
	 * <p>
	 * This constructor sets the component's locale property to the value
	 * returned by <code>JComponent.getDefaultLocale</code>.
	 *
	 * @param title the title for the frame
	 * @param relativeTo relative to which parent component
	 * @throws HeadlessException if GraphicsEnvironment.isHeadless()
	 * returns true.
	 * @see GraphicsEnvironment#isHeadless
	 * @see Component#setSize
	 * @see Component#setVisible
	 * @see JComponent#getDefaultLocale
	 */
	public GUI_About(@NotNull String title, @Nullable JFrame relativeTo) throws HeadlessException {
		super(relativeTo, title, JDialog.DEFAULT_MODALITY_TYPE);
		this.parent = relativeTo;
		this.imagePanel = this.aboutIcon();

		this.setVisible(false);
		this.createCloseButton();
		this.constructAbout();
		this.windowCloseOperation();

		this.setLocationRelativeTo(this.parent);
	}

	/**
	 * Construct the Content of the About-Window
	 */
	protected abstract void constructAbout();

	/**
	 * Returns the text of the Close-Button
	 *
	 * @return - Button-Text
	 */
	protected abstract String closeButtonText();

	/**
	 * Creates the About-Icon
	 *
	 * @return - JImageLabel or null if not set
	 */
	protected abstract JImageLabel aboutIcon();

	/**
	 * Window Close Operation
	 */
	private void windowCloseOperation() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				hideWindow();
			}
		});
	}

	/**
	 * Creates the close Button
	 */
	private void createCloseButton() {
		this.closeButton = new JButton(this.closeButtonText());
		this.closeButton.addActionListener(this.closeButtonAction());
	}

	/**
	 * Close Action for the Button
	 *
	 * @return - ActionListener
	 */
	private ActionListener closeButtonAction() {
		return e -> this.hideWindow();
	}

	/**
	 * Show this Window
	 */
	public void showWindow() {
		this.setLocationRelativeTo(this.parent);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}

	/**
	 * Hides the Window
	 */
	private void hideWindow() {
		this.setAlwaysOnTop(false);
		this.setVisible(false);
	}
}
