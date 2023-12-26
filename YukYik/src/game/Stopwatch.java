package game;


import javax.swing.JLabel;

public class Stopwatch {
	private boolean stop = false;
	private int count,min,sec;
	private String timeText;
	
	public Stopwatch(int count) {
		this.count=count;
		min = count/60;
		sec = count%60;	
	}
	
	public void getStopwatch(JLabel time) {
		new Thread() {
		 	public void run() {
		 		while(stop==false ) {
		     		if(sec==60) {
		    			sec=0;
		    			min++;
		     			}
		     		if(sec<10) {
		    			timeText = "0"+Integer.toString(min)+":0"+Integer.toString(sec);
		    			time.setText(timeText);
		    		}else {
		    			timeText = "0"+Integer.toString(min)+":"+Integer.toString(sec);
		    			time.setText(timeText);
		    			}	    		
		    		sec++;
		    		count++;
		            try{
		                 Thread.sleep(1000);
		               } catch(Exception e) {}
		            }
		        } 	
		   }.start();
	}
	
	public void stopStopwatch(boolean input) {
		stop = input;
	}
	
	public int getBonusCollect() {
		int bonusCollect;
		if(count < 150) {
			bonusCollect = 1000-count;
		}else {
			bonusCollect = 0;
			}
		return bonusCollect;
	}
	
	public int getCount() {
		return count;
	}
}