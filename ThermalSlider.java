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
        sliderFrame.setTitle("温度の閾値設定画面");
        sliderFrame.setSize(300, 150);
        //sliderFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        sliderFrame.setLocationRelativeTo(null);
        //sliderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        desideButton = new JButton("確定");
        closeButton = new JButton("閉じる");

        desideButton.addMouseListener(this);
        closeButton.addMouseListener(this);

        slider = new JSlider();
 
        slider.setMaximum(100);            /* スライダの最大値 */
        slider.setMinimum(0);              /* スライダの最小値 */
        slider.setValue(thermalThreshold); /* スライダの値 */
        slider.setMajorTickSpacing(10);    /* 大きな目盛りの間隔 */
        slider.setMinorTickSpacing(5);     /* 小さな目盛りの間隔 */
        slider.setPaintTicks(true);        /* 目盛りを表示 */
        slider.setPaintLabels(true);       /* ラベルを表示 */
        thermalThreshold = InfraRedApp.getPercentage();
        slider.setValue(thermalThreshold); /*スライダーの初期値を取得*/
        slider.addChangeListener(this);
 
        thermalSliderPanel = new JPanel();
        buttonsPanel = new JPanel();

        label = new JLabel();
        label.setText("閾値=温度の最大値に対する割合：" + slider.getValue() +"%");

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
        label.setText("閾値=温度の最大値に対する割合：" + thermalThreshold +"%");
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
        // 空実装
    }

    public void mouseExited(MouseEvent e) {
        // 空実装
    }

    public void mousePressed(MouseEvent e) {
        // 空実装
    }

    public void mouseReleased(MouseEvent e) {
        // 空実装
    }    
}