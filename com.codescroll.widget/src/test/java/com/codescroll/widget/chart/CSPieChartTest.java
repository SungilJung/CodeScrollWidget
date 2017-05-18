package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class CSPieChartTest {
	
	private Display display;
	private Shell shell;
	private CSPieChart pieChart;
	
	@Test
	public void test() {
		
		
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setSize(200, 200);
		
		pieChart = new CSPieChart(shell, SWT.NONE);
		pieChart.setLayoutData(new GridData(GridData.FILL_BOTH));
		pieChart.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
		pieChart.setTitle("hi");
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		
		
	}

}
