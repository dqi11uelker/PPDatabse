package de.szut.uelker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
/**
 * This class is a GUI to represent the database of an DBSZUT object.
 * @author Yunus �lker
 *
 */
public class DBDialog {

	private DBSZUT mainController;
	private ResultSet dbContent;
	private StringConstants strings;
	public static final int CURRENT = 0;
	public static final int FORWARD = 1;
	public static final int FASTFORWARD = 2;
	public static final int BACK = 3;
	public static final int FASTBACK = 4;
	private JFrame mainFrame;
	private JPanel mainPanel, inputPanel, selectorPanel, modifyPanel, searchPanel;
	private JButton buttonDelete,buttonNew, buttonSave, buttonSearch, buttonForward, 
	buttonFastForward, buttonBack, buttonFastBack;
	private JTextField textName, textFirstname, textClass, textSearch;
	private JRadioButton radioMale, radioFemale, radioName, radioFirstname, radioClass, radioEducationalProgram;
	private JLabel labelFilter, labelName, labelFirstname, labelGender,
	labelBirthday, labelEducationalProgram, labelClass, labelEntry, 
	labelMale, labelFemale, labelSearchName, labelSearchFirstname, labelSearchEducationalProgram, labelSearchClass;
	private ButtonGroup groupGender, groupSearch;
	private JComboBox<String> boxEducationalProgram;
	private JDateChooser datechooser;
	private final Color white = Color.WHITE;
	private final int columnWidth = 25;
	private final int width = 430;
	private final int height = 350;

	/**
	 * 
	 * @param dbszut the reference to the DBSZUT object to be displayed.
	 */
	public DBDialog(DBSZUT dbszut){
		this.mainController = dbszut;
		strings = new StringConstants();
		init();
	}

	/**
	 * Every Listener becomes initialized here.
	 */
	private void initListener(){
		buttonNew.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] values = new String[5];
				java.sql.Date date;

				date = new java.sql.Date( datechooser.getDate().getTime() );
				values[0] = textName.getText();
				values[1] = textFirstname.getText();
				if(radioFemale.isSelected()) values[2] = "f";
				if(radioMale.isSelected()) values[2] = "m";
				values[3] = (String) boxEducationalProgram.getSelectedItem();
				values[4] = textClass.getText();
				
