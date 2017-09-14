package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.codescroll.widget.CSWidget;
import com.codescroll.widget.chart.animation.AccelerateDecelerateAnimation;
import com.codescroll.widget.chart.animation.DecelerateAnimation;
import com.codescroll.widget.chart.animation.IAnimation;
import com.codescroll.widget.util.SWTGraphicUtil;

public class CSPie extends CSWidget {

	private static final String VALUE_FORMAT = "%.1f";
	private static final String VALUE_PERCENT_FORMAT = VALUE_FORMAT + "%%";
	private static final int SHADOW_ALPHA = 120;
	private static final int DEFAULT_ALPHA = 255;
	private static final int MIN_ALPHA = 0;

	public static final float MAX_STATE = 100.0f;
	public static final float MIN_STATE = 0.0f;

	private static final String MAX_TEXT = "100.0%";
	private static final int DEFAULT_THICKNESS = 16;

	private static final double ANGLE = 3.6;
	private static final int PADDING = 2;

	private Color outerColor;
	private Color outerShadowColor;
	private Color innerColor;
	private Color innerShadowColor;
	private Color preTextColor;
	private Font preTextFont;

	private float state;
	private int outerThickness;
	private int innerThickness;
	private int preStateAlpha;
	private float preGoal;
	private float goal;

	private int effectShadowThickness;
	private int effectThickness;
	private float effectFactor;
	private IAnimation animation;
	private Thread csPieThread;
	private Color effectCircleColor;
	private Color effectShadowCircleColor;
	private int effectFinish;

	public CSPie(Composite paramComposite) {
		super(paramComposite);
		init();
	}

	private void init() {
		initState();
		initColor();
		initFont();
		initEffectThickness();
		setThickness(DEFAULT_THICKNESS);
		setPreStateAlpha(DEFAULT_ALPHA);
		setAnimation(new AccelerateDecelerateAnimation());
	}

	private void initState() {
		state = MIN_STATE;
	}

	public void initValue() {
		initState();
		initEffectThickness();
		preGoal = MIN_STATE;
		goal = MIN_STATE;

	}

	private void initColor() {
		outerColor = SWTGraphicUtil.getColorSafely(132, 172, 75);
		outerShadowColor = SWTGraphicUtil.getColorSafely(203, 203, 203);
		innerColor = outerColor;
		innerShadowColor = SWTGraphicUtil.getColorSafely(250, 240, 190);
		preTextColor = SWTGraphicUtil.getColorSafely(33, 33, 33);
		effectShadowCircleColor = SWTGraphicUtil.getColorSafely(248, 222, 126);
		effectCircleColor = SWTGraphicUtil.getColorSafely(99, 176, 0);
	}

	@Deprecated
	private Color getShadowColor(Color color) {

		int max = 255;
		int red = color.getRed() + 50;
		int green = color.getGreen() + 50;
		int blue = color.getBlue() + 50;

		red = Math.min(red, max);
		green = Math.min(green, max);
		blue = Math.min(blue, max);

		Color shadow = SWTGraphicUtil.getColorSafely(red, green, blue);
		return shadow;
	}

	private void initFont() {
		setFont(new Font(Display.getDefault(), "Arial", 12, SWT.BOLD));
	}

	private void initEffectThickness() {
		effectShadowThickness = Integer.MIN_VALUE;
		effectThickness = Integer.MIN_VALUE;
	}

