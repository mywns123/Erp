package Erp.service;

import java.util.List;

import Erp.dao.TitleDao;
import Erp.dao.impl.TitleDaoImpl;
import Erp.dto.Title;

public class TitleService {
	private TitleDao dao = TitleDaoImpl.getInstance();
	
	public List<Title> showTitles(){
		return dao.selectTitleByAll();
		}
	public void addTitle(Title title) {
		dao.insertTitle(title);
	}
	
	public void removeTitle(Title title) {
		dao.deleteTitle(title.gettNo());
	}
	
	
}
