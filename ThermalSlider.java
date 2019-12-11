import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;

public class ThermalSlider implements ChangeListener, MouseListener {
    public static JFrame sliderFrame;
    public static JPanel thermalSliderPanel;
    public static JPanel buttonsPanel;
    public static JSlider slider;
    public static JLabel label;
    public static JButton desideButton;
    public static JButton closeButton;

    public static int thermalThreshold = 0;
 
    ThermalSlider() {
        sliderFrame = new JFrame();   
        sliderFrame.setTitle("���x��臒l�ݒ���");
        sliderFrame.setSize(300, 150);
        //sliderFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        sliderFrame.setLocationRelativeTo(null);
        //sliderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        desideButton = new JButton("�m��");
        closeButton = new JButton("����");

        desideButton.addMouseListener(this);
        closeButton.addMouseListener(this);

        slider = new JSlider();
 
        slider.setMaximum(100);            /* �X���C�_�̍ő�l */
        slider.setMinimum(0);              /* �X���C�_�̍ŏ��l */
        slider.setValue(thermalThreshold); /* �X���C�_�̒l */
        slider.setMajorTickSpacing(10);    /* �傫�Ȗڐ���̊Ԋu */
        slider.setMinorTickSpacing(5);     /* �����Ȗڐ���̊Ԋu */
        slider.setPaintTicks(true);        /* �ڐ����\�� */
        slider.setPaintLabels(true);       /* ���x����\�� */
        thermalThreshold = InfraRedApp.getPercentage();
        slider.setValue(thermalThreshold); /*�X���C�_�[�̏����l���擾*/
        slider.addChangeListener(this);
 
        thermalSliderPanel = new JPanel();
        buttonsPanel = new JPanel();

        label = new JLabel();
        label.setText("臒l=���x�̍ő�l�ɑ΂��銄���F" + slider.getValue() +"%");

        thermalSliderPanel.add(slider);
        thermalSliderPanel.add(label);
        
        buttonsPanel.add(desideButton);
        buttonsPanel.add(closeButton);

        sliderFrame.add(thermalSliderPanel, BorderLayout.CENTER);
        sliderFrame.add(buttonsPanel, BorderLayout.SOUTH);

        //sliderFrame.pack();
        sliderFrame.setVisible(true);
    }
 
    //public static void main(String[] args) {
    //    new ThermalSlider();
    //}
 
    @Override
    public void stateChanged(ChangeEvent e) {
        thermalThreshold = slider.getValue();
        label.setText("臒l=���x�̍ő�l�ɑ΂��銄���F" + thermalThreshold +"%");
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(desideButton)) {            
            InfraRedApp.setPercentage(thermalThreshold);           
            sliderFrame.dispose();
        }
 
        if(e.getSource().equals(closeButton)) {
            sliderFrame.dispose();
        }
    }

    public void mouseEntered(MouseEvent e) {  
        // �����
    }

    public void mouseExited(MouseEvent e) {
        // �����
    }

    public void mousePressed(MouseEvent e) {
        // �����
    }

    public void mouseReleased(MouseEvent e) {
        // �����
    }    
}