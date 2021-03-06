package com.zhy.Activity;
import java.util.ArrayList;
import java.util.List;

import com.zhy.Adapter.BrowseImageAdapter;
import com.dawnlightning.ucqa.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 浏览图片
 * @author Administrator
 *
 */
public class BrowseImageViewActivity extends BaseActivity implements OnClickListener,OnPageChangeListener {

	private ViewPager myViewPager;
	private List<View> allViewPagerView=new ArrayList<View>();
	private LayoutInflater inflater;
	private List<String> allImageUrl;
	private Button btnDelete;
	BrowseImageAdapter adapter;
	private int currentPosition;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.browse_image);
		
		initData();
		initViews();
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		btnDelete=(Button)findViewById(R.id.photo_bt_del);
		
		btnDelete.setOnClickListener(this);
	}
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		myViewPager=(ViewPager)findViewById(R.id.viewPager);
		inflater=LayoutInflater.from(BrowseImageViewActivity.this);
		List<String> allImageUrl=getIntent().getStringArrayListExtra("imageUrl");
		
		int psit=getIntent().getExtras().getInt("position");
		currentPosition=psit;
		this.allImageUrl=allImageUrl;
		for(int i=0;i<allImageUrl.size();i++){
			View v=inflater.inflate(R.layout.item_browse_image, null);
			allViewPagerView.add(v);
		}
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		adapter=new BrowseImageAdapter(BrowseImageViewActivity.this, allViewPagerView,allImageUrl);
		myViewPager.setAdapter(adapter);
		
		myViewPager.setOnPageChangeListener(this);
		myViewPager.setCurrentItem(currentPosition);
		
	}

	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		 if(arg0==btnDelete){
			
			if(allImageUrl.size()>0){
				
				Intent intent=new Intent();
				intent.setAction("delImage");
				intent.putExtra("position", currentPosition);
				sendBroadcast(intent);
				
				allImageUrl.remove(currentPosition);
				allViewPagerView.remove(currentPosition);
				adapter.notifyDataSetChanged();
				
			}else{
				finish();
			}
			
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		currentPosition=arg0;
	}
}
