package data;

import java.awt.Color;

public abstract class Element  {
		private Position position;
		private Color color;

		private boolean isVisit=false;
		
		//We define color directly in child-class, can be moved to config later
		public Element(Position position, Color color) {
			this.position=position;
			this.color = color;
		}
		
		public Position getPosition() {
			return position;
		}

		public void setPosition(Position position) {
			this.position = position;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}


	public boolean isVisit() {
		return isVisit;
	}

	public void setVisit() {
		isVisit = true;
	}

	//NOT SURE IF CAN WORK LIKE WE WANT
	public boolean ifSameType(Element check){
			return this.getColor().equals(check.getColor());
	}

	@Override
	public String toString() {
		return "Element{" +
				"position=" + position +
				'}';
	}
}
