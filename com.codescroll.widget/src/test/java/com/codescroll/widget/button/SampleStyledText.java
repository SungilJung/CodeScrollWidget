package com.codescroll.widget.button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class SampleStyledText {
	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout(SWT.HORIZONTAL));
		final Tree tree = new Tree(shell, SWT.BORDER | SWT.CHECK);
		tree.setLayoutData(new RowData(-1, 300));
		tree.setHeaderVisible(true);
		TreeColumn column = new TreeColumn(tree, SWT.LEFT);
		column.setText("Column 0");
		column = new TreeColumn(tree, SWT.CENTER);
		column.setText("Column 1");
		column = new TreeColumn(tree, SWT.LEFT);
		column.setText("Column 2");
		column = new TreeColumn(tree, SWT.RIGHT);
		column.setText("Column 3");
		column = new TreeColumn(tree, SWT.CENTER);
		column.setText("Column 4");
		for (int i = 0; i < 5; i++) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			String[] text = new String[] { i + ":0", i + ":1", i + ":2", i + ":3", i + ":4" };
			item.setText(text);
			for (int j = 0; j < 5; j++) {
				TreeItem subItem = new TreeItem(item, SWT.NONE);
				text = new String[] { i + "," + j + ":0", i + "," + j + ":1", i + "," + j + ":2", i + "," + j + ":3",
						i + "," + j + ":4" };
				subItem.setText(text);
				for (int k = 0; k < 5; k++) {
					TreeItem subsubItem = new TreeItem(subItem, SWT.NONE);
					text = new String[] { i + "," + j + "," + k + ":0", i + "," + j + "," + k + ":1",
							i + "," + j + "," + k + ":2", i + "," + j + "," + k + ":3", i + "," + j + "," + k + ":4" };
					subsubItem.setText(text);
				}
			}
		}

		Listener listener = new Listener() {

			@Override
			public void handleEvent(Event e) {
				// TODO Auto-generated method stub
				System.out.println("Move " + e.widget);
			}
		};
		TreeColumn[] columns = tree.getColumns();
		for (int i = 0; i < columns.length; i++) {
			columns[i].setWidth(100);
			columns[i].setMoveable(true);
			columns[i].addListener(SWT.Move, listener);
		}
		Button b = new Button(shell, SWT.PUSH);
		b.setText("invert column order");
		b.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				int[] order = tree.getColumnOrder();
				for (int i = 0; i < order.length / 2; i++) {
					int temp = order[i];
					order[i] = order[order.length - i - 1];
					order[order.length - i - 1] = temp;
				}
				tree.setColumnOrder(order);
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

	static int itemCount;

	static CoolItem createItem(CoolBar coolBar, int count) {
		ToolBar toolBar = new ToolBar(coolBar, SWT.FLAT);
		for (int i = 0; i < count; i++) {
			ToolItem item = new ToolItem(toolBar, SWT.PUSH);
			item.setText(itemCount++ + "");
		}
		toolBar.pack();
		Point size = toolBar.getSize();
		CoolItem item = new CoolItem(coolBar, SWT.NONE);
		item.setControl(toolBar);
		Point preferred = item.computeSize(size.x, size.y);
		item.setPreferredSize(preferred);
		return item;
	}

}
