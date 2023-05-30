package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/css/*","/image/*","/image"})
public class ResourceAccessFilter  implements Filter {
	
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Lấy đường dẫn của tệp tài nguyên từ URL
        String resourcePath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // Kiểm tra quyền truy cập của người dùng
        boolean hasAccess = checkUserAccess(httpRequest);

        if (hasAccess) {
            // Cho phép truy cập vào tệp tài nguyên
            chain.doFilter(request, response);
        } else {
        	//chain.doFilter(request, response);
            // Người dùng không có quyền truy cập, chuyển hướng đến trang lỗi hoặc trả về mã lỗi 403 Forbidden
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        	
        }
    }

    // Phương thức kiểm tra quyền truy cập của người dùng
    private boolean checkUserAccess(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
       // return false;
        return  session.getAttribute("id") != null;
        
    }
    // Các phương thức khác của interface Filter
    
    
    
    
    
    
    
    
    
}