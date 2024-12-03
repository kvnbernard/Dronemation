package data;

import java.awt.Color;

public class Tree extends Element {

	private boolean isLeftTree=true;
	private boolean isRightTree=true;
	private boolean isUpTree=true;
	private boolean isDownTree=true;
	private boolean isContour=false;
	private boolean isfire=false;

	public Tree(Position position) {
		super(position, Color.green);
	}

	public boolean isLeftTree() {
		return isLeftTree;
	}

	public void setLeftTree(boolean leftTree) {
		isLeftTree = leftTree;
	}

	public boolean isRightTree() {
		return isRightTree;
	}

	public void setRightTree(boolean rightTree) {
		isRightTree = rightTree;
	}

	public boolean isUpTree() {
		return isUpTree;
	}

	public void setUpTree(boolean upTree) {
		isUpTree = upTree;
	}

	public boolean isDownTree() {
		return isDownTree;
	}

	public void setDownTree(boolean downTree) {
		isDownTree = downTree;
	}

	public boolean isContour() {
		return isContour;
	}

	public void setContour(boolean contour) {
		isContour = contour;
	}

	public boolean isIsfire() {
		return isfire;
	}

	public void setIsfire(boolean isfire) {
		this.isfire = isfire;
	}

}
