package zapisi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import zapisi.FilterPanel.TaxiRideFilter;

/**
 *
 */
public class TableFrame extends JFrame {

    private GenericTablePanel tablePanel;
    private JTextField loadField;
    private JTextArea logArea;
    private JFrame filterFrame;
    private FilterPanel filterPanel;
    private JButton loadButton, applyFilterButton;

    public TableFrame() {
        //Creates the toolbar  
        JToolBar toolbar = new JToolBar();
        super.add(toolbar, BorderLayout.NORTH);

        JButton exitButton = new JButton("Exit");
        toolbar.add(exitButton);
        exitButton.addActionListener(actionEvent -> System.exit(0));

        loadField = new JTextField(30);
        toolbar.add(loadField);
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        loadButton = new JButton("Load file");
        loadButton.addActionListener(e -> {

            logArea.append("Loading records from \"" + loadField.getText() + "\"\n");
            loadButton.setEnabled(false);
            applyFilterButton.setEnabled(false);

            Runnable loadTask = () -> {
                Stream<String> lines;
                try {
                    lines = Files.lines(Paths.get(loadField.getText()));
                    //reset the counter when loading
                    TaxiRideRecord.resetCounter();
                    List<TaxiRideRecord> records = lines.
                            map(line -> new TaxiRideRecord(line)).collect(Collectors.toList());
                    SwingUtilities.invokeLater(() -> {
                        tablePanel.update(records);
                        logArea.append("Loaded " + records.size() + " records\n");
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        logArea.append("Cannot open file\n");
                    });
                } finally {
                    SwingUtilities.invokeLater(() -> {
                        loadButton.setEnabled(true);
                        applyFilterButton.setEnabled(true);
                    });
                }
            };

            new Thread(loadTask).start();

        });
        toolbar.add(loadButton);
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton defineFilterButton = new JButton("Define Filter");
        defineFilterButton.addActionListener(e -> {
            if (filterFrame.isVisible()) {
                filterFrame.setVisible(false);
            } else {
                filterFrame.setVisible(true);
            }
        });
        toolbar.add(defineFilterButton);

        applyFilterButton = new JButton("Apply Filter");
        applyFilterButton.addActionListener(e -> {

            loadButton.setEnabled(false);
            applyFilterButton.setEnabled(false);

            TaxiRideFilter filter = filterPanel.getFilter();
            if (filter.toLeave() || filter.toSkip() || filter.toFilterByPaymentType() || filter.toLimitDistance()) {
                logArea.append("Applying defined filter\n");
            }

            List<TaxiRideRecord> records = tablePanel.getRecords();

            Runnable filterTask = () -> {
                Stream<TaxiRideRecord> stream = records.stream();

                if (filter.toSkip()) {
                    stream = stream.skip(filter.getNumberToSkip());
                    SwingUtilities.invokeLater(() -> {
                        logArea.append("Skipping first " + filter.getNumberToSkip() + " records\n");
                    });
                }

                if (filter.toLeave()) {
                    stream = stream.limit(filter.getNumberToLeave());
                    SwingUtilities.invokeLater(() -> {
                        logArea.append("Leaving first " + filter.getNumberToLeave() + " records\n");
                    });
                }

                if (filter.toFilterByPaymentType()) {
                    stream = stream.filter(record -> record.getPaymentType().equals(filter.getPaymentType()));
                    SwingUtilities.invokeLater(() -> {
                        logArea.append("Keeping records with " + filter.getPaymentType() + " payment type\n");
                    });
                }

                List<TaxiRideRecord> filteredRecords = stream.collect(Collectors.toList());
                SwingUtilities.invokeLater(() -> {
                    tablePanel.update(filteredRecords);

                    loadButton.setEnabled(true);
                    applyFilterButton.setEnabled(true);
                });
            };
            new Thread(filterTask).start();
        });
        toolbar.add(applyFilterButton);

        //add the empty table
        tablePanel = new GenericTablePanel<>(TaxiRideRecord.class);
        tablePanel.setPreferredSize(new Dimension(1000, 700));
        super.add(tablePanel, BorderLayout.CENTER);

        //creates the log panel
        ScrollPane logPane = new ScrollPane();
        logArea = new JTextArea();
        logPane.add(logArea);
        super.add(logPane, BorderLayout.SOUTH);

        //filter frame
        filterFrame = new JFrame();
        filterFrame.setBounds(100, 100, 500, 400);
        filterFrame.add(filterPanel = new FilterPanel());
        filterFrame.pack();
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            TableFrame window = new TableFrame();
            window.setLocation(20, 20);
            window.setVisible(true);
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setTitle("Taxi Record Viewer");
            window.pack();
        });
    }
}
