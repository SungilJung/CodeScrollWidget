package com.codescroll.widget.progress;

import com.codescroll.widget.progress.CSStepProgressDialog.CSProgressMonitor;

public interface IRunnableWithCSProgress {
	public void run(CSProgressMonitor messageMonitor);
}
