package com.codescroll.widget.chart.animation;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.Before;
import org.junit.Test;

public class DecelerateAnimationTest {

private IAnimation animation = new DecelerateAnimation(1.5f);
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void test() {
		
		BigDecimal value = new BigDecimal(0.0);
		BigDecimal max = new BigDecimal(1.0);
		
		while(value.compareTo(max) != 0) {
			value = value.add(new BigDecimal(0.01, new MathContext(1)));
			System.out.println("origin: " + value);
			System.out.println("value: " + animation.getValue(value.floatValue()) * 100);
		}
	
	}


}
