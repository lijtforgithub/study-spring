package old.execption;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiJingTang
 * @date 2020-01-04 20:07
 */
public class CustomExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) {
        if (e instanceof NumberFormatException) {
            // doSomething...
            return new ModelAndView("exception/number", "exception", e);
        } else if (e instanceof NullPointerException) {
            // doSomething...
            return new ModelAndView("exception/null", "exception", e);
        }

        return new ModelAndView("exception/exception", "exception", e);
    }

}