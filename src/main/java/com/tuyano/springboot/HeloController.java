package com.tuyano.springboot;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HeloController {
	String[] names = {"tuyano",
			"hanako","taro",
			"sachiko", "ichiro"};
	String[] mails = {"syoda@tuuyano.com",
			"hanako@flower","taro@yamda",
			"sachiko@happy", "ichiro@baseball"};

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(new String[] {"taro","taro@yamada","090-999-999"});
		data.add(new String[] {"hanako","hanako@flower","080-888-888"});
		data.add(new String[] {"sachiko","sachiko@happy","080-888-888"});

		mav.addObject("data",data);
		return mav;
	}
}


class DataObject {
	private int id;
	private String name;
	private String value;

	public DataObject(int id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getValue() { return value; }

	public void setValue(String value) { this.value = value; }
}