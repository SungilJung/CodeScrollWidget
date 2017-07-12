package com.codescroll.widget.text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CSSearchTextTest {
	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		CSSearchText searchText = new CSSearchText(shell, SWT.NONE);
		searchText.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				System.out.println("Button Click!!!!!!!");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {
			}
		});
		
		Button button = new Button(shell, SWT.PUSH);
		button.setLayoutData(new GridData(GridData.FILL_BOTH));
		button.setText("BUTTON");
		
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
