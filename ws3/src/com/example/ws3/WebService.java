package com.example.ws3;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow.LayoutParams;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;

public class WebService extends Activity {
	
	final String NAMESPACE = "urn:sap-com:document:sap:soap:functions:mc-style"; // Name space from WSDL
	final String METHOD_NAME ="ZGssmwfmTestApi3"; //Operation Name form WSDL
	final String SOAP_ACTION = "http://75.99.152.10:8050/sap/bc/srt/wsdl/bndg_E0B1187AA52617F1AE7900188B47B426/wsdl11/allinone/ws_policy/document?sap-client=110";
	final String URL ="http://gsswd.globalsoftsolutions.net:7500/sap/bc/srt/rfc/sap/z_gssmwfm_test_api3/110/z_gssmwfm_test_api3/z_gssmwfm_test_api3"; // address location

	ListView listView1;
    String[] arrExploded,arrExploded1,b;
	String res,result,rephrase,arr,oneThird,twoThird,last;
	TableLayout ll;
	String[] w,w1,w2,strArray,stockArr,h1,h2,h3,h4,h5,h6;
	String[][] array,A,B,C;
	TableRow tr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	ll = (TableLayout) findViewById(R.id.tl);

	//getResponse();
	
	}
	
	public String getResponse(){

		Thread thread = new Thread(new Runnable() {			 
			    @Override
			    public void run() {
			        try{
						SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						request.addProperty("DpixInpt", "G");
						envelope.setOutputSoapObject(request);
						AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
								URL);
						androidHttpTransport.call(SOAP_ACTION, envelope);
						SoapObject response = (SoapObject) envelope.getResponse();
						System.out.println("ws3 Response is: " + response);
	
						result = envelope.getResponse().toString();
			        } catch (Exception e) {
			            System.out.println("Error: " +e.toString());
			        }
			        
			    }
			    
			});
		  
		 	thread.start();
		 	return result;
         
	}

		public String getResponse1(){
		
		Thread thread = new Thread(new Runnable() {

			 
			    @Override
			    public void run() {
			        try{
			         
			        	
			        	 
			        	 SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			   		     SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
			   		            SoapEnvelope.VER11);
			   		    request.addProperty("DpixInpt","G");    
			   		     envelope.setOutputSoapObject(request);
			             AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
			        	 androidHttpTransport.call(SOAP_ACTION, envelope);
			        	 SoapObject response = (SoapObject)envelope.getResponse();
					   //  System.out.println("result is : " +result);
					   //  res=result.substring(8,result.length()-2);
				       //  System.out.println(res);
				     	System.out.println("Response is: " + response);
         				
				         result = envelope.getResponse().toString();
				         //System.out.println(result);
				          
				         res=result.substring(21,result.length()-5);
				         System.out.println(res);
				         
			        
				         
				         strArray = res.split(" ");
			               
			                System.out.println("String converted to String array");
			               
			                //print elements of String array
			                for(int i=0; i < strArray.length; i++){
			                        System.out.print(strArray[i]);
			                }
				         
				      /*   for (int index = res.indexOf("=");
				        	     index >= 0;
				        	     index = res.indexOf("=", index + 1))
				        	{
				        	    System.out.println("Index: " +res.charAt(index));
				        	    
				        	   // System.out.println("INDEX: " +index);
				        	 
				        	}
				         
				         String[] split = res.split("=");
				         String firstSubString = split[0];
				         String secondSubString = split[1];
				         System.out.println("First:" +firstSubString);
				         System.out.println("Second:" +secondSubString); */
				         
				         
				        	
				        runOnUiThread(new Runnable() {
					            public void run() {
					            	
					            	arr = Arrays.toString(strArray);         
					            	arr = arr.substring(1, arr.length()-1).replaceAll(",", "");
					                System.out.println("String: " + arr); 
					            				           
					                String pattern1 =  "item=anyType{";
					            	String pattern2 =  " }";
					            	String s="";
					            	
					            	Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + (Pattern.quote(pattern2)));
					            	Matcher m = p.matcher(result);
					                	ArrayList<String> allValues = new ArrayList<String>();
						            	while (m.find()) {
						            		System.out.println(m.group(0));
						            		s =m.group(0);
						           
						            		  rephrase = null;
							            	    if (s != null && s.length() > 1) {
							            	        rephrase = s.substring(13, s.length() - 2);
							            	        
							            	    	String strArray1[] = rephrase.split(" ");
										               
										               array = new String[strArray1.length][];
										            	  
										                 // split the lines
										         	for ( int i = 0; i < strArray1.length; i++ )
										         		array [i] = strArray[i].split("=");    
										         	
										            //  wq = new String[array.length];
										         	     w = new String[array.length];
										         	
										                 // separate the array into arrays of first and second words
										      /*   	for ( int i = 0; i < array.length; i++ )
										         	    {
										         	  //  wq[i] = array[i][0];
										         	    w[i] = array[i][1];
										         	    }
										         
										         	String s1 = Arrays.toString(wq);
									            	s1 = s1.substring(1, s1.length()-1).replaceAll(",", "");
									            	System.out.println("SQ VALUE" +s1);  
									            	  String s2 = Arrays.toString(w);
									            	  s2 = s2.substring(1, s2.length()-1).replaceAll(",", "");
									            	  System.out.println("S VALUE" +s2); */
										         	    
									            	  
										         	
										           	//System.out.println("S1 = "+s1);
										         //	System.out.println("S = "+s3);
										        
							            	        
							            	    }
							            	//System.out.println("R:"+rephrase);
							            	allValues.add(rephrase);
							            	}
						            	
						            	stockArr = new String[allValues.size()];
						            	stockArr = allValues.toArray(stockArr);
						            	for(String s2 : stockArr)
						            	    System.out.println("S2:" +s2); 
						            	
						            	String op = Arrays.toString(stockArr);
						            	  op = op.substring(1, op.length()-1).replaceAll(",", "");
						            	System.out.println("OUT:" +op);

					            	
						            	   String separator = ";";
							            	arrExploded = explodeStringUsingCoreJava(op,separator);
							            	int len = res.length();
							            	System.out.println("Length is "+len);
							            	//for(int i=1;i<=len;i++)
							            	{
							            	System.out.println(arrExploded[1]);
							            	}
							            
							            	array = new String[arrExploded.length][];
						            	    
						                 // split the lines
						         	for ( int i = 0; i < arrExploded.length; i++ )
						         		array [i] = arrExploded[i].split("=");    
						         	 System.out.println("ARRAY:" +Arrays.deepToString(array));
						         	     w1 = new String[array.length];
						         	     w2 = new String[array.length];
						         	
						                 // separate the array into arrays of first and second words
						         	for ( int i = 0; i < array.length; i++ )
						         	    {
						         	    w1[i] = array[i][0];
						         	    w2[i] = array[i][1];
						         	    
						          		
					         	    }
				            
			            
					         	
					         	String s1 = Arrays.toString(w1);
				            	  s1 = s1.substring(1, s1.length()-1).replaceAll(",", "");
				            	  
				            	  String s2 = Arrays.toString(w2);
				            	  s2 = s2.substring(1, s2.length()-1).replaceAll(",", "");
					         	
					           	System.out.println("W1 = "+s1);
					         	System.out.println("W2 = "+s2); 
					        		
					         	//tableview();
					         	addHeader();
					         	//addData();
					            /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(WebService.this, android.R.layout.simple_list_item_1, arrExploded);
					              listView1.setAdapter(adapter); */ 
					         	
					         	
					         	  int arrayLength = array.length;
					         	  System.out.println("Array Length:" +arrayLength);
				            	  int three = (int) Math.floor(arrayLength/3);
				            	  //int half = (int)Math.floor(array/2);
				            	  int numberOfElementsInArray = array[1].length;
				            	  System.out.println("Number of Elements in splitted Array:" +numberOfElementsInArray);
				            	   A = new String[three][numberOfElementsInArray];
				            	  System.out.println("B");
				            	  B = new String[array.length - three][numberOfElementsInArray];
				            	  C = new String[array.length - three-three][numberOfElementsInArray];
				   
				            	  System.arraycopy(array,0,A,0,three);         	  
				            	  System.out.println("\n HALF ARRAY CONTENTS:" +Arrays.deepToString(A));
				            	  oneThird = Arrays.deepToString(A);
				            	  
				            	  h1 = new String[A.length];
					         	  h2 = new String[A.length];
				            	  
				            	  for ( int i = 0; i < A.length; i++ )
					         	    {
					         	    h1[i] = A[i][0];
					         	   System.out.print("H1:" +h1[i]);
					         	   h2[i] = A[i][1];
					         	  System.out.print("H2:" +h2[i]);
					         	    }  
				            	  
				            	  oneThird = oneThird.substring(1, oneThird.length()-1).replaceAll(",", "");
					                System.out.println("FIRST HALF: " + oneThird);
					               // addValue(h2);
				            	  
				            	  {
//				       			   List<String> name = Arrays.asList(w1);
				       			      List<String> value11  = Arrays.asList(h2);
				       			 //     List<String> value12  = Arrays.asList(h4);
				       			      ll = (TableLayout) findViewById(R.id.tl);
				       			      //TableLayout table = (TableLayout) findViewById(R.id.tl);
				       			      View v = new View(WebService.this);
				       			      View v1 = new View(WebService.this);
				       			      int i=0;
				       			    //  TableRow row1=new TableRow(this);
				       			      TableRow row2=new TableRow(WebService.this);
				       			      for(i=0;i<w.length;i++)
				       			      {

				       			    	 
//				       			         String name1 = name.get(i);
				       			          String value1 = value11.get(i);
				       			   //       String value2 = value12.get(i);
				       			          
				       			          TextView tvName=new TextView(WebService.this);
//				       			          tvName.setText(""+name1);
				       			         
				       			          TextView tvValue=new TextView(WebService.this);
				       			          tvValue.setText(""+value1);
				       			          
				       			     //     TextView tvValue1=new TextView(this);
				       			     //     tvValue1.setText(""+value2);
				       			         
//				       			          tvName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
//				       			          tvName.setPadding(10, 10, 10, 10);
				       			          tvValue.setPadding(10, 10, 10, 10);
				       			     //     tvValue1.setPadding(10, 10, 10, 10);
				       			          
				       			          v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
				       			          v.setBackgroundColor(Color.rgb(51, 51, 51));
				       			          
//				       			          v1.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
//				       			          v1.setBackgroundColor(Color.rgb(51, 51, 51));
				       			          
				       			       //   row1.addView(tvName);
				       			          row2.addView(tvValue);
				       			       //   row2.addView(tvValue1);
//				       		          ll.addView(row1);
				       			      }
				       			      //ll.addView(row1);

				       			   //   ll.addView(v1);
				       			     ll.addView(row2, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
				       			      ll.addView(v);
				       		   }

					          		
				         	    
					            	
				            	  
					              System.arraycopy(array,three,B,0,array.length - three);
				            	  System.out.println("\n SECOND HALF ARRAY CONTENTS:" +Arrays.deepToString(B));
				            	  twoThird = Arrays.deepToString(B);
				            	  
				            	  h3 = new String[B.length];
					         	  h4  = new String[B.length];
				            	  
				            	  for ( int i = 0; i < B.length; i++ )
					         	    {
				            		//Field Name of Second half
					         	    h3[i] = B[i][0];
					         	   System.out.print(h3[i]);
					         	   //Field Value of second half
					         	    h4[i] = B[i][1];
					         	   System.out.print(h4[i]);
					         	    }    
					          		
				         	    
				            	  
					            	twoThird = twoThird.substring(1, twoThird.length()-1).replaceAll(",", "");
					                System.out.println("SECOND HALF: " + twoThird);
					               // addValue(h4);
					                
					                {
//						       			   List<String> name = Arrays.asList(w1);
						       			      List<String> value11  = Arrays.asList(h4);
						       			 //     List<String> value12  = Arrays.asList(h4);
						       			      ll = (TableLayout) findViewById(R.id.tl);
						       			      //TableLayout table = (TableLayout) findViewById(R.id.tl);
						       			      View v = new View(WebService.this);
						       			      View v1 = new View(WebService.this);
						       			      int i=0;
						       			    //  TableRow row1=new TableRow(this);
						       			      TableRow row2=new TableRow(WebService.this);
						       			      for(i=0;i<w.length;i++)
						       			      {

						       			    	 
//						       			         String name1 = name.get(i);
						       			          String value1 = value11.get(i);
						       			   //       String value2 = value12.get(i);
						       			          
						       			          TextView tvName=new TextView(WebService.this);
//						       			          tvName.setText(""+name1);
						       			         
						       			          TextView tvValue=new TextView(WebService.this);
						       			          tvValue.setText(""+value1);
						       			          
						       			     //     TextView tvValue1=new TextView(this);
						       			     //     tvValue1.setText(""+value2);
						       			         
//						       			          tvName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
//						       			          tvName.setPadding(10, 10, 10, 10);
						       			          tvValue.setPadding(10, 10, 10, 10);
						       			     //     tvValue1.setPadding(10, 10, 10, 10);
						       			          
						       			          v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
						       			          v.setBackgroundColor(Color.rgb(51, 51, 51));
						       			          
//						       			          v1.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
//						       			          v1.setBackgroundColor(Color.rgb(51, 51, 51));
						       			          
						       			       //   row1.addView(tvName);
						       			          row2.addView(tvValue);
						       			       //   row2.addView(tvValue1);
//						       		          ll.addView(row1);
						       			      }
						       			      //ll.addView(row1);

						       			   //   ll.addView(v1);
						       			     ll.addView(row2, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
						       			      ll.addView(v);
						       		   } 

					                
					                System.arraycopy(array,array.length-three,C,0,array.length - three - three);
					            	  System.out.println("\n SECOND HALF ARRAY CONTENTS:" +Arrays.deepToString(C));
					            	  last = Arrays.deepToString(C);
					            	  
					            	  h5 = new String[C.length];
						         	  h6  = new String[C.length];
					            	  
					            	  for ( int i = 0; i < C.length; i++ )
						         	    {
					            		//Field Name of Second half
						         	    h5[i] = C[i][0];
						         	   System.out.print(h5[i]);
						         	   //Field Value of second half
						         	    h6[i] = C[i][1];
						         	   System.out.print(h6[i]);
						         	    }    
						          		
					         	    
					            	  
						            	last = last.substring(1, last.length()-1).replaceAll(",", "");
						                System.out.println("THIRD HALF: " + last);
						               // addValue(h4);
						                
						                {
//							       			   List<String> name = Arrays.asList(w1);
							       			      List<String> value11  = Arrays.asList(h6);
							       			 //     List<String> value12  = Arrays.asList(h4);
							       			      ll = (TableLayout) findViewById(R.id.tl);
							       			      //TableLayout table = (TableLayout) findViewById(R.id.tl);
							       			      View v = new View(WebService.this);
							       			      View v1 = new View(WebService.this);
							       			      int i=0;
							       			    //  TableRow row1=new TableRow(this);
							       			      TableRow row2=new TableRow(WebService.this);
							       			      for(i=0;i<w.length;i++)
							       			      {

							       			    	 
//							       			         String name1 = name.get(i);
							       			          String value1 = value11.get(i);
							       			   //       String value2 = value12.get(i);
							       			          
							       			          TextView tvName=new TextView(WebService.this);
//							       			          tvName.setText(""+name1);
							       			         
							       			          TextView tvValue=new TextView(WebService.this);
							       			          tvValue.setText(""+value1);
							       			          
							       			     //     TextView tvValue1=new TextView(this);
							       			     //     tvValue1.setText(""+value2);
							       			         
//							       			          tvName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
//							       			          tvName.setPadding(10, 10, 10, 10);
							       			          tvValue.setPadding(10, 10, 10, 10);
							       			     //     tvValue1.setPadding(10, 10, 10, 10);
							       			          
							       			          v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
							       			          v.setBackgroundColor(Color.rgb(51, 51, 51));
							       			          
//							       			          v1.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
//							       			          v1.setBackgroundColor(Color.rgb(51, 51, 51));
							       			          
							       			       //   row1.addView(tvName);
							       			          row2.addView(tvValue);
							       			       //   row2.addView(tvValue1);
//							       		          ll.addView(row1);
							       			      }
							       			      //ll.addView(row1);

							       			   //   ll.addView(v1);
							       			     ll.addView(row2, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
							       			     // ll.addView(v);
							       		   } 

						         	
						            }

					         	
					            

								
					          });

				        	
						     
				        } catch (Exception e) {
				            System.out.println("Error: " +e.toString());
				        }
				        
				    }
				    
				});
			  
			 	thread.start();
			 	return res;
		}
			 		     
	  /*  catch (Exception e) {
		    	 System.out.println("Error1: " +e.toString());
		    } */
	        
	
	
	private String[] explodeStringUsingCoreJava(String stringToExplode,
			String separator) {
		 return  stringToExplode.split(separator);
	}
				 
		/*	 public void addData(){
				 
			        for (int i = 0; i < array.length; i++)
			        {
			           
			            tr = new TableRow(this);
			            tr.setLayoutParams(new LayoutParams(
			                    LayoutParams.FILL_PARENT,
			                    LayoutParams.WRAP_CONTENT));
			 
			           
			            TextView valueTV = new TextView(this);
			            valueTV.setText(w2[i]);
			            valueTV.setTextColor(Color.GREEN);
			            valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
			            valueTV.setPadding(5, 5, 5, 0);
			            valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
			            tr.addView(valueTV); // Adding textView to tablerow.
			            
			            
			            
			            ll.addView(tr, new TableLayout.LayoutParams(
			                    LayoutParams.FILL_PARENT,
			                    LayoutParams.WRAP_CONTENT));
			        }
			 } */
			 
			 public void addHeader(){
				/*  TableRow row=new TableRow(this.getApplicationContext());
				  
				  row=new TableRow(this);

			      TextView text1=new TextView(this.getApplicationContext());
			      TextView text2=new TextView(this.getApplicationContext());
			      TextView text3=new TextView(this.getApplicationContext());
			      TextView text4=new TextView(this.getApplicationContext());
			      TextView text5=new TextView(this.getApplicationContext());
			      TextView text6=new TextView(this.getApplicationContext());
			      TextView text7=new TextView(this.getApplicationContext());
			      TextView text8=new TextView(this.getApplicationContext());
			      TextView text9=new TextView(this.getApplicationContext());
			      TextView text10=new TextView(this.getApplicationContext());
			      TextView text11=new TextView(this.getApplicationContext());
			      TextView text12=new TextView(this.getApplicationContext());
			      TextView text13=new TextView(this.getApplicationContext());
			      TextView text14=new TextView(this.getApplicationContext());
			      TextView text15=new TextView(this.getApplicationContext());
			      TextView text16=new TextView(this.getApplicationContext());
			      TextView text17=new TextView(this.getApplicationContext());
			      TextView text18=new TextView(this.getApplicationContext());
			     

			      text1.setText(R.string.Mandt);
			      row.addView(text1);
			      text2.setText(R.string.Bname);
			      row.addView(text2);
			      text3.setText(R.string.Stcod);
			      row.addView(text3);
			      text4.setText(R.string.Spld);
			      row.addView(text4);
			      text5.setText(R.string.Splg);
			      row.addView(text5);
			      text6.setText(R.string.Spdb);
			      row.addView(text6);
			      text7.setText(R.string.Spda);
			      row.addView(text7);
			      text8.setText(R.string.Datfm);
			      row.addView(text8);
			      text9.setText(R.string.Dcpfm);
			      row.addView(text9);
			      text10.setText(R.string.Hdest);
			      row.addView(text10);
			      text11.setText(R.string.Hmand);
			      row.addView(text11);
			      text12.setText(R.string.Hname);
			      row.addView(text12);
			      text13.setText(R.string.Menon);
			      row.addView(text13);
			      text14.setText(R.string.Menue);
			      row.addView(text14);
			      text15.setText(R.string.Strtt);
			      row.addView(text15);
			      text16.setText(R.string.Langu);
			      row.addView(text16);
			      text17.setText(R.string.Cattkennz);
			      row.addView(text17);
			      text18.setText(R.string.Timefm);
			      row.addView(text18); 
			      ll.addView(row); */
			      
			      List<String> name = Arrays.asList(w1);
			      List<String> value  = Arrays.asList(w2);  
			      //TableLayout table = (TableLayout) findViewById(R.id.tl);
			      View v = new View(this);
			      View v1 = new View(this);
			      TableRow row1=new TableRow(this);
			      TableRow row2=new TableRow(this);
			      for(int i=0;i<w.length;i++)
			      {
//			    	  TableRow row1=new TableRow(this);

			         String name1 = name.get(i);
			          String value1 = value.get(i);
			          
			          TextView tvName=new TextView(this);
			          tvName.setText(""+name1);
			         
			          TextView tvValue=new TextView(this);
			          tvValue.setText(""+value1);
			         
			          tvName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
			          tvName.setPadding(10, 10, 10, 10);
			          tvValue.setPadding(10, 10, 10, 10);

			          v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
			          v.setBackgroundColor(Color.rgb(51, 51, 51));
			          
			          v1.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
			          v1.setBackgroundColor(Color.rgb(51, 51, 51));
			          
			          
			          row1.addView(tvName);
			          row2.addView(tvValue);
//			          ll.addView(row1);
			      }
			      
			      ll.addView(row1);
			      ll.addView(v);
			   //   ll.addView(v1);
			    //  ll.addView(row2);
			      
			     
		          
			      
			      ll.setBackgroundDrawable(getResources().getDrawable(R.drawable.table));
			    		   
			 
			   //    for (int i = 0; i < array.length; i++)
			     //   {
			            /** Create a TableRow dynamically **/
			    	//    row=new TableRow(this.getApplicationContext());
			    	 
			        //    row = new TableRow(this);
			        //    TextView valueTV = new TextView(this);
			           /* tr.setLayoutParams(new LayoutParams(
			                    LayoutParams.FILL_PARENT,
			                    LayoutParams.WRAP_CONTENT)); */
			 
			            /** Creating a TextView to add to the row **/
			            
			          //  valueTV.setText(w2[i]);
			          //  valueTV.setTextColor(Color.BLACK);
			          //  valueTV.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1.0f));
			          //  valueTV.setPadding(20, 20, 20, 20);
			          
			          //  valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD); 
			           // row.addView(valueTV); // Adding textView to tablerow. 
				        
			           // ll.addView(row);
			            

			           
			            /*  ll.addView(row, new TableLayout.LayoutParams(
			                    LayoutParams.FILL_PARENT,
			                    LayoutParams.WRAP_CONTENT)); */  
			       // }			  
			 }
	
}
			
		   /* for (int j = 0; j < array.length; j++)
					    	{
					    		final TableRow tr = new TableRow(this);
					    		LayoutParams lp = new LayoutParams(150,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

					    		//tr.setId(i);

					    		tr.setLayoutParams(lp);
					    		lp.setMargins(0,20, 0, 0);

					    		final TextView tvLeft = new TextView(this);
					    		tvLeft.setLayoutParams(lp);
					    		tvLeft.setText(w1[j]);
					    		
					    		final TextView tvCenter = new TextView(this);
					    		tvCenter.setLayoutParams(lp);
					    		tvCenter.setText(w2[j]);
					    		
					    		tr.addView(tvLeft);
					    		tr.addView(tvCenter);
					    		
					    		ll.addView(tr, new TableLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
					    		
					    	} */
	
	
	
	
			          


	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
	}

	class Persondetails extends AsyncTask<Void, Void, Void> {
	private final ProgressDialog dialog = new ProgressDialog(WebService.this);

	@Override
	protected void onPreExecute() {
	this.dialog.setMessage("Loading data");
	this.dialog.show();
	}


	@Override
	protected Void doInBackground(Void... unused) {

		final String NAMESPACE = "urn:sap-com:document:sap:soap:functions:mc-style"; // Name space from WSDL
		final String METHOD_NAME ="ZGssmwfmTestApi3"; //Operation Name form WSDL
		final String SOAP_ACTION = "http://75.99.152.10:8050/sap/bc/srt/wsdl/bndg_E0B1187AA52617F1AE7900188B47B426/wsdl11/allinone/ws_policy/document?sap-client=110";
		final String URL ="http://75.99.152.10:8050/sap/bc/srt/rfc/sap/z_gssmwfm_test_api3/110/z_gssmwfm_test_api3/z_gssmwfm_test_api3"; // address location
		

	     SoapObject request2 = new SoapObject(NAMESPACE, METHOD_NAME);
	     SoapSerializationEnvelope envelope2 = new SoapSerializationEnvelope(
		            SoapEnvelope.VER11);
		     request2.addProperty("DpixInpt","GUJERSEY");
		     envelope2.setOutputSoapObject(request2);
		    

	Bean C = new Bean();
	PropertyInfo pi = new PropertyInfo();
	pi.setName("Bean");
	pi.setValue(C);
	pi.setType(C.getClass());
	
	envelope2.addMapping(NAMESPACE, "Bean", new Bean().getClass());
	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	androidHttpTransport.debug = true;

	try {
	androidHttpTransport.call(SOAP_ACTION, envelope2);
	SoapObject response2 = (SoapObject) envelope2.getResponse();
	 String result1 = envelope2.getResponse().toString();
     System.out.println("result is : " +result1);

	System.out.println("check Request" + request2);  
	System.out.println("" + response2);

	
	Bean[] personobj = new Bean[response2.getPropertyCount()];
	Bean beanobj = new Bean();

	for (int j = 0; j < personobj.length; j++) {
	SoapObject pii = (SoapObject) response2.getProperty(j);
	beanobj.Mandt = pii.getProperty(0).toString();
	beanobj.Bname = pii.getProperty(1).toString();
	beanobj.Stcod = pii.getProperty(2).toString();
	beanobj.Spld = pii.getProperty(3).toString();
	beanobj.Splg = pii.getProperty(4).toString();
	beanobj.Spdb = pii.getProperty(5).toString();
	beanobj.Spda = pii.getProperty(6).toString();
		personobj[j] = beanobj;
	}

	mandt=beanobj.Mandt;
    bname=beanobj.Bname;
    stcod=beanobj.Stcod;
    spld=beanobj.Spld;
    splg=beanobj.Splg;
    spdb=beanobj.Spdb;
    spda=beanobj.Spda;
	} 
		
		
	catch(SocketTimeoutException e){
	timeoutexcep=true;
	e.printStackTrace();
	}
	catch(ConnectException e){
	httpexcep=true;
	e.printStackTrace();
	}
	catch (Exception e) {
	generalexcep=true;
	e.printStackTrace();
	}
	return null;

	}
	@Override
	protected void onPostExecute(Void result) {
	if (this.dialog.isShowing()) {
	this.dialog.dismiss();
	}

	if(timeoutexcep){
	Toast.makeText(WebService.this, "Unable to connect to server, Please try again later",Toast.LENGTH_LONG).show();
	}
	else if(httpexcep){
	Toast.makeText(WebService.this, "Please check your Internet connection",Toast.LENGTH_LONG).show();
	}
	else if(generalexcep){
	Toast.makeText(WebService.this, "Please try later",Toast.LENGTH_LONG).show();
	}

	else {
	display();
	}
	timeoutexcep=false;httpexcep=false;generalexcep=false;
	}

	private void display() {

	TextView t1=(TextView)findViewById(R.id.mandt);
	t1.setText(mandt);
	TextView t2=(TextView)findViewById(R.id.bname);
    t2.setText(bname);
    TextView t3=(TextView)findViewById(R.id.stcod);
    t3.setText(stcod);
    TextView t4=(TextView)findViewById(R.id.spld);    
    t4.setText(spld);
    TextView t5=(TextView)findViewById(R.id.splg);
    t5.setText(splg);
    TextView t6=(TextView)findViewById(R.id.spdb);
    t6.setText(spdb);
    TextView t7=(TextView)findViewById(R.id.spda);
    t7.setText(spda);
	
	}
	}
	} */

