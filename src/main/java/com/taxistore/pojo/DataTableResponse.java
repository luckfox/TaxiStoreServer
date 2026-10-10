package com.taxistore.pojo;

import java.util.List;

public class DataTableResponse<T> {
    private int draw; // DataTables 會傳來這個參數，回傳時需保持一致
    private int recordsTotal; // 總記錄數
    private int recordsFiltered; // 搜尋過濾後的記錄數
    private List<T> data; // 實際回傳的數據

    public DataTableResponse() {
    }

    public DataTableResponse(int draw, int recordsTotal, int recordsFiltered, List<T> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
