package com.codescroll.widget.button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codescroll.widget.button.ToggleButton.Position;

public class ToggleButtonTest {
	
	private ToggleButton toggle;
	private Display display;
	private Shell shell;
	
	@Before
	public void init(){
		
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setSize(500, 300);
		toggle = createToggleButton();
		
		shell.open();
		
	}
	
	private ToggleButton createToggleButton() {
		ToggleButton toggle = new ToggleButton(shell);
		
		toggle.setText(Position.LEFT, "호스트");
		toggle.setText(Position.LEFT, "타깃");
		toggle.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
		toggle.setForeground(display.getSystemColor(SWT.COLOR_DARK_YELLOW));
		toggle.setFont(new Font(display, "Arial", 10, SWT.BOLD));
		toggle.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		return toggle;
	}
	
	@Test
	public void testDrawWidget() {
	}
	
	
	
	@After
	public void after() {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}
