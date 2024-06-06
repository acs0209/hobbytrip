import create from "zustand";
import * as StompJs from "@stomp/stompjs";
import SockJS from "sockjs-client";

const useWebSocketStore = create((set, get) => ({
  client: null,
  isConnected: false,
  connect: (userId) => {
    const stompClient = new StompJs.Client({
      webSocketFactory: () => new SockJS("https://fittrip.site/stomp/ws-stomp"),
      debug: (str) => console.log(str),
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      connectHeaders: {
        userId: userId,
        Authorization: "Bearer " + localStorage.getItem("accessToken"),
      },
    });

    stompClient.activate();
    set({ client: stompClient, isConnected: true });
    console.log("소켓 클라이언트 생성 성공");
  },
  disconnect: () => {
    const { client } = get();
    if (client && client.connected) {
      client.deactivate();
      set({ client: null, isConnected: false });
    }
  },
}));

export default useWebSocketStore;
