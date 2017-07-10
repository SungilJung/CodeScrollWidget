package com.codescroll.widget.progress;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.codescroll.widget.CSWidget;
import com.codescroll.widget.button.CSButton;

public class CSStepProgressDialog extends Dialog {

	private static final String INFO = "MESSAGE-INFO";
	private static final String ERROR = "MESSAGE-ERROR";
	private static final String WARNING = "MESSAGE-WARNING";
	private static final String MESSAGE_FORMAT = "[%s] %s \n";
	
	private CSStepProgress csStepProgress;
	private StepModel[] steps;
	private CSProgressMonitor progressMonitor;
	private Image imageOK;
	private Image imageCancel;
	private Image imageDown;
	private Image imageUp;
	private Composite messageComposite;
	private CSButton buttonOK;
	private int showMessageFlag = 1;
	private StyledText messageText;
	private Shell shell;
	public CSStepProgressDialog(Shell shell, StepModel[] steps) {
		super(shell);
		this.steps = steps;
		progressMonitor = new CSProgressMonitor();
		imageOK = new Image(null,
				getClass().getClassLoader().getResourceAsStream("com/codescroll/widget/progress/ok.png"));
		imageCancel = new Image(null,
				getClass().getClassLoader().getResourceAsStream("com/codescroll/widget/progress/cancel.png"));
		imageDown = new Image(null,
				getClass().getClassLoader().getResourceAsStream("com/codescroll/widget/progress/down_arrow.png"));
		imageUp = new Image(null,
				getClass().getClassLoader().getResourceAsStream("com/codescroll/widget/progress/up_arrow.png"));
	}
	
	
	public void run(IRunnableWithCSProgress runnable) {
		
		shell = new Shell(getParent().getDisplay(),SWT.CLOSE | SWT.APPLICATION_MODAL);
		shell.setImage(getParent().getImage());
		shell.setLayout(new GridLayout(1, false));
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent paramDisposeEvent) {
				imageOK.dispose();
				imageCancel.dispose();
				imageDown.dispose();
				imageUp.dispose();
			}
		});
		csStepProgress = new CSStepProgress(shell, steps);
		createMessageComposite(shell);
		
		Label separator = new Label(shell, SWT.H_SCROLL | SWT.SEPARATOR);
		separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		createButtonComposite(shell);
		shell.pack();
		shell.open();
		
		runnable.run(progressMonitor);
	}
	
	
	protected Composite createMessageComposite(Composite parent) {
		messageComposite = new Composite(parent, SWT.NONE);
		messageComposite.setLayout(getGridLayout(1));
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = 0;
		messageComposite.setLayoutData(gridData);
		messageText = new StyledText(messageComposite, SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		messageText.setLayoutData(new GridData(GridData.FILL_BOTH));
		messageText.addListener(SWT.Modify, new Listener() {
			
			@Override
			public void handleEvent(Event paramEvent) {
				messageText.setTopIndex(messageText.getLineCount() - 1);
			}
		});
		return messageComposite;
	}

	protected Composite createButtonComposite(Composite parent) {
		Composite buttonComposite = new Composite(parent, SWT.NONE);
		buttonComposite.setLayout(getGridLayout(3));
		buttonComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final CSButton buttonDown = new CSButton(buttonComposite, CSWidget.NONE);
		buttonDown.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_BEGINNING));
		buttonDown.setBorder(true);
		buttonDown.setButtonImage(imageDown);
		buttonDown.setButtonText("메시지 보기");
		buttonDown.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				if (showMessageFlag == 1) {
					buttonDown.setButtonImage(imageUp);
					buttonDown.setButtonText("메시지 숨기기");
				} else {
					buttonDown.setButtonImage(imageDown);
					buttonDown.setButtonText("메시지 보기");
				}
				getDisplay().timerExec(30, new Runnable() {
					
					@Override
					public void run() {
						Point size = shell.getSize();
						int tempH = size.y + (showMessageFlag * 50);
						shell.setSize(size.x, tempH);
						shell.redraw();
						if (messageText.getSize().y < 250 && messageText.getSize().y > 0) {
							getDisplay().timerExec(30, this);
						} else {
							showMessageFlag = -1 * showMessageFlag;
						}
					}
				});
			}
		});
		buttonOK = new CSButton(buttonComposite, CSWidget.NONE);
		buttonOK.setBorder(true);
		buttonOK.setButtonImage(imageOK);
		buttonOK.setButtonText("확인");
		buttonOK.setEnabled(false);
		CSButton buttonCancel = new CSButton(buttonComposite, CSWidget.NONE);
		buttonCancel.setBorder(true);
		buttonCancel.setButtonImage(imageCancel);
		buttonCancel.setButtonText("취소");
		buttonCancel.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent) {
				shell.close();
			}
		});
		return buttonComposite;
	}
	

	private void appendInfoMessage(String message) {
		messageText.append(String.format(MESSAGE_FORMAT, INFO, message));
	}
	
	private void appendErrorMessage(String message) {
		String error = String.format(MESSAGE_FORMAT, ERROR, message);
		StyleRange errorStyleRange = new StyleRange();
	    errorStyleRange.start = messageText.getText().length();
	    errorStyleRange.length = error.length();
	    errorStyleRange.foreground = getDisplay().getSystemColor(SWT.COLOR_RED);
		messageText.append(error);
		messageText.setStyleRange(errorStyleRange);
	}
	
	private void appendWarningMessage(String message) {
		String warning = String.format(MESSAGE_FORMAT, WARNING, message);
		StyleRange warningStyleRange = new StyleRange();
	    warningStyleRange.start = messageText.getText().length();
	    warningStyleRange.length = warning.length();
	    warningStyleRange.foreground = getDisplay().getSystemColor(SWT.COLOR_DARK_YELLOW);
		messageText.append(warning);
		messageText.setStyleRange(warningStyleRange);
	}
	
	private GridLayout getGridLayout(int columnNum) {
		GridLayout gridLayout = new GridLayout(columnNum, false);
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		return gridLayout;
	}
	
	private Display getDisplay() {
		return shell.getDisplay();
	}
	
	public class CSProgressMonitor {
		private CSProgressMonitor () {}
		
		public void infoMessage(String message) {
			appendInfoMessage(message);
		}
		public void errorMessage(String message) {
			appendErrorMessage(message);
		}
		public void warningMessage(String message) {
			appendWarningMessage(message);
		}
		public void start() {
			buttonOK.setEnabled(false);
			csStepProgress.start();
		}
		public void done() {
			buttonOK.setEnabled(true);
			csStepProgress.done();
		}
		public void next() {
			csStepProgress.next();
		}
		public void fail() {
			buttonOK.setEnabled(true);
			csStepProgress.fail();
		}
	}
}
