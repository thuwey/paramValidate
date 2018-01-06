package com.jointem.util;

import com.jointem.annotation.NumberParamAnnonation;
import com.jointem.annotation.StringParamAnotation;

/**
 * * author wangwei
 * * CREATE ON 2018/1/6 9:02
 * * DECRIPTION
 * * WWW.JOINTEM.COM
 **/
public class Model {
    @NumberParamAnnonation
    private Integer id;
    @StringParamAnotation
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
