/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.codescroll.widget.combo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TypedListener;

import com.codescroll.widget.CSWidget;

public class CCCombo extends Composite {
//	Text text;
	List list;
	int visibleItemCount = 5;
	Shell popup;
//	Button arrow;
	boolean hasFocus;
	Listener listener;
	Color foreground;
	Color background;
	Font font;
	Shell _shell = super.getShell();
	private String[] localObject;
	private int i;
	private int i1;
	ShowButton showButton;
	public CCCombo(Composite paramComposite, int paramInt) {
		super(paramComposite, SWT.NONE);
//		int i = SWT.SINGLE;
//		if ((paramInt & SWT.READ_ONLY) != 0)
//			i |= SWT.READ_ONLY;
//		if ((paramInt & 0x800000) != 0)
//			i |= 8388608;
//		this.text = new Text(this, i);
//		int j = 1028;
//		if ((paramInt & 0x800000) != 0)
//			j |= 8388608;
//		this.arrow = new Button(this, j);
		setBackground(new Color(null, 255,255,255));
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		setLayout(gridLayout);
		showButton = new ShowButton(this, "구문 커버리지");
		
		this.listener = new Listener() {
			public void handleEvent(Event paramEvent) {
				if (CCCombo.this.isDisposed())
					return;
				if (CCCombo.this.popup == paramEvent.widget) {
					CCCombo.this.popupEvent(paramEvent);
					return;
				}
//				if (CCCombo.this.text == paramEvent.widget) {
//					CCCombo.this.textEvent(paramEvent);
//					return;
//				}
//				if (CCCombo.this.list == paramEvent.widget) {
//					CCCombo.this.listEvent(paramEvent);
//					return;
//				}
				System.out.println(paramEvent.type);
				if (CCCombo.this.showButton == paramEvent.widget) {
					System.out.println(showButton.isFocusControl());
					CCCombo.this.arrowEvent(paramEvent);
					return;
				}
				if (CCCombo.this == paramEvent.widget) {
					CCCombo.this.comboEvent(paramEvent);
					return;
				}
				if (CCCombo.this.getShell() != paramEvent.widget)
					return;
				CCCombo.this.getDisplay().asyncExec(new Runnable() {
					public void run() {
						if (CCCombo.this.isDisposed())
							return;
						CCCombo.this.handleFocus(16);
					}
				});
			}
		};
		int[] arrayOfInt1 = { 12, 15, 10, 11 };
		for (int k = 0; k < arrayOfInt1.length; ++k)
			addListener(arrayOfInt1[k], this.listener);
//		this.showButton.addListener(SWT.MouseUp, this.listener);
		int[] arrayOfInt3 = { 29, 3, 6, 7, 32, 5, 4, 37, 13, 15 };
		for (int i1 = 0; i1 < arrayOfInt3.length; ++i1)
			this.showButton.addListener(arrayOfInt3[i1], this.listener);
		addListener(SWT.MouseUp, this.listener);
		createPopup(null, -1);
		if ((paramInt & 0x40) == 0) {
			i1 = this.list.getItemHeight();
			if (i1 != 0) {
				int i2 = getMonitor().getClientArea().height / 3;
				this.visibleItemCount = Math.max(this.visibleItemCount, i2 / i1);
			}
		}
	}

	
	void comboEvent(Event paramEvent) {
		switch (paramEvent.type) {
		case 12:
			removeListener(12, this.listener);
			notifyListeners(12, paramEvent);
			paramEvent.type = 0;
			if ((this.popup != null) && (!(this.popup.isDisposed()))) {
				this.list.removeListener(12, this.listener);
				this.popup.dispose();
			}
			Shell localShell = getShell();
			Display localDisplay = getDisplay();
			this.popup = null;
			this.list = null;
			this.showButton = null;
			this._shell = null;
			break;
		case 15:
			Control localControl = getDisplay().getFocusControl();
			if ((localControl == this.showButton) || (localControl == this.list))
				return;
			if (isDropped()) {
				this.list.setFocus();
				return;
			}
			break;
		case 10:
			dropDown(false);
			break;
		}
	}
	
