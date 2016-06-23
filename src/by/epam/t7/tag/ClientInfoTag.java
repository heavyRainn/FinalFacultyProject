package by.epam.t7.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class ClientInfoTag extends TagSupport {

	private String name;
	private String surname;
	private String language;
	
	private final String EN_WELCOME = "Welcome, ";
	private final String RU_WELCOME = "Добро пожаловать, ";

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setLocale(String language) {
		this.language = language;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if (language.equalsIgnoreCase("en_EN")) {
				pageContext.getOut().write(EN_WELCOME + name + " " + surname + "!");
			} else {
				pageContext.getOut().write(RU_WELCOME + name + " " + surname + "!");
			}
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}