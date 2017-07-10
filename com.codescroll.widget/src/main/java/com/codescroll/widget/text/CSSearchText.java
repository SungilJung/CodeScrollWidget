package com.codescroll.widget.text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.codescroll.widget.button.CSSearchButton;
import com.codescroll.widget.util.SWTGraphicUtil;

public class CSSearchText extends Composite {
	private Composite composite;
	private CSSearchButton searchButton;
	private Text text;
	private Color focusIn;
	private Color focusOut;

	public CSSearchText(Composite parent, int paramInt) {
		super(parent, paramInt);
		createComposite(parent);
		focusIn = SWTGraphicUtil.getColorSafely(203, 216, 230);
		focusOut = SWTGraphicUtil.getColorSafely(158, 158, 158);
	}

	private void createComposite(Composite parent) {
		setLayout(new GridLayout(1, false));
		setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		composite = new Composite(this, SWT.NONE);
		composite.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent event) {
				GC gc = event.gc;
				Rectangle bounds = composite.getBounds();
				gc.setAntialias(SWT.ON);
				gc.setBackground(text.getBackground());
				if (text.isFocusControl()) {
					gc.setForeground(focusIn);
				} else {
					gc.setForeground(focusOut);
				}
				gc.fillRoundRectangle(2, 2, bounds.width - 4, bounds.height - 4, 25, 25);
				gc.drawRoundRectangle(1, 1, bounds.width - 2, bounds.height - 2, 25, 25);
				
			}
		});

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = 5;
		gridLayout.marginLeft = 10;
		gridLayout.marginRight = 5;
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text = new Text(composite, SWT.SINGLE);
		text.addListener(SWT.FocusIn, new Listener() {

			@Override
			public void handleEvent(Event event) {
				composite.redraw();
			}
		});

		text.addListener(SWT.FocusOut, new Listener() {

			@Override
			public void handleEvent(Event event) {
				composite.redraw();
			}
		});
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 200;
		text.setLayoutData(gridData);
		text.setMessage("Search");
		searchButton = new CSSearchButton(composite);
	}
	
	public void addSelectionListener(SelectionListener selectionListener) {
		searchButton.addSelectionListener(selectionListener);
	}
	
	public void addModifyListener(ModifyListener modifyListener) {
		text.addModifyListener(modifyListener);
	}
	
	public String getText() {
		return text.getText();
	}
}