	public void add(String paramString) {
		checkWidget();
		if (paramString == null)
			SWT.error(4);
		this.list.add(paramString);
	}

	public void add(String paramString, int paramInt) {
		checkWidget();
		if (paramString == null)
			SWT.error(4);
		this.list.add(paramString, paramInt);
	}

	public void addSelectionListener(SelectionListener paramSelectionListener) {
		checkWidget();
		if (paramSelectionListener == null)
			SWT.error(4);
		TypedListener localTypedListener = new TypedListener(paramSelectionListener);
		addListener(13, localTypedListener);
		addListener(14, localTypedListener);
	}

	void arrowEvent(Event paramEvent) {
		switch (paramEvent.type) {
		case 4:
			this.showButton.setFocus();
			dropDown(!(isDropped()));
		}
	}
	
	public void clearSelection() {
		checkWidget();
		this.showButton.setText("");
		this.list.deselectAll();
	}


//	public Point computeSize(int paramInt1, int paramInt2, boolean paramBoolean) {
//		checkWidget();
//		int i = 0;
//		int j = 0;
//		String[] arrayOfString = this.list.getItems();
//		GC localGC = new GC(this.text);
//		int k = localGC.stringExtent(" ").x;
//		int l = localGC.stringExtent(this.text.getText()).x;
//		for (int i1 = 0; i1 < arrayOfString.length; ++i1)
//			l = Math.max(localGC.stringExtent(arrayOfString[i1]).x, l);
//		localGC.dispose();
//		Point localPoint1 = this.text.computeSize(-1, -1, paramBoolean);
//		Point localPoint2 = this.arrow.computeSize(-1, -1, paramBoolean);
//		Point localPoint3 = this.list.computeSize(-1, -1, paramBoolean);
//		int i2 = getBorderWidth();
//		j = Math.max(localPoint1.y, localPoint2.y);
//		i = Math.max(l + 2 * k + localPoint2.x + 2 * i2, localPoint3.x);
//		if (paramInt1 != -1)
//			i = paramInt1;
//		if (paramInt2 != -1)
//			j = paramInt2;
//		return new Point(i + 2 * i2, j + 2 * i2);
//	}

//	public void copy() {
//		checkWidget();
//		this.text.copy();
//	}

	void createPopup(String[] paramArrayOfString, int paramInt) {
		this.popup = new Shell(getShell(), 16392);
		int i = getStyle();
		int j = 772;
		if ((i & 0x800000) != 0)
			j |= 8388608;
		if ((i & 0x4000000) != 0)
			j |= 67108864;
		if ((i & 0x2000000) != 0)
			j |= 33554432;
		this.list = new List(this.popup, j);
		if (this.font != null)
			this.list.setFont(this.font);
		if (this.foreground != null)
			this.list.setForeground(this.foreground);
		if (this.background != null)
			this.list.setBackground(this.background);
		int[] arrayOfInt1 = { 21, 9 };
//		for (int k = 0; k < arrayOfInt1.length; ++k)
//			this.popup.addListener(arrayOfInt1[k], this.listener);
//		int[] arrayOfInt2 = { 4, 13, 31, 1, 2, 15, 16, 12 };
//		for (int l = 0; l < arrayOfInt2.length; ++l)
//			this.list.addListener(arrayOfInt2[l], this.listener);
		if (paramArrayOfString != null)
			this.list.setItems(paramArrayOfString);
		if (paramInt == -1)
			return;
		this.list.setSelection(paramInt);
	}

	public void deselect(int paramInt) {
		checkWidget();
		if ((0 > paramInt) || (paramInt >= this.list.getItemCount()) || (paramInt != this.list.getSelectionIndex())
				|| (!(this.showButton.getText().equals(this.list.getItem(paramInt)))))
			return;
		this.showButton.setText("");
		this.list.deselect(paramInt);
	}

	public void deselectAll() {
		checkWidget();
		this.showButton.setText("");
		this.list.deselectAll();
	}

