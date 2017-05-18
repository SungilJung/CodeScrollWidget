package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

public class CSPieChart extends Composite{

	private CSPie pie;
	private Label titleLabel;
	
	public CSPieChart(Composite parent, int style) {
		super(parent, style);
		super.setLayout(createLayout());
		
		this.setBackgroundMode(SWT.INHERIT_FORCE);
		
		createPie();
		createTitleLabel();
		
		setFont(new Font(getDisplay(), "Arial", 12, SWT.BOLD));

	}

	private Layout createLayout(){
		GridLayout layout = new GridLayout(1,true);
		layout.verticalSpacing = 0;
		layout.marginBottom = 0;
		layout.marginTop = 0;
		layout.marginLeft = 0;
		layout.marginTop = 0;
		return layout;
	}
	
	private void createPie(){
		pie = new CSPie(this);
		pie.setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	
	private void createTitleLabel(){
		titleLabel = new Label(this, SWT.CENTER | SWT.BOLD);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.CENTER);
		titleLabel.setLayoutData(gridData);
		
	}
	
	

	
	public void setTitle(String title){
		checkWidget();
		titleLabel.setText(title);
	}
	
	public void setValue(int value){
		checkWidget();
		pie.setValue(value); 
	}
	
	@Override
	public void setForeground(Color color) {
		checkWidget();
		pie.setForeground(color);
		titleLabel.setForeground(color);
	}
	
	@Override
	public void setBackground(Color color) {
		checkWidget();
		pie.setBackground(color);
		titleLabel.setBackground(color);
	}
	
	@Override
	public void setFont(Font font) {
		checkWidget();
		pie.setFont(font);
		titleLabel.setFont(font);
	}
	
	@Override
	public void setLayout(Layout layout) {
	}
}
