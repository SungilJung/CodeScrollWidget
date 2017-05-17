package com.codescroll.widget.button;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.codescroll.widget.CSWidget;
import com.codescroll.widget.ColorConfig;
import com.codescroll.widget.ColorThemeFactory;

/**
 * @author pmg
 *
 */
abstract class CSAbstractButton extends CSWidget {

	public static final int IMAGE_SIZE = 16;
	private Set<SelectionListener> listeners;
	protected ColorConfig colorConfig;
	protected Image buttonImage;
	protected String buttonText;
	protected boolean isBorder = false;

	public CSAbstractButton(Composite parent) {
		this(parent, null, null, null);
	}
	
	public CSAbstractButton(Composite parent, ColorConfig colorConfig) {
		this(parent, colorConfig, null, null);
	}
	
	public CSAbstractButton(Composite parent, Image buttonImage, String buttonText) {
		this(parent, null, buttonImage, buttonText);
	}
	
	public CSAbstractButton(Composite parent, ColorConfig colorConfig, Image buttonImage, String buttonText) {
		super(parent);
		
		this.buttonImage = buttonImage;
		this.buttonText = buttonText;
		
		if (colorConfig != null) {
			this.colorConfig = colorConfig;
		} else {
			this.colorConfig = ColorThemeFactory.getColorTheme(ColorThemeFactory.THEME_DEFAULT);
		}
	}

	@Override
	public void addListeners() {
		super.addListeners();
		addListener(SWT.MouseUp, new Listener() {

			public void handleEvent(Event paramEvent) {
				if (listeners != null) {
					for (SelectionListener selectionListener : listeners) {
						SelectionEvent selectionEvent = new SelectionEvent(paramEvent);
						selectionListener.widgetSelected(selectionEvent);
					}
				}
			}
		});
	}

	public void addSelectionListener(SelectionListener selectionListener) {
		if (listeners == null) {
			listeners = new HashSet<SelectionListener>();
		}
		listeners.add(selectionListener);
	}

	public void removeSelectionListener(SelectionListener selectionListener) {
		if (listeners != null) {
			listeners.remove(selectionListener);
		}
	}

	public Image getButtonImage() {
		return buttonImage;
	}

	public void setButtonImage(Image buttonImage) {
		this.buttonImage = buttonImage;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public boolean isBorder() {
		return isBorder;
	}
	
	public ColorConfig getButtonColorConfig() {
		return colorConfig;
	}
	
	public void setButtonColorConfig(ColorConfig colorConfig) {
		this.colorConfig = colorConfig;
	}
	
	public void setBorder(boolean isBorder) {
		this.isBorder = isBorder;
	}

	@Override
	protected void drawWidget(GC gc) {
		if ((buttonImage != null && buttonImage.isDisposed()) || (buttonText == null)) {
			SWT.error(SWT.ERROR_INVALID_ARGUMENT);
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		if (buttonImage != null && !buttonImage.isDisposed()) {
			buttonImage.dispose();
		}
	}
}
