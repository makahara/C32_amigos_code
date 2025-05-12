package model;

import java.util.List;

import dao.DiariesDAO;

public class GetDiaryListLogic {
	public List<Mutter> execute() {
		DiariesDAO dao = new DiariesDAO();
		List<Mutter> diaryList = dao.findAll();
		return diaryList;
	}
}