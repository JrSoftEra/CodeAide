package com.jrsoftera.guiapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.util.Log;

public class GuiSample extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		Toolbar toolBar = (Toolbar) findViewById(R.id.main_toolbar);
		setSupportActionBar(toolBar);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
		toolBar, R.string.action_open_nav, R.string.action_close_nav);
		drawer.setDrawerListener(toggle);
		toggle.syncState();
		NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
		navView.setNavigationItemSelectedListener(this);
		//ImageView imgView = (ImageView) findViewById(R.id.imageView);
		RelativeLayout lnrLyout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);
		//lnrLyout.setDrawingCacheEnabled(true);
		lnrLyout.addView(new Ui(this) );
		//lnrLyout.buildDrawingCache();
		/*Bitmap btMap = Bitmap.createBitmap( lnrLyout.getDrawingCache() );
		if (btMap == null) {
			Log.e("onCreate()", "error lnrLyout/btMap empty/null ");
		}
		imgView.setImageBitmap(btMap);
		lnrLyout.destroyDrawingCache();*/
		
    }
	@Override
	public boolean onNavigationItemSelected(MenuItem p1)
	{
		return false;
	}
	public class Ui extends View
	{
		private Paint p = new Paint();
		private float[] faP;
		private static final int X = 0;
		private static final int Y = 1;
		private static final int SEGS = 50;
		private static final float SIZE = 500;

		private RectF mBigOval;
		private boolean mUseCenter;
		private static final float SWEEP_INC = 2;
		private static final float START_INC = 15;
		private Paint mFrmPnt;
		private float mSweep;
		private float mStart;

		private Paint mOvalPnt;

	private void buildPt(){
		final int ptCnt = (SEGS + 1) * 2;
		faP = new float[ptCnt * 2];
		float v = 0;
		final float delta = SIZE / SEGS ;
		for ( int i = 0; i <= SEGS; i++) {
			faP[i*4 + X] = SIZE - v;
			faP[i*4 + Y] = 0;
			faP[i*4 + X + 2] = 0;
			faP[i*4 + Y + 2] = v;
			v += delta;
			}
		}
	private void drawArcs(Canvas c, RectF mOval,
		boolean useCenter, Paint p) {
			c.drawRect(mOval, mFrmPnt);
			c.drawArc(mOval, mStart, mSweep, useCenter, p);
		}
		public Ui(Context instance){
			super(instance);
			buildPt();
			mUseCenter = true;
			mOvalPnt = new Paint();
			mOvalPnt.setAntiAlias(true);
			mOvalPnt.setColor(0x880000FF);
			mOvalPnt.setStrokeWidth(6);
			mOvalPnt.setStyle(Paint.Style.FILL);
			mBigOval = new RectF(40, 10, 280, 250);
			mFrmPnt = new Paint();
			mFrmPnt.setAntiAlias(true);
			mFrmPnt.setStyle(Paint.Style.STROKE);
			mFrmPnt.setStrokeWidth(0);
		}
		@Override
		protected void onDraw(Canvas canvas)
		{
			canvas.translate(10 , 10);
			canvas.drawColor(Color.CYAN);
			Paint cp = p;
			cp.setColor(0x88FF0000);
			cp.setStrokeWidth(0);
			canvas.drawLines(faP,cp);
			
			cp.setColor(Color.MAGENTA);
			cp.setStrokeWidth(6);
			canvas.drawPoints(faP,cp);
			
			drawArcs(canvas, mBigOval, mUseCenter, mOvalPnt);
			
			mSweep += SWEEP_INC;
			if (mSweep > 360) {
				mSweep -= 360;
				mStart += START_INC;
				if (mStart >= 360) {
					mStart -= 360;
				}
			}
			invalidate();
		}
	}
}
