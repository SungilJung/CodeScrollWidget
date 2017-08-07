package com.codescroll.widget.button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CSIconButtonTest {
	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("StackOverflow");
		shell.setLayout(new GridLayout(4, false));
		createButton(display, shell, "버튼 텍스트");
		createButton(display, shell, "버튼\n텍스트");
		createButton(display, shell, "아이콘 버튼\n텍스트");
		createButton(display, shell, "버튼 텍스트");
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	private static void createButton(Display display, Composite parent, String text) { 
		Image image = new Image(display, CSIconButtonTest.class.getClassLoader().getResourceAsStream("com/codescroll/widget/button/iconbuttonImage.PNG"));
		CSIconButton iconButton = new CSIconButton(parent);
		iconButton.setButtonImage(image);
		iconButton.setButtonText(text);
	}
}
