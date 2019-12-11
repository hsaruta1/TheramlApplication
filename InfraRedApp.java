import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.swing.table.TableColumn;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Comparator;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.*;
import javax.swing.table.AbstractTableModel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import javax.swing.table.TableColumn;
import javax.swing.table.JTableHeader;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.BevelBorder;
import java.util.List;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

public class InfraRedApp extends JLabel implements MouseListener, ActionListener, MouseMotionListener { //ChangeListener, 
    public static String colorCsvPath = "./lib/palette/palette.csv";

    public static File start = new File(".\\lib\\Exe\\start.txt");
    public static File stop = new File(".\\lib\\Exe\\finish.txt");
    
    public static int line_count; 
    public static int col_count;

    public static int thermal_line_count; 
    public static int thermal_col_count;

    public static String[][] csvData;
    public static String[][] thermalCsvData;  // thermal��CSV

    public static String thermalCsvName;
    public static double max = 0;
    public static double min = 0;

    public static double maxAverage = 0;
    public static double minAverage = 0;

    public static String[] R; 
    public static String[] G;
    public static String[] B;

    public static boolean colorFlag = false;

    public static int p = 127;    // RGB�̕��ϒl(�O���[�X�P�[����f�l)

    public static String orgImagePath = "./OrgImages";
    public static String infraRedImagePath = "./InfraRedImages";

    public static File orgImagefolder = new File(orgImagePath);
    public static File infraRedImagefolder = new File(infraRedImagePath);

    public static String[] orgImageName;
    public static String[] infraRedImageName;

    public static int count = 0;
    public static int orgImageCount = 0;
    public static int infraRedImageCount = 0;
        
    public static double ratio = 0.0;

    public static int currentImageCounter = 0;

    public static JFrame frame;     // ��ʑS�̂̃t���[��

    public static JPanel panelNORTH;
    public static JLabel labelNORTH;

    public static JPanel panelWEST;
    public static JLabel labelWEST;
    

    public static JPanel panelCENTER; 
    public static JLabel labelCENTER;

    public static JPanel panelSOUTH;
    public static JLabel labelSOUTH;
    public static JPanel panelSOUTHWEST;
    public static JPanel panelSOUTHEAST;
    public static JButton paintButton;
    public static JButton button1;
    public static JButton button2;
    public static JButton button3;
    public static JButton button4;

    public static JPanel panelEAST;
    public static JLabel labelEAST;
    public static JButton okButton;
    public static JButton nextButton;
    public static JButton backButton;
    public static JButton regionSelectButton;
    public static JButton regionReleaseButton;
    public static JButton loupeButton;
    public static JButton realTimePlotButton;
    public static JButton reportGeneratorButton;

    public static ImageIcon icon;   // �g��摜�Ƃ��Ďg�p����摜��ImageIcon�^�Ƃ��ēǂݍ��ލۂɎg�p����ϐ�

    
    public static int SLIDER_WIDTH;
       
    public static int valueOfSlider1;   // �ԐF�̉����l  
    public static int valueOfSlider2;   // ���F�̉����l  
    
   
    public static RangeSlider RangeSlider1;
    public static JLabel RangeSliderValue1;
    public static JLabel RangeSliderValue2;
    
        
    JMenuBar menubar;             // ���j���[�o�[
    JMenu menu1;                  // ���j���[�P
    JMenuItem menuitem1;          // ���j���[�A�C�e���P
    JMenuItem menuitem2;          // ���j���[�A�C�e���Q
    JMenuItem menuitem3;          // ���j���[�A�C�e���R
    JMenuItem menuitem4;          // ���j���[�A�C�e���S
    JMenuItem menuitem5;          // ���j���[�A�C�e���T

    public static BufferedImage readImage = null;   
    public static BufferedImage readImage_ = null;

    public static List<Rectangle> rects;
    public static Rectangle draggingRect;
    volatile boolean dragging;

    public static int x1 = -150;          // �}�E�X�h���b�O�n�_��x���W�@�� �摜��̒��S�_����̑��΍��W
    public static int y1 = -120;          // �}�E�X�h���b�O�n�_��y���W�@�� �摜��̒��S�_����̑��΍��W

    public static int x2 = 150;          // �}�E�X�h���b�O�I�_��x���W�@�� �摜��̒��S�_����̑��΍��W
    public static int y2 = 120;          // �}�E�X�h���b�O�I�_��y���W�@�� �摜��̒��S�_����̑��΍��W
    
    public static File savedImageFolder = new File("./SavedImages");
    public static String savedImagesPath = "./SavedImages";

    public static File annotationImageFolder = new File("./SavedImages/Annotations");
    public static String annotationImagesPath = "./SavedImages/Annotations";

    public static File thermalImageFolder = new File("./SavedImages/ThermalImages");
    public static String thermalImagesPath = "./SavedImages/ThermalImages";
    public static int plotSize = 1;
    
    public static int img_width = 10;    //�ǂݍ��܂��摜(�O������)�̉��Əc
    public static int img_height = 10;   //�J�n��10�ɂ��Ă��邪�A0�łȂ���Ή��ł������B
    
    public static BufferedImage img_gray; //�I���W�i���̃O���[�X�P�[���摜
    public static BufferedImage img_gray_tmp; //�͈͎w���̃O���C�X�P�[���摜
    public static GraphicsEnvironment env;
    public static DisplayMode displayMode;
    public static int disp_width;
    public static int disp_height;
    public static double MaxValue = -7777;   //"����"�O���[�X�P�[���摜(img_gray�ő�p)�ɂ�����ő�l�E�ŏ��l
    public static double MinValue = 7777;     //�{����exif���ǂ�ŉ��x�Ƃ��Ή��t�������B
    public static JButton button_merge;
    public static boolean button_merge_clicked = false;
    public static boolean DragFlag = false;
    
    
    public static BufferedImage blackImage = null;  

    public static boolean flash = false;
  
    public static double narrowThermalMax = 7777;          // �I��͈͓��̉��x�̍ő�l
    public static double narrowThermalMin = -7777;         // �I��͈͓��̉��x�̍ŏ��l
    public static double narrowThermalAve = 0;          // �I��͈͓��̉��x�̕��ϒl
    //public static double narrowThermalWhiteAve = 0;     // �I��͈͓��̔����̈�̉��x�̕��ϒl
    public static int narrowThermalCount = 0;              // �I��͈͓��̉��x�f�[�^�̐�

    public static boolean selectRangeFlag = false;

    public static BufferedImage tmpImage = null;
    public static int selectCount = 0;

    public static int temp_x1 = -150;  //�ꎞ�I��x1���i�[���Ă����ϐ�
    public static int temp_y1 = -120;
    public static int temp_x2 = 150;
    public static int temp_y2 = 120;

    public static int percentage1 = 98;
    //public static int percentage2 = 95;
   
    // �R���X�g���N�^ ( GUI�A�v���P�[�V�����̏����l(�������)�̐ݒ肪�s����)
    public InfraRedApp()
    {

        csvReader1();
        R = new String[line_count];
        G = new String[line_count];
        B = new String[line_count];
        csvReader2();
        
       
        env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        displayMode = env.getDefaultScreenDevice().getDisplayMode();
        
        disp_width = displayMode.getWidth();
        disp_height = displayMode.getHeight();
        
        
        if(orgImagefolder.exists()==true)
        {
            count(orgImagefolder.listFiles());
            orgImageCount = count;
            count = 0;            
        } else
        {
            JOptionPane.showMessageDialog(null, "�u./�v�ɁuOrgImages�v�t�H���_�����݂��Ȃ����߃A�v���P�[�V���������s�ł��܂���B");
        }

        if(infraRedImagefolder.exists()==true)
        {
            count(infraRedImagefolder.listFiles());
            infraRedImageCount = count;           
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "�u./�v�ɁuInfraRedImages�v�t�H���_�����݂��Ȃ����߃A�v���P�[�V���������s�ł��܂���B");
        }

