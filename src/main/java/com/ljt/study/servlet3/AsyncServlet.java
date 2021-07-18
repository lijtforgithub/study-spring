package com.ljt.study.servlet3;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJingTang
 * @date 2021-07-18 13:11
 */
@WebServlet(value = "/async", asyncSupported = true)
class AsyncServlet extends HttpServlet {

    private static final long serialVersionUID = -8640546843844346413L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AsyncContext context = req.startAsync();

        context.start(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                req.getAsyncContext().complete();
                final ServletResponse response = req.getAsyncContext().getResponse();
                try {
                    response.getWriter().print("异步处理");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
