import React, { useState } from "react";
import s from "./AddFriend.module.css";
import { axiosInstance } from "../../../../utils/axiosInstance";
import API from "../../../../utils/API/API";

const AddFriend = ({ onBackClick }) => {
  const [email, setEmail] = useState("");
  const [error, setError] = useState("");
  const [isAddFriendSuccess, setIsAddFriendSuccess] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const isValidEmail = validateEmail(email);
    if (!isValidEmail) {
      setError("올바른 이메일을 입력해주세요.");
      return;
    }
    setError("");
    try {
      const response = await axiosInstance.post(API.ADD_FRIEND(email));
      console.log("친구 요청 성공", response.data);
      setIsAddFriendSuccess(true);
      setEmail("");
    } catch (err) {
      console.error("친구 요청 실패");
    }
    console.log("친구 추가:", email);
  };

  const handleChange = (e) => {
    setEmail(e.target.value);
    setError("");
  };

  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  return (
    <div className={s.wrapper}>
      <div className={s.des}>
        <h3 className={s.h3}>이메일로 친구를 추가할 수 있어요</h3>
        <h4 className={s.h4}>함께 여행할 친구를 찾아보세요.</h4>
      </div>

      <div className={s.container}>
        <form className={s.form} onSubmit={handleSubmit} noValidate>
          <div className={s.inputContainer}>
            <input
              type="email"
              placeholder="친구의 이메일을 입력해요!"
              className={s.input}
              value={email}
              onChange={handleChange}
            />
            <button type="submit" className={s.addFriendBtn}>
              친구 추가
            </button>
          </div>
        </form>
      </div>

      {error && (
        <p className={s.error}>
          <h4>{error}</h4>
        </p>
      )}
      {isAddFriendSuccess && (
        <p className={s.success}>
          <h4>친구추가가 완료되었습니다!</h4>
        </p>
      )}
      <button onClick={onBackClick} className={s.backBtn}>
        돌아가기
      </button>
    </div>
  );
};

export default AddFriend;
