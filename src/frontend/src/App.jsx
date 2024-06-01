import React, { useEffect } from "react";
import axios from "axios";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import StartView from "./components/StartView/StartView";
import LoginView from "./pages/LoginView/Loginview";
import RegView from "./pages/RegView/RegView";
import UserProfileView from "./pages/UserProfileView/UserProfileView";
import MediaView from "./pages/MediaView/MediaView";
import MenuView from "./pages/MenuView/MenuView.jsx";
import MainView from "./pages/MainView/MainView";
import ChatRoom from "./pages/Chat/ChatRoom/ChatRoom";
import SettingView from "./pages/ServerView/SettingView/SettingView.jsx";
import CategoryView from "./pages/ServerView/CategoryView/CategoryView.jsx";
import DmRoom from  "./pages/Chat/DmRoom/DmRoom.jsx";


axios.defaults.withCredentials = true;

function App() {
  return (
    <Router>
      <Routes>
        {/* <Route path="/" element={<StartView />} />
        <Route path="/login" element={<LoginView />} />
        <Route path="/register" element={<RegView />} />
        <Route path="/user/profile" element={<UserProfileView />} /> */}
        <Route
          path=":serverId/:channelId/chat"
          element={<ChatRoom userId={10} />}
        />
        <Route path="/user/profile" element={<UserProfileView />} />
        <Route path="/main" element={<MainView />} />
        <Route path="/menu" element={<MenuView />} />
        <Route path="/:serverId/setting" element={<SettingView />} />
        <Route path="/:serverId/menu" element={<CategoryView />} />
        <Route path="/:serverId/:channelId/vid" element={<MediaView />} />
        <Route 
          path="/:dmRoomId/chat"
          element={<DmRoom userId={10} />}
        />
      </Routes>
    </Router>
  );
}

export default App;
