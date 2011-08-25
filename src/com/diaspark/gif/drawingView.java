package com.diaspark.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class drawingView extends View implements OnTouchListener {

	static int x, y, r = 255, g = 255, b = 255;

	final static int radius = 30;

	Paint paint; // using this ,we can draw on canvas
	Bitmap kangoo;

	public drawingView(Context context) {
		super(context);
		paint = new Paint();
		
		paint.setAntiAlias(true); // for smooth rendering
		paint.setARGB(255, r, g, b); // setting the paint color
		setFocusable(true);
		this.setOnTouchListener(this);

		kangoo = BitmapFactory.decodeResource(getResources(), R.drawable.beach);

	}

	public void onDraw(Canvas canvas) {
		paint.setARGB(255, r, g, b);
		// canvas.drawCircle(x, y, radius, paint);
		// canvas.drawText("hello", x, y, paint);
		canvas.drawBitmap(kangoo, x, y, null);
	}

	public void randColor() {

		r = (int) (Math.random() * 255);

		g = (int) (Math.random() * 255);

		b = (int) (Math.random() * 255);

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			// TODO Auto-generated method stub
			x = (int) event.getX() - (radius / 2); // some math logic to plot
													// the
													// circle in exact touch
													// place
			y = (int) event.getY() - (radius / 2);
			randColor(); // calls this method to generate a color before drawing
			invalidate(); // calls onDraw method
		}
		return true;
	}

}
