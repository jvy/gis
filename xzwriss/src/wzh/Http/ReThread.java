package wzh.Http;

import javax.swing.JOptionPane;

public class ReThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			String s=HttpRequest.sendGet("http://hq.sinajs.cn/list=sh600874,sh601288,sh600028",null);
			  String [] arr=s.split(",");
			  double a=Double.parseDouble(arr[3]);
			  double b=Double.parseDouble(arr[35]);
			  double c=Double.parseDouble(arr[67]);
			  double total=(a-9.464)*500+(b-3.283)*300+(c-5.670)*200;
			  
//		      System.out.println(arr[3]);
//		      System.out.println(arr[35]);
//		      System.out.println(arr[67]);
		      System.out.println("total:"+total);
		      JOptionPane.showMessageDialog(null, "total:"+total); 
		      System.out.println("----------------------------------------------------------------------------");
		      try {
				Thread.sleep(1000*60*10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
