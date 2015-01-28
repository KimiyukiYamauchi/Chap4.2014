package com.example.chap4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.CompoundButton;

public class MainActivity extends Activity 
	implements View.OnClickListener,
		OnCheckedChangeListener,
		RadioGroup.OnCheckedChangeListener,
		Spinner.OnItemSelectedListener,
		TextView.OnEditorActionListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.WHITE);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		int wc = LinearLayout.LayoutParams.WRAP_CONTENT;
		// �{�^���̐���
		Button btn = new Button(this);
		btn.setText("PUSH");
		btn.setTag("btn1");
		btn.setLayoutParams(new LinearLayout.LayoutParams(300,wc));
		btn.setOnClickListener(this);

		layout.addView(btn);
		
		// �e�L�X�g�r���[�̐���
		TextView tv = new TextView(this);
		tv.setText("???");
		tv.setTag("tv1");
		layout.addView(tv);
		
		// �`�F�b�N�{�b�N�X�̐���
		CheckBox chk = new CheckBox(this);
		chk.setText("CHECK");
		chk.setChecked(false);
		chk.setTag("chk1");
		chk.setLayoutParams(new LinearLayout.LayoutParams(300,wc));
		chk.setOnCheckedChangeListener(this);
		layout.addView(chk);
		
		// ���W�I�{�^���̐���
		RadioButton rb1 = new RadioButton(this);
		rb1.setId(1);
		rb1.setText("RADDIO BUTTON-1");
		RadioButton rb2 = new RadioButton(this);
		rb2.setId(2);
		rb2.setText("RADDIO BUTTON-2");
		// �O���[�v�쐬
		RadioGroup rg = new RadioGroup(this);
		rg.addView(rb1);
		rg.addView(rb2);
		rg.check(1);
		rg.setTag("rg1");
		rg.setLayoutParams(new LinearLayout.LayoutParams(600,wc));
		rg.setOnCheckedChangeListener(this);
		layout.addView(rg);
		
		// �I�����̏���
		int ssi = android.R.layout.simple_spinner_item;
		int ssdi = android.R.layout.simple_spinner_dropdown_item;
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this, ssi);
		ad.setDropDownViewResource(ssdi);
		ad.add("SP1");
		ad.add("SP2");
		ad.add("SP3");
		
		// �X�s�i�[�̐���
		Spinner sp = new Spinner(this);
		sp.setAdapter(ad);
		sp.setSelection(0);
		sp.setTag("sp1");
		sp.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		sp.setOnItemSelectedListener(this);
		layout.addView(sp);
		
		// �G�f�B�b�g�e�L�X�g�̐���
		EditText ed = new EditText(this);
		ed.setText("EDIT");
		ed.setTag("ed1");
		ed.setInputType(InputType.TYPE_CLASS_TEXT);
		ed.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		ed.setOnEditorActionListener(this);
		layout.addView(ed);
		
		setContentView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuItem i0 = menu.add(0, 0, 0, "Add");
		i0.setIcon(android.R.drawable.ic_menu_add);
		i0.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		MenuItem i1 = menu.add(0, 1, 0, "Delete");
		i1.setIcon(android.R.drawable.ic_menu_delete);
		i1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		String text = "";
		switch(id){
		case 0: text = "Add"; break;
		case 1: text = "Del"; break;
		default: return false;
		}
		
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setMessage(text);
		ad.setPositiveButton("OK", null);
		ad.show();
		
		return true;
	}

	@Override
	public void onClick(View view) {
		String tag = (String)view.getTag();
		if(tag == "btn1"){
			View p = (View)view.getParent();
			TextView tv = (TextView)p.findViewWithTag("tv1");
			tv.setText("BOMB!");
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton view, boolean isChecked) {
		String tag = (String)view.getTag();
		if(tag == "chk1"){
			View p = (View)view.getParent();
			TextView tv = (TextView)p.findViewWithTag("tv1");
			tv.setText(isChecked ? "CHECK!" : "UNCHECK!");
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup view, int chkid) {
		String tag = (String)view.getTag();
		if(tag == "rg1"){
			View p = (View)view.getParent();
			TextView tv = (TextView)p.findViewWithTag("tv1");
			tv.setText("RADIO BUTTON-" + chkid);
		}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
                                        int position, long id) {
		String tag = (String)parent.getTag();
		if(tag == "sp1"){
			View parent2 = (View)parent.getParent();
			TextView tv = (TextView)parent2.findViewWithTag("tv1");
			tv.setText( ((TextView)view).getText() +  "[" + position + "]");
        }
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		String tag = (String)view.getTag();
		if(tag == "ed1"){
			View parent = (View)view.getParent();
			TextView tv = (TextView)parent.findViewWithTag("tv1");
			tv.setText(view.getText());
		}
		return false;
	}
}
