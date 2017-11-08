package com.nixsolutions.laboratoryseventeen.tag;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;


public class TableTag extends BodyTagSupport {

	private static final long serialVersionUID = 7900197099836654272L;
	private List<UserEntity> users;
	private final static Logger LOG = LoggerFactory.getLogger(BodyTagSupport.class);

	public int doStartTag() throws JspException {
		pageContext.getRequest().getServerName();
		JspWriter out = pageContext.getOut();
		StringBuilder s = new StringBuilder();
		s.append("<table class=\"table table-bordered\">");
		s.append("<thead>");
		s.append("<tr>");
		s.append("<th>");
		s.append("Login");
		s.append("</th>");
		s.append("<th>");
		s.append("First name");
		s.append("</th>");
		s.append("<th>");
		s.append("Last name");
		s.append("</th>");
		s.append("<th>");
		s.append("Age");
		s.append("</th>");
		s.append("<th>");
		s.append("Role");
		s.append("</th>");
		s.append("<th>");
		s.append("Actions");
		s.append("</th>");
		s.append("</tr>");
		s.append("</thead>");
		s.append("<tbody>");
		for (UserEntity user : users) {
			s.append("<tr>");
			s.append("<td>");
			s.append(user.getLogin());
			s.append("</td>");
			s.append("<td>");
			s.append(user.getFirstName());
			s.append("</td>");
			s.append("<td>");
			s.append(user.getLastName());
			s.append("</td>");
			s.append("<td>");
			s.append(user.getAge());
			s.append("</td>");
			s.append("<td>");
			s.append(user.getRole().getName());
			s.append("</td>");
			s.append("<td>");
			s.append("<a href=\"" + ((HttpServletRequest) pageContext.getRequest()).getContextPath()
					+ "/admin/updateUserForm?id=" + user.getId() + "\">Edit</a>");
			s.append(" ");
			s.append("<a href=\"" + ((HttpServletRequest) pageContext.getRequest()).getContextPath()
					+ "/admin/removeUser?id=" + user.getId()
					+ "\" onclick=\"return confirm('Are you sure?');\">Delete</a>");
			s.append("</td>");
			s.append("</tr>");
		}
		s.append("<tbody>");
		s.append("</table>");
		try {
			out.println(s.toString());
		} catch (IOException e) {
			LOG.error("IOException while printing users table", e);
			throw new RuntimeException(e);
		}
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * Set list of users.
	 * 
	 * @param users
	 *            list of users
	 */
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
}