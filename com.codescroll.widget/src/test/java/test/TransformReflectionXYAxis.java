package test;
import java.math.BigDecimal;
import java.math.MathContext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TransformReflectionXYAxis {
  public static void main(String[] args) {
    final Display display = new Display();

    final Image image = new Image(display, 110, 60);
    GC gc = new GC(image);
    Font font = new Font(display, "Times", 30, SWT.BOLD);
    gc.setFont(font);
    gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
    gc.fillRectangle(0, 0, 110, 60);
    gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
    gc.drawText("SWT", 10, 10, true);
    font.dispose();
    gc.dispose();

    final Rectangle rect = image.getBounds();
    Shell shell = new Shell(display);
    shell.setText("Matrix Tranformations");
    shell.setLayout(new FillLayout());

    final Canvas canvas = new Canvas(shell, SWT.DOUBLE_BUFFERED);
    canvas.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent e) {
        GC gc = e.gc;
        gc.setAdvanced(true);
        if (!gc.getAdvanced()) {
          gc.drawText("Advanced graphics not supported", 30, 30, true);
          return;
        }

        // Original image
        int x = 30, y = 30;
//        gc.drawImage(image, x, y);
//        x += rect.width + 30;

        Transform transform = new Transform(display);

        

		BigDecimal m11 = new BigDecimal(1.0);
		BigDecimal m12 = new BigDecimal(0.0);
		BigDecimal m21 = new BigDecimal(1.0);
		BigDecimal m22 = new BigDecimal(0.0);
		
		
        for(int i= 0; i< 10; i++) {
        	// Reflect around the y axis.
        	
        	m11 = m11.subtract(new BigDecimal(0.2, new MathContext(1)));
			m21 = m21.subtract(new BigDecimal(0.1, new MathContext(1)));
			m22 = m22.add(new BigDecimal(0.1, new MathContext(1)));
        	
        	transform.setElements(-1, 0, 0, 1, 0, 0);
        	gc.setTransform(transform);
        	gc.drawImage(image, -1*(x + rect.width), y);
        	
        }
        
        
        
        x = 30;
        y += rect.height + 30;

        // Reflect around the x axis.
        transform.setElements(1, 0, 0, -1, 0, 0);
        gc.setTransform(transform);
        gc.drawImage(image, x, -1 * y - rect.height);

        transform.dispose();
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