package com.codescroll.widget.button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.codescroll.widget.util.SWTGraphicUtil;

public class CSSearchButton extends CSAbstractButton {
	private Image searchImage;
	
	public CSSearchButton(Composite paramComposite) {
		super(paramComposite);
		searchImage = new Image (getDisplay(), getClass().getClassLoader().getResourceAsStream("com/codescroll/widget/text/search.png"));
		setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		setLayoutData(new GridData());
	}

	@Override
	protected void drawWidget(GC gc) {
		gc.drawImage(searchImage, 2, 2);
	}
	@Override
	public Point computeSize(int paramInt1, int paramInt2, boolean paramBoolean) {
		return super.computeSize(18, 18, paramBoolean);
	}
}

