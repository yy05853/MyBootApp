package com.tuyano.springboot;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HeloController {

	@Autowired
	MyDataRepository repository;

	@PersistenceContext
	EntityManager entityManager;

	MyDataDaoImple dao;

	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public ModelAndView ajax(ModelAndView mav) {
		mav.setViewName("ajax");
		mav.addObject("hello4Form", new Hello4Form());
		return mav;
	}

	@RequestMapping(value = "/ajax2", method = RequestMethod.GET)
	@ResponseBody
	public String getJsonData(ModelMap model) {
	     Gson gson = new Gson();
	     return gson.toJson("JSONで値受け渡しのテスト");
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("formModel", new FormModel());
		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editRun(@Validated FormModel form, BindingResult br,ModelAndView mav) {

        if (br.hasErrors()) {
    		mav.setViewName("edit");
            return mav;
        }
		mav.setViewName("edit");
        return mav;
	}


	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyDataのサンプルです。");
		mav.addObject("value","");
		Iterable<MyData> list = dao.getAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView searc(HttpServletRequest request, ModelAndView mav) {
		mav.setViewName("find");
		String param = request.getParameter("fstr");
		if(param == "") {
			mav = new ModelAndView("redirect:/find");
		} else {
			mav.addObject("title","Find result");
			mav.addObject("msg","「" + param + "」の検索結果");
			mav.addObject("value", param);
			List<MyData> list = dao.find(param);
			mav.addObject("datalist",list);
		}
		return mav;
	}

	@PostConstruct
	public void init() {
		dao = new MyDataDaoImple(entityManager);
//		// 1つ目のダミーデータ作成
//		MyData d1 = new MyData();
//		d1.setName("tuyano");
//		d1.setAge(123);
//		d1.setMail("syoda@tuyano.com");
//		d1.setMemo("090999999");
//		repository.saveAndFlush(d1);
//		// 2つ目のダミーデータ作成
//		MyData d2 = new MyData();
//		d2.setName("hanako");
//		d2.setAge(15);
//		d2.setMail("hanako@flower");
//		d2.setMemo("080888888");
//		repository.saveAndFlush(d2);
//		// 3つ目のダミーデータ作成
//		MyData d3 = new MyData();
//		d3.setName("sachiko");
//		d3.setAge(37);
//		d3.setMail("sachico@happy");
//		d3.setMemo("070777777");
//		repository.saveAndFlush(d3);

	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg","this is sample content.");
		Iterable<MyData> list = dao.getAll();
		mav.addObject("datalist",list);
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