	public void setThickness(int thickness) {

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

	public void setAnimation(IAnimation animation) {
		this.animation = animation;
	}

	@Override
	protected void drawWidget(GC gc) {
		gc.setAntialias(SWT.ON);

		int arc = (int) (state * ANGLE);// 360/100 = 3.6
		int preArc = (int) (preGoal * ANGLE);
		Point circlePoint = getCircleSize();

		int x = getSize().x;
		int y = getSize().y;

		int outerCircleX = (x - circlePoint.x);
		int outerCircleY = (y - circlePoint.y);
		int innerCircleX = outerCircleX + outerThickness;
		int innerCircleY = outerCircleY + outerThickness;
		int innerCircleWidth = circlePoint.x - outerThickness;
		int innerCircleHeight = circlePoint.y - outerThickness;

		int effectShadowCircleX = outerCircleX + effectShadowThickness;
		int effectShadowCircleY = outerCircleY + effectShadowThickness;
		int effectShadowCircleWidth = circlePoint.x - effectShadowThickness;
		int effectShadowCircleHeight = circlePoint.y - effectShadowThickness;

		int effectCircleX = outerCircleX + effectThickness;
		int effectCircleY = outerCircleY + effectThickness;
		int effectCircleWidth = circlePoint.x - effectThickness;
		int effectCircleHeight = circlePoint.y - effectThickness;

		effectShadowThickness = effectShadowThickness == Integer.MIN_VALUE ? circlePoint.x : effectShadowThickness;
		effectThickness = effectThickness == Integer.MIN_VALUE ? circlePoint.x : effectThickness;
		effectFinish = DEFAULT_THICKNESS / 2;
		effectFactor = circlePoint.x;

		String valueTxt = state != goal ? getStringOfValue(preGoal) : getStringOfValue(goal);
		Point textPoint = getTextPoint(valueTxt, getFont());
		String preValueTxt;

		preValueTxt = getPreValueText();
		Point preTextPoint = getTextPoint(preValueTxt, preTextFont);

		int shadowAlpha;
		int innerCircleAlpha;
		int preTextAlpha;

		if (preStateAlpha == DEFAULT_ALPHA) {
			shadowAlpha = SHADOW_ALPHA;
			innerCircleAlpha = DEFAULT_ALPHA;
			preTextAlpha = DEFAULT_ALPHA;
		} else {
			shadowAlpha = preStateAlpha;
			innerCircleAlpha = preStateAlpha;
			preTextAlpha = preStateAlpha;
		}

		// draw outerCircle

//		gc.setAlpha(shadowAlpha);
		gc.setBackground(outerShadowColor);
		gc.fillArc(outerCircleX / 2, outerCircleY / 2, circlePoint.x, circlePoint.y, 0, (int) (MAX_STATE * ANGLE));

		gc.setAlpha(DEFAULT_ALPHA);
		gc.setBackground(outerColor);
		gc.fillArc(outerCircleX / 2, outerCircleY / 2, circlePoint.x, circlePoint.y, 90, -arc);

		gc.setBackground(getBackground());
		gc.fillArc(innerCircleX / 2, innerCircleY / 2, innerCircleWidth, innerCircleHeight, 0,
				(int) (MAX_STATE * ANGLE));

		// draw innerCircle
		if (isDrawPreValue()) {

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

		if (state != MAX_STATE) {

			if (isDrawPreValue() && goal == state) {
				gc.setAlpha(DEFAULT_ALPHA);
				gc.drawText(valueTxt, (x / 2) - (textPoint.x / 2), (y / 2) - (textPoint.y / 2), true);
				gc.setFont(preTextFont);
				gc.setAlpha(preTextAlpha);
				gc.setForeground(preTextColor);
				gc.drawText(preValueTxt, (x / 2) - (preTextPoint.x / 2), (y / 2) + (preTextPoint.y), true);
			} else {
				gc.setAlpha(DEFAULT_ALPHA);
				gc.drawText(valueTxt, (x / 2) - (textPoint.x / 2), (y / 2) - (textPoint.y / 2), true);
			}

			// show Effect
		} else {

			if (effectShadowThickness >= effectFinish || effectThickness >= effectFinish) {

				if (effectThickness > effectFinish) {
					gc.setBackground(effectShadowCircleColor);
					gc.fillArc(effectShadowCircleX / 2, effectShadowCircleY / 2, effectShadowCircleWidth,
							effectShadowCircleHeight, 0, (int) (MAX_STATE * ANGLE));
				}
				gc.setBackground(effectCircleColor);
				gc.fillArc(effectCircleX / 2, effectCircleY / 2, effectCircleWidth, effectCircleHeight, 0,
						(int) (MAX_STATE * ANGLE));

				if (effectShadowThickness == effectFinish && effectThickness == effectFinish) {

					gc.setForeground(SWTGraphicUtil.getColorSafely(0, 0, 0));
					FontData fd = getFont().getFontData()[0];
					fd.setHeight(fd.getHeight() + 2);
					Font effectFont = new Font(Display.getDefault(), fd);
					gc.setFont(effectFont);
					Point effectTextPoint = getTextPoint(valueTxt, gc.getFont());
					gc.drawText(valueTxt, (x / 2) - (effectTextPoint.x / 2), (y / 2) - (effectTextPoint.y / 2), true);
					effectFont.dispose();
				}
			}
		}
	}

	private Point getCircleSize() {
		Point point = getSize();
		int x = point.x - PADDING;
		int y = point.y - PADDING;
		int value = Math.min(x, y);

		Point textPoint = getTextPoint(MAX_TEXT, getFont());
		int diameter = Math.max(textPoint.x, textPoint.y) + 10;

		value = Math.max(value, diameter);

		return new Point(value, value);
	}

	private String getStringOfValue(float value) {
		if (value == 0 || value == 100) {
			return String.format("%d%%", (int) value);
		} else {
			return String.format(VALUE_PERCENT_FORMAT, value);
		}
	}

	private String getPreValueText() {
		String preValueTxt;
		if (preGoal > goal) {
			preValueTxt = "(" + String.format(VALUE_PERCENT_FORMAT, goal - preGoal) + ")";
		} else {
			preValueTxt = "(+" + String.format(VALUE_PERCENT_FORMAT, goal - preGoal) + ")";
		}
		return preValueTxt;
	}

	private Point getTextPoint(String str, Font font) {
		GC tempGC = new GC(this);
		tempGC.setAntialias(SWT.ON);
		tempGC.setFont(font);
		Point stringExtent = tempGC.stringExtent(str);
		tempGC.dispose();
		return stringExtent;
	}

	private boolean isDrawPreValue() {
		return preGoal != 0 && goal != preGoal && state != MAX_STATE;
	}

	public void setOuterCircleColor(Color color) {
		outerColor = color;
	}

	public void setOuterShadowCircleColor(Color color) {
		outerShadowColor = color;
	}

	public void setInnerCircleColor(Color color) {
		innerColor = color;
	}

	public void setInnerShadowCircleColor(Color color) {
		innerShadowColor = color;
	}

	public void setPreTextColor(Color color) {
		preTextColor = color;
	}

	@Override
	public void setFont(Font font) {
		setPreTextFont(font);
		super.setFont(font);
	}

	public void setPreTextFont(Font font) {
		FontData fontData = font.getFontData()[0];
		int preFontHeight = fontData.getHeight() - 6;
		preFontHeight = preFontHeight < 8 ? 8 : preFontHeight;
		preTextFont = new Font(Display.getDefault(), fontData.getName(), preFontHeight, fontData.getStyle());
	}

	public void setValue(float value) {

		preStateAlpha = DEFAULT_ALPHA;
		preGoal = goal;
		goal = getGoal(value);

		initState();
		initThread();

		if (preGoal != goal) {
			initEffectThickness();
		}

		if (preGoal == goal) {
			state = goal;
			preGoal = goal;
		}

		csPieThread.start();
		redraw();
	}

	private float getGoal(float value) {

		float tempValue;

		if (value >= MAX_STATE) {
			tempValue = MAX_STATE;
		} else if (value < MIN_STATE) {
			tempValue = MIN_STATE;
		} else {
			tempValue = value;
		}
		return Float.parseFloat(String.format(VALUE_FORMAT, tempValue));
	}

	private void initThread() {

		if (csPieThread != null) {
			csPieThread.interrupt();
		}
		csPieThread = new Thread(new CSPieRunnable());
	}

	@Override
	public void setForeground(Color color) {
		super.setForeground(color);
		preTextColor = color;
	}

	class CSPieRunnable implements Runnable {

		private float stateValue = 0.0f;
		private float effectShadowAnimationValue = 0.0f;
		private float effectAnimationValue = 0.0f;

		@Override
		public void run() {

			while (state != goal) {

				if (state != goal) {

					stateValue += 0.01f;
					state = getState(goal, stateValue);
					if (state > goal) {
						state = goal;
					}
				}

				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					return;
				}

				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {

						if (!isDisposed()) {
							redraw();
						}
					}
				});
			}

			drawEffectCircle();
			drawTransparentCircle();

		}

