
package com.example.captprice.womaniya.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("data")
    @Expose
    private Data_ data;

    private Image image;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data_ getData() {
        return data;
    }

    public Image getImage() {
        return image;
    }

    public void setData(Data_ data) {
        this.data = data;
    }

}
