package com.hojin.mywep.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	//get set이 없는데 접근할수있게 되는 이유가 @Data가 접근때마다 자동으로 만들어준다.

  private Long POST_NO;
  private String POST_TITLE;
  private String POST_CONTENT;
  private String ID;
  private Date REGDATE;
  private Date UPDATEDATE;
  private char USERCHECK;
	
}
