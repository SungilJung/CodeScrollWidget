package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

import com.codescroll.widget.ColorThemeFactory;
import com.codescroll.widget.button.CSButton;

public class CSPieChartTest {

	private CSPieChart pieChar;
	private CSButton button;
	private Display display;
	private Shell shell;
	
	@Before
	public void init(){
		
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setSize(100, 180);
		pieChar = new CSPieChart(shell);
		pieChar.setColor(CircleKind.CURRENT_VALUE, display.getSystemColor(SWT.COLOR_DARK_GREEN));
		pieChar.setLayoutData(new GridData(GridData.FILL_BOTH));

		button = new CSButton(shell, CSButton.SEPARATOR_SLASH, ColorThemeFactory.getColorTheme(ColorThemeFactory.THEME_DARK)); 
		button.setBorder(true);
		button.setButtonImage(new Image(null,
				CSButton.class.getClassLoader().getResourceAsStream("com/codescroll/widget/button/ButtonImage2.png")));
		button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		button.setButtonText("테스트 실행");
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				pieChar.setValue(100);
			}

			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {
				// TODO Auto-generated method stub

			}
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		
	}

	@Test
	public void drawTest() {
		pieChar.setValue(10);
	}
	

}
