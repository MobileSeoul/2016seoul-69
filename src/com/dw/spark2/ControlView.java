package com.dw.spark2;

import android.R.color;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


@SuppressLint("ClickableViewAccessibility") 
public class ControlView extends View{
	private Handler mHandler;
	private int toggleMode;
	private int widthPixels;
	private Paint paint0, paint1, paint2, paint3, paint4, paint5;
	private int r1, r2, g1, g2, b1, b2, z1, z2; // 컨ㅌ롤뷰 동작의 좌표지정부
	private String rt1 = "0", rt2 = "255", gt1 = "0", gt2 = "255", bt1 = "0", bt2 = "255", zt1 = "x 100%"; // 컨트롤뷰에 보여줄 텍스트
	public ControlView(Context context, Handler mHandler, int widthPixels){
		super(context);
		this.mHandler = mHandler;
		this.widthPixels = widthPixels;
		Typeface mface = Typeface.createFromAsset(context.getAssets(),"dinultra.otf"); // 글씨체
		r1 = widthPixels/4;
		r2 = widthPixels/4*3;
		g1 = widthPixels/4;
		g2 = widthPixels/4*3;
		b1 = widthPixels/4;
		b2 = widthPixels/4*3;
		z1 = widthPixels/4;
		z2 = widthPixels/4*3;
		paint0 = new Paint();
		paint0.setColor(Color.WHITE);
		paint0.setTextAlign(Align.CENTER);
		paint0.setTypeface(mface);
		paint0.setTextSize((float) (widthPixels*0.05));
		paint1 = new Paint();
		paint1.setColor(Color.RED);
		paint1.setStrokeWidth(10);
		paint2 = new Paint();
		paint2.setColor(Color.GREEN);
		paint2.setStrokeWidth(10);
		paint3 = new Paint();
		paint3.setColor(Color.BLUE);
		paint3.setStrokeWidth(10);
		paint4 = new Paint();
		paint4.setColor(Color.WHITE);
		paint4.setStrokeWidth(10);
		paint4 = new Paint();
		paint4.setColor(Color.rgb(200, 200, 200));
		paint4.setStrokeWidth(10);
		paint5 = new Paint();
		paint5.setColor(Color.rgb(150, 150, 150));
		paint5.setStrokeWidth(10);
	}

	//draw Image Setting
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		
		canvas.drawLine((float)(widthPixels/4), widthPixels/9,(float)(widthPixels/4*3), widthPixels/9, paint1);
		canvas.drawText(rt1, widthPixels/8, widthPixels/9+25, paint0);
		canvas.drawText(rt2, widthPixels/8*7, widthPixels/9+25, paint0);
		canvas.drawCircle((r1),widthPixels/9,50,paint5);
		canvas.drawCircle((r1),widthPixels/9,30,paint1);
		canvas.drawCircle((r2),widthPixels/9,50,paint5);
		canvas.drawCircle((r2),widthPixels/9,30,paint1);

		canvas.drawLine((float)(widthPixels/4), widthPixels/9*2,(float)(widthPixels/4*3), widthPixels/9*2, paint2);
		canvas.drawText(gt1, widthPixels/8, widthPixels/9*2+25, paint0);
		canvas.drawText(gt2, widthPixels/8*7, widthPixels/9*2+25, paint0);
		canvas.drawCircle((g1),widthPixels/9*2,50,paint5);
		canvas.drawCircle((g1),widthPixels/9*2,30,paint2);
		canvas.drawCircle((g2),widthPixels/9*2,50,paint5);
		canvas.drawCircle((g2),widthPixels/9*2,30,paint2);

		canvas.drawLine((float)(widthPixels/4), widthPixels/9*3,(float)(widthPixels/4*3), widthPixels/9*3, paint3);
		canvas.drawText(bt1, widthPixels/8, widthPixels/9*3+25, paint0);
		canvas.drawText(bt2, widthPixels/8*7, widthPixels/9*3+25, paint0);
		canvas.drawCircle((b1),widthPixels/9*3,50,paint5);
		canvas.drawCircle((b1),widthPixels/9*3,30,paint3);
		canvas.drawCircle((b2),widthPixels/9*3,50,paint5);
		canvas.drawCircle((b2),widthPixels/9*3,30,paint3);

