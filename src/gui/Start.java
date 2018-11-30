package gui;

import gameDriver.PlayersTypes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {

    private JPanel contentPane;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final ButtonGroup buttonGroup_1 = new ButtonGroup();
    int p1, p2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Start frame = new Start();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Start() {
        setBackground(Color.RED);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 624, 587);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JRadioButton rdbtnNewRadioButton_3 = new JRadioButton(PlayersTypes.PACIFIST.toString());
        buttonGroup.add(rdbtnNewRadioButton_3);
        rdbtnNewRadioButton_3.setBounds(70, 277, 109, 23);
        contentPane.add(rdbtnNewRadioButton_3);
        rdbtnNewRadioButton_3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p1 = 3;
            }
        });

        JRadioButton rdbtnNewRadioButton_4 = new JRadioButton(PlayersTypes.GREEDY.toString());
        buttonGroup.add(rdbtnNewRadioButton_4);
        rdbtnNewRadioButton_4.setBounds(70, 333, 109, 23);
        contentPane.add(rdbtnNewRadioButton_4);
        rdbtnNewRadioButton_4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p1 = 4;
            }
        });

        JRadioButton rdbtnNewRadioButton_5 = new JRadioButton(PlayersTypes.A_STAR.toString());
        buttonGroup.add(rdbtnNewRadioButton_5);
        rdbtnNewRadioButton_5.setBounds(70, 387, 109, 23);
        contentPane.add(rdbtnNewRadioButton_5);
        rdbtnNewRadioButton_5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p1 = 5;
            }
        });


        JRadioButton rdbtnNewRadioButton_6 = new JRadioButton(PlayersTypes.REAL_TIME_A_STAR.toString());
        buttonGroup.add(rdbtnNewRadioButton_6);
        rdbtnNewRadioButton_6.setBounds(70, 430, 109, 23);
        contentPane.add(rdbtnNewRadioButton_6);
        rdbtnNewRadioButton_6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p1 = 6;
            }
        });

        JRadioButton radioButton = new JRadioButton(PlayersTypes.AGGRESSIVE.toString());
        radioButton.setSelected(true);
        buttonGroup_1.add(radioButton);
        radioButton.setBounds(373, 118, 109, 23);
        contentPane.add(radioButton);
        radioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p2 = 0;
            }
        });

        JRadioButton radioButton_1 = new JRadioButton(PlayersTypes.PASSIVE.toString());
        buttonGroup_1.add(radioButton_1);
        radioButton_1.setBounds(373, 166, 109, 23);
        contentPane.add(radioButton_1);
        radioButton_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p2 = 1;
            }
        });

        JRadioButton radioButton_2 = new JRadioButton(PlayersTypes.HUMAN.toString());
        buttonGroup_1.add(radioButton_2);
        radioButton_2.setBounds(373, 220, 109, 23);
        contentPane.add(radioButton_2);
        radioButton_2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p2 = 2;
                System.out.println("---"+p1+"---"+p2+"nnn");
            }
        });

        JRadioButton radioButton_3 = new JRadioButton(PlayersTypes.PACIFIST.toString());
        buttonGroup_1.add(radioButton_3);
        radioButton_3.setBounds(373, 277, 109, 23);
        contentPane.add(radioButton_3);
        radioButton_3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p2 = 3;
            }
        });

        JRadioButton radioButton_4 = new JRadioButton(PlayersTypes.GREEDY.toString());
        buttonGroup_1.add(radioButton_4);
        radioButton_4.setBounds(373, 333, 109, 23);
        contentPane.add(radioButton_4);
        radioButton_4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p2 = 4;
            }
        });

        JRadioButton radioButton_5 = new JRadioButton(PlayersTypes.A_STAR.toString());
        buttonGroup_1.add(radioButton_5);
        radioButton_5.setBounds(373, 387, 109, 23);
        contentPane.add(radioButton_5);
        radioButton_5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p2 = 5;
            }
        });

        JRadioButton radioButton_6 = new JRadioButton(PlayersTypes.REAL_TIME_A_STAR.toString());
        buttonGroup_1.add(radioButton_6);
        radioButton_6.setBounds(373, 430, 109, 23);
        contentPane.add(radioButton_6);
        radioButton_6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p2 = 6;
            }
        });

        JLabel lblPlayer = new JLabel("player 1");
        lblPlayer.setBounds(70, 24, 46, 14);
        contentPane.add(lblPlayer);

        JLabel lblPlayer_1 = new JLabel("player 2");
        lblPlayer_1.setBounds(373, 24, 46, 14);
        contentPane.add(lblPlayer_1);

        JRadioButton radioButton_7 = new JRadioButton(PlayersTypes.AGGRESSIVE.toString());
        buttonGroup.add(radioButton_7);
        radioButton_7.setSelected(true);
        radioButton_7.setBounds(70, 118, 109, 23);
        contentPane.add(radioButton_7);
        radioButton_7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p1 = 0;
            }
        });

        JRadioButton radioButton_8 = new JRadioButton(PlayersTypes.PASSIVE.toString());
        buttonGroup.add(radioButton_8);
        radioButton_8.setBounds(70, 166, 109, 23);
        contentPane.add(radioButton_8);
        radioButton_8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p1 = 1;
            }
        });

        JRadioButton radioButton_9 = new JRadioButton(PlayersTypes.HUMAN.toString());
        buttonGroup.add(radioButton_9);
        radioButton_9.setBounds(70, 220, 109, 23);
        contentPane.add(radioButton_9);
        radioButton_9.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                p1 = 2;
           
            }
        });

        JButton btnOk = new JButton("OK");
        btnOk.setBounds(210, 488, 89, 23);
        contentPane.add(btnOk);
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            dispose();
                           
                            GUIGame frame = new GUIGame(p1, p2);
                            
                            frame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
