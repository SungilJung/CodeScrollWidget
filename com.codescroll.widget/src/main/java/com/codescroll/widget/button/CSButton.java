package com.codescroll.widget.button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

import com.codescroll.widget.CSWidget;
import com.codescroll.widget.ColorConfig;
import com.codescroll.widget.ColorThemeFactory;
import com.codescroll.widget.util.SWTGraphicUtil;

/**
 * @author pmg
 *
 */
public class CSButton extends CSAbstractButton {

	private int separator;
	private int[] padding = { 10, 40, 10, 15 };
	protected ColorConfig colorConfig;
	public CSButton(Composite paramComposite, int separator) {
		this(paramComposite, separator, null, null, null);
	}
	
	public CSButton(Composite paramComposite, int separator , ColorConfig colorConfig) {
		this(paramComposite, separator, colorConfig, null, null);
	}
	
	public CSButton(Composite paramComposite, int separator, ColorConfig colorConfig, Image buttonImage, String text) {
		super(paramComposite, buttonImage, text);
		checkStyle(separator);
		if (colorConfig != null) {
			this.colorConfig = colorConfig;
		} else {
			this.colorConfig = ColorThemeFactory.getColorTheme(ColorThemeFactory.THEME_DEFAULT);
		}
		initDefaultFont();
	}

	private void initDefaultFont() {
		Font font = getFont();
		FontData[] arrFontData = font.getFontData();
		for (FontData fontData : arrFontData) {
			fontData.height = 10;
			fontData.setStyle(SWT.BOLD);

		}
		setFont(new Font(getDisplay(), arrFontData));
	}

	private void checkStyle(int style) {
		switch (style) {
		case CSWidget.SEPARATOR_VERTICAL:
			separator = CSWidget.SEPARATOR_VERTICAL;
			break;
		case CSWidget.SEPARATOR_SLASH:
			separator = CSWidget.SEPARATOR_SLASH;
			break;
		default:
			separator = CSWidget.NONE;
			break;
		}
	}

	@Override
	protected void drawWidget(GC gc) {
		super.drawWidget(gc);
		Point size = getSize();
		Rectangle rectangle = getRectangle(size);
		gc.setAntialias(SWT.ON);
		if (mouseState == SWT.MouseDown) {
			gc.setBackground(colorConfig.getForegroundColor());
		} else {
			gc.setBackground(colorConfig.getBackgroundColor());
		}
		gc.fillRoundRectangle(rectangle.x + 1, rectangle.y + 1, rectangle.width - 2, rectangle.height - 2, 8, 8);
		gc.setBackground(colorConfig.getSeparatorColor());
		int[] separatorPolygon = getSeparatorPolygon(size);
		
		if (separatorPolygon != null) {
			gc.fillPolygon(separatorPolygon);
		}
		
		if (isBorder()) {
			gc.setLineWidth(2);
			gc.setForeground(colorConfig.getBorderColor());
			gc.drawRoundRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 8, 8);
			gc.setForeground(SWTGraphicUtil.getColorSafely( 200, 200, 200));
			gc.drawRoundRectangle(rectangle.x + 1, rectangle.y + 1, rectangle.width - 2, rectangle.height - 2, 8, 8);
		} else {
			gc.setLineWidth(2);
			gc.setForeground(getBackground());
			gc.drawRoundRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 8, 8);
		}
		
		
		if (buttonImage != null) {
			gc.drawImage(buttonImage, 0, 0, buttonImage.getBounds().width, buttonImage.getBounds().height,
					DEFAULT_MARGIN + padding[3] + getImageLeftPadding(), (size.y / 2) - (IMAGE_SIZE / 2), IMAGE_SIZE,
					IMAGE_SIZE);
		}

		if (buttonText != null) {
			gc.setTextAntialias(SWT.ON);
			gc.setForeground(colorConfig.getTextColor());
			gc.setFont(getFont());
			Point stringExtent = gc.stringExtent(buttonText);
			gc.drawString(buttonText, size.x / 2 - (stringExtent.x / 2) + padding[2],
					size.y / 2 - (stringExtent.y / 2), true);
		}
		
		if (!isEnabled()) {
			gc.setAlpha(150);
			gc.setForeground(SWTGraphicUtil.getColorSafely(155, 155, 155));
			gc.fillRoundRectangle(rectangle.x + 1, rectangle.y + 1, rectangle.width - 2, rectangle.height - 2, 8, 8);
		}
	}

	private Rectangle getRectangle(Point size) {
		// x , y ,width, height
		Rectangle rectangle = new Rectangle(DEFAULT_MARGIN, DEFAULT_MARGIN, size.x - (DEFAULT_MARGIN * 2),
				size.y - (DEFAULT_MARGIN * 2));

		return rectangle;
	}
	
	public ColorConfig getButtonColorConfig() {
		return colorConfig;
	}
	
	public void setButtonColorConfig(ColorConfig colorConfig) {
		this.colorConfig = colorConfig;
	}
	
	private int getImageLeftPadding() {
		int imageLeftPadding = 0;
		if (separator == SEPARATOR_VERTICAL) {
			imageLeftPadding = 6;
		}
		return imageLeftPadding;
	}
	
	private int[] getSeparatorPolygon(Point size) {
		
		int[] polygon = null;
		
		if (separator == SEPARATOR_SLASH) {
			polygon = new int[]{ 
						DEFAULT_MARGIN + 2,	DEFAULT_MARGIN + 2, 
						DEFAULT_MARGIN + padding[3] + 30, DEFAULT_MARGIN + 2, 
						DEFAULT_MARGIN + padding[3]+ 20, size.y - 6, 
						DEFAULT_MARGIN + 2,	size.y - 6 };
		} else if (separator == SEPARATOR_VERTICAL) {
			polygon = new int[]{ 
					DEFAULT_MARGIN + 2,	DEFAULT_MARGIN + 2, 
					DEFAULT_MARGIN + padding[3] + 15, DEFAULT_MARGIN + 2, 
					DEFAULT_MARGIN + padding[3]+ 15, size.y - 6, 
					DEFAULT_MARGIN + 2,	size.y - 6 };
		}
		
		return polygon;
	}
	
	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		int width = 2 * DEFAULT_MARGIN + padding[1] + padding[3];
		int height = 2 * DEFAULT_MARGIN + padding[0] + padding[2];

		int imageHeight = 0, textHeight = 0;

		if ((buttonImage != null && buttonImage.isDisposed()) || (buttonText == null)) {
			SWT.error(SWT.ERROR_INVALID_ARGUMENT);
		}

		if (buttonImage != null) {
			width += IMAGE_SIZE;
			imageHeight = IMAGE_SIZE;
		}

		GC gc = new GC(this);
		gc.setFont(getFont());
		Point stringExtent = gc.stringExtent(buttonText);

		width += stringExtent.x;
		textHeight = stringExtent.y;
		gc.dispose();

		height += (imageHeight >= textHeight ? imageHeight : textHeight);

		return super.computeSize(width, height, changed);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		redraw();
	}
	
	public int getSeparator() {
		return separator;
	}

	public void setSeparator(int separator) {
		this.separator = separator;
	}
}
