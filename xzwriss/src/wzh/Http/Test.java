package wzh.Http;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		  String s=HttpRequest.sendGet("http://hq.sinajs.cn/list=sh600874,sh601288,sh600028",null);
//		  String [] arr=s.split(",");
//		  for (int i = 0; i < arr.length; i++) {
//			System.out.println(i+":"+arr[i]);
//		}
//		  System.out.println(s);
//	      System.out.println(arr[3]);
//	      System.out.println(arr[35]);
//	      System.out.println(arr[67]);
		String s=HttpRequest.sendGet("http://hq.sinajs.cn/list=sh600874,sh601288,sh600028,s_sh000001",null);
		String [] arr=s.split(",");
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(i+":"+arr[i]);
		}
	//	System.out.println(s);
		//new ReThread().start();

	}

}
