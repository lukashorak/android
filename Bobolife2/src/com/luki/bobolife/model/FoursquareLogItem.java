package com.luki.bobolife.model;

public class FoursquareLogItem {

	public FoursquareLogItem() {

	}

	private String checkinId;
	private String[] itemsBought;
	private Integer spending;

	public String getCheckinId() {
		return checkinId;
	}

	public void setCheckinId(String checkinId) {
		this.checkinId = checkinId;
	}

	public String[] getItemsBought() {
		return itemsBought;
	}

	public void setItemsBought(String[] itemsBought) {
		this.itemsBought = itemsBought;
	}

	public Integer getSpending() {
		return spending;
	}

	public void setSpending(Integer spending) {
		this.spending = spending;
	}

}
