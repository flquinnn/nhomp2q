package com.thick124.loplttd03.nhom03.API.Histories.Model;
import java.util.List;

public class HistoryResponse {
    private List<Histories> histories;
    private int totalHistories;

    // Getters and Setters
    public List<Histories> getHistories() {
        return histories;
    }

    public void setHistories(List<Histories> histories) {
        this.histories = histories;
    }

    public int getTotalHistories() {
        return totalHistories;
    }

    public void setTotalHistories(int totalHistories) {
        this.totalHistories = totalHistories;
    }
}