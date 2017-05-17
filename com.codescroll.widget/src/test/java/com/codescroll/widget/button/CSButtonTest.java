package com.codescroll.widget.button;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.codescroll.widget.ColorThemeFactory;

public class CSButtonTest {
	public static void main(String[] args) {
		
		
		Thread thread1 = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				Display display = new Display();
				Shell shell = new Shell(display);
				shell.setLayout(new GridLayout(3, false));
				
				List<Integer> configs = new ArrayList<Integer>();
				configs.add(10);
				configs.add(ColorThemeFactory.THEME_YELLOW);
				configs.add(ColorThemeFactory.THEME_DARK);
				configs.add(ColorThemeFactory.THEME_PURPLE);
				configs.add(ColorThemeFactory.THEME_EMERALD);
				configs.add(ColorThemeFactory.THEME_STEELBLUE);
				for (int colorConfig : configs) {
					CSButtonTest.createButton(shell, CSAbstractButton.SEPARATOR_SLASH, colorConfig, true);
					CSButtonTest.createButton(shell, CSAbstractButton.SEPARATOR_VERTICAL, colorConfig, true);
					CSButtonTest.createButton(shell, CSAbstractButton.NONE, colorConfig, true);
				}

				shell.pack();
				shell.open();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}
				display.dispose();
			}
		});
		thread1.start();
		Thread thread2 = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				Display display = new Display();
				Shell shell = new Shell(display);
				shell.setLayout(new GridLayout(3, false));
				
				List<Integer> configs = new ArrayList<Integer>();
				configs.add(10);
				configs.add(ColorThemeFactory.THEME_YELLOW);
				configs.add(ColorThemeFactory.THEME_DARK);
				configs.add(ColorThemeFactory.THEME_PURPLE);
				configs.add(ColorThemeFactory.THEME_EMERALD);
				configs.add(ColorThemeFactory.THEME_STEELBLUE);
				for (int colorConfig : configs) {
					CSButtonTest.createButton(shell, CSAbstractButton.SEPARATOR_SLASH, colorConfig, false);
					CSButtonTest.createButton(shell, CSAbstractButton.SEPARATOR_VERTICAL, colorConfig, false);
					CSButtonTest.createButton(shell, CSAbstractButton.NONE, colorConfig, false);
				}

				shell.pack();
				shell.open();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}
				display.dispose();
			}
		});
		thread2.start();
	}
	
	private static void createButton(Composite composite , int type , int colorTheme, boolean border) {
		CSButton button3 = new CSButton(composite,type, ColorThemeFactory.getColorTheme(colorTheme));
		button3.setBorder(border);
		button3.setButtonImage(new Image(null,
				CSButton.class.getClassLoader().getResourceAsStream("com/codescroll/widget/button/ButtonImage2.png")));
		button3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		button3.setButtonText("테스트 실행");
		button3.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				// TODO Auto-generated method stub

				System.out.println(((CSButton) paramSelectionEvent.widget).getButtonText());
			}

			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {
				// TODO Auto-generated method stub

			}
		});
	}
}
