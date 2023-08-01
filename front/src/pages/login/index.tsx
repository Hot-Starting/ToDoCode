import axios from "axios";
import Image from "next/image";
import googleLoginImg from "@/../public/btn_google_signin_dark_normal_web@2x.png";
import githubLoginImg from "@/../public/githubImage.png";

export default function Login() {
  const redirectUrl = "http://localhost:3000";

  // const googleUrl = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${process.env.NEXT_PUBLIC_GOOGLE_ID}&redirect_uri=${redirectUrl}&response_type=code&scope=email profile`;
  const googleUrl =
    "http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:3000/oauth/redirect";

  // const githubUrl = `/github/login/oauth/authorize?client_id=${process.env.NEXT_PUBLIC_GITHUB_ID}&redirect_url=${redirectUrl}`;
  const githubUrl =
    "http://localhost:8080/oauth2/authorization/github?redirect_uri=http://localhost:3000/oauth/redirect";

  return (
    <>
      <div>
        <h1>a 태그</h1>
        <a href={googleUrl}>구글</a>
        <br />
        <a href={githubUrl}>깃허브</a>
      </div>
      <hr />
      <div>
        <h1>GET 요청</h1>
        <Image
          src={googleLoginImg}
          alt=""
          style={{ cursor: "pointer" }}
          onClick={() => {
            axios({
              method: "get",
              url: "http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:3000/oauth/redirect",
            }).then((response) => console.log(response));
          }}
        />
        <Image
          src={githubLoginImg}
          alt=""
          style={{ cursor: "pointer" }}
          onClick={() => {
            axios({
              method: "get",
              url: "http://localhost:8080/oauth2/authorization/github?redirect_uri=http://localhost:3000/oauth/redirect",
            }).then((response) => console.log(response));
          }}
        />
      </div>
    </>
  );
}