	void dropDown(boolean paramBoolean) {
		if (paramBoolean == isDropped())
			return;
		Display localDisplay = getDisplay();
		if (!(paramBoolean)) {
			this.popup.setVisible(false);
			if ((!(isDisposed())) && (isFocusControl()))
				this.showButton.setFocus();
			return;
		}
		if (!(isVisible()))
			return;
		if (getShell() != this.popup.getParent()) {
			localObject = this.list.getItems();
			i = this.list.getSelectionIndex();
			this.popup.dispose();
			this.popup = null;
			this.list = null;
			createPopup(localObject, i);
		}
		Object localObject = getSize();
		int i = this.list.getItemCount();
		i = (i == 0) ? this.visibleItemCount : Math.min(this.visibleItemCount, i);
		int j = this.list.getItemHeight() * i;
		Point localPoint = this.list.computeSize(-1, j, false);
		Rectangle localRectangle1 = getMonitor().getClientArea();
		this.list.setBounds(1, 1,
				Math.max(((Point) localObject).x - 2, Math.min(localPoint.x, localRectangle1.width - 2)), localPoint.y);
		int k = this.list.getSelectionIndex();
		if (k != -1)
			this.list.setTopIndex(k);
		Rectangle localRectangle2 = this.list.getBounds();
		Rectangle localRectangle3 = localDisplay.map(getParent(), null, getBounds());
		int l = localRectangle2.width + 2;
		int i1 = localRectangle2.height + 2;
		int i2 = localRectangle3.x;
		if (i2 + l > localRectangle1.x + localRectangle1.width)
			i2 = localRectangle1.x + localRectangle1.width - l;
		int i3 = localRectangle3.y + ((Point) localObject).y;
		if (i3 + i1 > localRectangle1.y + localRectangle1.height) {
			int i4 = (localRectangle3.y - i1 < localRectangle1.y) ? localRectangle3.y - localRectangle1.y : i1;
			int i5 = localRectangle1.y + localRectangle1.height - i3;
			if (i4 > i5) {
				i1 = i4;
				i3 = localRectangle3.y - i4;
			} else {
				i1 = i5;
			}
			this.list.setSize(localRectangle2.width, i1 - 2);
		}
		this.popup.setBounds(i2, i3, l, i1);
		this.popup.setVisible(true);
		if (isFocusControl())
			this.list.setFocus();
//		localDisplay.removeFilter(13, this.filter);
//		localDisplay.addFilter(13, this.filter);
	}


	public String getItem(int paramInt) {
		checkWidget();
		return this.list.getItem(paramInt);
	}

	public int getItemCount() {
		checkWidget();
		return this.list.getItemCount();
	}

	public int getItemHeight() {
		checkWidget();
		return this.list.getItemHeight();
	}

	public String[] getItems() {
		checkWidget();
		return this.list.getItems();
	}

	public boolean getListVisible() {
		checkWidget();
		return isDropped();
	}

	public int getSelectionIndex() {
		checkWidget();
		return this.list.getSelectionIndex();
	}

	public Shell getShell() {
		checkWidget();
		Shell localShell = super.getShell();
		if (localShell != this._shell) {
//			if ((this._shell != null) && (!(this._shell.isDisposed())))
//				this._shell.removeListener(27, this.listener);
			this._shell = localShell;
		}
		return this._shell;
	}

	public String getText() {
		checkWidget();
		return this.showButton.getText();
	}

	public int getVisibleItemCount() {
		checkWidget();
		return this.visibleItemCount;
	}

	void handleFocus(int paramInt) {
		Object localObject1;
		Object localObject2;
		Object localObject3;
		switch (paramInt) {
		case 15:
			if (this.hasFocus)
				return;
			this.hasFocus = true;
			localObject1 = getShell();
			localObject2 = getDisplay();
			localObject3 = new Event();
			notifyListeners(15, (Event) localObject3);
			break;
		case 16:
			if (!(this.hasFocus))
				return;
			localObject1 = getDisplay().getFocusControl();
			if ((localObject1 == this.showButton) || (localObject1 == this.list))
				return;
			this.hasFocus = false;
			localObject2 = getShell();
			((Shell) localObject2).removeListener(27, this.listener);
			localObject3 = getDisplay();
			Event localEvent = new Event();
			notifyListeners(16, localEvent);
		}
	}

