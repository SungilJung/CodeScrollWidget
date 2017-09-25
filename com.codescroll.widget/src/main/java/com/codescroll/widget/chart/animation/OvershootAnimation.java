package com.codescroll.widget.chart.animation;

public class OvershootAnimation implements IAnimation {

	private final float mTension;

	public OvershootAnimation() {
		mTension = 2.0f;
	}
	
	  /**
     * @param tension Amount of overshoot. When tension equals 0.0f, there is
     *                no overshoot and the interpolator becomes a simple
     *                deceleration animation.
     */
    public OvershootAnimation(float tension) {
        mTension = tension;
    }

	@Override
	public float getValue(float value) {
		return (float) ((mTension + 1) * Math.pow((value - 1), 3) + mTension * Math.pow((value - 1), 2) + 1.0f);
	}

}
