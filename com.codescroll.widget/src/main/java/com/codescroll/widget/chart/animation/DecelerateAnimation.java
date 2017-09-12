package com.codescroll.widget.chart.animation;

public class DecelerateAnimation implements IAnimation {

    private float mFactor;
    
	public DecelerateAnimation(float factor) {
        mFactor = factor;
    }

	public DecelerateAnimation() {
		this(1.0f);
	}
	
	@Override
	public float getValue(float value) {
		if (mFactor == 1.0f) {
			return (float) (1.0f - (1.0f - value) * (1.0f - value));
		} else {
			return (float) (1.0f - Math.pow((1.0f - value), 2 * value));
		}

	}

}