		canvas.drawLine((float)(widthPixels/4), widthPixels/9*4,(float)(widthPixels/4*3), widthPixels/9*4, paint4);
		canvas.drawText(zt1, widthPixels/8*7, widthPixels/9*4+25, paint0);
		canvas.drawCircle((z1),widthPixels/9*4,50,paint5);
		canvas.drawCircle((z1),widthPixels/9*4,30,paint4);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch (event.getActionMasked()) {

		case MotionEvent.ACTION_DOWN:
			if(event.getX()-45<r1&&event.getX()+45>r1&&event.getY()>-45+widthPixels/9&&event.getY()<45+widthPixels/9) {
				Log.e("TAG", "AA");
				toggleMode = 11;
			}else if(event.getX()-45<r2&&event.getX()+45>r2&&event.getY()>-45+widthPixels/9&&event.getY()<45+widthPixels/9) {
				Log.e("TAG", "AA");
				toggleMode = 12;
			}else if(event.getX()-45<g1&&event.getX()+45>g1&&event.getY()>-45+widthPixels/9*2&&event.getY()<45+widthPixels/9*2) {
				Log.e("TAG", "AA");
				toggleMode = 21;
			}else if(event.getX()-45<g2&&event.getX()+45>g2&&event.getY()>-45+widthPixels/9*2&&event.getY()<45+widthPixels/9*2) {
				Log.e("TAG", "AA");
				toggleMode = 22;
			}else if(event.getX()-45<b1&&event.getX()+45>b1&&event.getY()>-45+widthPixels/9*3&&event.getY()<45+widthPixels/9*3) {
				Log.e("TAG", "AA");
				toggleMode = 31;
			}else if(event.getX()-45<b2&&event.getX()+45>b2&&event.getY()>-45+widthPixels/9*3&&event.getY()<45+widthPixels/9*3) {
				Log.e("TAG", "AA");
				toggleMode = 32;
			}else if(event.getX()-45<z1&&event.getX()+45>z1&&event.getY()>-45+widthPixels/9*4&&event.getY()<45+widthPixels/9*4) {
				Log.e("TAG", "AA");
				toggleMode = 41;
			}
			//if(event.getX()-25<widthPixels/4&&event.getX()+25>widthPixels/4) toggleMode = 12;
			//if(event.getX()-25<widthPixels/4&&event.getX()+25>widthPixels/4) toggleMode = 21;

			//mHandler.obtainMessage(1).sendToTarget();
			break;

		case MotionEvent.ACTION_MOVE:
			switch (toggleMode) {
			case 11:
				if(r1>=widthPixels/4 && r1<=widthPixels/4*3) r1 = (int)event.getX();
				if(r1<widthPixels/4) r1 = widthPixels/4;
				else if(r1>widthPixels/4*3) r1 = widthPixels/4*3;
				rt1 = String.valueOf(255*(r1-widthPixels/4)/(widthPixels/4*3-widthPixels/4));
				mHandler.obtainMessage(11,r1-widthPixels/4,widthPixels/4*3-widthPixels/4).sendToTarget();
				break;
			case 12:
				if(r2>=widthPixels/4 && r2<=widthPixels/4*3) r2 = (int)event.getX();
				if(r2<widthPixels/4) r2 = widthPixels/4;
				else if(r2>widthPixels/4*3) r2 = widthPixels/4*3;
				rt2 = String.valueOf(255*(r2-widthPixels/4)/(widthPixels/4*3-widthPixels/4));
				mHandler.obtainMessage(12,widthPixels/4*3-r2,widthPixels/4*3-widthPixels/4).sendToTarget();
				break;
			case 21:
				if(g1>=widthPixels/4 && g1<=widthPixels/4*3) g1 = (int)event.getX();
				if(g1<widthPixels/4) g1 = widthPixels/4;
				else if(g1>widthPixels/4*3) g1 = widthPixels/4*3;
				gt1 = String.valueOf(255*(g1-widthPixels/4)/(widthPixels/4*3-widthPixels/4));
				mHandler.obtainMessage(21,g1-widthPixels/4,widthPixels/4*3-widthPixels/4).sendToTarget();
				break;
			case 22:
				if(g2>=widthPixels/4 && g2<=widthPixels/4*3) g2 = (int)event.getX();
				if(g2<widthPixels/4) g2 = widthPixels/4;
				else if(g2>widthPixels/4*3) g2 = widthPixels/4*3;
				gt2 = String.valueOf(255*(g2-widthPixels/4)/(widthPixels/4*3-widthPixels/4));
				mHandler.obtainMessage(22,widthPixels/4*3-g2,widthPixels/4*3-widthPixels/4).sendToTarget();
				break;
			case 31:
				if(b1>=widthPixels/4 && b1<=widthPixels/4*3) b1 = (int)event.getX();
				if(b1<widthPixels/4) b1 = widthPixels/4;
				else if(b1>widthPixels/4*3) b1 = widthPixels/4*3;
				bt1 = String.valueOf(255*(b1-widthPixels/4)/(widthPixels/4*3-widthPixels/4));
				mHandler.obtainMessage(31,b1-widthPixels/4,widthPixels/4*3-widthPixels/4).sendToTarget();
				break;
			case 32:
				if(b2>=widthPixels/4 && b2<=widthPixels/4*3) b2 = (int)event.getX();
				if(b2<widthPixels/4) b2 = widthPixels/4;
				else if(b2>widthPixels/4*3) b2 = widthPixels/4*3;
				bt2 = String.valueOf(255*(b2-widthPixels/4)/(widthPixels/4*3-widthPixels/4));
				mHandler.obtainMessage(32,widthPixels/4*3-b2,widthPixels/4*3-widthPixels/4).sendToTarget();
				break;
			case 41:
				if(z1>=widthPixels/4 && z1<=widthPixels/4*3) z1 = (int)event.getX();
				if(z1<widthPixels/4) z1 = widthPixels/4;
				else if(z1>widthPixels/4*3) z1 = widthPixels/4*3;
				//rt1 = String.valueOf(255*r1/(widthPixels/4*3-widthPixels/4));
				zt1 = "x "+String.valueOf(200*(z1-widthPixels/4)/(widthPixels/4*3-widthPixels/4)+100)+"%";
				mHandler.obtainMessage(41,z1-widthPixels/4,widthPixels/4*3-widthPixels/4).sendToTarget();
				break;
				}
			
			
			break;

		case MotionEvent.ACTION_UP:
			if(r1<=widthPixels/4) r1 = widthPixels/4;
			else if(r1>=widthPixels/4*3) r1 = widthPixels/4*3;
			if(r2<=widthPixels/4) r2 = widthPixels/4;
			else if(r2>=widthPixels/4*3) r2 = widthPixels/4*3;
			if(g1<=widthPixels/4) g1 = widthPixels/4;
			else if(g1>=widthPixels/4*3) g1 = widthPixels/4*3;
			if(g2<=widthPixels/4) g2 = widthPixels/4;
			else if(g2>=widthPixels/4*3) g2 = widthPixels/4*3;
			if(b1<=widthPixels/4) b1 = widthPixels/4;
			else if(b1>=widthPixels/4*3) b1 = widthPixels/4*3;
			if(b2<=widthPixels/4) b2 = widthPixels/4;
			else if(b2>=widthPixels/4*3) b2 = widthPixels/4*3;
			if(z1<=widthPixels/4) z1 = widthPixels/4;
			else if(z1>=widthPixels/4*3) z1 = widthPixels/4*3;
			toggleMode = 0;
			mHandler.obtainMessage(2).sendToTarget();
			break;
		}
		invalidate();
		return true;
	}
}

