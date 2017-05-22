package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

import com.codescroll.widget.ColorThemeFactory;
import com.codescroll.widget.button.CSButton;

public class CSPieTest {

	private CSPie pie;
	private Display display;
	private Shell shell;
	private CSButton button;
	private int value; 
	
	@Before
	public void init(){
		
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(200, 200);
		
		pie = createCSPie();
		button = createButton(shell, CSButton.SEPARATOR_SLASH, ColorThemeFactory.THEME_PURPLE, true);;
		
		shell.open();
		
	}
	
	private CSPie createCSPie(){
		CSPie pie = new CSPie(shell);
		pie.setForeground(display.getSystemColor(SWT.COLOR_DARK_GREEN));
		pie.setBackground(display.getSystemColor(SWT.COLOR_DARK_GREEN));
		pie.setColor(CircleKind.CURRENT_VALUE, display.getSystemColor(SWT.COLOR_DARK_GREEN));
		pie.setFont(new Font(display, "Arial", 10, SWT.BOLD));
		pie.setSize(50, 50);
		
		return pie;
	}
	
	private CSButton createButton(Composite composite , int type , int colorTheme, boolean border) {
		CSButton button = new CSButton(composite,type, ColorThemeFactory.getColorTheme(colorTheme));
		button.setBorder(border);
		button.setButtonImage(new Image(null,
				CSButton.class.getClassLoader().getResourceAsStream("com/codescroll/widget/button/ButtonImage2.png")));
		button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		button.setButtonText("테스트 실행");
		
		
		return button;
	}

	@Test
	public void drawValueDownTest() {
		value = 100;
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				pie.setValue(value);
				value-=10;
			}

			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {

			}
		});
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	@Test
	public void drawValueUPTest() {
		value = 10;
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				pie.setValue(value);
				value+=10;
			}

			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {

			}
		});
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	

}
