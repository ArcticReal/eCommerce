package com.skytala.eCommerce.entity;

import java.math.BigDecimal;
import java.util.LinkedList;

public class ShoppingCart {

	private LinkedList<Position> positions = new LinkedList<>();
	private BigDecimal grandTotal;

	public ShoppingCart() {

	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
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

	public boolean removebyPosition(int positionInCard) {
		positions.remove(positionInCard);
		return true;
	}

	public void changePosition(Position oldPosition, Position newPosition) {
		positions.remove(oldPosition);
		positions.add(newPosition);
	}

}
