package com.codescroll.widget.button;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

import com.codescroll.widget.ColorConfig;

/**
 * @author pmg
 *
 */
public class CSIconButton extends CSAbstractButton {

	public CSIconButton(Composite parent) {
		super(parent, null, null, null);
	}
	
	public CSIconButton(Composite parent, ColorConfig colorConfig) {
		super(parent, colorConfig, null, null);
	}
	public CSIconButton(Composite parent, Image buttonImage, String buttonText) {
		super(parent, null, buttonImage, buttonText);
	}
	
	public CSIconButton(Composite parent, ColorConfig colorConfig, Image buttonImage, String buttonText) {
		super(parent, colorConfig, buttonImage, buttonText);
	}

	@Override
	protected void drawWidget(GC gc) {
		super.drawWidget(gc);
		
	}
	@Override
	public Point computeSize(int paramInt1, int paramInt2, boolean paramBoolean) {
		return super.computeSize(paramInt1, paramInt2, paramBoolean);
	}
}
