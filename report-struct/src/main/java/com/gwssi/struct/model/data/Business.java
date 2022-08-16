package com.gwssi.struct.model.data;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Business {

    private String key;
    private Object data;

    public Business(String key, Object data) {
        this.key = key;
        this.data = data;
    }
}
