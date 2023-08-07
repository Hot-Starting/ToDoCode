import { useRouter } from "next/router";
import React, { useEffect } from "react";

const Callback = () => {
  const router = useRouter();
  // console.log(router.query.token);
  useEffect(() => {
    const getAccessToken = () => {
      const location = new URL(window.location.href);
      const token = location.searchParams.get("token");

      if (token) {
        router.push({ pathname: "/", query: { token: token } });
      } else {
        console.log("토큰 없음");
      }
    };
    getAccessToken();
  }, []);
  return <div>로딩중 ...</div>;
};

export default Callback;
