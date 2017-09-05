package test;

import java.math.BigDecimal;
import java.math.MathContext;

import org.apache.commons.lang3.SystemUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ImageTest {
	public static void main(String[] args) {
		final Display display = new Display();

		final Image image = new Image(display,
				"D:\\01.Source\\CodeScrollWidget\\com.codescroll.widget\\src\\test\\java\\test\\next.png");

		final Rectangle rect = image.getBounds();
		Shell shell = new Shell(display);
		shell.setText("Matrix Tranformations");
		shell.setLayout(new FillLayout());
		final Canvas canvas = new Canvas(shell, SWT.DOUBLE_BUFFERED);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Graphics gc = new SWTGraphics(e.gc);
//				
//				gc.setAdvanced(true);
//				if (!gc.getAdvanced()) {
//					gc.drawText("Advanced graphics not supported", 30, 30, true);
//					return;
//				}

				// Original image
				int x = 50, y = 50;
				
				for(float i = 0; i<1; i+=0.1) {
					gc.shear(0.9f, 0.9f);
					gc.drawImage(image, x, y);
					
				}
				x += rect.width + 50;
				
//				Transform transform = new Transform(display);
//
//				// Note that the tranform is applied to the whole GC therefore
//				// the coordinates need to be adjusted too.
//
//				// Reflect around the y axis.
//				transform.setElements(-1, 0, 0, 1, 0, 0);
//				gc.sets
//				gc.drawImage(image, -1 * x - rect.width, y);
//
//				transform = new Transform(display);
//
//				// Note that the tranform is applied to the whole GC therefore
//				// the coordinates need to be adjusted too.
//
//				// Reflect around the y axis
//
//				BigDecimal m11 = new BigDecimal(1.0);
//				BigDecimal m12 = new BigDecimal(0.0);
//				BigDecimal m21 = new BigDecimal(1.0);
//				BigDecimal m22 = new BigDecimal(0.0);
//
//				for (int i = 0; i < 500; i += 50) {
//
//					m11 = m11.subtract(new BigDecimal(0.2, new MathContext(1)));
//					m21 = m21.subtract(new BigDecimal(0.1, new MathContext(1)));
//					m22 = m22.add(new BigDecimal(0.1, new MathContext(1)));
//
//					System.out.println("m11: " + m11.floatValue());
////					System.out.println("m21: " + m21.floatValue());
////					System.out.println("m22: " + m22.floatValue());
//
//					transform.setElements(m11.floatValue(), 0, 0, 1, 0, 0);
//
//					gc.setTransform(transform);
//					System.out.println("width : " + rect.width);
//					gc.drawImage(image, - (x + rect.width), y + rect.height + i);
//
//				}
//				
//				
////				for(int i = 0; i <360; i++) {
////					transform.rotate(i);
////					transform.sh
////					gc.setTransform(transform);
////					gc.drawImage(image, x, y);
////				}
//
//				transform.dispose();
			}
		});

		shell.setSize(350, 550);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		image.dispose();
		display.dispose();
	}
}