	boolean isDropped() {
		return ((!(isDisposed())) && (this.popup.getVisible()));
	}

	public boolean isFocusControl() {
		checkWidget();
		if ((this.showButton.isFocusControl()) || (this.list.isFocusControl())
				|| (this.popup.isFocusControl()))
			return true;
		return super.isFocusControl();
	}


//	void listEvent(Event paramEvent) {
//		Object localObject1;
//		Object localObject2;
//		Event localEvent;
//		switch (paramEvent.type) {
//		case 12:
//			if (getShell() == this.popup.getParent())
//				return;
//			localObject1 = this.list.getItems();
//			int j = this.list.getSelectionIndex();
//			this.popup = null;
//			this.list = null;
//			createPopup((String[])localObject1, j);
//			break;
//		case 15:
//			handleFocus(15);
//			break;
//		case 16:showButton
//			if (!("carbon".equals(SWT.getPlatform()))) {
//				localObject1 = this.arrow.toControl(getDisplay().getCursorLocation());
//				localObject2 = this.arrow.getSize();
//				Rectangle localRectangle = new Rectangle(0, 0, ((Point) localObject2).x, ((Point) localObject2).y);
//				if (localRectangle.contains((Point) localObject1)) {
//					int k = (getDisplay().getActiveShell() == getShell()) ? 1 : 0;
//					if (k != 0)
//						return;
//					dropDown(false);
//					return;
//				}
//			}
//			dropDown(false);
//			break;
//		case 4:
//			if (paramEvent.button != 1)
//				return;
//			dropDown(false);
//			break;
//		case 13:
//			int i = this.list.getSelectionIndex();
//			if (i == -1)
//				return;
//			this.text.setText(this.list.getItem(i));
//			this.text.selectAll();
//			this.list.setSelection(i);
//			localObject2 = new Event();
//			((Event) localObject2).time = paramEvent.time;
//			((Event) localObject2).stateMask = paramEvent.stateMask;
//			((Event) localObject2).doit = paramEvent.doit;
//			notifyListeners(13, (Event) localObject2);
//			paramEvent.doit = ((Event) localObject2).doit;
//			break;
//		case 31:
//			switch (paramEvent.detail) {
//			case 2:
//			case 4:
//			case 32:
//			case 64:
//				paramEvent.doit = false;
//				break;
//			case 8:
//			case 16:
//				paramEvent.doit = this.text.traverse(paramEvent.detail);
//				paramEvent.detail = 0;
//				if (paramEvent.doit)
//					dropDown(false);
//				return;
//			}
//			localEvent = new Event();
//			localEvent.time = paramEvent.time;
//			localEvent.detail = paramEvent.detail;
//			localEvent.doit = paramEvent.doit;
//			localEvent.character = paramEvent.character;
//			localEvent.keyCode = paramEvent.keyCode;
//			localEvent.keyLocation = paramEvent.keyLocation;
//			notifyListeners(31, localEvent);
//			paramEvent.doit = localEvent.doit;
//			paramEvent.detail = localEvent.detail;
//			break;
//		case 2:
//			localEvent = new Event();
//			localEvent.time = paramEvent.time;
//			localEvent.character = paramEvent.character;
//			localEvent.keyCode = paramEvent.keyCode;
//			localEvent.keyLocation = paramEvent.keyLocation;
//			localEvent.stateMask = paramEvent.stateMask;
//			notifyListeners(2, localEvent);
//			paramEvent.doit = localEvent.doit;
//			break;
//		case 1:
//			if (paramEvent.character == '\27')
//				dropDown(false);
//			if (((paramEvent.stateMask & 0x10000) != 0)
//					&& (((paramEvent.keyCode == 16777217) || (paramEvent.keyCode == 16777218))))
//				dropDown(false);
//			if (paramEvent.character == '\r') {
//				dropDown(false);
//				localEvent = new Event();
//				localEvent.time = paramEvent.time;
//				localEvent.stateMask = paramEvent.stateMask;
//				notifyListeners(14, localEvent);
//			}
//			if (isDisposed())
//				return;
//			localEvent = new Event();
//			localEvent.time = paramEvent.time;
//			localEvent.character = paramEvent.character;
//			localEvent.keyCode = paramEvent.keyCode;
//			localEvent.keyLocation = paramEvent.keyLocation;
//			localEvent.stateMask = paramEvent.stateMask;
//			notifyListeners(1, localEvent);
//			paramEvent.doit = localEvent.doit;
//		}
//	}

//	public void paste() {
//		checkWidget();
//		this.text.paste();
//	}

