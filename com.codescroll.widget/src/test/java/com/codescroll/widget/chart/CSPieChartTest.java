package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codescroll.widget.ColorThemeFactory;
import com.codescroll.widget.button.CSButton;
import com.codescroll.widget.util.SWTGraphicUtil;

public class CSPieChartTest {

	private Display display;
	private Shell shell;
	private CSPieChart[] pieChartArr;
	private String[] titleArr;
	private float[] valueArr;
	private Button button;


	@Before
	public void init() {
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(4, true));
		shell.setSize(400, 200);
		initData();
		createPieChart();
		button = createButton(shell, CSButton.SEPARATOR_SLASH, ColorThemeFactory.THEME_PURPLE, true);

	}

	private void initData() {
		pieChartArr = new CSPieChart[4];
		titleArr = new String[] { "구문", "분기", "MC/DC", "함수 호출" };
		valueArr = new float[] { 100, 100, 100, 100 };
	}

	private void createPieChart() {
		for (int i = 0; i < 4; i++) {

			pieChartArr[i] = new CSPieChart(shell, SWT.NONE);
			pieChartArr[i].setLayoutData(new GridData(GridData.FILL_BOTH));
			pieChartArr[i].setForeground(display.getSystemColor(SWT.COLOR_GRAY));
			pieChartArr[i].setTitleColor(display.getSystemColor(SWT.COLOR_GRAY));
			pieChartArr[i].setTitle(titleArr[i]);
			pieChartArr[i].setValue(valueArr[i]);
			pieChartArr[i].setFontSize(9);
			pieChartArr[i].setThickness(13);
//			pieChartArr[i].setOuterCircleColor(SWTGraphicUtil.getColorSafely(200 + i, 156 + i * 10, 120 + i * 20));
			;
		}

	}
	
	private Button createButton(Composite composite, int type, int colorTheme, boolean border) {

		Button button = new Button(composite,SWT.NONE);
//		CSButton button = new CSButton(composite, type, ColorThemeFactory.getColorTheme(colorTheme));
		GridData gridData = new GridData(GridData.CENTER,GridData.CENTER,true,true);
		gridData.horizontalSpan = 4;
		gridData.verticalIndent = 15;
		button.setLayoutData(gridData);
//		button.setBorder(border);
//		button.setButtonImage(new Image(null,
//				CSButton.class.getClassLoader().getResourceAsStream("com/codescroll/widget/button/ButtonImage2.png")));
//		button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.GRAB_HORIZONTAL));
//		button.setButtonText("테스트 실행");
		button.setText("테스트 실행");

		return button;
	}

//	@Test
	public void testEffect() {
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				for (int i = 0; i < 4; i++) {
					pieChartArr[i].initValue();
					pieChartArr[i].setValue(valueArr[i]);
				}
			}
			
		});
	}
	
	@Test
	public void test이전값_정상출력_여부() {
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				for (int i = 0; i < 4; i++) {
					if(valueArr[i]>=100) {
						valueArr[i]=0;
					}
					valueArr[i]+=10.23;
					pieChartArr[i].setValue(valueArr[i]);
				}
			}
			
		});
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
