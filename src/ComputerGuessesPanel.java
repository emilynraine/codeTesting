import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 * TODO: refactor this class
 */
public class ComputerGuessesPanel extends JPanel {

    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    private JLabel guessMessage;
    private JPanel cardsPanel;
    private Consumer<GameResult> gameFinishedCallback;

    //TODO: Split constructor
    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;
        this.cardsPanel = cardsPanel;
        this.gameFinishedCallback = gameFinishedCallback;

        //UI setup
        ComputerGuessesPanelUI();

        //Buttons
        CreateCorrectButton();
        CreateUpperLowerButton("Higher", true);
        CreateUpperLowerButton("Lower", false);

        //Add listener
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                numGuesses = 0;
                upperBound = 1000;
                lowerBound = 1;
                lastGuess = (lowerBound + upperBound + 1) / 2;
                guessMessage.setText("I guess " + lastGuess + ".");
            }
        });
    }

    public void ComputerGuessesPanelUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        guessMessage = new JLabel("I guess ___.");
        guessMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(guessMessage);
        guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel prompt = new JLabel("Your number is...");
        this.add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));
    }

    public void CreateUpperLowerButton(String text, boolean isHigher) {
        JButton button = new JButton(text);
        button.addActionListener(e -> {
            HandleGuess(isHigher);
            guessMessage.setText("I guess " + lastGuess + ".");
        });
        this.add(button);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        if(!isHigher) {
            this.add(Box.createRigidArea(new Dimension(0,10)));
        }
    }

    public void HandleGuess(boolean isHigher) {
        if(isHigher) {
            lowerBound = Math.max(lowerBound, lastGuess + 1);
        } else {
            upperBound = Math.min(upperBound, lastGuess + 1);
        }
        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;
    }

    public void CreateCorrectButton() {
        JButton correctBtn = new JButton("Equal");
        correctBtn.addActionListener(e -> {
            HandleCorrectGuess();
        });
        this.add(correctBtn);
        correctBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));
    }

    public void HandleCorrectGuess() {
        guessMessage.setText("I guess ___.");

        // Send the result of the finished game to the callback
        GameResult result = new GameResult(false, lastGuess, numGuesses);
        gameFinishedCallback.accept(result);

        CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
        cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
    }
}