	void popupEvent(Event paramEvent) {
		switch (paramEvent.type) {
		case 9:
			Rectangle localRectangle = this.list.getBounds();
			Color localColor = getDisplay().getSystemColor(2);
			paramEvent.gc.setForeground(localColor);
			paramEvent.gc.drawRectangle(0, 0, localRectangle.width + 1, localRectangle.height + 1);
			break;
		case 21:
			paramEvent.doit = false;
//			dropDown(false);
		}
	}

	public void redraw() {
		super.redraw();
//		this.text.redraw();
		this.showButton.redraw();
		if (!(this.popup.isVisible()))
			return;
		this.list.redraw();
	}

	public void remove(int paramInt) {
		checkWidget();
		this.list.remove(paramInt);
	}

	public void remove(int paramInt1, int paramInt2) {
		checkWidget();
		this.list.remove(paramInt1, paramInt2);
	}

	public void remove(String paramString) {
		checkWidget();
		if (paramString == null)
			SWT.error(4);
		this.list.remove(paramString);
	}

	public void removeAll() {
		checkWidget();
		this.showButton.setText("");
		this.list.removeAll();
	}

	public void removeModifyListener(ModifyListener paramModifyListener) {
		checkWidget();
		if (paramModifyListener == null)
			SWT.error(4);
		removeListener(24, paramModifyListener);
	}

	public void removeSelectionListener(SelectionListener paramSelectionListener) {
		checkWidget();
		if (paramSelectionListener == null)
			SWT.error(4);
		removeListener(13, paramSelectionListener);
		removeListener(14, paramSelectionListener);
	}

	public void removeVerifyListener(VerifyListener paramVerifyListener) {
		checkWidget();
		if (paramVerifyListener == null)
			SWT.error(4);
		removeListener(25, paramVerifyListener);
	}

	public void select(int paramInt) {
		checkWidget();
		if (paramInt == -1) {
			this.list.deselectAll();
			this.showButton.setText("");
			return;
		}
//		if ((0 > paramInt) || (paramInt >= this.list.getItemCount()) || (paramInt == getSelectionIndex()))
//			return;
		this.showButton.setText(this.list.getItem(paramInt));
//		this.text.selectAll();
		this.list.select(paramInt);
		this.list.showSelection();
	}
////
////	public void setBackground(Color paramColor) {
////		super.setBackground(paramColor);
////		this.background = paramColor;
////		if (this.text != null)
////			this.text.setBackground(paramColor);
////		if (this.list != null)
////			this.list.setBackground(paramColor);
////		if (this.arrow == null)
////			return;
////		this.arrow.setBackground(paramColor);
////	}
////
////	public void setEditable(boolean paramBoolean) {
////		checkWidget();
////		this.text.setEditable(paramBoolean);
////	}
//
////	public void setEnabled(boolean paramBoolean) {
////		super.setEnabled(paramBoolean);
////		if (this.popup != null)
////			this.popup.setVisible(false);
////		if (this.text != null)
////			this.text.setEnabled(paramBoolean);
////		if (this.arrow == null)
////			return;
////		this.arrow.setEnabled(paramBoolean);
////	}
//
	public void setItem(int paramInt, String paramString) {
		checkWidget();
		this.list.setItem(paramInt, paramString);
	}

