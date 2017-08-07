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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codescroll.widget.ColorThemeFactory;
import com.codescroll.widget.button.CSButton;

public class CSPieTest {

	private CSPie pie;
	private Display display;
	private Shell shell;
	private CSButton button;
	private float value;

	@Before
	public void init() {

		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setSize(208, 208);
		pie = createCSPie();
		button = createButton(shell, CSButton.SEPARATOR_SLASH, ColorThemeFactory.THEME_PURPLE, true);

		shell.open();

	}

	private CSPie createCSPie() {
		CSPie pie = new CSPie(shell);
		pie.setForeground(display.getSystemColor(SWT.COLOR_DARK_GREEN));
		pie.setFont(new Font(display, "Arial", 12, SWT.BOLD));
		pie.setLayoutData(new GridData(GridData.FILL_BOTH));
		return pie;
	}

	private CSButton createButton(Composite composite, int type, int colorTheme, boolean border) {

		CSButton button = new CSButton(composite, type, ColorThemeFactory.getColorTheme(colorTheme));
		button.setBorder(border);
		button.setButtonImage(new Image(null,
				CSButton.class.getClassLoader().getResourceAsStream("com/codescroll/widget/button/ButtonImage2.png")));
		button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.GRAB_HORIZONTAL));
		button.setButtonText("테스트 실행");

		return button;
	}

	@Test
	public void drawValueDownTest() {
		
		value = 100;
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				if (!pie.isCalculate()) {
					pie.setValue(value);
					value -= 10;
				}
			}

			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {

			}
		});
	}

	@Test
	public void drawValueUPTest() {
		value = 10;
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				if (!pie.isCalculate()) {
					pie.setValue(value);
					value += 10.5;
				}
			}

			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {

			}
		});

	}
	
	@Test
	public void drawValueInitTest() {
		value = 50;
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				pie.initState();
				pie.setValue(50);
			}

			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {

			}
		});

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
