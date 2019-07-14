package cn.waggag.travle.web.servlet;

import cn.waggag.travle.domain.Category;
import cn.waggag.travle.domain.User;
import cn.waggag.travle.service.CategoryService;
import cn.waggag.travle.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/14 0:20
 */
@WebServlet("/category/*")
public class CateGoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用Service查询List
        List<Category> categoryList = categoryService.findAll();
        //2.将List集合序列化
//        String json = writeValueAsString(categoryList);
//        response.setContentType("applicatin/json;charset=utf-8");
//        response.getWriter().write(json);
        writeValue(categoryList, response);
    }
}
