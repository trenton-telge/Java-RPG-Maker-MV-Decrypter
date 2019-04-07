package org.trentontelge.rpgmakermv.decrypt;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.trentontelge.lib.Const;
import org.trentontelge.lib.gui.JImageLabel;
import org.trentontelge.lib.gui.JLabelExtra;
import org.trentontelge.lib.gui.JPanelLine;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;

/**
 * Author: Peter Dragicevic [peter@petschko.org]
 * Authors-Website: http://petschko.org/
 * Date: 15.01.2017
 * Time: 19:42
 * Update: -
 * Version: 0.0.1
 *
 * Notes: GUI_About Class
 */
class GUI_About extends org.trentontelge.lib.gui.GUI_About {
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
	GUI_About(@NotNull String title, @Nullable JFrame relativeTo) throws HeadlessException {
		super(title, relativeTo);
	}

	/**
	 * Construct the Content of the About-Window
	 */
	@Override
	protected void constructAbout() {
		int lineSpace = 4;

		// Initial Comps - Panels
		JPanel borderFrame = new JPanel();
		JPanel descriptionContainer = new JPanel();
		JPanel logoPanel = new JPanel();
		JPanel okButton = new JPanel();

		// Initial Comps - Labels
		JPanelLine versionLine = new JPanelLine();
		JLabelExtra versionHeading = new JLabelExtra("Version:");
		JLabel version = new JLabel(Config.version);

		JPanelLine licenceLine = new JPanelLine();
		JLabelExtra licenceHeading = new JLabelExtra("Licence:");
		JLabelExtra licence = new JLabelExtra("MIT-Licence");
		licence.setURL(Config.projectLicenceURL, true);

		JPanelLine projectLine = new JPanelLine();
		JLabelExtra projectHpHeading = new JLabelExtra("Project-HP:");
		JLabelExtra projectHp = new JLabelExtra("Visit the Project-Page on Github");
		projectHp.setURL(Config.projectPageURL, true);

		JPanelLine creditLine = new JPanelLine();
		JLabelExtra creditHeading = new JLabelExtra("Credits:");
		JPanelLine creatorLine = new JPanelLine();
		JLabel programmedBy = new JLabel(Const.creator + " (Programmer) - ");
		JLabelExtra programmedByURL = new JLabelExtra("Website");
		programmedByURL.setURL(Const.creatorURL, true);

		// Set Layouts
		borderFrame.setLayout(new BorderLayout());
		descriptionContainer.setLayout(new BoxLayout(descriptionContainer, BoxLayout.Y_AXIS));

		// Style
		versionHeading.setUnderline(true);
		licenceHeading.setUnderline(true);
		projectHpHeading.setUnderline(true);
		creditHeading.setUnderline(true);
		borderFrame.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		descriptionContainer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

		// Add stuff
		logoPanel.add(this.imagePanel);
		okButton.add(this.closeButton);

		versionLine.add(versionHeading);
		versionLine.addSpaceDimension(10);
		versionLine.add(version);
		licenceLine.add(licenceHeading);
		licenceLine.addSpaceDimension(10);
		licenceLine.add(licence);
		projectLine.add(projectHpHeading);
		projectLine.addSpaceDimension(10);
		projectLine.add(projectHp);
		creditLine.add(creditHeading);
		creatorLine.add(programmedBy);
		creatorLine.add(programmedByURL);

		descriptionContainer.add(versionLine);
		descriptionContainer.add(Box.createRigidArea(new Dimension(0, lineSpace)));
		descriptionContainer.add(licenceLine);
		descriptionContainer.add(Box.createRigidArea(new Dimension(0, lineSpace)));
		descriptionContainer.add(projectLine);
		descriptionContainer.add(Box.createRigidArea(new Dimension(0, lineSpace)));
		descriptionContainer.add(creditLine);
		descriptionContainer.add(creatorLine);

		borderFrame.add(logoPanel, BorderLayout.NORTH);
		borderFrame.add(descriptionContainer, BorderLayout.CENTER);
		borderFrame.add(okButton, BorderLayout.SOUTH);

		this.add(borderFrame);
		this.setResizable(false);
		this.pack();
	}

	/**
	 * Returns the text of the Close-Button
	 *
	 * @return - Button-Text
	 */
	@Override
	protected String closeButtonText() {
		return "Ok";
	}

	/**
	 * Creates the About-Icon
	 *
	 * @return - JImageLabel or null if not set
	 */
	@Override
	protected JImageLabel aboutIcon() {
		JImageLabel imagePanel = new JImageLabel(Config.authorImage, true);

		// Ensure this size
		imagePanel.setImageSize(200, 150);

		return imagePanel;
	}
}
