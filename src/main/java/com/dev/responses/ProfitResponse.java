package com.dev.responses;

public class ProfitResponse extends BasicResponse {
    private double profit;

    public ProfitResponse(double profit) {
        this.profit = profit;
    }

    public ProfitResponse(boolean success, Integer errorCode, double profit) {
        super(success, errorCode);
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
