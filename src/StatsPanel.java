import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Displays statistics about how many guesses the person took during past games
 * Loads data from the file and displays in a JPanel
 *
 * TODO: refactor this class
 */
public class StatsPanel extends JPanel {
    public static final String FILENAME = "guess-the-number-stats.csv";
    private JPanel resultsPanel;
    private JPanel cardsPanel;
    private GameStats stats;

    // Stats will display the number of games in each "bin"
    // A bin goes from BIN_EDGES[i] through BIN_EDGES[i+1]-1, inclusive
    private static final int [] BIN_EDGES = {1, 2, 4, 6, 8, 10, 12, 14};
    private ArrayList<JLabel> resultsLabels;

    public StatsPanel(JPanel cardsPanel) {
        this.cardsPanel = cardsPanel;

        StatsPanelUI();
        CreateQuitButton();

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                updateResultsPanel();
            }
        });
    }

    private void clearResults(){
        for(JLabel lbl : resultsLabels){
            lbl.setText("--");
        }
    }

    private void updateResultsPanel() {
        clearResults();

        try {
            this.stats = new StatsFile(LocalDateTime.now().minusDays(30), new CSVReader(new FileReader(FILENAME)));
        } catch (Exception e) {

        }

        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            JLabel resultLabel = resultsLabels.get(binIndex);
            resultLabel.setText(Integer.toString(updateResultsNums(binIndex)));
        }
    }

    // TODO: TEST
    private int updateResultsNums(int binIndex) {
        final int lowerBound = BIN_EDGES[binIndex];
        int numGames = 0;

        if(binIndex == BIN_EDGES.length-1){
            // last bin
            // Sum all the results from lowerBound on up
            for(int numGuesses=lowerBound; numGuesses<stats.maxNumGuesses(); numGuesses++){
                numGames += stats.numGames(numGuesses);
            }
        }
        else{
            int upperBound = BIN_EDGES[binIndex+1];
            for(int numGuesses=lowerBound; numGuesses <= upperBound; numGuesses++) {
                numGames += stats.numGames(numGuesses);
            }
        }
        return numGames;
    }

    // TODO: TEST
    private int calculateNumGames(int lowerBound, int binIndex) {
        int numGames = 0;
        if (binIndex == BIN_EDGES.length - 1) {
            // Last bin: Sum all the results from lowerBound on up
            for (int numGuesses = lowerBound; numGuesses < stats.maxNumGuesses(); numGuesses++) {
                numGames += stats.numGames(numGuesses);
            }
        } else {
            int upperBound = BIN_EDGES[binIndex + 1];
            for (int numGuesses = lowerBound; numGuesses <= upperBound; numGuesses++) {
                numGames += stats.numGames(numGuesses);
            }
        }
        return numGames;
    }

    public void StatsPanelUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Your Stats");
        this.add(title);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("(Past 30 Days)");
        this.add(subtitle);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0,40)));

        resultsPanel = new JPanel();
        resultsLabels = new ArrayList<>();
        resultsPanel.setLayout(new GridLayout(0, 2));
        resultsPanel.add(new JLabel("Guesses"));
        resultsPanel.add(new JLabel("Games"));
        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            String binName;
            if(binIndex == BIN_EDGES.length-1){
                // last bin
                binName = BIN_EDGES[binIndex] + " or more";
            }
            else{
                int upperBound = BIN_EDGES[binIndex+1] - 1;
                if(upperBound > BIN_EDGES[binIndex]){
                    binName = BIN_EDGES[binIndex] + "-" + upperBound;
                }
                else{
                    binName = Integer.toString(BIN_EDGES[binIndex]);
                }
            }

            resultsPanel.add(new JLabel(binName));
            JLabel result = new JLabel("--");
            resultsLabels.add(result);
            resultsPanel.add(result);
        }

        resultsPanel.setMinimumSize(new Dimension(120, 120));
        this.add(resultsPanel);
        resultsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateResultsPanel();

        this.add(Box.createVerticalGlue());
    }

    public void CreateQuitButton() {
        JButton quit = new JButton("Back to Home");
        quit.addActionListener(e -> {
            // See itemStateChanged in https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.HOME.name());
        });
        this.add(quit);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0,20)));
    }
}
