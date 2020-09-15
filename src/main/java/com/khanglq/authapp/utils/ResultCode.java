package com.khanglq.authapp.utils;


/**
 * <p> Response code enumeration-refer to the semantics of HTTP status codes </p>
 *
 * @description:
 * @date: 2019/8/22 11:09
 */
public enum ResultCode {
    //success
    SUCCESS( 200, "SUCCESS" ),
    //failure
    FAILURE( 400, "FAILURE" ),
    //Not certified (signature error, token error)
    UNAUTHORIZED( 401, "Uncertified or invalid Token" ),
    //Not certified
    USER_UNAUTHORIZED( 402, "User name or password is incorrect" ),
    //The interface does not exist
    NOT_FOUND( 404, "Interface does not exist" ),
    //Server internal error
    INTERNAL_SERVER_ERROR( 500, "Server Internal Error" );

    private int code;
    private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