	public void setItems(String[] paramArrayOfString) {
		checkWidget();
		this.list.setItems(paramArrayOfString);
//		if (this.text.getEditable())
//			return;
//		this.text.setText("");
	}
//
////	public void setMenu(Menu paramMenu) {
////		this.text.setMenu(paramMenu);
////	}
//
////	public void setSelection(Point paramPoint) {
////		checkWidget();
////		if (paramPoint == null)
////			SWT.error(4);
////		this.text.setSelection(paramPoint.x, paramPoint.y);
////	}
//
	public void setText(String paramString) {
		checkWidget();
		if (paramString == null)
			SWT.error(4);
		int i = this.list.indexOf(paramString);
		if (i == -1) {
			this.list.deselectAll();
			this.showButton.setText(paramString);
			return;
		}
//		this.text.setText(paramString);
//		this.text.selectAll();
		this.list.setSelection(i);
		this.list.showSelection();
	}
//
////	public void setTextLimit(int paramInt) {
////		checkWidget();
////		this.text.setTextLimit(paramInt);
////	}
//
////	public void setToolTipText(String paramString) {
////		checkWidget();
////		super.setToolTipText(paramString);
////		this.arrow.setToolTipText(paramString);
////		this.text.setToolTipText(paramString);
////	}
//
	public void setVisible(boolean paramBoolean) {
		super.setVisible(paramBoolean);
		if (isDisposed())
			return;
		if ((this.popup == null) || (this.popup.isDisposed()))
			return;
		if (paramBoolean)
			return;
		this.popup.setVisible(false);
	}
//
	public void setVisibleItemCount(int paramInt) {
		checkWidget();
		if (paramInt < 0)
			return;
		this.visibleItemCount = paramInt;
	}

//	public boolean traverse(int paramInt) {
//		if ((paramInt == 64) || (paramInt == 16))
//			return this.text.traverse(paramInt);
//		return super.traverse(paramInt);
//	}
	
	class ShowButton extends CSWidget {

		private String text;
		private boolean isBorder = true;
		private static final int BTN_WIDTH = 20;
		public ShowButton(Composite paramComposite, String text) {
			super(paramComposite);
			this.text = text;
			setBackground(new Color(null, 255,255,255));
		}

		@Override
		protected void drawWidget(GC gc) {
			Point size = getSize();
			gc.setAntialias(SWT.ON);
			gc.setBackground(new Color(getDisplay(), 100, 100,100));
			Point stringExtent = gc.stringExtent(text);
			gc.setLineWidth(2);
			int startX = 5;
			int startY = (size.y/2) - (stringExtent.y / 2);
			
			gc.drawString(text, startX, startY, true);
			gc.fillPolygon(new int[]{startX  + stringExtent.x + 4, size.y/2-3,
									startX  + 12 + stringExtent.x, size.y/2-3,
									startX + stringExtent.x + 8, size.y/2 + 3});
			
			if (isBorder) {
				gc.setForeground(new Color(getDisplay(), 201, 201, 201));
				gc.drawRoundRectangle(0, 0, size.x-1, size.y-1, 6, 6);
			}
			
		}
		
		@Override
		public Point computeSize(int paramInt1, int paramInt2, boolean paramBoolean) {
			GC gc = new GC(getDisplay());
			Point stringExtent = gc.stringExtent(text);
			gc.dispose();
			return super.computeSize(stringExtent.x + BTN_WIDTH + 10 , stringExtent.y +10  , paramBoolean);
		}
		
		public void setText(String text) {
			this.text = text;
		}
		
		public String getText() {
			return text;
		}
		
		public boolean isBorder() {
			return isBorder;
		}
		
		public void setBorder(boolean isBorder) {
			this.isBorder = isBorder;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(0x800000);
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(300, 300);
		shell.setBackground(new Color(null, 255,255,255));
		shell.setLayout(new GridLayout(1, false));
		CCCombo csCombo = new CCCombo(shell,SWT.BORDER |SWT.READ_ONLY);
//		csCombo.add("asdadadas");
		
		 Combo combo = new Combo(shell, SWT.DROP_DOWN);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		combo.setItems(new String[]{"111","2222"});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}