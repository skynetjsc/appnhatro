package com.skynet.mumgo.ui.chatting;


import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Message;
import com.skynet.mumgo.models.Post;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.network.socket.SocketClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChattingPresenter implements ChattingContract.Presenter {
    ChattingContract.View view;
    ChattingContract.Interactor interactor;
    SocketClient socketClient;
    public ChattingPresenter(ChattingContract.View view) {
        this.view = view;
        interactor = new ChattingRemoteImpl(this);
    }

    @Override
    public void getMessages(int idUser,int hostId,int idPost) {
        view.showProgress();
        Profile profile  = AppController.getInstance().getmProfileUser();
        if(profile==null){
            onErrorAuthorization();
            return;
        }
        interactor.getMessages(hostId,idUser,idPost);
    }

    @Override
    public void sendMessage(int idPost,int idUser, int idShop, String content, SocketClient socketClient,int attach) {
        view.showProgress();
        this.socketClient   = socketClient;
        Profile profile  = AppController.getInstance().getmProfileUser();
        if(profile==null){
            onErrorAuthorization();
            return;
        }
        interactor.sendMessage(idPost,idUser,idShop,content,new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()),attach);
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSuccessGetMessages(List<Message> list,Post post) {
        if(view ==null )return;
        view.hiddenProgress();
        if(list!=null)
        view.onSuccessGetMessages(list,post);

    }

    @Override
    public void onSuccessSendMessage(Message message) {
        if(view ==null )return;
        view.hiddenProgress();
        if(socketClient!=null){
//            SocketResponse data = new SocketResponse();
//            Profile user = new Profile();
//            user.setId(message.getUId());
//            data.setTypeData(SocketClient.TYPE_MESSAGE);
//            data.setUser(user);
//            Shop shop  = new Shop();
//            shop.setId(message.getShId());
//            data.setShop(shop);
//            data.setMessage(message);
//            data.setContentMessage(message.getContent());
//            getSocketClient().sendMessage(data);
        }
        view.onSuccessSendMessage(message);
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    @Override
    public void onErrorApi(String message) {
        if(view ==null )return;
        view.hiddenProgress();
        view.onErrorApi(message);
    }

    @Override
    public void onError(String message) {
        if(view ==null )return;
        view.hiddenProgress();
        view.onError(message);
    }

    @Override
    public void onErrorAuthorization() {
        if(view ==null )return;
        view.hiddenProgress();
        view.onErrorAuthorization();
    }
}
