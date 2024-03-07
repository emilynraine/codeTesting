import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Panel displays after a game has concluded
 * Displays the game outcome
 * Writes the results to file (if human was guessing)
 *
 * TODO: Refactor the setGameResults method. Leave the rest of this file unchanged.
 */
public class GameOverPanel extends JPanel {

    private GameResult gameResult;

    private JLabel answerTxt;
    private JLabel numGuessesTxt;

    private JPanel cardsPanel;
    private JPanel buttonPanel;

    public GameOverPanel(JPanel cardsPanel){
        this.gameResult = null;
        this.cardsPanel = cardsPanel;
        this.buttonPanel = new JPanel();

        //UI setup
        CreateGameOverPanelUI();
        CreateRestartButton();
        CreateQuitButton();

        this.add(buttonPanel);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Sets the game results, updates the UI, and saves results to the log file (if human was playing)
     */
    // TODO: refactor this method
    public void setGameResults(GameResult result, CSVWriter writer){
        this.gameResult = result;

        SetText(gameResult);

        if(result.humanWasPlaying){
            // write stats to file
            String [] record = new String[2];
            record[0] = LocalDateTime.now().toString();
            record[1] = Integer.toString(result.numGuesses);

            writer.writeNext(record);
        }
    }

    public void CreateGameOverPanelUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Game Finished");
        this.add(title);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0,20)));

        answerTxt = new JLabel("The answer was ___.");
        this.add(answerTxt);
        answerTxt.setAlignmentX(Component.CENTER_ALIGNMENT);

        numGuessesTxt = new JLabel("It took ___ ___ guesses.");
        this.add(numGuessesTxt);
        numGuessesTxt.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0,60)));
    }

    public void CreateRestartButton() {
        JButton restart = new JButton("Play Again");
        restart.addActionListener(e -> {
            // See itemStateChanged in https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            String screenName = (gameResult == null || gameResult.humanWasPlaying ?
                    ScreenID.HUMAN_PLAY.name() : ScreenID.COMPUTER_PLAY_LAUNCH.name());
            cardLayout.show(cardsPanel, screenName);
        });
        buttonPanel.add(restart);
    }

    public void CreateQuitButton() {
        JButton quit = new JButton("Back to Home");
        quit.addActionListener(e -> {
            // See itemStateChanged in https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.HOME.name());
        });
        buttonPanel.add(quit);
    }

    public void SetText(GameResult result) {
        answerTxt.setText("The answer was " + result.correctValue + ".");
        if(result.numGuesses == 1){
            numGuessesTxt.setText((result.humanWasPlaying ? "You" : "I") + " guessed it on the first try!");
        }
        else {
            numGuessesTxt.setText("It took " + (result.humanWasPlaying ? "you" : "me") + " " + result.numGuesses + " guesses.");
        }
    }
}
