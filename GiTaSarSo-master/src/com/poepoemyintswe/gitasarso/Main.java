package com.poepoemyintswe.gitasarso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class Main extends Activity {
	
	ListView l;
	
	
	Sounds play = new Sounds(this);
	
	
public static final int [] song = {R.raw.a1, R.raw.a2, R.raw.a3, R.raw.a4,
			R.raw.a5, R.raw.a6, R.raw.a7, R.raw.a8,
			R.raw.a9, R.raw.a10, R.raw.a11 };
	
	public static final  String [] title = {" ညနေမျက်ရည်", "ဆေး ", "လမ်းဘေးမှာရှိသည်", "Hello December", "နန်း", 
			"တကယ်ချစ်ရင် ", "မင်းနဲ့ပတ်သတ်ရင်", "No Hero ", "လူသားသစ္စာ", "သူငယ်ချင်းသို့ ပေးစာ ", 
			"ပြည်ပသို့ပေးစာ"};
	
	public static final  String [] singers = {"ဝေလ", "ဝေလ", "ဝေလ ", "ပြုံးမြသွေး", "ထက်ယံ","Rဇာနည်", 
			"ရေမွန်", "စိုင်းစိုင်း", "G-Fatt", "လွှမ်းပိုင် ", "G-Fatt"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		l = (ListView)findViewById(R.id.list);
		@SuppressWarnings("unchecked")
		SimpleAdapter s = new SimpleAdapter( this, (List<? extends Map<String, ?>>) GetData(), R.layout.item, new String[] { "icon", "title", "singers" }, new int[] {R.id.img, R.id.song_title, R.id.singers})
		{
			@SuppressWarnings("deprecation")
			public View getView(int position, View view, ViewGroup parent) {
				View v = view;
				if (v == null) {
					LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					v = vi.inflate(R.layout.item, null);
				}
				
				TextView t1 = (TextView)v.findViewById(R.id.song_title);
				ChangeFont(t1);
				t1.setTextColor(Color.rgb((int)(Math.random()*100), (int)(Math.random()*100), (int)(Math.random()*100)));
				
				TextView t2 = (TextView)v.findViewById(R.id.singers);
				ChangeFont(t2);
				t2.setTextColor(Color.rgb((int)(Math.random()*100)+50, (int)(Math.random()*100)+50, (int)(Math.random()*100)+50));
				
				return super.getView(position, v, parent);
			}
		};
		l.setAdapter(s);
		
		l.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				
				Intent i = new Intent(getBaseContext(), Detail.class);
				i.putExtra("pressed", arg2);
				startActivity(i);
			}
		});
		
	}
	
	private void ChangeFont(TextView t) {
		if (Build.VERSION.SDK_INT > 14) {
			Typeface face = Typeface.createFromAsset(this.getAssets(), String.format("fonts/%s", "ZAWGYI1.TTF")); 
			t.setTypeface(face);
		}
		else {
			Typeface face = Typeface.createFromAsset(this.getAssets(), String.format("fonts/%s", "ZAWGYI.TTF")); 
			t.setTypeface(face);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> GetData() {
		List<Map> list = new ArrayList<Map>();
		for (int i = 0; i < title.length  ; i++) {
			Map map = new HashMap();
			map.put("icon", R.drawable.img);
			map.put("title", title[i]);
			map.put("singers", singers[i]);
			list.add(map);
		}
		return list;
	}
	
}
