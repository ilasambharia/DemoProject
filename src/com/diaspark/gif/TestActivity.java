package com.diaspark.gif;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

public class TestActivity extends Activity implements OnClickListener, AnimationListener {
	private ImageView image;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagevideo);
        
		image = (ImageView) findViewById(R.id.image);
        ((Button) findViewById(R.id.button)).setOnClickListener(this);
    }

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.button) {
			Animation animation;
			if (image.getVisibility() == View.VISIBLE) {
				animation = new BlinkAnimation(3, true);
				animation.setInterpolator(new DecelerateInterpolator());
			} else {
				animation = new BlinkAnimation(3, false);
				animation.setInterpolator(new AccelerateInterpolator());
			}
			animation.setDuration(1000L);
			animation.setAnimationListener(this);
			
			image.startAnimation(animation);
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		image.setVisibility(image.getVisibility() == View.VISIBLE ? 
				View.INVISIBLE : View.VISIBLE);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {}

	@Override
	public void onAnimationStart(Animation animation) {}
}