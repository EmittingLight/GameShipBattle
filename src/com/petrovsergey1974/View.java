package com.petrovsergey1974;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    public View() {
        Field field = new Field();
        Container cont = getContentPane();
        cont.add(field);
        setTitle("Игра \"Морской бой\"");
        setBounds(0, 0, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}