				if (values[0].length() == 0 || values[1].length() == 0) {
					String tmp = strings.getMESSAGE_ERROR_COMPLETE();
					if (values[0].length() == 0) tmp += "Name, ";
					if (values[1].length() == 0) tmp += "Firstname, ";
					if (values[4].length() == 0) tmp += "Class";
					JOptionPane.showMessageDialog(null, tmp);	
				}else 
					mainController.insert(values, date);
				dbContent = mainController.selectAll();
				fillContent(FASTFORWARD);
			}
		});
		
		ActionListener skipActionListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch (arg0.getActionCommand()){
				case "forward":
					fillContent(FORWARD);
					break;
				case "fastforward":
					fillContent(FASTFORWARD);
					break;
				case "back":
					fillContent(BACK);
					break;
				case "fastback":
					fillContent(FASTBACK);
					break;
				}
			}
		};
		
		buttonDelete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dbContent = mainController.delete(textName.getText(), textFirstname.getText());
				fillContent(FASTBACK);
			}
		});
		
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainController.commit();
			}
		});
		
		buttonForward.addActionListener(skipActionListener);
		buttonFastForward.addActionListener(skipActionListener);
		buttonBack.addActionListener(skipActionListener);
		buttonFastBack.addActionListener(skipActionListener);

		buttonSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet tmp = null;
				if (radioName.isSelected() ){
					tmp = mainController.select(DBSZUT.NAME, textSearch.getText());
				} else if (radioFirstname.isSelected()){
					tmp = mainController.select(DBSZUT.FIRSTNAME, textSearch.getText());
				} else if (radioClass.isSelected()){
					tmp = mainController.select(DBSZUT.CLASS, textSearch.getText());
				} else if (radioEducationalProgram.isSelected()){
					tmp = mainController.select(DBSZUT.EDUCATIONALPROGRAM, textSearch.getText());
				}
				try {
					tmp.beforeFirst();
					if (tmp.next()) dbContent = tmp; 
					else JOptionPane.showMessageDialog(null, strings.getMESSAGE_ENTRY_NOT_FOUND());;
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, strings.getMESSAGE_ENTRY_NOT_FOUND());
				}
				fillContent(FASTFORWARD);
			}
		});
	}

	/**
	 * Initializes every part of the GUI. It's divided into many smaller "ini-methods".
	 */
	private void init(){
		mainFrame = new JFrame(strings.getNAME());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(width, height));

		inputPanel = new JPanel(new GridBagLayout());
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

		selectorPanel = new JPanel();
		selectorPanel.setPreferredSize(new Dimension(width, 35));

		modifyPanel = new JPanel();

		searchPanel = new JPanel(new GridLayout(2, 1));

		mainPanel.setOpaque(false);
		mainFrame.setContentPane(mainPanel);

		initComponents();
		initListener();
		fillMainPanel();
		dbContent = mainController.selectAll();
		fillContent(FASTBACK);

		mainPanel.setOpaque(false);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void initComponents() {
		labelBirthday = new JLabel(strings.getTextBirthday() + ":");
		labelClass = new JLabel(strings.getTextClass() + ":");
		labelEducationalProgram = new JLabel(strings.getTextEducationalProgram() + ":");
		labelFirstname = new JLabel(strings.getTextFirstname() + ":");
		labelGender = new JLabel(strings.getTextGender() + ":");
		labelName = new JLabel(strings.getTextName() + ":");
		labelFilter = new JLabel(strings.getTextFilter() + ":");
		labelEntry = new JLabel(strings.getTextEntry());
		labelMale = new JLabel(strings.getTextMale());
		labelFemale = new JLabel(strings.getTextFemale());
		labelSearchClass = new JLabel(strings.getTextClass());
		labelSearchEducationalProgram = new JLabel(strings.getTextEducationalProgram());
		labelSearchFirstname = new JLabel(strings.getTextFirstname());
		labelSearchName = new JLabel(strings.getTextName());

		buttonBack = new JButton(strings.getTextBack());
		buttonDelete = new JButton(strings.getTextDelete());
		buttonFastBack = new JButton(strings.getTextFastBack());
		buttonFastForward = new JButton(strings.getTextFastForward());
		buttonForward = new JButton(strings.getTextForward());
		buttonNew = new JButton(strings.getTextNew());
		buttonSearch = new JButton(strings.getTextSearch());
		buttonSave = new JButton(strings.getTextSave());
		buttonForward.setActionCommand("forward");
		buttonFastForward.setActionCommand("fastforward");
		buttonBack.setActionCommand("back");
		buttonFastBack.setActionCommand("fastback");

		datechooser = new JDateChooser(); //dateobject geben um auszulesen, ODER getDate() beim event
		textClass = new JTextField(columnWidth);
		textName = new JTextField(columnWidth);
		textFirstname = new JTextField(columnWidth);

		radioMale = new JRadioButton();
		radioFemale = new JRadioButton();
		radioClass = new JRadioButton();
		radioEducationalProgram = new JRadioButton();
		radioFirstname = new JRadioButton();
		radioName = new JRadioButton();
		radioName.setSelected(true);

		groupGender = new ButtonGroup();
		groupGender.add(radioMale);
		groupGender.add(radioFemale);

		groupSearch = new ButtonGroup();
		groupSearch.add(radioClass);
		groupSearch.add(radioEducationalProgram);
		groupSearch.add(radioName);
		groupSearch.add(radioFirstname);

		boxEducationalProgram = new JComboBox<String>(strings.getComboStringEducationalProgram());
		textSearch = new JTextField(columnWidth);

	}

	private void fillMainPanel() {
		fillInputPanel();
		fillControllerPanel(); //including selectorP and modifyP
		fillSearchPanel();

		//coming soon: addActionListenerToComponents();

		mainPanel.add(inputPanel);
		mainPanel.add(selectorPanel);
		mainPanel.add(modifyPanel);
		mainPanel.add(searchPanel);
	}

	private void fillInputPanel() {
		GridBagConstraints c = new GridBagConstraints();
		inputPanel.setBackground(white);
		radioMale.setBackground(white);
		radioFemale.setBackground(white);
		boxEducationalProgram.setBackground(white);

		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		inputPanel.add(labelName, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		inputPanel.add(textName, c);


		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		inputPanel.add(labelFirstname, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		inputPanel.add(textFirstname, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		inputPanel.add(labelGender, c); 

		c.gridx = 2;
		c.gridy = 2;
		inputPanel.add(labelMale, c);

		JPanel labelGender = new JPanel();
		labelGender.setBackground(Color.WHITE);
		c.gridx = 1;
		c.gridy = 2;
		labelGender.add(radioMale);
		radioMale.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		labelGender.add(labelMale);
		labelMale.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
		labelGender.add(radioFemale);
		labelGender.add(labelFemale);
		labelFemale.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
		inputPanel.add(labelGender, c);

		c.gridx = 0;
		c.gridy = 3;
		inputPanel.add(labelBirthday, c);

		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		inputPanel.add(datechooser, c);

		c.gridx = 0;
		c.gridy = 4;
		inputPanel.add(labelEducationalProgram, c);

		c.gridx = 1;
		c.gridy = 4;
		inputPanel.add(boxEducationalProgram, c);

		c.gridx = 0;
		c.gridy = 5;
		inputPanel.add(labelClass, c);

		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		inputPanel.add(textClass, c);
	}

	private void fillControllerPanel() {
		selectorPanel.add(buttonFastBack);
		selectorPanel.add(buttonBack);
		selectorPanel.add(labelEntry);
		//TODO------------counter-----------------
		selectorPanel.add(buttonForward);
		selectorPanel.add(buttonFastForward);
		buttonDelete.setPreferredSize(new Dimension(width/3 - 10, 35));
		buttonNew.setPreferredSize(new Dimension(width/3 - 10, 35));
		buttonSave.setPreferredSize(new Dimension(width/3 - 10, 35));
		modifyPanel.add(buttonDelete);
		modifyPanel.add(buttonNew);
		modifyPanel.add(buttonSave);
	}

	private void fillSearchPanel() {
		JPanel radioFilterPanel = new JPanel();
		JPanel textFilterPanel = new JPanel();

		radioFilterPanel.add(labelFilter);		
		radioFilterPanel.add(radioName);
		radioFilterPanel.add(labelSearchName);
		radioFilterPanel.add(radioFirstname);
		radioFilterPanel.add(labelSearchFirstname);
		radioFilterPanel.add(radioClass);
		radioFilterPanel.add(labelSearchClass);
		radioFilterPanel.add(radioEducationalProgram);
		radioFilterPanel.add(labelSearchEducationalProgram);

		textFilterPanel.add(textSearch);
		textFilterPanel.add(buttonSearch);

		searchPanel.add(radioFilterPanel);
		searchPanel.add(textFilterPanel);		
	}

	//TOOO cJAVADOC SEE CONSTANTS
	/**
	 * Sets the values from DBSZUT-object into their fields. 
	 * @param type Used to determine which data from the database need to be displayed. <br>see the constants to use it. 
	 */
	private void fillContent(int type){
		try {
			if (type ==  FORWARD){
				dbContent.next();
			} else if (type == FASTFORWARD) {
				dbContent.last();
			} else if (type == BACK) {
				dbContent.previous();
			} else if (type == FASTBACK){
				dbContent.first();
			}

			if (!dbContent.isAfterLast() && !dbContent.isBeforeFirst()){
				textName.setText( dbContent.getString(1) );
				textFirstname.setText( dbContent.getString(2) );
				if (dbContent.getString(3).equals("m")) radioMale.setSelected(true);
				if (dbContent.getString(3).equals("f")) radioFemale.setSelected(true);
				datechooser.setDate(dbContent.getDate(4) );

				boxEducationalProgram.setSelectedItem(dbContent.getString(5));
				textClass.setText(dbContent.getString(6));

				labelEntry.setText( String.valueOf( dbContent.getRow() ));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
