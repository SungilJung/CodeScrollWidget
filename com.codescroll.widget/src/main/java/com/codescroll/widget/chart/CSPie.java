package com.codescroll.widget.chart;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

import com.codescroll.widget.CSWidget;

public class CSPie extends CSWidget {

	public static final int MAX_VALUE = 100;
	public static final int MIN_VALUE = 0;

	private final double ANGLE = 3.6;
	private final int PADDING = 2;

	private Map<CircleKind, Color> colorMap;
	
	private int goal;
	private int preValue;
	
	public CSPie(Composite paramComposite) {
		super(paramComposite);
		init();
	}

	private void init(){
		initColor();
		initFont();
	}

	private void initColor(){
		colorMap = new HashMap<CircleKind, Color>();
		colorMap.put(CircleKind.CALCULATION, getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
		colorMap.put(CircleKind.CURRENT_VALUE, getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
		colorMap.put(CircleKind.PREVIOUS_VALUE, getDisplay().getSystemColor(SWT.COLOR_GREEN));
	}

	private void initFont() {
		setFont(new Font(getDisplay(), "Arial", 12, SWT.BOLD));
	}
	
	@Override
	protected void drawWidget(GC gc) {
		int arc = (int) (goal * ANGLE);// 360/100 = 3.6
		Point point = getSize();

		gc.setAntialias(SWT.ON);

		gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		gc.fillArc(PADDING, PADDING, point.x - PADDING * 2, point.y - PADDING * 2, 0, (int) (MAX_VALUE * ANGLE));

		gc.setBackground(colorMap.get(CircleKind.CURRENT_VALUE));
		gc.fillArc(PADDING, PADDING, point.x - PADDING * 2, point.y - PADDING * 2, 90, -arc);

		int innerCirclePadding = PADDING * 4;
		gc.setBackground(getBackground());
		gc.fillArc(innerCirclePadding, innerCirclePadding, point.x - innerCirclePadding * 2,
				point.y - innerCirclePadding * 2, 0, (int) (MAX_VALUE * ANGLE));
		
		Point textPoint = getTextPoint(String.format("%d%%", goal));
		gc.drawText(String.format("%d%%", goal), (point.x / 2) - (textPoint.x / 2), (point.y / 2) - (textPoint.y / 2), true);
	}
	
	private Point getTextPoint(String str){
		GC tempGC = new GC(this);
		Point stringExtent = tempGC.stringExtent(str);
		tempGC.dispose();
		return stringExtent;
		
	}
	
	public void setColor(CircleKind kind, Color color) {
		colorMap.put(kind, color);
	}

	public void setValue(int value) {
		
		final int max = calValue(value);
		
		initValue();
		
		getDisplay().timerExec(10, new Runnable() {
			
			@Override
			public void run() {
				if (goal < max){
					goal++;
					redraw();
					
					getDisplay().timerExec(10, this);
				}
			}
		});
	}
	
	private void initValue(){
		preValue = goal;
		goal = MIN_VALUE;
	}
	
	private int calValue(int value){
		if (value >= MAX_VALUE) {
			return MAX_VALUE;
		} else if (value < MIN_VALUE) {
			return MIN_VALUE;
		} else {
			return value;
		}
	}

}


