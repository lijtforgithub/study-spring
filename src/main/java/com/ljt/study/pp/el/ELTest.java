package com.ljt.study.pp.el;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.*;

/**
 * @author LiJingTang
 * @date 2022-03-15 16:27
 */
@Slf4j
class ELTest {

    private final ExpressionParser parser = new SpelExpressionParser();

    @Test
    void test01() {
        Expression exp = parser.parseExpression("'Hello World'");
        String value = (String) exp.getValue();
        log.info(value);
    }

    @Test
    void test02() {
        Expression exp = parser.parseExpression("'Hello World'.concat(' !!!')");
        String value = (String) exp.getValue();
        log.info(value);
    }

    @Test
    void test03() {
        Expression exp = parser.parseExpression("'Hello World'.bytes");
        byte[] value = (byte[]) exp.getValue();
        log.info(Arrays.toString(value));
    }

    @Test
    void test04() {
        Expression exp = parser.parseExpression("'Hello World'.bytes.length");
        int value = (Integer) exp.getValue();
        log.info(String.valueOf(value));
    }

    @Test
    void test05() {
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String value = exp.getValue(String.class);
        log.info(value);
    }

    @Test
    void test06() {
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, Calendar.AUGUST, 9);

        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        Expression exp = parser.parseExpression("name");
        String name = (String) exp.getValue(tesla);
        log.info(name);

        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class);
        log.info(String.valueOf(result));
    }

    @Test
    void test07() {
        Simple simple = new Simple();
        simple.booleanList.add(true);
        log.info(simple.booleanList.get(0).toString());

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        parser.parseExpression("booleanList[0]").setValue(context, simple, "false");

        log.info(simple.booleanList.get(0).toString());
    }

    @Test
    void test08() {
        // Turn on:
        // - auto null reference initialization
        // - auto collection growing
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);

        ExpressionParser expParser = new SpelExpressionParser(config);
        Expression exp = expParser.parseExpression("list[3]");
        Demo demo = new Demo();
        Object o = exp.getValue(demo);
        log.info(o.toString());
    }

    @Test
    void test09() {
        SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, this.getClass().getClassLoader());
        SpelExpressionParser expParser = new SpelExpressionParser(config);
        Expression exp = expParser.parseExpression("payload");

        Message<String> message = new Message<String>() {
            @Override
            public String getPayload() {
                return "OK";
            }

            @Override
            public MessageHeaders getHeaders() {
                return null;
            }
        };

        Object value = exp.getValue(message);
        log.info(value.toString());
    }

}

@Getter
@AllArgsConstructor
class Inventor {
    private String name;
    private Date date;
    private String product;
}

class Simple {
    public List<Boolean> booleanList = new ArrayList<>();
}

class Demo {
    public List<String> list;
}