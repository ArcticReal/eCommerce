package com.skytala.eCommerce.entity;

import java.util.LinkedList;

public class ShoppingCart {

	private LinkedList<Position> positions = new LinkedList<>();

	public ShoppingCart() {

	}

	public LinkedList<Position> getPositions() {
		return positions;
	}

	public void setPositions(LinkedList<Position> positions) {
		this.positions = positions;
	}

	public void addPosition(Position newPosition) {
		positions.add(newPosition);
	}

	public boolean removePosition(Position position) {
		return positions.remove(position);
	}

	public void changePosition(Position oldPosition, Position newPosition) {
		positions.remove(oldPosition);
		positions.add(newPosition);
	}

}
