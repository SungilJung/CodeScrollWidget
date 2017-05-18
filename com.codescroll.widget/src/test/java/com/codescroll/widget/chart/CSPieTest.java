package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

public class CSPieTest {

	private CSPie pieChar;
	private Display display;
	private Shell shell;
	
	@Before
	public void init(){
		
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setSize(200, 200);
		pieChar = new CSPie(shell);
		pieChar.setForeground(display.getSystemColor(SWT.COLOR_DARK_GREEN));
		pieChar.setColor(CircleKind.CURRENT_VALUE, display.getSystemColor(SWT.COLOR_DARK_GREEN));
		pieChar.setFont(new Font(display, "Arial", 10, SWT.BOLD));
		pieChar.setSize(50, 50);
		shell.open();
		
	}

	@Test
	public void drawTest() {
		pieChar.setValue(100);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	

}
