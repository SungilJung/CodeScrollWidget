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
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.hamcrest.CoreMatchers;

import com.codescroll.widget.util.SWTGraphicUtil;

public class ToggleButton extends CSAbstractButton {

	public enum Position {
		LEFT, RIGHT
	}

	private static final int TXT_ARR_SIZE = 2;
	private static final int HORIZONTAL_SPACING = 5;

	private Color border;
	private Color unSelectedColor;
	private String[] toggleTexts = new String[TXT_ARR_SIZE];
	private int selectionIndex;

	public ToggleButton(Composite parent) {
		super(parent);
		init();
	}
	
	private void init() {
		this.selectionIndex = 0;
		toggleTexts = new String[] { "", "" };
		border = SWTGraphicUtil.getColorSafely(231, 230, 230);
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
		int roundRectangleHeight =leftExtent.y;

		gc.drawText(leftText, 0, y / 2 - leftExtent.y / 2);
		gc.setBackground(border);
		gc.fillRoundRectangle(leftExtent.x + HORIZONTAL_SPACING, y / 2 - roundRectangleHeight / 2, roundRectangleHeight * 2,
				roundRectangleHeight, roundRectangleHeight, roundRectangleHeight);
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

}
