package Erp.ui.content;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public  abstract class InterfaceItem<T> extends JPanel{
	
	public InterfaceItem() {
//		initialize();
	}
	
	public abstract void initialize();
	
	public abstract void setItem (T item);	
	public abstract T getItem();
	public abstract void validCheck();
	public abstract void clearTf();
	
}
