package tn.MITProject.entities;

public class ChargeRequest {
	
	public enum Currency {
        EUR, USD;
    }
    public String name;
    public String email;
    public String customerId;

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setStripeEmail(String stripeEmail) {
		this.stripeEmail = stripeEmail;
	}
	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}
	private String description;
    private int amount; // cents
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
    public String getDescription() {
        return description;
    }
    public int getAmount() {
        return amount;
    }
    public Currency getCurrency() {
        return currency;
    }
    public String getStripeEmail() {
        return stripeEmail;
    }
    public String getStripeToken() {
        return stripeToken;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

}