        if(orgImageCount==infraRedImageCount)
        {
            orgImageName = new String[orgImageCount];
            infraRedImageName = new String[infraRedImageCount];            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "OrgImages�t�H���_���̉摜����InfraRedImages�t�H���_���̉摜�����قȂ�܂��B");
        }

        String orgImages_f[] = orgImagefolder.list();
        int orgImageNumber = 0;
        for (String org_image_name: orgImages_f)
        {
            orgImageName[orgImageNumber] = org_image_name;
            orgImageNumber++;
        }

        String infraRedImages_f[] = infraRedImagefolder.list();
        int infraRedImageNumber = 0;

        if (start.exists() == true) {
                start.delete();
            }

        if (stop.exists() == true) {
            stop.delete();
        }

        for (String infraRed_image_name: infraRedImages_f) {        
            infraRedImageName[infraRedImageNumber] = infraRed_image_name;            
            createCSV(infraRedImageName[infraRedImageNumber]);
            thermalCsvName = "./lib/Thermal_CSV/"+infraRedImageName[infraRedImageNumber].replace("JPG", "csv");           
            thermalCsvReader1(thermalCsvName);            
            thermalCsvReader2(thermalCsvName); // thermalCsvData��0�s0�񂩂�511�s639��ɒl���i�[����
            min_max_detector();
            maxAverage = maxAverage + max;
            minAverage = minAverage + min;
            infraRedImageNumber++;
        }   
    
        maxAverage = maxAverage/count;
        minAverage = minAverage/count;
     
        
        MaxValue = maxAverage;
        MinValue = minAverage;
        valueOfSlider1 = (int)( (maxAverage-minAverage)/3 + minAverage );   // �ԐF�̉����l
        //System.out.println("valueOfSlider1 = "+valueOfSlider1);
        valueOfSlider2 = (int)( 2*(maxAverage-minAverage)/3 + minAverage );   // ���F�̉����l
        //System.out.println("valueOfSlider2 = "+valueOfSlider2);

        //thermalCsvName = "./lib/thermal_info"+(currentImageCounter+1)+".csv";
        //thermalCsvReader1(thermalCsvName);
        //thermalCsvReader2(thermalCsvName);   // thermalCsvData��0�s0�񂩂�511�s639��ɒl���i�[����
        thermalCsvReaderMethod(); // ��L�̋@�\���܂Ƃ߂����\�b�h

        blackImage = blackImageGenerator(readImageMethod(infraRedImageName[currentImageCounter]));   // �����摜�𐶐�

        west_Image_Setter(orgImageName[currentImageCounter]);  // WEST�̏�����Ԃ͉�����������Ă��Ȃ����摜
        center_Image_Setter( readImageMethod(infraRedImageName[currentImageCounter]) );  // CENTER�̏�����Ԃ͉�����������Ă��Ȃ����摜        
        //south_Image_Setter( grayScaleConverter(readImageMethod(infraRedImageName[currentImageCounter])) );  // SOUTH�̏�����Ԃ�3�l�����ꂽ�摜 
        south_Image_Setter( blackImage );

        imageNameSetterOnNorth(orgImageName[currentImageCounter]);   // NORTH�̏�����Ԃ͈�Ԗڂ̉摜��
        
        // �E�B���h�E����
        frame = new JFrame();                                  // ���C���E�E�B���h�E�쐬                                          
        //frame.setSize(1340, 1020);                           // ���C���E�E�B���h�E�̃T�C�Y��ݒ�
        frame.setSize(1900, 1050);// ���C���E�E�B���h�E�̃T�C�Y��ݒ�
        frame.setTitle("Thermal Vision");          // �E�B���h�E�̃^�C�g���ݒ�
        frame.setLocationRelativeTo(null);                     // �ŏ��ɉ�ʒ����ɕ\�������悤�ɐݒ�
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // �E�B���h�E����铮��̓o�^

        // GUI���\������R���|�[�l���g��z�u�����郁�\�b�h�̌Ăяo��(GUI�R���|�[�l���g�̔z�u)        
        buildUI();

        // �E�B���h�E�S�̂�����
        frame.setVisible(true);             
    }


    // GUI�R���|�[�l���g���W��E�\�����AGUI�S�̂𐶐����郁�\�b�h
    public void buildUI()
    {                
        // �����UI �k
        panelNORTH = new JPanel();
        BevelBorder borderNORTH = new BevelBorder(BevelBorder.RAISED);  
        panelNORTH.setBorder(borderNORTH);
        panelNORTH.add(labelNORTH);

        // �g�債�����摜��\������p�l���Ɋւ���UI ��
        panelWEST = new JPanel(); 
        BevelBorder borderWEST = new BevelBorder(BevelBorder.RAISED);  
        panelWEST.setBorder(borderWEST);     
        panelWEST.setLayout(new CardLayout());        
        panelWEST.add(labelWEST);
        panelWEST.setPreferredSize(new Dimension(disp_width * 2/5, disp_height * 2/5));
        
        // �g�債���ԊO���摜��\������p�l���Ɋւ���UI ����
        panelCENTER = new JPanel();        
        addMouseMotionListener(this); // CENTER��JLabel(this)�Ƀy�C���g�C�x���g��o�^
        addMouseListener(this);       // CENTER��JLabel(this)�Ƀy�C���g�C�x���g��o�^
        rects = Collections.synchronizedList(new ArrayList<>());
        draggingRect = new Rectangle(0, 0);
        dragging = false;
        BevelBorder borderCENTER = new BevelBorder(BevelBorder.RAISED);
        panelCENTER.setBorder(borderCENTER);     
        panelCENTER.setLayout(new CardLayout());       
        panelCENTER.add(this);        
        
        // �u�m��v�A�uNEXT�v�A�uBACK�v�{�^����\������UI ��
        panelEAST = new JPanel();       
        BevelBorder borderEAST = new BevelBorder(BevelBorder.RAISED);
        panelEAST.setBorder(borderEAST);
        panelEAST.setLayout(new GridLayout(8, 1));  
        okButton = new JButton("�m��(�ۑ�)");     
        nextButton = new JButton("NEXT"); 
        backButton = new JButton("BACK"); 
        regionSelectButton = new JButton("�v���b�g�`���̐ݒ�");
        regionReleaseButton = new JButton("�^���J���[�摜�\��");
        loupeButton = new JButton("���[�y");
        realTimePlotButton = new JButton("���x�����̃}�b�v�o��");
        reportGeneratorButton = new JButton("���|�[�g����");

        okButton.addMouseListener(this);
        nextButton.addMouseListener(this);
        backButton.addMouseListener(this);
        regionSelectButton.addMouseListener(this);
        regionReleaseButton.addMouseListener(this);
        loupeButton.addMouseListener(this);
        realTimePlotButton.addMouseListener(this);
        reportGeneratorButton.addMouseListener(this);

        panelEAST.add(okButton);
        panelEAST.add(nextButton);
        panelEAST.add(backButton);
        panelEAST.add(regionSelectButton);
        panelEAST.add(regionReleaseButton);
        panelEAST.add(loupeButton);
        panelEAST.add(realTimePlotButton);
        panelEAST.add(reportGeneratorButton);
        
        // �g�債��3�l���摜��\������p�l���Ɋւ���UI ��
        panelSOUTH = new JPanel();     
        panelSOUTH.setLayout(new BorderLayout());   
        panelSOUTH.add(labelSOUTH, BorderLayout.CENTER);     
        panelSOUTHWEST = new JPanel();
        BevelBorder borderSOUTHWEST = new BevelBorder(BevelBorder.RAISED);
        panelSOUTHWEST.setBorder(borderSOUTHWEST);
        
              
        panelSOUTHWEST.setPreferredSize(new Dimension(disp_width * 2/5, disp_height * 2/5));
        panelSOUTHWEST.setLayout(new GridLayout(3, 1));
        RangeSlider1 = new RangeSlider( (int)(minAverage), (int)(maxAverage) );
        RangeSliderValue1 = new JLabel();
        

        BigDecimal bd_maxAve = new BigDecimal(maxAverage);  //���f�[�^��BigDecimal�^�ɂ���
        BigDecimal bd3_maxAve = bd_maxAve.setScale(2, BigDecimal.ROUND_HALF_UP);  //������3�ʂŎl�̌ܓ�
        BigDecimal bd_minAve = new BigDecimal(minAverage);  //���f�[�^��BigDecimal�^�ɂ���
        BigDecimal bd3_minAve = bd_minAve.setScale(2, BigDecimal.ROUND_HALF_UP);  //������3�ʂŎl�̌ܓ�
        button_merge = new JButton("���x�̍ő�l�̑�������: "+bd3_maxAve.doubleValue()+"��"+"  |  "+"���x�̍ŏ��l�̑�������: "+bd3_minAve.doubleValue()+"��");
        button_merge.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 20));
        button_merge.setForeground(Color.WHITE);
        button_merge.setBackground(new Color(45, 45, 45));
        button_merge.addMouseListener(this);
        RangeSliderValue1 = new JLabel("     �ԐF�̉����l: " + String.valueOf(valueOfSlider1) +"��"+"\t" + "           ���F�̉����l: " + String.valueOf(valueOfSlider2) + "��" );        
        RangeSliderValue1.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 20));
        RangeSlider1.setValue(valueOfSlider1);  // �����̒l
        RangeSlider1.setUpperValue(valueOfSlider2); //�@�㑤�̒l
        RangeSliderValue1.setForeground(Color.WHITE);
        
        
        RangeSlider1.addChangeListener(new ChangeListener()     // �X���C�_�[�̃C�x���g����
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                RangeSlider slider = (RangeSlider) e.getSource();
               
                RangeSliderValue1.setText("     �ԐF�̉����l: " + String.valueOf(valueOfSlider1) +"��"+"\t" + "           ���F�̉����l: " + String.valueOf(valueOfSlider2)+ "��" );
                //RangeSlider1.setUpperValue(valueOfSlider2);
                
                stateChanged_modoki();
            }
        });
        
        RangeSlider1.setBackground(new Color(45, 45, 45));
        panelSOUTHWEST.add(RangeSliderValue1);
        panelSOUTHWEST.add(RangeSlider1);
        panelSOUTHWEST.add(button_merge);
        
        panelSOUTH.add(panelSOUTHWEST, BorderLayout.LINE_START);
        
        
        panelSOUTHEAST = new JPanel();
        BevelBorder borderSOUTHEAST = new BevelBorder(BevelBorder.RAISED);
        panelSOUTHEAST.setBorder(borderSOUTHEAST);
        panelSOUTHEAST.setLayout(new GridLayout(6, 1));
        //paintButton = new JButton("�摜�����A���S���Y��");
        paintButton = new JButton("���x��臒l��ύX����");
        button1 = new JButton("����1");
        button2 = new JButton("����2");
        button3 = new JButton("����3");
        button4 = new JButton("����4");
        paintButton.addMouseListener(this);
        button1.addMouseListener(this);
        button2.addMouseListener(this);
        button3.addMouseListener(this);
        button4.addMouseListener(this);
     
        panelSOUTHEAST.add(paintButton);
        panelSOUTHEAST.add(button1);
        panelSOUTHEAST.add(button2);
        panelSOUTHEAST.add(button3);
        panelSOUTHEAST.add(button4);
        panelSOUTH.add(panelSOUTHEAST, BorderLayout.LINE_END);

        menubar = new JMenuBar();           // ���j���[�o�[
        menu1 = new JMenu("�t�@�C��");      // ���j���[�P
        menubar.add(menu1);
        menuitem1 = new JMenuItem("����1");
        menuitem2 = new JMenuItem("����2");
        menuitem3 = new JMenuItem("����3");
        menuitem4 = new JMenuItem("����4");
        menuitem5 = new JMenuItem("�A�v���P�[�V�������I������");
        menu1.add(menuitem1);
        menu1.add(menuitem2);
        menu1.add(menuitem3);
        menu1.add(menuitem4);
        menu1.add(menuitem5);

        //�C�x���g���X�i�̓o�^
        menuitem1.addActionListener(this);
        menuitem2.addActionListener(this);
        menuitem3.addActionListener(this);
        menuitem4.addActionListener(this);
        menuitem5.addActionListener(this);

        panelEAST.setBackground(new Color(45, 45, 45));
        panelCENTER.setBackground(new Color(45, 45, 45));
        panelWEST.setBackground(new Color(45, 45, 45));
        panelSOUTH.setBackground(new Color(45, 45, 45));
        panelSOUTHWEST.setBackground(new Color(45, 45, 45));
        panelSOUTHEAST.setBackground(new Color(45, 45, 45));
        panelNORTH.setBackground(new Color(45, 45, 45));
        menubar.setBackground(new Color(45, 45, 45));
        menu1.setForeground(Color.WHITE);
              

        frame.setLayout(new BorderLayout());                             // ��ʑS�̂̃��C�A�E�g��BorderLayout�ɐݒ�
        frame.setJMenuBar(menubar);
        frame.add(panelNORTH, BorderLayout.PAGE_START);                  // btnPanel����ʏ�ɓo�^(�z�u) 
        frame.add(panelCENTER, BorderLayout.CENTER);                     // panelCENTER����ʒ����ɓo�^(�z�u)  
        //frame.add(this, BorderLayout.CENTER);      
        frame.add(panelWEST, BorderLayout.LINE_START);                   // panelWEST����ʍ��ɓo�^(�z�u)
        frame.add(panelEAST, BorderLayout.LINE_END);                     // panelEAST����ʉE�ɓo�^(�z�u)
        frame.add(panelSOUTH, BorderLayout.PAGE_END);                    // panelSOUTH����ʉ��ɓo�^(�z�u)     
    }

    public static void createCSV(String imageName) {
        //String imageName = "test.JPG";

        try{
            //�����^�C���擾
            Runtime runtime = Runtime.getRuntime();

            if (start.exists() == true) {
                start.delete();
            }
      
            if (start.exists() == false) { 
                //exe�����s����
                //runtime.exec(".\\lib\\Save2CSV.exe .\\test_image\\" + imageName );
                runtime.exec(".\\lib\\Exe\\Save2CSV.exe "+imageName );
                start.delete();
            } else {
                JOptionPane.showMessageDialog(null, "Exif���擾����v���O�����̎��s�Ɏ��s���܂����B");
                JOptionPane.showMessageDialog(null, "�A�v���P�[�V�������I�����A�ēx���s���Ă��������B");
                start.delete();
            }
                       
        } catch(Exception ex){
            //�w�肵���t�@�C���������ꍇ�̏���
            JOptionPane.showMessageDialog(null, "./lib/Exe/Save2CSV.exe�����s�ł��܂���ł����B");
            JOptionPane.showMessageDialog(null, "�A�v���P�[�V�������I�����A�ēx���s���Ă��������B");
            start.delete();
        }

        while (true) {
            // �t�@�C���̑��݂��m�F����
            if (stop.exists() == false && start.exists() == true) {
               
            } else if (stop.exists() == true) {    
                if(start.exists() == true) {                
                    start.delete();
                }
                stop.delete();
                break;
            } 
        }

        if (stop.exists() == true) {
            stop.delete();
        }
    }

    // csv�t�@�C�����̃f�[�^�����݂���͈͂𑖍����āA�f�[�^�����݂���͈͂̍s���Ɨ񐔂��擾���郁�\�b�h
    public static void csvReader1() {                                 
        String sLine = "";                                     // �ꎞ�i�[�p�ϐ�(�_�~�[�Ƃ��Ďg���ϐ�)
        ArrayList<String> arrText = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader( new FileReader(colorCsvPath) );
            
            // �t�@�C������1�s���ǂݍ���
            while (sLine != null) {                
                // �t�@�C������1�s���ǂݍ���ReadToEnd�ł�����
                sLine = br.readLine();
                if (sLine != null)
                    arrText.add(sLine); // add���\�b�h�ŒǋL
            }
                       
            br.close();
        } catch( IOException e )  {
            JOptionPane.showMessageDialog(null, "CSV�t�@�C���u"+colorCsvPath+"�v�̓ǂݍ��݂Ɏ��s���܂����B");
        } catch( NumberFormatException e )  {
            JOptionPane.showMessageDialog(null, "CSV�t�@�C���u"+colorCsvPath+"�v�ɕs���ȃt�H�[�}�b�g�����݂��܂��B");
        }
        line_count = arrText.size();

        // �񐔂𐔂��邽�߂ɓK���Ȕz��Ɋi�[���Đ����Ă݂�
        String temp = (String)arrText.get(0);   // string�^�ɃL���X�g
        // split���\�b�h�ŕ�����A���C�ɂ��Đ�����
        String[] temp2 = temp.split(",");
        // �񐔂𐔂���
        col_count = temp2.length;
    }

    // csv�t�@�C�����̃f�[�^�����݂���͈͂𑖍����āA�f�[�^�����݂���͈͂̍s���Ɨ񐔂��擾���郁�\�b�h
    public static void thermalCsvReader1(String csvName) {                                 
        String sLine = "";                                     // �ꎞ�i�[�p�ϐ�(�_�~�[�Ƃ��Ďg���ϐ�)
        ArrayList<String> arrText = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader( new FileReader(csvName) );
            
            // �t�@�C������1�s���ǂݍ���
            while (sLine != null) {                
                // �t�@�C������1�s���ǂݍ���ReadToEnd�ł�����
                sLine = br.readLine();
                if (sLine != null)
                    arrText.add(sLine); // add���\�b�h�ŒǋL
            }
                       
            br.close();
        } catch( IOException e )  {
            JOptionPane.showMessageDialog(null, "���x�f�[�^���i�[���ꂽCSV�t�@�C���u"+csvName+"�v�̓ǂݍ��݂Ɏ��s���܂����B");
        } catch( NumberFormatException e )  {
            JOptionPane.showMessageDialog(null, "���x�f�[�^���i�[���ꂽCSV�t�@�C���u"+csvName+"�v�ɕs���ȃt�H�[�}�b�g�����݂��܂��B");
        }
        thermal_line_count = arrText.size();

        // �񐔂𐔂��邽�߂ɓK���Ȕz��Ɋi�[���Đ����Ă݂�
        String temp = (String)arrText.get(0);   // string�^�ɃL���X�g
        // split���\�b�h�ŕ�����A���C�ɂ��Đ�����
        String[] temp2 = temp.split(",");
        // �񐔂𐔂���
        thermal_col_count = temp2.length;
    }


    // csv�t�@�C���ɋL����Ă���e��v�f�����ꂼ��ɑΉ����镶����^�z��Ɋi�[���郁�\�b�h
    public static void csvReader2() {
        String sLine = ""; // �ꎞ�i�[�p
        
        ArrayList<String> arrText = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader( new FileReader(colorCsvPath) );
            
            // �t�@�C������1�s���ǂݍ���
            while (sLine != null)
            {
                
                // �t�@�C������1�s���ǂݍ���ReadToEnd�ł�����
                sLine = br.readLine();
                if (sLine != null)
                    arrText.add(sLine); // add���\�b�h�ŒǋL
            }
                       
            br.close();
        } catch( IOException e )  {
            JOptionPane.showMessageDialog(null, "CSV�t�@�C���u"+colorCsvPath+"�v�̓ǂݍ��݂Ɏ��s���܂����B");
        } catch( NumberFormatException e )  {
            JOptionPane.showMessageDialog(null, "CSV�t�@�C���u"+colorCsvPath+"�v�ɕs���ȃt�H�[�}�b�g�����݂��܂��B");
        }

        // ArrayList����2�����z��Ɋi�[����D
        // 2�����z��̒�`
        csvData = new String[line_count][col_count];
        int a = 0;
        int b = 0;

        for (String sOutput : arrText) {
            // ��s���ǂݍ���ŁC�e�s��split���\�b�h�ŕ�����
            String[] temp_line = sOutput.split(",");
            for (String value : temp_line)
            {
                csvData[a][b] = value;
                b++;
            }
            b = 0;
            a++;
        }

        
        // imageNames[0]����imageNames[line_count-2]�Ƀf�[�^���i�[�����        
        for (int i = 0; i < line_count; i++) {
            R[i] = csvData[i][0];
        }

        // x[0]����x[line_count-2]�Ƀf�[�^���i�[�����        
        for (int i = 0; i < line_count; i++) {
            G[i] = csvData[i][1];
        }

        // y[0]����y[line_count-2]�Ƀf�[�^���i�[�����        
        for (int i = 0; i < line_count; i++) {
            B[i] = csvData[i][2];
        }                    
    }

    // csv�t�@�C���ɋL����Ă���e��v�f�����ꂼ��ɑΉ����镶����^�z��Ɋi�[���郁�\�b�h
    public static void thermalCsvReader2(String csvName) {
        String sLine = ""; // �ꎞ�i�[�p
        
        ArrayList<String> arrText = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader( new FileReader(csvName) );
            
            // �t�@�C������1�s���ǂݍ���
            while (sLine != null)
            {
                
                // �t�@�C������1�s���ǂݍ���ReadToEnd�ł�����
                sLine = br.readLine();
                if (sLine != null)
                    arrText.add(sLine); // add���\�b�h�ŒǋL
            }
                       
            br.close();
        } catch( IOException e )  {
            JOptionPane.showMessageDialog(null, "���x�f�[�^���i�[���ꂽCSV�t�@�C���u"+csvName+"�v�̓ǂݍ��݂Ɏ��s���܂����B");
        } catch( NumberFormatException e )  {
            JOptionPane.showMessageDialog(null, "���x�f�[�^���i�[���ꂽCSV�t�@�C���u"+csvName+"�v�ɕs���ȃt�H�[�}�b�g�����݂��܂��B");
        }

        // ArrayList����2�����z��Ɋi�[����D
        // 2�����z��̒�`
        thermalCsvData = new String[thermal_line_count][thermal_col_count];
        int a = 0;
        int b = 0;

        for (String sOutput : arrText) {
            // ��s���ǂݍ���ŁC�e�s��split���\�b�h�ŕ�����
            String[] temp_line = sOutput.split(",");
            for (String value : temp_line)
            {
                thermalCsvData[a][b] = value;
                b++;
            }
            b = 0;
            a++;
        }                
    }


    public static void min_max_detector() {
        try {
            max = Double.parseDouble(thermalCsvData[0][0]);
            min = Double.parseDouble(thermalCsvData[0][0]);

            for(int i=0; i<thermal_line_count; i++) {
                for(int j=0; j<thermal_col_count; j++) {
                    if(Double.parseDouble(thermalCsvData[i][j]) > max) {
                        max = Double.parseDouble(thermalCsvData[i][j]);
                    }

                    if(Double.parseDouble(thermalCsvData[i][j]) < min) {
                        min = Double.parseDouble(thermalCsvData[i][j]);
                    }
                }
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "���x�f�[�^���i�[����Ă���CSV�t�@�C����ǂݍ��񂾍ۂɃt�H�[�}�b�g�Ɋւ���G���[���������܂����B");
        }
    }

    public static void thermalCsvReaderMethod() {
        thermalCsvName = "./lib/Thermal_CSV/"+infraRedImageName[currentImageCounter].replace("JPG", "csv");
        thermalCsvReader1(thermalCsvName);
        thermalCsvReader2(thermalCsvName);   // thermalCsvData��0�s0�񂩂�511�s639��ɒl���i�[����
    }

    public static BufferedImage blackImageGenerator(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        blackImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        return blackImage;
    }

    // InfraRedImages�t�H���_����摜��BufferedImage�^�œǂݍ��݁ABufferedImage�^�ŕԂ����\�b�h
    public static BufferedImage readImageMethod(String imageName)
    {
        try
        {
            readImage = ImageIO.read(new File(infraRedImagePath+"/"+imageName));
            img_gray = ImageIO.read(new File(infraRedImagePath+"/"+imageName)); // double�^��2�����z��ɕς���
            img_gray_tmp = ImageIO.read(new File(infraRedImagePath+"/"+imageName));// 
            
            img_width = readImage.getWidth();
            img_height = readImage.getHeight();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "�o�b�N�O���E���h�ł̉摜�ǂݍ��݂Ɏ��s���܂����B");
        }
        return readImage;
    } 
    
    // ���݂̉摜����labelNORTH�ɐݒ肷�郁�\�b�h
    public void imageNameSetterOnNorth(String fileName)
    {
        labelNORTH = new JLabel(fileName, JLabel.CENTER);
        labelNORTH.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 25));
        //labelNORTH.setBackground(new Color(45, 45, 45));
        labelNORTH.setForeground(Color.WHITE);
    }
   
    // �摜��ImageIcon�^�œǂݍ��݁A�摜���k������labelWEST�ɃA�C�R����\��t���郁�\�b�h
    public void west_Image_Setter(String fileName)
    {               
        ImageIcon icon = new ImageIcon(orgImagePath+"/"+fileName);
        //MediaTracker tracker = new MediaTracker(this);  // �����̏I����҂�MediaTracker���`
        //Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * RATIO_WEST), -1, Image.SCALE_SMOOTH); // getScaledInstance�ő傫����ύX����
        double ratio1 = (double) (disp_height * 2/5) / (double) icon.getIconHeight(); //�ق��ق�
        double ratio2 = (double) (disp_width * 2/5) / (double) icon.getIconWidth();
        double ratio_ = ratio1 >= ratio2 ? ratio2 : ratio1;
        //System.out.println("(double) (disp_width/3):" + (double) (disp_width/3));
        Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * ratio_), -1, Image.SCALE_SMOOTH);
        
        //tracker.addImage(bigImg, 1);  // MediaTracker�ŏ����̏I����҂�
        ImageIcon smallIcon = new ImageIcon(smallImg);
        try
        {
            //tracker.waitForAll();
        }
        catch (Exception e)
        {
            System.out.println("Error in west_Image_Setter()");
        }
    
        labelWEST = new JLabel(smallIcon, JLabel.CENTER); 
        //labelWEST = new JLabel(smallIcon, JLabel.RIGHT);
        //labelWEST.setBorder(new LineBorder(Color.WHITE, 2, true)); 
    }

    // �摜��ImageIcon�^�œǂݍ��݁A�摜���k������labelCENTER�ɃA�C�R����\��t���郁�\�b�h
    public void center_Image_Setter(BufferedImage img)
    {
        ImageIcon icon = new ImageIcon(img);
        //MediaTracker tracker = new MediaTracker(this);  // �����̏I����҂�MediaTracker���`
        //Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * RATIO), -1, Image.SCALE_SMOOTH); // getScaledInstance�ő傫����ύX����
        double ratio1 = (double) (disp_height * 2/5) / (double) img.getHeight(); //�ق��ق�
        double ratio2 = (double) (disp_width * 2/5) / (double) img.getWidth();
        ratio = ratio1 >= ratio2 ? ratio2 : ratio1; 
        Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * ratio), -1, Image.SCALE_SMOOTH); // getScaledInstance�ő傫����ύX����
        //tracker.addImage(bigImg, 1);  // MediaTracker�ŏ����̏I����҂�
        ImageIcon smallIcon = new ImageIcon(smallImg);
        try
        {
            //tracker.waitForAll();
        } 
        catch (Exception e) 
        {
            System.out.println("Error in center_Image_Setter()");
        }
    
        //labelCENTER = new JLabel(smallIcon, JLabel.CENTER);     
        this.setIcon(smallIcon);
        this.setHorizontalAlignment(JLabel.CENTER); 
        //labelCENTER.setBorder(new LineBorder(Color.WHITE, 2, true)); 
    }

    // �摜��ImageIcon�^�œǂݍ��݁A�摜���k������labelSOUTH�ɃA�C�R����\��t���郁�\�b�h
    public static void south_Image_Setter(BufferedImage img)
    {       
        ImageIcon icon = new ImageIcon(img);
        //MediaTracker tracker = new MediaTracker(this);  // �����̏I����҂�MediaTracker���`
        //Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * RATIO), -1, Image.SCALE_SMOOTH); // getScaledInstance�ő傫����ύX����
        double ratio1 = (double) (disp_height * 2/5) / (double) img.getHeight(); //�ق��ق�
        double ratio2 = (double) (disp_width * 2/5) / (double) img.getWidth();
        ratio = ratio1 >= ratio2 ? ratio2 : ratio1; 
        Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * ratio), -1, Image.SCALE_SMOOTH);
        //tracker.addImage(bigImg, 1);  // MediaTracker�ŏ����̏I����҂�
        ImageIcon smallIcon = new ImageIcon(smallImg);
        try
        {
            //tracker.waitForAll();
        }
        catch (Exception e) 
        {
            System.out.println("Error in south_Image_Setter()");
        }
    
        labelSOUTH = new JLabel(smallIcon, JLabel.CENTER);  
    }

    public static void count(File[] list) 
    {
        for (File f : list)
        {
            if (f.isDirectory())
            {
                count(f.listFiles());
            } 
            else if (f.isFile()) 
            {
	        count++;
            }
        }
    }
   
    public static BufferedImage DecideMeshColor(int x, int y, BufferedImage img, BufferedImage write, int plotSize)
    {
        int color = 0x000000;

        double thermal = 0;
        int white = 0;
        int red = 0;
        int black = 0;

        
        for(int i = 0; i < plotSize; i++) 
        {
            for(int j = 0; j < plotSize; j++)
            {   
                thermal = (Double.parseDouble(thermalCsvData[y][x]));

                if (thermal >= valueOfSlider2) {                                  
                    white++;
                } else if (thermal >= valueOfSlider1) {                             
                    red++;
                } else {
                    black++;
                }
            }
        }
        
        if (white >= red && white >= black)
            color = 0xFFFFFF;
        else if (red >= white && red >= black)
            color = 0xFF0000;
        
        if (color == 0x000000)
        {
            return write;
        }
        
        for(int i = 0; i < plotSize + 1; i++)
        {
            for(int j = 0; j < plotSize + 1; j++)
            {
                if (i != plotSize && j != plotSize)
                    write.setRGB(x + i, y + j, color);
            }
        }
        return write;
    }
    
    
    public static BufferedImage grayScaleConverter(BufferedImage img) {   // �O���C�X�P�[���摜����͂��ASOUTH�ɔ��f�����郁�\�b�h
        int w = img.getWidth();
        int h = img.getHeight();
        int color, r, g, b;
	        
        BufferedImage write = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        
        for (int y = 0; y < h - plotSize - 1; y=y+(plotSize+1)) 
        {
            for (int x = 0; x < w - plotSize - 1; x=x+(plotSize+1)) 
            {
                write = DecideMeshColor(x, y, img, write, plotSize);
            }
        }          
        return write;      
    }
    
    
    public static BufferedImage narrowRangeGrayScaleConverter(BufferedImage img)   // �͂񂾔͈͂���ő�l�ƍŏ��l�𓾂邾���̋@�\ /////////////////////////////////////////////////
    {   
        int rgb, r, g, b, gray;
        BufferedImage write = null;
        
        int w = img.getWidth();
        int h = img.getHeight();
        
        int x_start = (int)( x1/ratio + img_width / 2 );
        int x_stop  = (int)( x2/ratio + img_width / 2 ) - (plotSize + 1);
        int y_start = (int)( y1/ratio + img_height / 2 );
        int y_stop  = (int)( y2/ratio + img_height / 2 ) - (plotSize + 1);
        
        x_start = x_start > img_width - (plotSize + 1) ? 0: x_start;
        y_start = y_start > img_height - (plotSize + 1) ? 0 : y_start;
        x_start = x_start < 0 ? 0: x_start;
        y_start = y_start < 0 ? 0 :y_start;
        
        x_stop = x_stop > img_width - (plotSize + 1) ? 0: x_stop;
        y_stop = y_stop > img_height - (plotSize + 1) ? 0 : y_stop;
        x_stop = x_stop < 0 ? 0: x_stop;
        y_stop = y_stop < 0 ? 0 : y_stop;
        
        int _x_start = x_start <= x_stop ? x_start : x_stop;
        int _y_start = y_start <= y_stop ? y_start : y_stop;
        int _x_stop  = x_start > x_stop ? x_start : x_stop;
        int _y_stop  = y_start > y_stop ? y_start : y_stop;
        
        
        ///////////////////////////////////////////////////////////////////////////////////       
        MaxValue = -7777;
        MinValue = 7777;

        for (int y = _y_start; y < _y_stop; y++)//y=y+(plotSize+1))
        {
            for (int x = _x_start; x < _x_stop; x++)//x=x+(plotSize+1))
            {
                try {
                    if(Double.parseDouble(thermalCsvData[y][x]) > MaxValue) {
                        MaxValue = (int)(Double.parseDouble(thermalCsvData[y][x]));
                    }

                    if(Double.parseDouble(thermalCsvData[y][x]) < MinValue) {
                        MinValue = (int)(Double.parseDouble(thermalCsvData[y][x]));
                    }
                } catch(NumberFormatException e) {
                    System.out.println("NumberFormatException");
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////
        return img; 
    }
    
   
    public static void RearrangeRainbow()  
    {
        int rgb, r, g, b, gray, newcolor;
        double thermal;
        int w = readImage.getWidth();
        int h = readImage.getHeight();

        //BufferedImage write = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        if (MaxValue <= MinValue) {        
            return;
        }
              
        for (int y = 0; y < h; y++)
        {
            for (int x = 0; x < w; x++)
            {
                try {
                    thermal = Double.parseDouble(thermalCsvData[y][x]);
               
                    if (MaxValue < thermal)
                        thermal = 255;
                    else if (MinValue > thermal)
                        thermal = 0;
                    else
                    {
                        thermal = (thermal - MinValue) * 255. / (MaxValue - MinValue);  /// ���x�f�[�^�̍ő�l�ƍŏ��l�̍�
                    }                
               
                    thermal = thermal > 255. ? 255. : thermal;
                    thermal = thermal < 0. ? 0. : thermal;

                    r = Integer.parseInt(R[(int) thermal]);
                    g = Integer.parseInt(G[(int) thermal]);
                    b = Integer.parseInt(B[(int) thermal]);
                    thermal = ((int) thermal <<16) + ((int) thermal << 8) + (int) thermal;
                    img_gray_tmp.setRGB(x, y, (int) thermal);
                    newcolor = ( r << 16 ) + ( g << 8 ) + b;
                    readImage.setRGB( x, y, newcolor );
                } catch(NullPointerException e) {

                }
            }
        }
        //System.out.println("rearranged");    
    }
    
    public static BufferedImage RearrangeMesh(BufferedImage img)
    {
        int rgb, r, g, b;
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage write = DeepCopy(img);
        
        if (MaxValue <= MinValue) {
            return write;
        }

        for (int y = 0; y < h - (plotSize + 1); y += plotSize + 1)
        {
            for (int x = 0; x < w - (plotSize + 1); x += plotSize + 1)
            {
                write = DecideMeshColor(x, y, img, write, plotSize);
            }
        }
        return write;
    }
    public static BufferedImage DeepCopy(BufferedImage img)
    {
        ColorModel cm = img.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = img.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    public static BufferedImage rainbowConverter(BufferedImage img) // ���̉摜����^���J���[�摜�𐶐����郁�\�b�h    
    {    
        int w = img.getWidth();
    	int h = img.getHeight();
        int color, r, g, b;
        int p;
        int newcolor;
                
        for (int y = 0; y < h; y=y+1)
        {
            for (int x = 0; x < w; x=x+1)
            {
                try {
                    p = (int)((Double.parseDouble(thermalCsvData[y][x]) - minAverage)/(maxAverage - minAverage)*255);
                    //p = p+62;
                    //p = p+12;
                    p = p+25;
                    p = p > 255 ? 255 : p;
                    p = p < 0 ? 0 : p;

                    // r,g,b�ɃJ���[�p���b�g�̒l����
                    r = Integer.parseInt(R[p]);
                    g = Integer.parseInt(G[p]);
                    b = Integer.parseInt(B[p]);
                
                    // r,g,b�̐F������
                    newcolor = ( r << 16 ) + ( g << 8 ) + b;
                
                    // ���������F��(x,y)�ɐݒ�
                    img.setRGB( x, y, newcolor );
                } catch(NullPointerException e) {

                }
            }
    	}                          
        return img;      
    }

    // �C�x���g���X�i(MouseListener�C���^�[�t�F�C�X)�������ۃ��\�b�h������    
    public void mouseClicked(MouseEvent e) 
    {
        if(e.getSource().equals(okButton)) 
        {
             thermalCsvReaderMethod();

             if(savedImageFolder.exists()==false) 
             {
                 savedImageFolder.mkdir();
             }

             if(annotationImageFolder.exists()==false) 
             {
                 annotationImageFolder.mkdir();
             }

             if(thermalImageFolder.exists()==false) 
             {
                 thermalImageFolder.mkdir();
             }

             String imageName = infraRedImageName[currentImageCounter].replace("JPG", "png");
             try 
             {   
                if( (colorFlag==true) && (selectRangeFlag==true) && (selectCount>0) ) {               
                    ImageIO.write(grayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))), "png", new File(annotationImagesPath+"/"+imageName));
                    JOptionPane.showMessageDialog(null, "�A�m�e�[�V�����摜 : "+imageName+"��"+"�u"+annotationImagesPath+"�v  �����ɕۑ����܂����B");

                    ImageIO.write(tmpImage, "png", new File(thermalImagesPath+"/"+imageName));
                    JOptionPane.showMessageDialog(null, "�T�[�}���摜 : "+imageName+"��"+"�u"+thermalImagesPath+"�v  �����ɕۑ����܂����B"); 
                }      
                    
                if( (colorFlag==true) && (selectCount==0) ) {                    
                    ImageIO.write(grayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))), "png", new File(annotationImagesPath+"/"+imageName));
                    JOptionPane.showMessageDialog(null, "�A�m�e�[�V�����摜 : "+imageName+"��"+"�u"+annotationImagesPath+"�v  �����ɕۑ����܂����B");
                  
                    ImageIO.write(readImage, "png", new File(thermalImagesPath+"/"+imageName));  
                    JOptionPane.showMessageDialog(null, "�T�[�}���摜 : "+imageName+"��"+"�u"+thermalImagesPath+"�v  �����ɕۑ����܂����B");
                }

                if( (colorFlag==false) && (selectRangeFlag==false) ) {                      
                    ImageIO.write(readImage, "png", new File(thermalImagesPath+"/"+imageName));  
                    JOptionPane.showMessageDialog(null, "�T�[�}���摜 : "+imageName+"��"+"�u"+thermalImagesPath+"�v  �����ɕۑ����܂����B");
                }
                
                if( (colorFlag==false) && (selectRangeFlag==true) ) {                    
                    ImageIO.write(readImage, "png", new File(thermalImagesPath+"/"+imageName));
                    JOptionPane.showMessageDialog(null, "�T�[�}���摜 : "+imageName+"��"+"�u"+thermalImagesPath+"�v  �����ɕۑ����܂����B");
                }
            }
            catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(null, "�A�m�e�[�V�����摜�̕ۑ��Ɏ��s���܂����B");
            }
            catch(IllegalArgumentException ioe2) 
            {

            }
        }

        if(e.getSource().equals(nextButton)) 
        {
        
            if(x2==0&&y2==0) {
                x1 = temp_x1;
                x2 = temp_x2;
                y1 = temp_y1;
                y2 = temp_y2;
            }
          
            if( (x1>=x2)&&(y1>=y2) ) { 
                x1 = temp_x1;
                x2 = temp_x2;
                y1 = temp_y1;
                y2 = temp_y2;
            }

            if( (x1>=x2)&&(y1<=y2) ) { 
                x1 = temp_x1;
                x2 = temp_x2;
                y1 = temp_y1;
                y2 = temp_y2;
            }

            if( (x1<=x2)&&(y1>=y2) ) { 
                x1 = temp_x1;
                x2 = temp_x2;
                y1 = temp_y1;
                y2 = temp_y2;
            }

            MaxValue = -7777;
            MinValue = 7777;
            if(currentImageCounter < (count-1)) 
            {
                currentImageCounter = currentImageCounter + 1;
                thermalCsvReaderMethod();
                
                panelWEST.remove(labelWEST);
                west_Image_Setter(orgImageName[currentImageCounter]);
                panelWEST.add(labelWEST);
                
                if(colorFlag==true) 
                {                   
                    panelCENTER.remove(this);            
                    narrowRangeGrayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter])));  
                    RearrangeRainbow();
                    center_Image_Setter(imageProcessing(readImage));
                    //center_Image_Setter( rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter])) );               
                    panelCENTER.add(this);
                } 
                else 
                {
                    panelCENTER.remove(this);
                    center_Image_Setter( readImageMethod(infraRedImageName[currentImageCounter]) );/////////////////////////////////////////////////////////////////////////
                    panelCENTER.add(this);
                }
             
                if(colorFlag==true) 
                {
                    thermalCsvReaderMethod();
                    panelSOUTH.remove(labelSOUTH);               
                    //south_Image_Setter( grayScaleConverter(readImageMethod(infraRedImageName[currentImageCounter])) );
                    south_Image_Setter( grayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))) );
                    panelSOUTH.add(labelSOUTH);
                } 
                else 
                {
                    panelSOUTH.remove(labelSOUTH);               
                    //south_Image_Setter( grayScaleConverter(readImageMethod(infraRedImageName[currentImageCounter])) );
                    south_Image_Setter( blackImage );
                    panelSOUTH.add(labelSOUTH);
                }

                panelNORTH.remove(labelNORTH);
                imageNameSetterOnNorth(orgImageName[currentImageCounter]);
                panelNORTH.add(labelNORTH);

                frame.setVisible(true);
            }
                                         
        }
 
        if(e.getSource().equals(backButton)) 
        {
            if(x2==0&&y2==0) {  // �Z���^�[�̉摜���Ǐ��I�ɉ��������Ƃ�
                x1 = temp_x1;
                x2 = temp_x2;
                y1 = temp_y1;
                y2 = temp_y2;
            }

            if( (x1>=x2)&&(y1>=y2) ) { 
                x1 = temp_x1;
                x2 = temp_x2;
                y1 = temp_y1;
                y2 = temp_y2;
            }

            if( (x1>=x2)&&(y1<=y2) ) { 
                x1 = temp_x1;
                x2 = temp_x2;
                y1 = temp_y1;
                y2 = temp_y2;
            }

            if( (x1<=x2)&&(y1>=y2) ) { 
                x1 = temp_x1;
                x2 = temp_x2;
                y1 = temp_y1;
                y2 = temp_y2;
            }

            MaxValue = -7777;
            MinValue = 7777;

            if(currentImageCounter > 0) 
            {
                currentImageCounter = currentImageCounter - 1;
                thermalCsvReaderMethod();
              
                panelWEST.remove(labelWEST);
                west_Image_Setter(orgImageName[currentImageCounter]);
                panelWEST.add(labelWEST);
                       
                if(colorFlag==true) 
                {        
                    panelCENTER.remove(this);                                     
                    //center_Image_Setter( rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter])) ); ///////////////////////////////////////////////////////     
                    narrowRangeGrayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter])));
                    RearrangeRainbow(); 
                    center_Image_Setter(imageProcessing(readImage));                   
                    panelCENTER.add(this);
                } 
                else 
                {
                    panelCENTER.remove(this);
                    center_Image_Setter( readImageMethod(infraRedImageName[currentImageCounter]) );
                    panelCENTER.add(this);
                }
                
                if(colorFlag==true) 
                {
                    thermalCsvReaderMethod();
                    panelSOUTH.remove(labelSOUTH);               
                    //south_Image_Setter( grayScaleConverter(readImageMethod(infraRedImageName[currentImageCounter])) );
                    south_Image_Setter( grayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))) );
                    panelSOUTH.add(labelSOUTH);
                } 
                else 
                {
                    panelSOUTH.remove(labelSOUTH);               
                    //south_Image_Setter( grayScaleConverter(readImageMethod(infraRedImageName[currentImageCounter])) );
                    south_Image_Setter( blackImage );
                    panelSOUTH.add(labelSOUTH);
                }

                panelNORTH.remove(labelNORTH);
                imageNameSetterOnNorth(orgImageName[currentImageCounter]);
                panelNORTH.add(labelNORTH);

                frame.setVisible(true);
            }          
        }

        if(e.getSource().equals(regionSelectButton)) 
        {            
            new RadioSample("�v���b�g�̑傫���ݒ�");
        }

        if(e.getSource().equals(regionReleaseButton))
        {                
            if(colorFlag==false) 
            {
                thermalCsvReaderMethod();

                panelCENTER.remove(this);
                center_Image_Setter( rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter])) );
                panelCENTER.add(this);

                panelSOUTH.remove(labelSOUTH);
                south_Image_Setter( grayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))) );
                panelSOUTH.add(labelSOUTH);

                colorFlag = true;
                regionReleaseButton.setText("���摜�\��");                
                frame.setVisible(true);               
            }
            else
            {
                thermalCsvReaderMethod();

                panelCENTER.remove(this);
                center_Image_Setter( readImageMethod(infraRedImageName[currentImageCounter]) );
                panelCENTER.add(this);

                panelSOUTH.remove(labelSOUTH);   
                //south_Image_Setter( grayScaleConverter(readImageMethod(infraRedImageName[currentImageCounter])) );
                south_Image_Setter(blackImageGenerator(readImage));
                panelSOUTH.add(labelSOUTH);

                colorFlag = false;
                regionReleaseButton.setText("�^���J���[�摜�\��");                
                frame.setVisible(true);                
            }
        }

        if(e.getSource().equals(loupeButton))
        {            
            if(flash==true) {
                 flash = false;
             }

            try
            {
                Runtime rt = Runtime.getRuntime();
                rt.exec("java -jar \".\\lib\\jar\\Loupe.jar\"");
                
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null, "�O���v���O�����̎��s�Ɏ��s���܂����B");
            }            
        }

        if(e.getSource().equals(realTimePlotButton))
        {                        
            try
            {
                Runtime rt = Runtime.getRuntime();
                rt.exec("java -jar \".\\lib\\jar\\difference.jar\"");
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null, "�O���v���O�����̎��s�Ɏ��s���܂����B");
            }
                        
        }

        if(e.getSource().equals(reportGeneratorButton))
        {
            //System.out.println("���x���̕�:"+this.getWidth()+"\n"+"���x���̍���:"+this.getHeight());            
        }

        if(e.getSource().equals(paintButton))
        {
            new ThermalSlider();
        }

        if(e.getSource().equals(button1))
        {

        }

        if(e.getSource().equals(button2))
        {

        }
  
        if(e.getSource().equals(button3))
        {

        }

        if(e.getSource().equals(button4))
        {

        }

        if (e.isControlDown())
        {   // ��`���N���A(�uCtrl�v+�u�}�E�X�N���b�N�v)
            //rects.clear();
        }
        
        if(e.getSource().equals(button_merge)) 
        {
            /*
            if ((button_merge_clicked) == false && colorFlag == true)
            {
                //thermalCsvReaderMethod();
                panelCENTER.remove(this);
                //narrowRangeGrayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter])));
                //RearrangeRainbow(); 
                center_Image_Setter( RearrangeMesh(readImage));
                //center_Image_Setter( RearrangeMesh(imageProcessing(readImage)));
                panelCENTER.add(this);
                button_merge_clicked =true;
            }
            else if (colorFlag == true)
            {
                //thermalCsvReaderMethod();
                panelCENTER.remove(this);
                center_Image_Setter( readImage );
                panelCENTER.add(this);
                button_merge_clicked =false;
            }
            */
        }
    }
     
    public void mouseEntered(MouseEvent e)
    {
        // �����
    }
    
    public void mouseExited(MouseEvent e)
    {
        // �����
    }

    public void mouseMoved(MouseEvent e)
    {
        // �����
    }
   
    public void mousePressed(MouseEvent e)
    {       // �}�E�X�����������Ƃ��̃C�x���g         
        if (!(e.getSource().equals(this))) {  //�Z���^�[�̉摜�ȊO�̗̈���N���b�N�����Ƃ��͔������Ȃ��悤�ɂ���B
            return;
        }

        x1 = e.getX() - panelCENTER.getWidth() / 2;//e.getX();
        y1 = e.getY() - panelCENTER.getHeight() / 2;//e.getY();
        
        x1 = x1 >= (double) img_width / 2.0 * ratio ? (int)(img_width / 2 * ratio ): x1;
        y1 = y1 >= (double) img_height / 2.0 * ratio ? (int)(img_height / 2 * ratio ): y1;
        x1 = x1 <= (double) -img_width / 2.0 * ratio ? (int)(-img_width / 2 * ratio ): x1;
        y1 = y1 <= (double) -img_height / 2.0 * ratio ? (int)(-img_height / 2 * ratio ): y1;
        
        draggingRect.setBounds(x1 + panelCENTER.getWidth()/2, y1 + panelCENTER.getHeight()/2, 0, 0);      // �}�E�X�h���b�O�̊J�n�_        
        dragging = true;
        rects.clear();
        x2 = 0;
        y2 = 0;
        repaint();  
    }
    

    public void mouseReleased(MouseEvent e)
    {      // �}�E�X�𗣂����Ƃ��̃C�x���g
        dragging = false;
        
        Dimension rectSize = draggingRect.getSize();
        if (rectSize.getWidth() > 0 && rectSize.getHeight() > 0)
        {
            rects.add(new Rectangle(draggingRect));
        }
        
        if(colorFlag==true && DragFlag == true) {
            if( (x1<x2)&&(y1<y2) ) { 
                thermalCsvReaderMethod();/////////////////////////////////////////////////////////
                panelCENTER.remove(this);              
                narrowRangeGrayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))); ////////////////////////////////////////////////////////////////////
                RearrangeRainbow();            
                center_Image_Setter(imageProcessing(readImage)); //img_gray_tmp
                panelCENTER.add(this);
                frame.setVisible(true);
                DragFlag = false;   
                draggingRect.setBounds(0, 0, 0, 0);
                repaint();
                selectRangeFlag = true;             
            }                           
        }
        else
        {
                
        }
        
        draggingRect.setBounds(0, 0, 0, 0);
        repaint();
        selectRangeFlag = true;

        if((x2!=0)&&(y2!=0)&&(x1<x2)&&(y1<y2)) {
            temp_x1 = x1;
            temp_x2 = x2;
            temp_y1 = y1;
            temp_y2 = y2;
        }       
    }


   
    public void mouseDragged(MouseEvent e)  // �}�E�X���h���b�O�����Ƃ��̃C�x���g//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    {   
        if (DragFlag == false){
           DragFlag = true;
        }            

        x2 = e.getX() - panelCENTER.getWidth() / 2;
        y2 = e.getY() - panelCENTER.getHeight() / 2;
        x2 = x2 >= (double) img_width / 2.0 * ratio ? (int)(img_width / 2 * ratio ): x2;
        y2 = y2 >= (double) img_height / 2.0 * ratio ? (int)(img_height / 2 * ratio ): y2;
        x2 = x2 <= (double) -img_width / 2.0 * ratio ? (int)(-img_width / 2 * ratio ): x2;
        y2 = y2 <= (double) -img_height / 2.0 * ratio ? (int)(-img_height / 2 * ratio ): y2;
        
        if( (x1<x2)&&(y1<y2) ) {
            draggingRect.setSize(x2 - x1, y2 - y1);
            repaint();
        }
    }
    
    
    public void stateChanged_modoki() // �C�x���g���ɒl���i�[
    {
        
        valueOfSlider1 = RangeSlider1.getValue();
        
        valueOfSlider2 = RangeSlider1.getUpperValue();///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
        if(colorFlag == true)
        {
            thermalCsvReaderMethod();
            panelSOUTH.remove(labelSOUTH);
            south_Image_Setter( grayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))) );
            panelSOUTH.add(labelSOUTH);
        }
        else
        {
            panelSOUTH.remove(labelSOUTH);   
            //south_Image_Setter( grayScaleConverter(readImageMethod(infraRedImageName[currentImageCounter])) );
            south_Image_Setter( blackImage );
            panelSOUTH.add(labelSOUTH);
        }

        if( (x1>-img_width/2) && (y1>-img_height/2) && (x2<img_width/2) && (y2<img_height/2) )
        {
            if(colorFlag==true)
            {
                //panelCENTER.remove(this);
                //center_Image_Setter(narrowRangeGrayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))) ); //////////////////////////////////////////////////////////////
                //panelCENTER.add(this);
                //frame.setVisible(true);
            }
            else
            {
                panelCENTER.remove(this);
                center_Image_Setter( narrowRangeGrayScaleConverter(readImageMethod(infraRedImageName[currentImageCounter])) ); ///////////////////////////////////////////////////////////////////////////
                panelCENTER.add(this);
                frame.setVisible(true);
            }
        }  
    }

    //���j���[�̃C�x���g����
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand() == "����1")
        {

        }

        if (e.getActionCommand() == "����2")
        {

        }

        if (e.getActionCommand() == "����3")
        {

        }

        if (e.getActionCommand() == "����4")
        {

        }

        if (e.getActionCommand() == "�A�v���P�[�V�������I������")
        {
            System.exit(0);  // �I��
        }
    }
    
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);        
        
        g.setXORMode(Color.WHITE);
        
        if (dragging)
        {
            g.drawRect(draggingRect.x, draggingRect.y, draggingRect.width, draggingRect.height);
        }

        for (Rectangle r : rects)
        {
            g.drawRect(r.x, r.y, r.width, r.height);
        }
    }


    public static int a(int c) {
        return c>>>24;
    }
    public static int r(int c) {
        return c>>16&0xff;
    }
    public static int g(int c) {
        return c>>8&0xff;
    }
    public static int b(int c) {
        return c&0xff;
    }
    public static int rgb(int r,int g,int b) {
        return 0xff000000 | r <<16 | g <<8 | b;
    }
    public static int argb(int a,int r,int g,int b) {
        return a<<24 | r <<16 | g <<8 | b;
    }    

    // ���ӏ������o���郁�\�b�h
    public static BufferedImage imageProcessing(BufferedImage img) {           
        int w = img.getWidth();
    	int h = img.getHeight();
        int color, r, g, b;
        int p;
        int newcolor;
        
        thermalCsvReaderMethod();
        narrowThermalCount = 0;
        narrowThermalMax = Double.parseDouble(thermalCsvData[(int)(y1/ratio+(h/2))][(int)(x1/ratio+(w/2))]);
        narrowThermalMin = Double.parseDouble(thermalCsvData[(int)(y1/ratio+(h/2))][(int)(x1/ratio+(w/2))]);

        ArrayList<Integer> intListX1 = new ArrayList<Integer>();
        ArrayList<Integer> intListY1 = new ArrayList<Integer>();

        ArrayList<Integer> intListX2 = new ArrayList<Integer>();
        ArrayList<Integer> intListY2 = new ArrayList<Integer>();

        for (int y = (int)(y1/ratio+(h/2)); y < (int)(y2/ratio+(h/2)); y=y+1) {   // �I��͈͓��𑖍�
            for (int x = (int)(x1/ratio+(w/2)); x < (int)(x2/ratio+(w/2)); x=x+1) {
        
                narrowThermalCount = narrowThermalCount + 1;
                narrowThermalAve = narrowThermalAve + Double.parseDouble(thermalCsvData[y][x]);   // ���̒i�K�ł�narrowThermalAve�ɂ͉��x�̑��a����������(���narrowThermalCount�Ŋ���)

                if(Double.parseDouble(thermalCsvData[y][x]) > narrowThermalMax) {
                    narrowThermalMax = Double.parseDouble(thermalCsvData[y][x]);
                }

                if(Double.parseDouble(thermalCsvData[y][x]) < narrowThermalMin) {
                    narrowThermalMin = Double.parseDouble(thermalCsvData[y][x]);
                }
                
            }
   	}    

        boolean flag = false;
        for (int y = (int)(y1/ratio+(h/2)); y < (int)(y2/ratio+(h/2)); y=y+1) {   // �I��͈͓��𑖍�
            for (int x = (int)(x1/ratio+(w/2)); x < (int)(x2/ratio+(w/2)); x=x+1) {         
                //------���x�Ō��o����o�[�W����-----------------------------------------------------------------
                if( Double.parseDouble(thermalCsvData[y][x]) >= narrowThermalMax*percentage1/100 ){ 
               
                    intListX1.add(x);
                    intListY1.add(y);                              
                } 
                //-----------------------------------------------------------------------------------------------

                if( Double.parseDouble(thermalCsvData[y][x]) >= narrowThermalMax*(percentage1-3)/100 ){ 
                   
                    intListX2.add(x);
                    intListY2.add(y);                              
                } 
            } 
        }


        int maxX1 = (int)(Collections.max(intListX1));
        int minX1 = (int)(Collections.min(intListX1));
        int maxY1 = (int)(Collections.max(intListY1));
        int minY1 = (int)(Collections.min(intListY1));

        //for (int y = minY1; y < maxY1; y=y+1) {   /////////////////////////////////
        //    for (int x = minX1; x < maxX1; x=x+1) {         
        //        if( Double.parseDouble(thermalCsvData[y][x]) < narrowThermalMax*percentage1/100 ){ 
        //
        //        }


        int maxX2 = (int)(Collections.max(intListX2));
        int minX2 = (int)(Collections.min(intListX2));
        int maxY2 = (int)(Collections.max(intListY2));
        int minY2 = (int)(Collections.min(intListY2));
       
        
        Graphics2D circle1 = img.createGraphics(); 
        circle1.setPaint(Color.black);
        BasicStroke stroke_1 = new BasicStroke(2.0f);
        circle1.setStroke(stroke_1);               
        circle1.drawOval(minX1, minY1, (int)(maxX1-minX1), (int)(maxY1-minY1)); 
        circle1.dispose();

        /*
        Graphics2D circle2 = img.createGraphics(); 
        circle2.setPaint(Color.green);
        BasicStroke stroke_2 = new BasicStroke(2.0f);
        circle2.setStroke(stroke_2);               
        circle2.drawOval(minX2, minY2, (int)(maxX2-minX2), (int)(maxY2-minY2)); 
        circle2.dispose();
        */


        narrowThermalAve = narrowThermalAve / narrowThermalCount;
        BigDecimal bd = new BigDecimal(narrowThermalAve);  //���f�[�^��BigDecimal�^�ɂ���
        BigDecimal bd3 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);  //������3�ʂŎl�̌ܓ�
        
        Graphics2D graphics = img.createGraphics(); 
        graphics.setPaint(Color.CYAN);
        BasicStroke stroke = new BasicStroke(8.0f);
        graphics.setStroke(stroke);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawRect((int)(x1/ratio+(w/2)), (int)(y1/ratio+(h/2)), (int)((x2-x1)/ratio), (int)((y2-y1)/ratio));
        graphics.drawString("MAX="+narrowThermalMax, (int)(x1/ratio+(w/2)), (int)(y1/ratio+(h/2)-55)); // drawString(String str, int x, int y)
        graphics.drawString("MIN="+narrowThermalMin, (int)(x1/ratio+(w/2)), (int)(y1/ratio+(h/2)-35)); // drawString(String str, int x, int y)
        graphics.drawString("AVE="+bd3.doubleValue(), (int)(x1/ratio+(w/2)), (int)(y1/ratio+(h/2)-15)); // drawString(String str, int x, int y)
        //valueOfSlider1 = (int)(bd3.doubleValue());////////////////////////////////////////////////////////////////////////////////////////////////////
        //valueOfSlider2 = (int)(narrowThermalMax-1);/////////////////////////////////////////////////////////////////////////////////////////////////////
        //----------------------------------------------------------------------------------------------------------------------------------------------
        RangeSlider1.setValue((int)(bd3.doubleValue()));
        RangeSlider1.setUpperValue((int)(narrowThermalMax-1));
        RangeSliderValue1.setText("     �ԐF�̉����l: " + String.valueOf((int)(bd3.doubleValue())) +"��"+"\t" + "           ���F�̉����l: " + String.valueOf((int)(narrowThermalMax-1))+ "��" );
        //RangeSlider1.setValue(valueOfSlider1);
        //RangeSlider1.setUpperValue(valueOfSlider2);/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        thermalCsvReaderMethod();
        panelSOUTH.remove(labelSOUTH);
        south_Image_Setter( grayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))) );
        panelSOUTH.add(labelSOUTH);
        //----------------------------------------------------------------------------------------------------------------------------------------------
        graphics.dispose();
  
        selectCount++;
        tmpImage = img;
        //System.out.println("x="+(int)(x1/ratio+(w/2))+"\n"+"y="+(int)(y1/ratio+(h/2)) );
        
        return img;      
    }

    public static void setPercentage(int a) {
        percentage1 = a;
    }

    public static int getPercentage() {
        return percentage1;
    }
              
    //public void initTimer() {
    //  Timer timer = new Timer(true);
    //  timer.schedule(new TimerTask() {
    //     @Override
    //     public void run() {
    //        //if (flash) { 
    //        //   flashMethod();
    //        //}
    //     }
    //  }, 0, 1000);
    //}
   
    // ���C�����\�b�h (�R���X�g���N�^���N�����邽�߂����ɗ��p�����)
    public static void main(String[] args)
    {         
        SwingUtilities.invokeLater(new Runnable(){   
            public void run() {   
                new InfraRedApp(); 
            }  
        });                                
    }
}