		private float getState(float goal, float stateValue) {

			return animation.getValue(stateValue) * goal;
		}

		private void drawEffectCircle() {

			IAnimation effectShadowAnimation = new DecelerateAnimation(0.9f);
			IAnimation effectAnimation = new DecelerateAnimation(1.3f);

			while (preGoal != goal && (effectShadowThickness != effectFinish || effectThickness != effectFinish)) {
				if (effectShadowThickness > effectFinish) {
					effectShadowAnimationValue += 0.01f;

					effectShadowThickness = (int) (effectFactor
							- effectShadowAnimation.getValue(effectShadowAnimationValue) * effectFactor);
				}

				if (effectShadowAnimationValue > 0.2) {
					effectAnimationValue += 0.01f;
					effectThickness = (int) (effectFactor
							- effectAnimation.getValue(effectAnimationValue) * effectFactor);

					if (effectThickness < effectFinish) {
						effectThickness = effectFinish;
						effectShadowThickness = effectFinish;
					}
				}

				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					return;

				}
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						if (!isDisposed()) {
							redraw();
						}
					}
				});
			}
		}

		private void drawTransparentCircle() {
			while (preStateAlpha != MIN_ALPHA) {
				preStateAlpha--;
				if (preStateAlpha < MIN_ALPHA) {
					preStateAlpha = MIN_ALPHA;
				}

				try {
					Thread.sleep(13);
				} catch (InterruptedException e) {
					return;

				}
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						if (!isDisposed()) {
							redraw();
						}
					}
				});
			}
		}
	}

}
