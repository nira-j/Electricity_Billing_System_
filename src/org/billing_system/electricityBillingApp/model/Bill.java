package org.billing_system.electricityBillingApp.model;

public class Bill {
	private String startDate;
	private String endDate;
	private int totalAmount;
	private int energyConsumed;
	private int startReading;
	private int endReading;

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getEnergyConsumed() {
		return energyConsumed;
	}
	public void setEnergyConsumed(int energyConsumed) {
		this.energyConsumed = energyConsumed;
	}
	public int getStartReading() {
		return startReading;
	}
	public void setStartReading(int startReading) {
		this.startReading = startReading;
	}
	public int getEndReading() {
		return endReading;
	}
	public void setEndReading(int endReading) {
		this.endReading = endReading;
	}

}
