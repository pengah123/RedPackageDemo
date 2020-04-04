package FrontEnd;

import BackEnd.OpenMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/*红包页面框架*/
public class MyRedFrame extends JFrame {
    //    界面图片路径
    private static final String DIR = "src/pic";
    //    存放红包金额
    private ArrayList<Integer> moneyList = null;

    private static int initMoney = 0;
    private static int totalMoney = 0;
    private static int count = 0;

    private static HashMap<JPanel, JLabel> panellable = new HashMap<JPanel, JLabel>();

    //    设置字体
    private static Font fontYaHei20 = new Font("微软雅黑", Font.BOLD, 20);
    private static Font fontYaHei40 = new Font("微软雅黑", Font.BOLD, 40);
    private static Font fontYaHei50 = new Font("微软雅黑", Font.BOLD, 50);
    private static Font fontYaHei15 = new Font("微软雅黑", Font.BOLD, 15);

    /*设置窗体大小*/
    private static final int FRAME_WIDTH = 416;
    private static final int FRAME_HEIGHT = 650;

    private static JLayeredPane layeredPane = null;


    /*第一个页面*/
    private static JPanel inputPanel = new JPanel();

    //    输入红包金额和个数
    private static JTextField input_total = new JTextField();
    private static JTextField input_count = new JTextField();
    //    新建一个30列的可输入文本框，显示群成员
    private static JTextField input_people = new JTextField("30");
    //    输入红包吉祥语
    private static JTextField input_msg = new JTextField("恭喜发财 ，大吉大利");
    //    显示输入的红包金额，发了多少钱
    private static JTextField input_total_show = new JTextField("￥" + input_total.getText());

    //    发红包按钮
    private static JLabel input_Money = new JLabel();
    //    背景图片
    private static JLabel input_bg_label = new JLabel(new ImageIcon(DIR + "/01_input.jpg"));


    //添加组件并设置格式
    static {
        //设置组件位置
        input_total.setBounds(200, 90, 150, 50);
        input_count.setBounds(200, 215, 150, 50);

        input_people.setBounds(90, 275, 25, 30);
        input_msg.setBounds(180, 340, 200, 50);
        input_total_show.setBounds(130, 430, 200, 80);
        input_Money.setBounds(10, 535, 380, 65);
        input_bg_label.setBounds(0, 0, 400, 600);

//        设置字体
        input_total.setFont(fontYaHei20);
        input_count.setFont(fontYaHei20);
        input_people.setFont(fontYaHei20);
        input_msg.setFont(fontYaHei20);
        input_msg.setForeground(new Color(255, 233, 38));//设置红包标题字体颜色为金色
        input_total_show.setFont(fontYaHei40);
        input_Money.setFont(fontYaHei20);

//设置透明度
        input_people.setOpaque(false);
        input_total_show.setOpaque(false);
//        设置不可编辑区域
        input_people.setEditable(false);
        input_total_show.setEditable(false);

//        设置边框格式
        input_total.setBorder(null);
        input_count.setBorder(null);
        input_people.setBorder(null);
        input_msg.setBorder(null);
        input_total_show.setBorder(null);

    }

    /*第二个页面，打开页面，组件初始化*/
    private static JPanel openPanel = new JPanel();

    private static JTextField open_ownerName = new JTextField("群主名称");
    private static JLabel open_label = new JLabel(new ImageIcon(DIR + "/02_open_2.gif"));
    private static JLabel open_bg_label = new JLabel(new ImageIcon(DIR + "/02_open_1.jpg"));

    static {
//        设置组件位置
        open_ownerName.setBounds(0, 110, 400, 50);
        open_bg_label.setBounds(0, 0, 400, 620);
        open_label.setBounds(102, 280, 200, 200);
        open_ownerName.setHorizontalAlignment(JTextField.CENTER);

//        设置组件字体
        open_ownerName.setFont(fontYaHei40);
        open_ownerName.setForeground(new Color(255, 200, 163));//设置字体颜色为金色

//        设置背景色
        open_ownerName.setBackground(new Color(219, 90, 68));

//        不可编辑
        open_ownerName.setEditable(false);
//        边框
        open_ownerName.setBorder(null);
    }

