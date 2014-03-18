
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class StockSpan {

	public static void main (String [] args ) {


		//create lists


		List<Float> prices = new ArrayList<Float>();
		List<String> dates= new ArrayList<String>();


String s;

			try{

				BufferedReader br= new BufferedReader(new FileReader (args[1]));


			while (br.readLine()!=null){
				s=br.readLine();
				String [] fields =s.split(",");

				     	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				     	         Float p=Float.parseFloat(fields[1]);
				     	         Date d=sdf.parse(fields[0]);
				     	         String date=sdf.format(d);
				     	         prices.add(p);
     	        				 dates.add(date);

			}
		}catch(Exception e)
		   	    	{System.out.println(e);
   		}

			Float[] pArray=new Float[prices.size()];
			String[] dArray=new String[dates.size()];
			for (int i=0;i<prices.size();i++){
				pArray[i]=prices.get(i);
				dArray[i]=dates.get(i); }



			if ( args[0].equals("-n")){
				naive(pArray, dArray, 0);

			}else if (args[0].equals("-s")){
				stack(pArray, dArray, 0);

			}else if (args[0].equals("-b")){
				timer(pArray, dArray, 1);

			}

	}


				public static void naive(Float [] pArray, String [] dArray, int aa ){



					for(int i=0;i<=pArray.length-1;i++){
									int k=1;

									boolean span_end=false;
									while(i-k>=0 && !span_end){
										if(pArray[i-k]<=pArray[i])
										k=k+1;
										else
										span_end=true;
									}
									if (aa==0)
									System.out.println(dArray[i]+":"+k);
			}


				}


				public static void stack (Float [] pArray, String [] dArray, int aa ){

			Integer[] span=new Integer[pArray.length];

			Stack<Integer> spans=new Stack<Integer>();
			spans.push(0);
			span[0]=1;

			for(int i=1;i<=pArray.length-1;i++){

				while((!spans.empty()) && (pArray[spans.peek()]<=pArray[i]))
					spans.pop();

					if(spans.empty())
						span[i]=i+1;
					else
						span[i]=i-spans.peek();
				spans.push(i);
			}
			for(int i=0;i<=pArray.length-1;i++)
			if (aa==0)
				System.out.println(dArray[i]+":"+span[i]);
		}

				public static void timer(Float [] pArray, String [] dArray, int aa ) {

					int j;
					long startTime = System.nanoTime();

			for(j=1;j<100;j++) {
				naive(pArray, dArray, 1);

			}long endTime = System.nanoTime();

			System.out.println("Naive implementation took:"+(endTime-startTime)/(10^6*100));

				int z;
				long startTime2 = System.nanoTime();


			for(z=0;z<100;z++) {
				stack(pArray, dArray, 1);


	}long endTime2 = System.nanoTime();

	System.out.println("Stack implementation took:"+(endTime2-startTime2)/(10^6*100));


		}



}




