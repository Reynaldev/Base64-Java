package com.ReynDev.Base64Java;

import jdk.jfr.Unsigned;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        // Encode
        if (e.getActionCommand().equals(encodeCmd)) {
            encode(taInput.getText());
        }

        // Decode
        if (e.getActionCommand().equals(decodeCmd)) {
            decode(taInput.getText());
        }
    }

    public char codeToChar(int c) {
        char[] ascii = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
        };

        return ascii[c];
    }

    public int charToCode(char c) {
        char[] ascii = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
        };

        for (int i = 0; i < ascii.length; i++) {
            if (c == ascii[i]) {
                return i;
            }
        }

        return 0;
    }

    public void encode(String text) {
        // Create an array to store ASCII code
        ArrayList<Integer> charCode = new ArrayList<>();

        // Store individual ASCII code into the array
        for (int code : text.getBytes()) {
            charCode.add(code);
        }

        // Create a StringBuilder to store a sequence of binary
        StringBuilder binaries = new StringBuilder();
        for (Integer integer : charCode) {
            StringBuilder s = new StringBuilder();  // Binary
            int n = integer;                        // Elements at index

            // Calculate each ASCII code into binary
            while (n > 0) {
                int r = n % 2;                      // Remainder
                n /= 2;

                s.append(r);
            }

            // If length is less than 8; add a 0
            while (s.length() < 8) {
                s.append(0);
            }

            // Reverse string
            s.reverse();

            // Append to the binaries StringBuilder
            binaries.append(s);
        }

        // Slice the binaries StringBuilder with a length of 6 and insert it into an ArrayList
        ArrayList<String> bits = new ArrayList<>();
        while (binaries.length() > 0) {
            while (binaries.length() < 6) {
                binaries.append(0);
            }

            String b = binaries.substring(0, 6);
            binaries.delete(0, 6);
            bits.add(b);
        }

        // Clear charCode ArrayList so we can use it later
        charCode.clear();

        // Calculate the binary and put it into the charCode ArrayList
        for (String t : bits) {
            int n = 0;

            for (int j = t.length() - 1; j >= 0; j--) {
                int p = Integer.parseInt(String.valueOf(t.charAt(j)));
                int q = t.length() - j - 1;
                n += Math.pow(2, q) * p;
            }

            charCode.add(n);
        }

        // Convert each code into a char
        StringBuilder out = new StringBuilder();
        for (Integer i : charCode) {
            out.append(codeToChar(i));
        }

        // Add padding
        while (out.length() % 4 != 0) {
            out.append('=');
        }

        // Set the output JTextArea
        taOutput.setText(out.toString());
    }

    public void decode(String text) {
        ArrayList<Integer> charCode = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == '=')
                continue;

            charCode.add(charToCode(ch));
        }

        // Create a StringBuilder to store a sequence of binary
        StringBuilder binaries = new StringBuilder();
        for (Integer integer : charCode) {
            StringBuilder s = new StringBuilder();  // Binary
            int n = integer;                        // Elements at index

            // Calculate each ASCII code into binary
            while (n > 0) {
                int r = n % 2;                      // Remainder
                n /= 2;

                s.append(r);
            }

            // If length is less than 6; add a 0
            while (s.length() < 6) {
                s.append(0);
            }

            // Reverse string
            s.reverse();

            // Append to the binaries StringBuilder
            binaries.append(s);
        }

        // Slice the binaries StringBuilder with a length of 8 and insert it into an ArrayList
        ArrayList<String> bits = new ArrayList<>();
        while (binaries.length() > 0) {
            while (binaries.length() < 8) {
                binaries.append(0);
            }

            String b = binaries.substring(0, 8);
            binaries.delete(0, 8);
            bits.add(b);
        }

        // Clear charCode ArrayList so we can use it later
        charCode.clear();

        // Calculate the binary and put it into the charCode ArrayList
        for (String t : bits) {
            int n = 0;

            for (int j = t.length() - 1; j >= 0; j--) {
                int p = Integer.parseInt(String.valueOf(t.charAt(j)));
                int q = t.length() - j - 1;
                n += Math.pow(2, q) * p;
            }

            if (n == 0)
                continue;

            charCode.add(n);
        }

        // Convert each code into a char
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < charCode.size(); i++) {
            char c = (char) charCode.get(i).intValue();
            out.append(c);
        }

        taOutput.setText(out.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
}