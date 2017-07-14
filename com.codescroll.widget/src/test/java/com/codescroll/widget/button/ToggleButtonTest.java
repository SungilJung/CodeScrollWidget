package com.codescroll.widget.button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import com.codescroll.widget.util.SWTGraphicUtil;

public class ToggleButtonTest {
	
	private ToggleButton toggle;
	private Display display;
	private Shell shell;
	
	@Before
	public void init(){
		
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(200, 500);
		toggle = createToggleButton();
		toggle.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		
		shell.open();
		
	}
	
	private ToggleButton createToggleButton() {
		ToggleButton toggle = new ToggleButton(shell);
		
		Color color = SWTGraphicUtil.getColorSafely(193, 141, 34);
		toggle.setText(Position.LEFT, "호스트");
		toggle.setText(Position.RIGHT, "타깃");
		toggle.setForeground(color);
		toggle.setToggleColor(color);
		toggle.setFont(new Font(display, "Arial", 55, SWT.BOLD));
		toggle.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("hi");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
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
