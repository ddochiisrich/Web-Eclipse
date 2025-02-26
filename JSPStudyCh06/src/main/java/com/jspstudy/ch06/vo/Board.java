package com.jspstudy.ch06.vo;

import java.sql.Timestamp;

/* 하나의 게시 글 정보를 저장하는 VO(Value Object)
 * VO 객체에 저장될 데이터는 테이블에서 읽어오기 때문에 각각의 변수는
 * 테이블의 컬럼이 가지는 데이터 형식과 같거나 자동 형 변환이 가능해야 한다.
 **/
public class Board {
	// no, title, writer, content, reg_date, read_count, pass, file1
	private int no;
	private String title;
	private String writer;
	private String content;	
	private Timestamp regDate;
	private int readCount;
	private String pass;
	private String file1;
	
	public Board() { }
	public Board(int no, String title, String writer, String content,
			Timestamp regDate, int readCount, String pass, String file1) {
		this.no = no;
		this.title = title;		
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
		this.pass = pass;
		this.file1 = file1;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
}
