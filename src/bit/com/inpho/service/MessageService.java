package bit.com.inpho.service;

import java.util.List;

import bit.com.inpho.dto.MessageDto;
import bit.com.inpho.dto.MessageUserDto;

public interface MessageService {

	int sendMsg(MessageDto msg);
	
	List<MessageDto> getMsg(MessageDto msg);
	
	List<MessageUserDto> getUserList(int user_seq);
	
	boolean checkList(MessageDto msg);
	
	int setOpen(MessageDto msg);
	
	boolean getUnread(int user_seq);
}

