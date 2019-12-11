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
    public static String[][] thermalCsvData;  // thermalのCSV

    public static String thermalCsvName;
    public static double max = 0;
    public static double min = 0;

    public static double maxAverage = 0;
    public static double minAverage = 0;

    public static String[] R; 
    public static String[] G;
    public static String[] B;

    public static boolean colorFlag = false;

    public static int p = 127;    // RGBの平均値(グレースケール画素値)

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

    public static JFrame frame;     // 画面全体のフレーム

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

    public static ImageIcon icon;   // 拡大画像として使用する画像をImageIcon型として読み込む際に使用する変数

    
    public static int SLIDER_WIDTH;
       
    public static int valueOfSlider1;   // 赤色の下限値  
    public static int valueOfSlider2;   // 白色の下限値  
    
   
    public static RangeSlider RangeSlider1;
    public static JLabel RangeSliderValue1;
    public static JLabel RangeSliderValue2;
    
        
    JMenuBar menubar;             // メニューバー
    JMenu menu1;                  // メニュー１
    JMenuItem menuitem1;          // メニューアイテム１
    JMenuItem menuitem2;          // メニューアイテム２
    JMenuItem menuitem3;          // メニューアイテム３
    JMenuItem menuitem4;          // メニューアイテム４
    JMenuItem menuitem5;          // メニューアイテム５

    public static BufferedImage readImage = null;   
    public static BufferedImage readImage_ = null;

    public static List<Rectangle> rects;
    public static Rectangle draggingRect;
    volatile boolean dragging;

    public static int x1 = -150;          // マウスドラッグ始点のx座標　→ 画像上の中心点からの相対座標
    public static int y1 = -120;          // マウスドラッグ始点のy座標　→ 画像上の中心点からの相対座標

    public static int x2 = 150;          // マウスドラッグ終点のx座標　→ 画像上の中心点からの相対座標
    public static int y2 = 120;          // マウスドラッグ終点のy座標　→ 画像上の中心点からの相対座標
    
    public static File savedImageFolder = new File("./SavedImages");
    public static String savedImagesPath = "./SavedImages";

    public static File annotationImageFolder = new File("./SavedImages/Annotations");
    public static String annotationImagesPath = "./SavedImages/Annotations";

    public static File thermalImageFolder = new File("./SavedImages/ThermalImages");
    public static String thermalImagesPath = "./SavedImages/ThermalImages";
    public static int plotSize = 1;
    
    public static int img_width = 10;    //読み込まれる画像(三枚共通)の横と縦
    public static int img_height = 10;   //開始を10にしているが、0でなければ何でもいい。
    
    public static BufferedImage img_gray; //オリジナルのグレースケール画像
    public static BufferedImage img_gray_tmp; //範囲指定後のグレイスケール画像
    public static GraphicsEnvironment env;
    public static DisplayMode displayMode;
    public static int disp_width;
    public static int disp_height;
    public static double MaxValue = -7777;   //"元の"グレースケール画像(img_grayで代用)における最大値・最小値
    public static double MinValue = 7777;     //本当はexif情報読んで温度とも対応付けたい。
    public static JButton button_merge;
    public static boolean button_merge_clicked = false;
    public static boolean DragFlag = false;
    
    
    public static BufferedImage blackImage = null;  

    public static boolean flash = false;
  
    public static double narrowThermalMax = 7777;          // 選択範囲内の温度の最大値
    public static double narrowThermalMin = -7777;         // 選択範囲内の温度の最小値
    public static double narrowThermalAve = 0;          // 選択範囲内の温度の平均値
    //public static double narrowThermalWhiteAve = 0;     // 選択範囲内の白い領域の温度の平均値
    public static int narrowThermalCount = 0;              // 選択範囲内の温度データの数

    public static boolean selectRangeFlag = false;

    public static BufferedImage tmpImage = null;
    public static int selectCount = 0;

    public static int temp_x1 = -150;  //一時的にx1を格納しておく変数
    public static int temp_y1 = -120;
    public static int temp_x2 = 150;
    public static int temp_y2 = 120;

    public static int percentage1 = 98;
    //public static int percentage2 = 95;
   
    // コンストラクタ ( GUIアプリケーションの初期値(初期画面)の設定が行われる)
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
            JOptionPane.showMessageDialog(null, "「./」に「OrgImages」フォルダが存在しないためアプリケーションを実行できません。");
        }

        if(infraRedImagefolder.exists()==true)
        {
            count(infraRedImagefolder.listFiles());
            infraRedImageCount = count;           
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "「./」に「InfraRedImages」フォルダが存在しないためアプリケーションを実行できません。");
        }

        if(orgImageCount==infraRedImageCount)
        {
            orgImageName = new String[orgImageCount];
            infraRedImageName = new String[infraRedImageCount];            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "OrgImagesフォルダ内の画像数とInfraRedImagesフォルダ内の画像数が異なります。");
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
            thermalCsvReader2(thermalCsvName); // thermalCsvDataの0行0列から511行639列に値を格納する
            min_max_detector();
            maxAverage = maxAverage + max;
            minAverage = minAverage + min;
            infraRedImageNumber++;
        }   
    
        maxAverage = maxAverage/count;
        minAverage = minAverage/count;
     
        
        MaxValue = maxAverage;
        MinValue = minAverage;
        valueOfSlider1 = (int)( (maxAverage-minAverage)/3 + minAverage );   // 赤色の下限値
        //System.out.println("valueOfSlider1 = "+valueOfSlider1);
        valueOfSlider2 = (int)( 2*(maxAverage-minAverage)/3 + minAverage );   // 白色の下限値
        //System.out.println("valueOfSlider2 = "+valueOfSlider2);

        //thermalCsvName = "./lib/thermal_info"+(currentImageCounter+1)+".csv";
        //thermalCsvReader1(thermalCsvName);
        //thermalCsvReader2(thermalCsvName);   // thermalCsvDataの0行0列から511行639列に値を格納する
        thermalCsvReaderMethod(); // 上記の機能をまとめたメソッド

        blackImage = blackImageGenerator(readImageMethod(infraRedImageName[currentImageCounter]));   // 黒い画像を生成

        west_Image_Setter(orgImageName[currentImageCounter]);  // WESTの初期状態は何も処理されていない元画像
        center_Image_Setter( readImageMethod(infraRedImageName[currentImageCounter]) );  // CENTERの初期状態は何も処理されていない元画像        
        //south_Image_Setter( grayScaleConverter(readImageMethod(infraRedImageName[currentImageCounter])) );  // SOUTHの初期状態は3値化された画像 
        south_Image_Setter( blackImage );

        imageNameSetterOnNorth(orgImageName[currentImageCounter]);   // NORTHの初期状態は一番目の画像名
        
        // ウィンドウ生成
        frame = new JFrame();                                  // メイン・ウィンドウ作成                                          
        //frame.setSize(1340, 1020);                           // メイン・ウィンドウのサイズを設定
        frame.setSize(1900, 1050);// メイン・ウィンドウのサイズを設定
        frame.setTitle("Thermal Vision");          // ウィンドウのタイトル設定
        frame.setLocationRelativeTo(null);                     // 最初に画面中央に表示されるように設定
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ウィンドウを閉じる動作の登録

        // GUIを構成するコンポーネントを配置させるメソッドの呼び出し(GUIコンポーネントの配置)        
        buildUI();

        // ウィンドウ全体を可視化
        frame.setVisible(true);             
    }


    // GUIコンポーネントを集約・構成し、GUI全体を生成するメソッド
    public void buildUI()
    {                
        // 未定のUI 北
        panelNORTH = new JPanel();
        BevelBorder borderNORTH = new BevelBorder(BevelBorder.RAISED);  
        panelNORTH.setBorder(borderNORTH);
        panelNORTH.add(labelNORTH);

        // 拡大した元画像を表示するパネルに関するUI 西
        panelWEST = new JPanel(); 
        BevelBorder borderWEST = new BevelBorder(BevelBorder.RAISED);  
        panelWEST.setBorder(borderWEST);     
        panelWEST.setLayout(new CardLayout());        
        panelWEST.add(labelWEST);
        panelWEST.setPreferredSize(new Dimension(disp_width * 2/5, disp_height * 2/5));
        
        // 拡大した赤外線画像を表示するパネルに関するUI 中央
        panelCENTER = new JPanel();        
        addMouseMotionListener(this); // CENTERのJLabel(this)にペイントイベントを登録
        addMouseListener(this);       // CENTERのJLabel(this)にペイントイベントを登録
        rects = Collections.synchronizedList(new ArrayList<>());
        draggingRect = new Rectangle(0, 0);
        dragging = false;
        BevelBorder borderCENTER = new BevelBorder(BevelBorder.RAISED);
        panelCENTER.setBorder(borderCENTER);     
        panelCENTER.setLayout(new CardLayout());       
        panelCENTER.add(this);        
        
        // 「確定」、「NEXT」、「BACK」ボタンを表示するUI 東
        panelEAST = new JPanel();       
        BevelBorder borderEAST = new BevelBorder(BevelBorder.RAISED);
        panelEAST.setBorder(borderEAST);
        panelEAST.setLayout(new GridLayout(8, 1));  
        okButton = new JButton("確定(保存)");     
        nextButton = new JButton("NEXT"); 
        backButton = new JButton("BACK"); 
        regionSelectButton = new JButton("プロット形式の設定");
        regionReleaseButton = new JButton("疑似カラー画像表示");
        loupeButton = new JButton("ルーペ");
        realTimePlotButton = new JButton("温度差分のマップ出力");
        reportGeneratorButton = new JButton("レポート生成");

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
        
        // 拡大した3値化画像を表示するパネルに関するUI 南
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
        

        BigDecimal bd_maxAve = new BigDecimal(maxAverage);  //元データをBigDecimal型にする
        BigDecimal bd3_maxAve = bd_maxAve.setScale(2, BigDecimal.ROUND_HALF_UP);  //小数第3位で四捨五入
        BigDecimal bd_minAve = new BigDecimal(minAverage);  //元データをBigDecimal型にする
        BigDecimal bd3_minAve = bd_minAve.setScale(2, BigDecimal.ROUND_HALF_UP);  //小数第3位で四捨五入
        button_merge = new JButton("温度の最大値の相加平均: "+bd3_maxAve.doubleValue()+"℃"+"  |  "+"温度の最小値の相加平均: "+bd3_minAve.doubleValue()+"℃");
        button_merge.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
        button_merge.setForeground(Color.WHITE);
        button_merge.setBackground(new Color(45, 45, 45));
        button_merge.addMouseListener(this);
        RangeSliderValue1 = new JLabel("     赤色の下限値: " + String.valueOf(valueOfSlider1) +"℃"+"\t" + "           白色の下限値: " + String.valueOf(valueOfSlider2) + "℃" );        
        RangeSliderValue1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
        RangeSlider1.setValue(valueOfSlider1);  // 下側の値
        RangeSlider1.setUpperValue(valueOfSlider2); //　上側の値
        RangeSliderValue1.setForeground(Color.WHITE);
        
        
        RangeSlider1.addChangeListener(new ChangeListener()     // スライダーのイベント処理
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                RangeSlider slider = (RangeSlider) e.getSource();
               
                RangeSliderValue1.setText("     赤色の下限値: " + String.valueOf(valueOfSlider1) +"℃"+"\t" + "           白色の下限値: " + String.valueOf(valueOfSlider2)+ "℃" );
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
        //paintButton = new JButton("画像処理アルゴリズム");
        paintButton = new JButton("温度の閾値を変更する");
        button1 = new JButton("未定1");
        button2 = new JButton("未定2");
        button3 = new JButton("未定3");
        button4 = new JButton("未定4");
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

        menubar = new JMenuBar();           // メニューバー
        menu1 = new JMenu("ファイル");      // メニュー１
        menubar.add(menu1);
        menuitem1 = new JMenuItem("未定1");
        menuitem2 = new JMenuItem("未定2");
        menuitem3 = new JMenuItem("未定3");
        menuitem4 = new JMenuItem("未定4");
        menuitem5 = new JMenuItem("アプリケーションを終了する");
        menu1.add(menuitem1);
        menu1.add(menuitem2);
        menu1.add(menuitem3);
        menu1.add(menuitem4);
        menu1.add(menuitem5);

        //イベントリスナの登録
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
              

        frame.setLayout(new BorderLayout());                             // 画面全体のレイアウトをBorderLayoutに設定
        frame.setJMenuBar(menubar);
        frame.add(panelNORTH, BorderLayout.PAGE_START);                  // btnPanelを画面上に登録(配置) 
        frame.add(panelCENTER, BorderLayout.CENTER);                     // panelCENTERを画面中央に登録(配置)  
        //frame.add(this, BorderLayout.CENTER);      
        frame.add(panelWEST, BorderLayout.LINE_START);                   // panelWESTを画面左に登録(配置)
        frame.add(panelEAST, BorderLayout.LINE_END);                     // panelEASTを画面右に登録(配置)
        frame.add(panelSOUTH, BorderLayout.PAGE_END);                    // panelSOUTHを画面下に登録(配置)     
    }

    public static void createCSV(String imageName) {
        //String imageName = "test.JPG";

        try{
            //ランタイム取得
            Runtime runtime = Runtime.getRuntime();

            if (start.exists() == true) {
                start.delete();
            }
      
            if (start.exists() == false) { 
                //exeを実行する
                //runtime.exec(".\\lib\\Save2CSV.exe .\\test_image\\" + imageName );
                runtime.exec(".\\lib\\Exe\\Save2CSV.exe "+imageName );
                start.delete();
            } else {
                JOptionPane.showMessageDialog(null, "Exifを取得するプログラムの実行に失敗しました。");
                JOptionPane.showMessageDialog(null, "アプリケーションを終了し、再度実行してください。");
                start.delete();
            }
                       
        } catch(Exception ex){
            //指定したファイルが無い場合の処理
            JOptionPane.showMessageDialog(null, "./lib/Exe/Save2CSV.exeを実行できませんでした。");
            JOptionPane.showMessageDialog(null, "アプリケーションを終了し、再度実行してください。");
            start.delete();
        }

        while (true) {
            // ファイルの存在を確認する
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

    // csvファイル内のデータが存在する範囲を走査して、データが存在する範囲の行数と列数を取得するメソッド
    public static void csvReader1() {                                 
        String sLine = "";                                     // 一時格納用変数(ダミーとして使う変数)
        ArrayList<String> arrText = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader( new FileReader(colorCsvPath) );
            
            // ファイルから1行ずつ読み込む
            while (sLine != null) {                
                // ファイルから1行ずつ読み込むReadToEndでもいい
                sLine = br.readLine();
                if (sLine != null)
                    arrText.add(sLine); // addメソッドで追記
            }
                       
            br.close();
        } catch( IOException e )  {
            JOptionPane.showMessageDialog(null, "CSVファイル「"+colorCsvPath+"」の読み込みに失敗しました。");
        } catch( NumberFormatException e )  {
            JOptionPane.showMessageDialog(null, "CSVファイル「"+colorCsvPath+"」に不正なフォーマットが存在します。");
        }
        line_count = arrText.size();

        // 列数を数えるために適当な配列に格納して数えてみる
        String temp = (String)arrText.get(0);   // string型にキャスト
        // splitメソッドで文字列アレイにして数える
        String[] temp2 = temp.split(",");
        // 列数を数える
        col_count = temp2.length;
    }

    // csvファイル内のデータが存在する範囲を走査して、データが存在する範囲の行数と列数を取得するメソッド
    public static void thermalCsvReader1(String csvName) {                                 
        String sLine = "";                                     // 一時格納用変数(ダミーとして使う変数)
        ArrayList<String> arrText = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader( new FileReader(csvName) );
            
            // ファイルから1行ずつ読み込む
            while (sLine != null) {                
                // ファイルから1行ずつ読み込むReadToEndでもいい
                sLine = br.readLine();
                if (sLine != null)
                    arrText.add(sLine); // addメソッドで追記
            }
                       
            br.close();
        } catch( IOException e )  {
            JOptionPane.showMessageDialog(null, "温度データが格納されたCSVファイル「"+csvName+"」の読み込みに失敗しました。");
        } catch( NumberFormatException e )  {
            JOptionPane.showMessageDialog(null, "温度データが格納されたCSVファイル「"+csvName+"」に不正なフォーマットが存在します。");
        }
        thermal_line_count = arrText.size();

        // 列数を数えるために適当な配列に格納して数えてみる
        String temp = (String)arrText.get(0);   // string型にキャスト
        // splitメソッドで文字列アレイにして数える
        String[] temp2 = temp.split(",");
        // 列数を数える
        thermal_col_count = temp2.length;
    }


    // csvファイルに記されている各列要素をそれぞれに対応する文字列型配列に格納するメソッド
    public static void csvReader2() {
        String sLine = ""; // 一時格納用
        
        ArrayList<String> arrText = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader( new FileReader(colorCsvPath) );
            
            // ファイルから1行ずつ読み込む
            while (sLine != null)
            {
                
                // ファイルから1行ずつ読み込むReadToEndでもいい
                sLine = br.readLine();
                if (sLine != null)
                    arrText.add(sLine); // addメソッドで追記
            }
                       
            br.close();
        } catch( IOException e )  {
            JOptionPane.showMessageDialog(null, "CSVファイル「"+colorCsvPath+"」の読み込みに失敗しました。");
        } catch( NumberFormatException e )  {
            JOptionPane.showMessageDialog(null, "CSVファイル「"+colorCsvPath+"」に不正なフォーマットが存在します。");
        }

        // ArrayListから2次元配列に格納する．
        // 2次元配列の定義
        csvData = new String[line_count][col_count];
        int a = 0;
        int b = 0;

        for (String sOutput : arrText) {
            // 一行ずつ読み込んで，各行をsplitメソッドで分ける
            String[] temp_line = sOutput.split(",");
            for (String value : temp_line)
            {
                csvData[a][b] = value;
                b++;
            }
            b = 0;
            a++;
        }

        
        // imageNames[0]からimageNames[line_count-2]にデータが格納される        
        for (int i = 0; i < line_count; i++) {
            R[i] = csvData[i][0];
        }

        // x[0]からx[line_count-2]にデータが格納される        
        for (int i = 0; i < line_count; i++) {
            G[i] = csvData[i][1];
        }

        // y[0]からy[line_count-2]にデータが格納される        
        for (int i = 0; i < line_count; i++) {
            B[i] = csvData[i][2];
        }                    
    }

    // csvファイルに記されている各列要素をそれぞれに対応する文字列型配列に格納するメソッド
    public static void thermalCsvReader2(String csvName) {
        String sLine = ""; // 一時格納用
        
        ArrayList<String> arrText = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader( new FileReader(csvName) );
            
            // ファイルから1行ずつ読み込む
            while (sLine != null)
            {
                
                // ファイルから1行ずつ読み込むReadToEndでもいい
                sLine = br.readLine();
                if (sLine != null)
                    arrText.add(sLine); // addメソッドで追記
            }
                       
            br.close();
        } catch( IOException e )  {
            JOptionPane.showMessageDialog(null, "温度データが格納されたCSVファイル「"+csvName+"」の読み込みに失敗しました。");
        } catch( NumberFormatException e )  {
            JOptionPane.showMessageDialog(null, "温度データが格納されたCSVファイル「"+csvName+"」に不正なフォーマットが存在します。");
        }

        // ArrayListから2次元配列に格納する．
        // 2次元配列の定義
        thermalCsvData = new String[thermal_line_count][thermal_col_count];
        int a = 0;
        int b = 0;

        for (String sOutput : arrText) {
            // 一行ずつ読み込んで，各行をsplitメソッドで分ける
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
            JOptionPane.showMessageDialog(null, "温度データが格納されているCSVファイルを読み込んだ際にフォーマットに関するエラーが発生しました。");
        }
    }

    public static void thermalCsvReaderMethod() {
        thermalCsvName = "./lib/Thermal_CSV/"+infraRedImageName[currentImageCounter].replace("JPG", "csv");
        thermalCsvReader1(thermalCsvName);
        thermalCsvReader2(thermalCsvName);   // thermalCsvDataの0行0列から511行639列に値を格納する
    }

    public static BufferedImage blackImageGenerator(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        blackImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        return blackImage;
    }

    // InfraRedImagesフォルダから画像をBufferedImage型で読み込み、BufferedImage型で返すメソッド
    public static BufferedImage readImageMethod(String imageName)
    {
        try
        {
            readImage = ImageIO.read(new File(infraRedImagePath+"/"+imageName));
            img_gray = ImageIO.read(new File(infraRedImagePath+"/"+imageName)); // double型の2次元配列に変える
            img_gray_tmp = ImageIO.read(new File(infraRedImagePath+"/"+imageName));// 
            
            img_width = readImage.getWidth();
            img_height = readImage.getHeight();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "バックグラウンドでの画像読み込みに失敗しました。");
        }
        return readImage;
    } 
    
    // 現在の画像名をlabelNORTHに設定するメソッド
    public void imageNameSetterOnNorth(String fileName)
    {
        labelNORTH = new JLabel(fileName, JLabel.CENTER);
        labelNORTH.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 25));
        //labelNORTH.setBackground(new Color(45, 45, 45));
        labelNORTH.setForeground(Color.WHITE);
    }
   
    // 画像をImageIcon型で読み込み、画像を縮小してlabelWESTにアイコンを貼り付けるメソッド
    public void west_Image_Setter(String fileName)
    {               
        ImageIcon icon = new ImageIcon(orgImagePath+"/"+fileName);
        //MediaTracker tracker = new MediaTracker(this);  // 処理の終了を待つMediaTrackerを定義
        //Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * RATIO_WEST), -1, Image.SCALE_SMOOTH); // getScaledInstanceで大きさを変更する
        double ratio1 = (double) (disp_height * 2/5) / (double) icon.getIconHeight(); //ほげほげ
        double ratio2 = (double) (disp_width * 2/5) / (double) icon.getIconWidth();
        double ratio_ = ratio1 >= ratio2 ? ratio2 : ratio1;
        //System.out.println("(double) (disp_width/3):" + (double) (disp_width/3));
        Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * ratio_), -1, Image.SCALE_SMOOTH);
        
        //tracker.addImage(bigImg, 1);  // MediaTrackerで処理の終了を待つ
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

    // 画像をImageIcon型で読み込み、画像を縮小してlabelCENTERにアイコンを貼り付けるメソッド
    public void center_Image_Setter(BufferedImage img)
    {
        ImageIcon icon = new ImageIcon(img);
        //MediaTracker tracker = new MediaTracker(this);  // 処理の終了を待つMediaTrackerを定義
        //Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * RATIO), -1, Image.SCALE_SMOOTH); // getScaledInstanceで大きさを変更する
        double ratio1 = (double) (disp_height * 2/5) / (double) img.getHeight(); //ほげほげ
        double ratio2 = (double) (disp_width * 2/5) / (double) img.getWidth();
        ratio = ratio1 >= ratio2 ? ratio2 : ratio1; 
        Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * ratio), -1, Image.SCALE_SMOOTH); // getScaledInstanceで大きさを変更する
        //tracker.addImage(bigImg, 1);  // MediaTrackerで処理の終了を待つ
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

    // 画像をImageIcon型で読み込み、画像を縮小してlabelSOUTHにアイコンを貼り付けるメソッド
    public static void south_Image_Setter(BufferedImage img)
    {       
        ImageIcon icon = new ImageIcon(img);
        //MediaTracker tracker = new MediaTracker(this);  // 処理の終了を待つMediaTrackerを定義
        //Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * RATIO), -1, Image.SCALE_SMOOTH); // getScaledInstanceで大きさを変更する
        double ratio1 = (double) (disp_height * 2/5) / (double) img.getHeight(); //ほげほげ
        double ratio2 = (double) (disp_width * 2/5) / (double) img.getWidth();
        ratio = ratio1 >= ratio2 ? ratio2 : ratio1; 
        Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * ratio), -1, Image.SCALE_SMOOTH);
        //tracker.addImage(bigImg, 1);  // MediaTrackerで処理の終了を待つ
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
    
    
    public static BufferedImage grayScaleConverter(BufferedImage img) {   // グレイスケール画像を解析し、SOUTHに反映させるメソッド
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
    
    
    public static BufferedImage narrowRangeGrayScaleConverter(BufferedImage img)   // 囲んだ範囲から最大値と最小値を得るだけの機能 /////////////////////////////////////////////////
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
                        thermal = (thermal - MinValue) * 255. / (MaxValue - MinValue);  /// 温度データの最大値と最小値の差
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
    public static BufferedImage rainbowConverter(BufferedImage img) // 紫の画像から疑似カラー画像を生成するメソッド    
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

                    // r,g,bにカラーパレットの値を代入
                    r = Integer.parseInt(R[p]);
                    g = Integer.parseInt(G[p]);
                    b = Integer.parseInt(B[p]);
                
                    // r,g,bの色を合成
                    newcolor = ( r << 16 ) + ( g << 8 ) + b;
                
                    // 合成した色を(x,y)に設定
                    img.setRGB( x, y, newcolor );
                } catch(NullPointerException e) {

                }
            }
    	}                          
        return img;      
    }

    // イベントリスナ(MouseListenerインターフェイス)が持つ抽象メソッドを実装    
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
                    JOptionPane.showMessageDialog(null, "アノテーション画像 : "+imageName+"を"+"「"+annotationImagesPath+"」  直下に保存しました。");

                    ImageIO.write(tmpImage, "png", new File(thermalImagesPath+"/"+imageName));
                    JOptionPane.showMessageDialog(null, "サーマル画像 : "+imageName+"を"+"「"+thermalImagesPath+"」  直下に保存しました。"); 
                }      
                    
                if( (colorFlag==true) && (selectCount==0) ) {                    
                    ImageIO.write(grayScaleConverter(rainbowConverter(readImageMethod(infraRedImageName[currentImageCounter]))), "png", new File(annotationImagesPath+"/"+imageName));
                    JOptionPane.showMessageDialog(null, "アノテーション画像 : "+imageName+"を"+"「"+annotationImagesPath+"」  直下に保存しました。");
                  
                    ImageIO.write(readImage, "png", new File(thermalImagesPath+"/"+imageName));  
                    JOptionPane.showMessageDialog(null, "サーマル画像 : "+imageName+"を"+"「"+thermalImagesPath+"」  直下に保存しました。");
                }

                if( (colorFlag==false) && (selectRangeFlag==false) ) {                      
                    ImageIO.write(readImage, "png", new File(thermalImagesPath+"/"+imageName));  
                    JOptionPane.showMessageDialog(null, "サーマル画像 : "+imageName+"を"+"「"+thermalImagesPath+"」  直下に保存しました。");
                }
                
                if( (colorFlag==false) && (selectRangeFlag==true) ) {                    
                    ImageIO.write(readImage, "png", new File(thermalImagesPath+"/"+imageName));
                    JOptionPane.showMessageDialog(null, "サーマル画像 : "+imageName+"を"+"「"+thermalImagesPath+"」  直下に保存しました。");
                }
            }
            catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(null, "アノテーション画像の保存に失敗しました。");
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
            if(x2==0&&y2==0) {  // センターの画像を局所的に押下したとき
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
            new RadioSample("プロットの大きさ設定");
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
                regionReleaseButton.setText("元画像表示");                
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
                regionReleaseButton.setText("疑似カラー画像表示");                
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
                JOptionPane.showMessageDialog(null, "外部プログラムの実行に失敗しました。");
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
                JOptionPane.showMessageDialog(null, "外部プログラムの実行に失敗しました。");
            }
                        
        }

        if(e.getSource().equals(reportGeneratorButton))
        {
            //System.out.println("ラベルの幅:"+this.getWidth()+"\n"+"ラベルの高さ:"+this.getHeight());            
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
        {   // 矩形をクリア(「Ctrl」+「マウスクリック」)
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
        // 空実装
    }
    
    public void mouseExited(MouseEvent e)
    {
        // 空実装
    }

    public void mouseMoved(MouseEvent e)
    {
        // 空実装
    }
   
    public void mousePressed(MouseEvent e)
    {       // マウスを押下したときのイベント         
        if (!(e.getSource().equals(this))) {  //センターの画像以外の領域をクリックしたときは反応しないようにする。
            return;
        }

        x1 = e.getX() - panelCENTER.getWidth() / 2;//e.getX();
        y1 = e.getY() - panelCENTER.getHeight() / 2;//e.getY();
        
        x1 = x1 >= (double) img_width / 2.0 * ratio ? (int)(img_width / 2 * ratio ): x1;
        y1 = y1 >= (double) img_height / 2.0 * ratio ? (int)(img_height / 2 * ratio ): y1;
        x1 = x1 <= (double) -img_width / 2.0 * ratio ? (int)(-img_width / 2 * ratio ): x1;
        y1 = y1 <= (double) -img_height / 2.0 * ratio ? (int)(-img_height / 2 * ratio ): y1;
        
        draggingRect.setBounds(x1 + panelCENTER.getWidth()/2, y1 + panelCENTER.getHeight()/2, 0, 0);      // マウスドラッグの開始点        
        dragging = true;
        rects.clear();
        x2 = 0;
        y2 = 0;
        repaint();  
    }
    

    public void mouseReleased(MouseEvent e)
    {      // マウスを離したときのイベント
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


   
    public void mouseDragged(MouseEvent e)  // マウスをドラッグしたときのイベント//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    
    
    public void stateChanged_modoki() // イベント時に値を格納
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

    //メニューのイベント処理
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand() == "未定1")
        {

        }

        if (e.getActionCommand() == "未定2")
        {

        }

        if (e.getActionCommand() == "未定3")
        {

        }

        if (e.getActionCommand() == "未定4")
        {

        }

        if (e.getActionCommand() == "アプリケーションを終了する")
        {
            System.exit(0);  // 終了
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

    // 問題箇所を検出するメソッド
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

        for (int y = (int)(y1/ratio+(h/2)); y < (int)(y2/ratio+(h/2)); y=y+1) {   // 選択範囲内を走査
            for (int x = (int)(x1/ratio+(w/2)); x < (int)(x2/ratio+(w/2)); x=x+1) {
        
                narrowThermalCount = narrowThermalCount + 1;
                narrowThermalAve = narrowThermalAve + Double.parseDouble(thermalCsvData[y][x]);   // この段階ではnarrowThermalAveには温度の総和が代入される(後でnarrowThermalCountで割る)

                if(Double.parseDouble(thermalCsvData[y][x]) > narrowThermalMax) {
                    narrowThermalMax = Double.parseDouble(thermalCsvData[y][x]);
                }

                if(Double.parseDouble(thermalCsvData[y][x]) < narrowThermalMin) {
                    narrowThermalMin = Double.parseDouble(thermalCsvData[y][x]);
                }
                
            }
   	}    

        boolean flag = false;
        for (int y = (int)(y1/ratio+(h/2)); y < (int)(y2/ratio+(h/2)); y=y+1) {   // 選択範囲内を走査
            for (int x = (int)(x1/ratio+(w/2)); x < (int)(x2/ratio+(w/2)); x=x+1) {         
                //------温度で検出するバージョン-----------------------------------------------------------------
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
        BigDecimal bd = new BigDecimal(narrowThermalAve);  //元データをBigDecimal型にする
        BigDecimal bd3 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);  //小数第3位で四捨五入
        
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
        RangeSliderValue1.setText("     赤色の下限値: " + String.valueOf((int)(bd3.doubleValue())) +"℃"+"\t" + "           白色の下限値: " + String.valueOf((int)(narrowThermalMax-1))+ "℃" );
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
   
    // メインメソッド (コンストラクタを起動するためだけに利用される)
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

        button1 = new JButton("確定");
        button2 = new JButton("閉じる");

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
        // 空実装
    }

    public void mouseExited(MouseEvent e)
    {
        // 空実装
    }

    public void mousePressed(MouseEvent e)
    {
        // 空実装
    }

    public void mouseReleased(MouseEvent e)
    {
        // 空実装
    }    
}