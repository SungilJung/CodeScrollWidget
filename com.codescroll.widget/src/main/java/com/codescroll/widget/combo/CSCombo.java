package com.codescroll.widget.combo;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import com.codescroll.widget.CSWidget;

public class CSCombo extends Composite {

	private List list;
	
	public CSCombo(Composite parent) {
		super(parent, SWT.NONE);
		createComposite();
	}
	
	public void addItem(String item) {
//		items.add(item);
	}
	
	public void addItem(String item, int index) {
//		items.add(index, item);
	}
	
//	public void addItems(List<String> items) {
//		this.items.addAll(items);
//	}
	
	private void createComposite() {
		setBackground(new Color(null, 255,255,255));
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		setLayout(gridLayout);
//		ShowButton button = new ShowButton(this, "구문 커버리지");
//		List list = new List(this, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL| SWT.BORDER);
//		list.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		list.getVerticalBar();
//		list.add("구문 커버리지");
//		list.add("커버리지");
//		list.add("커버리지");
//		list.add("커버리지");
//		list.add("커버리지");
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(300, 300);
		shell.setBackground(new Color(null, 255,255,255));
		shell.setLayout(new GridLayout(1, false));
		CSCombo csCombo = new CSCombo(shell);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	
}


