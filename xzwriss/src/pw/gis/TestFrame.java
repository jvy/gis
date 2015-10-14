package pw.gis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import wzh.Http.HttpRequest;

public class TestFrame extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel;
	 private boolean isMoved;  
	    private Point pre_point;  
	    private Point end_point; 
	    DecimalFormat df = new DecimalFormat("#.00");  

	    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
					
					frame.setDragable(frame);
				//	frame.setUndecorated(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	
	private  void setDragable(final JFrame lui) {  
        this.addMouseListener(new java.awt.event.MouseAdapter() {  
            public void mouseReleased(java.awt.event.MouseEvent e) {  
                isMoved = false;// 鼠标释放了以后，是不能再拖拽的了  
                lui.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
            }  
  
            public void mousePressed(java.awt.event.MouseEvent e) {  
                isMoved = true;  
                pre_point = new Point(e.getX(), e.getY());// 得到按下去的位置  
                lui.setCursor(new Cursor(Cursor.MOVE_CURSOR));  
            }  
        });  
        //拖动时当前的坐标减去鼠标按下去时的坐标，就是界面所要移动的向量。  
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {  
            public void mouseDragged(java.awt.event.MouseEvent e) {  
                if (isMoved) {// 判断是否可以拖拽  
                    end_point = new Point(lui.getLocation().x + e.getX() - pre_point.x,  
                            lui.getLocation().y + e.getY() - pre_point.y);  
                    lui.setLocation(end_point);  
                }  
            }  
        });  
    }  
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public TestFrame() {
	//	com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.6f);
		this.setUndecorated(true); // 去掉窗口的装饰
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 30);
		
		setBackground(new Color(58,110,30));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	    lblNewLabel = new JLabel("New label");
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
	    new  SwingWorker<String,String>(){

			@Override
			protected String doInBackground() throws Exception {
				// TODO Auto-generated method stub
				while(true)
				{
					Thread.sleep(5000);
					  String s=HttpRequest.sendGet("http://hq.sinajs.cn/list=sh600874,sh601288,sh600028,s_sh000001",null);
					  String [] arr=s.split(",");
					  double a=Double.parseDouble(arr[3]);
					  double b=Double.parseDouble(arr[35]);
					  double c=Double.parseDouble(arr[67]);
					  double shou1=Double.parseDouble(arr[2]);
					  double shou2=Double.parseDouble(arr[34]);
					  double shou3=Double.parseDouble(arr[66]);
					  double bili1=(a-shou1)/shou1*100;
					  double bili2=(b-shou2)/shou2*100;
					  double bili3=(c-shou3)/shou3*100;
					  double total=(a-9.464)*500+(b-3.283)*300+(c-5.670)*200;
					  System.out.println(s);
					  publish(df.format((a-shou1))+"||"+df.format(bili1)+"            "+df.format((a-shou1))+"||"+df.format(bili2)+"        "+df.format((a-shou1))+"||"+df.format(bili3)+"         "+arr[97]+"||"+arr[99]+"     "+df.format(total)+"");
				}
			
			
			}
			protected void process(List<String> chunks)
			{
				for(String s :chunks)
				{
					//lblNewLabel.setText("<html>"+s+"</html>");
					lblNewLabel.setText(s);
				}
			}
	    	
	    }.execute();
	    this.setAlwaysOnTop(true);
	}

}
