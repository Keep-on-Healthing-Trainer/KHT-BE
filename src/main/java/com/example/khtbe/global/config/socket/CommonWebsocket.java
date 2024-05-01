//package com.example.khtbe.global.config.socket;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.RemoteEndpoint;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//@Controller
//@ServerEndpoint(value="/qrEcho.action")
//public class CommonWebsocket {
//
//    public static final List<Session> sessionLists = new ArrayList<Session>();
//    public static final Map<String,String> guidLists = new HashMap<String,String>();
//    public static final Map<String,String> guidUserSeqMap = new HashMap<String,String>();
//
//    private static final Logger logger = LoggerFactory.getLogger(CommonWebsocket.class);
//
//    //소켓통신 객체만 생성
//    public CommonWebsocket() {
//        // TODO Auto-generated constructor stub
//    }
//
//    //소켓통신 직접 연결
//    @OnOpen
//    public void onOpen(Session session) {
//        logger.info("Open session id:" + session.getId());
//
//        try {
//            final RemoteEndpoint.Basic basic = session.getBasicRemote();
//            String uuid = UUID.randomUUID().toString();//uuid 생성
//            guidLists.put(uuid, session.getId());//uuid 가 key의 역할을 수행
//
//            basic.sendText("gen,"+uuid);
//            sessionLists.add(session);//새로운 세션 등록
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @OnMessage
//    public void onMessage(String message,Session session) {
//
//
//
//        try {
//            final RemoteEndpoint.Basic basic = session.getBasicRemote();
//            basic.sendText("pass," + message);
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.out.println(e.getMessage());
//        }
//
//        sendAllSessionToMessage(session,message);
//    }
//
//    private void sendAllSessionToMessage(Session self, String message) {
//
//        try {
//            for(Session session : CommonWebsocket.sessionLists) {
//                if(self.getId().equals(session.getId())) {
//                    session.getBasicRemote().sendText("message : " + message);
//                }
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @OnError
//    public void onError(Throwable e, Session session) {
//        System.out.println(e.getCause());
//    }
//
//    //소켓 닫기
//    @OnClose
//    public void onClose(Session session) {
//        logger.info("Session " + session.getId() + " has ended");
//        guidLists.remove(session.getId());
//        sessionLists.remove(session);
//        guidUserSeqMap.remove(session.getId());
//    }
//
//}