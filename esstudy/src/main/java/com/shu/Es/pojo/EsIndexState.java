package com.shu.Es.pojo;

/**
 * Es中index状态
 */
public class EsIndexState {
    private String index;
    private String status;
    private String health;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }
}
