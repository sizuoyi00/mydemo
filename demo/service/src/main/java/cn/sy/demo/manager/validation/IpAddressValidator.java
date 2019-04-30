package cn.sy.demo.manager.validation;

import cn.sy.demo.dao.demo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义校验功能
 */
class IpAddressValidator implements ConstraintValidator<IpAddress, String> {

    /**
     * 这里可以通过调用数据库做参数验证
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        //测试下能否注入到userMapper --可以
        userMapper.selectByPrimaryKey(1L);

        Pattern pattern = Pattern.compile("^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$");
        Matcher matcher = pattern.matcher(value);
        try {
            if (!matcher.matches()) {
                return false;
            } else {
                for (int i = 1; i <= 4; i++) {
                    int octet = Integer.valueOf(matcher.group(i));
                    if (octet > 255) {
                        return false;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}