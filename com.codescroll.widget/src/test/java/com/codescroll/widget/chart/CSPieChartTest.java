package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codescroll.widget.util.SWTGraphicUtil;

public class CSPieChartTest {

	private Display display;
	private Shell shell;
	private CSPieChart[] pieChartArr;
	private String[] titleArr;
	private float[] valueArr;

	@Before
	public void init() {
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(5, false));
		shell.setSize(450, 200);
		initData();
		createPieChart();
	}

	private void initData() {
		pieChartArr = new CSPieChart[4];
		titleArr = new String[] { "구문", "분기", "MC/DC", "함수 호출" };
		valueArr = new float[] { 92.5f, 60, 50, 75 };
	}

	private void createPieChart() {
		for (int i = 0; i < 4; i++) {

			pieChartArr[i] = new CSPieChart(shell, SWT.NONE);
			pieChartArr[i].setLayoutData(new GridData(GridData.FILL_BOTH));
			pieChartArr[i].setForeground(display.getSystemColor(SWT.COLOR_GRAY));
			pieChartArr[i].setTitleColor(display.getSystemColor(SWT.COLOR_GRAY));
			pieChartArr[i].setTitle(titleArr[i]);
			pieChartArr[i].setValue(valueArr[i]);
			pieChartArr[i].setOuterCircleColor(SWTGraphicUtil.getColorSafely(200+i, 156 + i*10, 120 + i*20));;
		}

	}

	@Test
	public void test() {

	}

	@After
	public void after() {
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
