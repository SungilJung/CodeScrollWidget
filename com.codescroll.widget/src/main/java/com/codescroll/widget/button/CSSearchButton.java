package com.codescroll.widget.button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.codescroll.widget.util.SWTGraphicUtil;

public class CSSearchButton extends CSAbstractButton {
	private Image searchImage;
	private Color colorMouseDown;
	private Color colorMouseEnter;
	private Color colorBorder;
	
	public CSSearchButton(Composite paramComposite) {
		super(paramComposite);
		searchImage = new Image (getDisplay(), getClass().getClassLoader().getResourceAsStream("com/codescroll/widget/text/search.png"));
		setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		setLayoutData(new GridData());
		Cursor cursor = new Cursor(getDisplay(), SWT.CURSOR_HAND);
		setCursor(cursor);
		colorMouseDown = SWTGraphicUtil.getColorSafely(222, 222, 222);
		colorMouseEnter = SWTGraphicUtil.getColorSafely(240, 240, 240);
		colorBorder = SWTGraphicUtil.getColorSafely(150, 150, 150);
	}

	@Override
	protected void drawWidget(GC gc) {
		
		gc.setAntialias(SWT.ON);
		if (mouseState == SWT.MouseDown) {
			gc.setForeground(colorMouseDown);
			gc.fillGradientRectangle(1, 1, 16, 16, true);
			gc.setForeground(colorBorder);
			gc.drawRoundRectangle(0, 0, 17, 17, 5, 5);
		} else if(mouseState == SWT.MouseEnter) {
			gc.setForeground(colorMouseEnter);
			gc.fillGradientRectangle(1, 1, 18, 18, true);
			gc.drawRoundRectangle(0, 0, 17, 17, 5, 5);
		}
		gc.drawImage(searchImage, 2, 2);
	}
	@Override
	public Point computeSize(int paramInt1, int paramInt2, boolean paramBoolean) {
		return super.computeSize(18, 18, paramBoolean);
	}
}

