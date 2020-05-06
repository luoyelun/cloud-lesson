package icu.thyself.cloudlesson.enums;

/**
 * @author luoyelun
 * @date 2020/5/2 22:17
 */

public enum InformationEnumImpl implements InformationEnum {
    REGISTER_SUCCESS(200, "注册成功，请登录"),
    REGISTER_INFO_EMPTY_OR_SPACE(201, "注册信息含有空格或信息填写不完全"),
    REGISTER_USER_EXITS(202, "用户名已存在"),
    REGISTER_ERROR(203, "注册失败，联系管理员alun96@outlook.com"),
    LOGIN_SUCCESS(204, "登录成功"),
    LOGIN_FAIL(205, "登录失败，请检查账号密码是否正确"),
    ;
    private final Integer code;
    private final String message;

    InformationEnumImpl(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
