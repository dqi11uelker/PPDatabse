package de.szut.uelker;

public class StringConstants {

	//not abstract, because methods are better to comment than static String-variables
	private final String NAME = "Schülerdatenbank";

	private final String MESSAGE_EXIT = "ExitMessage";
	private final String MESSAGE_Error = "Please enter a valid / correct value!";
	private final String MESSAGE_ERROR_COMPLETE = "Please enter the following values:";
	private final String MESSAGE_ERROR_UNIQUE_ENTRY = "This person has already been inserted!";
	private final String MESSAGE_ERROR_INCORRECT = "Please insert a valid date!";
	private final String MESSAGE_ERROR_DELETE = "Entry can't delete!";
	private final String MESSAGE_ERROR_SAVE = "Can't save Entry!";

	private final String GHOST_TEXT_BIRTHDAY = "dd.mm.yyyy";
	private final String GHOST_TEXT_CLASS = "Class";
	private final String GHOST_TEXT_FIRSTNAME = "Firstname";
	private final String GHOST_TEXT_NAME = "Name";

	private final String textName = "Lastname";
	private final String textFirstname= "Firstname";
	private final String textGender = "Gender";
	private final String textBirthday = "Birthday";
	private final String textEducationalProgram = "Educational Program";
	private final String textClass = "Class";
	private final String textMale = "male";
	private final String textFemale = "female";
	private final String textFilter = "Filter";
	private final String textDelete = "Delete";
	private final String textNew = "New";
	private final String textSave = "Save";
	private final String textSearch = "Search";
	private final String textForward = ">";
	private final String textFastForward = ">>";
	private final String textBack = "<";
	private final String textFastBack = "<<";
	private final String textEntry = "0000000";
	private final String textReturn = "Go Back";

	private final String[] comboStringEducationalProgram = new String[]{"Please select an Education-Program", "BTA", "CTA", "MaTA", "PhyTA", "PTA", "ITA", "WiA-F", "WiA-IV", "Augenoptiker/in", "Biologielaborant/in", "Chemielaborant/in", "FAMI", "IT-Berufe", "Lacklaborant", "Steuerfachangestellte/r", "BOS", "DQM", "DQF", "DQI", "FOS"};

	/**
	 * Return the name of the Program
	 * @return name of the program
	 */
	public String getNAME() {
		return NAME;
	}


	/**
	 * Return the message, which appear if the program gets closed
	 * @return exit-message
	 */
	public String getMESSAGE_EXIT() {
		return MESSAGE_EXIT;
	}

	/**
	 * Return the error-message
	 * @return error-message
	 */
	public String getMESSAGE_Error() {
		return MESSAGE_Error;
	}

	/**
	 * Return the error-message, which appear if not all values for SQL-insert are available
	 * @return error-message (complete)
	 */
	public String getMESSAGE_ERROR_COMPLETE() {
		return MESSAGE_ERROR_COMPLETE;
	}

	/**
	 * Return the error-message, which appear if the values already exist in the database
	 * @return error-message (entry exist)
	 */
	public String getMESSAGE_ERROR_UNIQUE_ENTRY() {
		return MESSAGE_ERROR_UNIQUE_ENTRY;
	}

	/**
	 * Return the error-message, which appear if not all values are correct for SQL-insert
	 * @return error-message (incorrect)
	 */
	public String getMESSAGE_ERROR_INCORRECT() {
		return MESSAGE_ERROR_INCORRECT;
	}

	/**
	 * Return the error-message, which appear if the entry can't delete in the database
	 * @return error-message (delete)
	 */
	public String getMESSAGE_ERROR_DELETE() {
		return MESSAGE_ERROR_DELETE;
	}

	/**
	 * Return the error-message, which appear if the entry can't save in the database
	 * @return error-message (save)
	 */
	public String getMESSAGE_ERROR_SAVE() {
		return MESSAGE_ERROR_SAVE;
	}

	/**
	 * Return the ghost-text for the birthday
	 * @return ghost-text (birthday)
	 */
	public String getGHOST_TEXT_BIRTHDAY() {
		return GHOST_TEXT_BIRTHDAY;
	}

	/**
	 * Return the ghost-text for the class
	 * @return ghost-text (class)
	 */
	public String getGHOST_TEXT_CLASS() {
		return GHOST_TEXT_CLASS;
	}

	/**
	 * Return the ghost-text for the firstname
	 * @return ghost-text (firstname)
	 */
	public String getGHOST_TEXT_FIRSTNAME() {
		return GHOST_TEXT_FIRSTNAME;
	}

	/**
	 * Return the ghost-text for the lastname
	 * @return ghost-text (lastname)
	 */
	public String getGHOST_TEXT_NAME() {
		return GHOST_TEXT_NAME;
	}

	/**
	 * Return the text for the lastname
	 * @return text (lastname)
	 */
	public String getTextName() {
		return textName;
	}

	/**
	 * Return the text for the firstname
	 * @return text (firstname)
	 */
	public String getTextFirstname() {
		return textFirstname;
	}

	/**
	 * Return the text for the gender
	 * @return text (gender)
	 */
	public String getTextGender() {
		return textGender;
	}

	/**
	 * Return the text for the birthday
	 * @return text (birthday)
	 */
	public String getTextBirthday() {
		return textBirthday;
	}

	/**
	 * Return the text for the educational program
	 * @return text (educational program)
	 */
	public String getTextEducationalProgram() {
		return textEducationalProgram;
	}

	/**
	 * Return the text for the class
	 * @return text (class)
	 */
	public String getTextClass() {
		return textClass;
	}

	/**
	 * Return the text for the gender - male
	 * @return text (male)
	 */
	public String getTextMale() {
		return textMale;
	}

	/**
	 * Return the text for the gender - female
	 * @return text (female)
	 */
	public String getTextFemale() {
		return textFemale;
	}

	/**
	 * Return the text for the searchfield
	 * @return text (search)
	 */
	public String getTextFilter() {
		return textFilter;
	}

	/**
	 * Return the text for the delete button
	 * @return text (delete)
	 */
	public String getTextDelete() {
		return textDelete;
	}

	/**
	 * Return the text for the newbutton
	 * @return text (new)
	 */
	public String getTextNew() {
		return textNew;
	}

	/**
	 * Return the text for the save button
	 * @return text (save)
	 */
	public String getTextSave() {
		return textSave;
	}

	/**
	 * Return the text for the forward button
	 * @return text (forward)
	 */
	public String getTextForward() {
		return textForward;
	}

	/**
	 * Return the text for the fast-forward button
	 * @return text (fast-forward)
	 */
	public String getTextFastForward() {
		return textFastForward;
	}

	/**
	 * Return the text for the back button
	 * @return text (back)
	 */
	public String getTextBack() {
		return textBack;
	}

	/**
	 * Return the text for the fast-back button
	 * @return text (fast-back)
	 */
	public String getTextFastBack() {
		return textFastBack;
	}

	/**
	 * Return the text for the entry label
	 * @return text (entry)
	 */
	public String getTextEntry() {
		return textEntry;
	}

	/**
	 * Return the text for the ok button
	 * @return text (ok)
	 */
	public String getTextSearch() {
		return textSearch;
	}

	/**
	 * Return the text for the return button
	 * @return text (return)
	 */
	public String getTextReturn() {
		return textReturn;
	}

	/**
	 * Return the text for the educational comboBox
	 * @return text (educational comboBox)
	 */
	public String[] getComboStringEducationalProgram() {
		return comboStringEducationalProgram;
	}
}
