package com.codescroll.widget.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

import com.codescroll.widget.util.SWTGraphicUtil;

public class CSPieChart extends Composite{

	private CSPie pie;
	private Label titleLabel;
	
	public CSPieChart(Composite parent, int style) {
		super(parent, style);
		super.setLayout(createLayout());
		
		this.setBackgroundMode(SWT.INHERIT_FORCE);
		
		createPie();
		createTitleLabel();
		
		setFont(new Font(getDisplay(), "Arial", 10, SWT.BOLD));

	}

	private Layout createLayout(){
		GridLayout layout = new GridLayout(1,true);
		layout.verticalSpacing = 0;
		layout.marginBottom = 0;
		layout.marginTop = 0;
		layout.marginLeft = 0;
		layout.marginTop = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
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
	
	public void setValue(Float value){
		checkWidget();
		pie.setValue(value); 
	}
	
	@Override
	public void setForeground(Color color) {
		checkWidget();
		pie.setForeground(color);
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
	
	public void setTtitleFont(Font font) {
		checkWidget();
		titleLabel.setFont(font);
	}
	
	public void setPieFont(Font font) {
		checkSubclass();
		pie.setFont(font);
	}
	
	public void setTitleFontSize(int size) {
		checkWidget();
		FontData fontData = titleLabel.getFont().getFontData()[0];
		fontData.setHeight(size);
		Font font = new Font(getDisplay(), fontData);
		titleLabel.setFont(font);
	}
	
	public void setPieFontSize(int size) {
		checkWidget();
		FontData fontData = pie.getFont().getFontData()[0];
		fontData.setHeight(size);
		Font font = new Font(getDisplay(), fontData);
		pie.setFont(font);
	}
	
	public void setFontSize(int size) {
		checkWidget();
		FontData fontData = getFont().getFontData()[0];
		fontData.setHeight(size);
		Font font = new Font(getDisplay(), fontData);
		setFont(font);
	}
	
	public void setTitleColor(Color color){
		checkWidget();
		titleLabel.setForeground(color);
	}
	public void setOuterCircleColor(Color color) {
		pie.setOuterCircleColor(color);
	}

	public void setInnerCircleColor(Color color) {
		pie.setInnerCircleColor(color);
	}
	
	@Override
	public void setLayout(Layout layout) {
	}
	
	public void initValue() {
		checkWidget();
		pie.initState();
	}
	
	public void setThickness(int thickness) {
		pie.setThickness(thickness);
	}
	
}
