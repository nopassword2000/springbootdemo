package com.mybatis.filter;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PageFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpRequest = request;

        int pageNum = getPageNum(httpRequest);
        int pageSize = getPageSize(httpRequest);

        if (pageNum > 0 && pageSize > 0){
            PaginationContext.setPageNum(pageNum);
            PaginationContext.setPageSize(pageSize);
        }

        try {
            filterChain.doFilter(request, response);
        }
        // 使用完Threadlocal，将其删除。使用finally确保一定将其删除
        finally {
            PaginationContext.removePageNum();
            PaginationContext.removePageSize();
        }
    }

    /**
     * 获得pager.offset参数的值
     *
     * @param request
     * @return
     */
    protected int getPageNum(HttpServletRequest request) {
        int pageNum = -1;
        try {
            String pageNums = request.getParameter("pageNum");
            if (pageNums != null && StringUtils.isNumeric(pageNums)) {
                pageNum = Integer.parseInt(pageNums);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return pageNum;
    }

    /**
     * 设置默认每页大小
     *
     * @return
     */
    protected int getPageSize(HttpServletRequest request) {
        int pageSize = -1;    // 默认每页10条记录
        try {
            String pageSizes = request.getParameter("pageSize");
            if (pageSizes != null && StringUtils.isNumeric(pageSizes)) {
                pageSize = Integer.parseInt(pageSizes);
            }

            if (pageSize > 5000){

                pageSize = 10;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return pageSize;
    }
}
