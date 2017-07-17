package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

import com.codescroll.widget.CSWidget;
import com.codescroll.widget.util.SWTGraphicUtil;

public class CSPie extends CSWidget {

	private static final int SHADOW_ALPHA = 120;
	private static final int DEFAULT_ALPHA = 255;
	private static final int MIN_ALPHA = 0;

	public static final float MAX_STATE = 100.0f;
	public static final float MIN_STATE = 0.0f;

	private final String MAX_TEXT = "100.0%";
	private final int DEFAULT_THICKNESS = 16;

	private final double ANGLE = 3.6;
	private final int PADDING = 2;

	private Color outerColor;
	private Color outerShadowColor;
	private Color innerColor;
	private Color innerShadowColor;
	private Font preTextFont;

	private float state;
	private float preState;
	private int outerThickness;
	private int innerThickness;
	private int preStateAlpha;
	private boolean isCalculate;

	public CSPie(Composite paramComposite) {
		super(paramComposite);
		init();
	}

	private void init() {
		initState();
		initColor();
		initFont();
		setThickness(DEFAULT_THICKNESS);
		setPreStateAlpha(DEFAULT_ALPHA);
	}

	public void initState() {
		preState = MIN_STATE;
		state = MIN_STATE;
		isCalculate = false;
	}

	private void initColor() {
		outerColor = SWTGraphicUtil.getColorSafely(193, 141, 34);
		outerShadowColor = getShadowColor(outerColor);
		innerColor = SWTGraphicUtil.getColorSafely(255, 204, 153);
		innerShadowColor = getShadowColor(innerColor);
	}

	private Color getShadowColor(Color color) {

		int max = 255;
		int red = color.getRed() + 30;
		int green = color.getGreen() + 30;
		int blue = color.getBlue() + 30;

		red = Math.min(red, max);
		green = Math.min(green, max);
		blue = Math.min(blue, max);

		Color shadow = SWTGraphicUtil.getColorSafely(red, green, blue);
		return shadow;
	}

	private void initFont() {
		setFont(new Font(getDisplay(), "Arial", 12, SWT.BOLD));
	}

	private void setThickness(int thickness) {

		int outerThickness = thickness;
		if (outerThickness < 4) {
			outerThickness = 4;
		} else if (outerThickness % 2 != 0) {
			outerThickness -= 1;
		}

		this.outerThickness = outerThickness;
		this.innerThickness = outerThickness - 5 < 1 ? 1 : outerThickness - 5;
		
	}
	
	private void setPreStateAlpha(int alpha) {
		preStateAlpha = alpha;
	}
	

	@Override
	protected void drawWidget(GC gc) {
		gc.setAntialias(SWT.ON);

		int arc = (int) (state * ANGLE);// 360/100 = 3.6
		int preArc = (int) (preState * ANGLE);
		Point circlePoint = getCircleSize();

		int x = getSize().x;
		int y = getSize().y;

		int outerCircleX = (x - circlePoint.x);
		int outterCircleY = (y - circlePoint.y);
		int innerCircleX = outerCircleX + outerThickness;
		int innerCircleY = outterCircleY + outerThickness;
		int innerCircleWidth = circlePoint.x - outerThickness;
		int innerCircleHeight = circlePoint.y - outerThickness;

		String valueTxt = getStringOfValue();
		Point textPoint = getTextPoint(valueTxt, getFont());
		float value;
		String preValueTxt;
		if (preState > state) {
			value = preState - state;
			preValueTxt = "(-" + String.format("%.1f%%", value) + ")";
		} else {
			value = state - preState;
			preValueTxt = "(+" + String.format("%.1f%%", value) + ")";
		}
		Point preTextPoint = getTextPoint(preValueTxt, preTextFont);

		int shadowAlpha;
		int innerCircleAlpha ;
		int preTextAlpha;
		
		if(preStateAlpha == DEFAULT_ALPHA) {
			shadowAlpha = SHADOW_ALPHA;
			innerCircleAlpha = DEFAULT_ALPHA;
			preTextAlpha = DEFAULT_ALPHA;
		}else {
			shadowAlpha = preStateAlpha;
			innerCircleAlpha = preStateAlpha;
			preTextAlpha = preStateAlpha;
		}
		
		// draw outerCircle
		gc.setAlpha(SHADOW_ALPHA);
		gc.setBackground(outerShadowColor);
		gc.fillArc(outerCircleX / 2, outterCircleY / 2, circlePoint.x, circlePoint.y, 0, (int) (MAX_STATE * ANGLE));

		gc.setAlpha(DEFAULT_ALPHA);
		gc.setBackground(outerColor);
		gc.fillArc(outerCircleX / 2, outterCircleY / 2, circlePoint.x, circlePoint.y, 90, -arc);

		gc.setBackground(getBackground());
		gc.fillArc(innerCircleX / 2, innerCircleY / 2, innerCircleWidth, innerCircleHeight, 0,
				(int) (MAX_STATE * ANGLE));
		
		// draw innerCircle
		if (preState != 0) {
			gc.setAlpha(shadowAlpha);
			gc.setBackground(innerShadowColor);
			gc.fillArc(innerCircleX / 2, innerCircleY / 2, innerCircleWidth, innerCircleHeight, 0,
					(int) (MAX_STATE * ANGLE));

			gc.setAlpha(innerCircleAlpha);
			gc.setBackground(innerColor);
			gc.fillArc(innerCircleX / 2, innerCircleY / 2, innerCircleWidth, innerCircleHeight, 90, -preArc);
			
			gc.setAlpha(DEFAULT_ALPHA);
			gc.setBackground(getBackground());
			gc.fillArc((innerCircleX + innerThickness) / 2, (innerCircleY + innerThickness) / 2,
					innerCircleWidth - innerThickness, innerCircleHeight - innerThickness, 0,
					(int) (MAX_STATE * ANGLE));
		}

		
		// draw text
		
		gc.setForeground(getForeground());
		
		if (preState != 0) {
			gc.setAlpha(DEFAULT_ALPHA);
			gc.drawText(valueTxt, (x / 2) - (textPoint.x / 2), (y / 2) - (textPoint.y / 2), true);
			gc.setFont(preTextFont);
			gc.setAlpha(preTextAlpha);
			gc.drawText(preValueTxt, (x / 2) - (preTextPoint.x / 2), (y / 2) + (preTextPoint.y), true);
		} else {
			gc.setAlpha(DEFAULT_ALPHA);
			gc.drawText(valueTxt, (x / 2) - (textPoint.x / 2), (y / 2) - (textPoint.y / 2), true);
		}
	}

