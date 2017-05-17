package com.codescroll.widget;

import org.eclipse.swt.graphics.Color;

/**
 * @author pmg
 *
 */
public class ColorConfig {
	
	private Color backgroundColor;
	private Color foregroundColor;
	private Color separatorColor;
	private Color textColor;
	private Color borderColor;

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}
	
	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}
	
	public Color getSeparatorColor() {
		return separatorColor;
	}
	
	public void setSeparatorColor(Color separatorColor) {
		this.separatorColor = separatorColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
}
