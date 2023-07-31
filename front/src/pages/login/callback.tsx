import { cookies } from "next/dist/client/components/headers";
import React, { useEffect } from "react";
import { Create } from "./cookies";

const Callback = () => {
  useEffect(() => {
    const fetchAccessToken = async () => {
      // 쿼리스트링에서 Authorization Code를 가져옵니다.
      const location = new URL(window.location.href);
      const code = location.searchParams.get("code");

      const ACCESS_TOKEN_URL = `/github/login/oauth/access_token?client_id=${process.env.NEXT_PUBLIC_GITHUB_ID}&client_secret=${process.env.NEXT_PUBLIC_GITHUB_SECRET}&code=${code}`;
      console.log("ACCESS_TOKEN_URL", ACCESS_TOKEN_URL);

      return fetch(ACCESS_TOKEN_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      });
    };

    interface data {
      access_token: string;
      token_type: string;
      scope: string;
    }

    fetchAccessToken()
      .then((response) => response.json())
      .then((data: data) => {
        console.log("DATA", data);
        Create(data.access_token);
      })
      .catch((err) => console.log(err));
  });

  return <div>로딩중 ...</div>;
};

export default Callback;
