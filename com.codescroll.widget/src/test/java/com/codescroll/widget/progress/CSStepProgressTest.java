package com.codescroll.widget.progress;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.codescroll.widget.progress.CSStepProgressDialog;
import com.codescroll.widget.progress.CSStepProgressDialog.CSProgressMonitor;
import com.codescroll.widget.progress.IRunnableWithCSProgress;
import com.codescroll.widget.progress.StepModel;

public class CSStepProgressTest {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		Button btn = new Button(shell, SWT.PUSH);
		Text text = new Text(shell, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		btn.setText("테스트");
		btn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				// TODO Auto-generated method stub
				
				StepModel[] models = {new StepModel("분석"), new StepModel("컴파일"), new StepModel("테스트"), new StepModel("커버리지 재계산")};
				CSStepProgressDialog di = new CSStepProgressDialog(shell, models);
				di.run(new IRunnableWithCSProgress() {
					
					@Override
					public void run(CSProgressMonitor messageMonitor) {
						messageMonitor.start();
						messageMonitor.infoMessage("일반 메시지");
						messageMonitor.errorMessage("에러 메시지");
						messageMonitor.warningMessage("경고 메시지");
						messageMonitor.next();
					}
					
				});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {
				
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
}
