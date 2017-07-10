package com.codescroll.widget.button;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.codescroll.widget.ColorThemeFactory;

public class CSButtonTest {
	static Point originalPosition = null;
	public static void main(String[] args) {
		
		
		Thread thread1 = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				final Display display = new Display();
				final Shell shell = new Shell(display); 
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

				// Make the shell movable by using the mouse pointer. 
			    shell.addMouseListener(new MouseListener() {
			      public void mouseDoubleClick(MouseEvent e) {
			        shell.dispose(); // Double click to dispose the shell.
			      }

			      public void mouseDown(MouseEvent e) {
			        originalPosition = new Point(e.x, e.y);
			      }

			      public void mouseUp(MouseEvent e) {
			        originalPosition = null;
			      }
			    });
			    
			    shell.addMouseMoveListener(new MouseMoveListener() {
			      public void mouseMove(MouseEvent e) {
			        if(originalPosition == null)
			          return;
			        Point point = display.map(shell, null, e.x, e.y);
			        shell.setLocation(point.x - originalPosition.x, point.y - originalPosition.y);
			        System.out.println("Moved from: " + originalPosition + " to " + point);
			      }
			    });
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
				CSButtonTest.class.getClassLoader().getResourceAsStream("com/codescroll/widget/button/ButtonImage2.png")));
		button3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		button3.setButtonText("테스트 실행");
		button3.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				System.out.println(((CSButton) paramSelectionEvent.widget).getButtonText());
			}

			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {

			}
		});
	}
}
