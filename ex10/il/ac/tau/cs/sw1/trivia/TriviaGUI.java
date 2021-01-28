package il.ac.tau.cs.sw1.trivia;

//import java.awt.desktop.QuitStrategy;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.Reader;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TriviaGUI {

	private static final int MAX_ERRORS = 3;
	private Shell shell;
	private Label scoreLabel;
	private Composite questionPanel;
	private Label startupMessageLabel;
	private Font boldFont;
	private String lastAnswer;
	private int score = 0;
	private int questionNumber = 0;
	private List<String[]> questionsList;
	private int numOfQuestinsTotal = 0;
	private int numOfWrongQuestions = 0;
	private int numOfQuestionsPassed = 0 ;
	private int numOfQuestionsFifty = 0 ;

	
	// Currently visible UI elements.
	Label instructionLabel;
	Label questionLabel;
	private List<Button> answerButtons = new LinkedList<>();
	private Button passButton;
	private Button fiftyFiftyButton;

	public void open() {
		createShell();
		runApplication();
	}

	/**
	 * Creates the widgets of the application main window
	 */
	private void createShell() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		shell.setText("Trivia");

		// window style
		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width / 3,
				monitor_bounds.height / 4));
		shell.setLayout(new GridLayout());

		FontData fontData = new FontData();
		fontData.setStyle(SWT.BOLD);
		boldFont = new Font(shell.getDisplay(), fontData);

		// create window panels
		createFileLoadingPanel();
		createScorePanel();
		createQuestionPanel();
	}

	/**
	 * Creates the widgets of the form for trivia file selection
	 */
	private void createFileLoadingPanel() {
		final Composite fileSelection = new Composite(shell, SWT.NULL);
		fileSelection.setLayoutData(GUIUtils.createFillGridData(1));
		fileSelection.setLayout(new GridLayout(4, false));

		final Label label = new Label(fileSelection, SWT.NONE);
		label.setText("Enter trivia file path: ");

		// text field to enter the file path
		final Text filePathField = new Text(fileSelection, SWT.SINGLE
				| SWT.BORDER);
		filePathField.setLayoutData(GUIUtils.createFillGridData(1));

		// "Browse" button
		final Button browseButton = new Button(fileSelection, SWT.PUSH);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String path = GUIUtils.getFilePathFromFileDialog(shell);
				filePathField.setText(path);
			}
		}
		);
		
		// "Play!" button
		final Button playButton = new Button(fileSelection, SWT.PUSH);
		playButton.setText("Play!");
		playButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0)  {
				String path = filePathField.getText();
				questionsList = Qusetions.openFileToRead(path);
				numOfQuestinsTotal = questionsList.size();
				loadQusetionsToPanel();
			}
		});
	}
	
	/**
	 * Load the next question to the panel and set the new score
	 */
	private void loadQusetionsToPanel() {
		lastAnswer = "";
		String[] nextQusetion = questionsList.get(questionNumber); 
		List<String> answersArr = Arrays.asList(Arrays.copyOfRange(nextQusetion, 1, 5));
		updateQuestionPanel(nextQusetion[0], answersArr);
		scoreLabel.setText(String.valueOf(score));
		questionNumber++;
	}
	
	/**
	 * Check if game over ; if so end game, other load next question
	 */
	private  void endGameOrLoadOtherQuestion() {
		if ( numOfWrongQuestions == MAX_ERRORS || questionNumber == numOfQuestinsTotal -1) {
			endGameDialog();
		}else {
			loadQusetionsToPanel();
		}
	}
	
	/**
	 * End game with a final score massage and initial the data
	 */
	private  void endGameDialog() {
		GUIUtils.showInfoDialog(shell, "GAME OVER", "Your final score is " + score + " after "+ (questionNumber - numOfQuestionsPassed - numOfQuestionsFifty) + " questions");
// 		set initial start:
		score = 0;
		questionNumber = 0;
		numOfQuestinsTotal = 0;
		numOfWrongQuestions = 0;
		numOfQuestionsPassed = 0 ;
		numOfQuestionsFifty = 0 ;
		questionPanel.dispose();
		createQuestionPanel();
		questionPanel.pack();
		questionPanel.getParent().layout();
		scoreLabel.setText("");
		
		
	}
	
	/**
	 * Creates the panel that displays the current score
	 */
	private void createScorePanel() {
		Composite scorePanel = new Composite(shell, SWT.BORDER);
		scorePanel.setLayoutData(GUIUtils.createFillGridData(1));
		scorePanel.setLayout(new GridLayout(2, false));

		final Label label = new Label(scorePanel, SWT.NONE);
		label.setText("Total score: ");

		// The label which displays the score; initially empty
		scoreLabel = new Label(scorePanel, SWT.NONE);
		scoreLabel.setLayoutData(GUIUtils.createFillGridData(1));
	}

	/**
	 * Creates the panel that displays the questions, as soon as the game
	 * starts. See the updateQuestionPanel for creating the question and answer
	 * buttons
	 */
	private void createQuestionPanel() {
		questionPanel = new Composite(shell, SWT.BORDER);
		questionPanel.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		questionPanel.setLayout(new GridLayout(2, true));

		// Initially, only displays a message
		startupMessageLabel = new Label(questionPanel, SWT.NONE);
		startupMessageLabel.setText("No question to display, yet.");
		startupMessageLabel.setLayoutData(GUIUtils.createFillGridData(2));
	}
	
	/**
	 * 
	 */
	private void activatPassButton() {
		questionNumber++;
		numOfQuestionsPassed++;
		endGameOrLoadOtherQuestion();
	}
	
	

	/**
	 * Serves to display the question and answer buttons
	 */
	private void updateQuestionPanel(String question, List<String> answers) {
		// Save current list of answers.
		List<String> currentAnswers = answers;
		
		// clear the question panel
		Control[] children = questionPanel.getChildren();
		for (Control control : children) {
			control.dispose();
		}

		// create the instruction label
		instructionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		instructionLabel.setText(lastAnswer + "Answer the following question:");
		instructionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the question label
		questionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		questionLabel.setText(question);
		questionLabel.setFont(boldFont);
		questionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the answer buttons
		for (int i = 0; i < 4; i++) {
			Button answerButton = new Button(questionPanel, SWT.PUSH | SWT.WRAP);
			answerButton.setText(answers.get(i));
			GridData answerLayoutData = GUIUtils.createFillGridData(1);
			answerLayoutData.verticalAlignment = SWT.FILL;
			answerButton.setLayoutData(answerLayoutData);
			answerButton.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if (answerButton.getText().equals(answers.get(0))) {
						score +=3;
						endGameOrLoadOtherQuestion();
						numOfWrongQuestions = 0;
					}
					else {
						score -=2;
						numOfWrongQuestions++;
						endGameOrLoadOtherQuestion();
					}
				}
			});
			
			answerButtons.add(answerButton);
		}
		
	
		
		
		// create the "Pass" button to skip a question
		passButton = new Button(questionPanel, SWT.PUSH);
		passButton.setText("Pass");
		GridData data = new GridData(GridData.END, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		passButton.setLayoutData(data);
		passButton.addSelectionListener(new SelectionAdapter() {
			
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (numOfQuestionsPassed == 0) {
					activatPassButton();
				}else if(score > 0){
						score--;
						activatPassButton();
					}
				}
		});
		
		// create the "50-50" button to show fewer answer options
		fiftyFiftyButton = new Button(questionPanel, SWT.PUSH);
		fiftyFiftyButton.setText("50-50");
		data = new GridData(GridData.BEGINNING, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		fiftyFiftyButton.setLayoutData(data);
		
		fiftyFiftyButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (numOfQuestionsFifty == 0) {
					activatFiftyButton();
				}else if(score > 0){
					activatFiftyButton();
					score--;
				}
			}
			
			public void activatFiftyButton() {
				Collections.shuffle(currentAnswers.subList(1, 4));
				numOfQuestionsFifty++;
				currentAnswers.set(1," ");
				currentAnswers.set(2," ");
				Collections.shuffle(currentAnswers.subList(1, 4));
				for (Button i :answerButtons) {
					if (i.equals("")) {
						i.setEnabled(false);
					}
				}
				updateQuestionPanel(question, currentAnswers);
				fiftyFiftyButton.setEnabled(false);
			}
		});

		// two operations to make the new widgets display properly
		questionPanel.pack();
		questionPanel.getParent().layout();
	}

	/**
	 * Opens the main window and executes the event loop of the application
	 */
	private void runApplication() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		boldFont.dispose();
	}
}
