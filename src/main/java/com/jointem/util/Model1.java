package com.jointem.util;

import com.jointem.annotation.StringParamAnotation;

/**
 * * author wangwei
 * * CREATE ON 2018/1/6 9:03
 * * DECRIPTION
 * * WWW.JOINTEM.COM
 **/
public class Model1 extends Model {
    @StringParamAnotation
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