    /*第三个页面，抢红包之后的显示页面*/
    private static JPanel showPanel = new JPanel();
    private static JPanel showPanel2 = new JPanel();
    private static JScrollPane show_jsp = new JScrollPane(showPanel2);

    private static JLabel show_bg_label = new JLabel(new ImageIcon(DIR + "/03_money_1.jpg"));
    //发红包的人是：
    private static JTextField show_name = new JTextField("用户名称");
    //    发红包的时候输入的祝福信息
    private static JTextField show_msg = new JTextField("祝福信息");
    //    你领取的红包的钱数
    private static JTextField show_money = new JTextField("99.99");
    //    红包被领取的信息
    private static JTextField show_result = new JTextField(count + "个红包共" + (totalMoney / 100.0) + "元,被抢光了");

    static {
        /*
         * 两部分 页面 . 1.本人获得的红包-- showPanel 2.别人获得的红包-- show_jsp
         */
        show_name.setBounds(125, 150, 100, 90);
        show_name.setOpaque(false);
        show_name.setBorder(null);
        show_name.setFont(fontYaHei20);

        show_msg.setBounds(0, 220, 400, 30);
        show_msg.setOpaque(false);
        show_msg.setBorder(null);
        show_msg.setFont(fontYaHei20);
        show_msg.setHorizontalAlignment(JTextField.CENTER);

        show_money.setBounds(0, 270, 250, 40);
        show_money.setOpaque(false);
        show_money.setBorder(null);
        show_money.setFont(fontYaHei50);
        show_money.setForeground(new Color(255, 233, 38)); // 字体颜色 为金色
        show_money.setHorizontalAlignment(SwingConstants.RIGHT);

        show_result.setBounds(10, 460, 400, 20);
        show_result.setOpaque(false);
        show_result.setBorder(null);
        show_result.setFont(fontYaHei15);
        show_result.setForeground(new Color(170, 170, 170));

        show_bg_label.setBounds(0, 0, 400, 500);
    }

    static {
        /*添加页面和背景的对应关系*/
        panellable.put(inputPanel, input_bg_label);
        panellable.put(openPanel, open_bg_label);
        panellable.put(showPanel, show_bg_label);
    }

    public void init() {

        layeredPane = this.getLayeredPane();
        /*初始化框架方法*/
        initFrame();
        /*初始化页面*/
        initPanel();
        /*添加页面方法*/
        setPanel(inputPanel);
        /*添加监听*/
        addListener();
    }

