import { create } from "zustand";
import axios from "axios";
import API from "../utils/API/API";

const useUserStore = create((set) => ({
  USER: {},
  notificationEnabled: false,
  setUserInfo: (user) => set({ USER: user }),
  updateUserInfo: async (endpoint, updates) => {
    try {
      console.log(localStorage.getItem("accessToken"));
      const response = await axios.patch(
        API.UPDATE_PROFILE(endpoint),
        updates,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
          },

          withCredentials: true,
        }
      );
      if (response.data) {
        set((state) => ({
          ...state,
          ...updates,
        }));
      } else {
        console.error("Failed to update user data");
      }
    } catch (error) {
      console.log("Error updating user data:", error);
    }
  },
  setNotificationEnabled: (enabled) =>
    set(() => ({ notificationEnabled: enabled })),
}));

export default useUserStore;
