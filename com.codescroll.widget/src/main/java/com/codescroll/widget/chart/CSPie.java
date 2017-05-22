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

	public static final int MAX_VALUE = 100;
	public static final int MIN_VALUE = 0;

	private final double ANGLE = 3.6;
	private final int PADDING = 4;
	private Map<CircleKind, Color> colorMap;

	private int goal;
	private int preGoal;
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
		Point textPoint = getTextPoint(String.format("%d%%", goal));
		gc.drawText(String.format("%d%%", goal), (x / 2) - (textPoint.x / 2), (y / 2) - (textPoint.y / 2), true);

	}

	private Point getCircleSize() {
		Point point = getSize();
		int x = point.x - PADDING;
		int y = point.y - PADDING;

		if (x < y) {
			return new Point(x, x); 
		} else {
			return new Point(y, y);
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

	public void setValue(int value) {

		final int max = calValue(value);

		saveValue();

		getDisplay().timerExec(10, new Runnable() {

			@Override
			public void run() {
				if (goal < max) {
					goal++;
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

	private int calValue(int value) {
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
