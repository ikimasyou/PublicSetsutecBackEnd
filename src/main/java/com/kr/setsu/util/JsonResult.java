package com.kr.setsu.util;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {
	 /** じょうたいコード */
    private Integer state;
    /** 状態記述情報 */
    private String message;
    /** データ */
    private E data;

    public JsonResult() {
        super();
    }
    
    public JsonResult(HttpStatus hs) {
    	super();
    	this.state = hs.getCode();
    	this.message = hs.getMessage();
    }

    public JsonResult(Integer state) {
        super();
        this.state = state;
    }

    /** 例外が発生した場合に呼び出す */
    public JsonResult(Throwable e) {
        super();
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        super();
        this.state = state;
        this.data = data;
    }

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

    
}