    public void initFrame() {
        /*加载logo*/
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(DIR + "/logo.gif"));
        /*加载窗口*/
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocation(280, 30);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        /**/
        this.setVisible(true);

    }

    public void initPanel() {
        initInputPanel();
        initOpenPanel();
        initShowPanel();
    }

    public void initInputPanel() {
        inputPanel.setLayout(null);
        inputPanel.setBounds(0, -5, 400, 600);

        inputPanel.add(input_total);
        inputPanel.add(input_count);
        inputPanel.add(input_people);
        inputPanel.add(input_msg);
        inputPanel.add(input_total_show);
        inputPanel.add(input_Money);
    }

    public void initOpenPanel() {
        openPanel.setLayout(null);
        openPanel.setBounds(0, 0, 400, 600);
        openPanel.add(open_ownerName);
        openPanel.add(open_label);
    }

    public void initShowPanel() {
        showPanel.setLayout(null);
        showPanel.setBounds(10, 10, 300, 600);

        // ==============
        showPanel.add(show_name);
        showPanel.add(show_msg);
        showPanel.add(show_money);
        showPanel.add(show_result);
//        System.out.println("展示页面||" + showPanel);
        // ====================================
        // showPanel2.setLayout(null);
        // showPanel2.setBounds(0, 500, 401, 300);

        showPanel2.setPreferredSize(new Dimension(300, 1000));
        showPanel2.setBackground(Color.white);

        show_jsp.setBounds(0, 500, 400, 110);
    }

    public void setPanel(JPanel panel) {
        layeredPane.removeAll();
        // 背景lable添加到layeredPane的默认层
        layeredPane.add(panellable.get(panel), JLayeredPane.DEFAULT_LAYER);

        // 面板panel设置为透明
        panel.setOpaque(false);

        // 面板panel 添加到 layeredPane的modal层
        layeredPane.add(panel, JLayeredPane.MODAL_LAYER);
    }


    public void addListener() {

        /*输入总金额，显示红包总金额*/
        input_total.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input_total_money = input_total.getText();
                input_total_show.setText("￥" + input_total_money);
            }
        });

        /*输入红包个数事件，暂未添加*/
        input_count.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

        /*输入红包标题事件，暂未添加*/
        input_msg.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

        input_Money.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    totalMoney = (int) (Double.parseDouble(input_total.getText()) * 100);
                    count = Integer.parseInt(input_count.getText());

                    if (count > 30) {
                        JOptionPane.showMessageDialog(null, "红包个数不得超过群成员数", "红包个数有误", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    initMoney = totalMoney;
                    System.out.println("总金额：[" + totalMoney + "]分");
                    System.out.println("红包个数：[" + count + "]个");

                    input_Money.removeMouseListener(this);

                    /*设置群主名称*/
                    open_ownerName.setText(ownerName);
                    /*设置打开页面*/
                    setPanel(openPanel);
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "请输入正确的【总金额】或【红包个数】", "输入信息有误", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

/*        // open_ownerName ,点击 [名称],触发的方法 , 提示如何设置群主名称.
        open_ownerName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                JOptionPane.showMessageDialog(null, "请通过【setOwnerName】方法设置群主名称", "群主名称未设置",
                        JOptionPane.QUESTION_MESSAGE);
            }
        });*/

       /*点击开红包,触发的方法*/
      open_label.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              if(openWay==null){
                  JOptionPane.showMessageDialog(null,"请输入红包分配方式——随机红包OR平均红包","打开方式未设置",JOptionPane.QUESTION_MESSAGE);
              return;
              }
              moneyList=openWay.divide(totalMoney,count);
              /*显示发红包的人*/
              show_name.setText(ownerName);
              /*显示红包吉祥语*/
              show_msg.setText(input_msg.getText());
              /*显示抢到的红包金额*/
              if(moneyList.size()>0){
                  show_money.setText(moneyList.get(moneyList.size()-1)/100+"");
              }
              show_result.setText(count+"个红包共"+(initMoney/100.0)+"元，被抢光了");

              open_label.removeMouseListener(this);
              /*跳转到下一页面*/
              setPanel(showPanel);
              for (int i = 0; i < moneyList.size(); i++) {
                  JTextField tf=new JTextField();
                  tf.setBorder(null);
                  tf.setFont(fontYaHei20);
                  tf.setHorizontalAlignment(JTextField.LEFT);
                  if(i==moneyList.size()-1){
                      tf.setText(ownerName+":\t"+moneyList.get(i)/100.0+"元");
                  }
                  else {
                      tf.setText("群成员-" + i + "：\t" + moneyList.get(i) / 100.0 + "元");
                  }
                  showPanel2.add(tf);
              }

              layeredPane.add(show_jsp,JLayeredPane.MODAL_LAYER);
          }
      });


    }


/*=========================================================================================*/
  /*一些初始化*/

    /**
     * ownerName : 群主名称
     */
    private String ownerName = "谁谁谁"; // 群主名称

    /**
     * openWay : 红包的类型 [普通红包/手气红包]
     */
    private OpenMode openWay = null;

    /*构造函数*/
    public MyRedFrame(String title) {
        super(title);

        // 页面相关的初始化
        init();
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOpenWay(OpenMode openWay) {
        this.openWay = openWay;
    }
}
