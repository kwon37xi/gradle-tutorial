package gradletutorial.servlet;

import gradletutorial.Greeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	private Logger logger = LoggerFactory.getLogger(HomeServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("to", Greeting.TO);

		logger.debug("Greetings to {}", Greeting.TO);

		req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);

	}
}
