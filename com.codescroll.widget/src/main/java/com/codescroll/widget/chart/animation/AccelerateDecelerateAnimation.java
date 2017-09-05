package com.codescroll.widget.chart.animation;

public class AccelerateDecelerateAnimation implements IAnimation{

	@Override
	public float getValue(float value) {
		return (float)(Math.cos((value + 1) * Math.PI) / 2.0f) + 0.5f ;
	}
}
