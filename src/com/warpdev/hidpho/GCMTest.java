package com.warpdev.hidpho;

import android.os.*;
import com.google.android.gcm.server.*;
//import javax.xml.transform.*;
import com.google.android.gcm.server.Message;
//import javax.xml.transform.Result;
import com.google.android.gcm.*;
import com.google.android.gcm.GCMRegistrar;
import java.io.*;

public class GCMTest
 {
	public static void main(String[] args) throws Exception {
		try {
			String sendTlt = "stoneis.pe.kr~~";
			String sendMsg = "blog.naver.com/musasin84";
			Sender sender = new Sender("326810664960");
			Message message = new Message.Builder()
			// GCM 서버에서 중복 메시지를 보낼 경우 일부 수신이 안될 수 있다.
			//중복으로 보내는 메시지래도 꼭 보낼 수 있게 랜덤 고유 키 생성
				.collapseKey(String.valueOf(Math.random() % 100 + 1))
				// 전송할 DATA
				.addData("title", sendTlt)
				.addData("contents", sendMsg)
			// 기기가 활성화 상태일때 보여줄것인지? true라면 절전모드시 기기가 
			//비활성화 일때 알림창이 안뜬다.
			.delayWhileIdle(false)
				// 초, 단말기가 비활성화 상태일때 메시지 유효시간
				.timeToLive(3)
				.build();
			// 3번째 인자는 메시지 전송실패시 재시도 횟수
			Result result = sender.send(message, "RegistrationID", 3);
			if (result.getMessageId() != null) {
				System.out.println("Send success");
			} else {
				String error = result.getErrorCodeName();
				System.out.println("Send fail : " + error);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
