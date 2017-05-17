package com.codescroll.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author pmg
 *
 */
public abstract class CSWidget extends Canvas {

	public static final int DEFAULT_MARGIN = 5;

	public static final int NONE = 0;
	public static final int SEPARATOR_VERTICAL = 1;
	public static final int SEPARATOR_SLASH = 2;

	protected int mouseState = SWT.MouseExit;

	public CSWidget(Composite paramComposite) {
		super(paramComposite, SWT.DOUBLE_BUFFERED);
		addListeners();
	}

	public void addListeners() {
		this.addListener(SWT.MouseExit, new Listener() {

			public void handleEvent(Event paramEvent) {
				checkWidget();
				mouseState = paramEvent.type;
				redraw();
			}
		});
		this.addListener(SWT.MouseEnter, new Listener() {

			public void handleEvent(Event paramEvent) {
				checkWidget();
				mouseState = paramEvent.type;
				redraw();
			}
		});
		this.addListener(SWT.MouseUp, new Listener() {

			public void handleEvent(Event paramEvent) {
				checkWidget();
				mouseState = paramEvent.type;
				redraw();
			}
		});
		this.addListener(SWT.MouseDown, new Listener() {

			public void handleEvent(Event paramEvent) {
				checkWidget();
				if (paramEvent.button == 1) {
					//mouse left button
					mouseState = paramEvent.type;
					redraw();
				} else if (paramEvent.button == 3) {
					//mouse right button
				}
			}
		});

		addPaintListener(new PaintListener() {

			public void paintControl(PaintEvent paramPaintEvent) {
				checkWidget();
				CSWidget.this.drawWidget(paramPaintEvent.gc);
			}
		});
	}

	protected abstract void drawWidget(GC gc);
}
