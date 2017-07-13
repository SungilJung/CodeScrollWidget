package com.codescroll.widget.button;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.hamcrest.CoreMatchers;

import com.codescroll.widget.util.SWTGraphicUtil;

public class ToggleButton extends CSAbstractButton {

	public enum Position {
		LEFT, RIGHT
	}

	private final int TXT_ARR_SIZE = 2;
	private final int HORIZONTAL_SPACING = 5;
	private final int MARGIN = 10;
	private final int BORDER_MARGIN = 3;
	private final int TOGGLE_BORDER_MARGIN = 3;
	private final int MIN_WIDTH = 60;
	private final int ANGLE = 360;

	private Color borderColor;
	private Color unSelectedColor;
	private Color selectionColor;
	private Color selectionBordoerColor;

	private String[] toggleTexts = new String[TXT_ARR_SIZE];
	private int selectionIndex;

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

		Canvas canvas = this;
		int x = canvas.getBounds().width;
		int y = canvas.getBounds().height;

		String leftText = toggleTexts[Position.LEFT.ordinal()];
		String rightText = toggleTexts[Position.RIGHT.ordinal()];

		Point leftExtent = gc.textExtent(leftText);
		Point rightExtent = gc.textExtent(rightText);

		gc.setBackground(getBackground());
		gc.setForeground(getSelectionFontColor(Position.LEFT.ordinal()));
		gc.drawText(leftText, 0, y / 2 - leftExtent.y / 2);

		gc.setBackground(borderColor);
		int borderRectangleWidth = x - (leftExtent.x + rightExtent.x + (HORIZONTAL_SPACING) * 2);
		borderRectangleWidth = Math.max(borderRectangleWidth, (y - (MARGIN + BORDER_MARGIN)) * 2);
		gc.fillRoundRectangle(leftExtent.x + HORIZONTAL_SPACING, MARGIN, borderRectangleWidth, y - (MARGIN * 2),
				y - MARGIN, y - MARGIN);

		gc.setBackground(unSelectedColor);
		gc.fillRoundRectangle(leftExtent.x + HORIZONTAL_SPACING + BORDER_MARGIN, MARGIN + BORDER_MARGIN,
				borderRectangleWidth - BORDER_MARGIN * 2, y - (MARGIN + BORDER_MARGIN) * 2,
				y - (MARGIN + BORDER_MARGIN), y - (MARGIN + BORDER_MARGIN));

		gc.setBackground(selectionBordoerColor);
		gc.fillArc(leftExtent.x + HORIZONTAL_SPACING, MARGIN - BORDER_MARGIN, y - (MARGIN + BORDER_MARGIN), y - (MARGIN + BORDER_MARGIN), 0, ANGLE);

		gc.setBackground(selectionColor);
		gc.fillArc(leftExtent.x + HORIZONTAL_SPACING + TOGGLE_BORDER_MARGIN, MARGIN - BORDER_MARGIN + TOGGLE_BORDER_MARGIN, y - (MARGIN + BORDER_MARGIN + TOGGLE_BORDER_MARGIN * 2), y - (MARGIN + BORDER_MARGIN + TOGGLE_BORDER_MARGIN * 2), 0, ANGLE);

		
		gc.setBackground(getBackground());
		int rightTextX = leftExtent.x + borderRectangleWidth + HORIZONTAL_SPACING * 2;
		rightTextX = Math.max(rightTextX, x - rightExtent.x);
		gc.setForeground(getSelectionFontColor(Position.RIGHT.ordinal()));
		gc.drawText(rightText, rightTextX, y / 2 - leftExtent.y / 2);

	}

	private Color getSelectionFontColor(int index) {
		Color color = getForeground();
		if (selectionIndex != index) {
			color = unSelectedColor;
		}
		return color;
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
	public void setSize(int width, int height) {
		super.setSize(Math.max(width, MIN_WIDTH), height);
	}

}
