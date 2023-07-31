package com.ReynDev.Base64Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements Runnable, ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton btnEncode, btnDecode;
    private JMenuBar menuBar;
    private JMenu menuFile, menuHelp;
    private JMenuItem miFileOpen, miFileExit;
    private JMenuItem miHelpAbout;
    private JTextArea taInput, taOutput;

    // Action commands
    private String encodeCmd = "EncodeCommand";
    private String decodeCmd = "DecodeCommand";

    private int width = 640;
    private int height = 480;

    @Override
    public void run() {
        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Frame
        frame = new JFrame("Base64 Encoder and Decoder");
        frame.setMinimumSize(new Dimension(width, height));
        frame.setLocation(
                (screenWidth - width) / 2,
                (screenHeight - height) / 2
        );

        // MenuBar
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // File menu
        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        // File menu items
        miFileOpen = new JMenuItem("Read from file...");
        menuFile.add(miFileOpen);

        miFileExit = new JMenuItem("Exit");
        menuFile.add(miFileExit);

        // Help menu
        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);

        // Help menu items
        miHelpAbout = new JMenuItem("About");
        menuHelp.add(miHelpAbout);

        // Panel
        GridBagConstraints ctr = new GridBagConstraints();
        panel = new JPanel(new GridBagLayout());
        frame.add(panel);

        // Input text area
        taInput = new JTextArea();

        ctr.fill = GridBagConstraints.BOTH;
        ctr.insets = new Insets(12, 12, 12, 12);
        ctr.weightx = 1;
        ctr.weighty = 0.5;
        ctr.gridwidth = 2;
        ctr.gridx = 0;
        ctr.gridy = 0;
        panel.add(taInput, ctr);

        // Encode button
        btnEncode = new JButton("Encode");
        btnEncode.setActionCommand(encodeCmd);
        btnEncode.addActionListener(this);

        ctr = new GridBagConstraints();
        ctr.insets = new Insets(12, 12, 12, 12);
        ctr.weightx = 1;
        ctr.anchor = GridBagConstraints.EAST;
        ctr.gridx = 0;
        ctr.gridy = 1;
        panel.add(btnEncode, ctr);

        // Decode button
        btnDecode = new JButton("Decode");
        btnDecode.setActionCommand(decodeCmd);
        btnDecode.addActionListener(this);

        ctr = new GridBagConstraints();
        ctr.insets = new Insets(12, 12, 12, 12);
        ctr.anchor = GridBagConstraints.EAST;
        ctr.gridx = 1;
        ctr.gridy = 1;
        panel.add(btnDecode, ctr);

        // Output text area
        taOutput = new JTextArea();

        ctr = new GridBagConstraints();
        ctr.fill = GridBagConstraints.BOTH;
        ctr.insets = new Insets(12, 12, 12, 12);
        ctr.weightx = 1;
        ctr.weighty = 0.5;
        ctr.gridwidth = 2;
        ctr.gridx = 0;
        ctr.gridy = 2;
        panel.add(taOutput, ctr);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
}