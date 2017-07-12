package com.codescroll.widget.button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

import com.codescroll.widget.util.SWTGraphicUtil;

/**
 * @author pmg
 *
 */
public class CSIconButton extends CSAbstractButton {

	String[] labels;
	private Color mouseEnterColor;
	private Color mouseDownColor;
	private Color borderColor;
	
	public CSIconButton(Composite parent) {
		this(parent, null, null);
	}
	
	public CSIconButton(Composite parent, Image buttonImage, String buttonText) {
		super(parent, buttonImage, buttonText);
		setBorder(true);
		mouseEnterColor = SWTGraphicUtil.getColorSafely(220, 220, 220);
		mouseDownColor = SWTGraphicUtil.getColorSafely(200, 200, 200);
		borderColor = SWTGraphicUtil.getColorSafely(215, 215, 215);
	}

	@Override
	protected void drawWidget(GC gc) {
		super.drawWidget(gc);
		Point size = getSize();
		gc.setAntialias(SWT.ON);
		Color fontColor = getForeground();
		if (mouseState == SWT.MouseEnter) {
			gc.setBackground(mouseEnterColor);
			gc.fillRectangle(0, 0, size.x, size.y);
		} else if (mouseState == SWT.MouseDown) {
			gc.setBackground(mouseDownColor);
			gc.fillRectangle(0, 0, size.x, size.y);
		}

		if (isBorder()) {
			gc.setForeground(borderColor);
			gc.drawRoundRectangle((size.x / 2) - 25, 4, 50, 50, 5, 5);
		}
		
		Image buttonImage = getButtonImage();
		gc.drawImage(buttonImage, (size.x / 2) - (buttonImage.getBounds().width / 2) , 29 - (buttonImage.getBounds().height / 2));
		
		gc.setForeground(fontColor);
		for (int i = 0 ; i < labels.length ; i++) {
			Point point = gc.stringExtent(labels[i]);
			gc.drawString(labels[i], (size.x / 2) - (point.x / 2), 60 + (i * point.y), true);
		}
	}
	
	@Override
	public Point computeSize(int paramInt1, int paramInt2, boolean paramBoolean) {
		return super.computeSize(80, 95, paramBoolean);
	}
	
	/**
	 * 버튼의 이미지 밑에 들어가는 텍스트 설정 <br>
	 * */
	@Override
	public void setButtonText(String buttonText) {
		super.setButtonText(buttonText);
		labels = buttonText.split("\n");
	}
}
