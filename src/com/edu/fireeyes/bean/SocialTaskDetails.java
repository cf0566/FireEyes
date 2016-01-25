package com.edu.fireeyes.bean;

public class SocialTaskDetails {
	private int code;
    private String msg;
    private SocialTaskDetailsData data;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }
	public SocialTaskDetailsData getData() {
		return data;
	}
	public void setData(SocialTaskDetailsData data) {
		this.data = data;
	}
	public SocialTaskDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SocialTaskDetails [code=" + code + ", msg=" + msg + ", data="
				+ data + "]";
	}

}
