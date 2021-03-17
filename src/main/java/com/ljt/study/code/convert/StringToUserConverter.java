package com.ljt.study.code.convert;

import com.ljt.study.entity.User;
import org.springframework.core.convert.converter.Converter;

import static com.ljt.study.code.editor.AddressEditor.SEP;

/**
 * @author LiJingTang
 * @date 2021-03-17 17:42
 */
public class StringToUserConverter implements Converter<String, User> {

    @Override
    public User convert(String source) {
        String[] array = source.split(SEP);
        User user = new User();
        user.setId(Integer.parseInt(array[0]));
        user.setName(array[1]);
        return user;
    }

}
