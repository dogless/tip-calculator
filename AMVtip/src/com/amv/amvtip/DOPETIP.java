package com.amv.amvtip;

import android.os.Bundle;
import java.text.DecimalFormat;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class DOPETIP extends Activity implements OnClickListener {
	private static final String BILL_TOTAL = "BILL_TOTAL";
	private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";
	private static final String PARTY = "PARTY";
	TextView billAmount;
	TextView gTotal;
	TextView customTip;
	TextView switcher;
	TextView tipPerPerson;
	SeekBar peopletipBar;
	Button one;
	Button two;
	Button three;
	Button four;
	Button five;
	Button six;
	Button seven;
	Button eight;
	Button nine;
	Button zero;
	Button decimal;
	Button clear;
	DecimalFormat df = new DecimalFormat("#.##");
	
	String display = ""; //string holder for billAmount
	double numBillAmount = 0;
	double grandTotal = 0;
	double tip = 15;
	double individualtip;
	int people = 1;
	boolean flip = true;
	int counter = 0;
	
	
	public void initialize () {
		billAmount = (TextView) findViewById (R.id.billAmount);
		gTotal = (TextView) findViewById (R.id.gTotal);
		customTip = (TextView) findViewById (R.id.customTip);
		switcher = (TextView) findViewById (R.id.PEOPLE);
		tipPerPerson = (TextView) findViewById (R.id.tipPerPerson);
		one = (Button) findViewById (R.id.one);
		one.setOnClickListener(this);
		two = (Button) findViewById (R.id.two);
		two.setOnClickListener(this);
		three = (Button) findViewById (R.id.three);
		three.setOnClickListener(this);
		four = (Button) findViewById (R.id.four);
		four.setOnClickListener(this);
		five = (Button) findViewById (R.id.five);
		five.setOnClickListener(this);
		six = (Button) findViewById (R.id.six);
		six.setOnClickListener(this);
		seven = (Button) findViewById (R.id.seven);
		seven.setOnClickListener(this);
		eight = (Button) findViewById (R.id.eight);
		eight.setOnClickListener(this);
		nine = (Button) findViewById (R.id.nine);
		nine.setOnClickListener(this);
		zero = (Button) findViewById (R.id.zero);
		zero.setOnClickListener(this);
		decimal = (Button) findViewById (R.id.decimal);
		decimal.setOnClickListener(this);
		clear = (Button) findViewById (R.id.clear);
		clear.setOnClickListener(this);
		peopletipBar = (SeekBar) findViewById (R.id.tipBar);
		switcher.setOnClickListener(this);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dopetip);
        if (!(savedInstanceState == null))
        {
        	display = savedInstanceState.getString(BILL_TOTAL);
        	people = savedInstanceState.getInt(PARTY);
        	tip = savedInstanceState.getDouble(CUSTOM_PERCENT);
        }
        initialize();
        billAmount.setText("$0.00");
        gTotal.setText("$0.00");
        tipPerPerson.setText("$0.00");
        peopletipBar.setProgress(people);
        customTip.setText(df.format(people));
        switcher.setText("Party Members");
        peopletipBar.setOnSeekBarChangeListener(customSeekBarListener);
        
    }
    
    public void update() {
    	
    	if (display == "") {
    		billAmount.setText("$0.00");
    	}
    	else {
    		billAmount.setText("$" + display);
    	}
    	
    	if (grandTotal == 0)
    	{
    		gTotal.setText("$0.00");
    	}
    	else 
    	{
    		gTotal.setText("$" + df.format(grandTotal));
    	}
    	
    
    	if (flip == false) {
    		customTip.setText(tip+ "%");
    	} else if (flip == true) {
    		customTip.setText(df.format(people));
    	}

    	if (!(display == "")){
    		numBillAmount = (Double.parseDouble(display));
    		grandTotal = (numBillAmount * (1+(tip/100)));
        	gTotal.setText("$"+df.format(grandTotal));
        	individualtip = (grandTotal-numBillAmount)/people;
        	tipPerPerson.setText("$"+df.format(individualtip));
    	}
    	
    	
    }
    
    private OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener()
    {
    	@Override
    	public void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
    	{
    		if (flip == false) {
    			tip = seekBar.getProgress();
    		} else if (flip == true) {
    			people = seekBar.getProgress();
    			update();
    		}
       		update();
    	}
    	@Override
    	public void onStopTrackingTouch(SeekBar seekBar)
    	{
    	}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) 
		{	
		}
    	
    };
    
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
    	super.onSaveInstanceState(outState);
    	
    	outState.putString(BILL_TOTAL, display);
    	outState.putInt(PARTY, people);
    	outState.putDouble(CUSTOM_PERCENT, tip);
    }
   

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dopetip, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (display.length()<= 7) {
			switch(v.getId()){
			case R.id.decimal:
				if (!(display == ""))
				{
				if (counter == 0){
				display += ".";
				}
				counter++;
				update();
				}
				break;
			case R.id.zero:
				if (!(display == ""))
				{
				display += "0";
				update();
				}			
				break;
			case R.id.one:
				display += "1";
				update();
				break;
			case R.id.two:
				display += "2";
				update();
				break;
			case R.id.three:
				display += "3";
				update();
				break;
			case R.id.four:
				display += "4";
				update();
				break;
			case R.id.five:
				display += "5";
				update();
				break;
			case R.id.six:
				display += "6";
				update();
				break;
			case R.id.seven:
				display += "7";
				update();
				break;
			case R.id.eight:
				display += "8";
				update();
				break;
			case R.id.nine:
				display += "9";
				update();
				break;
			}
		}
		
		switch (v.getId()) {
		case R.id.clear:
			display = "";
			billAmount.setText("$0.00");
			grandTotal = 0;
			gTotal.setText("$0.00");
			individualtip = 0;
			tipPerPerson.setText("$0.00");
			counter = 0;
			break;
		case R.id.PEOPLE:
			if (switcher.getText() == "Party Members"){
				switcher.setText("Custom Tip");
				flip = false;
				peopletipBar.setProgress((int) tip);
				update();			
			}
			else if (switcher.getText() == "Custom Tip")
			{
				switcher.setText("Party Members") ;
				flip = true;
				peopletipBar.setProgress(people);
				update();
			}
			break;
		}
	}
}