class RadioSample implements MouseListener
{
    public static JRadioButton[] radio;
    public static JFrame radioFrame;    
    public static String selectRadio = "";
    public static String nonSelectRadio = "";
    JButton button1;
    JButton button2;
    JPanel p;

    RadioSample(String title)
    {
        radioFrame = new JFrame();
        radioFrame.setTitle(title);
        radioFrame.setSize(300, 100);
        //radioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //radioFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        radioFrame.setLocationRelativeTo(null);

        p = new JPanel();

        radio = new JRadioButton[5];
        if( InfraRedApp.plotSize == 1 )
        {
            radio[0] = new JRadioButton("1", true);
            radio[1] = new JRadioButton("2");
            radio[2] = new JRadioButton("3");
            radio[3] = new JRadioButton("4");
            radio[4] = new JRadioButton("5");
        }
 
        if( InfraRedApp.plotSize == 2 )
        {
            radio[0] = new JRadioButton("1");
            radio[1] = new JRadioButton("2", true);
            radio[2] = new JRadioButton("3");
            radio[3] = new JRadioButton("4");
            radio[4] = new JRadioButton("5");
        }

        if( InfraRedApp.plotSize == 3 )
        {
            radio[0] = new JRadioButton("1");
            radio[1] = new JRadioButton("2");
            radio[2] = new JRadioButton("3", true);
            radio[3] = new JRadioButton("4");
            radio[4] = new JRadioButton("5");
        }

        if( InfraRedApp.plotSize == 4 )
        {
            radio[0] = new JRadioButton("1");
            radio[1] = new JRadioButton("2");
            radio[2] = new JRadioButton("3");
            radio[3] = new JRadioButton("4", true);
            radio[4] = new JRadioButton("5");
        }

        if( InfraRedApp.plotSize == 5 )
        {
            radio[0] = new JRadioButton("1");
            radio[1] = new JRadioButton("2");
            radio[2] = new JRadioButton("3");
            radio[3] = new JRadioButton("4");
            radio[4] = new JRadioButton("5", true);
        }
        

        ButtonGroup group = new ButtonGroup();
        group.add(radio[0]);
        group.add(radio[1]);
        group.add(radio[2]);
        group.add(radio[3]);
        group.add(radio[4]);

        p.add(radio[0]);
        p.add(radio[1]);
        p.add(radio[2]);
        p.add(radio[3]);
        p.add(radio[4]);

        JPanel buttonpanel = new JPanel();

        button1 = new JButton("�m��");
        button2 = new JButton("����");

        button1.addMouseListener(this);
        button2.addMouseListener(this);

        buttonpanel.add(button1);
        buttonpanel.add(button2);

        Container contentPane = radioFrame.getContentPane();
        contentPane.add(p, BorderLayout.CENTER);
        contentPane.add(buttonpanel, BorderLayout.SOUTH);
        
        radioFrame.setVisible(true);
    }

    public void mouseClicked(MouseEvent e)
    {
        if(e.getSource().equals(button1))
        {           
            for (int i = 0 ; i < radio.length; i++)
            {
                if (radio[i].isSelected())
                {                    
                    selectRadio = radio[i].getText();                                                 
                }
                else
                {
                    nonSelectRadio = radio[i].getText();                    
                }
            }
           
            InfraRedApp.plotSize = Integer.parseInt(selectRadio);
                        
            radioFrame.dispose();
        }
        
        if(e.getSource().equals(button2))
        {
            radioFrame.dispose();
        }
    }

    public void mouseEntered(MouseEvent e)
    {
        // �����
    }

    public void mouseExited(MouseEvent e)
    {
        // �����
    }

    public void mousePressed(MouseEvent e)
    {
        // �����
    }

    public void mouseReleased(MouseEvent e)
    {
        // �����
    }    
}