	private Point getCircleSize() {
		Point point = getSize();
		int x = point.x - PADDING;
		int y = point.y - PADDING;
		int value = Math.min(x, y);

		Point textPoint = getTextPoint(MAX_TEXT, getFont());
		int diameter = Math.max(textPoint.x, textPoint.y) + 30;

		value = Math.max(value, diameter);

		return new Point(value, value);
	}

	private String getStringOfValue() {
		if (state - (int) state == 0) {
			return String.format("%d%%", (int) state);
		} else {
			return String.format("%.1f%%", state);
		}
	}

	private Point getTextPoint(String str, Font font) {
		GC tempGC = new GC(this);
		tempGC.setAntialias(SWT.ON);
		tempGC.setFont(font);
		Point stringExtent = tempGC.stringExtent(str);
		tempGC.dispose();
		return stringExtent;
	}

	public void setOuterCircleColor(Color color) {
		outerColor = color;
		outerShadowColor = getShadowColor(color);
	}

	public void setInnerCircleColor(Color color) {
		innerColor = color;
		innerShadowColor = getShadowColor(color);
	}

	@Override
	public void setFont(Font font) {
		setPreTextFont(font);
		super.setFont(font);
	}

	private void setPreTextFont(Font font) {
		FontData fontData = font.getFontData()[0];
		int preFontHeight = fontData.getHeight() - 6;
		preFontHeight = preFontHeight < 8 ? 8 : preFontHeight;
		preTextFont = new Font(getDisplay(), fontData.getName(), preFontHeight, fontData.getStyle());
	}

	public void setValue(float value) {

		if (isCalculate) {
			return;
		}
		
		preStateAlpha = DEFAULT_ALPHA;
		final float preGoal = state;
		final float goal = getGoal(value);
		final int[] milliseconds = new int[] { 5 };
		initState();

		drawState(preGoal, goal, milliseconds);

		redraw();
	}

	private void drawState(final float preGoal, final float goal, final int[] milliseconds) {
		getDisplay().timerExec(milliseconds[0], new Runnable() {

			@Override
			public void run() {
				if (state != goal || preState != preGoal) {
					isCalculate = true;

					if (state != goal) {
						state++;
						if (state > goal) {
							state = goal;
						}
					}

					if (preState != preGoal) {
						preState += 2;
						if (preState > preGoal) {
							preState = preGoal;
						}
					}

					if (milliseconds[0] < 10) {
						milliseconds[0] += 1;
					}

					if (!CSPie.this.isDisposed()) {
						redraw();
						getDisplay().timerExec(milliseconds[0], this);
					}
				} else {
					drawTransparentCircle();
				}
			}

			private void drawTransparentCircle() {
				getDisplay().timerExec(100, new Runnable() {
					
					@Override
					public void run() {
						if(preStateAlpha != MIN_ALPHA && preState != 0) {
							preStateAlpha-=20;
							if(preStateAlpha < MIN_ALPHA) {
								preStateAlpha = MIN_ALPHA;
							}
							
							if (!CSPie.this.isDisposed()) {
								redraw();
								getDisplay().timerExec(100, this);
							}
							
						}else {
							isCalculate = false;
						}
					}
				});
			}
		});
	}

	private float getGoal(float value) {
		if (value >= MAX_STATE) {
			return MAX_STATE;
		} else if (value < MIN_STATE) {
			return MIN_STATE;
		} else {
			return value;
		}
	}

	public boolean isCalculate() {
		return isCalculate;
	}

}
