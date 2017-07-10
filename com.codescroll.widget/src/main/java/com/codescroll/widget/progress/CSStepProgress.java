package com.codescroll.widget.progress;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class CSStepProgress extends Composite {
	
	enum StepState {
		NORMAL, INPROGRESS, FAIL, COMPLETE
	}
	
	private StepModel[] stepModels;
	private int currentStep = 0;
	private boolean isStart = false;
	private CircleStep[] addSteps;

	
	public CSStepProgress(Composite parent, StepModel[] steps) {
		super(parent, SWT.NONE);
		this.stepModels = steps;
	
		createProgress();
	}
	
	private void createProgress() {
		setLayout(getGridLayout(1));
		setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Composite stepComposite = new Composite(this, SWT.NONE);
		stepComposite.setLayout(getGridLayout(stepModels.length));
		addSteps = new CircleStep[stepModels.length];
		for (int i = 0; i < stepModels.length; i++) {
			CircleStep circleStep = new CircleStep(stepComposite, stepModels[i]);
			addSteps[i] = circleStep;
		}
	}
	
	private GridLayout getGridLayout(int columnNum) {
		GridLayout gridLayout = new GridLayout(columnNum, false);
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		return gridLayout;
	}
	
	public void start() {
		if (!isStart) {
			isStart = true;
			this.currentStep = 0;
			addSteps[currentStep].setState(StepState.INPROGRESS);
		}
		getDisplay().timerExec(25, new Runnable() {
			
			@Override
			public void run() {
				if (!isDisposed() && isStart) {
					addSteps[currentStep].animation();
					if (isStart) {
						getDisplay().timerExec(25, this);
					}
				}
			}
		});
		
	}
	
	protected void done() {
		isStart = false;
	}
	
	protected void next() {
		if (isStart) {
			addSteps[currentStep].setState(StepState.COMPLETE);
			int nextStep = currentStep + 1;
			if (nextStep >= stepModels.length) {
				addSteps[currentStep].setState(StepState.COMPLETE);
				addSteps[currentStep].redraw();
				isStart = false;
			} else {
				addSteps[currentStep].redraw();
				addSteps[++currentStep].setState(StepState.INPROGRESS);
			}
		}
	}
	
	protected void fail() {
		if (isStart) {
			addSteps[currentStep].setState(StepState.FAIL);
			addSteps[currentStep].redraw();
			isStart = false;
		}
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
	}
}
