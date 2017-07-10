package com.codescroll.widget.progress;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

import com.codescroll.widget.CSWidget;
import com.codescroll.widget.progress.CSStepProgress.StepState;
import com.codescroll.widget.util.SWTGraphicUtil;

public class CircleStep extends CSWidget {

	private static Color FAIL_COLOR = SWTGraphicUtil.getColorSafely(255, 70, 70);
	private static Color DONE_COLOR = SWTGraphicUtil.getColorSafely(142, 187, 141);
	private static Color NORMAL_COLOR = SWTGraphicUtil.getColorSafely(140, 140, 140);
	private static Color INPROGRESS_COLOR = SWTGraphicUtil.getColorSafely(177, 201, 228);
	private static Color CIRCLE_INPROGRESS_COLOR = SWTGraphicUtil.getColorSafely(225, 225, 225);
	private static Color CIRCLE_NORMAL_COLOR = SWTGraphicUtil.getColorSafely(255, 255, 255);
	private static int CIRCLEWIDTH = 70;
	
	private StepState state;
	private StepModel model;
	private int startAngle = 0;
	private int arcAngle = 360;
	private int alpha = 200;
	private Image doneImage;
	private Image image;
	private Font textFont;
	private int flag = -1;
	public CircleStep(Composite paramComposite, StepModel stepModel) {
		super(paramComposite);
		this.state = StepState.NORMAL;
		this.model = stepModel;
		initialize();
	}
	
	private void initialize() {
		doneImage = new Image(null,
				getClass().getClassLoader().getResourceAsStream("com/codescroll/widget/progress/done.png"));
		image = new Image(null,
				getClass().getClassLoader().getResourceAsStream("com/codescroll/widget/progress/nomal.png"));
		Font initialFont = getFont();
		FontData[] fontData = initialFont.getFontData();
		for (int i = 0; i < fontData.length; i++) {
			fontData[i].setStyle(SWT.BOLD);
		}
		
		textFont = new Font(getDisplay(), fontData);
	}

	@Override
	protected void drawWidget(GC gc) {
		gc.setTextAntialias(SWT.ON);
		Point stringExtent = gc.stringExtent(model.getStepName());
		int x = stringExtent.x;
		gc.setAntialias(SWT.ON);
		
		if (this.state == StepState.INPROGRESS){
			gc.setAlpha(alpha);
		} 
		if (this.state == StepState.INPROGRESS){
			gc.setBackground(INPROGRESS_COLOR);
		} else if (this.state == StepState.NORMAL) {
			gc.setBackground(NORMAL_COLOR);
		} else if (this.state == StepState.FAIL) {
			gc.setBackground(FAIL_COLOR);
		} else if (this.state == StepState.COMPLETE) {
			gc.setBackground(DONE_COLOR);
		}
		
		gc.fillArc(30, 10, CIRCLEWIDTH, CIRCLEWIDTH, startAngle, arcAngle);
		gc.setAlpha(255);
		if (this.state == StepState.INPROGRESS){
			gc.setBackground(CIRCLE_INPROGRESS_COLOR);
		} else {
			gc.setBackground(CIRCLE_NORMAL_COLOR);
		}
		gc.fillArc(34, 14, CIRCLEWIDTH-8, CIRCLEWIDTH-8, 0, 360);
		
		
	    if (this.state == StepState.COMPLETE){
		    Rectangle bounds = doneImage.getBounds();
		    gc.drawImage(doneImage, (CIRCLEWIDTH / 2) - (bounds.width/2) + 28, (CIRCLEWIDTH / 2) - (bounds.height/2) + 10);
	    } else {
	    	 Rectangle bounds = image.getBounds();
			 gc.drawImage(image, (CIRCLEWIDTH / 2) - (bounds.width/2) + 28, (CIRCLEWIDTH / 2) - (bounds.height/2) + 10);
	    }

	    gc.setFont(textFont);
	    gc.setForeground(NORMAL_COLOR);
		gc.drawText(model.getStepName(), (CIRCLEWIDTH / 2) - (x/2) + 28, CIRCLEWIDTH + 20, true);
	}

	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		return super.computeSize(140, 120, changed);
	}
	
	@Override
	public void dispose() {
		doneImage.dispose();
		image.dispose();
		super.dispose();
	}
	
	public void setState(StepState stepState) {
		this.state = stepState;
	}

	public StepState getState() {
		return state;
	}
	
	public void animation() {
		
		alpha += (flag * 5);
		
		if ( alpha <= 50 || alpha >= 200) {
			flag = flag * -1;
		}
		redraw();
	}
	
}
