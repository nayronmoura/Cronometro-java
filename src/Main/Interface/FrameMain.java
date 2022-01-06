package Main.Interface;
//imports

import sun.misc.PostVMInitHook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//class
public class FrameMain extends JFrame {
    private JPanel MainPainel;
    private JButton iniciarButton;
    private JButton pararButton;
    public JLabel Label;
    private JPanel ContentLabel;
    private JPanel ButtonContent;
    private boolean contando = false;

    public FrameMain(String Titulo) {
        super.setTitle(Titulo);
        this.setContentPane(MainPainel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Dimension d = new Dimension();
        d.width = d.height = 300;
        this.setSize(d);
        pararButton.setEnabled(false);
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contando = true;
                estado();
                iniciarButton.setEnabled(false);
                pararButton.setEnabled(true);
            }
        });
        pararButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contando = false;
                estado();
                pararButton.setEnabled(false);
                iniciarButton.setEnabled(true);
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new FrameMain("Cronometro");
        frame.setVisible(true);
    }

    public void MostrarLabel(String Text) {
        Label.setText(Text);
    }

    private void estado() {
        Thread operacao = new Thread(t);
        if (contando) {
            operacao.start();
        } else {
            operacao.interrupt();
        }
    }

    private Runnable t = new Runnable() {
        @Override
        public void run() {
            int min = 0;
            for (int seg = 0; seg >= 0; seg++) {
                if (contando) {
                    if (seg == 60) {
                        min++;
                        seg = 0;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MostrarLabel(min + " : " + seg);
                } else {
                    return;
                }
            }
        }
    };
}


