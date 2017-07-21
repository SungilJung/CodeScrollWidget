package com.codescroll.widget.button;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.codescroll.widget.util.SWTGraphicUtil;

public class ToggleButton extends CSAbstractButton {

	public enum Position {
		LEFT, RIGHT
	}

	private final int TXT_ARR_SIZE = 2;
	private final int HORIZONTAL_SPACING = 5;
	private final int MARGIN = 5;
	private final int BORDER_MARGIN = 3;
	private final int TOGGLE_BORDER_MARGIN = 3;
	private final int ANGLE = 360;

	private Color borderColor;
	private Color unSelectedColor;
	private Color selectionColor;
	private Color selectionBordoerColor;

	private String[] toggleTexts = new String[TXT_ARR_SIZE];
	private int selectionIndex;
	private int minHeight;
	private int minWidth;
	

	public ToggleButton(Composite parent) {
		super(parent);
		init();
	}

	private void init() {
		this.selectionIndex = 0;
		toggleTexts = new String[] { "", "" };
		borderColor = SWTGraphicUtil.getColorSafely(231, 230, 230);
		unSelectedColor = SWTGraphicUtil.getColorSafely(208, 206, 206);
	}

	@Override
	protected void drawWidget(GC gc) {

		gc.setAntialias(SWT.ON);
		gc.setTextAntialias(SWT.ON);

		int width = Math.max(getSize().x, minWidth);
		int height = Math.max(getSize().y, minHeight);

		String leftText = toggleTexts[Position.LEFT.ordinal()];
		String rightText = toggleTexts[Position.RIGHT.ordinal()];

		Point leftExtent = gc.textExtent(leftText);
		Point rightExtent = gc.textExtent(rightText);

		int borderRectangleX = leftExtent.x + HORIZONTAL_SPACING;
		int borderRectangleWidth = width - (leftExtent.x + rightExtent.x + (HORIZONTAL_SPACING) * 2);
		borderRectangleWidth = Math.max(borderRectangleWidth, (height - (MARGIN + BORDER_MARGIN)));

		int rightTextX = leftExtent.x + borderRectangleWidth + HORIZONTAL_SPACING * 2;
		rightTextX = Math.max(rightTextX, width - rightExtent.x);

		int circleDiameter = height - (MARGIN);
		int circleX = getCirclePointX(borderRectangleX, borderRectangleWidth, circleDiameter);

		gc.setForeground(getSelectionFontColor(Position.LEFT.ordinal()));
		gc.drawText(leftText, 0, (height) / 2 - leftExtent.y / 2, true);

		gc.setBackground(borderColor);
		gc.fillRoundRectangle(borderRectangleX, MARGIN, borderRectangleWidth, height - (MARGIN * 2),
				height - (MARGIN) * 2, height - (MARGIN) * 2);

		gc.setBackground(unSelectedColor);
		gc.fillRoundRectangle(borderRectangleX + BORDER_MARGIN, MARGIN + BORDER_MARGIN,
				borderRectangleWidth - BORDER_MARGIN * 2, height - (MARGIN + BORDER_MARGIN) * 2,
				height - (MARGIN + BORDER_MARGIN) * 2, height - (MARGIN + BORDER_MARGIN) * 2);

		drawCircle(gc, circleX, circleDiameter);

		gc.setForeground(getSelectionFontColor(Position.RIGHT.ordinal()));
		gc.drawText(rightText, rightTextX, (height) / 2 - rightExtent.y / 2 , true);

	}

	private int getCirclePointX(int borderRectangleX, int borderRectangleWidth, int circleDiameter) {
		int circleX;
		if (selectionIndex == Position.LEFT.ordinal()) {
			circleX = borderRectangleX - BORDER_MARGIN;
		} else {
			circleX = borderRectangleX + borderRectangleWidth - circleDiameter + BORDER_MARGIN;
		}
		return circleX;
	}

	private Color getSelectionFontColor(int index) {
		Color color = getForeground();
		if (selectionIndex != index) {
			color = unSelectedColor;
		}
		return color;
	}

	private void drawCircle(GC gc, int x, int y) {

		int borderY = MARGIN - BORDER_MARGIN;

		gc.setBackground(selectionBordoerColor);
		gc.fillArc(x, borderY, y, y, 0, ANGLE);

		gc.setBackground(selectionColor);
		gc.fillArc(x + TOGGLE_BORDER_MARGIN, borderY + TOGGLE_BORDER_MARGIN, y - TOGGLE_BORDER_MARGIN * 2,
				y - TOGGLE_BORDER_MARGIN * 2, 0, ANGLE);
	}

	public void setText(Position position, String text) {
		toggleTexts[position.ordinal()] = text;
	}

	public void setTexts(String[] texts) {
		toggleTexts = Arrays.copyOf(texts, 2);
	}

	public void setTexts(List<String> textList) {
		setTexts(textList.toArray(new String[TXT_ARR_SIZE]));
	}

	@Override
	@Deprecated
	public Image getButtonImage() {
		return super.getButtonImage();
	}

	@Override
	@Deprecated
	public void setButtonImage(Image buttonImage) {
	}

	@Override
	@Deprecated
	public String getButtonText() {
		return StringUtils.EMPTY;
	}

	@Override
	@Deprecated
	public void setButtonText(String buttonText) {
	}

	/**
	 * 선택한 index를 반환한다.
	 * 
	 * @return 왼쪽이면 0, 오른쪽으면 1을 반환한다.
	 */
	public int getSelectionIndex() {
		return selectionIndex;
	}

	/**
	 * 선택한 index의 text를 반환한다.
	 * 
	 * @return
	 */
	public String getSelectionText() {
		return toggleTexts[selectionIndex];
	}

	public void setToggleColor(Color color) {
		int max = 255;
		int red = Math.min(color.getRed() + 40, max);
		int green = Math.min(color.getGreen() + 40, max);
		int blue = Math.min(color.getBlue() + 40, max);

		selectionColor = SWTGraphicUtil.getColorSafely(color.getRed(), color.getGreen(), color.getBlue());
		selectionBordoerColor = SWTGraphicUtil.getColorSafely(red, green, blue);
	}

	@Override
	public void addListeners() {
		super.addListeners();

		addListener(SWT.MouseUp, new Listener() {

			public void handleEvent(Event paramEvent) {
				int halfWidth = getBounds().width / 2;
				Set<SelectionListener> listeners = getListeners();

				if (listeners != null && paramEvent.button == 1) {
					if (halfWidth <= paramEvent.x) {
						selectionIndex = Position.RIGHT.ordinal();
					} else {
						selectionIndex = Position.LEFT.ordinal();
					}
					redraw();
				}
			}
		});
	}

	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {

		Point leftTextPoint = getTextPoint(toggleTexts[Position.LEFT.ordinal()]);
		Point rightTextPoint = getTextPoint(toggleTexts[Position.RIGHT.ordinal()]);

		int textHeight = Math.max(leftTextPoint.y, rightTextPoint.y);
		int textWidth = Math.max(leftTextPoint.x, rightTextPoint.x);
		int height = Math.max(hHint, textHeight + MARGIN);
		int width = Math.max(wHint, height + (textWidth + HORIZONTAL_SPACING) * 2);
		minHeight = height;
		minWidth = width;
		return super.computeSize(width, height, changed);
	}

	private Point getTextPoint(String str) {
		GC tempGC = new GC(this);
		tempGC.setFont(getFont());
		tempGC.setAntialias(SWT.ON);
		tempGC.setTextAntialias(SWT.ON);

		Point stringExtent = tempGC.textExtent(str);
		tempGC.dispose();
		return stringExtent;
	}

}
