package cn.sy.demo.constant.enums;

import lombok.Getter;

@Getter
public enum CacheConstsEnum {
    USER ("USER", "用户名"),
    DEMO_USER ("DEMO_USER", "特别用户名");

    private String code;
    private String desc;

    CacheConstsEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 正常key使用
    * @param keyNames
     * @return
     */
    public String appendKeyByUnderline(String... keyNames) {
        StringBuilder builder = new StringBuilder(this.code);
        for (String keyName : keyNames) {
            builder.append("_");
            builder.append(keyName);
        }
        return builder.toString();
    }

    /**
     * cache使用
     * @return
     */
    public String getPrefixKey(){
        return this.code + "_";
    }
}
