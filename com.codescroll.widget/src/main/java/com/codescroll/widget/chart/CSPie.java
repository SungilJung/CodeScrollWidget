package com.codescroll.widget.chart;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.codescroll.widget.CSWidget;

public class CSPie extends CSWidget {

	public static final float MAX_VALUE = 100.0f;
	public static final float MIN_VALUE = 0.0f;

	private final double ANGLE = 3.6;
	private final int PADDING = 4;
	private Map<CircleKind, Color> colorMap;

	private float goal;
	private float preGoal;
	private int thickness;

	public CSPie(Composite paramComposite) {
		super(paramComposite);
		init();
	}

	private void init() {
		initColor();
		initFont();
		setThickness(16);
	}

	private void initColor() {
		colorMap = new HashMap<CircleKind, Color>();
		colorMap.put(CircleKind.CALCULATION, getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
		colorMap.put(CircleKind.CURRENT_VALUE, getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
		colorMap.put(CircleKind.PREVIOUS_VALUE, getDisplay().getSystemColor(SWT.COLOR_YELLOW));
	}

	private void initFont() {
		setFont(new Font(getDisplay(), "Arial", 12, SWT.BOLD));
	}

	@Override
	protected void drawWidget(GC gc) {
		int arc = (int) (goal * ANGLE);// 360/100 = 3.6
		int preArc = (int) (preGoal * ANGLE);
		Point point = getCircleSize();
		Canvas canvas = this;

		int x = canvas.getBounds().width;
		int y = canvas.getBounds().height;

		gc.setAntialias(SWT.ON);

		gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		gc.fillArc((x - point.x) / 2, (y - point.y) / 2, point.x, point.y, 0, (int) (MAX_VALUE * ANGLE));

		gc.setBackground(colorMap.get(CircleKind.PREVIOUS_VALUE));
		gc.fillArc((x - point.x) / 2, (y - point.y) / 2, point.x, point.y, 90, -preArc);

		gc.setBackground(colorMap.get(CircleKind.CURRENT_VALUE));
		gc.fillArc((x - point.x) / 2, (y - point.y) / 2, point.x, point.y, 90, -arc);

		gc.setBackground(getBackground());
		gc.fillArc((x - point.x + thickness) / 2, (y - point.y + thickness) / 2, point.x - thickness,
				point.y - thickness, 0, (int) (MAX_VALUE * ANGLE));

		gc.setBackground(getForeground());
		
		String valueTxt = getStringOfValue();
		Point textPoint = getTextPoint(valueTxt);
		gc.drawText(valueTxt, (x / 2) - (textPoint.x / 2), (y / 2) - (textPoint.y / 2), true);

	}

	private Point getCircleSize() {
		Point point = getSize();
		int x = point.x - PADDING;
		int y = point.y - PADDING;
		int value = Math.min(x, y);

		return new Point(value, value);
	}
	
	private String getStringOfValue(){
		if(goal - (int)goal == 0){
			return String.format("%d%%", (int)goal);
		}else{
			return String.format("%.2f%%", goal);
		}
	}

	private Point getTextPoint(String str) {
		GC tempGC = new GC(this);
		Point stringExtent = tempGC.stringExtent(str);
		tempGC.dispose();
		return stringExtent;

	}

	public void setColor(CircleKind kind, Color color) {
		colorMap.put(kind, color);
	}

	public void setThickness(int thickness) {

		if (thickness < 2) {
			thickness = 2;
		} else if (thickness % 2 != 0) {
			thickness -= 1;
		}

		this.thickness = thickness;
	}

	public void setValue(float value) {

		final float max = calValue(value);

		saveValue();

		getDisplay().timerExec(10, new Runnable() {

			@Override
			public void run() {
				if (goal < max) {
					goal++;
					if(goal > max){
						goal = max;
					}
					
					redraw();
					getDisplay().timerExec(10, this);
				}
			}
		});
		redraw();
	}

	private void saveValue() {
		preGoal = goal;
		goal = MIN_VALUE;
	}

	private float calValue(float value) {
		if (value >= MAX_VALUE) {
			return MAX_VALUE;
		} else if (value < MIN_VALUE) {
			return MIN_VALUE;
		} else {
			return value;
		}
	}

	public void initValue() {
		preGoal = MIN_VALUE;
		goal = MIN_VALUE;
	}

}
