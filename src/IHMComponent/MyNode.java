package IHMComponent;

import java.util.ArrayList;

public class MyNode {
	public String name;
	public Object o;
	private ArrayList<MyNode> child = new ArrayList<MyNode>();
	public MyNode(Object o, String name) {
		this.name = name;
		this.o = o;
	}
	
	public void add(MyNode n) {
		child.add(n);
	}
	
	public int getChildCount() {
		return child.size();
	}
	
	public MyNode getChildAt(int index) {
		return child.get(index);
	}
	
	public Object getUserObject() {
		return o;
	}
	public void setUserObject(Object o) {
		this.o = o;
	}
	public String getUserObjectName() {
		return name;
	}
	public void setUserObjectName( String name) {
		this.name = name;
	}
	public boolean isLeaf() {
		return (child.size() == 0);
	}

}
